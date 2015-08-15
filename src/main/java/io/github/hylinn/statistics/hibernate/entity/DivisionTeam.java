package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "division_team", uniqueConstraints = @UniqueConstraint(columnNames = {"league_season_division_id", "team_id"}))
@JsonIgnoreProperties(value = {"divisionTeamPlayers", "statistics"})
public class DivisionTeam implements Serializable {

    private Integer id;
    private Team team;
    private LeagueSeasonDivision leagueSeasonDivision;
    private Set<DivisionTeamPlayer> divisionTeamPlayers = new HashSet<>(0);
    private TeamStatistics statistics;

    protected DivisionTeam() {}
    public DivisionTeam(LeagueSeasonDivision leagueSeasonDivision, Team team) {
        this.leagueSeasonDivision = leagueSeasonDivision;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "division_team_id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "league_season_division_id", nullable = false)
    public LeagueSeasonDivision getLeagueSeasonDivision() {
        return leagueSeasonDivision;
    }
    public void setLeagueSeasonDivision(LeagueSeasonDivision leagueSeasonDivision) {
        this.leagueSeasonDivision = leagueSeasonDivision;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "divisionTeam", cascade=CascadeType.ALL)
    public Set<DivisionTeamPlayer> getDivisionTeamPlayers() { return divisionTeamPlayers; }
    public void setDivisionTeamPlayers(Set<DivisionTeamPlayer> divisionTeamPlayers) { this.divisionTeamPlayers = divisionTeamPlayers; }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "divisionTeam", cascade = CascadeType.ALL)
    public TeamStatistics getStatistics() {
        return statistics;
    }
    public void setStatistics(TeamStatistics statistics) {
        this.statistics = statistics;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisionTeam that = (DivisionTeam) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (leagueSeasonDivision != null ? !leagueSeasonDivision.equals(that.leagueSeasonDivision) : that.leagueSeasonDivision != null) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (leagueSeasonDivision != null ? leagueSeasonDivision.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
}
