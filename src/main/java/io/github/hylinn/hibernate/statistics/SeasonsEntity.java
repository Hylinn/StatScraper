package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "seasons", schema = "", catalog = "statistics")
@IdClass(SeasonsEntityPK.class)
public class SeasonsEntity {
    private int id;
    private int year;
    private String playSeason;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Id
    @Column(name = "play_season")
    public String getPlaySeason() {
        return playSeason;
    }

    public void setPlaySeason(String playSeason) {
        this.playSeason = playSeason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeasonsEntity that = (SeasonsEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (playSeason != null ? !playSeason.equals(that.playSeason) : that.playSeason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + year;
        result = 31 * result + (playSeason != null ? playSeason.hashCode() : 0);
        return result;
    }
}
