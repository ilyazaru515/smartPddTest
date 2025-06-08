package smartTest.pdd.question.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Data
@Entity
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
})
@Table(name = "questions", schema = "pdd")
public class QuestionEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "CATEGORY", nullable = false)
    private String category;
    @Column(name = "QUESTION", nullable = false)
    private String question;
    @Column(name = "OPTIONS", nullable = false, columnDefinition = "text[]")
    @Type(type = "string-array")
    private String[] options;
    @Column(name = "CORRECT_ANSWER", nullable = false)
    private String correctAnswer;
    @Column(name = "IMAGE_NAME", nullable = false)
    private String imageName;
}
