package io.github.hylinn.statistics.hibernate.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "team_statistics")
public class TeamStatistics implements Serializable {

    private int id;
    private Integer wins;
    private Integer losses;
    private Integer ties;
    private Integer overtimeLosses;
    private Integer gameMisconducts;
    private DivisionTeam divisionTeam;

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "divisionTeam"))
    @GeneratedValue(generator = "generator")
    @Column(name = "division_team_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "wins")
    public Integer getWins() {
        return wins;
    }
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    @Column(name = "losses")
    public Integer getLosses() {
        return losses;
    }
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    @Column(name = "ties")
    public Integer getTies() {
        return ties;
    }
    public void setTies(Integer ties) {
        this.ties = ties;
    }

    @Column(name = "overtime_losses")
    public Integer getOvertimeLosses() {
        return overtimeLosses;
    }
    public void setOvertimeLosses(Integer overtimeLosses) {
        this.overtimeLosses = overtimeLosses;
    }

    @Column(name = "game_misconducts")
    public Integer getGameMisconducts() {
        return gameMisconducts;
    }
    public void setGameMisconducts(Integer gameMisconducts) {
        this.gameMisconducts = gameMisconducts;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public DivisionTeam getDivisionTeam() {
        return divisionTeam;
    }
    public void setDivisionTeam(DivisionTeam divisionTeam) {
        this.divisionTeam = divisionTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatistics that = (TeamStatistics) o;

        if (id != that.id) return false;
        if (wins != null ? !wins.equals(that.wins) : that.wins != null) return false;
        if (losses != null ? !losses.equals(that.losses) : that.losses != null) return false;
        if (ties != null ? !ties.equals(that.ties) : that.ties != null) return false;
        if (overtimeLosses != null ? !overtimeLosses.equals(that.overtimeLosses) : that.overtimeLosses != null) return false;
        if (gameMisconducts != null ? !gameMisconducts.equals(that.gameMisconducts) : that.gameMisconducts != null) return false;
        if (divisionTeam != null ? !divisionTeam.equals(that.divisionTeam) : that.divisionTeam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wins != null ? wins.hashCode() : 0);
        result = 31 * result + (losses != null ? losses.hashCode() : 0);
        result = 31 * result + (ties != null ? ties.hashCode() : 0);
        result = 31 * result + (overtimeLosses != null ? overtimeLosses.hashCode() : 0);
        result = 31 * result + (gameMisconducts != null ? gameMisconducts.hashCode() : 0);
        result = 31 * result + (overtimeLosses != null ? overtimeLosses.hashCode() : 0);
        return result;
    }
}
