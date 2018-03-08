package AsyncTasks.LoginAndRegister;

import android.os.AsyncTask;

import java.sql.ClientInfoStatus;

import Client_Server_Communication.ClientFacade;
import Client_Server_Communication.Poller;
import Models.Client;
import Models.Request;
import Models.Result;
import ObserverPattern.TTR_Observable;

public class LoginAsyncTask extends AsyncTask <Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        Result result = clientFacade.login(requests[0]);
        if(result.isSuccessful())
        {
            Client.getInstance().setUserName(requests[0].getUsername());
        }
        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.getErrorMsg() == null)
        {
            Client.getInstance().setAuthToken(result.getAuthToken());
            clientFacade.runCMD(result);
            Client.getInstance().setIsLoggedIn(true);
            TTR_Observable.getInstance().login();
            Client.getInstance().getPoller().runLobbyCommands();
        }else {
            TTR_Observable.getInstance().sendMessage(result.getErrorMsg());
        }
        System.out.println("Logged in - AsyncTask");
    }
}
