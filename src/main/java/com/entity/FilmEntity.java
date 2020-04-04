package com.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "films")
public class FilmEntity implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long film_id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private String year;

    @Column(name = "quality")
    private String quality;

    @Column(name = "translation")
    private String translation;

    @Column(name = "continuance")
    private String continuance;
    @Column(name = "date")
    private String date;

    public FilmEntity() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setContinuance(String continuance) {
        this.continuance = continuance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return film_id;
    }

    public void setId(Long id) {
        this.film_id = id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getQuality() {
        return quality;
    }

    public String getTranslation() {
        return translation;
    }

    public String getContinuance() {
        return continuance;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + film_id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", quality='" + quality + '\'' +
                ", translation='" + translation + '\'' +
                ", continuance='" + continuance + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
