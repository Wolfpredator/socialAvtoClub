package com.brain.crud.socialclub.model;

public class FriendRelation {
    private long friend_one;
    private long friend_two;
    private int status;

    public FriendRelation(long friend_one, long friend_two, int status) {
        this.friend_one = friend_one;
        this.friend_two = friend_two;
        this.status = status;
    }

    public long getFriend_one() {
        return friend_one;
    }

    public long getFriend_two() {
        return friend_two;
    }

    public int getStatus() {
        return status;
    }
}
