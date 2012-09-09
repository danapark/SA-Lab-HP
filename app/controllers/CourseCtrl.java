package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.course.course_list;
public class CourseCtrl extends Controller{
    
    public static Result course() {
        return ok(course_list.render("Course", Course.allCourses()));
    }
}
