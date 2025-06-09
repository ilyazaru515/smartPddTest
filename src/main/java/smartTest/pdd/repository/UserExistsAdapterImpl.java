package smartTest.pdd.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import smartTest.pdd.question.entity.UserStatsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserExistsAdapterImpl implements UserStatsAdapter{
    private final UserStatsRepository userStatsRepository;

    @Override
    public UserStatsEntity updatedUserStats(String userName, String category, boolean answerIsCorrect) {
        Optional<UserStatsEntity> userStatsOpt = userStatsRepository.getByNameAndCategory(userName, category);
        if (userStatsOpt.isPresent()) {
            UserStatsEntity storedUserStat = userStatsOpt.get();
            storedUserStat.setTotalAnswer(storedUserStat.getTotalAnswer() + 1);
            if (answerIsCorrect) {
                storedUserStat.setCorrectAnswer(storedUserStat.getCorrectAnswer() + 1);
            }
            return userStatsRepository.save(storedUserStat);
        } else {
            UserStatsEntity newUserStats = new UserStatsEntity();
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
