package smartTest.pdd.repository;

import smartTest.pdd.question.entity.QuestionEntity;

public interface QuestionsAdapter {

    QuestionEntity getRandomQuestionExcite(String excitedCategory);
}
