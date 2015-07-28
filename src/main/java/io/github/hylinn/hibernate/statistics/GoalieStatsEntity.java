package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "goalie_stats", schema = "", catalog = "statistics")
public class GoalieStatsEntity {
    private int teamPlayer;
    private int gamesPlayed;
    private Integer shots;
    private Integer goalsAgainst;
    private Integer goals;
    private Integer assists;
    private Integer penaltyMinutes;

    @Id
    @Column(name = "team_player")
    public int getTeamPlayer() {
        return teamPlayer;
    }

    public void setTeamPlayer(int teamPlayer) {
        this.teamPlayer = teamPlayer;
    }

    @Basic
    @Column(name = "games_played")
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Basic
    @Column(name = "shots")
    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    @Basic
    @Column(name = "goals_against")
    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Basic
    @Column(name = "goals")
    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    @Basic
    @Column(name = "assists")
    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    @Basic
    @Column(name = "penalty_minutes")
    public Integer getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public void setPenaltyMinutes(Integer penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalieStatsEntity that = (GoalieStatsEntity) o;

        if (teamPlayer != that.teamPlayer) return false;
        if (gamesPlayed != that.gamesPlayed) return false;
        if (shots != null ? !shots.equals(that.shots) : that.shots != null) return false;
        if (goalsAgainst != null ? !goalsAgainst.equals(that.goalsAgainst) : that.goalsAgainst != null) return false;
        if (goals != null ? !goals.equals(that.goals) : that.goals != null) return false;
        if (assists != null ? !assists.equals(that.assists) : that.assists != null) return false;
        if (penaltyMinutes != null ? !penaltyMinutes.equals(that.penaltyMinutes) : that.penaltyMinutes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamPlayer;
        result = 31 * result + gamesPlayed;
        result = 31 * result + (shots != null ? shots.hashCode() : 0);
        result = 31 * result + (goalsAgainst != null ? goalsAgainst.hashCode() : 0);
        result = 31 * result + (goals != null ? goals.hashCode() : 0);
        result = 31 * result + (assists != null ? assists.hashCode() : 0);
        result = 31 * result + (penaltyMinutes != null ? penaltyMinutes.hashCode() : 0);
        return result;
    }
}
