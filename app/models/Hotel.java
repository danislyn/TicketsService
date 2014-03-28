package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="hotel")
public class Hotel extends Model {

	@Column(name="name")
	public String name;
	
	@ManyToOne
	@JoinColumn(name="city_id", referencedColumnName="id")
	public City city;
	
	@Column(name="info")
	public String info;
	
	@OneToMany
	@JoinColumn(name="hotel_id")
	public List<HotelType> hotelTypes;
	
}
