package models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;
import utils.Constants;


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
    public String degree;
    public Blob image;
    
    public static Student findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }

    public static Student findByName(String family_name_en, String name_en) {
        return find
                .where()
                    .eq("family_name_en", family_name_en)
                    .eq("name_en", name_en)
                .findUnique();
    }

    public static List<Student> all() {
        return find.all();
    }
    
    public static List<Student> allMaster() {
        //TODO 입학년도 순으로 정렬하기 (차후 History)
        return find
                .where()
                    .eq("present_career", Constants.KAIST)
                    .eq("degree", Constants.Master)
                .findList();
    }
    
    public static List<Student> allPhD() {
        //TODO 입학년도 순으로 정렬하기 (차후 History)
        return find
                .where()
                    .eq("present_career", Constants.KAIST)
                    .eq("degree", Constants.PhD)
                .findList();
    }
    
    public static List<Student> allAlumni() {
        //TODO 졸업년도 순으로 정렬하기 (차후 History)
        return find
                .where()
                    .ne("present_career", Constants.KAIST)
                .findList();
    }
}
