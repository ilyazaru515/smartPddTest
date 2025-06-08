package smartTest.pdd.service;

import org.springframework.ui.Model;
import smartTest.pdd.controller.dto.RequestParams;

public interface SmartTestPddService {
    String submitQuestion(RequestParams requestParams, Model model);
    String submitFirstQuestion(RequestParams requestParams, Model model);
}
