package com.example.chronos.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "journal_entry")
public class JournalEntry {

    @Id
    private ObjectId id; // Primary key.
    private LocalDateTime date;
    private String title;
    private String content;

}
