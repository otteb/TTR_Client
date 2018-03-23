package RegisterLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import activities.R;

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
        username= view.findViewById(R.id.editText_username1);
        password= view.findViewById(R.id.editText2_password1);
        confpswd= view.findViewById(R.id.editText2_passwordconf1);
        register= view.findViewById(R.id.button2_register1);
        backToLogin= view.findViewById(R.id.login1);

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

