package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "skater_stats", schema = "", catalog = "statistics")
public class SkaterStatsEntity {
    private int teamPlayer;
    private int gamesPlayed;
    private Integer goals;
    private Integer assists;
    private Integer powerplayGoals;
    private Integer powerplayAssists;
    private Integer shorthandedGoals;
    private Integer gameWinningGoals;
    private Integer gameWinningAssits;
    private Integer penaltyShotGoals;
    private Integer emptyNetGoals;
    private Integer shotsOnGoal;
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
    @Column(name = "powerplay_goals")
    public Integer getPowerplayGoals() {
        return powerplayGoals;
    }

    public void setPowerplayGoals(Integer powerplayGoals) {
        this.powerplayGoals = powerplayGoals;
    }

    @Basic
    @Column(name = "powerplay_assists")
    public Integer getPowerplayAssists() {
        return powerplayAssists;
    }

    public void setPowerplayAssists(Integer powerplayAssists) {
        this.powerplayAssists = powerplayAssists;
    }

    @Basic
    @Column(name = "shorthanded_goals")
    public Integer getShorthandedGoals() {
        return shorthandedGoals;
    }

    public void setShorthandedGoals(Integer shorthandedGoals) {
        this.shorthandedGoals = shorthandedGoals;
    }

    @Basic
    @Column(name = "game_winning_goals")
    public Integer getGameWinningGoals() {
        return gameWinningGoals;
    }

    public void setGameWinningGoals(Integer gameWinningGoals) {
        this.gameWinningGoals = gameWinningGoals;
    }

    @Basic
    @Column(name = "game_winning_assits")
    public Integer getGameWinningAssits() {
        return gameWinningAssits;
    }

    public void setGameWinningAssits(Integer gameWinningAssits) {
        this.gameWinningAssits = gameWinningAssits;
    }

    @Basic
    @Column(name = "penalty_shot_goals")
    public Integer getPenaltyShotGoals() {
        return penaltyShotGoals;
    }

    public void setPenaltyShotGoals(Integer penaltyShotGoals) {
        this.penaltyShotGoals = penaltyShotGoals;
    }

    @Basic
    @Column(name = "empty_net_goals")
    public Integer getEmptyNetGoals() {
        return emptyNetGoals;
    }

    public void setEmptyNetGoals(Integer emptyNetGoals) {
        this.emptyNetGoals = emptyNetGoals;
    }

    @Basic
    @Column(name = "shots_on_goal")
    public Integer getShotsOnGoal() {
        return shotsOnGoal;
    }

    public void setShotsOnGoal(Integer shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
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

        SkaterStatsEntity that = (SkaterStatsEntity) o;

        if (teamPlayer != that.teamPlayer) return false;
        if (gamesPlayed != that.gamesPlayed) return false;
        if (goals != null ? !goals.equals(that.goals) : that.goals != null) return false;
        if (assists != null ? !assists.equals(that.assists) : that.assists != null) return false;
        if (powerplayGoals != null ? !powerplayGoals.equals(that.powerplayGoals) : that.powerplayGoals != null)
            return false;
        if (powerplayAssists != null ? !powerplayAssists.equals(that.powerplayAssists) : that.powerplayAssists != null)
            return false;
        if (shorthandedGoals != null ? !shorthandedGoals.equals(that.shorthandedGoals) : that.shorthandedGoals != null)
            return false;
        if (gameWinningGoals != null ? !gameWinningGoals.equals(that.gameWinningGoals) : that.gameWinningGoals != null)
            return false;
        if (gameWinningAssits != null ? !gameWinningAssits.equals(that.gameWinningAssits) : that.gameWinningAssits != null)
            return false;
        if (penaltyShotGoals != null ? !penaltyShotGoals.equals(that.penaltyShotGoals) : that.penaltyShotGoals != null)
            return false;
        if (emptyNetGoals != null ? !emptyNetGoals.equals(that.emptyNetGoals) : that.emptyNetGoals != null)
            return false;
        if (shotsOnGoal != null ? !shotsOnGoal.equals(that.shotsOnGoal) : that.shotsOnGoal != null) return false;
        if (penaltyMinutes != null ? !penaltyMinutes.equals(that.penaltyMinutes) : that.penaltyMinutes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamPlayer;
        result = 31 * result + gamesPlayed;
        result = 31 * result + (goals != null ? goals.hashCode() : 0);
        result = 31 * result + (assists != null ? assists.hashCode() : 0);
        result = 31 * result + (powerplayGoals != null ? powerplayGoals.hashCode() : 0);
        result = 31 * result + (powerplayAssists != null ? powerplayAssists.hashCode() : 0);
        result = 31 * result + (shorthandedGoals != null ? shorthandedGoals.hashCode() : 0);
        result = 31 * result + (gameWinningGoals != null ? gameWinningGoals.hashCode() : 0);
        result = 31 * result + (gameWinningAssits != null ? gameWinningAssits.hashCode() : 0);
        result = 31 * result + (penaltyShotGoals != null ? penaltyShotGoals.hashCode() : 0);
        result = 31 * result + (emptyNetGoals != null ? emptyNetGoals.hashCode() : 0);
        result = 31 * result + (shotsOnGoal != null ? shotsOnGoal.hashCode() : 0);
        result = 31 * result + (penaltyMinutes != null ? penaltyMinutes.hashCode() : 0);
        return result;
    }
}
