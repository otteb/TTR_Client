package StatePattern;

import Models.Client;
import game.Cards.CardsPresenter;
import game.GamePresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class State {
    public void claimRoute(GamePresenter wrapper) {
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

    public void drawDestCards(GamePresenter wrapper) {
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
    //public void returnDestCard(GamePresenter wrapper) {}
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
}
