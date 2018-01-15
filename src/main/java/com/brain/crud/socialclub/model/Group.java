package com.brain.crud.socialclub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

/**
 * @author Gorchakov Vladimir
 * @version 1.0
 */

@Entity
public class Group {

    @Id
    private Long id;

    private String name;

    private Set<User> users;

}
