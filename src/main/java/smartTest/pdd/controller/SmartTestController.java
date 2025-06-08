package smartTest.pdd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import smartTest.pdd.controller.dto.RequestParams;
import smartTest.pdd.service.SmartTestPddService;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class SmartTestController {
    private Random random = new Random();
    private final SmartTestPddService smartTestPddService;

    @PostMapping(value = "/submit")
    public String submitAnswer(@RequestParam(name = "option", required = false) String option,
                               @RequestParam(name = "userName", required = false) String userName,
                               @RequestParam(name = "questionCount") Integer questionCount,
                               @RequestParam(name = "correctAnswer") String correctAnswer,
                               @RequestParam(name = "categoryName") String categoryName,
                               @RequestParam(name = "correctAnswersCount", defaultValue = "0", required = false) Integer correctAnswersCount,
                               Model model) {
        return smartTestPddService.submitQuestion(
                new RequestParams(correctAnswersCount, questionCount, option, userName, correctAnswer, categoryName),
                model);
//        if (questionCount == 2) {
//            model.addAttribute("correctAnswersSum", correctAnswersCount + 1);
//            model.addAttribute("questionCount", questionCount);
//            model.addAttribute("questionNumber", questionCount);
//            model.addAttribute("userName", userName);
//            return "results";
//        } else {
//            model.addAttribute("question", "Вопрос " + option);
//            model.addAttribute("option1", "На право " + random.nextInt(100));
//            model.addAttribute("option2", "На лево " + random.nextInt(100));
//            model.addAttribute("option3", "Назад " + random.nextInt(100));
//            model.addAttribute("option4", "Вперед" + random.nextInt(100));
//            model.addAttribute("userName", userName);
//            model.addAttribute("questionNumber", questionCount + 1);
//            model.addAttribute("correctAnswersSum", correctAnswersCount + 1);
//            model.addAttribute("imageName", "images/img1-1.png");
//            return "question";
//        }
    }

    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam(name = "userName", required = false) String userName,
                         Model model) {
        if (userName == null || userName.isBlank()) {
            model.addAttribute("signInHead", "Вы не ввели имя пользователя");
            return "signIn";
        } else {
            return smartTestPddService.submitFirstQuestion(
                    new RequestParams(userName),
                    model);
        }
    }

    @PostMapping(value = "/firstSignIn")
    public String firstSignIn(Model model) {
        return "firstSignIn";
    }
}
