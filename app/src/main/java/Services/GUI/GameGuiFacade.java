package Services.GUI;

import java.util.ArrayList;

import AsyncTasks.GamePlay.ChatMessageAsyncTask;
import AsyncTasks.GamePlay.DiscardDestinationCardAsyncTask;
import AsyncTasks.LoginAndRegister.LoginAsyncTask;
import Models.Cards.DestinationCard;
import Models.Client;
import Models.Gameplay.Chat;
import Models.Request;

/**
 * Created by brianotte on 3/6/18.
 */

//this class takes information from the presenter, and packages it into a request object
//that is then sent to the AsyncTasks:
public class GameGuiFacade {
    //this houses all of the game-specific functions that call the Async tasks:
    //instantiate all AsyncTasks here:
    ChatMessageAsyncTask chatMessageAsyncTask = new ChatMessageAsyncTask();
    DiscardDestinationCardAsyncTask discardDestinationCardAsyncTask = new DiscardDestinationCardAsyncTask();

    //FINISHED:
    public void addChat (Chat chat)
    {
        //addChat code:
        //add properties for the addChat:
        Request addChatRequest = new Request();
        addChatRequest.setAuthToken(Client.getInstance().getAuthToken());
        addChatRequest.setGameId(Client.getInstance().getActiveGame().getId());
        addChatRequest.setUsername(Client.getInstance().getUserName());
        addChatRequest.setChat(chat);
        addChatRequest.setChatMessage(chat.displayChat());
        addChatRequest.setGameCMDNum(Client.getInstance().getActiveGameCMDNum());
        //execute the AsyncTask
        chatMessageAsyncTask.execute(addChatRequest);
    }

    //FINISHED:
    public void discardDestinationCard(ArrayList<DestinationCard> destCards)
    {
        Request dDCRequest = new Request();
        //add properties for the dDCRequest:
        dDCRequest.setAuthToken(Client.getInstance().getAuthToken());
        dDCRequest.setUsername(Client.getInstance().getUserName());
        dDCRequest.setGameId(Client.getInstance().getActiveGame().getId());
        dDCRequest.setDiscardDest(destCards);
        dDCRequest.setGameCMDNum(Client.getInstance().getActiveGameCMDNum());
        //execute the AsyncTask
        discardDestinationCardAsyncTask.execute(dDCRequest);
    }
}
