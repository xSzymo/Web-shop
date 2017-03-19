package com.shop.data.tables;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="role_id")
	private Long roleid;
	
	@Column(name="role")
	private String role;	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private Collection<Users> user = new LinkedHashSet<Users>();

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserRoleId() {
		return roleid;
	}

	public void setUserroleid(Long roleid) {
		this.roleid = roleid;
	}

	public Collection<Users> getUser() {
		return user;
	}

	public void setUser(Collection<Users> user) {
		this.user = user;
	}
}
