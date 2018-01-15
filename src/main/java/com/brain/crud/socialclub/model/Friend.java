package com.brain.crud.socialclub.model;

public class Friend {
    private Long idFriend;
    private Enum status;

    public enum FriendStatus {
        Subscribe,
        SubscribeOnYou,
        Friend
    }

    public Friend(Long idFriend, int idStatus) {
        this.idFriend = idFriend;
        parseStatus(idStatus);
    }

    public Long getIdFriend() {
        return idFriend;
    }

    public Enum getStatus() {
        return status;
    }

    private void parseStatus(int idForParse) {
        switch (idForParse) {
            case 0:
                status = FriendStatus.Subscribe;
                break;
            case 1:
                status = FriendStatus.SubscribeOnYou;
                break;
            case 2:
                status = FriendStatus.Friend;
                break;
            default:
                status = null;
        }
    }

}
