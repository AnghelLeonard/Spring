package sh.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anghel Leonard
 */
@Entity
@Table(name = "players")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
        
    @Basic(optional = false)
    @Column(name = "player_rank", nullable = false)
    @NotNull    
    private int rank;

    @Basic(optional = false)
    @Column(name = "player_name", nullable = false, length = 35)
    @NotNull
    @Size(min = 3, max = 35)
    private String playername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    // constructors

    public Player() {
    }        

    public Player(Long id, int rank, String playername, Tournament tournament) {
        this.id = id;
        this.rank = rank;
        this.playername = playername;
        this.tournament = tournament;
    }        

    // getters and setters    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }        
       
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + this.rank;
        hash = 67 * hash + Objects.hashCode(this.playername);
        return hash;
    }

    // hashCode(), equals() and toString()
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
        final Player other = (Player) obj;
        if (this.rank != other.rank) {
            return false;
        }
        if (!Objects.equals(this.playername, other.playername)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", playername=" + playername + '}';
    }
}
