package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.mentoring.domain.Mentee;
import simple.mentoring.dto.MenteeSignupDto;
import simple.mentoring.service.MenteeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentees")
@Slf4j
public class MenteeController {

    private final MenteeService menteeService;

    @GetMapping("/add")
    public String addForm() {
        log.info("Mentee Controller: addForm");
        return "mentees/addForm";
    }

    @PostMapping("/add")
    public String signup(@ModelAttribute MenteeSignupDto menteeSignupDto) {
        log.info("Mentee Controller: signup");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Mentee mentee = Mentee.builder()
                .email(menteeSignupDto.getEmail())
                .password(encoder.encode(menteeSignupDto.getPassword()))
                .name(menteeSignupDto.getName())
                .phone(menteeSignupDto.getPhone())
                .build();

        menteeService.join(mentee);

        return "redirect:/mentees/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        log.info("Mentee Controller: loginForm");
        return "mentees/loginForm";
    }

    @PostMapping("/login")
    public String login(MenteeSignupDto menteeSignupDto) {
        log.info("Mentee Controller: login");
        if (menteeService.findByEmail(menteeSignupDto.getEmail()) == null) {
            throw new IllegalStateException("존재하지 않는 멘티입니다.");
        }
        return "redirect:/mentorings";
    }
}
