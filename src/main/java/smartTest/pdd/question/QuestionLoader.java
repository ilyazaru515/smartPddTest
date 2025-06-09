package smartTest.pdd.question;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import smartTest.pdd.question.entity.QuestionEntity;
import smartTest.pdd.repository.QuestionRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(
        value="spring.datasource.load-questions",
        havingValue = "true",
        matchIfMissing = false)
@RequiredArgsConstructor
public class QuestionLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FileReader fileReader;
    private final QuestionRepository questionRepository;

    @PostConstruct
    void loadQuestion() {
        try {
            var crosswalkQuestions = objectMapper.readValue(fileReader.readFile("questions/crosswalk.json"), new TypeReference<List<QuestionEntity>>(){});
            var overtrakingQuestions = objectMapper.readValue(fileReader.readFile("questions/overtraking.json"), new TypeReference<List<QuestionEntity>>(){});
            var roadMarkingsQuestions = objectMapper.readValue(fileReader.readFile("questions/roadMarkings.json"), new TypeReference<List<QuestionEntity>>(){});
            var roadSignsQuestions = objectMapper.readValue(fileReader.readFile("questions/roadSigns.json"), new TypeReference<List<QuestionEntity>>(){});
            var speedQuestions = objectMapper.readValue(fileReader.readFile("questions/speed.json"), new TypeReference<List<QuestionEntity>>(){});
            var railWays = objectMapper.readValue(fileReader.readFile("questions/railWays.json"), new TypeReference<List<QuestionEntity>>(){});
            var stop = objectMapper.readValue(fileReader.readFile("questions/stop.json"), new TypeReference<List<QuestionEntity>>(){});
            crosswalkQuestions.forEach(questionRepository::save);
            overtrakingQuestions.forEach(questionRepository::save);
            roadMarkingsQuestions.forEach(questionRepository::save);
            roadSignsQuestions.forEach(questionRepository::save);
            speedQuestions.forEach(questionRepository::save);
            railWays.forEach(questionRepository::save);
            stop.forEach(questionRepository::save);
        } catch (Exception e) {
            log.error("Failed load questions: " + e.getMessage(), e);
        }
    }
}
