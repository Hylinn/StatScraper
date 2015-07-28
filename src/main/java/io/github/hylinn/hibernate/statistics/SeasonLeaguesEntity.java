package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "season_leagues", schema = "", catalog = "statistics")
@IdClass(SeasonLeaguesEntityPK.class)
public class SeasonLeaguesEntity {
    private int id;
    private int league;
    private int season;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "league")
    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    @Id
    @Column(name = "season")
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

        SeasonLeaguesEntity that = (SeasonLeaguesEntity) o;

        if (id != that.id) return false;
        if (league != that.league) return false;
        if (season != that.season) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + league;
        result = 31 * result + season;
        return result;
    }
}
