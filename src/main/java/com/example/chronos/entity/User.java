package com.example.chronos.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id; // Primary key for the 'users' collection/table.

    @NonNull
    @Indexed(unique = true)
    private String userName; // Unique usernames and fast searching.

    @NonNull
    private String password;

    @DBRef // Like a Foreign key, that references an entry in another collection(journal_entries).
    private List<JournalEntry> userJournalEntries = new ArrayList<>();

}
