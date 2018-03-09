package game.Cards;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Models.Gameplay.ActiveGame;
import RegisterLogin.LoginRegisterPresenter;
import activities.R;

/**
 * Created by brianotte on 3/7/18.
 */

public class CardsFragment extends Fragment {
    CardsPresenter cardsPresenter;
    LinearLayout card1;
    LinearLayout card2;
    LinearLayout card3;
    LinearLayout card4;
    LinearLayout card5;
    LinearLayout temp;
    ArrayList<LinearLayout> cards;
    int card1Chosen=0;
    int card2Chosen=0;
    Button returnToGame;
    Button fromTable;
    Button fromDeck;
    Button sendDCardBack;
    TextView title;
    boolean destinationCardSetup = true;
    final Bundle currentPlayer = getArguments();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.card_view, container, false);
        cards = new ArrayList<LinearLayout>();
        card1=(LinearLayout)view.findViewById(R.id.cardViewCard1);
        cards.add(card1);
        card2=(LinearLayout)view.findViewById(R.id.cardViewCard2);
        cards.add(card2);
        card3=(LinearLayout)view.findViewById(R.id.cardViewCard3);
        cards.add(card3);
        card4=(LinearLayout)view.findViewById(R.id.cardViewCard4);
        cards.add(card4);
        card5=(LinearLayout)view.findViewById(R.id.cardViewCard5);
        cards.add(card5);
        title= (TextView)view.findViewById(R.id.cardViewName);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);
        returnToGame= (Button)view.findViewById(R.id.cardViewToGame);
        fromDeck= (Button)view.findViewById(R.id.cardViewFromDeck);
        fromTable= (Button)view.findViewById(R.id.cardViewFromTable);
        sendDCardBack= (Button)view.findViewById(R.id.cardViewDCardToDeck);

        final Bundle currentPlayer = getArguments();
        destinationCardSetup= currentPlayer.getBoolean("destinationCardSetup");
        cardsPresenter = new CardsPresenter(getActivity());
        if (destinationCardSetup==true)
        {
            title.setText("Destination Cards");
            card1.setVisibility(View.GONE);
            card5.setVisibility(View.GONE);
            fromTable.setVisibility(View.GONE);
            fromDeck.setVisibility(View.GONE);
            card2.setBackgroundColor(Color.parseColor("#F5F5DC"));
            card3.setBackgroundColor(Color.parseColor("#F5F5DC"));
            card4.setBackgroundColor(Color.parseColor("#F5F5DC"));

        }
        else{
            title.setText("Face Up Train Cards");
            for(int i = 0; i < cards.size(); i++)
            {
                switch (ActiveGame.getInstance().getFaceUpCards().get(i).getColor()){
                    case "purple":
                        cards.get(i).setBackgroundColor(Color.parseColor("#DDA0DD"));
                        break;
                    case "white":
                        cards.get(i).setBackgroundColor(Color.parseColor("#FFFAFA"));
                        break;
                    case "blue":
                        cards.get(i).setBackgroundColor(Color.parseColor("#4169E1"));
                        break;
                    case "yellow":
                        cards.get(i).setBackgroundColor(Color.parseColor("#FFD700"));
                        break;
                    case "orange":
                        cards.get(i).setBackgroundColor(Color.parseColor("#FF8C00"));
                        break;
                    case "black":
                        cards.get(i).setBackgroundColor(Color.parseColor("#000000"));
                        break;
                    case "red":
                        cards.get(i).setBackgroundColor(Color.parseColor("#A52A2A"));
                        break;
                    case "green":
                        cards.get(i).setBackgroundColor(Color.parseColor("#3CB371"));
                        break;

                    default:
                        break;
                }
            }
//            card1.setBackgroundColor(Color);
            sendDCardBack.setVisibility(View.GONE);
        }
        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.switchToGame(getActivity());
            }
        });

       fromDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.drawTrainCardFromDeck();
            }
        });

        fromTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.drawTrainCardFromTable(card1Chosen); //, card2Chosen);
                cards.get(card1Chosen-1).setBackgroundColor(Color.parseColor("#4169E1"));

            }
        });

        sendDCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.sendBackDestinationCard(getActivity(),card1Chosen);
            }
        });
        //card1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen== 0)
                {
                    card1Chosen=1;
                    card1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
                else if (card2Chosen ==0 && destinationCardSetup == false) {
                    card2Chosen=1;
                    card1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen== 0)
                {
                    card1Chosen=2;
                    card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
                else if (card2Chosen ==0 && destinationCardSetup == false) {
                    card2Chosen=2;
                    card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen== 0)
                {
                    card1Chosen=3;
                    card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
                else if (card2Chosen ==0 && destinationCardSetup == false) {
                    card2Chosen=3;
                    card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen== 0)
                {
                    card1Chosen=4;
                    card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
                else if (card2Chosen ==0 && destinationCardSetup == false) {
                    card2Chosen=4;
                    card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen== 0)
                {
                    card1Chosen=5;
                    card5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
                else if (card2Chosen ==0 && destinationCardSetup == false) {
                    card2Chosen=5;
                    card5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                }
            }
        });
        return view;
    }

    public void cardAlgorithm(LinearLayout card, int cardNum)
    {
        if (destinationCardSetup == true)
        {
            if(card1Chosen != 0)
            {
                cards.get(card1Chosen).setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rainbow));
            }
            card1Chosen=cardNum;
            card.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.got_highlight));
        }
        else
        {
            //if(card1Chosen != 0 == card2Chosen !=0)
        }
    }

}
