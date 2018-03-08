package game.Cards;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Models.Result;
import activities.R;
import game.GameFragment;

/**
 * Created by brianotte on 3/7/18.
 */

public class CardsFragment extends Fragment {

    Button returnToGame;
    CardsPresenter cardsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final CardsPresenter  cardsPresenter = new CardsPresenter(getContext());
        View view = inflater.inflate(R.layout.card_view, container, false);

        returnToGame= (Button)view.findViewById(R.id.statsToGame);
        returnToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardsPresenter.switchToGame(getActivity());

            }
        });

        return view;

    }
}
