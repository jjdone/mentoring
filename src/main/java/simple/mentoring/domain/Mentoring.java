package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
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

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
        mentor.getMentorings().add(this);
    }

    @Builder
    public Mentoring(Mentee mentee, String title, String content, LocalDateTime createDate) {
        this.mentee = mentee;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }
}
