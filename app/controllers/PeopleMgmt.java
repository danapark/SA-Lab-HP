package controllers;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.History;
import models.ResearchArea;
import models.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

import utils.Constants;
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
    
    public static Result saveImage(Long studentId) {
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart filePart = body.getFile("imageFile");
        if(filePart != null) {
            String type = filePart.getFilename();
            type = type.substring(type.lastIndexOf("."));
            
            if(!validationType(type)) {
                flash("error", "Wrong type of the image file");
                return redirect(routes.PeopleMgmt.studentDetail(studentId, "Students Management"));
            }
            
            String fileName = Constants.DEFAULT_PEOPLE_IMAGE_PATH + "student" + studentId;
             
            File file;
            for(int i = 0; i < Constants.SUPPORT_IMAGE_TYPE.length; i++) {
                String tempFileName = fileName + "." + Constants.SUPPORT_IMAGE_TYPE[i];
                file = new File(tempFileName);
                if(file.exists()) {
                    file.delete();
                    break;
                }                    
            }
            
            filePart.getFile().renameTo(new File(fileName + type));
            
        }
        
        return redirect(routes.PeopleMgmt.studentDetail(studentId, "Students Management"));
    }
    
    public static boolean validationType(String type) {
        String stringPattern = "\\.(";
        for(int i = 0; i < Constants.SUPPORT_IMAGE_TYPE.length; i++) {
            if(i != Constants.SUPPORT_IMAGE_TYPE.length - 1)
                stringPattern += Constants.SUPPORT_IMAGE_TYPE[i] + "|";
            else
                stringPattern += Constants.SUPPORT_IMAGE_TYPE[i] + ")";
        }
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(type);
        return matcher.find();
    }
}