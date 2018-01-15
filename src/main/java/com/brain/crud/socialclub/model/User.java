package com.brain.crud.socialclub.model;

import com.brain.crud.socialclub.dao.Identified;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;


/**
 * @author Gorchakov Vladimir
 * @version 1.0
 */

@Entity
public class User implements Identified<Long> {

    @Id
    private Long id;

    private String name;

    private String surname;

    private String nickname;

    private String password;

    private Calendar dateOfBirth;

    private Set<Role> role;

    private Set<User> friends;

    private Date create_date;

    private Date last_modify;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_modify() {
        return last_modify;
    }

    public void setLastModify(Date last_modify) {
        this.last_modify = last_modify;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "User{" +
                "idFriend=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", friends=" + friends +
                '}';
    }
}
