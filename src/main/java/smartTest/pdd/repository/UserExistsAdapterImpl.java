package smartTest.pdd.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smartTest.pdd.question.entity.UserStatsEntity;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserExistsAdapterImpl implements UserStatsAdapter{
    private final UserStatsRepository userStatsRepository;

    @Override
    public UserStatsEntity updatedUserStats(String userName, String category, boolean answerIsCorrect) {
        var userStatsOpt = userStatsRepository.getByNameAndCategory(userName, category);
        if (userStatsOpt.isPresent()) {
            var storedUserStat = userStatsOpt.get();
            storedUserStat.setTotalAnswer(storedUserStat.getTotalAnswer() + 1);
            if (answerIsCorrect) {
                storedUserStat.setCorrectAnswer(storedUserStat.getCorrectAnswer() + 1);
            }
            return userStatsRepository.save(storedUserStat);
        } else {
            var newUserStats = new UserStatsEntity();
            newUserStats.setId(UUID.randomUUID().toString());
            newUserStats.setUserName(userName);
            newUserStats.setCategory(category);
            newUserStats.setTotalAnswer(1);
            if (answerIsCorrect) {
                newUserStats.setCorrectAnswer(1);
            } else {
                newUserStats.setCorrectAnswer(0);
            }
            return userStatsRepository.save(newUserStats);
        }
    }

    @Override
    public List<UserStatsEntity> getUserStats(String userName) {
        return userStatsRepository.getUserStats(userName);
    }
}
