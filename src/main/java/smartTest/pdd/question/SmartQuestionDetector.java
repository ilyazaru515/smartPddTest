package smartTest.pdd.question;

import smartTest.pdd.question.entity.QuestionEntity;

public interface SmartQuestionDetector {

    QuestionEntity detect(String userName);
}
