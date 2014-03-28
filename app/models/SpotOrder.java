package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="spot_order")
public class SpotOrder extends Model {

	@ManyToOne
	@JoinColumn(name="spot_id", referencedColumnName="id")
	public Spot spot;
	
	@Column(name="num")
	public Integer num;  //购买票数
	
	@Column(name="total_price")
	public Integer totalPrice;
	
	@Column(name="create_time")
	public Date createTime;
	
	@Column(name="create_account")
	public Integer createAccountId;
	
	@Column(name="status")
	public Integer status;  //0：正常；-1：已取消
	
}
