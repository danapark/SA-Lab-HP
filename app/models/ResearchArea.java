package models;

import java.util.ArrayList;
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

    @ManyToMany(targetEntity = models.Student.class, mappedBy = "researchAreas")
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
    
    public static List<String> findAllNames() {
        List<String> results = new ArrayList<String>();
        for(ResearchArea researchArea : find.select("name").orderBy("name asc").findList()){
            results.add("\"" + researchArea.name + "\"");
        }
        return results;
    }
    
    public static void update(ResearchArea researchArea, Long id) {
        researchArea.update(id);
    }
    
    //FIXME 테스트 안 만들었다..
    public static boolean alreadyExists(String researchAreaName) {
        int findRowCount = find .where()
                                    .eq("name", researchAreaName)
                                .findRowCount();
        return (findRowCount != 0) ? true : false;
    }
    
    public static boolean alreadyAdded(Long studentId, String researchAreaName) {
        int findRowCount = find .where()
                                    .eq("students.id", studentId)
                                    .eq("name", researchAreaName)
                                .findRowCount();
        return (findRowCount != 0) ? true : false;
    }
}
