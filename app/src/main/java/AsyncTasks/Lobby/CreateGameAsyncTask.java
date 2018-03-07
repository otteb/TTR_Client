package AsyncTasks.Lobby;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 2/13/18.
 */

public class CreateGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.createGame(requests[0]);
//        return result;
    }

    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
//        if(result.isSuccessful()) { System.out.println("Created a game - This is the asyncTask"); }
        if(result.getErrorMsg() == null)
        {
            System.out.println("Creating a game - This is the asyncTask");
            clientFacade.runCMD(result);
//            Client.getInstance().createGame();
        }
        else
        {
            Client.getInstance().sendMessage(result.getErrorMsg());
        }
    }

}
