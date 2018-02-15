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
import Models.Result;
import lobby.LobbyFragment;

import Activities.R;

/**
 * Created by fjameson on 2/2/18.
 */

public class LoginFragment extends Fragment {
    EditText username;
    EditText password;
    Button login;
    LoginRegisterPresentor l;
    TextView register;

    public LoginFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        l = new LoginRegisterPresentor(getContext());
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        username= (EditText)view.findViewById(R.id.editText_username);
        password= (EditText)view.findViewById(R.id.editText2_password);

        login= (Button)view.findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result r = l.login(getActivity(), username.getText().toString(), password.getText().toString());
                if(r != null)
                {
                    FragmentManager headfrag = getActivity().getSupportFragmentManager();
                    Fragment fragment = new LobbyFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username.getText().toString());
                    bundle.putString("password", password.getText().toString());
                    bundle.putString("authToken", r.getAuthToken());
                    fragment.setArguments(bundle);
                    headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
                }

            }
        });

        register= (TextView) view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.switchToRegister(getActivity());
                FragmentManager headfrag = getActivity().getSupportFragmentManager();
                Fragment fragment = new RegisterFragment();
                headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
            }
        });


        return view;
    }

    public void switchToLobby()
    {
        FragmentManager headfrag = getActivity().getSupportFragmentManager();
        Fragment fragment = new LobbyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", username.getText().toString());
        bundle.putString("password", password.getText().toString());
        //bundle.putString("authToken", r.getAuthToken());
        fragment.setArguments(bundle);
        headfrag.beginTransaction().replace(R.id.activity_main, fragment).commit();
    }


}
