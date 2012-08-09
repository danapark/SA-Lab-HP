package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.management.*;

public class PeopleMgmtController extends Controller {
    
    public static Result index() {
        return ok(people_mgmt.render("People Management"));
    }
}
