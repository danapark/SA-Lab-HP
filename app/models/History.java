package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
public class History extends Model {
    private static final long serialVersionUID = 1L;
    private static Finder<Long, History> find = new Finder<Long, History>(Long.class, History.class);
    
    public static String PROFESSIONAL = "professional";
    public static String ACTIVITY = "activity";
    
    @Id
    public Long id;
    
    public String organization;
    public String position;
    public String kind;
    public boolean isRepresentitive;
    
    @Formats.DateTime(pattern = "dd/mm/yy")
    public Date begin_date;
    
    @Formats.DateTime(pattern = "dd/mm/yy")
    public Date end_date;
    
    public static Long create(History history) {
        history.save();
        return history.id;
    }
    
    public static History findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }
    
    public static List<History> allProfessional() {
        return find
                .where()
                    .eq("kind", PROFESSIONAL)
                .orderBy("begin_date desc")
                .findList();
    }
    
    public static List<History> allActivities() {
        return find
                .where()
                    .eq("kind", ACTIVITY)
                .orderBy("begin_date desc")
                .findList();
    }
    
    public static List<History> representitive() {
        return find
                .where()
                    .eq("isRepresentitive", true)
                .orderBy("begin_date desc")
                .findList();
    }
    
}
