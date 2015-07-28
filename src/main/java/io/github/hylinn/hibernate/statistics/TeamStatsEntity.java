package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "team_stats", schema = "", catalog = "statistics")
public class TeamStatsEntity {
    private int divisionTeam;
    private Integer wins;
    private Integer losses;
    private Integer ties;
    private Integer overtimeLosses;
    private Integer gameMisconducts;

    @Id
    @Column(name = "division_team")
    public int getDivisionTeam() {
        return divisionTeam;
    }

    public void setDivisionTeam(int divisionTeam) {
        this.divisionTeam = divisionTeam;
    }

    @Basic
    @Column(name = "wins")
    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    @Basic
    @Column(name = "losses")
    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    @Basic
    @Column(name = "ties")
    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }

    @Basic
    @Column(name = "overtime_losses")
    public Integer getOvertimeLosses() {
        return overtimeLosses;
    }

    public void setOvertimeLosses(Integer overtimeLosses) {
        this.overtimeLosses = overtimeLosses;
    }

    @Basic
    @Column(name = "game_misconducts")
    public Integer getGameMisconducts() {
        return gameMisconducts;
    }

    public void setGameMisconducts(Integer gameMisconducts) {
        this.gameMisconducts = gameMisconducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatsEntity that = (TeamStatsEntity) o;

        if (divisionTeam != that.divisionTeam) return false;
        if (wins != null ? !wins.equals(that.wins) : that.wins != null) return false;
        if (losses != null ? !losses.equals(that.losses) : that.losses != null) return false;
        if (ties != null ? !ties.equals(that.ties) : that.ties != null) return false;
        if (overtimeLosses != null ? !overtimeLosses.equals(that.overtimeLosses) : that.overtimeLosses != null)
            return false;
        if (gameMisconducts != null ? !gameMisconducts.equals(that.gameMisconducts) : that.gameMisconducts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = divisionTeam;
        result = 31 * result + (wins != null ? wins.hashCode() : 0);
        result = 31 * result + (losses != null ? losses.hashCode() : 0);
        result = 31 * result + (ties != null ? ties.hashCode() : 0);
        result = 31 * result + (overtimeLosses != null ? overtimeLosses.hashCode() : 0);
        result = 31 * result + (gameMisconducts != null ? gameMisconducts.hashCode() : 0);
        return result;
    }
}
