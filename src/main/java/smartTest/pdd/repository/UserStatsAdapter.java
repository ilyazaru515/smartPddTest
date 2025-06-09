package smartTest.pdd.repository;

import smartTest.pdd.question.entity.UserStatsEntity;

import java.util.List;

public interface UserStatsAdapter {

    UserStatsEntity updatedUserStats(String userName, String category, boolean answerIsCorrect);
    List<UserStatsEntity> getUserStats(String userName);

}
