package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hylinn on 7/27/2015.
 */
public class DivisionTeamsEntityPK implements Serializable {
    private int leagueDivision;
    private int team;

    @Column(name = "league_division")
    @Id
    public int getLeagueDivision() {
        return leagueDivision;
    }

    public void setLeagueDivision(int leagueDivision) {
        this.leagueDivision = leagueDivision;
    }

    @Column(name = "team")
    @Id
    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisionTeamsEntityPK that = (DivisionTeamsEntityPK) o;

        if (leagueDivision != that.leagueDivision) return false;
        if (team != that.team) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leagueDivision;
        result = 31 * result + team;
        return result;
    }
}
