package quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long topic_id;
}
