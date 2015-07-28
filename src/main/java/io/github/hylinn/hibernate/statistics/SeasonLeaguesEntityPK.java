package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hylinn on 7/27/2015.
 */
public class SeasonLeaguesEntityPK implements Serializable {
    private int league;
    private int season;

    @Column(name = "league")
    @Id
    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    @Column(name = "season")
    @Id
    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeasonLeaguesEntityPK that = (SeasonLeaguesEntityPK) o;

        if (league != that.league) return false;
        if (season != that.season) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = league;
        result = 31 * result + season;
        return result;
    }
}
