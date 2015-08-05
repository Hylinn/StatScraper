package io.github.hylinn.statistics.hibernate.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "skater_statistics")
public class SkaterStatistics implements Serializable {

    private int id;
    private Integer number;
    private Integer gamesPlayed;
    private Integer goals;
    private Integer assists;
    private Integer powerplayGoals;
    private Integer powerplayAssists;
    private Integer shorthandedGoals;
    private Integer shorthandedAssists;
    private Integer gameWinningGoals;
    private Integer gameWinningAssists;
    private Integer penaltyShotGoals;
    private Integer emptyNetGoals;
    private Integer shotsOnGoal;
    private Integer penaltyMinutes;
    private DivisionTeamPlayer divisionTeamPlayer;

    protected SkaterStatistics() {}
    public SkaterStatistics(DivisionTeamPlayer divisionTeamPlayer) {
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

    @Column(name = "powerplay_goals")
    public Integer getPowerplayGoals() {
        return powerplayGoals;
    }
    public void setPowerplayGoals(Integer powerplayGoals) {
        this.powerplayGoals = powerplayGoals;
    }

    @Column(name = "powerplay_assists")
    public Integer getPowerplayAssists() {
        return powerplayAssists;
    }
    public void setPowerplayAssists(Integer powerplayAssists) {
        this.powerplayAssists = powerplayAssists;
    }

    @Column(name = "shorthanded_goals")
    public Integer getShorthandedGoals() {
        return shorthandedGoals;
    }
    public void setShorthandedGoals(Integer shorthandedGoals) {
        this.shorthandedGoals = shorthandedGoals;
    }

    @Column(name = "shorthanded_assists")
    public Integer getShorthandedAssists() {
        return shorthandedAssists;
    }
    public void setShorthandedAssists(Integer shorthandedAssists) {
        this.shorthandedAssists = shorthandedAssists;
    }

    @Column(name = "game_winning_goals")
    public Integer getGameWinningGoals() {
        return gameWinningGoals;
    }
    public void setGameWinningGoals(Integer gameWinningGoals) {
        this.gameWinningGoals = gameWinningGoals;
    }

    @Column(name = "game_winning_assists")
    public Integer getGameWinningAssists() {
        return gameWinningAssists;
    }
    public void setGameWinningAssists(Integer gameWinningAssists) {
        this.gameWinningAssists = gameWinningAssists;
    }

    @Column(name = "penalty_shot_goals")
    public Integer getPenaltyShotGoals() {
        return penaltyShotGoals;
    }
    public void setPenaltyShotGoals(Integer penaltyShotGoals) {
        this.penaltyShotGoals = penaltyShotGoals;
    }

    @Column(name = "empty_net_goals")
    public Integer getEmptyNetGoals() {
        return emptyNetGoals;
    }
    public void setEmptyNetGoals(Integer emptyNetGoals) {
        this.emptyNetGoals = emptyNetGoals;
    }

    @Column(name = "shots_on_goal")
    public Integer getShotsOnGoal() {
        return shotsOnGoal;
    }
    public void setShotsOnGoal(Integer shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
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

        SkaterStatistics that = (SkaterStatistics) o;

        if (id != that.id) return false;
        if (gamesPlayed != that.gamesPlayed) return false;
        if (goals != null ? !goals.equals(that.goals) : that.goals != null) return false;
        if (assists != null ? !assists.equals(that.assists) : that.assists != null) return false;
        if (powerplayGoals != null ? !powerplayGoals.equals(that.powerplayGoals) : that.powerplayGoals != null) return false;
        if (powerplayAssists != null ? !powerplayAssists.equals(that.powerplayAssists) : that.powerplayAssists != null) return false;
        if (shorthandedGoals != null ? !shorthandedGoals.equals(that.shorthandedGoals) : that.shorthandedGoals != null) return false;
        if (gameWinningGoals != null ? !gameWinningGoals.equals(that.gameWinningGoals) : that.gameWinningGoals != null) return false;
        if (gameWinningAssists != null ? !gameWinningAssists.equals(that.gameWinningAssists) : that.gameWinningAssists != null) return false;
        if (penaltyShotGoals != null ? !penaltyShotGoals.equals(that.penaltyShotGoals) : that.penaltyShotGoals != null) return false;
        if (emptyNetGoals != null ? !emptyNetGoals.equals(that.emptyNetGoals) : that.emptyNetGoals != null) return false;
        if (shotsOnGoal != null ? !shotsOnGoal.equals(that.shotsOnGoal) : that.shotsOnGoal != null) return false;
        if (penaltyMinutes != null ? !penaltyMinutes.equals(that.penaltyMinutes) : that.penaltyMinutes != null) return false;
        if (divisionTeamPlayer != null ? !divisionTeamPlayer.equals(that.divisionTeamPlayer) : that.divisionTeamPlayer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + gamesPlayed;
        result = 31 * result + (goals != null ? goals.hashCode() : 0);
        result = 31 * result + (assists != null ? assists.hashCode() : 0);
        result = 31 * result + (powerplayGoals != null ? powerplayGoals.hashCode() : 0);
        result = 31 * result + (powerplayAssists != null ? powerplayAssists.hashCode() : 0);
        result = 31 * result + (shorthandedGoals != null ? shorthandedGoals.hashCode() : 0);
        result = 31 * result + (gameWinningGoals != null ? gameWinningGoals.hashCode() : 0);
        result = 31 * result + (gameWinningAssists != null ? gameWinningAssists.hashCode() : 0);
        result = 31 * result + (penaltyShotGoals != null ? penaltyShotGoals.hashCode() : 0);
        result = 31 * result + (emptyNetGoals != null ? emptyNetGoals.hashCode() : 0);
        result = 31 * result + (shotsOnGoal != null ? shotsOnGoal.hashCode() : 0);
        result = 31 * result + (penaltyMinutes != null ? penaltyMinutes.hashCode() : 0);
        result = 31 * result + (divisionTeamPlayer != null ? divisionTeamPlayer.hashCode() : 0);
        return result;
    }
}
