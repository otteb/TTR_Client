package StatePattern;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;
import game.GamePresenter;

public class MyTurn extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void claimRoute(GamePresenter wrapper) {
        //TODO: implement claimRoute()
        gui.claimRoute();
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
            gui.incTurn();
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
    public void drawDestCards(GamePresenter wrapper) {
        //TODO: implement drawDestCards
    }

}
