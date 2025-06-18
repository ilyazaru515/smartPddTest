package smartTest.pdd.model;

import org.springframework.ui.Model;
import smartTest.pdd.controller.dto.RequestParams;
import smartTest.pdd.question.entity.QuestionEntity;

public interface ModelUpdater {

    void updateByFirstQuestion(RequestParams requestParams, QuestionEntity questionEntity, Model model);
    void updateByQuestion(RequestParams requestParams, QuestionEntity questionEntity, Model model, boolean answerIsCorrect);
    void updateForResults(RequestParams requestParams, Model model, boolean answerIsCorrect, String weakCategory);
}
