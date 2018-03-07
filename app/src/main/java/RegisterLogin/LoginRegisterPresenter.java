package RegisterLogin;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ILoginRegisterPresenter;
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
    public LoginRegisterPresenter(Context c) {
        context=c;
        guiFacade.addObserver(this);
        //make a login frag
    }
    public Result login(Context c, String username, String password)
    {
        if(username.equals("") || password.equals("")){
            Toast.makeText(c, "Either Your Password or Username is Null", Toast.LENGTH_SHORT).show();
        }
        else
        {
            guiFacade.login(username, password);
            user = new Request();
            user.setUsername(username);
            user.setPassword(password);
//           if(r.getErrorMsg()==null)
//           {
//               //Toast.makeText(c, "You successfully logged in", Toast.LENGTH_SHORT).show();
//               return r;
//               //switch to Lobby Mode
//           }
//           else
//           {
//               Toast.makeText(c, r.getErrorMsg(), Toast.LENGTH_SHORT).show();
//           }
        }
        return null;
    }

    public void switchToRegister(Context c)
    {

        Toast.makeText(c, "Switching to Register", Toast.LENGTH_SHORT).show();
    }

    public Result register(Context c, String username, String password, String confpswd)
    {
        if (password.equals(confpswd) && !username.equals("") & !password.equals(""))
        {
            user = new Request();
            user.setUsername(username);
            user.setPassword(password);
            Result r =  new Result();
            guiFacade.register(username, password);
//            if(r.getErrorMsg()==null){
//                Toast.makeText(c, "You successfully registered", Toast.LENGTH_SHORT).show();
//                return r;
//            }
//            else
//            {
//                Toast.makeText(c, r.getErrorMsg(), Toast.LENGTH_SHORT).show();
//            }
        }
        else
        {
            Toast.makeText(c, "Either your passwords do not match or something is null", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public void switchToLobby(Context c, String username, String password, String authToken)
    {
        user.setAuthToken(authToken);
        user.setPassword(password);
        user.setUsername(username);
        MainActivity mainActivity = (MainActivity) c;
        mainActivity.switchToLobby(user);
        user = null;
    }


    @Override
    public void update(Observable o, Object authToken) {

        if (!authToken.equals("create") && !authToken.equals("join") && !authToken.equals("start")) {
            if (user != null) {
                if (authToken.equals("ERROR: Invalid Registration") || authToken.equals("ERROR: Incorrect username/password combination")) {
                    Toast.makeText(context, (CharSequence) authToken, Toast.LENGTH_SHORT).show();
                } else {
                    String a = (String) authToken;
                    user.setAuthToken(a);
                    user.setAuthToken((String) authToken);
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.switchToLobby(user);
                    authToken = a;
                    user = null;
                }
            }
        }
    }
}
