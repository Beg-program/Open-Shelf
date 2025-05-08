package com.openshelf.service;

import com.openshelf.model.Audiobook;
import com.openshelf.model.Audiobook.Status;
import com.openshelf.repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudiobookService {

    @Autowired
    private AudiobookRepository audiobookRepository;

    public Audiobook addAudiobook(Audiobook audiobook) {
        audiobook.setStatus(Status.PENDING);
        return audiobookRepository.save(audiobook);
    }

    public List<Audiobook> getApprovedAudiobooks() {
        return audiobookRepository.findByStatus(Status.APPROVED);
    }

    public List<Audiobook> getPendingAudiobooks() {
        return audiobookRepository.findByStatus(Status.PENDING);
    }

    public Optional<Audiobook> getAudiobookById(Long id) {
        return audiobookRepository.findById(id);
    }

    public Audiobook updateAudiobookStatus(Long id, Status status) {
        Audiobook audiobook = audiobookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audiobook not found"));
        audiobook.setStatus(status);
        return audiobookRepository.save(audiobook);
    }
}
