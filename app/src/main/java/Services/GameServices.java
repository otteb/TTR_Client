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

//        //check if requesting client is an active (logged in) client
//        if(Database.getInstance().getClients().contains(authToken))
//        {
//            //check if gameId already exists
//            if(!Database.getInstance().getGames().containsKey(gameId))
//            {
//                //if it doesn't exist yet, create it
//                Game newGame = new Game(gameId);
//                //add the creator to the game
//                String username = Database.getInstance().getUsername(authToken);
//                newGame.getPlayers().add(username);
//                //add the game to the database
//                Database.getInstance().getGames().put(gameId, newGame);
////                result.setGameId(gameId); //do we want to return anything other than a boolean?
//                result.setSuccess(true);
//
//                //create commands for other active clients
//                Request clientRequest = new Request();
//                clientRequest.setAuthToken(authToken); //DO THIS FOR EACH METHOD
//                clientRequest.setUsername(username); //This is specific to createGame()
//                clientRequest.setGameId(gameId); //This is specific to createGame()
//                //add command for other clients
//                //creates a command object for each client except the requesting client
//                ClientProxy.getInstance().createGame(request);
//            }
//            else
//            {
//                result.setSuccess(false);
//                result.setErrorMsg("The requested game ID already exists.");
//            }
//        }
//        else
//        {
//            result.setSuccess(false);
//            result.setErrorMsg("Invalid authorization token.");
//        }
        return null;
    }

    @Override
    public Result joinGame(Request request) { //(String authToken, String gameId);
        System.out.println("COMMAND EXECUTED - JOIN GAME");
        return null;
    }

    @Override
    public Result startGame(Request request) { //(String authToken, String gameId);

        System.out.println("COMMAND EXECUTED - START GAME");return null;
    }

    @Override //polling response
    public Result updateClient(Request request) { //(String authToken);

        System.out.println("COMMAND EXECUTED - UPDATE CLIENT GAME");
        return null;
    }

    //TODO:

}
