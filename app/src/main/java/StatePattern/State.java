package StatePattern;

import game.Cards.CardsPresenter;
import game.GamePresenter;

/**
 * Created by ferrell3 on 3/13/18.
 */

public class State {
    public void claimRoute(GamePresenter wrapper) {}
    //public void takeFaceUpCard(GamePresenter wrapper) {}
    //public void drawTrainCard(GamePresenter wrapper) {}
    public void takeFaceUpCard(CardsPresenter wrapper) {}
    public void drawTrainCard(CardsPresenter wrapper) {}

    public void drawDestCards(GamePresenter wrapper) {}
    //public void returnDestCard(GamePresenter wrapper) {}
    public void returnDestCard(CardsPresenter wrapper) {}
}
