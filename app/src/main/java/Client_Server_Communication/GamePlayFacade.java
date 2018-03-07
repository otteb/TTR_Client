package Client_Server_Communication;

import Models.Client;
import Models.Command;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class GamePlayFacade {
    //builds the command to be executed and returns it in a result object:

    // le Fin:
    public Result addChat(Request request){
        //build command:
        Command chatCommand = new Command("Interfaces.IChat", "addChat",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        Result chatResult = ClientCommunicator.getInstance().sendCommand(chatCommand);
        //return the Result object;
        return chatResult;
    }

    // le Fin:
    public Result discardDestinationCard(Request request){
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "discardDestCards",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        Result gameResult = ClientCommunicator.getInstance().sendCommand(gameCommand);
        //return the Result object;
        return gameResult;
    }


    //TODO still needs to be adjusted:
    Result updateClient(Request request){
        Command gameCommand = new Command("Interfaces.IGamePlay", "updateClient",
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


    //Finished
    public void runCMD(Result result) {
        if (result.isSuccessful())
        {
            for(int i = 0; i < result.getUpdateCommands().size(); i++){
                int temp = Client.getInstance().getActiveGameCMDNum();
                Client.getInstance().setActiveGameCMDNum(++temp);
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
