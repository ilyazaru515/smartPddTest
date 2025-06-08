package smartTest.pdd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smartTest.pdd.question.entity.UserStatsEntity;

import java.util.Optional;

@Repository
public interface UserStatsRepository extends CrudRepository<UserStatsEntity, String> {

    @Query(value = "SELECT COUNT(*) FROM pdd.user_stats WHERE user_name = :userName",
            nativeQuery = true)
    Integer userExists(@Param("userName") String userName);

    @Query(value = "SELECT * FROM pdd.user_stats WHERE user_name = :userName AND category = :category",
            nativeQuery = true)
    Optional<UserStatsEntity> getByNameAndCategory(
            @Param("userName") String userName,
            @Param("category") String category
    );
}
