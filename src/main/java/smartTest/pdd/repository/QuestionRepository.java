package smartTest.pdd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smartTest.pdd.question.entity.QuestionEntity;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, String> {

    @Query(value = "SELECT * FROM pdd.questions  WHERE category <> :category ORDER BY RANDOM() LIMIT 1;",
            nativeQuery = true)
    QuestionEntity getRandomQuestionExcite(@Param("category") String category);

    @Query(value = "SELECT * FROM pdd.questions ORDER BY RANDOM() LIMIT 1;",
            nativeQuery = true)
    QuestionEntity getRandomQuestion();

    @Query(value = "SELECT * FROM pdd.questions  WHERE category = :category ORDER BY RANDOM() LIMIT 1;",
            nativeQuery = true)
    QuestionEntity getRandomQuestionsByCategory(@Param("category") String category);
}
