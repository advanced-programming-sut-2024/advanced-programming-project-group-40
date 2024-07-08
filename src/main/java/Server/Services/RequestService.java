package Server.Services;

import models.Relation;

import java.util.ArrayList;

public class RequestService {
    private static RequestService instance;
    private final ArrayList<Relation> followRequests = new ArrayList<>();
    private final ArrayList<Relation> friends = new ArrayList<>();

    private RequestService() {
    }

    public static RequestService getInstance() {
        if (instance == null) {
            instance = new RequestService();
        }
        return instance;
    }

    public void createFriendRequest(String fromUsername, String targetUsername) {
        followRequests.add(new Relation(fromUsername,targetUsername));
    }

    public void acceptFriendRequest(String fromUsername, String targetUsername) {
        followRequests.remove(new Relation(fromUsername, targetUsername));
        friends.add(new Relation(fromUsername, targetUsername));
        friends.add(new Relation(targetUsername, fromUsername));
    }

    public ArrayList<String> getFriends(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : friends) {
            if (friend.getFirst().equals(user) && !friendsList.contains(friend.getSecond())) {
                friendsList.add(friend.getSecond());
            }
        }

        return friendsList;
    }

    public boolean areFriends(String first, String second) {
        for (Relation friend : friends)
            if (friend.getFirst().equals(first) && friend.getSecond().equals(second))
                return true;

        return false;
    }

    public ArrayList<String> getPendingFriends(String username) {
        System.err.println(username);
        ArrayList<String> result = new ArrayList<>();
        for (Relation friendRequest : followRequests) {
            if (areFriends(friendRequest.getFirst(), friendRequest.getSecond()))
                continue;

            if (friendRequest.getSecond().equals(username) && !result.contains(friendRequest.getFirst()))
                result.add(friendRequest.getFirst());
        }

        return result;
    }
}
