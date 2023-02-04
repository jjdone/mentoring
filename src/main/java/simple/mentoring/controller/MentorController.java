package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.mentoring.domain.Mentor;
import simple.mentoring.dto.MentorSignupDto;
import simple.mentoring.service.MentorService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentors")
@Slf4j
public class MentorController {

    private final MentorService mentorService;

    @GetMapping("/add")
    public String addForm() {
        log.info("Mentor Controller: addForm");
        return "mentors/addForm";
    }

    @PostMapping("/add")
    public String signup(@ModelAttribute MentorSignupDto mentorSignupDto) {
        log.info("Mentor Controller: signup");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Mentor mentor = Mentor.builder()
                .email(mentorSignupDto.getEmail())
                .password(encoder.encode(mentorSignupDto.getPassword()))
                .name(mentorSignupDto.getName())
                .phone(mentorSignupDto.getPhone())
                .rating(0l)
                .build();

        mentorService.join(mentor);

        return "redirect:/mentors/login";
    }

    @GetMapping("/login")
    public String login() {
        log.info("Mentor Controller: login");
        return "mentors/loginForm";
    }
}
