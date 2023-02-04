package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/login")
    public String login() {
        log.info("Mentor Controller: login");
        return "mentors/loginForm";
    }
}
