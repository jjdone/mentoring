package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Mentee;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee findByEmail(String email);
}
