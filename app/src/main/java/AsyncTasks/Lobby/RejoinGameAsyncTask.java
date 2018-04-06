package AsyncTasks.Lobby;

import android.os.AsyncTask;
import Client_Server_Communication.ClientFacade;
import Models.Request;
import Models.Result;

public class RejoinGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.rejoinGame(requests[0]);
    }

    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        System.out.println("Rejoined the game - This is the asyncTask");
        //executes the command:
        clientFacade.runCMD(result);
    }
}
