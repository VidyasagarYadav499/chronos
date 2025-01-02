package com.example.chronos.service;

import com.example.chronos.entity.JournalEntry;
import com.example.chronos.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry entry){
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void deleteById(ObjectId myId) {
        journalEntryRepository.deleteById(myId);
    }
}

// BEST PRACTICES : controller ---> service ---> repository