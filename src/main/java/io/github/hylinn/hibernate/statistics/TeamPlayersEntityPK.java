package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hylinn on 7/27/2015.
 */
public class TeamPlayersEntityPK implements Serializable {
    private int divisionTeam;
    private int player;

    @Column(name = "division_team")
    @Id
    public int getDivisionTeam() {
        return divisionTeam;
    }

    public void setDivisionTeam(int divisionTeam) {
        this.divisionTeam = divisionTeam;
    }

    @Column(name = "player")
    @Id
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

        TeamPlayersEntityPK that = (TeamPlayersEntityPK) o;

        if (divisionTeam != that.divisionTeam) return false;
        if (player != that.player) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = divisionTeam;
        result = 31 * result + player;
        return result;
    }
}
