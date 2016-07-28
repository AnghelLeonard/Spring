package sh.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anghel Leonard
 */
@Entity
@Table(name = "child")
@NamedQueries({
    @NamedQuery(name = "Child.findAll", query = "SELECT c FROM Child c")})
public class Child implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)    
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "childname")
    private String childname;
    @ManyToOne
    private Parent parent;

    public Child() {
    }

    public Child(Long id) {
        this.id = id;
    }

    public Child(Long id, String childname) {
        this.id = id;
        this.childname = childname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.childname);
        hash = 89 * hash + Objects.hashCode(this.parent);
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
        final Child other = (Child) obj;
        if (!Objects.equals(this.childname, other.childname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        return true;
    }   
}
