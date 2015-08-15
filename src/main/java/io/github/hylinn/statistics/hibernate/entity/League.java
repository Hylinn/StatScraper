package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "league")
@JsonIgnoreProperties(value = {"leagueSeasons"})
public class League implements Serializable {

    private Integer id;
    private String name;
    private Set<LeagueSeason> leagueSeasons = new HashSet<>(0);

    protected League() {}
    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "league_id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "league_name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "league", cascade=CascadeType.ALL)
    public Set<LeagueSeason> getLeagueSeasons() { return leagueSeasons; }
    public void setLeagueSeasons(Set<LeagueSeason> leagueSeasons) { this.leagueSeasons = leagueSeasons; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        League that = (League) o;

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
        return getName() + " League";
    }
}
