package game.EndGame;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IEndGamePresenter;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;

/**
 * Created by brianotte on 3/24/18.
 */

public class EndGamePresenter implements IEndGamePresenter, Observer {
    //Properties:
    public Context context;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();
    private MainActivity mainActivity;

    //create Contructor that is able to handle the context:
    EndGamePresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //This is where navigation buttons should be added in the future - I don't think we really want to do anything here correct??

    //this is an Observable-specific operation:
    @Override
    public void update(Observable observable, Object o) {
        //I honestly do not know if anything should need to be updated in here:
        //the EndGameFragment should be static
    }
}
