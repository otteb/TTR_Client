package Client_Server_Communication;

import java.util.Timer;

import Models.Client;
import Models.Command;
import Models.Request;
import Models.Result;

/**
 * Created by ferrell3 on 2/6/18.
 */




public class ClientFacade {
    public static void main(String[] args) {




//        //TEST LOGIN
//        Request loginRequest = new Request();
////        loginRequest.setUsername("jordan");
////        loginRequest.setPassword("jf");
//        loginRequest.setUsername("brian");
//        loginRequest.setPassword("bo");
//        login(loginRequest);
//        //END LOGIN TEST
//
//        //TEST REGISTER
//        Request registerRequest = new Request();
//        registerRequest.setUsername("chipper");
//        registerRequest.setPassword("tacos");
////        register(registerRequest);
//        //END REGISTER TEST
//
//        //TEST CREATE GAME
//        Request gameRequest = new Request();
////        gameRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
//        gameRequest.setAuthToken("01b7cb2c-24c1-4c82-8f6f-c6ee8ab39d2e");
//        gameRequest.setGameId("Brian's Game");
//        int temp = Client.getInstance().getCommandNum();
//        gameRequest.setCommandNum(temp);
//        createGame(gameRequest);
////        //END CREATE GAME TEST
//
//
//        //login a new user:
//        loginRequest.setUsername("jordan");
//        loginRequest.setPassword("jf");
//        login(loginRequest);
//
//
//        //TEST JOIN GAME
//        Request joinRequest = new Request();
//        //brian's authToken
//        joinRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
//        joinRequest.setGameId("Brian's Game");
//        temp = Client.getInstance().getCommandNum();
//        joinRequest.setCommandNum(temp);
//        joinGame(joinRequest);
//        //END JOIN GAME TEST
//
//        loginRequest.setUsername("kip");
//        loginRequest.setPassword("kh");
//        login(loginRequest);
//
//
//        //TEST START GAME
//        Request startRequest = new Request();
//        startRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
//        startRequest.setGameId("Brian's Game");
//        temp = Client.getInstance().getCommandNum();
//        startRequest.setCommandNum(temp);
//        startGame(startRequest);
//        //END START GAME TEST

//        Timer timer = new Timer();
//        timer.schedule(new Poller(), 0, 1000);


//        //TEST UPDATE CLIENT COMMAND
//        Request updateClientRequest = new Request();
//        updateClientRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
//        updateClientRequest.setCommandNum(0);
//        updateClient(updateClientRequest);
//        //END UPDATE CLIENT COMMAND



    }

    //login function:
    public static Result login(Request request){

        Command loginCommand = new Command("Interfaces.IServerUser", "login",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result result = ClientCommunicator.getInstance().sendCommand(loginCommand);

        if (result.isSuccessful())
        {
            //save the authToken in the Client Model for future use:
            Client.getInstance().setAuthToken(result.getAuthToken());
            System.out.println("Login successful!");
            System.out.println(result.getAuthToken());
        }
        else
        {
            System.out.println("Error:");
            System.out.println(result.getErrorMsg());
        }
        return result;
    }


    public static Result register(Request request){
        Command registerCommand = new Command("Interfaces.IServerUser", "register",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result regResult = ClientCommunicator.getInstance().sendCommand(registerCommand);

        if (regResult.isSuccessful())
        {
            Client.getInstance().setAuthToken(regResult.getAuthToken());
            System.out.println("Registration successful!");
            System.out.println(regResult.getAuthToken());
        }
        else
        {
            System.out.println("Error:");
            System.out.println(regResult.getErrorMsg());
        }
        System.out.println();
        return regResult;
    }

    public static Result createGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "createGame",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);

        if (gameResult.isSuccessful())
        {
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
                int temp = Client.getInstance().getCommandNum();
                Client.getInstance().setCommandNum(++temp);
//                System.out.println(gameResult.getUpdateCommands().get(i).getClass());
                try {
                    gameResult.getUpdateCommands().get(i).execute();
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
            System.out.println(gameResult.getErrorMsg());
        }
        System.out.println();
        return gameResult;
    }

    public static Result joinGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "joinGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        if (gameResult.isSuccessful())
        {
//            System.out.println(request.getGameId() + " successfully joined!");
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
                int temp = Client.getInstance().getCommandNum();
                Client.getInstance().setCommandNum(++temp);
                try {
                    gameResult.getUpdateCommands().get(i).execute();
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
            System.out.println(gameResult.getErrorMsg());
        }
        System.out.println();
        return gameResult;
    }


    public static Result startGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "startGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        if (gameResult.isSuccessful())
        {
            System.out.println(gameResult.getGameId() + " successfully started!");
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
                int temp = Client.getInstance().getCommandNum();
                ++temp;
                Client.getInstance().setCommandNum(temp);
                try {
                    gameResult.getUpdateCommands().get(i).execute();
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
            System.out.println(gameResult.getErrorMsg());
        }
        System.out.println();
        return gameResult;
    }

    public static Result updateClient(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "updateClient",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);

//        System.out.println(gameResult.isSuccessful());
        if (gameResult.isSuccessful())
        {
//            System.out.println(gameResult.getUpdateCommands().size());
            //loop through and execute commands:
            return gameResult;
        }
        else
        {
            System.out.println("Error:");
            System.out.println(gameResult.getErrorMsg());
        }
        System.out.println();
        return gameResult;
    }


}
