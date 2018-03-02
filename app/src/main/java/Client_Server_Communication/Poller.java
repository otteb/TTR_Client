package Client_Server_Communication;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Models.Client;
import Models.Command;
import Models.Request;

/**
 * Created by brianotte on 2/12/18.
 */
public class Poller {
//public class Poller extends TimerTask{
//    @Override
//    public void run() {
//        //this is where we call updateClient();
//        ClientFacade clientFacade = new ClientFacade();
//            Request request = new Request();
//            request.setAuthToken(Client.getInstance().getAuthToken());
//            request.setCommandNum(Client.getInstance().getCommandNum());
////            request.setCommandNum(0);
//            //call the client facade updateClient() - use the current index;
//            ArrayList<Command> commands = clientFacade.updateClient(request).getUpdateCommands();
//            request.setCommandNum(Client.getInstance().getCommandNum());
//        if(commands != null){
//            for(int i = 0; i < commands.size(); i++){
//                try {
//                    commands.get(i).execute();
//                }catch (Exception e)
//                {
//                    System.out.println("ERROR");
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }

    //create a Poller that manually updates everything:

    //Use this stuff for next time:

    Timer LobbyListTimer = new Timer();
//    static Poller instance = new Poller();
//    private Poller(){}

//    public static Poller getInstance() {return instance;};
    public void runLobbyCommands(){
        LobbyListTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                LobbyPolling lp = new LobbyPolling();
                lp.execute();
            }
        }, 1, 1000);
    }


    public static class LobbyPolling extends AsyncTask<Void, Void, ArrayList<Command>> {

        @Override
        protected ArrayList<Command> doInBackground(Void... params) {
            ClientFacade clientFacade = new ClientFacade();
            Request request = new Request();
            request.setAuthToken(Client.getInstance().getAuthToken());
            request.setCommandNum(Client.getInstance().getCommandNum());
            //call the client facade updateClient() - use the current index;
            ArrayList<Command> returnList = clientFacade.updateClient(request).getUpdateCommands();
            return returnList;
        }

        @Override
        protected void onPostExecute(ArrayList<Command> commands){
            super.onPostExecute(commands);
            //create for loop and execute all of the commands;
            if(commands != null){
                Client.getInstance().incCommandNum(commands.size());
                for(int i = 0; i < commands.size(); i++){
                    try {
//                    gameResult.getUpdateCommands().get(i).execute();
                        commands.get(i).execute();
                    }catch (Exception e)
                    {
                        System.out.println("ERROR");
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
