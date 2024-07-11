package Server.Messages;

public enum MessageSubType {
    SEND_FOLLOW_REQUEST,
    ACCEPT_FOLLOW_REQUEST,
    REJECT_FOLLOW_REQUEST,
    SEND_GAME_REQUEST,
    ACCEPT_GAME_REQUEST,
    REJECT_GAME_REQUEST,
    GET_FRIENDS,
    GET_REJECTED_REQUESTS,
    GET_FOLLOW_REQUESTS,
    PREGAME_UPDATE,
    GAME_UPDATE,
    GET_PENDING_FOLLOW_REQUESTS,
    GET_GAME_REQUEST,
    GET_ACCEPTED_GAME_REQUESTS,
    GET_REJECTED_GAME_REQUEST,
    GET_PENDING_GAME_REQUEST,
    GAME_REQUEST,
    MAKE_PERSON_GO_TO_PRE_GAME,
    RESET_GAME_REQUEST,
    CHECK_ONLINE,
    MAIN_MENU_UPDATE,
    ADD_TO_USERS_IN_GAME,
    REMOVE_FROM_USERS_IN_GAME,
    CHECK_IN_GAME,;
}
