package io.github.hylinn.statistics.hibernate.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "goalie_statistics")
public class GoalieStatistics implements Serializable{

    private int id;
    private Integer number;
    private Integer gamesPlayed;
    private Integer shots;
    private Integer goalsAgainst;
    private Integer goals;
    private Integer assists;
    private Integer penaltyMinutes;
    private DivisionTeamPlayer divisionTeamPlayer;

    protected GoalieStatistics() {}
    public GoalieStatistics(DivisionTeamPlayer divisionTeamPlayer) {
        this.divisionTeamPlayer = divisionTeamPlayer;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "divisionTeamPlayer"))
    @GeneratedValue(generator = "generator")
    @Column(name = "division_team_player_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "number")
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    @Column(name = "games_played")
    public Integer getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Column(name = "shots")
    public Integer getShots() {
        return shots;
    }
    public void setShots(Integer shots) {
        this.shots = shots;
    }

    @Column(name = "goals_against")
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }
    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Column(name = "goals")
    public Integer getGoals() {
        return goals;
    }
    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    @Column(name = "assists")
    public Integer getAssists() {
        return assists;
    }
    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    @Column(name = "penalty_minutes")
    public Integer getPenaltyMinutes() {
        return penaltyMinutes;
    }
    public void setPenaltyMinutes(Integer penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public DivisionTeamPlayer getDivisionTeamPlayer() {
        return divisionTeamPlayer;
    }
    public void setDivisionTeamPlayer(DivisionTeamPlayer divisionTeamPlayer) {
        this.divisionTeamPlayer = divisionTeamPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalieStatistics that = (GoalieStatistics) o;

        if (id != that.id) return false;
        if (gamesPlayed != that.gamesPlayed) return false;
        if (shots != null ? !shots.equals(that.shots) : that.shots != null) return false;
        if (goalsAgainst != null ? !goalsAgainst.equals(that.goalsAgainst) : that.goalsAgainst != null) return false;
        if (goals != null ? !goals.equals(that.goals) : that.goals != null) return false;
        if (assists != null ? !assists.equals(that.assists) : that.assists != null) return false;
        if (penaltyMinutes != null ? !penaltyMinutes.equals(that.penaltyMinutes) : that.penaltyMinutes != null) return false;
        if (divisionTeamPlayer != null ? !divisionTeamPlayer.equals(that.divisionTeamPlayer) : that.divisionTeamPlayer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + gamesPlayed;
        result = 31 * result + (shots != null ? shots.hashCode() : 0);
        result = 31 * result + (goalsAgainst != null ? goalsAgainst.hashCode() : 0);
        result = 31 * result + (goals != null ? goals.hashCode() : 0);
        result = 31 * result + (assists != null ? assists.hashCode() : 0);
        result = 31 * result + (penaltyMinutes != null ? penaltyMinutes.hashCode() : 0);
        result = 31 * result + (divisionTeamPlayer != null ? divisionTeamPlayer.hashCode() : 0);
        return result;
    }
}
