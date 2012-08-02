package models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class Student extends Model {
    private static final long serialVersionUID = 1L;
    private static Finder<Long, Student> find = new Finder<Long, Student>(
            Long.class, Student.class);

    @Id
    public Long id;
    public String family_name_en;
    public String name_en;
    public String family_name_ko;
    public String name_ko;
    public String email;
    public List<String> research_areas;
    public String present_career;
    public Blob image;
    @OneToOne (mappedBy = "student")
    public LoginUser loginUser;
    
    
    
    public static Student findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static Student findByName(String family_name_en, String name_en) {
        return find.where().eq("family_name_en", family_name_en).eq("name_en", name_en).findUnique();
    }

    public static List<Student> all() {
        return find.all();
    }

}
