package Client_Server_Communication;

import Models.Client;
import Models.Command;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class GamePlayFacade {

    //TODO STILL NEEDS TO BE ADJUSTED:
    public Result addChat(Request request){
        //build command:
        Command gameCommand = new Command("Interfaces.ILobby", "joinGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        //return the Result object;
        return gameResult;
    }

    //TODO STILL NEEDS TO BE ADJUSTED:
    public Result discardDestinationCard(Request request){
        //build command:
        Command gameCommand = new Command("Interfaces.ILobby", "joinGame",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        //return the Result object;
        return gameResult;
    }






    //TODO ADJUST this function to run in the active Game Model:
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
