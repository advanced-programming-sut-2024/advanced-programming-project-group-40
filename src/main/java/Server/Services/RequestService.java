package Server.Services;

import models.Relation;

import java.util.ArrayList;

public class RequestService {
    private static RequestService instance;
    private final ArrayList<Relation> followRequests = new ArrayList<>();
    private final ArrayList<Relation> rejectedFollowRequests = new ArrayList<>();
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
        followRequests.add(new Relation(fromUsername, targetUsername));
    }

    public void acceptFollowRequest(String originUsername, String targetUsername) {
        followRequests.remove(new Relation(originUsername, targetUsername));
        friends.add(new Relation(originUsername, targetUsername));
    }

    public void rejectFollowRequest(String originUsername, String targetUsername) {
        // the person who rejects a request is target username in follow request
        Relation rejected = getFollowRequestByNames(targetUsername, originUsername);
        followRequests.remove(rejected);
        rejectedFollowRequests.add(rejected);
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

    private Relation getFollowRequestByNames(String originUsername, String destinationUsername) {
        for (Relation friendRequest : followRequests) {
            if (friendRequest.getFirst().equals(originUsername) && friendRequest.getSecond().equals(destinationUsername))
                return friendRequest;
        }
        return null;
    }
}
