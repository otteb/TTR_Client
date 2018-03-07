package Client_Server_Communication;

import Models.Client;
import Models.Command;
import Models.Request;
import Models.Result;

public class ClientFacade {
    public static void main(String[] args) {
        //this is for testing purposes:
        GamePlayFacade gamePlayFacade = new GamePlayFacade();
        ClientFacade clientFacade = new ClientFacade();



        //TEST LOGIN
        Request loginRequest = new Request();
//        loginRequest.setUsername("jordan");
//        loginRequest.setPassword("jf");
        loginRequest.setUsername("brian");
        loginRequest.setPassword("bo");
        clientFacade.login(loginRequest);
        //END LOGIN TEST
//
//
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
