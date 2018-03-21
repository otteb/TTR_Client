package StatePattern;

import java.util.ArrayList;

import Models.Cards.DestinationCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class GameSetup extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void returnDestCard(CardsPresenter wrapper, int cardIndex){
        ArrayList<DestinationCard> discard= new ArrayList<>();
        discard.add(ActiveGame.getInstance().getMyPlayer().getDestination_cards().get(cardIndex));
//            ActiveGame.getInstance().getMyPlayer().getDestination_cards().remove(discard.get(0));
        gui.discardDestinationCards(discard);
        //don't change state here, change state when the command is executed on the client
        if(ActiveGame.getInstance().getActivePlayer().equals(Client.getInstance().getUserName())){
            Client.getInstance().setCurState(new MyTurn());
        }
        else
        {
            Client.getInstance().setCurState(new NotMyTurn());
        }
    }
}
