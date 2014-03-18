package dao;

import java.util.List;

import models.Role;


public class RoleDAO {

	public static List<Role> getRolesByAccountId(Long accountId){
		return Role.find("account_id", accountId).fetch();
	}
	
}
