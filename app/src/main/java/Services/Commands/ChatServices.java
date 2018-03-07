package Services.Commands;

import Interfaces.IChat;
import Models.Gameplay.Chat;
import Models.Request;
import Models.Result;

public class ChatServices implements IChat {
    private static ChatServices theOne = new ChatServices();

    public static ChatServices getInstance() {
        return theOne;
    }

    //TODO
    @Override
    public Result addChat(Request request) {
        return null;
    }
}
