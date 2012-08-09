package controllers;

import java.util.Calendar;
import models.Student;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.people.*;

public class PeopleController extends Controller {
    private static int current_year = Calendar.getInstance().get(Calendar.YEAR);
    
    public static Result professor() {
        return ok(professor.render("Kang, Sungwon", current_year));
    }
    
    public static Result masters() {
        return ok(students.render("Master students", current_year, Student.allMaster()));
    }
    
    public static Result phds() {
        return ok(students.render("Ph.D students", current_year, Student.allPhD()));
    }
    
    public static Result alumni(int year) {
        return ok(students.render("Alumni at " + year, current_year, Student.allAlumniByYear(year)));
    }
}
