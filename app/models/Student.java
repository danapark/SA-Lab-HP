package models;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
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
    public String degree;
    public String career;
    @Formats.DateTime(pattern = "YYYY-MM")
    public Date career_begin;
    
    public Blob image;
    // TODO image 처리는 차후에...
    
    public static Long create(Student student) {
        student.save();
        return student.id;
    }
    
    public static void update(Student student, Long id) {
        student.update(id);
    }

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
        return find
                .all();
    }

    public static List<Student> allMaster() {
        return find
                .where()
                    .eq("career", Constants.KAIST)
                    .eq("degree", Constants.Master)
                .orderBy("career_begin asc")
                .findList();
    }

    public static List<Student> allPhD() {
        return find
                .where()
                    .eq("career", Constants.KAIST)
                    .eq("degree", Constants.PhD)
                .orderBy("career_begin asc")
                .findList();
    }

    public static List<Student> allAlumni() {
        return find
                .where()
                    .ne("career", Constants.KAIST)
                .orderBy("career_begin desc")    
                .findList();
    }
}
