package game.Chat;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IChatPresenter;
import Models.Client;
import Models.Gameplay.Chat;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;


public class ChatPresenter implements IChatPresenter, Observer {
    //attributes:
    public Context context;
    public MainActivity mainActivity;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    ChatFragment chatFragment = new ChatFragment();
    //Constructor:
    public ChatPresenter(Context c){
        this.context = c;
        TTR_Observable.getInstance().addObserver(this);
    }

    //this grabs the text from the edit view the user presses enter in the view and sends a request to the server
    public void addChat(Context c, String message){
        Chat chat = new Chat();
        chat.setUsername(Client.getInstance().getUserName());
        chat.setMessage(message);
        gameGuiFacade.addChat(chat);
    }

    public void switchToStats(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToStats();
    }

    public void switchToGameHistory(Context c){
        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToGameHistory();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o.equals("chat"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateChat();
        }
//        else if(o.equals("turn"))
//        {
//            mainActivity = (MainActivity) context;
//            mainActivity.displayTurn();
////            String username = ActiveGame.getInstance().getActivePlayer();
////            Toast.makeText(getActivity(), "It's " + ActiveGame.getInstance().getActivePlayer() + "'s turn", Toast.LENGTH_SHORT).show();
//        }
        observable.hasChanged();
    }
}
