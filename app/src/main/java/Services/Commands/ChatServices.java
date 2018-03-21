package Services.Commands;

import Interfaces.IChat;
import Models.Gameplay.ActiveGame;
import Models.Request;

public class ChatServices implements IChat {
    private static ChatServices theOne = new ChatServices();

    public static ChatServices getInstance() {
        return theOne;
    }
    private ChatServices() {}

    @Override
    public void addChat(Request request) {
        System.out.println("COMMAND EXECUTING - addChat");
        ActiveGame.getInstance().addChat(request.getChat());
    }
}
