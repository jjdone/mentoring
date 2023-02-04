package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Mentee {

    @Id @GeneratedValue
    @Column(name = "mentee_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phone;

    @Builder
    public Mentee(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
