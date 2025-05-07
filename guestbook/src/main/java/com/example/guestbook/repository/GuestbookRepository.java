package com.example.guestbook.repository;

import com.example.guestbook.domain.GuestbookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<GuestbookEntry, Long> {
}
