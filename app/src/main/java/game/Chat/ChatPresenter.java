package game.Chat;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IChatPresenter;
import Models.Request;
import Services.GUI.GameGuiFacade;

/**
 * Created by brianotte on 3/7/18.
 */

public class ChatPresenter implements IChatPresenter, Observer {
    //attributes:
    public Context context;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    ChatFragment chatFragment = new ChatFragment();
    //Constructor:
    public ChatPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //TODO - this needs to display the chats:
    public void displayChat(){

    }

    //TODO - this needs to grab the text from the edit view
    //and display it once the user presses enter in the view:
    public void addChat(){

    }

    //TODO - switch view back to the stats fragment
    public void switchToStats(Context c){

    }

    //TODO - I honestly have no idea
    @Override
    public void update(Observable observable, Object o) {
        //consider pulling in code from the login/register fragment:
    }
}
