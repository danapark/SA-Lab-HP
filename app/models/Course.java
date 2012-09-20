package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

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
    
    @Column(columnDefinition = "TEXT")
    public String   overview;
    
    @Column(columnDefinition = "TEXT")
    public String   introduction;
    
    public String   introduction_url;
    public String   classroom;
    public String   point_distribution;
    public String   courseTimeFrom;
    public String   courseTimeTo;
    
    public static Long create(Course Course) {
        Course.save();
        return Course.id;
    }
    public static List<Course> allCoursesDesc() {
        return find
                .orderBy("year desc")
                .findList();
    }
    public static Course findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }
}
