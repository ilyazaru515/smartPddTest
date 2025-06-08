package smartTest.pdd.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import smartTest.pdd.controller.dto.RequestParams;
import smartTest.pdd.model.ModelUpdater;
import smartTest.pdd.repository.QuestionsAdapter;
import smartTest.pdd.repository.UserStatsAdapter;

@Service
@RequiredArgsConstructor
public class SmartTestPddServiceImpl implements SmartTestPddService {
    private final UserStatsAdapter userStatsAdapter;
    private final QuestionsAdapter questionsAdapter;
    private final ModelUpdater modelUpdater;
    @Value("${spring.application.question-test-count:10}")
    private int questionLimit;

    @Override
    public String submitQuestion(RequestParams requestParams, Model model) {
//        if (userStatsAdapter.userExists(requestParams.getUserName())) {
            var answerIsCorrect = requestParams.getCorrectAnswer().equals(requestParams.getOption());
            userStatsAdapter.updatedUserStats(requestParams.getUserName(), requestParams.getCategory(), answerIsCorrect);
            if (requestParams.getQuestionCount() < questionLimit) {
                var newQuestion = questionsAdapter.getRandomQuestionExcite(null);
                modelUpdater.updateByQuestion(requestParams, newQuestion, model, answerIsCorrect);
                return "question";
            } else {
                modelUpdater.updateForResults(requestParams, model, answerIsCorrect);
                return "results";
            }
//        } else {
//            var question = questionsAdapter.getRandomQuestionExcite(null);
//            modelUpdater.updateByFirstQuestion(requestParams, question, model);
//            return "question";
//        }
    }

    @Override
    public String submitFirstQuestion(RequestParams requestParams, Model model) {
        var question = questionsAdapter.getRandomQuestionExcite(null);
        modelUpdater.updateByFirstQuestion(requestParams, question, model);
        return "question";
    }

}
