package com.brain.crud.socialclub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

/**
 * @author Gorchakov Vladimir
 * @version 1.0
 */


@Entity
public class ChatRoom {

    @Id
    private Long id;


    private Set<User> users;

}
