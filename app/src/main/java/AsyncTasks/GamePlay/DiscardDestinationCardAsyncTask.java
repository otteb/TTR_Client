package AsyncTasks.GamePlay;

import android.os.AsyncTask;

import Client_Server_Communication.GamePlayFacade;
import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public class DiscardDestinationCardAsyncTask extends AsyncTask<Request, Void, Result> {
    GamePlayFacade gamePlayFacade = new GamePlayFacade();
    @Override
    protected Result doInBackground(Request... requests) {
        return gamePlayFacade.discardDestinationCard(requests[0]);
    }
    //onPostExecute updates the Game model:
    @Override
    protected void onPostExecute(Result result) {
//        if(result.isSuccessful()) { System.out.println("Created a game - This is the asyncTask"); }
        if (result.getErrorMsg() == null) {
            System.out.println("Adding a Chat - This is the asyncTask");
            //this references the correct facade, and runs the command:
            gamePlayFacade.runCMD(result);
            //notifies the observer:
//            Client.getInstance().createGame();
        } else {
            //create an error message for the Active Game Model:
//            Client.getInstance().sendMessage(result.getErrorMsg());
        }
    }
}
