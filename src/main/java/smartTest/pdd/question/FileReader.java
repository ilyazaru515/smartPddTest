package smartTest.pdd.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileReader {

    public String readFile(String filePath) throws IOException {
        Path path = new ClassPathResource(filePath).getFile().toPath();
        try (InputStream inputStream = Files.newInputStream(path) ;
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader);) {
            var builder = new StringBuilder();
            reader.lines().forEach( line -> {
                builder.append(line);
                builder.append("\n");
                    });
            return builder.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
