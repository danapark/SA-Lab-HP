package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.db.ebean.Model;

public class History extends Model {
    //TODO 나중에 computer-database 예제 참조
    
    private static final long serialVersionUID = 1L;
    private static Finder<Long, History> find = new Finder<Long, History>(
            Long.class, History.class);
    
    @Id
    public Long id;
    public String career;
    public String place;
    @Formats.DateTime(pattern = "YYYY-MM")
    public Date duration_begin;
    @Formats.DateTime(pattern = "YYYY-MM")
    public Date duration_end;
    public boolean present;
    
    
    public static History findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }
    
    public static List<History> all() {
        return find.all();
    }
    
    public static List<History> findByBeginDesc() {
        return find.where().orderBy("duration_begin desc").findList();
    }
    
    public static List<History> findStudentByBeginAsc(String state) {
        //TODO Test 필요
        return find
                .fetch("student")
                .fetch("student.history_list")
                .where()
                    .eq("student.state", state)
                    .eq("present", true)
                .orderBy("duration_begin asc")
                .findList();
    }
    
    public static List<History> findByEndOrder() {
        return find.where().orderBy("duration_end desc").findList();
    }
    
    public static void update(History history, Long id) {
        history.update(id);
    }
}