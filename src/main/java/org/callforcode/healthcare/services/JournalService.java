package org.callforcode.healthcare.services;

import org.callforcode.healthcare.entities.Journal;
import org.springframework.stereotype.Service;
import org.callforcode.healthcare.repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JournalService implements IJournalService {

    private JournalRepository journalRepository;
    
    @Autowired
    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }
    
    @Override
    public void save(Journal journal) {
        journalRepository.save(journal);
    }
    
}
