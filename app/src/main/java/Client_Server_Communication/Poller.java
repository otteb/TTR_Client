package Client_Server_Communication;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Models.Client;
import Models.Command;
import Models.Gameplay.ActiveGame;
import Models.Request;

public class Poller {

//    private static Poller theOneAndOnly = new Poller();
//    public static Poller getInstance()
//    {
//        return theOneAndOnly;
//    }
    public Poller() {}

    Timer LobbyListTimer = new Timer();
    Timer GamePlayTimer = new Timer();

    //For the Lobby:
    public void runLobbyCommands(){
        LobbyListTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                LobbyPolling lp = new LobbyPolling();
                lp.execute();
            }
        }, 1, 1000);
    }

    public void stopLobbyCommands() {
        LobbyListTimer.cancel();
        LobbyListTimer.purge();
    }

    //For the Game:
    public void runGamePlayCommands(){
        GamePlayTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                GamePolling gamePolling = new GamePolling();
                gamePolling.execute();
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
    public static class GamePolling extends AsyncTask<Void, Void, ArrayList<Command>> {
        //attributes:
        GamePlayFacade gamePlayFacade = new GamePlayFacade();
        @Override
        protected ArrayList<Command> doInBackground(Void... params) {

            Request request = new Request();
            request.setGameId(ActiveGame.getInstance().getId());
            request.setAuthToken(Client.getInstance().getAuthToken());
            request.setGameCMDNum(ActiveGame.getInstance().getGameCMDNum());
            //call the client facade updateClient() - use the current index;
//            ArrayList<Command> returnList = clientFacade.updateClient(request).getUpdateCommands();
            ArrayList<Command> returnList = gamePlayFacade.updateClient(request).getUpdateCommands();
            return returnList;
        }
        @Override
        protected void onPostExecute(ArrayList<Command> commands){
            super.onPostExecute(commands);
            //create for loop and execute all of the commands;
            if(commands != null){
                //updates the ActiveGame's CMD number:
                ActiveGame.getInstance().incGameCMDNum(commands.size());
                //iterates through the commands and executes them.
                for(int i = 0; i < commands.size(); i++){
                    try {
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
