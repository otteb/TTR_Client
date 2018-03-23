package Client_Server_Communication;

import Models.Command;
import Models.Gameplay.ActiveGame;
import Models.Request;
import Models.Result;

public class GamePlayFacade {
    //builds the command to be executed and returns it in a result object:

    // le Fin:
    public Result addChat(Request request){
        //build command:
        Command chatCommand = new Command("Interfaces.IChat", "addChat",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(chatCommand);
    }

    // le Fin:
    public Result discardDestinationCard(Request request){
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "discardDestCards",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }

    public Result drawDestCards(Request request){
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "drawDestCards",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }

    public Result takeFaceUpCard(Request request)
    {
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "takeFaceUpCard",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }

    public Result drawTrainCard(Request request)
    {
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "drawTrainCard",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }

    public Result claimRoute(Request request)
    {
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "claimRoute",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }

    public Result endTurn(Request request)
    {
        //build command:
        Command gameCommand = new Command("Interfaces.IGamePlay", "endTurn",
                new String[]{ "Models.Request" }, new Request[]{ request });
        //send the command to the server via the ClientCommunicator
        return ClientCommunicator.getInstance().sendCommand(gameCommand);
    }


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


    public void runCMD(Result result) {
        if (result.isSuccessful())
        {
            ActiveGame.getInstance().incGameCMDNum(result.getUpdateCommands().size());
            for(int i = 0; i < result.getUpdateCommands().size(); i++)
            {
                try { result.getUpdateCommands().get(i).execute(); }
                catch (Exception e) {
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
