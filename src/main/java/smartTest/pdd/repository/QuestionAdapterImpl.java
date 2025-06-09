package smartTest.pdd.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smartTest.pdd.question.entity.QuestionEntity;

@Component
@RequiredArgsConstructor
public class QuestionAdapterImpl implements QuestionsAdapter {
    private final QuestionRepository questionRepository;

    @Override
    public QuestionEntity getRandomQuestionExcite(String excitedCategory) {
        if (excitedCategory == null) {
            return questionRepository.getRandomQuestion();
        } else {
            return questionRepository.getRandomQuestionExcite(excitedCategory);
        }
    }

    @Override
    public QuestionEntity getRandomQuestion() {
        return questionRepository.getRandomQuestion();
    }

    @Override
    public QuestionEntity getRandomQuestionsByCategory(String category) {
        if (category == null) {
            return questionRepository.getRandomQuestion();
        } else {
            QuestionEntity question = questionRepository.getRandomQuestionsByCategory(category);
            if (question == null) {
                question = questionRepository.getRandomQuestion();
            }
            return question;
        }
    }
}
