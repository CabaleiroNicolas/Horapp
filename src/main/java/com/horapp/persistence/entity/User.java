package com.horapp.persistence.entity;

import com.horapp.util.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "users_id_seq", allocationSize = 1)
    private long idUser;
    @Column(unique = true)
    private String username;
    private String name;
    private String lastname;
    private String password;
    private boolean enabled;
    //private boolean accountNonExpired;
    private boolean accountNonLocked;
    //private boolean credentialsNonExpired;

    @Column(unique = true)
    private String email;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Major> majorList;

    @OneToMany(mappedBy = "user")
    private List<Course> coursesList;

    @OneToMany(mappedBy = "user")
    private List<TimeTable> timeTablesList;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }


    public User(boolean accountNonLocked, String email, boolean enabled, String lastname, String name, String password, Role role, String username) {
        this.accountNonLocked = accountNonLocked;
        this.email = email;
        this.enabled = enabled;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public User(long idUser) {
        this.idUser = idUser;
    }

    public User(String username) {
        this.username = username;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    public void setAccountNonExpired(boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

//    @Override
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public List<TimeTable> getTimeTablesList() {
        return timeTablesList;
    }

    public void setTimeTablesList(List<TimeTable> timeTablesList) {
        this.timeTablesList = timeTablesList;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
