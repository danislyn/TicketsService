package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="account")
public class Account extends Model {

	@Column(name="account")
	public String account;
	
	@Column(name="password")
	public String password;
	
	@Column(name="level")
	public Integer level;
	
	@OneToMany
	@JoinColumn(name="account_id")
	public List<Role> roles;
	
}
