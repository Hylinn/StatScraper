package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "team_players", schema = "", catalog = "statistics")
@IdClass(TeamPlayersEntityPK.class)
public class TeamPlayersEntity {
    private int id;
    private int divisionTeam;
    private int player;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "division_team")
    public int getDivisionTeam() {
        return divisionTeam;
    }

    public void setDivisionTeam(int divisionTeam) {
        this.divisionTeam = divisionTeam;
    }

    @Id
    @Column(name = "player")
    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamPlayersEntity that = (TeamPlayersEntity) o;

        if (id != that.id) return false;
        if (divisionTeam != that.divisionTeam) return false;
        if (player != that.player) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + divisionTeam;
        result = 31 * result + player;
        return result;
    }
}
