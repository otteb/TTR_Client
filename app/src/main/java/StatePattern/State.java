package StatePattern;

import java.util.ArrayList;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.Route;
import game.Cards.CardsPresenter;
import game.GamePresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class State {
    public void claimRoute(Route claimRoute, ArrayList<TrainCard> cards) {
        //toast it's not your turn
    }
    //public void takeFaceUpCard(GamePresenter wrapper) {}
    //public void drawTrainCard(GamePresenter wrapper) {}
//    public void takeFaceUpCard(CardsPresenter wrapper) {
    public void takeFaceUpCard(CardsPresenter wrapper, int cardIndex) {
        //toast it's not your turn
        System.out.println("It's not your turn!");

        if(Client.getInstance().getCurState() instanceof MyTurn) {
            System.out.println("Current state: MyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof NotMyTurn) {
            System.out.println("Current state: NotMyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup) {
            System.out.println("Current state: GameSetup");
        }
        else if(Client.getInstance().getCurState() instanceof Drew1Card) {
            System.out.println("Current state: Drew1Card");
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards) {
            System.out.println("Current state: DrewDestCards");
        }
        else
        {
            System.out.println("Current state: ?");
        }

    }
    public void drawTrainCard(CardsPresenter wrapper) {
        //toast it's not your turn
        System.out.println("It's not your turn!");

        if(Client.getInstance().getCurState() instanceof MyTurn) {
            System.out.println("Current state: MyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof NotMyTurn) {
            System.out.println("Current state: NotMyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup) {
            System.out.println("Current state: GameSetup");
        }
        else if(Client.getInstance().getCurState() instanceof Drew1Card) {
            System.out.println("Current state: Drew1Card");
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards) {
            System.out.println("Current state: DrewDestCards");
        }
        else
        {
            System.out.println("Current state: ?");
        }
    }
    public void drawDestCards(CardsPresenter wrapper) {
        //toast it's not your turn
        System.out.println("You cannot draw any more cards this turn.");

        if(Client.getInstance().getCurState() instanceof MyTurn) {
            System.out.println("Current state: MyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof NotMyTurn) {
            System.out.println("Current state: NotMyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup) {
            System.out.println("Current state: GameSetup");
        }
        else if(Client.getInstance().getCurState() instanceof Drew1Card) {
            System.out.println("Current state: Drew1Card");
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards) {
            System.out.println("Current state: DrewDestCards");
        }
        else
        {
            System.out.println("Current state: ?");
        }
    }
    public void returnDestCard(CardsPresenter wrapper, int cardIndex) {
        //toast it's not your turn
        System.out.println("It's not your turn!");

        if(Client.getInstance().getCurState() instanceof MyTurn) {
            System.out.println("Current state: MyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof NotMyTurn) {
            System.out.println("Current state: NotMyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup) {
            System.out.println("Current state: GameSetup");
        }
        else if(Client.getInstance().getCurState() instanceof Drew1Card) {
            System.out.println("Current state: Drew1Card");
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards) {
            System.out.println("Current state: DrewDestCards");
        }
        else
        {
            System.out.println("Current state: ?");
        }
    }
    public void return2DestCards(CardsPresenter wrapper, int cardIndex, int cardIndex2) {
        //toast it's not your turn
        System.out.println("It's not your turn!");

        if(Client.getInstance().getCurState() instanceof MyTurn) {
            System.out.println("Current state: MyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof NotMyTurn) {
            System.out.println("Current state: NotMyTurn");
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup) {
            System.out.println("Current state: GameSetup");
        }
        else if(Client.getInstance().getCurState() instanceof Drew1Card) {
            System.out.println("Current state: Drew1Card");
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards) {
            System.out.println("Current state: DrewDestCards");
        }
        else
        {
            System.out.println("Current state: ?");
        }
    }
}
