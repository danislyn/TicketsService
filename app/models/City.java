package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="city")
public class City extends Model {

	@Column(name="name")
	public String name;
	
	@Column(name="code")
	public String code;
	
}
