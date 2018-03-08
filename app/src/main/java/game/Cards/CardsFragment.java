package game.Cards;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import RegisterLogin.LoginRegisterPresenter;
import activities.R;

/**
 * Created by brianotte on 3/7/18.
 */

public class CardsFragment extends Fragment {
    EditText message;
    Button chat;
    ImageButton returnToStats;
    ImageButton goToGameHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_view, container, false);
        return view;
    }
}
