package simple.mentoring.domain;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Mentoring {

    @Id @GeneratedValue
    @Column(name = "mentoring_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    private String title;
    private String content;
    private LocalDateTime createDate;
}
