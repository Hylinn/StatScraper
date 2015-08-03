package io.github.hylinn.statistics.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    private int id;
    private String name;
    private Set<DivisionTeam> divisionTeams = new HashSet<DivisionTeam>(0);

    protected Team() {}

    @Id
    @Column(name = "team_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "team_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade=CascadeType.ALL)
    public Set<DivisionTeam> getDivisionTeams() { return divisionTeams; }
    public void setDivisionTeams(Set<DivisionTeam> divisionTeams) { this.divisionTeams = divisionTeams; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team that = (Team) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (divisionTeams != null ? !divisionTeams.equals(that.divisionTeams) : that.divisionTeams != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (divisionTeams != null ? divisionTeams.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Team " + getName() + ", " + getId();
    }
}
