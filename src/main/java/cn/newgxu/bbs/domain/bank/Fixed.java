package cn.newgxu.bbs.domain.bank;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.newgxu.bbs.common.util.Util;
import cn.newgxu.bbs.domain.user.User;
import cn.newgxu.jpamodel.JPAEntity;
import cn.newgxu.jpamodel.ObjectNotFoundException;

/**
 * BankFixed generated by MyEclipse - Hibernate Tools
 */

@Entity
@Table(name = "bank_fixed")
public class Fixed extends JPAEntity {

	private static final long serialVersionUID = -6429122751239641811L;
	// Fields    

	@Id
	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="id_seq")    
//	@SequenceGenerator(name="id_seq", sequenceName="seq_bank_operate_log")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id = -1;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private User user;
    
    private int money;
    private int days;
    
    @Column(name = "fixed_rate", precision = 4)
    private Float fixedRate;
    
    private boolean drawing;

    @Column(name = "begin_time")
    private Date beginTime;
    
    @Column(name = "draw_time")
    private Date drawTime;

    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public int getMoney() {
        return this.money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }

    public Date getBeginTime() {
        return this.beginTime;
    }
    
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public int getDays() {
        return this.days;
    }
    
    public void setDays(int days) {
        this.days = days;
    }

    public Float getFixedRate() {
        return this.fixedRate;
    }
    
    public void setFixedRate(Float fixedRate) {
        this.fixedRate = fixedRate;
    }

    public boolean getDrawing() {
        return this.drawing;
    }
    
    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public Date getDrawTime() {
        return this.drawTime;
    }
    
    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    // Constructors

	public static Fixed get(int id) throws ObjectNotFoundException {
		return (Fixed) getById(Fixed.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<Fixed> getByUser(User user) {
		return (List<Fixed>) Q("from Fixed f where f.user = ?1 and drawing = ?2", 
				P(1, user), P(2, false)).getResultList();
	}

    public Date getEndTime() {
        return  Util.getDateAfterDay(this.beginTime, this.days);
    }

    public int getAccrual() {
    	if (Util.days(this.beginTime, new Date()) < this.days) {
    		return 0;
    	}
        return (int)(this.money * this.days * this.fixedRate);
    }

 	// ------------------------------------------------
 	@SuppressWarnings("serial")
 	@Override
 	public String toString() {
 		return "Fixed" + new LinkedHashMap<String, Object>() {
 			{
 				put("id", id);
 				put("user", user);
 				put("money", money);
 				put("days", days);
 				put("fixedRate", fixedRate);
 				put("drawing", drawing);
 				put("beginTime", beginTime);
 				put("drawTime", drawTime);
 			}
 		}.toString();
	}

}