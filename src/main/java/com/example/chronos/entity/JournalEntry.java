package com.example.chronos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "journal_entry")
public class JournalEntry {

    @Id
    private ObjectId id; // Primary key for the journal_entry collection/table.

    private LocalDateTime date;

    @NonNull
    private String title;

    private String content;

}
