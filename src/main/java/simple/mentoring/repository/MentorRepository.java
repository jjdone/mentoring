package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor findByEmail(String email);
}
