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
import Models.Gameplay.Game;
import Models.Gameplay.Player;
import StatePattern.DrewDestCards;
import StatePattern.GameSetup;
import StatePattern.MyTurn;
import StatePattern.NotMyTurn;
import activities.R;

/**
 * Created by ferrell3 on 3/22/18.
 */

public class DestinationCardsFragment extends Fragment {
    CardsPresenter cardsPresenter;
    LinearLayout card2;
    TextView card2Start;
    TextView card2End;
    LinearLayout card3;
    TextView card3Start;
    TextView card3End;
    LinearLayout card4;
    TextView card4Start;
    TextView card4End;
    ArrayList<LinearLayout> cards;
    int card1Chosen = 0;
    int card2Chosen = 0;
    int cardCount = 0;
    Button returnToGame;
    Button drawCards;
    Button sendDCardBack;
    TextView title;
//    boolean destinationCardSetup;
    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        player = ActiveGame.getInstance().getMyPlayer();
        final View view = inflater.inflate(R.layout.destination_card_view, container, false);
        cards = new ArrayList<>();
        card2 = (LinearLayout) view.findViewById(R.id.cardViewCard2);
        card2Start = (TextView) view.findViewById(R.id.cardViewCard2StartCity);
        card2End = (TextView) view.findViewById(R.id.cardViewCard2EndCity);
        cards.add(card2);
        card3 = (LinearLayout) view.findViewById(R.id.cardViewCard3);
        card3Start = (TextView) view.findViewById(R.id.cardViewCard3StartCity);
        card3End = (TextView) view.findViewById(R.id.cardViewCard3EndCity);
        cards.add(card3);
        card4 = (LinearLayout) view.findViewById(R.id.cardViewCard4);
        card4Start = (TextView) view.findViewById(R.id.cardViewCard4StartCity);
        card4End = (TextView) view.findViewById(R.id.cardViewCard4EndCity);
        cards.add(card4);
        title = (TextView) view.findViewById(R.id.cardViewName);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);
        returnToGame = (Button) view.findViewById(R.id.cardViewToGame);
        sendDCardBack = (Button) view.findViewById(R.id.cardViewDCardToDeck);
        drawCards = (Button) view.findViewById(R.id.cardViewDrawDests);

        final Bundle currentPlayer = getArguments();
//        destinationCardSetup = currentPlayer.getBoolean("destinationCardSetup");
        cardsPresenter = new CardsPresenter(getActivity());
        title.setText(R.string.dest_cards);
        updateView();
//        if (Client.getInstance().getCurState() instanceof GameSetup)
//        {
//            updateDestinationCards();
//        }
//        else if(Client.getInstance().getCurState() instanceof MyTurn)
//        {
//            //display return to game button and draw destination cards button
//            //hide discard button and cards
//            sendDCardBack.setVisibility(View.GONE);
//            card2.setVisibility(View.GONE);
//            card3.setVisibility(View.GONE);
//            card4.setVisibility(View.GONE);
//        }
//        else if(Client.getInstance().getCurState() instanceof DrewDestCards)
//        {
//            updateDestinationCards();
//        }
//        else //NotMyTurn or //Drew1Card
//        {
//            //only display return to game button
//            sendDCardBack.setVisibility(View.GONE);
//            drawCards.setVisibility(View.GONE);
//            card2.setVisibility(View.GONE);
//            card3.setVisibility(View.GONE);
//            card4.setVisibility(View.GONE);
//        }

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
                cardsPresenter.sendBackDestinationCard(getActivity(),card1Chosen);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen == 0) {
                    card1Chosen = 2;
                    card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (card1Chosen == 2) {
                    resetCard(0);
                    card1Chosen = 0;
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen == 0) {
                    card1Chosen = 3;
                    card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (card1Chosen == 3) {
                    resetCard(1);
                    card1Chosen = 0;
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen == 0) {
                    card1Chosen = 4;
                    card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                } else if (card1Chosen == 4) {
                    resetCard(2);
                    card1Chosen = 0;
                }
            }
        });

        return view;
    }

    public void updateDestinationCards() {
        card2.setBackgroundColor(Color.parseColor("#F5F5DC"));
        card2Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(0).getCity1());
        card2End.setText(ActiveGame.getInstance().getDrawnDestinations().get(0).getCity2());
        card3.setBackgroundColor(Color.parseColor("#F5F5DC"));
        card3Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(1).getCity1());
        card3End.setText(ActiveGame.getInstance().getDrawnDestinations().get(1).getCity2());
        card4.setBackgroundColor(Color.parseColor("#F5F5DC"));
        card4Start.setText(ActiveGame.getInstance().getDrawnDestinations().get(2).getCity1());
        card4End.setText(ActiveGame.getInstance().getDrawnDestinations().get(2).getCity2());
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
        }
        else if(Client.getInstance().getCurState() instanceof DrewDestCards)
        {
            card2.setVisibility(View.VISIBLE);
            card3.setVisibility(View.VISIBLE);
            card4.setVisibility(View.VISIBLE);
            sendDCardBack.setVisibility(View.VISIBLE);
            updateDestinationCards();
        }
        else //if(Client.getInstance().getCurState() instanceof NotMyTurn)
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
