package AsyncTasks.GamePlay;

import android.os.AsyncTask;

import Client_Server_Communication.GamePlayFacade;
import Models.Request;
import Models.Result;
import ObserverPattern.TTR_Observable;

public class DiscardDestinationsTask extends AsyncTask<Request, Void, Result> {
    private GamePlayFacade gamePlayFacade = new GamePlayFacade();

    @Override
    protected Result doInBackground(Request... requests) {
        return gamePlayFacade.discardDestinationCard(requests[0]);
    }
    //onPostExecute updates the Game model:
    @Override
    protected void onPostExecute(Result result) {
        if (result.getErrorMsg() == null) {
            System.out.println("Discarding a destination card - This is the asyncTask");
            //this references the correct facade, and runs the command:
            gamePlayFacade.runCMD(result);
        } else {
            //create an error message for the Active Game Model:
            TTR_Observable.getInstance().sendMessage(result.getErrorMsg());
        }
    }
}
