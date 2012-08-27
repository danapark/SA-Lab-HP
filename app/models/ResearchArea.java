package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class ResearchArea extends Model {
    private static final long serialVersionUID = 1L;
    private static Finder<Long, ResearchArea> find = new Finder<Long, ResearchArea>(
            Long.class, ResearchArea.class);
    
    @Id
    public Long id;
    public String name;

    @ManyToMany
    public List<Student> students;
    
    public static boolean create(String name) {
        if(ResearchArea.findByName(name) == null){
            ResearchArea researchArea = new ResearchArea();
            researchArea.name = name;
            researchArea.save();
            return true;
        } else
            return false;
    }
    
    public static ResearchArea findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }
    
    public static ResearchArea findByName(String name) {
        return find
                .where()
                    .eq("name", name)
                .findUnique();
    }
    
    public static void update(ResearchArea researchArea, Long id) {
        researchArea.update(id);
    }
}
