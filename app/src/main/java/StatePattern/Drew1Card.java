package StatePattern;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class Drew1Card extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void takeFaceUpCard(CardsPresenter wrapper, int cardIndex) {
        //add cards to player hand
        //replace the cards on the table
        if(ActiveGame.getInstance().getFaceUpCards().get(cardIndex).getColor().equals("wild"))
        {
            System.out.println("You can't take that card");
        }
        else {


            TrainCard card = ActiveGame.getInstance().getFaceUpCards().remove(cardIndex);

//        ActiveGame.getInstance().getMyPlayer().getHand().add(ActiveGame.getInstance().getFaceUpCards().get(cardIndex));
            //check if card is a wild
            Client.getInstance().setCurState(new NotMyTurn());
//        ActiveGame.getInstance().getFaceUpCards().remove(cardIndex);

            gui.takeFaceUpCard(card);
            TrainCard newCard = new TrainCard("red");
            ActiveGame.getInstance().getFaceUpCards().add(cardIndex, newCard);
            ActiveGame.getInstance().incTurn();
        }
    }

    @Override
    public void drawTrainCard(CardsPresenter wrapper) {
        //add all functionality
        Client.getInstance().setCurState(new NotMyTurn());
        ActiveGame.getInstance().incTurn();
    }
}
