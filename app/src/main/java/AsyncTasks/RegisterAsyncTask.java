package AsyncTasks;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Models.Client;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 2/13/18.
 */

public class RegisterAsyncTask extends AsyncTask<Request, Void, Result> {
    ClientFacade clientFacade = new ClientFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        Result result = clientFacade.register(requests[0]);
        return result;
    }
    //onPostExecute updates the Client model:
    @Override
    protected void onPostExecute(Result result){
        if(result.getErrorMsg() == null) {
            Client.getInstance().setAuthToken(result.getAuthToken());
        }else {
            Client.getInstance().sendMessage(result.getErrorMsg());
        }
        System.out.println("Registered new user - AsyncTask");
    }
}
