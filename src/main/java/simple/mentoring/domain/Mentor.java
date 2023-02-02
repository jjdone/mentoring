package simple.mentoring.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Mentor {

    @Id @GeneratedValue
    @Column(name = "mentor_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phone;
    private double rating;
}
