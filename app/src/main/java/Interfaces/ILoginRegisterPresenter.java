package Interfaces;

import android.content.Context;
import Models.Result;

public interface ILoginRegisterPresenter {



        public void login(Context c, String username, String password);

        public void switchToRegister(Context c);

        public void register(Context c, String username, String password, String confpswd);




}
