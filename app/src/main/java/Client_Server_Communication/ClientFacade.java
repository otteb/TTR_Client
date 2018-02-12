package Client_Server_Communication;

import Models.Command;
import Models.Request;
import Models.Result;

/**
 * Created by ferrell3 on 2/6/18.
 */


//this will become the clientFacade:
    //(1) - use this to confirm that everything on the server works
    //(2) - adapt to then communicate with the poller and the server proxy:


public class ClientFacade {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);

        //TEST LOGIN
        Request loginRequest = new Request();
//        loginRequest.setUsername("jordan");
//        loginRequest.setPassword("jf");
        loginRequest.setUsername("brian");
        loginRequest.setPassword("bo");
        login(loginRequest);
        //END LOGIN TEST

        //TEST REGISTER
        Request registerRequest = new Request();
        registerRequest.setUsername("chipper");
        registerRequest.setPassword("tacos");
//        register(registerRequest);
        //END REGISTER TEST

        //TEST CREATE GAME
        Request gameRequest = new Request();
//        gameRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        gameRequest.setAuthToken("01b7cb2c-24c1-4c82-8f6f-c6ee8ab39d2e");
        gameRequest.setGameId("Brian's Game");
        gameRequest.setCommandNum(0);
        createGame(gameRequest);
//        //END CREATE GAME TEST


        //login a new user:
        loginRequest.setUsername("jordan");
        loginRequest.setPassword("jf");
        login(loginRequest);


        //TEST JOIN GAME
        Request joinRequest = new Request();
        //brian's authToken
        joinRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        joinRequest.setGameId("Brian's Game");
        joinRequest.setCommandNum(0);
        joinGame(joinRequest);
        //END JOIN GAME TEST

        loginRequest.setUsername("kip");
        loginRequest.setPassword("kh");
        login(loginRequest);


        //TEST START GAME
        Request startRequest = new Request();
        startRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        startRequest.setGameId("Brian's Game");
        startRequest.setCommandNum(0);
        startGame(startRequest);
        //END START GAME TEST

        //TEST UPDATE CLIENT COMMAND
        Request updateClientRequest = new Request();
        updateClientRequest.setAuthToken("a1fb6d30-51e7-4669-b944-120989aefb06");
        updateClientRequest.setCommandNum(0);
        updateClient(updateClientRequest);
        //END UPDATE CLIENT COMMAND




//        System.out.println();

//        while (true)
//        {
//            System.out.println("Welcome to Phase 0.5! Prepare to have your strings processed! (Enter Q to quit)");
//            System.out.print("Enter a method to process: ");
//            String type = in.nextLine().toLowerCase().trim();
//            if(type.equals("q")) { break; }
////            else if(!type.equals("parseinteger")
////                    && !type.equals("trim")
////                    && !type.equals("tolowercase"))
////            {
////                System.out.println("Sorry, that's not a valid suffix. Valid options include: toLowerCase, trim, and parseInteger.");
////            }
//            else
//            {
//                if(type.equals("parseinteger") || type.equals("parse")){
//                    type = "parseInteger";
//                }
//                else if(type.equals("tolowercase") || type.equals("lower"))
//                {
//                    type = "toLowerCase";
//                }
//                System.out.print("Enter the string: ");
//
//                String input = in.nextLine();
//                if(input.toLowerCase().equals("q")) { break; }
//
//                Command command = new Command("Interfaces.IStringProcessor", type,
//                        new String[]{ "Models.Request" }, new Request[]{new Request(input)});
//
////                GenericCommand move = new GenericCommand("VideoGame", "move",
////                        new Class<?>[]{ int.class, Request.class },
////                        new Object[] { 3 , new Location(75, 12) });
//
//                Result result = ClientCommunicator.getInstance().sendCommand(command);
//
//                if (result.isSuccessful())
//                {
//                    System.out.println(result.getData());
//                }
//                else
//                {
//                    System.out.println(result.getErrorMsg());
//                }
//                System.out.println();
//            }
//        }
    }

    //login function:
    public static void login(Request request){

        Command loginCommand = new Command("Interfaces.IServerUser", "login",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result result = ClientCommunicator.getInstance().sendCommand(loginCommand);

        if (result.isSuccessful())
        {
            System.out.println("Login successful!");
            System.out.println(result.getAuthToken());
        }
        else
        {
            System.out.println("Error:");
            System.out.println(result.getErrorMsg());
        }
    }


    public static void register(Request request){
        Command registerCommand = new Command("Interfaces.IServerUser", "register",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result regResult = ClientCommunicator.getInstance().sendCommand(registerCommand);

        if (regResult.isSuccessful())
        {
            System.out.println("Registration successful!");
            System.out.println(regResult.getAuthToken());
        }
        else
        {
            System.out.println("Error:");
            System.out.println(regResult.getErrorMsg());
        }
        System.out.println();
    }

    public static void createGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "createGame",
                new String[]{ "Models.Request" }, new Request[]{ request });

        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);

        if (gameResult.isSuccessful())
        {
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
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
    }

    public static void joinGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "joinGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        if (gameResult.isSuccessful())
        {
//            System.out.println(request.getGameId() + " successfully joined!");
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
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
    }


    public static void startGame(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "startGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        if (gameResult.isSuccessful())
        {
            System.out.println(gameResult.getGameId() + " successfully started!");
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
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
    }

    public static void updateClient(Request request){
        Command gameCommand = new Command("Interfaces.IServerGame", "updateClient",
                new String[]{ "Models.Request" }, new Request[]{ request });
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);

        System.out.println(gameResult.isSuccessful());
        if (gameResult.isSuccessful())
        {
//            System.out.println(gameResult.getUpdateCommands().size());
            //loop through and execute commands:
            for(int i = 0; i < gameResult.getUpdateCommands().size(); i++){
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
    }

}
