package io.github.hylinn.hibernate.statistics;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    private int id;
    private String name;
    private String displayName;
    private Set<DivisionTeamPlayer> divisionTeamPlayers = new HashSet<DivisionTeamPlayer>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (divisionTeamPlayers != null ? !divisionTeamPlayers.equals(that.divisionTeamPlayers) : that.divisionTeamPlayers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (divisionTeamPlayers != null ? divisionTeamPlayers.hashCode() : 0);
        return result;
    }
}
