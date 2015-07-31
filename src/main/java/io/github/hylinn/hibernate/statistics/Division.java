package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "division")
public class Division implements Serializable {

    private int id;
    private String name;
    private Set<LeagueSeasonDivision> leagueSeasonDivisions = new HashSet<LeagueSeasonDivision>(0);

    @Id
    @Column(name = "division_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "division_name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "division", cascade=CascadeType.ALL)
    public Set<LeagueSeasonDivision> getLeagueSeasonDivisions() { return leagueSeasonDivisions; }
    public void setLeagueSeasonDivisions(Set<LeagueSeasonDivision> leagueSeasonDivisions) { this.leagueSeasonDivisions = leagueSeasonDivisions; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Division that = (Division) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (leagueSeasonDivisions != null ? !leagueSeasonDivisions.equals(that.leagueSeasonDivisions) : that.leagueSeasonDivisions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (leagueSeasonDivisions != null ? leagueSeasonDivisions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName() + " Division, " + getId();
    }
}
