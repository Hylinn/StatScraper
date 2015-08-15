package io.github.hylinn.statistics.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "season")
@JsonIgnoreProperties(value = {"leagueSeasons"})
public class Season implements Serializable {

    private Integer id;
    private Integer year;
    private TimeOfYear time;
    private Set<LeagueSeason> leagueSeasons = new HashSet<>(0);

    protected Season() {}
    public Season(int id, Integer year, TimeOfYear time) {
        this.id = id;
        this.year = year;
        this.time = time;
    }

    @Id
    @Column(name = "season_id")
    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "season_year", nullable = false)
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) { this.year = year; }

    @ManyToOne()
    @JoinColumn(name = "season_time", nullable = false)
    public TimeOfYear getTimeOfYear() { return time; }
    public void setTimeOfYear(TimeOfYear time) { this.time = time; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade=CascadeType.ALL)
    public Set<LeagueSeason> getLeagueSeasons() { return leagueSeasons; }
    public void setLeagueSeasons(Set<LeagueSeason> leagueSeasons) { this.leagueSeasons = leagueSeasons; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Season that = (Season) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() { return "" + time + " " + year; }
}
