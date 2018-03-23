package Interfaces;

import Models.Request;

public interface IChat {
    void addChat(Request request);        //authtoken, gameId, and message
}
