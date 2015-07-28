package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hylinn on 7/27/2015.
 */
public class LeagueDivisionsEntityPK implements Serializable {
    private int seasonLeague;
    private int division;

    @Column(name = "season_league")
    @Id
    public int getSeasonLeague() {
        return seasonLeague;
    }

    public void setSeasonLeague(int seasonLeague) {
        this.seasonLeague = seasonLeague;
    }

    @Column(name = "division")
    @Id
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

        LeagueDivisionsEntityPK that = (LeagueDivisionsEntityPK) o;

        if (seasonLeague != that.seasonLeague) return false;
        if (division != that.division) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = seasonLeague;
        result = 31 * result + division;
        return result;
    }
}
