package com.brain.crud.socialclub.service;

import java.util.HashSet;
import java.util.Set;

public class OnlineUsers {
    private static OnlineUsers ourInstance;
    private Set<String> onlineUsersSessionId;


    public static OnlineUsers getInstance() {
        if (ourInstance == null) {
            ourInstance = new OnlineUsers();
        }
        return ourInstance;
    }

    private OnlineUsers() {
        onlineUsersSessionId = new HashSet<>();
    }

    public void setUserOnline(String sessionId) {
        onlineUsersSessionId.add(sessionId);
    }

    public boolean isOnline(String sessionId) {
        return onlineUsersSessionId.contains(sessionId);
    }


}
