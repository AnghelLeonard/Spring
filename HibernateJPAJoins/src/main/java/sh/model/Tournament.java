package sh.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anghel Leonard
 */
@Entity
@Table(name = "tournaments")
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "tournament_name", nullable = false, length = 35)
    @NotNull
    @Size(min = 3, max = 35)
    private String tournamentname;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)    
    private Set<Player> players = new LinkedHashSet<Player>();

    // constructors

    public Tournament() {
    }        
    
    public Tournament(Long id) {
        this.id = id;
    }

    public Tournament(Long id, String tournamentname) {
        this.id = id;
        this.tournamentname = tournamentname;
    }

    // helpers
    public void addPlayer(Player player) {
        players.add(player);
        player.setTournament(this);
    }

    public void removePlayer(Player player) {
        player.setTournament(null);
        this.players.remove(player);
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTournamentname() {
        return tournamentname;
    }

    public void setTournamentname(String tournamentname) {
        this.tournamentname = tournamentname;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }  

    // hashCode(), equals() and toString()

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.tournamentname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tournament other = (Tournament) obj;
        if (!Objects.equals(this.tournamentname, other.tournamentname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Tournament{" + "id=" + id + ", name=" + tournamentname + '}';
    }
}
