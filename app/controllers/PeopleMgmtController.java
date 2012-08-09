package controllers;

import models.Student;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.management.*;

public class PeopleMgmtController extends Controller {
    
    public static Result professor() {
        return ok(professor_mgmt.render("Professor Management"));
    }
    
    public static Result students() {
        return ok(students_list.render("Students Management", Student.allStudents()));
    }
    
    public static Result studentDetail(Long id) {
        return TODO;
    }
    
    public static Result alumni() {
        return TODO;
    }
}
