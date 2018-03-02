package AsyncTasks;

import android.os.AsyncTask;

import java.sql.ClientInfoStatus;

import Client_Server_Communication.ClientFacade;
import Client_Server_Communication.Poller;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 2/13/18.
 */

public class LoginAsyncTask extends AsyncTask <Request, Void, Result> {
    private ClientFacade clientFacade = new ClientFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        return clientFacade.login(requests[0]);
//        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.getErrorMsg() == null) {
            Client.getInstance().setAuthToken(result.getAuthToken());
        }else {
            Client.getInstance().sendMessage(result.getErrorMsg());
        }
        System.out.println("Logged in - AsyncTask");
    }
}
