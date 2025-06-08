package smartTest.pdd.question.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Data
@Entity
@Table(name = "user_stats", schema = "pdd")
public class UserStatsEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "CATEGORY", nullable = false)
    private String category;
    @Column(name = "TOTAL_ANSWER", nullable = false)
    private Integer totalAnswer;
    @Column(name = "CORRECT_ANSWER", nullable = false)
    private Integer correctAnswer;
}
