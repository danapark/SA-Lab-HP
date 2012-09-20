package controllers;

import models.Course;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.mgmt.course.course_details;
import views.html.mgmt.course.course_list;
public class CourseMgmt extends Controller{
    public static Result course() {
        return ok(course_list.render("Course",Course.allCoursesDesc()));
    }
    public static Result courseDetail(Long id, String title) {
        return ok(course_details.render(title, form(Course.class).fill(Course.findById(id))));
    }
    public static Result saveCourseDetail(Long id) {
        Form<Course> courseForm = form(Course.class).bindFromRequest();
        
        if(courseForm.hasErrors())
            return badRequest(course_details.render("Courses Management", courseForm));
        else {
            Course course = courseForm.get();
            course.id = id;
            course.update();
            return redirect(routes.CourseMgmt.course());
        }
    }
}
