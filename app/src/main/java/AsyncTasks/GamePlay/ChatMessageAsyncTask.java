package AsyncTasks.GamePlay;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class ChatMessageAsyncTask extends AsyncTask<Request, Void, Result> {
    ClientFacade clientFacade = new ClientFacade();
    //instance of the Facade/Services class:
    @Override
    protected Result doInBackground(Request... requests) {
        return null;
    }
    //onPostExecute updates the Game model:
    @Override
    protected void onPostExecute(Result result){
//        if(result.isSuccessful()) { System.out.println("Created a game - This is the asyncTask"); }
        if(result.getErrorMsg() == null)
        {
            System.out.println("Creating a game - This is the asyncTask");
            //this references the correct facade, and runs the command:
            clientFacade.runCMD(result);
            //notifies the observer:
            Client.getInstance().createGame();
        }
        else
        {
            Client.getInstance().sendMessage(result.getErrorMsg());
        }
    }
}
