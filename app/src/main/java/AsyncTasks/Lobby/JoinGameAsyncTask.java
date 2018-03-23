package AsyncTasks.Lobby;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Request;
import Models.Result;
import ObserverPattern.TTR_Observable;

public class JoinGameAsyncTask extends AsyncTask<Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        //returns the correct Result object from the server via the Client facade through the ClientCommunicator
        return clientFacade.joinGame(requests[0]);
//        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.isSuccessful()) {
            System.out.println("joined a game - This is the asyncTask");
            //executes the command in the clientFacade:
            clientFacade.runCMD(result);
        }
//        Game game = Client.getInstance().getGameMap().get(result.getGameId());
        else { TTR_Observable.getInstance().sendMessage(result.getErrorMsg()); }
    }
}
