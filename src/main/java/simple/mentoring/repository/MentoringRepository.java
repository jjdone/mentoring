package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Mentoring;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
}
