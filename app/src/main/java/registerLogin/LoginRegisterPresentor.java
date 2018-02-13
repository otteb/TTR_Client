package registerLogin;

import android.content.Context;
import android.widget.Toast;
import Models.Result;

import Services.GuiFacade;

/**
 * Created by fjameson on 2/2/18.
 */

public class LoginRegisterPresentor implements ILoginRegisterPresentor {

    public GuiFacade guiFacade = new GuiFacade();
    public LoginRegisterPresentor() {}


    public Result login(Context c, String username, String password)
    {
        if(username.equals("") || password.equals("")){
            Toast.makeText(c, "Either Your Password or Username is Null", Toast.LENGTH_SHORT).show();
        }
        else
        {
           Result r =  guiFacade.login(username, password);
           if(r.getErrorMsg()==null)
           {
               //Toast.makeText(c, "You successfully logged in", Toast.LENGTH_SHORT).show();
               return r;
               //switch to Lobby Mode
           }
           else
           {
               Toast.makeText(c, r.getErrorMsg(), Toast.LENGTH_SHORT).show();
           }
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
            Result r =  guiFacade.register(username, password);
            if(r.getErrorMsg()==null){
                Toast.makeText(c, "You successfully registered", Toast.LENGTH_SHORT).show();
                return r;
            }
            else
            {
                Toast.makeText(c, r.getErrorMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(c, "Either your passwords do not match or something is null", Toast.LENGTH_SHORT).show();
        }
        return null;
    }




}
