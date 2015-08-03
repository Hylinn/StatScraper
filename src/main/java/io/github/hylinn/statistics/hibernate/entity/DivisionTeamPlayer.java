package io.github.hylinn.statistics.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "division_team_player", uniqueConstraints = @UniqueConstraint(columnNames = {"division_team_id", "player_id"}))
public class DivisionTeamPlayer implements Serializable {

    private int id;
    private DivisionTeam divisionTeam;
    private Player player;
    private GoalieStatistics goalieStats;
    private SkaterStatistics skaterStats;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "division_team_player_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "division_team_id", nullable = false)
    public DivisionTeam getDivisionTeam() {
        return divisionTeam;
    }
    public void setDivisionTeam(DivisionTeam divisionTeam) {
        this.divisionTeam = divisionTeam;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "divisionTeamPlayer", cascade = CascadeType.ALL)
    public GoalieStatistics getGoalieStats() {
        return goalieStats;
    }
    public void setGoalieStats(GoalieStatistics goalieStats) {
        this.goalieStats = goalieStats;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "divisionTeamPlayer", cascade = CascadeType.ALL)
    public SkaterStatistics getSkaterStats() {
        return skaterStats;
    }
    public void setSkaterStats(SkaterStatistics skaterStats) {
        this.skaterStats = skaterStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisionTeamPlayer that = (DivisionTeamPlayer) o;

        if (id != that.id) return false;
        if (divisionTeam != null ? !divisionTeam.equals(that.divisionTeam) : that.divisionTeam != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (goalieStats != null ? !goalieStats.equals(that.goalieStats) : that.goalieStats != null) return false;
        if (skaterStats != null ? !skaterStats.equals(that.skaterStats) : that.skaterStats != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (divisionTeam != null ? divisionTeam.hashCode() : 0);
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (goalieStats != null ? goalieStats.hashCode() : 0);
        result = 31 * result + (skaterStats != null ? skaterStats.hashCode() : 0);
        return result;
    }
}
