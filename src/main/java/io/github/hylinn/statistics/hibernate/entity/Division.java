package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "division")
@JsonIgnoreProperties(value = {"leagueSeasonDivisions"})
public class Division implements Serializable {

    private Integer id;
    private String name;
    private Set<LeagueSeasonDivision> leagueSeasonDivisions = new HashSet<>(0);

    protected Division() {}
    public Division(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "division_id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName() + " Division";
    }
}
