package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.course.course_list;
import views.html.course.course_intro;
public class CourseCtrl extends Controller{
    
    public static Result course() {
        return ok(course_list.render("Course", Course.allCoursesDesc()));
    }
    public static Result courseIntro(Long id){
        return ok(course_intro.render(Course.findById(id)));
    }
}
