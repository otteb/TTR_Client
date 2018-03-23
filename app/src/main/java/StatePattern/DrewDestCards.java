package StatePattern;

import java.util.ArrayList;

import Models.Cards.DestinationCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class DrewDestCards extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void returnDestCard(CardsPresenter wrapper, int cardIndex) {
        ArrayList<DestinationCard> discard= new ArrayList<>();
        if(cardIndex >= 0)
        {
            discard.add(ActiveGame.getInstance().getMyPlayer().getDrawnDestCards().get(cardIndex));
        }
        gui.discardDestinationCards(discard);
        gui.endTurn();
        Client.getInstance().setCurState(new NotMyTurn());
        TTR_Observable.getInstance().updateCards("destinations");
    }

    @Override
    public void return2DestCards(CardsPresenter wrapper, int cardIndex, int cardIndex2) {
        ArrayList<DestinationCard> discard= new ArrayList<>();
        discard.add(ActiveGame.getInstance().getMyPlayer().getDrawnDestCards().get(cardIndex));
        discard.add(ActiveGame.getInstance().getMyPlayer().getDrawnDestCards().get(cardIndex2));

        gui.discardDestinationCards(discard);
        gui.endTurn();
        Client.getInstance().setCurState(new NotMyTurn());
        TTR_Observable.getInstance().updateCards("destinations");
    }

}
