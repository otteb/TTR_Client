package RegisterLogin;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ILoginRegisterPresenter;
import Models.Client;
import ObserverPattern.TTR_Observable;
import activities.MainActivity;
import Models.Request;
import Models.Result;

import Services.GUI.GuiFacade;

/**
 * Created by fjameson on 2/2/18.
 */

public class LoginRegisterPresenter implements ILoginRegisterPresenter, Observer {

    public Context context;
    public Request user;
    public GuiFacade guiFacade = new GuiFacade();
    LoginFragment loginFragment;
    MainActivity mainActivity;

    public LoginRegisterPresenter(Context c) {
        context=c;
        guiFacade.addObserver(this);
        user = new Request();
    }

    public void login(Context c, String username, String password)
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
            guiFacade.login(username, password);
        }
    }

    public void switchToRegister(Context c)
    {
        context=c;
        Toast.makeText(c, "Switching to Login", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToRegister();
    }

    public void switchToLogin(Context c){
        context=c;
        Toast.makeText(c, "Switching to Login", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToLogin();
    }

    public void register(Context c, String username, String password, String confpswd)
    {
        context = c;
        if (password.equals(confpswd) && !username.equals("") & !password.equals(""))
        {
            user = new Request();
            user.setUsername(username);
            user.setPassword(password);
            guiFacade.register(username, password);
        }
        else
        {
            Toast.makeText(c, "Either your passwords do not match or something is null", Toast.LENGTH_SHORT).show();
        }
    }


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

//        if (!authToken.equals("create") && !authToken.equals("join") && !authToken.equals("start")) {
//            if (user != null) {
//                if (authToken.equals("ERROR: Invalid Registration") || authToken.equals("ERROR: Incorrect username/password combination")) {
//                    Toast.makeText(context, (CharSequence) authToken, Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    user.setAuthToken((String) authToken);
//                    mainActivity = (MainActivity) context;
//                    mainActivity.switchToLobby(user);
//                    user = null;
//                }
//            }
//        }
    }
}
