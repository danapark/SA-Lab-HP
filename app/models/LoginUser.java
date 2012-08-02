package models;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

public class LoginUser extends Model {
    private static final long serialVersionUID = 1L;
    private static Finder<Long, LoginUser> find = new Finder<Long, LoginUser>(
            Long.class, LoginUser.class);
    
    @Id
    public String login_id;
    public int password; // encrypted through hash
    @OneToOne
    public Student student;
    
    LoginUser(String login_id, int password) {
        this.login_id = login_id;
        this.password = password;
    }
    
    LoginUser(String login_id, int password, Long student_id) {
        this.login_id = login_id;
        this.password = password;
        this.student = Ebean.find(Student.class).where().eq("id", student_id).findUnique();
    }
    
    public static void deleteById(String login_id) {
        LoginUser.findById(login_id).delete();
    }
    
    public static LoginUser findById(String login_id) {
        return find.where().eq("login_id", login_id).findUnique();
    }
    
    public static List<LoginUser> all() {
        return find.all();
    }
    
    public static void assignPassword(String login_id, String password) {
        new LoginUser(login_id, password.hashCode()).update(LoginUser.findById(login_id));
    }
    
    public static boolean checkPassword(String login_id, String password) {
        return (LoginUser.findById(login_id).password == password.hashCode()) ? true : false;
    }
    
    public void addStudent(Long student_id) {
        this.student = Ebean.find(Student.class).where().eq("id", student_id).findUnique();
    }
}
