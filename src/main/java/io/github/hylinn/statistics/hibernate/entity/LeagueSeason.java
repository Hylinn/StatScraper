package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "league_season", uniqueConstraints = @UniqueConstraint(columnNames = {"league_id", "season_id"}))
@JsonIgnoreProperties(value = {"leagueSeasonDivisions"})
public class LeagueSeason implements Serializable {

    private Integer id;
    private League league;
    private Season season;
    private Set<LeagueSeasonDivision> leagueSeasonDivisions = new HashSet<>(0);

    protected LeagueSeason() {}
    public LeagueSeason(League league, Season season) {
        this.league = league;
        this.season = season;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "league_season_id")
    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    public League getLeague() { return league; }
    public void setLeague(League league) { this.league = league; }

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leagueSeason", cascade=CascadeType.ALL)
    public Set<LeagueSeasonDivision> getLeagueSeasonDivisions() { return leagueSeasonDivisions; }
    public void setLeagueSeasonDivisions(Set<LeagueSeasonDivision> leagueSeasonDivisions) { this.leagueSeasonDivisions = leagueSeasonDivisions; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeagueSeason that = (LeagueSeason) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (league != null ? !league.equals(that.league) : that.league != null) return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;

        return true;
    }

    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (league != null ? league.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        return result;
    }
}
