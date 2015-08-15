package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team_player", uniqueConstraints = @UniqueConstraint(columnNames = {"division_team_id", "player_name"}))
@JsonIgnoreProperties(value = {"goalieStats", "skaterStats"})
public class DivisionTeamPlayer implements Serializable {

    private Integer id;
    private DivisionTeam divisionTeam;
    private Player player;
    private GoalieStatistics goalieStats;
    private SkaterStatistics skaterStats;

    protected DivisionTeamPlayer() {}
    public DivisionTeamPlayer(DivisionTeam divisionTeam, Player player) {
        this.divisionTeam = divisionTeam;
        this.player = player;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_player_id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "player_name", nullable = false)
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (divisionTeam != null ? !divisionTeam.equals(that.divisionTeam) : that.divisionTeam != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (divisionTeam != null ? divisionTeam.hashCode() : 0);
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }
}
