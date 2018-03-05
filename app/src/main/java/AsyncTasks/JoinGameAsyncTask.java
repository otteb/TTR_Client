package AsyncTasks;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 2/13/18.
 */

public class JoinGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.joinGame(requests[0]);
//        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.isSuccessful()) {
            System.out.println("joined a game - This is the asyncTask");
            clientFacade.runCMD(result);
        }
//        Game game = Client.getInstance().getGameMap().get(result.getGameId());
        else { Client.getInstance().sendMessage(result.getErrorMsg()); }
    }
}
