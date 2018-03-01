package game.Stats;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Models.Result;
import activities.R;
import game.GameFragment;

import static java.security.AccessController.getContext;

/**
 * Created by fjameson on 2/28/18.
 */

public class StatsFragment extends Fragment {
    Button returnToGame;
    Button chatMenu;
    Button gameHistory;
    StatsPresenter statsPresenter;

    public StatsFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        statsPresenter = new StatsPresenter(getContext());
        View view = inflater.inflate(R.layout.stats, container, false);

        returnToGame= (Button)view.findViewById(R.id.returntogame);
        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = statsPresenter.returnToGame(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    Fragment fragment = new GameFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });

        chatMenu= (Button)view.findViewById(R.id.chatMenu);
        chatMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = statsPresenter.viewChat(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    // Fragment fragment = new LobbyFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    // headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });

        gameHistory= (Button)view.findViewById(R.id.gamehistory);
        gameHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = statsPresenter.viewGameHistory(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    //Fragment fragment = new LobbyFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    // headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });
        return view;

    }
}