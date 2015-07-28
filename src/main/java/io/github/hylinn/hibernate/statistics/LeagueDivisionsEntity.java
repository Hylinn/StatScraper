package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "league_divisions", schema = "", catalog = "statistics")
@IdClass(LeagueDivisionsEntityPK.class)
public class LeagueDivisionsEntity {
    private int id;
    private int seasonLeague;
    private int division;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "season_league")
    public int getSeasonLeague() {
        return seasonLeague;
    }

    public void setSeasonLeague(int seasonLeague) {
        this.seasonLeague = seasonLeague;
    }

    @Id
    @Column(name = "division")
    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeagueDivisionsEntity that = (LeagueDivisionsEntity) o;

        if (id != that.id) return false;
        if (seasonLeague != that.seasonLeague) return false;
        if (division != that.division) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + seasonLeague;
        result = 31 * result + division;
        return result;
    }
}
