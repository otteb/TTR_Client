package activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import Models.Client;
import Models.Gameplay.Game;
import Models.Request;
import RegisterLogin.LoginFragment;
//import game.GameHistory.GameHistoryFragment;

import game.Cards.CardsFragment;
import game.Chat.ChatFragment;
import game.GameHistory.GameHistoryFragment;
import game.Stats.StatsFragment;
import lobby.LobbyFragment;
import game.GameFragment;
import RegisterLogin.LoginRegisterPresenter;
import RegisterLogin.RegisterFragment;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button button;
    public final static String openMap= "true";
    public LobbyFragment lobbyFragment;
    public GameFragment gameFragment;
    public LoginFragment loginFragment;
    public StatsFragment statsFragment;
    public ChatFragment chatFragment;
    public CardsFragment cardsFragment;
    public GameHistoryFragment gameHistoryFragment;
    public RegisterFragment registerFragment;
    FragmentManager headfrag = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (openMap.equals("true")) {
            switchToCards("kip", false);
        } else {
           switchToLogin();

        }
    }

    public void switchToLogin()
    {
        loginFragment = new LoginFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, loginFragment).commit();
    }

    public void switchToRegister()
    {
        registerFragment = new RegisterFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, registerFragment).commit();
    }


    public void switchToLobby(Request r)
    {
        if (r.getAuthToken().equals("ERROR: Invalid Registration"))
        {
            Toast.makeText(getBaseContext(), "ERROR: Invalid Registration", Toast.LENGTH_SHORT).show();
        }
        else{
            lobbyFragment = new LobbyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", r.getUsername());
            bundle.putString("password", r.getPassword());
            bundle.putString("authToken", r.getAuthToken());
            lobbyFragment.setArguments(bundle);
            headfrag.beginTransaction().replace(R.id.activity_main, lobbyFragment).commit();}
    }

    public void openGame()
    {
        gameFragment = new GameFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, gameFragment).commit();
    }

    public void updateCreate(Game currentGame)
    {
        //currentGame = lobbyP.createGame(getActivity(), play, gameName.getText().toString());

        lobbyFragment.createGame = currentGame;
        lobbyFragment.gameList = Client.getInstance().getGameList();
        lobbyFragment.getView();
    }


    public void switchToStats()
    {
        //game
        statsFragment= new StatsFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, statsFragment).commit();

    }

    public void switchToChat()
    {
        chatFragment= new ChatFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, chatFragment).commit();
    }

    public void switchToCards(String player, boolean destinationCardSetup )
    {
        cardsFragment = new CardsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", player);
        bundle.putBoolean("destinationCardSetup", destinationCardSetup);
        cardsFragment.setArguments(bundle);
        headfrag.beginTransaction().replace(R.id.activity_main, cardsFragment).commit();
    }



    public void switchToGameHistory()
    {
        gameHistoryFragment = new GameHistoryFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, gameHistoryFragment).commit();
    }

    public void updateGamesList()
    {
        lobbyFragment.updateGameList();
    }

    public void updatePlayers()
    {
        lobbyFragment.updatePlayers();
    }

    public void updateGameHistory()
    {
        gameHistoryFragment.update();
    }

    public void updateChat() { chatFragment.update(); }


}
