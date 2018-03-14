package StatePattern;

import game.Cards.CardsPresenter;
import game.GamePresenter;

public class MyTurn extends State {
    @Override
    public void claimRoute(GamePresenter wrapper) {}

    @Override
    public void takeFaceUpCard(CardsPresenter wrapper) {}

    @Override
    public void drawTrainCard(CardsPresenter wrapper) {}

    @Override
    public void drawDestCards(GamePresenter wrapper) {}

}
