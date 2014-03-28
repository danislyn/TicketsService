package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="spot")
public class Spot extends Model {

	@Column(name="name")
	public String name;
	
	@ManyToOne
	@JoinColumn(name="city_id", referencedColumnName="id")
	public City city;
	
	@Column(name="info")
	public String info;
	
	@Column(name="price")
	public Integer price;
	
}
