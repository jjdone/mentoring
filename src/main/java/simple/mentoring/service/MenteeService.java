package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Mentee;
import simple.mentoring.repository.MenteeRepository;

@Service
@RequiredArgsConstructor
public class MenteeService {

    private final MenteeRepository menteeRepository;

    @Transactional
    public Long join(Mentee mentee) {
        validationMentee(mentee);

        menteeRepository.save(mentee);

        return mentee.getId();
    }

    private void validationMentee(Mentee mentee) {
        if (menteeRepository.findByEmail(mentee.getEmail()) != null) {
            throw new IllegalStateException("이미 존재하는 멘티입니다.");
        }
    }
}
