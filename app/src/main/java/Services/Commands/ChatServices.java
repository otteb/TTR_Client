package Services.Commands;

import Interfaces.IChat;
import Models.Gameplay.Chat;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class ChatServices implements IChat {
    private static ChatServices theOne = new ChatServices();

    public static ChatServices getInstance() {
        return theOne;
    }
    private ChatServices() {}


    //TODO
    @Override
    public void addChat(Request request) {
        System.out.println("COMMAND EXECUTING - addChat");
    }
}
