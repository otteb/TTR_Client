package game.Cards;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    int card1Chosen=0;
    int card2Chosen=0;
    Button returnToGame;
    Button fromTable;
    Button fromDeck;
    Button sendDCardBack;
    TextView title;
    boolean destinationCardSetup = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_view, container, false);
        card1=(LinearLayout)view.findViewById(R.id.cardViewCard1);
        card2=(LinearLayout)view.findViewById(R.id.cardViewCard2);
        card3=(LinearLayout)view.findViewById(R.id.cardViewCard3);
        card4=(LinearLayout)view.findViewById(R.id.cardViewCard4);
        card5=(LinearLayout)view.findViewById(R.id.cardViewCard5);
        title= (TextView)view.findViewById(R.id.cardViewName);
        returnToGame= (Button)view.findViewById(R.id.cardViewToGame);
        fromDeck= (Button)view.findViewById(R.id.cardViewFromDeck);
        fromTable= (Button)view.findViewById(R.id.cardViewFromTable);
        sendDCardBack= (Button)view.findViewById(R.id.cardViewDCardToDeck);
        cardsPresenter = new CardsPresenter(getActivity());

        if (destinationCardSetup==true)
        {
            title.setText("Destination Cards");
            card1.setVisibility(View.GONE);
            card5.setVisibility(View.GONE);
            fromTable.setVisibility(View.GONE);
            fromDeck.setVisibility(View.GONE);

        }
        else{
            title.setText("Face Up Train Cards");
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

            }
        });

        fromTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sendDCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.sendBackDestinationCard(getActivity(),card1Chosen);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card1Chosen=1;
                if (card1Chosen!= 0 && destinationCardSetup ==false)
                {
                    card2Chosen=1;
                }
                else {
                    card1Chosen=1;
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen!= 0 && destinationCardSetup ==false)
                {
                    card2Chosen=2;
                }
                else {
                    card1Chosen=2;
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen!= 0 && destinationCardSetup ==false)
                {
                    card2Chosen=3;
                }
                else {
                    card1Chosen=3;
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen!= 0 && destinationCardSetup ==false)
                {
                    card2Chosen=4;
                }
                else {
                    card1Chosen=4;
                }

            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card1Chosen!= 0 && destinationCardSetup ==false)
                {
                    card2Chosen=5;
                }
                else {
                    card1Chosen=5;
                }
            }
        });
        return view;
    }
}
