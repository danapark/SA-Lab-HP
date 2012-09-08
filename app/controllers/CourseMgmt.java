package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.mgmt.course.*;
public class CourseMgmt extends Controller{
    public static Result course() {
        return ok(course_list.render("Course",Course.allCourses()));
    }
}
