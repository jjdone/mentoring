package simple.mentoring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Mentor;
import simple.mentoring.repository.MentorRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MentorServiceTest {

    @Autowired private MentorService mentorService;
    @Autowired private MentorRepository mentorRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private Mentor getMentor() {
        return Mentor.builder()
                .email("test@email.com")
                .password(encoder.encode("test1234!"))
                .name("testName")
                .phone("00011112222")
                .rating(0L)
                .build();
    }

    @Test
    public void 멘토_회원가입() throws Exception {
        //given
        Mentor mentor = getMentor();
        //when
        Long savedId = mentorService.join(mentor);
        //then
        assertThat(mentorRepository.findById(savedId).get()).isEqualTo(mentor);
    }

    @Test
    public void 멘토_회원가입_중복() throws Exception {
        //given
        Mentor mentor1 = getMentor();
        Mentor mentor2 = getMentor();
        //when
        mentorService.join(mentor1);
        //then
        assertThrows(IllegalStateException.class, () -> {
            mentorService.join(mentor2);
        });
    }
}