package com.senlainc.project.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "announcement_category")
public class AnnouncementCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannouncement_category")
    private int idCategory;

    @Column(name = "category")
    private String category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "announcementCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Announcement> announcementList;

    public AnnouncementCategory(String category, List<Announcement> announcementList) {
        this.category = category;
        this.announcementList = announcementList;
    }

    public AnnouncementCategory() {

    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementCategory that = (AnnouncementCategory) o;
        return idCategory == that.idCategory &&
                category.equals(that.category) &&
                Objects.equals(announcementList, that.announcementList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, category, announcementList);
    }

    @Override
    public String toString() {
        return "AnnouncementCategory{" +
                "idCategory=" + idCategory +
                ", category='" + category + '\'' +
                ", announcementList=" + announcementList +
                '}';
    }
}
