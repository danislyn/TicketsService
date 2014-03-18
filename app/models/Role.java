package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="role")
public class Role extends Model {

	@Column(name="true_name")
	public String trueName;
	
	@Column(name="id_card")
	public String idCard;
	
	@Column(name="gender")
	public Integer gender;
	
	@Column(name="tel")
	public String tel;
	
//	@ManyToOne
//	@JoinColumn(name="account_id", referencedColumnName="id")
//	public Account account;
	
}
