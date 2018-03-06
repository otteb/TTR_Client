package Services;

import AsyncTasks.LoginAndRegister.LoginAsyncTask;
import Models.Request;

/**
 * Created by brianotte on 3/6/18.
 */

public class GameGuiFacade {
    //this houses all of the game-specific functions that call the Async tasks:

    //this is an example function:
    public void login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.execute(loginRequest);
    }
}
