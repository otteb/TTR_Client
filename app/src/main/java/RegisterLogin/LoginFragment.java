package RegisterLogin;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import activities.R;


public class LoginFragment extends Fragment {
    EditText username;
    EditText password;
    EditText ipAddress;
    EditText port;
    Button login;
    LoginRegisterPresenter loginRegisterPresenter;
    TextView register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginRegisterPresenter = new LoginRegisterPresenter(getActivity());
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        TextView title = (TextView) view.findViewById(R.id.login_title);
        AssetManager am = getContext().getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(am, "game_of_thrones.ttf");
        title.setTypeface(custom_font);

        username= (EditText)view.findViewById(R.id.editText_username);
        password= (EditText)view.findViewById(R.id.editText2_password);
        ipAddress = (EditText)view.findViewById(R.id.ipEditText);
        port = (EditText)view.findViewById(R.id.portEditText);

        login= (Button)view.findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterPresenter.login(getActivity(), username.getText().toString(), password.getText().toString(), port.getText().toString(), ipAddress.getText().toString());
            }
        });

        register= (TextView) view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterPresenter.switchToRegister(getActivity());
            }
        });


        return view;
    }

}
