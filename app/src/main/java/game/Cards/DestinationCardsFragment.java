package game.Cards;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import StatePattern.DrewDestCards;
import StatePattern.GameSetup;
import StatePattern.MyTurn;
import activities.R;

/**
 * Created by ferrell3 on 3/22/18.
 */

public class DestinationCardsFragment extends Fragment {
    CardsPresenter cardsPresenter;
    LinearLayout card2;
    TextView card2Start;
    TextView card2End;
    TextView card2Points;
    LinearLayout card3;
    TextView card3Start;
    TextView card3End;
    TextView card3Points;
    LinearLayout card4;
    TextView card4Start;
    TextView card4End;
    TextView card4Points;
    ArrayList<LinearLayout> cards;
    int selected1 = 0;
    int selected2 = 0;
    Button returnToGame;
    Button drawCards;
    Button sendDCardBack;
    TextView title;
    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        player = ActiveGame.getInstance().getMyPlayer();
        final View view = inflater.inflate(R.layout.destination_card_view, container, false);
        cards = new ArrayList<>();
        card2 = (LinearLayout) view.findViewById(R.id.cardViewCard2);
        card2Start = (TextView) view.findViewById(R.id.cardViewCard2StartCity);
        card2End = (TextView) view.findViewById(R.id.cardViewCard2EndCity);
        card2Points = (TextView) view.findViewById(R.id.cardViewCard2Points);
        cards.add(card2);
        card3 = (LinearLayout) view.findViewById(R.id.cardViewCard3);
        card3Start = (TextView) view.findViewById(R.id.cardViewCard3StartCity);
        card3End = (TextView) view.findViewById(R.id.cardViewCard3EndCity);
        card3Points = (TextView) view.findViewById(R.id.cardViewCard3Points);
        cards.add(card3);
        card4 = (LinearLayout) view.findViewById(R.id.cardViewCard4);
        card4Start = (TextView) view.findViewById(R.id.cardViewCard4StartCity);
        card4End = (TextView) view.findViewById(R.id.cardViewCard4EndCity);
        card4Points = (TextView) view.findViewById(R.id.cardViewCard4Points);
        cards.add(card4);
        title = (TextView) view.findViewById(R.id.cardViewName);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);
        returnToGame = (Button) view.findViewById(R.id.cardViewToGame);
        sendDCardBack = (Button) view.findViewById(R.id.cardViewDCardToDeck);
        drawCards = (Button) view.findViewById(R.id.cardViewDrawDests);

        cardsPresenter = new CardsPresenter(getActivity());
        title.setText(R.string.dest_cards);
        updateView();

        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.switchToGame(getActivity());
            }
        });

        drawCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You cannot draw any more cards this turn", Toast.LENGTH_SHORT).show();
                }
                cardsPresenter.drawDestinationCards();
            }
        });

        sendDCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected2 == 0) {
                    cardsPresenter.discardDestCard(getActivity(), selected1);
                } else if (selected1 == 0) { //selected2 != 0 && selected1 == 0 //which means that only one is selected, but it is selected2
                    cardsPresenter.discardDestCard(getActivity(), selected2);
                } else {
                    cardsPresenter.discard2DestCards(getActivity(), selected1, selected2);
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected1 == 0) {
                    selected1 = 2;
                    card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (selected1 == 2) {
                    resetCard(0);
                    selected1 = 0;
                } else if(selected2 == 0 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    selected2 = 2;
                    card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if(selected2 == 2 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    resetCard(0);
                    selected2 = 0;
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected1 == 0)
                {
                    selected1 = 3;
                    card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (selected1 == 3) {
                    resetCard(1);
                    selected1 = 0;
                }else if(selected2 == 0 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    selected2 = 3;
                    card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if(selected2 == 3 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    resetCard(1);
                    selected2 = 0;
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected1 == 0) {
                    selected1 = 4;
                    card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (selected1 == 4) {
                    resetCard(2);
                    selected1 = 0;
                }else if(selected2 == 0 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    selected2 = 4;
                    card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if(selected2 == 4 && Client.getInstance().getCurState() instanceof DrewDestCards) {
                    resetCard(2);
                    selected2 = 0;
                }
            }
        });

        return view;
    }

    public void updateDestinationCards() {
        if(!ActiveGame.getInstance().getDrawnDestinations().isEmpty()) {

            card2.setBackgroundColor(Color.parseColor("#F5F5DC"));
            card2Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(0).getCity1());
            card2End.setText(ActiveGame.getInstance().getDrawnDestinations().get(0).getCity2());
            card2Points.setText(String.valueOf(ActiveGame.getInstance().getDrawnDestinations().get(0).getPoints()));

            card3.setBackgroundColor(Color.parseColor("#F5F5DC"));
            card3Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(1).getCity1());
            card3End.setText(ActiveGame.getInstance().getDrawnDestinations().get(1).getCity2());
            card3Points.setText(String.valueOf(ActiveGame.getInstance().getDrawnDestinations().get(1).getPoints()));

            card4.setBackgroundColor(Color.parseColor("#F5F5DC"));
            card4Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(2).getCity1());
            card4End.setText(ActiveGame.getInstance().getDrawnDestinations().get(2).getCity2());
            card4Points.setText(String.valueOf(ActiveGame.getInstance().getDrawnDestinations().get(2).getPoints()));
        }
    }

    public void updateView() {
        if(Client.getInstance().getCurState() instanceof MyTurn)
        {
            sendDCardBack.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            card4.setVisibility(View.GONE);
        }
        else if(Client.getInstance().getCurState() instanceof GameSetup)
        {
            updateDestinationCards();
            drawCards.setVisibility(View.GONE);
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards)
        {
            card2.setVisibility(View.VISIBLE);
            card3.setVisibility(View.VISIBLE);
            card4.setVisibility(View.VISIBLE);
            sendDCardBack.setVisibility(View.VISIBLE);
            updateDestinationCards();
        }
        else
        {
            sendDCardBack.setVisibility(View.GONE);
            drawCards.setVisibility(View.GONE);
            card2.setVisibility(View.GONE);
            card3.setVisibility(View.GONE);
            card4.setVisibility(View.GONE);
        }

    }

    private void resetCard(int index) {
        cards.get(index).setBackgroundColor(Color.parseColor("#F5F5DC"));
    }

//    public void displayTurn() {
//        Toast.makeText(getActivity(), "It's " + ActiveGame.getInstance().getActivePlayer() + "'s turn", Toast.LENGTH_SHORT).show();
//    }
}
