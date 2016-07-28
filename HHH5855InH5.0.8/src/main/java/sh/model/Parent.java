package sh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anghel Leonard
 */
@Entity
@Table(name = "parent")
@NamedQueries({
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p")})
public class Parent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "parentname")
    private String parentname;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "parent", orphanRemoval = true)
    private List<Child> childList = new ArrayList();

    public Parent() {
    }

    public Parent(Long id) {
        this.id = id;
    }

    public Parent(Long id, String parentname) {
        this.id = id;
        this.parentname = parentname;
    }

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public void removeChild(Child child) {
        childList.remove(child);
        child.setParent(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Parent other = (Parent) obj;
        if (!Objects.equals(this.parentname, other.parentname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.childList, other.childList)) {
            return false;
        }
        return true;
    }    
}
