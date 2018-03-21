package StatePattern;

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
        else
        {
            Client.getInstance().setCurState(new NotMyTurn());
            gui.takeFaceUpCard(cardIndex);
            gui.incTurn();
        }
    }

    @Override
    public void drawTrainCard(CardsPresenter wrapper) {
        //add all functionality
        //TODO: Finish drawTrainCard (from deck) full-stack
        Client.getInstance().setCurState(new NotMyTurn());
//        ActiveGame.getInstance().incTurn();
    }
}
