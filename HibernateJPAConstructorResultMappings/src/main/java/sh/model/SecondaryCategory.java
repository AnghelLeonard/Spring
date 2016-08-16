package sh.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anghel Leonard
 */
@Entity
@Table(name = "secondarycategory")
@XmlRootElement
public class SecondaryCategory {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secondary", orphanRemoval=true)
    private Set<ThirdCategory> third = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "top_id")
    private TopCategory top;
    
    public void addThirdCategory(ThirdCategory tc){
        this.third.add(tc);
        tc.setSecondary(this);
    }

    public SecondaryCategory() {
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

    public Set<ThirdCategory> getThird() {
        return third;
    }

    public void setThird(Set<ThirdCategory> third) {
        this.third = third;
    }

    public TopCategory getTop() {
        return top;
    }

    public void setTop(TopCategory top) {
        this.top = top;
    }
           
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final SecondaryCategory other = (SecondaryCategory) obj;
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
        return "SecondaryCategory{" + "id=" + id + ", name=" + name + ", third=" + third + '}';
    }        
}
