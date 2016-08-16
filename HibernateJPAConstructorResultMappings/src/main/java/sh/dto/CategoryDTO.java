package sh.dto;

import java.io.Serializable;

/**
 *
 * @author Anghel Leonard
 */
public class CategoryDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    String namep;
    String names;
    String namet;

    public CategoryDTO(String namep, String names, String namet) {
        this.namep = namep;
        this.names = names;
        this.namet = namet;
    }
        
    public String getNamep() {
        return namep;
    }

    public void setNamep(String namep) {
        this.namep = namep;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "namep=" + namep + ", names=" + names + ", namet=" + namet + '}';
    }                 
}
