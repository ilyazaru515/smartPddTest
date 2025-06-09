package smartTest.pdd.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smartTest.pdd.question.entity.QuestionEntity;
import smartTest.pdd.question.entity.UserStatsEntity;
import smartTest.pdd.repository.QuestionsAdapter;
import smartTest.pdd.repository.UserStatsAdapter;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SmartQuestionDetectorImpl implements SmartQuestionDetector {
    private final UserStatsAdapter userStatsAdapter;
    private final QuestionsAdapter questionsAdapter;
    @Value("${spring.application.weak-category-answer-probability-percent:10}")
    private int probabilityPercent;
    private final Random random = new Random();

    @Override
    public QuestionEntity detect(String userName) {
        List<UserStatsEntity> userStats = userStatsAdapter.getUserStats(userName);

        if (userStats == null || userStats.isEmpty()) {
            return questionsAdapter.getRandomQuestion();
        }

        Map<String, Double> percentCorrectAnswer = new HashMap<>();
        userStats.stream().forEach(stat -> {
            double percent = (double) 100 * (stat.getCorrectAnswer() == 0 ? 1 : stat.getCorrectAnswer()) / stat.getTotalAnswer();
            percentCorrectAnswer.put(stat.getCategory(), percent);
        });

        String categoryLessPercentCorrectAnswer = percentCorrectAnswer.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    if (entry2.getValue() > entry1.getValue()) return -1;
                    else if (entry2.getValue() < entry1.getValue()) return 1;
                    else return 0;
                }).map(Map.Entry::getKey)
                .findFirst()
                .get();

        if (isWeakCategoryQuestion()) {
            return questionsAdapter.getRandomQuestionsByCategory(categoryLessPercentCorrectAnswer);
        } else {
            return questionsAdapter.getRandomQuestionExcite(categoryLessPercentCorrectAnswer);
        }
    }

    private boolean isWeakCategoryQuestion() {
        int randomValue = random.nextInt(100);
        return randomValue < probabilityPercent;
    }
}
