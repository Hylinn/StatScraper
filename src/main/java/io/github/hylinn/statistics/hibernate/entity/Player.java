package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player")
@JsonIgnoreProperties(value = {"divisionTeamPlayers"})
public class Player implements Serializable {

    private String name;
    private String displayName;
    private Set<DivisionTeamPlayer> divisionTeamPlayers = new HashSet<>(0);

    protected Player() {}
    public Player(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    @Id
    @Column(name = "player_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player", cascade=CascadeType.ALL)
    public Set<DivisionTeamPlayer> getDivisionTeamPlayers() { return divisionTeamPlayers; }
    public void setDivisionTeamPlayers(Set<DivisionTeamPlayer> divisionTeamPlayers) { this.divisionTeamPlayers = divisionTeamPlayers; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player that = (Player) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() { return displayName; }
}
