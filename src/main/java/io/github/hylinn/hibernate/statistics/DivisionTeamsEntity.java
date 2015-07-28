package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "division_teams", schema = "", catalog = "statistics")
@IdClass(DivisionTeamsEntityPK.class)
public class DivisionTeamsEntity {
    private int id;
    private int leagueDivision;
    private int team;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "league_division")
    public int getLeagueDivision() {
        return leagueDivision;
    }

    public void setLeagueDivision(int leagueDivision) {
        this.leagueDivision = leagueDivision;
    }

    @Id
    @Column(name = "team")
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

        DivisionTeamsEntity that = (DivisionTeamsEntity) o;

        if (id != that.id) return false;
        if (leagueDivision != that.leagueDivision) return false;
        if (team != that.team) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + leagueDivision;
        result = 31 * result + team;
        return result;
    }
}
