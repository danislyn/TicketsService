package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import dao.AccountDAO;
import dao.RoleDAO;

import models.*;

public class Application extends Controller {

    public static void index() {
    	render("Application/index.html");
    }
    
    
	public static void login(){
		String username = params.get("account");
		String password = params.get("password");
		Account account = AccountDAO.login(username, password);
		
		if(account != null){
			session.put("account", account.account);
			session.put("account_id", account.id);
			session.put("account_level", account.level);
			
			if(account.level == 0){
				redirect("/basic/home");
			}
			else if(account.level == 2){
				redirect("/admin/home");
			}
		}
		redirect("/");
	}
    
	
	public static void home(){
		if(session.get("account") != null){
			Long accountId = Long.valueOf(session.get("account_id"));
			renderArgs.put("roles", RoleDAO.getRolesByAccountId(accountId));
			render("Application/home.html");
		}
		redirect("/");
	}
	
	
	public static void homeAdmin(){
		if(session.get("account") != null && (session.get("account_level")).equals("2")){
			render("Application/home2.html");
		}
		redirect("/");
	}

}