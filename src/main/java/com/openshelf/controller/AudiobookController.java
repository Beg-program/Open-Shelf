package com.openshelf.controller;

import com.openshelf.model.Audiobook;
import com.openshelf.service.AudiobookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audiobooks")
public class AudiobookController {

    @Autowired
    private AudiobookService audiobookService;

    // ✅ List only APPROVED audiobooks
    @GetMapping
    public List<Audiobook> getApprovedAudiobooks() {
        return audiobookService.getApprovedAudiobooks();
    }

    // ✅ Upload a new audiobook (status = PENDING by default)
    @PostMapping
    public ResponseEntity<Audiobook> addAudiobook(@RequestBody Audiobook audiobook) {
        Audiobook created = audiobookService.addAudiobook(audiobook);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ✅ Get audiobook by ID
    @GetMapping("/{id}")
    public ResponseEntity<Audiobook> getAudiobookById(@PathVariable Long id) {
        Optional<Audiobook> audiobook = audiobookService.getAudiobookById(id);
        return audiobook.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
