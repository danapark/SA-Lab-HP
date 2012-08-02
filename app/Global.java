import java.util.List;
import java.util.Map;

import models.LoginUser;
import models.Student;

import com.avaje.ebean.Ebean;

import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

public class Global extends GlobalSettings {
    public void onStart(Application app) {
        InitialData.insert(app);
    }

    static class InitialData {

        public static void insert(Application app) {
            if (Ebean.find(Student.class).findRowCount() == 0) {
                @SuppressWarnings("unchecked")
                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
                        .load("initial-data.yml");

                Ebean.save(all.get("students"));
                Ebean.save(all.get("loginUsers"));
                LoginUser.assignPassword("byron1st", "abcd");
                LoginUser.assignPassword("dana.park", "abc");
                LoginUser.assignPassword("esh21c", "ab");
            }
        }
    }

    public void onStop(Application app) {
    }
}
