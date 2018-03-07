package AsyncTasks.GamePlay;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Client_Server_Communication.GamePlayFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class ChatMessageAsyncTask extends AsyncTask<Request, Void, Result> {
    GamePlayFacade gamePlayFacade = new GamePlayFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        //call the gamePlayFacade's addChat function -> return the Result and pass it to the onPostExecute();
        return gamePlayFacade.addChat(requests[0]);
    }
    //onPostExecute updates the Game model:
    //TODO - needs to the call the obeserver in the onPOSTEXECUTE function
    @Override
    protected void onPostExecute(Result result){
        if(result.getErrorMsg() == null)
        {
            System.out.println("Adding a Chat - This is the asyncTask");
            //this references the correct facade, and runs the command:
            gamePlayFacade.runCMD(result);
            //notifies the observer:
//            Client.getInstance().createGame();
        }
        else
        {
            //create an error message for the Active Game Model:
            Client.getInstance().sendMessage(result.getErrorMsg());
        }
    }
}
