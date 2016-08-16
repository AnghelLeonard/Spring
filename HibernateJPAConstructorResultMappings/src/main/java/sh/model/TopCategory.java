package sh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import sh.pojo.CategoryDTO;

/**
 *
 * @author Anghel Leonard
 */
@SqlResultSetMapping(name="CategoryDTOMapping",
    classes = {
     @ConstructorResult(targetClass = CategoryDTO.class,
       columns = {@ColumnResult(name="namep"), @ColumnResult(name="names"), @ColumnResult(name="namet")}
     )}
)
@Entity
@Table(name = "topcategory")
@XmlRootElement
public class TopCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable=false)
    private String name;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "top", orphanRemoval=true, fetch=FetchType.LAZY)
    private Set<SecondaryCategory> secondary = new HashSet<>();
    
    public void addSecondaryCategory(SecondaryCategory sc){
        this.secondary.add(sc);
        sc.setTop(this);
    }

    public TopCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SecondaryCategory> getSecondary() {
        return secondary;
    }

    public void setSecondary(Set<SecondaryCategory> secondary) {
        this.secondary = secondary;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final TopCategory other = (TopCategory) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TopCategory{" + "id=" + id + ", name=" + name + ", secondary=" + secondary + '}';
    }      
}
