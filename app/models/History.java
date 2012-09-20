package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Update;

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
    
    @Formats.DateTime(pattern = "MM/dd/yyyy")
    public Date begin_date;
    
    @Formats.DateTime(pattern = "MM/dd/yyyy")
    public Date end_date;
    
    public static Long create(History history) {
        history.save();
        return history.id;
    }
    
    @Override
    public void update() {
        String updateHistory = "update history set " +
        		"organization = :organization, " +
        		"position = :position, " +
        		"kind = :kind, " +
        		"isRepresentitive = :isRepresentitive, " +
        		"begin_date = :begin_date, " +
        		"end_date = :end_date " +
        		"where id = :id";
        Update<History> update = Ebean.createUpdate(History.class, updateHistory);
        update.setParameter("id", this.id);
        update.setParameter("organization", this.organization);
        update.setParameter("position", this.position);
        update.setParameter("kind", this.kind);
        update.setParameter("isRepresentitive", this.isRepresentitive);
        update.setParameter("begin_date", this.begin_date);
        update.setParameter("end_date", this.end_date);
        
        update.execute();
    }
    
    public static History findById(Long id) {
        return find
                .where()
                    .eq("id", id)
                .findUnique();
    }
    
    public static List<History> all() {
        return find.all();
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
