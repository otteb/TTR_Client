package Services.GUI;

import java.util.ArrayList;
import java.util.Observer;

import AsyncTasks.GamePlay.ChatMessageAsyncTask;
import AsyncTasks.GamePlay.DiscardDestinationCardAsyncTask;
import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Chat;
import Models.Request;
import ObserverPattern.TTR_Observable;
import Services.Commands.GamePlayServices;

/**
 * Created by brianotte on 3/6/18.
 */

//this class takes information from the presenter, and packages it into a request object
//that is then sent to the AsyncTasks:
public class GameGuiFacade {
    //this houses all of the game-specific functions that call the Async tasks:
    //instantiate all AsyncTasks here:

    public void addChat (Chat chat)
    {
        //addChat code:
        //add properties for the addChat:
        Request addChatRequest = new Request();
        addChatRequest.setAuthToken(Client.getInstance().getAuthToken());
        addChatRequest.setGameId(ActiveGame.getInstance().getId());
        addChatRequest.setUsername(Client.getInstance().getUserName());
        addChatRequest.setChat(chat);
        addChatRequest.setChatMessage(chat.getMessage());
        addChatRequest.setGameCMDNum(ActiveGame.getInstance().getGameCMDNum());
        //execute the AsyncTask
        ChatMessageAsyncTask chatMessageAsyncTask = new ChatMessageAsyncTask();
        chatMessageAsyncTask.execute(addChatRequest);
    }

    public void discardDestinationCards(ArrayList<DestinationCard> destCards)
    {
        Request dDCRequest = new Request();
        //add properties for the dDCRequest:
        dDCRequest.setAuthToken(Client.getInstance().getAuthToken());
        dDCRequest.setUsername(Client.getInstance().getUserName());
        dDCRequest.setGameId(ActiveGame.getInstance().getId());
        dDCRequest.setDiscardDest(destCards);
        dDCRequest.setGameCMDNum(ActiveGame.getInstance().getGameCMDNum());
        //execute the AsyncTask
        DiscardDestinationCardAsyncTask discardDestinationCardAsyncTask = new DiscardDestinationCardAsyncTask();
        discardDestinationCardAsyncTask.execute(dDCRequest);
    }

    public void claimRoute(){

    }

    public void takeFaceUpCard(TrainCard card) {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(ActiveGame.getInstance().getId());
        request.setUsername(Client.getInstance().getUserName());
        ArrayList<TrainCard> cards = new ArrayList<>();
        cards.add(card);
        request.setTrainCards(cards);
        request.setGameCMDNum(ActiveGame.getInstance().getGameCMDNum());
        //execute the AsyncTask
        //TODO: Create the asyncTask and then execute it here
        //until we have server connection for this method, call the gamePlayServices
        //delete the following lines once the server is connected
//        ActiveGame.getInstance().getFaceUpCards().add
//        GamePlayServices.getInstance().takeFaceUpCard(request);
    }

    public void addObserver(Observer o)
    {
//        Client.getInstance().addObserver(o);
        TTR_Observable.getInstance().addObserver(o);
    }
}
