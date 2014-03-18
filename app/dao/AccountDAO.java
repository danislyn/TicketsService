package dao;

import models.Account;

public class AccountDAO {

	public static Account getAccountById(Long id){
		return Account.findById(id);
	}
	
	public static Account login(String account, String password){
		return Account.find("byAccountAndPassword", account, password).first();
	}
	
}
