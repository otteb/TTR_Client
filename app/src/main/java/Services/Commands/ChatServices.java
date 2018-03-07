package Services.Commands;

import Interfaces.IChat;
import Models.Gameplay.Chat;
import Models.Request;
import Models.Result;

public class ChatServices implements IChat {
    private static ChatServices theOne = new ChatServices();
<<<<<<< HEAD

    public static ChatServices getInstance() {
        return theOne;
    }
=======
    public static ChatServices getInstance() {
        return theOne;
    }
    private ChatServices() {}
>>>>>>> brian

    //TODO
    @Override
    public void addChat(Request request) {
        System.out.println("COMMAND EXECUTING - addChat");
    }
}
