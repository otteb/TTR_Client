package activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import Models.Gameplay.Route;
import Models.Request;
import game.Cards.TrainCardsFragment;
import game.Cards.DestinationCardsFragment;
import game.Chat.ChatFragment;
import game.ClaimRouteFragment;
import game.EndGame.EndGameFragment;
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
    public EndGameFragment endGameFragment;
    public ClaimRouteFragment claimRouteFragment;
    FragmentManager headfrag = getSupportFragmentManager();
    String currentFrag = "";

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
        currentFrag = "login";
        headfrag.beginTransaction().replace(R.id.activity_main, loginFragment).commit();
    }

    public void switchToRegister()
    {
        registerFragment = new RegisterFragment();
        currentFrag = "register";
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
            currentFrag = "lobby";
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
        currentFrag = "game";
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
        currentFrag = "stats";
        headfrag.beginTransaction().replace(R.id.activity_main, statsFragment).commit();

    }

    public void switchToChat()
    {
        chatFragment= new ChatFragment();
        currentFrag = "chat";
        headfrag.beginTransaction().replace(R.id.activity_main, chatFragment).commit();
    }

    public void switchToTrainCards()
    {
        trainCardsFragment = new TrainCardsFragment();
        currentFrag = "trains";
        headfrag.beginTransaction().replace(R.id.activity_main, trainCardsFragment).commit();
    }

    public void switchToDestCards()
    {
        destCardsFragment = new DestinationCardsFragment();
        currentFrag = "destinations";
        headfrag.beginTransaction().replace(R.id.activity_main, destCardsFragment).commit();
    }

    public void switchToGameHistory()
    {
        gameHistoryFragment = new GameHistoryFragment();
        currentFrag = "history";
        headfrag.beginTransaction().replace(R.id.activity_main, gameHistoryFragment).commit();
    }

    public void switchToEndGame(){
        endGameFragment = new EndGameFragment();
        currentFrag = "end";
        headfrag.beginTransaction().replace(R.id.activity_main, endGameFragment).commit();
    }

    public void switchToClaimRoute(Route route)
    {
        claimRouteFragment = new ClaimRouteFragment();
        currentFrag = "claimRoute";
        Bundle bundle = new Bundle();
        bundle.putInt("routeNumber", route.getRouteNumber());
        bundle.putString("start", route.getStartCity());
        bundle.putString("end", route.getEndCity());
        bundle.putString("color", route.getColor());
        bundle.putInt("length", route.getLength());
        claimRouteFragment.setArguments(bundle);

        headfrag.beginTransaction().replace(R.id.activity_main, claimRouteFragment).commit();
    }

    public void updateGamesList() { lobbyFragment.updateGameList(); }

    public void updatePlayers() { lobbyFragment.updatePlayers(); }

    public void updateGameHistory() { gameHistoryFragment.update(); }

    public void updateChat() { chatFragment.update(); }

    public void updateStats() { statsFragment.updateStats(); }

    public void updateHand() { statsFragment.updateHand(); }

    public void updateFaceUp() {
        if(currentFrag.equals("trains"))
        {
            trainCardsFragment.updateFaceUp();
            System.out.println("Face up cards updated");
        }
        System.out.println("you're not in trains");
//        if(trainCardsFragment != null)
//        {
//            trainCardsFragment.updateFaceUp();
//
//        }
    }

    public void updateRoutes() { gameFragment.updateRoutes(); }

    public void updateDestinations()
    {
        if(currentFrag.equals("destinations"))
        {
            destCardsFragment.updateView();
        }
        if(currentFrag.equals("stats"))
        {
            statsFragment.updateDestCards();
        }
//        if(statsFragment != null) { statsFragment.updateDestCards(); }
    }

    public void displayDrawnCard() {
        if(currentFrag.equals("trains"))
        {
            trainCardsFragment.displayCard();
        }
    }
}
