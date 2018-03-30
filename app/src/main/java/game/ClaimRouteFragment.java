package game;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import activities.R;

/**
 * Created by ferrell3 on 3/26/18.
 */

public class ClaimRouteFragment extends Fragment {
    ClaimRoutePresenter claimRoutePresenter;
    TextView title;
    TextView routeInfo;
    TextView wildTitle;
    TextView colorTitle;
    TextView redCards;
    TextView orangeCards;
    TextView yellowCards;
    TextView greenCards;
    TextView blueCards;
    TextView purpleCards;
    TextView blackCards;
    TextView whiteCards;
    TextView wildCards;
    Spinner color_spinner;
    Spinner number_spinner;
    Spinner wild_num_spinner;
    Button claimRoute;
    Button returnToGame;
//    ImageButton goToStats;
    String color = "";
    int numRegular = 0;
    int numWild = 0;
    int routeNum = -1;

    private String[] cardColors = { "red", "orange", "yellow", "green", "blue", "purple", "black", "white"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.claim_route_view, container, false);
        claimRoutePresenter = new ClaimRoutePresenter(getActivity());
        final Bundle routeBundle = getArguments();
        routeNum = routeBundle.getInt("routeNumber");

        title = (TextView) view.findViewById(R.id.claim_route_title);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);

        returnToGame= (Button)view.findViewById(R.id.claimRouteToGame);
        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claimRoutePresenter.switchToGame(getActivity());
            }
        });

        claimRoute= (Button)view.findViewById(R.id.claimRouteButton);
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claimRoutePresenter.claimRoute(getActivity(), routeNum, color, numRegular, numWild);
                if(ActiveGame.getInstance().getMyPlayer().getSelectedRoute()==null)
                {
                    claimRoutePresenter.switchToGame(getActivity());
                }
            }
        });

//        goToStats= (ImageButton)view.findViewById(R.id.claimToStats);
//        goToStats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                claimRoutePresenter.switchToStats(getActivity());
//            }
//        });


        routeInfo = (TextView) view.findViewById(R.id.routeInfo);
        String rtInfo = routeBundle.getString("start") + " --> " + routeBundle.getString("end") + " -- " + routeBundle.getString("color") + " -- " + routeBundle.getInt("length");
        routeInfo.setText(rtInfo);

        color_spinner = (Spinner) view.findViewById(R.id.color_spinner);
//        int indexColor =  //SessionData.getInstance().getLineColorIndex(SessionData.getInstance().getLifeStoryLineColor());
//        color_spinner.setSelection(indexColor);
        color_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        color = cardColors[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Must be overridden but we don't use it.
                    }
                });

        number_spinner = (Spinner) view.findViewById(R.id.number_spinner);
        number_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        numRegular = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Must be overridden but we don't use it.
                    }
                });

        wild_num_spinner = (Spinner) view.findViewById(R.id.wild_num_spinner);
        wild_num_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        numWild = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Must be overridden but we don't use it.
                    }
                });

        redCards = (TextView) view.findViewById(R.id.numRedCards);
        orangeCards = (TextView) view.findViewById(R.id.numOrangeCards);
        yellowCards = (TextView) view.findViewById(R.id.numYellowCards);
        greenCards = (TextView) view.findViewById(R.id.numGreenCards);
        blueCards = (TextView) view.findViewById(R.id.numBlueCards);
        purpleCards = (TextView) view.findViewById(R.id.numPurpleCards);
        blackCards = (TextView) view.findViewById(R.id.numBlackCards);
        whiteCards = (TextView) view.findViewById(R.id.numWhiteCards);
        wildCards = (TextView) view.findViewById(R.id.numWildCards);

        updateHand();
        return view;
    }

    public void updateHand() {
        Player player = ActiveGame.getInstance().getMyPlayer();
        redCards.setText(String.valueOf(player.getNumColorCards("red")));
        orangeCards.setText(String.valueOf(player.getNumColorCards("orange")));
        yellowCards.setText(String.valueOf(player.getNumColorCards("yellow")));
        greenCards.setText(String.valueOf(player.getNumColorCards("green")));
        blueCards.setText(String.valueOf(player.getNumColorCards("blue")));
        purpleCards.setText(String.valueOf(player.getNumColorCards("purple")));
        blackCards.setText(String.valueOf(player.getNumColorCards("black")));
        whiteCards.setText(String.valueOf(player.getNumColorCards("white")));
        wildCards.setText(String.valueOf(player.getNumColorCards("wild")));
    }
}
