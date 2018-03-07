package Services.GUI;

import AsyncTasks.GamePlay.ChatMessageAsyncTask;
import AsyncTasks.GamePlay.DiscardDestinationCardAsyncTask;
import AsyncTasks.LoginAndRegister.LoginAsyncTask;
import Models.Request;

/**
 * Created by brianotte on 3/6/18.
 */

//The Presenters will call this class when they want information:
public class GameGuiFacade {
    //this houses all of the game-specific functions that call the Async tasks:
    //instantiate all AsyncTasks here:
    ChatMessageAsyncTask chatMessageAsyncTask = new ChatMessageAsyncTask();
    DiscardDestinationCardAsyncTask discardDestinationCardAsyncTask = new DiscardDestinationCardAsyncTask();

    //NOT FINISHED:
    public void addChat (String username, String password)
    {
//        Request loginRequest = new Request();
//        loginRequest.setUsername(username);
//        loginRequest.setPassword(password);
//        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
//        loginAsyncTask.execute(loginRequest);
        //addChat code:
        Request addChatRequest = new Request();
        //add properties for the addChat:
        //execute the AsyncTask
        chatMessageAsyncTask.execute(addChatRequest);
    }

    //NOT FINISHED:
    public void discardDestinationCard(String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.execute(loginRequest);
        //addChat code:
        Request dDCRequest = new Request();
        //add properties for the addChat:
        //execute the AsyncTask
        discardDestinationCardAsyncTask.execute(dDCRequest);
    }

}
