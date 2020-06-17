package com.senlainc.project.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private long idUser;

    @Column(name = "userName")
    @NotNull(message = "is required")
    private String userName;

    @Column(name = "password")
    @NotNull(message = "is required")
    @Size(min = 3, message = "must be more than 2 symbols! ")
    private String password;

    @Column(name = "email")
    @Email(message = "enter correct email!")
    //@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "enter correct format of email, any letters, digits, and symbols: _ ,-, . are possible!")
    private String email;

    @Column(name = "role",columnDefinition = "USER")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "rating")
    private float rating;

    @Column(name = "region")
    @NotNull(message = "is required")
    //@Enumerated(EnumType.STRING)
    private String region;

    @Column(name = "city")
   // @Enumerated(EnumType.STRING)
    private String city;

    @Column(name = "phoneNumber")
    @NotNull
    @Pattern(regexp = "^((\\+375))?(\\(?\\d{9}\\)?)$", message = "enter phone number in format +375(your phone number!)!")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<CreditCard> creditCards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Message> messages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Announcement> announcements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Comment> comments;

    public User() {
    }

    public User(String userName, String password,String email, Role role, float rating,String region, String city,
                String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.rating = rating;
        this.region = region;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /*public void setRegion(Region region) {
        this.region = region;
    }*/

    public String getCity() {
        return city;
    }

   /* public void setCity(City city) {
        this.city = city;
    }*/

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
