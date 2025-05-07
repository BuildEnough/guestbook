package com.example.guestbook.controller;

import com.example.guestbook.domain.GuestbookEntry;
import com.example.guestbook.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    // 전체 글 목록
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("entries", guestbookService.getAllEntries());
        return "list";
    }

    // 글 작성 폼
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("entry", new GuestbookEntry());
        return "form";
    }

    // 글 저장
    @PostMapping("/save")
    public String save(@ModelAttribute GuestbookEntry entry) {
        guestbookService.saveEntry(entry);
        return "redirect:/";
    }

    // 글 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam Long id, @RequestParam String password, Model model) {
        boolean deleted = guestbookService.deleteEntry(id, password);
        if (!deleted) {
            model.addAttribute("error", "비밀번호가 틀렸습니다.");
        }
        model.addAttribute("entries", guestbookService.getAllEntries());
        return "list";
    }
}
