package Interfaces;

import android.content.Context;
import Models.Result;

/**
 * Created by fjameson on 2/12/18.
 */

public interface ILoginRegisterPresenter {



        public void login(Context c, String username, String password);

        public void switchToRegister(Context c);

        public void register(Context c, String username, String password, String confpswd);




}
