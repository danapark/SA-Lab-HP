package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import utils.Constants;

@Entity
public class Course extends Model{
    private static final long serialVersionUID = 1L;
    private static Finder<Long, Course> find = new Finder<Long, Course>(
            Long.class, Course.class);

    @Id
    public Long   id;
    
    public String   name;
    public String   courseId;
    public String   semester;
    public int      year;
    public String   overview;
    public String   introduction;
    public String   classroom;
    public String   url;
    
    public static Long create(Course Course) {
        Course.save();
        return Course.id;
    }
    public static List<Course> allCourses() {
        return find
                .all();
    }
}
