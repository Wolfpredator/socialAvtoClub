package com.brain.crud.socialclub.dao;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    PK getId();

}