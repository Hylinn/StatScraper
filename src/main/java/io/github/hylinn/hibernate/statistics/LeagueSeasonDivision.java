package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "league_season_division", uniqueConstraints = @UniqueConstraint(columnNames = {"league_season_id", "division_id"}))
public class LeagueSeasonDivision implements Serializable {

    private int id;
    private LeagueSeason leagueSeason;
    private Division division;
    private Set<DivisionTeam> divisionTeams = new HashSet<DivisionTeam>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "league_season_division_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "league_season_id", nullable = false)
    public LeagueSeason getLeagueSeason() {
        return leagueSeason;
    }
    public void setLeagueSeason(LeagueSeason leagueSeason) {
        this.leagueSeason = leagueSeason;
    }

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    public Division getDivision() {
        return division;
    }
    public void setDivision(Division division) {
        this.division = division;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leagueSeasonDivision", cascade=CascadeType.ALL)
    public Set<DivisionTeam> getDivisionTeams() { return divisionTeams; }
    public void setDivisionTeams(Set<DivisionTeam> divisionTeams) { this.divisionTeams = divisionTeams; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeagueSeasonDivision that = (LeagueSeasonDivision) o;

        if (id != that.id) return false;
        if (leagueSeason != null ? !leagueSeason.equals(that.leagueSeason) : that.leagueSeason != null) return false;
        if (division != null ? !division.equals(that.division) : that.division != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (leagueSeason != null ? leagueSeason.hashCode() : 0);
        result = 31 * result + (division != null ? division.hashCode() : 0);
        return result;
    }
}
