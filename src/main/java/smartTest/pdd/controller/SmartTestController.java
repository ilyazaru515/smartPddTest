package smartTest.pdd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class SmartTestController {
    private Random random = new Random();

    @PostMapping(value = "/submit")
    public String submitAnswer(@RequestParam(name = "option", required = false) String option,
                               @RequestParam(name = "userName", required = false) String userName,
                               @RequestParam(name = "questionCount") Integer questionCount,
                               @RequestParam(name = "correctAnswersCount", defaultValue = "0", required = false) Integer correctAnswersCount,
                               Model model) {
        if (questionCount == 2) {
            model.addAttribute("correctAnswersSum", correctAnswersCount + 1);
            model.addAttribute("questionCount", questionCount);
            model.addAttribute("questionNumber", questionCount);
            model.addAttribute("userName", userName);
            return "results";
        } else {
            model.addAttribute("question", "Вопрос " + option);
            model.addAttribute("option1", "На право " + random.nextInt(100));
            model.addAttribute("option2", "На лево " + random.nextInt(100));
            model.addAttribute("option3", "Назад " + random.nextInt(100));
            model.addAttribute("option4", "Вперед" + random.nextInt(100));
            model.addAttribute("userName", userName);
            model.addAttribute("questionNumber", questionCount + 1);
            model.addAttribute("correctAnswersSum", correctAnswersCount + 1);
            return "question";
        }
    }

    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam(name = "userName", required = false) String userName,
                         Model model) {
        if (userName == null || userName.isBlank()) {
            model.addAttribute("signInHead", "Вы не ввели имя пользователя");
            return "signIn";
        } else {
            model.addAttribute("question", "Вопрос первый");
            model.addAttribute("option1", "Направо " + random.nextInt(100));
            model.addAttribute("option2", "Налево " + random.nextInt(100));
            model.addAttribute("option3", "Назад " + random.nextInt(100));
            model.addAttribute("option4", "Вперед " + random.nextInt(100));
            model.addAttribute("userName", userName);
            model.addAttribute("questionNumber", 1);
            return "question";
        }
    }

    @PostMapping(value = "/firstSignIn")
    public String firstSignIn(Model model) {
        return "firstSignIn";
    }
}
