package Client_Server_Communication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by brianotte on 2/12/18.
 */

public class Poller extends TimerTask{
//    Timer LobbyListTimer = new Timer();
//    static Poller instance = new Poller();
//    private Poller(){}

    ClientFacade clientFacade = new ClientFacade();

//    public static Poller getInstance(){return instance;}

    public void run(){
        System.out.println("hello world");

//        System.out.println(authToken);
        //build request:

        //the execute method will call the doinbackground and on post execute
        clientFacade.updateClient();

    }

}
