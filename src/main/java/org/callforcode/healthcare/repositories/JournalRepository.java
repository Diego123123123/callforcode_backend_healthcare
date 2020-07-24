package org.callforcode.healthcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.callforcode.healthcare.entities.Journal;
import org.callforcode.healthcare.entities.User;


public interface JournalRepository extends JpaRepository<Journal, Long> {
    //Journal findJournalByUser(User user);
}
