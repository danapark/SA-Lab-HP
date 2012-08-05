package controllers;

import java.util.Calendar;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.people.*;

public class PeopleController extends Controller {
    private static int current_year = Calendar.getInstance().get(Calendar.YEAR);
    
    public static Result professor() {
        return ok(professor.render("Kang, Sungwon", current_year));
    }
    
    public static Result masters() {
        return TODO;
    }
    
    public static Result phds() {
        return TODO;
    }
    
    public static Result alumni() {
        return TODO;
    }
}
