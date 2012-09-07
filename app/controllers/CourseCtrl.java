package controllers;

import java.util.Calendar;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.course.course_list;
public class CourseCtrl extends Controller{
    private static int current_year = Calendar.getInstance().get(Calendar.YEAR);
    
    public static Result course() {
        return ok(course_list.render("Course"));
    }
}
