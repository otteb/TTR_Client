package AsyncTasks.Lobby;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 2/13/18.
 */

public class StartGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.startGame(requests[0]);
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        System.out.println("Started a game - This is the asyncTask");
        //executes the command:
        clientFacade.runCMD(result);
        //notifies the observer:
//        Client.getInstance().startGame();
    }
}
