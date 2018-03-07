package Models.Gameplay;

import java.util.ArrayList;
import java.util.List;

import Models.Cards.DestinationDeck;
import Models.Cards.TrainCard;
import Models.Cards.TrainDeck;
import Models.Client;

public class Game {

    private String id;
    private ArrayList<Player> players; //list of players
    private ArrayList<String> chats;  //List of all chats (format of "username: msg" )
    private List<Route> Routes;
    private List<String> Cities;
    private List<TrainCard> faceUpCards;

//    private TrainDeck trainDeck;
//    private DestinationDeck destinationDeck;
    private boolean active = false;    //Has the game started

    private GameHistory history;

    public Game(){
        players = new ArrayList<>();
        chats = new ArrayList<>();
        history = new GameHistory();
    }

    //constructor allowing to instantiate new game with given id
    public Game(String id){
        this.id = id;
        players = new ArrayList<>();
        chats = new ArrayList<>();
        history = new GameHistory();
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

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isJoinable() {
        return (players.size() < 5 && !active);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addChatMessage(String message){
        chats.add(message);
    }

    public ArrayList<String> getChats() {
        return chats;
    }

    public void setChats(ArrayList<String> chat) {
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
}
