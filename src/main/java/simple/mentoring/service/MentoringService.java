package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Mentor;
import simple.mentoring.domain.Mentoring;
import simple.mentoring.dto.MentoringDto;
import simple.mentoring.repository.MentorRepository;
import simple.mentoring.repository.MentoringRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringService {

    private final MentoringRepository mentoringRepository;
    private final MentorRepository mentorRepository;

    @Transactional
    public void saveMentoring(Mentoring mentoring) {
        mentoringRepository.save(mentoring);
    }

    public List<Mentoring> findMentorings() {
        return mentoringRepository.findAll();
    }

    public Mentoring findOne(Long mentoringId) {
        return mentoringRepository.findById(mentoringId).get();
    }

    public Long addMentoring(MentoringDto mentoringDto) {
        Mentor mentor = mentorRepository.findByEmail(mentoringDto.getEmail());

        Mentoring mentoring = Mentoring.builder()
                .title(mentoringDto.getTitle())
                .content(mentoringDto.getContent())
                .createDate(LocalDateTime.now())
                .mentee(null)
                .build();
        mentoring.setMentor(mentor);

        mentoringRepository.save(mentoring);
        return mentoring.getId();
    }
}
