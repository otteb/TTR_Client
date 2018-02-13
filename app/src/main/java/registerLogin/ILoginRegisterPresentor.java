package registerLogin;

import android.content.Context;
import Models.Result;

/**
 * Created by fjameson on 2/12/18.
 */

public interface ILoginRegisterPresentor {



        public Result login(Context c, String username, String password);

        public void switchToRegister(Context c);

        public Result register(Context c, String username, String password, String confpswd);




}
