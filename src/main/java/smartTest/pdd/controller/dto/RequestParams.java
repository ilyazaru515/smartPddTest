package smartTest.pdd.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestParams {
    private Integer correctAnswersCount;
    private Integer questionCount;
    private String option;
    private String userName;
    private String correctAnswer;
    private String category;

    public RequestParams(String userName) {
        this.userName = userName;
    }
}
