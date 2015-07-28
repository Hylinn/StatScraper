package io.github.hylinn.hibernate.statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Hylinn on 7/27/2015.
 */
@Entity
@Table(name = "play_seasons", schema = "", catalog = "statistics")
public class PlaySeasonsEntity {
    private String name;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaySeasonsEntity that = (PlaySeasonsEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
