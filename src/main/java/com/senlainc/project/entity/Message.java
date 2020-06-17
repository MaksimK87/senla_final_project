package com.senlainc.project.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmessage")
    private long idMessage;

    @Column(name = "message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="publication_date")
    private Calendar publicationDate;

    @Column(name = "user_id_to")
    private long userIdTo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id_from")
    private User user;

    public Message(String message, Calendar publicationDate, long userIdTo) {
        this.message = message;
        this.publicationDate = publicationDate;
        this.userIdTo = userIdTo;
    }

    public Message() {
    }

    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(long userIdTo) {
        this.userIdTo = userIdTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", message='" + message + '\'' +
                ", publicationDate=" + publicationDate +
                ", userIdTo=" + userIdTo +
                '}';
    }
}
