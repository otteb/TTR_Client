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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import StatePattern.GameSetup;
import StatePattern.NotMyTurn;
import activities.R;

public class TrainCardsFragment extends Fragment {
    CardsPresenter cardsPresenter;
    LinearLayout card1;
    LinearLayout card2;
    LinearLayout card3;
    LinearLayout card4;
    LinearLayout card5;
    ArrayList<LinearLayout> cards;
    int selectedCard = 0;
    Button returnToGame;
    Button fromTable;
    Button fromDeck;
    Button skipTurn;
    TextView title;
    Player player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        player= ActiveGame.getInstance().getMyPlayer();
        final View view = inflater.inflate(R.layout.card_view, container, false);
        cards = new ArrayList<>();
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
        skipTurn = (Button) view.findViewById(R.id.cardViewSkipTurn);

        cardsPresenter = new CardsPresenter(getActivity());

        updateFaceUp();

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
                selectedCard = 0;
            }
        });

        fromTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCard != 0)
                {
                    cardsPresenter.drawTrainCardFromTable(selectedCard);
                    selectedCard = 0;
//                    cardCount++;
                }
            }
        });


        skipTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.skipTurn();
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof NotMyTurn)
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You must choose to keep or discard a destination card first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (selectedCard == 0) {
                        selectedCard = 1;
                        card1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                    } else if (selectedCard == 1) {
                        resetCard(0);
                        selectedCard = 0;
                    }
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof NotMyTurn)
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You must choose to keep or discard a destination card first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (selectedCard == 0) {
                        selectedCard = 2;
                        card2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                    } else if (selectedCard == 2) {
                        resetCard(1);
                        selectedCard = 0;
                    }
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof NotMyTurn)
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You must choose to keep or discard a destination card first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (selectedCard == 0) {
                        selectedCard = 3;
                        card3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                    } else if (selectedCard == 3) {
                        resetCard(2);
                        selectedCard = 0;
                    }
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof NotMyTurn)
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You must choose to keep or discard a destination card first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (selectedCard == 0) {
                        selectedCard = 4;
                        card4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                    } else if (selectedCard == 4) {
                        resetCard(3);
                        selectedCard = 0;
                    }
                }
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Client.getInstance().getCurState() instanceof NotMyTurn)
                {
                    Toast.makeText(getActivity(), "It's not your turn!", Toast.LENGTH_SHORT).show();
                }
                else if(Client.getInstance().getCurState() instanceof GameSetup)
                {
                    Toast.makeText(getActivity(), "You must choose to keep or discard a destination card first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (selectedCard == 0)// && Client.getInstance().getCurState() instanceof MyTurn)
                    {
                        selectedCard = 5;
                        card5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.got_highlight));
                    } else if (selectedCard == 5) {
                        resetCard(4);
                        selectedCard = 0;
                    }
                }
            }
        });
        return view;
    }

//    public void cardAlgorithm(LinearLayout card, int cardNum)
//    {
//        if (destinationCardSetup)
//        {
//            if(selectedCard != 0)
//            {
//                cards.get(selectedCard).setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rainbow));
//            }
//            selectedCard=cardNum;
//            card.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.got_highlight));
//        }
//        else
//        {
//            //if(selectedCard != 0 == card2Chosen !=0)
//        }
//    }

    public void updateFaceUp() {
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
                case "wild":
                    cards.get(i).setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rainbow));
                    break;

                default:
                    break;
            }
        }
    }

    private void resetCard(int index) {
        switch (ActiveGame.getInstance().getFaceUpCards().get(index).getColor()){
            case "purple":
                cards.get(index).setBackgroundColor(Color.parseColor("#DDA0DD"));
                break;
            case "white":
                cards.get(index).setBackgroundColor(Color.parseColor("#FFFAFA"));
                break;
            case "blue":
                cards.get(index).setBackgroundColor(Color.parseColor("#4169E1"));
                break;
            case "yellow":
                cards.get(index).setBackgroundColor(Color.parseColor("#FFD700"));
                break;
            case "orange":
                cards.get(index).setBackgroundColor(Color.parseColor("#FF8C00"));
                break;
            case "black":
                cards.get(index).setBackgroundColor(Color.parseColor("#000000"));
                break;
            case "red":
                cards.get(index).setBackgroundColor(Color.parseColor("#A52A2A"));
                break;
            case "green":
                cards.get(index).setBackgroundColor(Color.parseColor("#3CB371"));
                break;
            case "wild":
                cards.get(index).setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rainbow));
                break;

            default:
                break;
        }
    }

    public void displayCard() {
        String color = ActiveGame.getInstance().getLastCard().getColor();
        Toast.makeText(getActivity(), "You drew a " + color + " train card", Toast.LENGTH_SHORT).show();
    }
}
