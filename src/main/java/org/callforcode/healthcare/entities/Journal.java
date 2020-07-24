package org.callforcode.healthcare.entities;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Journal")
public class Journal {

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    @Id
    private Long Id;
    private String title;
    private String content;
    private User owner;
}
