package quiz.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
@Data
public class Topic {

    @Id
    private Long id;
    @Column(name = "topic_name")
    private String name;

}
