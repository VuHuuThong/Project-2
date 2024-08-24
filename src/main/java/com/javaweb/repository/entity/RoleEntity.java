package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 
 @Column(name="name", nullable=false)
 private String name;
 @Column(name="code" , nullable= false)
 private String code;
 
 
// @OneToMany(mappedBy="role", fetch = FetchType.LAZY)
// private List<UserRoleEntity> userRole = new ArrayList<>();
// 
//public List<UserRoleEntity> getUserRole() {
//	return userRole;
//}
//public void setUserRole(List<UserRoleEntity> userRole) {
//	this.userRole = userRole;
//}
 
 
 @ManyToMany(fetch=FetchType.LAZY)
 @JoinTable(name = "user_role",
 joinColumns = @JoinColumn(name= "roleid" , nullable = false),
 inverseJoinColumns = @JoinColumn(name=" userid", nullable = false))
 private List<UserEntity> users= new ArrayList<>();
 
 
 

public List<UserEntity> getUsers() {
	return users;
}
public void setUsers(List<UserEntity> users) {
	this.users = users;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
 
 
}
