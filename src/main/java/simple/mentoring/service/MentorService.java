package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Mentor;
import simple.mentoring.dto.MentorSignupDto;
import simple.mentoring.repository.MentorRepository;


@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    @Transactional
    public Long join(Mentor mentor) {
        validationMentor(mentor);

        mentorRepository.save(mentor);

        return mentor.getId();
    }

    private void validationMentor(Mentor mentor) {
        if (mentorRepository.findByEmail(mentor.getEmail()) != null) {
            throw new IllegalStateException("이미 존재하는 멘토입니다.");
        }
    }
}
