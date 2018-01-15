package com.brain.crud.socialclub.service;

import com.brain.crud.socialclub.model.Friend;


public class Main {
    public static void main(String[] args) {
        Friend friend = new Friend(1L, 1);
        if (Friend.FriendStatus.Friend == friend.getStatus()) {
            System.out.println("friend");
        }
        if (Friend.FriendStatus.Subscribe == friend.getStatus()) {
            System.out.println("subsc");
        }
        if (Friend.FriendStatus.SubscribeOnYou == friend.getStatus()) {
            System.out.println("subscObyou");
        }

    }
}

