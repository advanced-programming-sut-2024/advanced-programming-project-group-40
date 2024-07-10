package Server.Services;

import models.Relation;

import java.util.ArrayList;

public class RequestService {
    private static RequestService instance;
    private final ArrayList<Relation> followRequests = new ArrayList<>();
    private final ArrayList<Relation> rejectedFollowRequests = new ArrayList<>();
    private final ArrayList<Relation> friends = new ArrayList<>();
    private final ArrayList<Relation> gameRequest = new ArrayList<>();
    private final ArrayList<Relation> rejectedGameRequest = new ArrayList<>();
    private final ArrayList<Relation> acceptedGameRRequests = new ArrayList<>();


    private RequestService() {
    }

    public static RequestService getInstance() {
        if (instance == null) {
            instance = new RequestService();
        }
        return instance;
    }

    public void createFriendRequest(String fromUsername, String targetUsername) {
        System.out.println(fromUsername);
        System.out.println(targetUsername);
        followRequests.add(new Relation(fromUsername, targetUsername));
    }

    public void acceptFollowRequest(String originUsername, String targetUsername) {
        Relation accepted = getRequestByNames(originUsername, targetUsername, followRequests);
        followRequests.remove(accepted);
        friends.add(accepted);
    }

    public void rejectFollowRequest(String originUsername, String targetUsername) {
        // the person who rejects a request is target username in follow request
        Relation rejected = getRequestByNames(originUsername, targetUsername, followRequests);
        followRequests.remove(rejected);
        rejectedFollowRequests.add(rejected);
    }

    public ArrayList<String> getFriends(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : friends) {
            if (friend.getFirst().equals(user) ) {
                friendsList.add(friend.getSecond());
            }else if (friend.getSecond().equals(user)){
                friendsList.add(friend.getFirst());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getFollowRequests(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : followRequests) {
            if (friend.getSecond().equals(user)) {
                friendsList.add(friend.getFirst());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getPendingFollowRequests(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : followRequests) {
            if (friend.getFirst().equals(user)) {
                friendsList.add(friend.getSecond());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getRejectedFollowRequest(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : rejectedFollowRequests) {
            System.out.println();
            if (friend.getSecond().equals(user)) {
                friendsList.add(friend.getFirst());
            }
        }
        return friendsList;
    }

    public void createGameRequest(String fromUsername, String targetUsername) {
        gameRequest.add(new Relation(fromUsername, targetUsername));
    }

    public void acceptGameRequest(String originUsername, String targetUsername) {
        Relation accepted = getRequestByNames(originUsername, targetUsername,gameRequest);
        gameRequest.remove(accepted);
        acceptedGameRRequests.add(accepted);
    }

    public void rejectGameRequest(String originUsername, String targetUsername) {
        // the person who rejects a request is target username in follow request
        Relation rejected = getRequestByNames(originUsername, targetUsername,gameRequest);
        gameRequest.remove(rejected);
        rejectedGameRequest.add(rejected);
    }


    public ArrayList<String> getGameRequests(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        System.out.println("get game request");
        for (Relation friend : gameRequest) {
            if (friend.getSecond().equals(user)) {
                friendsList.add(friend.getFirst());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getRejectedGameRequest(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : rejectedGameRequest) {
            System.out.println();
            if (friend.getSecond().equals(user)) {
                friendsList.add(friend.getFirst());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getAcceptedGameRequest(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : acceptedGameRRequests) {
            if (friend.getFirst().equals(user) || friend.getSecond().equals(user)) {
                friendsList.add(friend.getSecond());
            }
        }
        return friendsList;
    }

    public ArrayList<String> getPendingGameRequests(String user) {
        ArrayList<String> friendsList = new ArrayList<>();
        for (Relation friend : gameRequest) {
            if (friend.getFirst().equals(user)) {
                friendsList.add(friend.getSecond());
            }
        }
        return friendsList;
    }


    private Relation getRequestByNames(String originUsername, String destinationUsername, ArrayList<Relation> list) {
        for (Relation friendRequest : list) {
            if (friendRequest.getFirst().equals(originUsername) && friendRequest.getSecond().equals(destinationUsername)) {
                return friendRequest;
            }
        }
        return null;
    }
}
