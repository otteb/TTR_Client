package game.Chat;

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

public class ChatFragment extends Fragment {
    EditText message;
    Button chat;
    ChatPresenter chatPresenter;
    ImageButton returnToStats;
    ImageButton goToGameHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatPresenter = new ChatPresenter(getActivity());
        View view = inflater.inflate(R.layout.chat, container, false);
        chat= (Button) view.findViewById(R.id.addChat);
        message = (EditText)view.findViewById(R.id.chatMessage);
        returnToStats = (ImageButton)view.findViewById(R.id.chatToStats);
        returnToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.switchToStats(getActivity());
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.addChat(getActivity(), message.getText().toString());
                message.setText("");
            }
        });
        return view;
    }
}
