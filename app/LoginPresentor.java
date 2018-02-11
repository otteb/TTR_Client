package cs340.ttr_client;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.shared.LoginRequest;
import com.example.shared.LoginResult;
import com.example.shared.Result;

/**
 * Created by fjameson on 2/2/18.
 */

public class LoginPresentor extends AsyncTask<String, Void, LoginRequest> {

    public GuiFacade guiFacade = new GuiFacade();
    public LoginPresentor() {}

    @Override
    protected LoginRequest doInBackground(String... strings) {
        LoginRequest lq= new LoginRequest(strings[0], strings[1]);
        //exe to send stuff on or onto server proxy stuff
        //Login Result = sp.serverLogin(lq)
        return lq;
    }

    public void login(Context c, String username, String password)
    {
        LoginResult lr = new LoginResult(username, password);
        if(username.equals("") || password.equals("")){
            Toast.makeText(c, "Either Your Password or Username is Null", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Result r=  guiFacade.login(username, password);
            if(r.getErrorMsg() == null)
            {
                Toast.makeText(c, "You successfully logged in", Toast.LENGTH_SHORT).show();
                //switch to Lobby Mode
            }
            else
            {
                Toast.makeText(c, r.getErrorMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        //return lr;
    }

    public void switchToRegister(Context c)
    {

        Toast.makeText(c, "Switching to Register", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(c, RegisterPresentor.class);
        //intent.putExtra(Game.Straight_to_Map, "false");
        //((Game)context).switchToGame();
        //Fragment fragment = new LoginFragment();
        //  FragmentManager headfrag = getSupportFragmentManager();
        //headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
    }



}
