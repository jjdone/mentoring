package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Mentor {

    @Id @GeneratedValue
    @Column(name = "mentor_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;
    private String phone;
    private double rating;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Mentoring> mentorings = new ArrayList<>();

    @Builder
    public Mentor(String email, String password, String name, String phone, double rating) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.rating = rating;
    }
}
