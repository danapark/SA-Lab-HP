package controllers;

import java.util.Calendar;

import models.History;
import models.Student;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.people.*;

public class PeopleCtrl extends Controller {
    private static int current_year = Calendar.getInstance().get(Calendar.YEAR);
    
    public static Result professor() {
        return ok(professor.render("Kang, Sungwon", current_year, History.representitive(), History.allProfessional(), History.allActivities()));
    }
    
    public static Result masters() {
        return ok(students_list.render("Master students", current_year, Student.allMaster()));
    }
    
    public static Result phds() {
        return ok(students_list.render("Ph.D students", current_year, Student.allPhD()));
    }
    
    public static Result alumni(int year) {
        return ok(students_list.render("Alumni at " + year, current_year, Student.allAlumniByYear(year)));
    }
}
