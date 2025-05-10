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

    // Add a new audiobook (set status to PENDING)
    public Audiobook addAudiobook(Audiobook audiobook) {
        audiobook.setStatus(Status.PENDING);
        return audiobookRepository.save(audiobook);
    }

    // Get all audiobooks
    public List<Audiobook> getAllAudiobooks() {
        return audiobookRepository.findAll();
    }

    // Get audiobooks by status (approved, pending, etc.)
    public List<Audiobook> getAudiobooksByStatus(Status status) {
        return audiobookRepository.findByStatus(status);
    }

    // Get only APPROVED audiobooks
    public List<Audiobook> getApprovedAudiobooks() {
        return audiobookRepository.findByStatus(Status.APPROVED);
    }

    // Get only PENDING audiobooks
    public List<Audiobook> getPendingAudiobooks() {
        return audiobookRepository.findByStatus(Status.PENDING);
    }

    // Get a single audiobook by ID
    public Optional<Audiobook> getAudiobookById(Long id) {
        return audiobookRepository.findById(id);
    }

    // Update the details of an existing audiobook
    public Audiobook updateAudiobook(Long id, Audiobook updatedAudiobook) {
        return audiobookRepository.findById(id)
            .map(audiobook -> {
                audiobook.setTitle(updatedAudiobook.getTitle());
                audiobook.setAuthor(updatedAudiobook.getAuthor());
                audiobook.setDescription(updatedAudiobook.getDescription());
                audiobook.setFileURL(updatedAudiobook.getFileURL());
                
                // Ensure status is updated only if explicitly provided
                if (updatedAudiobook.getStatus() != null) {
                    audiobook.setStatus(updatedAudiobook.getStatus());
                }
                return audiobookRepository.save(audiobook);
            })
            .orElseGet(() -> {
                updatedAudiobook.setId(id);
                return audiobookRepository.save(updatedAudiobook);
            });
    }

    // Update the status of an existing audiobook
    public Audiobook updateAudiobookStatus(Long id, Status status) {
        Audiobook audiobook = audiobookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audiobook not found"));
        audiobook.setStatus(status);
        return audiobookRepository.save(audiobook);
    }

    // Delete an audiobook
    public void deleteAudiobook(Long id) {
        audiobookRepository.deleteById(id);
    }
}
