package controllers;

import models.History;
import models.ResearchArea;
import models.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.mgmt.people.*;

public class PeopleMgmt extends Controller {
    
    public static Result professor() {
        return ok(professor_details.render("Professor Management", History.allProfessional(), History.allActivities()));
    }
    
    public static Result saveHistory(Long historyId) {
        return TODO;
    }
    
    public static Result newHistory() {
        return TODO;
    }
    
    public static Result deleteHistory(Long historyId) {
        return TODO;
    }
    
    public static Result students() {
        return ok(students_list.render("Students Management", Student.allStudents()));
    }
    
    public static Result studentDetail(Long id, String title) {
        return ok(student_details.render(title, form(Student.class).fill(Student.findById(id))));
    }
    
    public static Result saveStudentDetail(Long id) {
        Form<Student> studentForm = form(Student.class).bindFromRequest();
        
        if(studentForm.hasErrors())
            return badRequest(student_details.render("Students Management", studentForm));
        else {
            Student student = studentForm.get();
            student.id = id;
            student.update();
            if(!student.isAlumni)
                return redirect(routes.PeopleMgmt.students());
            else
                return redirect(routes.PeopleMgmt.alumni());
        }
    }
    
    public static Result alumni() {
        return ok(students_list.render("Alumni Management", Student.allAlumni()));
    }
    
    public static Result researchAreas(Long id, String title) {
        return ok(researchAreas_list.render(title, form(Student.class).fill(Student.findById(id)), ResearchArea.findAllNames()));
    }
    
    public static Result deleteResearchAreas(Long id, String title, Long researchAreaId) {
        Student.deleteResearchArea(id, researchAreaId);
        return redirect(routes.PeopleMgmt.researchAreas(id, title));
    }
    
    public static Result addResearchAreas(Long id, String title) {
        String researchAreaName = form(ResearchArea.class).bindFromRequest().field("name").value();
        Student.saveResearchArea(id, researchAreaName);
        return redirect(routes.PeopleMgmt.researchAreas(id, title));
    }
}