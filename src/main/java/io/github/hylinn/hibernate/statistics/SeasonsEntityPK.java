package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hylinn on 7/27/2015.
 */
public class SeasonsEntityPK implements Serializable {
    private int year;
    private String playSeason;

    @Column(name = "year")
    @Id
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "play_season")
    @Id
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

        SeasonsEntityPK that = (SeasonsEntityPK) o;

        if (year != that.year) return false;
        if (playSeason != null ? !playSeason.equals(that.playSeason) : that.playSeason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + (playSeason != null ? playSeason.hashCode() : 0);
        return result;
    }
}
