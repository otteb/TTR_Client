package Services.Commands;

import Interfaces.IGamePlay;
import Models.Request;
import Models.Result;

public class GamePlayServices implements IGamePlay {
    private static GamePlayServices theOne = new GamePlayServices();

    public static GamePlayServices getInstance() {
        return theOne;
    }

    //TODO
    @Override
    public void setupGame(Request request) {

    }

    //TODO
    @Override
    public Result addGameHistory(Request request) {
        return null;
    }

    //TODO
    @Override
    public Result discardDestCards(Request request) {
        return null;
    }
    //all executed commands FROM
}
