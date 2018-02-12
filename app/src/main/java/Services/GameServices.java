package Services;

import Interfaces.IServerGame;
import Models.Request;
import Models.Result;

public class GameServices implements IServerGame {

    //this is going to be the client facade: THIS IS FOR TESTING

    private static GameServices theGS = new GameServices();

    public static GameServices getInstance() {
        return theGS;
    }

    private GameServices() {}

    //TODO: Finish implementing IServerGame and adding functionality to methods

    //TODO: Remember to add the authToken to each request in order to skip it while adding commands

    //this one should be done
    @Override
    public Result createGame(Request request){ //(String authToken, String gameId){
//        String authToken = request.getAuthToken();
//        String gameId = request.getGameId();
//        Result result = new Result();

        System.out.println("COMMAND EXECUTED - CREATE GAME");

        return null;
    }

    @Override
    public Result joinGame(Request request) { //(String authToken, String gameId);
        System.out.println("COMMAND EXECUTED - JOIN GAME");
        return null;
    }

    @Override
    public Result startGame(Request request) { //(String authToken, String gameId);

        System.out.println("COMMAND EXECUTED - START GAME");
        return null;
    }

    @Override //polling response
    public Result updateClient(Request request) { //(String authToken);

        System.out.println("COMMAND EXECUTED - UPDATE CLIENT GAME");
        return null;
    }

    //TODO:

}
