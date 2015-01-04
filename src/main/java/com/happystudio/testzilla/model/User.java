package com.happystudio.testzilla.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户的Model.
 * @author Xie Haozhe
 */
@Entity
@Table(name = "tz_users")
public class User implements Serializable {
	/**
     * User类的默认构造函数. 
     */
    public User() { }
   
    /**
     * User类的构造函数.
     * @param username - 用户名
     * @param password - 密码
     * @param userGroup - 用户组
     * @param realName - 用户真实姓名或公司名称
     * @param email - 电子邮件地址
     * @param country - 用户所在国家
     * @param province - 用户所在省份
     * @param city - 用户所在城市
     * @param phone - 用户的联系电话
     * @param isIndividual - 是否为个人用户
     * @param isEmailValidated - 是否验证了电子邮件地址
     * @param isInspected - 用户资料是否被审核
     * @param isApproved - 用户资料是否通过审核
     */
    public User(String username, String password, UserGroup userGroup, String realName, 
    		    String email, String country, String province, String city, String phone,
    		    boolean isIndividual, boolean isEmailValidated, boolean isInspected, boolean isApproved) {
        this.username = username;
        this.password = password;
        this.userGroup = userGroup;
        this.realName = realName;
        this.email = email;
        this.country = country;
        this.province = province;
        this.city = city;
        this.phone = phone;
        this.isIndividual = isIndividual;
        this.isEmailValidated = isEmailValidated;
        this.isInspected = isInspected;
        this.isApproved = isApproved;
    }
    
    /**
     * User类的构造函数(用于单元测试).
     * @param uid - 用户唯一标识符
     * @param username - 用户名
     * @param password - 密码
     * @param userGroup - 用户组
     * @param realName - 用户真实姓名或公司名称
     * @param email - 电子邮件地址
     * @param country - 用户所在国家
     * @param province - 用户所在省份
     * @param city - 用户所在城市
     * @param phone - 用户的联系电话
     * @param isIndividual - 是否为个人用户
     * @param isEmailValidated - 是否验证了电子邮件地址
     * @param isInspected - 用户资料是否被审核
     * @param isApproved - 用户资料是否通过审核
     */
    public User(int uid, String username, String password, UserGroup userGroup, String realName, 
		    String email, String country, String province, String city, String phone,
		    boolean isIndividual, boolean isEmailValidated, boolean isInspected, boolean isApproved) {
    	this(username, password, userGroup, realName, email, country, province, city, 
    			phone, isIndividual, isEmailValidated, isInspected, isApproved);
    	this.uid = uid;
    }
    
    /**
     * 获取用户唯一标识符.
     * @return 用户唯一标识符
     */
    public int getUid() {
        return uid;
    }
    
    /**
     * 设置用户唯一标识符.
     * @param uid - 用户唯一标识符
     */
    public void setUid(int uid) {
        this.uid = uid;
    }
    
    /**
     * 获取用户名.
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名.
     * @param Username - 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 获取密码(已采用MD5加密).
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码.
     * @param password - 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户组.
     * @return 用户组对象
     */
    public UserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * 设置用户组.
     * @param userGroup - 用户组对象
     */
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * 获取用户的真实姓名或公司名称.
	 * @return 用户的真实姓名或公司名称
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 设置用户的真实姓名或公司名称.
	 * @param realName - 用户的真实姓名或公司名称
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
     * 获取电子邮件地址.
     * @return 电子邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件地址
     * @param email - 电子邮件地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

	/**
	 * 获取用户所在国家.
	 * @return 用户所在国家
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 设置用户所在国家.
	 * @param country - 用户所在国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取用户所在省份.
	 * @return 用户所在省份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置用户所在省份.
	 * @param province - 用户所在省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取用户所在城市.
	 * @return 用户所在城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置用户所在城市.
	 * @param city - 用户所在城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取用户的联系电话.
	 * @return 用户的联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置用户的联系电话.
	 * @param phone - 用户的联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取是否为个人用户.
	 * @return 是否为个人用户
	 */
	public boolean isIndividual() {
		return isIndividual;
	}

	/**
	 * 设置是否为个人用户.
	 * @param isIndividual - 是否为个人用户
	 */
	public void setIndividual(boolean isIndividual) {
		this.isIndividual = isIndividual;
	}

	/**
	 * 获取是否验证了电子邮件地址.
	 * @return 是否验证了电子邮件地址
	 */
	public boolean isEmailValidated() {
		return isEmailValidated;
	}

	/**
	 * 设置是否验证了电子邮件地址.
	 * @param isEmailValidated - 是否验证了电子邮件地址
	 */
	public void setEmailValidated(boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}

	/**
	 * 获取用户资料是否通过审核.
	 * @return 用户资料是否通过审核
	 */
	public boolean isInspected() {
		return isInspected;
	}

	/**
	 * 设置用户资料是否通过审核.
	 * @param isInspected - 用户资料是否通过审核
	 */
	public void setInspected(boolean isInspected) {
		this.isInspected = isInspected;
	}

	/**
	 * 获取用户资料是否通过审核.
	 * @return 用户资料是否通过审核
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * 设置用户资料是否通过审核.
	 * @param isApproved - 用户资料是否通过审核
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User: [Uid=%s, Username=%s, Password=%s, UserGroup={%s}, RealName=%s, Email=%s, ]", 
                new Object[] { uid, username, password, userGroup, realName, email });
	}

	/**
     * 用户的唯一标识符.
     */
    @Id
    @GeneratedValue
    @Column(name = "uid")
    private int uid;
    
    /**
     * 用户名.
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码(已采用MD5加密).
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户组对象.
     */
    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    /**
     * 用户的真实姓名或公司名称.
     */
    @Column(name = "real_name")
    private String realName;
    
    /**
     * 电子邮件地址.
     */
    @Column(name = "email")
    private String email;
    
    /**
     * 用户所在国家.
     */
    @Column(name = "country")
    private String country;
    
    /**
     * 用户所在省份.
     */
    @Column(name = "province")
    private String province;
    
    /**
     * 用户所在城市.
     */
    @Column(name = "city")
    private String city;
    
    /**
     * 用户的联系电话.
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * 是否为个人用户.
     */
    @Column(name = "is_individual")
    private boolean isIndividual;
    
    /**
     * 是否验证了电子邮件地址.
     */
    @Column(name = "is_email_validated")
    private boolean isEmailValidated;
    
    /**
     * 用户资料是否被审核.
     */
    @Column(name = "is_inspected")
    private boolean isInspected;
    
    /**
     * 用户资料是否通过审核.
     */
    @Column(name = "is_approved")
    private boolean isApproved;
    
    /**
	 * 唯一的序列化标识符.
	 */
	private static final long serialVersionUID = 1540715610889693930L;
}