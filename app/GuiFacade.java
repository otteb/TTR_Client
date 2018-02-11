package cs340.ttr_client;

import com.example.shared.Request;
import com.example.shared.Result;

/**
 * Created by fjameson on 2/9/18.
 */

public class GuiFacade {
    public ClientModel clientModel= new ClientModel();

    public Result login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        clientModel.setLoginRequest(loginRequest);
        return clientModel.loginTest();
    }

    public void register (String username, String password)
    {

    }

    public void joinGame (String authToken)
    {}

    public void createGame (String gameId)
    {
    }

    public void startGame (String gameId)
    {

    }

}