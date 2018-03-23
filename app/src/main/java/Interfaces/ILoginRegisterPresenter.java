package Interfaces;

import android.content.Context;

public interface ILoginRegisterPresenter {
        void login(Context c, String username, String password);

        void switchToRegister(Context c);

        void register(Context c, String username, String password, String confpswd);
}
