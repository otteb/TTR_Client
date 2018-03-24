package StatePattern;

import java.util.ArrayList;

import Models.Cards.DestinationCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import game.Cards.CardsPresenter;

public class GameSetup extends State {
    private GameGuiFacade gui = new GameGuiFacade();

    @Override
    public void returnDestCard(CardsPresenter wrapper, int cardIndex){
        ArrayList<DestinationCard> discard= new ArrayList<>();
        if(cardIndex >= 0)
        {
            discard.add(ActiveGame.getInstance().getMyPlayer().getDrawnDestCards().get(cardIndex));
        }
        gui.discardDestinationCards(discard);

        if(ActiveGame.getInstance().getActivePlayer().equals(Client.getInstance().getUserName())){
            Client.getInstance().setCurState(new MyTurn());
        }
        else
        {
            Client.getInstance().setCurState(new NotMyTurn());
        }
        TTR_Observable.getInstance().updateTurn();
    }
}
