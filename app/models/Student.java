package models;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.avaje.ebean.Ebean;

import play.data.format.Formats;
import play.data.validation.Constraints.Email;
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
    
    @Email
    public String email;
    public String degree;
    public boolean isAlumni;
    public String company;
    
    @Formats.DateTime(pattern = "MM/dd/yyyy")
    public Date begin_date;
    
    @ManyToMany
    public List<ResearchArea> researchAreas;
    
    public Blob image;
    // TODO image 처리는 차후에...
    
    
    public static Long create(Student student) {
        student.save();
        return student.id;
    }
    
    public static void saveResearchArea(Long studentId, String researchAreaName) {
        Student student = Student.findById(studentId);
        if(!ResearchArea.alreadyExists(researchAreaName)) {
            ResearchArea.create(researchAreaName);
        }
        if(!ResearchArea.alreadyAdded(studentId, researchAreaName)){
            student.researchAreas.add(ResearchArea.findByName(researchAreaName));
            Ebean.saveManyToManyAssociations(student, "researchAreas");
        }
    }
    
    public static void deleteResearchArea(Long studentId, Long researchAreaId) {
        Student student = Student.findById(studentId);
        student.researchAreas.remove(ResearchArea.findById(researchAreaId));
        Ebean.saveManyToManyAssociations(student, "researchAreas");
    }

    public static Student findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }
    
    public static Student findByIdWithResearch(Long id) {
        return find
                .fetch("researchAreas")
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
                .fetch("researchAreas")
                .where()
                    .eq("isAlumni", false)
                    .eq("degree", Constants.Master)
                .orderBy("begin_date asc")
                .findList();
    }

    public static List<Student> allPhD() {
        return find
                .fetch("researchAreas")
                .where()
                    .eq("isAlumni", false)
                    .eq("degree", Constants.PhD)
                .orderBy("begin_date asc")
                .findList();
    }
    
    public static List<Student> allStudents() {
        return find
                .where()
                    .eq("isAlumni", false)
                .orderBy("family_name_en asc")
                .findList();
    }

    public static List<Student> allAlumni() {
        return find
                .fetch("researchAreas")
                .where()
                    .eq("isAlumni", true)
                .orderBy("begin_date desc")    
                .findList();
    }
    
    public static List<Student> allAlumniByYear(int year) {
        return find
                .fetch("researchAreas")
                .where()
                    .eq("isAlumni", true)
                    .contains("begin_date", Integer.toString(year))
                .orderBy("begin_date desc")    
                .findList();
    }
}