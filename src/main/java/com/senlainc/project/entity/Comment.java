package com.senlainc.project.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomment")
    private long idComment;

    @Column(name = "comment_text")
    private String commentText;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "publication_date")
    private Calendar publicationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


    public Comment(String commentText, Calendar publicationDate, Announcement announcement, User user) {
        this.commentText = commentText;
        this.publicationDate = publicationDate;
        this.announcement = announcement;
        this.user = user;
    }

    public Comment() {
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
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
        Comment comment = (Comment) o;
        return idComment == comment.idComment &&
                commentText.equals(comment.commentText) &&
                publicationDate.equals(comment.publicationDate) &&
                announcement.equals(comment.announcement) &&
                user.equals(comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, commentText, publicationDate, announcement, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", commentText='" + commentText + '\'' +
                ", publicationDate=" + publicationDate +
                ", announcement=" + announcement +
                ", user=" + user +
                '}';
    }
}
