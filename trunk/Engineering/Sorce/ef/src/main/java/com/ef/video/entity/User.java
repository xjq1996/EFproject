package com.ef.video.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ef_user")
public class User extends BaseEntity {
	private String username;
	private String password;
    private Integer score;
	private String sno;//学号
	private Profession profession;//专业
	private String gender;
	private Integer status;//状态是否激活
	private Role role;//0为管理员
	private String email;//邮箱qq或者163
	@Column(name="username", unique=true,nullable=false,length=16)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password",nullable=false,length=16)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="sno", unique=true,nullable=false,length=9)
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	@OneToOne
	@JoinColumn(name="profession_Id",referencedColumnName="id",nullable=false)
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	@Column(name="gender",length=2)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="status",precision=1,columnDefinition="INT default 0")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="email" ,length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="score",columnDefinition="INT default 0")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
