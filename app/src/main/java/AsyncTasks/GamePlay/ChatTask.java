package AsyncTasks.GamePlay;

import android.os.AsyncTask;

import Client_Server_Communication.ClientFacade;
import Client_Server_Communication.GamePlayFacade;
import Models.Client;
import Models.Request;
import Models.Result;
import ObserverPattern.TTR_Observable;

public class ChatTask extends AsyncTask<Request, Void, Result> {
    private GamePlayFacade gamePlayFacade = new GamePlayFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        //call the gamePlayFacade's addChat function -> return the Result and pass it to the onPostExecute();
        return gamePlayFacade.addChat(requests[0]);
    }

    @Override
    protected void onPostExecute(Result result){
        if(result.getErrorMsg() == null)
        {
            System.out.println("Adding a Chat - This is the asyncTask");
            //this references the correct facade, and runs the command:
            gamePlayFacade.runCMD(result);
        }
        else
        {
            //create an error message for the Active Game Model:
            TTR_Observable.getInstance().sendMessage(result.getErrorMsg());
        }
    }
}
