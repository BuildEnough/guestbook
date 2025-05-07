package com.example.guestbook.service;

import com.example.guestbook.domain.GuestbookEntry;
import com.example.guestbook.repository.GuestbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    public GuestbookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    // 글 등록
    public GuestbookEntry saveEntry(GuestbookEntry entry) {
        return guestbookRepository.save(entry);
    }

    // 전체 글 조회
    public List<GuestbookEntry> getAllEntries() {
        return guestbookRepository.findAll();
    }

    // 글 삭제 (비밀번호 일치 시)
    public boolean deleteEntry(Long id, String password) {
        Optional<GuestbookEntry> optional = guestbookRepository.findById(id);
        if (optional.isPresent()) {
            GuestbookEntry entry = optional.get();
            if (entry.getPassword().equals(password)) {
                guestbookRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
