package StatePattern;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;
import game.GamePresenter;

public class MyTurn extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void claimRoute(GamePresenter wrapper) {
        //TODO: finish this
        gui.claimRoute();
    }

    @Override
    public void takeFaceUpCard(CardsPresenter wrapper, int cardIndex) {
        //add cards to player hand
        //replace the cards on the table
        TrainCard card = ActiveGame.getInstance().getFaceUpCards().remove(cardIndex);
//        ActiveGame.getInstance().getMyPlayer().getHand().add(ActiveGame.getInstance().getFaceUpCards().get(cardIndex));
        //check if card is a wild
        if(card.getColor().equals("wild"))
        {
            Client.getInstance().setCurState(new NotMyTurn());
            ActiveGame.getInstance().incTurn();
        }
        else
        {
            Client.getInstance().setCurState(new Drew1Card());
        }
//        ActiveGame.getInstance().getFaceUpCards().remove(cardIndex);

        gui.takeFaceUpCard(card);
        TrainCard newCard = new TrainCard("blue");
        ActiveGame.getInstance().getFaceUpCards().add(cardIndex, newCard);

    }

    @Override
    public void drawTrainCard(CardsPresenter wrapper) {
        //add all functionality
        Client.getInstance().setCurState(new Drew1Card());
    }

    @Override
    public void drawDestCards(GamePresenter wrapper) {}

}
