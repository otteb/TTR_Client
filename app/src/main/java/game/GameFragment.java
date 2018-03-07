package game;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Models.Result;
import activities.R;
import lobby.LobbyFragment;
import RegisterLogin.LoginRegisterPresenter;

/**
 * Created by fjameson on 2/2/18.
 */

public class GameFragment extends Fragment {
    Button returnToLobby;
    Button claimRoute;
    Button goToStats;
    GamePresenter gamePresenter;

    public GameFragment()
    {
        LoginRegisterPresenter loginRegisterPresenter = new LoginRegisterPresenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gamePresenter = new GamePresenter(getContext());
        View view = inflater.inflate(R.layout.game, container, false);

        returnToLobby= (Button)view.findViewById(R.id.returntolobby);
        returnToLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = gamePresenter.switchToLobby(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    Fragment fragment = new LobbyFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });

        claimRoute= (Button)view.findViewById(R.id.claim);
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = gamePresenter.claimRoute(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    Fragment fragment = new LobbyFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });

        goToStats= (Button)view.findViewById(R.id.stats);
        goToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = gamePresenter.switchToStats(getActivity());
                if(r != null)
                {

                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    Fragment fragment = new LobbyFragment();

                    //need something along these lines for the game and users in it?
                    /*bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);*/

                    headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });
        return view;

    }
}