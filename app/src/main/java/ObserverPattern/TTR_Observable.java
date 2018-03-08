package ObserverPattern;

import java.util.Observable;

public class TTR_Observable extends Observable {
    private static TTR_Observable theOneAndOnly = new TTR_Observable();
    public static TTR_Observable getInstance()
    {
        return theOneAndOnly;
    }

    private TTR_Observable(){}

    public void updateChat () {
        setChanged();
        notifyObservers("chat");
    }

    public void updateHistory () {
        setChanged();
        notifyObservers("updateHistory");
    }

    public void createGame () {
        setChanged();
        notifyObservers("create");
    }

    public void joinGame () {
        setChanged();
        notifyObservers("join");
    }

    public void leaveGame () {
        setChanged();
        notifyObservers("leave");
    }

    public void startGame () {
        setChanged();
        notifyObservers("start");
    }

    public void sendMessage(String m) {
        setChanged();
        notifyObservers(m);
    }

    public void login() {
        setChanged();
        notifyObservers("login");
    }

}
