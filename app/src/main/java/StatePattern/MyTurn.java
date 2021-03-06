package StatePattern;

import java.util.ArrayList;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Route;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;
import game.GamePresenter;

public class MyTurn extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void claimRoute(Route routeNumber, ArrayList<TrainCard> cards) {
        gui.claimRoute(routeNumber, cards);
        Client.getInstance().setCurState(new NotMyTurn());
        ActiveGame.getInstance().getMyPlayer().setSelectedRoute(null);
     //   ActiveGame.getInstance().getMyPlayer().setIsinProcessofClaimingRoute(false);
        ActiveGame.getInstance().getMyPlayer().setSelectedRoute(null);
        gui.endTurn();
    }

    @Override
    public void takeFaceUpCard(CardsPresenter wrapper, int cardIndex) {
        //add cards to player hand
        //replace the cards on the table
        //check if card is a wild
        if(ActiveGame.getInstance().getFaceUpCards().get(cardIndex).getColor().equals("wild"))
        {
            gui.takeFaceUpCard(cardIndex);
            Client.getInstance().setCurState(new NotMyTurn());
            gui.endTurn();
        }
        else
        {
            Client.getInstance().setCurState(new Drew1Card());
            gui.takeFaceUpCard(cardIndex);
        }
    }

    @Override
    public void drawTrainCard(CardsPresenter wrapper) {
        gui.drawTrainCard();
        Client.getInstance().setCurState(new Drew1Card());
    }

    @Override
    public void drawDestCards(CardsPresenter wrapper) {
        gui.drawDestinationCards();
        Client.getInstance().setCurState(new DrewDestCards());
    }


}
