package com.senlainc.project.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "announcement")
public class Announcement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannouncement")
    private long idAnnouncement;

    @Column(name = "header")
    @NotNull
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "isTop")
    private boolean topStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "creationDate")
    private Calendar creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    // @CreationTimestamp
    @Column(name = "paymentDate")
    private Date paymentDate;//Calendar paymentDate;

    @Column(name = "isActive")
    private boolean activeStatus;


    @Positive(message = "must be positive value!")
    @Column(name = "price")
    private double itemPrice;

    /*private String dateFormat;*/

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "announcement_category_id")
    private AnnouncementCategory announcementCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Comment> comments;


    public Announcement(String header, String description, boolean topStatus, Calendar creationDate,
                        Date paymentDate, boolean activeStatus, double itemPrice, User user,
                        AnnouncementCategory announcementCategory, List<Comment> comments) {
        this.header = header;
        this.description = description;
        this.topStatus = topStatus;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.activeStatus = activeStatus;
        this.itemPrice = itemPrice;
        this.user = user;
        this.announcementCategory = announcementCategory;
        this.comments = comments;
    }

    public Announcement() {
    }

    public long getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(long idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTopStatus() {
        return topStatus;
    }

    public void setTopStatus(boolean topStatus) {
        this.topStatus = topStatus;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnnouncementCategory getAnnouncementCategory() {
        return announcementCategory;
    }

    public void setAnnouncementCategory(AnnouncementCategory announcementCategory) {
        this.announcementCategory = announcementCategory;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }



    /*public String getDateFormat() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        return simpleDateFormat.format(creationDate);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return idAnnouncement == that.idAnnouncement &&
                topStatus == that.topStatus &&
                activeStatus == that.activeStatus &&
                Double.compare(that.itemPrice, itemPrice) == 0 &&
                header.equals(that.header) &&
                Objects.equals(description, that.description) &&
                creationDate.equals(that.creationDate) &&
                Objects.equals(paymentDate, that.paymentDate) &&
                user.equals(that.user) &&
                announcementCategory.equals(that.announcementCategory) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnnouncement, header, description, topStatus, creationDate, paymentDate, activeStatus, itemPrice, user, announcementCategory, comments);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "idAnnouncement=" + idAnnouncement +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", topStatus=" + topStatus +
                ", creationDate=" + creationDate +
                ", paymentDate=" + paymentDate +
                ", activeStatus=" + activeStatus +
                ", itemPrice=" + itemPrice +
                ", user=" + user +
                ", announcementCategory=" + announcementCategory +
                ", comments=" + comments +
                '}';
    }
}
