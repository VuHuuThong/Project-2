package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Column(name="username" , nullable=false , unique= true)
    private String username;
    @Column(name="password" , nullable=false )
    private String password;
    @Column(name="fullname" )
    private String fullname;
    @Column(name="status" , nullable=false)
    private String status;
    @Column(name="email" )
    private String email;
    
//    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRole = new ArrayList<>();
//    
    
//	public List<UserRoleEntity> getUserRole() {
//		return userRole;
//	}
//	public void setUserRole(List<UserRoleEntity> userRole) {
//		this.userRole = userRole;
//	}
    
    @ManyToMany(mappedBy="users", fetch=FetchType.LAZY)
    private List<RoleEntity> roles=new ArrayList<>();
    
    
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
