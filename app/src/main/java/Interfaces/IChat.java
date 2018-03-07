package Interfaces;

import Models.Request;
import Models.Result;

public interface IChat {
    //adjusted to void:
    void addChat(Request request);        //authtoken, gameId, and message
}
