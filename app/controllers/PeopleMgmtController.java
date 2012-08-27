package controllers;

import models.Student;
import play.data.Form;
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
    
    public static Result studentDetail(Long id, String title) {
        return ok(student_mgmt.render(title, form(Student.class).fill(Student.findById(id))));
    }
    
    public static Result saveStudentDetail(Long id) {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        
        if(studentForm.hasErrors())
            return badRequest(student_mgmt.render("Students Management", studentForm));
        else {
            Student student = studentForm.get();
            student.id = id;
            student.update();
            if(student.company.equals("KAIST"))
                return redirect(routes.PeopleMgmtController.students());
            else
                return redirect(routes.PeopleMgmtController.alumni());
        }
    }
    
    public static Result alumni() {
        return ok(students_list.render("Alumni Management", Student.allAlumni()));
    }
    
    public static Result getResearchAreas(Long id) {
        return ok(researchAreasList.render(form(Student.class).fill(Student.findById(id))));
    }
    
    public static Result deleteResearchAreas(Long id, String researchAreaId) {
        
        Student.deleteResearchArea(id, Long.parseLong(researchAreaId));
        return ok(researchAreasList.render(form(Student.class).fill(Student.findById(id))));
    }
}