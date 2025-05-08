package com.openshelf.controller;

import com.openshelf.model.Audiobook;
import com.openshelf.service.AudiobookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/audiobooks")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAudiobookController {

    @Autowired
    private AudiobookService audiobookService;

    // ✅ List all PENDING audiobooks
    @GetMapping("/pending")
    public List<Audiobook> getPendingAudiobooks() {
        return audiobookService.getAudiobooksByStatus(Audiobook.Status.PENDING);
    }

    // ✅ Approve an audiobook
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveAudiobook(@PathVariable Long id) {
        audiobookService.updateAudiobookStatus(id, Audiobook.Status.APPROVED);
        return ResponseEntity.ok("Audiobook approved.");
    }

    // ✅ Reject an audiobook
    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectAudiobook(@PathVariable Long id) {
        audiobookService.updateAudiobookStatus(id, Audiobook.Status.REJECTED);
        return ResponseEntity.ok("Audiobook rejected.");
    }
}
