package smartTest.pdd.repository;

import smartTest.pdd.question.entity.UserStatsEntity;

public interface UserStatsAdapter {

    boolean userExists(String userName);
    UserStatsEntity updatedUserStats(String userName, String category, boolean answerIsCorrect);

}
