package AsyncTasks;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;
/**
 * Created by ferrell3 on 3/1/18.
 */

public class LeaveGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.leaveGame(requests[0]);
//        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.isSuccessful()) {
            clientFacade.runCMD(result);
            System.out.println("Left a game - This is the asyncTask");
        }
//        Game game = Client.getInstance().getGameMap().get(result.getGameId());
        else { Client.getInstance().sendMessage(result.getErrorMsg()); }
    }
}
