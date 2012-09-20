package controllers;

import java.util.Map;

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
        Form<History> historyForm = form(History.class).bindFromRequest();
        boolean isValidated = validation(historyForm);
        
        if(isValidated){
            History history = historyForm.get();
            history.id = historyId;
            history.update();
        }
        
        return redirect(routes.PeopleMgmt.professor());
    }
    
    public static Result newHistory() {
        Form<History> historyForm = form(History.class).bindFromRequest();
        boolean isValidated = validation(historyForm);       
        
        if(isValidated)
            History.create(historyForm.get());
        
        return redirect(routes.PeopleMgmt.professor());
    }
    
    public static Result deleteHistory(Long historyId) {
        History history = History.findById(historyId);
        if(history.isRepresentitive)
            flash("error", "This career is a representitive career, so you cannot delete it.");
        else
            history.delete();
        return redirect(routes.PeopleMgmt.professor());
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
    
    public static boolean validation(Form<History> historyForm) {
        Map<String, String> historyValues = historyForm.data();
        
        if(historyValues.get("organization").equals("")) {
            flash("error", "Organization is empty!");
            historyForm.reject("organization");
        }
        
        if(historyValues.get("position").equals("")) {
            flash("error", "Position is empty!");
            historyForm.reject("position");
        }
        
        if(historyValues.get("begin_date").equals("")) {
            flash("error", "Begin date is empty!");
            historyForm.reject("begin_date");
        }
        
        if(historyForm.hasErrors())
            return false;
        else
            return true;
    }
}