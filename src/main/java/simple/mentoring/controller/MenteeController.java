package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.mentoring.domain.Mentee;
import simple.mentoring.dto.MenteeLoginDto;
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
    public String loginForm(Model model) {
        log.info("Mentee Controller: loginForm");
        return "mentees/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MenteeLoginDto menteeLoginDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("Mentee Controller: login");
        Mentee mentee = menteeService.findByEmail(menteeLoginDto.getEmail());
        if (mentee == null) {
            throw new IllegalStateException("존재하지 않는 멘티입니다.");
        }
        if (!encoder.matches(menteeLoginDto.getPassword(), mentee.getPassword())) {
            throw new IllegalStateException("비밀번호를 틀렸습니다.");
        }
        return "redirect:/mentorings";
    }
}
