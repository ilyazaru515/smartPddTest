package smartTest.pdd.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import smartTest.pdd.controller.dto.RequestParams;
import smartTest.pdd.question.entity.QuestionEntity;

@Component
public class ModelUpdaterImpl implements ModelUpdater {
    @Value("${spring.application.question-test-count:10}")
    private int questionLimit;

    @Override
    public void updateByFirstQuestion(RequestParams requestParams, QuestionEntity question, Model model) {
        model.addAttribute("question", question.getQuestion());
        model.addAttribute("option1", question.getOptions()[0]);
        model.addAttribute("option2", question.getOptions()[1]);
        model.addAttribute("option3", question.getOptions()[2]);
        model.addAttribute("userName", requestParams.getUserName());
        model.addAttribute("questionNumber", 1);
        model.addAttribute("category", question.getCategory());
        model.addAttribute("answer", question.getCorrectAnswer());
        model.addAttribute("questionLimit", questionLimit);
        model.addAttribute("imageName", "images/" + question.getImageName());
    }

    @Override
    public void updateByQuestion(RequestParams requestParams, QuestionEntity question, Model model, boolean answerIsCorrect) {
        model.addAttribute("question", question.getQuestion());
        model.addAttribute("option1", question.getOptions()[0]);
        model.addAttribute("option2", question.getOptions()[1]);
        model.addAttribute("option3", question.getOptions()[2]);
        model.addAttribute("userName", requestParams.getUserName());
        model.addAttribute("category", question.getCategory());
        model.addAttribute("answer", question.getCorrectAnswer());
        model.addAttribute("questionLimit", questionLimit);
        model.addAttribute("questionNumber", requestParams.getQuestionCount() + 1);
        if (answerIsCorrect) {
            model.addAttribute("correctAnswersSum", requestParams.getCorrectAnswersCount() + 1);
        } else {
            model.addAttribute("correctAnswersSum", requestParams.getCorrectAnswersCount());
        }
        model.addAttribute("imageName", "images/" + question.getImageName());
    }

    @Override
    public void updateForResults(RequestParams requestParams, Model model, boolean answerIsCorrect) {
        if (answerIsCorrect) {
            model.addAttribute("correctAnswersSum", requestParams.getCorrectAnswersCount() + 1);
        } else {
            model.addAttribute("correctAnswersSum", requestParams.getCorrectAnswersCount());
        }
        model.addAttribute("questionCount", requestParams.getQuestionCount());
        model.addAttribute("userName", requestParams.getUserName());
    }
}
