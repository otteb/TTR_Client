package Models.Gameplay;

import java.util.ArrayList;
import java.util.List;

import Client_Server_Communication.Poller;
import Models.Cards.TrainCard;
import Models.Client;
import ObserverPattern.TTR_Observable;

public class ActiveGame {

    private static ActiveGame theGame = new ActiveGame();
    public static ActiveGame getInstance()
    {
        return theGame;
    }

    private Poller poller = new Poller();
    private String id;
    private GameHistory history;
    private ArrayList<Player> players; //list of players
    private ArrayList<Chat> chats;  //List of all chats (format of "username: msg" )
    private List<TrainCard> faceUpCards;
    private List<Route> Routes;
    private List<String> Cities;
    private int activeGameCMDNum = 0;

    private ActiveGame(){
        players = new ArrayList<>();
        chats = new ArrayList<>();
        history = new GameHistory();
    }

    public void incActiveGameCMDNum(int num){
        this.activeGameCMDNum += num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean addPlayer(String username){
        Player p = new Player(username);
        return addPlayer(p);
    }

    public boolean addPlayer(Player p){
        return players.size() < 5 && !players.contains(p) && players.add(p);
    }

    public boolean isValidPlayer(String username){
        for(Player p : players)
        {
            if(p.getName().equals(username))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String username){
        for(Player p: players){
            if(p.getName().equals(username)){
                return p;
            }
        }
        return null;
    }

    public Player getMyPlayer(){
        for(Player p: players){
            if(p.getName().equals(Client.getInstance().getUserName())){
                return p;
            }
        }
        return null;
    }

    public Player getOtherPlayer() {
        for(Player p : players) {
            if(!p.getName().equals(Client.getInstance().getUserName())){
                return p;
            }
        }
        return null;
    }

    //get the player whose turn it is
    public Player getActivePlayer() {
        for(Player p : players)
        {
            if(p.isTurn())
            {
                return p;
            }
        }
        return null;
    }

    public void incTurn() {
        for(int i = 1; i < players.size(); i++)
        {
            if (players.get(i-1).isTurn())
            {
                players.get(i-1).setTurn(false);
                players.get(i).setTurn(true);
                break;
            }
            else if(i == (players.size()-1))
            {
                if(players.get(i).isTurn())
                {
                    players.get(i).setTurn(false);
                    players.get(0).setTurn(true);
                }
            }
        }
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addChatMessage(Chat chat){
        chats.add(chat);
        TTR_Observable.getInstance().updateChat();
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chat) {
        this.chats = chat;
    }

    public List<Route> getRoutes() {
        return Routes;
    }

    public void setRoutes(List<Route> routes) {
        Routes = routes;
    }

    public List<String> getCities() {
        return Cities;
    }

    public void setCities(List<String> cities) {
        Cities = cities;
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public GameHistory getHistory() {
        return history;
    }

    public void setHistory(GameHistory history) {
        this.history = history;
    }

    public Poller getPoller() {
        return poller;
    }

    public int getActiveGameCMDNum() {
        return activeGameCMDNum;
    }

    public void setActiveGameCMDNum(int activeGameCMDNum) {
        this.activeGameCMDNum = activeGameCMDNum;
    }
}
