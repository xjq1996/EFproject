package com.ef.video.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ef_profession")
public class Profession extends BaseEntity{
public String name;
public Academy academy;
@Column(name="name",length=40)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="academy_Id")
public Academy getAcademy() {
	return academy;
}
public void setAcademy(Academy academy) {
	this.academy = academy;
}

}
