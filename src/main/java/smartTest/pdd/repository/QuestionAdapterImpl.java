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
            return questionRepository.getRandomQuestionExcite("");
        } else {
            return questionRepository.getRandomQuestionExcite(excitedCategory);
        }
    }
}
