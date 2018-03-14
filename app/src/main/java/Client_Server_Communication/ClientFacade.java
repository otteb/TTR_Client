package Client_Server_Communication;

import java.util.ArrayList;

import Models.Cards.DestinationCard;
import Models.Client;
import Models.Command;
import Models.Gameplay.Chat;
import Models.Gameplay.Game;
import Models.Request;
import Models.Result;

public class ClientFacade {
    public static void main(String[] args) {
        //this is for testing purposes:
        GamePlayFacade gamePlayFacade = new GamePlayFacade();
        ClientFacade clientFacade = new ClientFacade();
        Client.getInstance().setUserName("brian");


        //Login p1:
        Request loginRequest = new Request();
        loginRequest.setUsername("brian");
        loginRequest.setPassword("bo");
        clientFacade.login(loginRequest);
        //p1 create game:
        Request createGameRequest = new Request();
        createGameRequest.setGameId("brian-game");
        createGameRequest.setAuthToken("01b7cb2c-24c1-4c82-8f6f-c6ee8ab39d2e");
        clientFacade.createGame(createGameRequest);
        //Login p2:
        loginRequest.setUsername("jordan");
        loginRequest.setPassword("jf");
        clientFacade.login(loginRequest);
        //p2 join's p1 game:
        Game joinableGame = Client.getInstance().getGameMap().get("brian-game");
        Request joinGameRequest = new Request();
        joinGameRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        joinGameRequest.setGameId("brian-game");
        joinGameRequest.setUsername("jordan");
        clientFacade.joinGame(joinGameRequest);
        //start the game:
        Request startGameRequest = new Request();
        startGameRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        startGameRequest.setGameId("brian-game");
        clientFacade.startGame(startGameRequest);
        //test addChat:
        Chat newChat = new Chat();
        newChat.setMessage("this is a test from the ClientFacade");
        newChat.setUsername("jordan");
        Request chatRequest = new Request();
        chatRequest.setChat(newChat);
        chatRequest.setChatMessage(newChat.getMessage());
        chatRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        chatRequest.setGameId("brian-game");

        gamePlayFacade.runCMD(gamePlayFacade.addChat(chatRequest));
        //run the updateClient function in the gamePlayFacade:
        Request updateClientReq = new Request();
        updateClientReq.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        updateClientReq.setGameCMDNum(0);
        updateClientReq.setGameId("brian-game");
        gamePlayFacade.runCMD(gamePlayFacade.updateClient(updateClientReq));

        //test the discardDestinationCard function:
        Request discardDestCardRequest = new Request();
        discardDestCardRequest.setAuthToken(Client.getInstance().getAuthToken());
        discardDestCardRequest.setUsername(Client.getInstance().getUserName());
        discardDestCardRequest.setGameId(Client.getInstance().getActiveGame().getId());
        if(Client.getInstance().getActiveGame().isValidPlayer(Client.getInstance().getUserName()))
        {
            ArrayList<DestinationCard> deleteList = new ArrayList<>();
            deleteList.add(Client.getInstance().getActiveGame().getMyPlayer().getDestination_cards().get(0));
            discardDestCardRequest.setDiscardDest(deleteList);

        }
        gamePlayFacade.runCMD(gamePlayFacade.discardDestinationCard(discardDestCardRequest));


//        Request request = new Request();
//        request.setAuthToken(Client.getInstance().getAuthToken());
//        request.setGameCMDNum(Client.getInstance().getGameCMDNum());
//        //call the client facade updateClient() - use the current index;
////            ArrayList<Command> returnList = clientFacade.updateClient(request).getUpdateCommands();
//        ArrayList<Command> returnList = gamePlayFacade.updateClient(request).getUpdateCommands();

        int temp = 0;



    }

    //login function:
    public Result login(Request request){

        Command loginCommand = new Command("Interfaces.IServerUser", "login",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result result = ClientCommunicator.getInstance().sendCommand(loginCommand);
        if (result.isSuccessful())
        {
            //save the authToken in the Client Model for future use:
            Client.getInstance().setAuthToken(result.getAuthToken());
            System.out.println("Login successful!");
            System.out.println(result.getAuthToken());
//            runCMD(result);
           // Poller.LobbyPolling lobbyPoller = new Poller.LobbyPolling();
            //lobbyPoller.execute();
        }
        else
        {
            System.out.println("Error:");
            System.out.println(result.getErrorMsg());
        }
        return result;
    }


    public Result register(Request request){
        Command registerCommand = new Command("Interfaces.IServerUser", "register",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result regResult = ClientCommunicator.getInstance().sendCommand(registerCommand);
        if (regResult.isSuccessful())
        {
            Client.getInstance().setAuthToken(regResult.getAuthToken());
            System.out.println("Registration successful!");
            System.out.println(regResult.getAuthToken());
//            runCMD(regResult);
        }
        else
        {
            System.out.println("Error:");
            System.out.println(regResult.getErrorMsg());
        }
//        System.out.println();
        return regResult;
    }

    public Result createGame(Request request){
        Command gameCommand = new Command("Interfaces.ILobby", "createGame",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
//        runCMD(gameResult);
//        System.out.println();
        return gameResult;
    }

    public Result joinGame(Request request){
        Command gameCommand = new Command("Interfaces.ILobby", "joinGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
//        runCMD(gameResult);
//        System.out.println();
        return gameResult;
    }

    public Result leaveGame(Request request) {
        Command gameCommand = new Command("Interfaces.ILobby", "leaveGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
//        runCMD(gameResult);
//        System.out.println();
        return gameResult;
    }

    public Result startGame(Request request){
        Command gameCommand = new Command("Interfaces.ILobby", "startGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        return gameResult;
    }

    Result updateClient(Request request){
        Command gameCommand = new Command("Interfaces.ILobby", "updateClient",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        if (gameResult.isSuccessful()) { return gameResult; }
        else
        {
            System.out.println("Error:");
            System.out.println(gameResult.getErrorMsg());
        }
        return gameResult;
    }

    public void runCMD(Result result) {
        if (result.isSuccessful())
        {
            for(int i = 0; i < result.getUpdateCommands().size(); i++){
                int temp = Client.getInstance().getCommandNum();
                Client.getInstance().setCommandNum(++temp);
                try {
                    result.getUpdateCommands().get(i).execute();
                }catch (Exception e)
                {
                    System.out.println("ERROR");
                    e.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("Error:");
            System.out.println(result.getErrorMsg());
        }
    }
}
