package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.mgmt.people.professor_details;

public class CourseMgmt extends Controller{
    public static Result course() {
        return ok(professor_details.render("Professor Management"));
    }
}
