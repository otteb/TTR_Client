package ObserverPattern;

import java.util.Observable;

public class TTR_Observable extends Observable {
    private static TTR_Observable theOneAndOnly = new TTR_Observable();
    public static TTR_Observable getInstance()
    {
        return theOneAndOnly;
    }

    private TTR_Observable(){}

    public void updateTurn(){
        setChanged();
        notifyObservers("turn");
    }

    //msg can be "faceUp" or "deck" or "destinations"
    public void updateCards(String msg) {
        setChanged();
        notifyObservers(msg);
        updateStats("stats");
    }

    /*
    msg can be "hand" or "stats"
    "hand" will updateStats the number of each color train card and total destination and train cards
    "stats" will updateStats all stats
    */
    public void updateStats(String msg) {
        setChanged();
        notifyObservers(msg);
    }

    public void updateChat() {
        setChanged();
        notifyObservers("chat");
    }

    public void updateHistory() {
        setChanged();
        notifyObservers("history");
    }

    public void createGame() {
        setChanged();
        notifyObservers("create");
    }

    public void joinGame() {
        setChanged();
        notifyObservers("join");
    }

    public void leaveGame() {
        setChanged();
        notifyObservers("leave");
    }

    public void startGame() {
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

    public void claimRoute() {
        setChanged();
        notifyObservers("claim");
    }

}
