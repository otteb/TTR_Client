package registerLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import activities.R;
import Models.Result;
import lobby.LobbyFragment;


/**
 * Created by fjameson on 2/2/18.
 */
public class RegisterFragment extends Fragment {
    EditText username;
    EditText password;
    EditText confpswd;
    Button register;
    TextView backToLogin;
    LoginRegisterPresenter loginRegisterPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginRegisterPresenter = new LoginRegisterPresenter(getContext());
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        username= (EditText)view.findViewById(R.id.editText_username1);
        password= (EditText)view.findViewById(R.id.editText2_password1);
        confpswd= (EditText)view.findViewById(R.id.editText2_passwordconf1);
        register= (Button)view.findViewById(R.id.button2_register1);
        backToLogin= (TextView) view.findViewById(R.id.login1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterPresenter.register(getActivity(), username.getText().toString(), password.getText().toString(), confpswd.getText().toString());
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterPresenter.switchToLogin(getActivity());
            }
        });

        return view;
    }
}

