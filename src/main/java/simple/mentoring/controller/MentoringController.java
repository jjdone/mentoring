package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.mentoring.domain.Mentoring;
import simple.mentoring.dto.MentoringDto;
import simple.mentoring.service.MentoringService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentorings")
@Slf4j
public class MentoringController {

    private final MentoringService mentoringService;

    @GetMapping()
    public String mentoringList(Model model) {
        log.info("Mentoring Controller: mentoringList");
        List<Mentoring> mentorings = mentoringService.findMentorings();
        model.addAttribute("mentorings", mentorings);
        return "mentorings/mentoringList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        log.info("Mentoring Controller: addForm");
        model.addAttribute("form", new MentoringDto());
        return "mentorings/addForm";
    }

    @PostMapping("/add")
    public String add(MentoringDto mentoringDto) {
        log.info("Mentoring Controller: add");
        mentoringService.addMentoring(mentoringDto);
        return "redirect:/mentorings";
    }

    @GetMapping("/{mentoringId}")
    public String mentoringDetail(@PathVariable("mentoringId") Long mentoringId, Model model) {
        log.info("Mentoring Controller: mentoringDetail");
        Mentoring mentoring = mentoringService.findOne(mentoringId);

        MentoringDto mentoringDto = new MentoringDto();
        mentoringDto.setTitle(mentoring.getTitle());
        mentoringDto.setContent(mentoring.getContent());
        mentoringDto.setEmail(mentoring.getMentor().getEmail());

        model.addAttribute("form", mentoringDto);
        return "mentorings/detail";
    }
}
