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

    @Column(name = "userIdTo")
    private long userIdTo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id_from")
    private User user;

    public Message(String message, Calendar publicationDate, long userIdTo, User user) {
        this.message = message;
        this.publicationDate = publicationDate;
        this.userIdTo = userIdTo;
        this.user = user;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return idMessage == message1.idMessage &&
                userIdTo == message1.userIdTo &&
                message.equals(message1.message) &&
                Objects.equals(publicationDate, message1.publicationDate) &&
                user.equals(message1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMessage, message, publicationDate, userIdTo, user);
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", message='" + message + '\'' +
                ", publicationDate=" + publicationDate +
                ", userIdTo=" + userIdTo +
                ", user=" + user +
                '}';
    }
}
