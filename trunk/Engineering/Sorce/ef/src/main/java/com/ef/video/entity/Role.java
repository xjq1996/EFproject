package com.ef.video.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ef_role")
public class Role extends BaseEntity{
	private String admin_name;
	private List<User>users;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="role")
public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

@Column(name="name",length=8)
	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	
}

