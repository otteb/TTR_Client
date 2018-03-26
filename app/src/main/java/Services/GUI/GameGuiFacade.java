package Services.GUI;

import java.util.ArrayList;
import java.util.Observer;

import AsyncTasks.GamePlay.ChatTask;
import AsyncTasks.GamePlay.ClaimRouteTask;
import AsyncTasks.GamePlay.DiscardDestinationsTask;
import AsyncTasks.GamePlay.DrawDestinationsTask;
import AsyncTasks.GamePlay.DrawTrainTask;
import AsyncTasks.GamePlay.FaceUpTask;
import AsyncTasks.GamePlay.EndTurnTask;
import Models.Cards.DestinationCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Chat;
import Models.Gameplay.Route;
import Models.Request;
import ObserverPattern.TTR_Observable;

//this class takes information from the presenter, and packages it into a request object
//that is then sent to the AsyncTasks:
public class GameGuiFacade {
    //this houses all of the game-specific functions that call the Async tasks:

    public void addChat (Chat chat)
    {
        //add properties for the addChat:
        Request chatRequest = setUpRequest();
        chatRequest.setChat(chat);
        chatRequest.setChatMessage(chat.getMessage());
        //execute the AsyncTask
        new ChatTask().execute(chatRequest);
    }

    public void discardDestinationCards(ArrayList<DestinationCard> destCards)
    {
        Request dDCRequest = setUpRequest();
        //add other properties for the dDCRequest:
        dDCRequest.setDiscardDest(destCards);
        //execute the AsyncTask
        new DiscardDestinationsTask().execute(dDCRequest);
    }

    public void drawDestinationCards()
    {
        new DrawDestinationsTask().execute(setUpRequest());
    }

    public void takeFaceUpCard(int cardIndex) {
        Request request = setUpRequest();
        request.setCardIndex(cardIndex);
        //execute the AsyncTask
        new FaceUpTask().execute(request);
    }

    public void drawTrainCard() {
        new DrawTrainTask().execute(setUpRequest());
    }

    public void endTurn(){
        new EndTurnTask().execute(setUpRequest());
    }

    public void claimRoute(Route routeNumber){
        Request request = setUpRequest();
        request.setRoute(routeNumber);

        new ClaimRouteTask().execute(request);
    }


    private Request setUpRequest() {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(ActiveGame.getInstance().getId());
        request.setGameCMDNum(ActiveGame.getInstance().getGameCMDNum());
        return request;
    }
}
