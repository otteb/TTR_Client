package activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import Models.Request;
import game.Cards.TrainCardsFragment;
import game.Cards.DestinationCardsFragment;
import game.Chat.ChatFragment;
import game.GameHistory.GameHistoryFragment;
import game.Stats.StatsFragment;
import game.GameFragment;
import lobby.LobbyFragment;
import RegisterLogin.RegisterFragment;
import RegisterLogin.LoginFragment;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
//    public final static String openMap= "false";
    public LobbyFragment lobbyFragment;
    public GameFragment gameFragment;
    public LoginFragment loginFragment;
    public StatsFragment statsFragment;
    public ChatFragment chatFragment;
    public TrainCardsFragment trainCardsFragment;
    public DestinationCardsFragment destCardsFragment;
    public GameHistoryFragment gameHistoryFragment;
    public RegisterFragment registerFragment;
    FragmentManager headfrag = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (openMap.equals("true")) {
//            switchToTrainCards("kip", false);
//        } else {
           switchToLogin();
//
//        }
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


//    public void updateCreate(Game currentGame)
//    {
//        //currentGame = lobbyP.createGame(getActivity(), play, gameName.getText().toString());
//        lobbyFragment.createGame = currentGame;
//        lobbyFragment.gameList = Client.getInstance().getGameList();
//        lobbyFragment.getView();
//    }


    public void switchToStats()
    {
        statsFragment= new StatsFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, statsFragment).commit();

    }

    public void switchToChat()
    {
        chatFragment= new ChatFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, chatFragment).commit();
    }

    public void switchToTrainCards()
    {
        trainCardsFragment = new TrainCardsFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, trainCardsFragment).commit();
    }

    public void switchToDestCards()
    {
        destCardsFragment = new DestinationCardsFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, destCardsFragment).commit();
    }

    public void switchToGameHistory()
    {
        gameHistoryFragment = new GameHistoryFragment();
        headfrag.beginTransaction().replace(R.id.activity_main, gameHistoryFragment).commit();
    }

    public void updateGamesList() { lobbyFragment.updateGameList(); }

    public void updatePlayers() { lobbyFragment.updatePlayers(); }

    public void updateGameHistory() { gameHistoryFragment.update(); }

    public void updateChat() { chatFragment.update(); }

    public void updateStats() { statsFragment.updateStats(); }

    public void updateHand() { statsFragment.updateHand(); }

    public void updateFaceUp() {
        if(trainCardsFragment != null)
        {
            trainCardsFragment.updateFaceUp();
        }
    }

    public void updateDestinations()
    {
        destCardsFragment.updateView();
        if(statsFragment == null) { statsFragment = new StatsFragment(); }
        statsFragment.updateDestCards();
    }

    public void displayDrawnCard() { trainCardsFragment.displayCard(); }
}
