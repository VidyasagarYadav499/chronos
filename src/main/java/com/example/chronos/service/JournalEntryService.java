package com.example.chronos.service;

import com.example.chronos.entity.JournalEntry;
import com.example.chronos.entity.User;
import com.example.chronos.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    /*
    Everything written in this method is treated as a single operation.
    If anything fails, @Transactional tells spring-boot to rollback all the operations.
    */
    @Transactional
    public void saveEntry(JournalEntry entry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepository.save(entry);
            user.getUserJournalEntries().add(savedEntry);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
            throw new RuntimeException("An error occurred while saving a journal entry ", e);
        }
    }

    public void saveEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);
        user.getUserJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }
}

// BEST PRACTICES : controller ---> service ---> repository