package com.shop.data.tables;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long id;

    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Collection<User> user = new LinkedHashSet<User>();

    private UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long roleid) {
        this.id = roleid;
    }

    public Collection<User> getUsers() {
        return user;
    }

    public void setUsers(Collection<User> user) {
        this.user = user;
    }
}
