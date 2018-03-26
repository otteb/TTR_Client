package RegisterLogin;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Client_Server_Communication.ClientCommunicator;
import Interfaces.ILoginRegisterPresenter;
import Models.Client;
import activities.MainActivity;
import Models.Request;

import Services.GUI.GuiFacade;

/**
 * @inv any context arg != null
 * @inv this.context= current context of the activity
 */

public class LoginRegisterPresenter implements ILoginRegisterPresenter, Observer {

    public Context context;
    public Request user;
    public GuiFacade guiFacade = new GuiFacade();
    LoginFragment loginFragment;
    MainActivity mainActivity;

    /**
     * @pre gameGuiFacade != null
     * @post The CardsPresenter is an Observer of the gameGuiFacade class
     * @post user != null
     */
    public LoginRegisterPresenter(Context c) {
        context=c;
        guiFacade.addObserver(this);
        user = new Request();
    }

    /**
     * @pre username != null
     * @pre password != null
     * @post user.username = username
     * @post user.password = password
     * @post username && passed onto the guiFacade
     */
    public void login(Context c, String username, String password, String port, String ipAddress)
    {
        context=c;
        if(username.equals("") || password.equals("")){
            Toast.makeText(c, "Either Your Password or Username is Null", Toast.LENGTH_SHORT).show();
        }
        else
        {
//            user = new Request();
            user.setUsername(username);
            user.setPassword(password);
            user.setPort(port);
            user.setIpAddress(ipAddress);
            //set the port and ipAddress in the client communicator:
            ClientCommunicator.getInstance().setServerHost(ipAddress);
            ClientCommunicator.getInstance().setServerPort(port);
            guiFacade.login(username, password);
        }
    }

    /**
     * @post The Activity is switched to the Register Fragment
     */
    public void switchToRegister(Context c)
    {
        context=c;
        Toast.makeText(c, "Switching to Login", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToRegister();
    }

    /**
     * @post The Activity is switched to the Login Fragment
     */
    public void switchToLogin(Context c){
        context=c;
        Toast.makeText(c, "Switching to Login", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToLogin();
    }

    /**
     * @pre username != null
     * @pre password != null
     * @pre confpswd = password
     * @post user != null
     * @post user.username = username
     * @post user.password = password
     * @post username && passed onto the guiFacade
     */
    public void register(Context c, String username, String password, String confpswd, String port, String ipAddress)
    {
        context = c;
        if (password.equals(confpswd) && !username.equals("") & !password.equals(""))
        {
            user = new Request();
            user.setUsername(username);
            user.setPassword(password);
            user.setPort(port);
            user.setIpAddress(ipAddress);
            ClientCommunicator.getInstance().setServerHost(ipAddress);
            ClientCommunicator.getInstance().setServerPort(port);
            guiFacade.register(username, password);
        }
        else
        {
            Toast.makeText(c, "Either your passwords do not match or something is null", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * @pre o != null
     * @pre result != null
     * @pre result= "login"
     * @post user.authToken = Client.authToken (singleton class)
     * @post the activity is switched to the Lobby Fragment
     */
    @Override
    public void update(Observable o, Object result) {
        if(result.equals("login"))
        {
            mainActivity = (MainActivity) context;
            if(user == null)
            {
                user = new Request();
            }
            user.setAuthToken(Client.getInstance().getAuthToken());
            mainActivity.switchToLobby(user);
        }
        o.hasChanged();
    }
}
