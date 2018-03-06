package Models.Gameplay;

import java.util.ArrayList;
import java.util.List;

import Models.Cards.DestinationDeck;
import Models.Cards.TrainCard;
import Models.Cards.TrainDeck;

public class Game {

    private String id;
    private ArrayList<Player> players; //list of players' usernames
    private ArrayList<String> chats;  //List of all chats (format of "username: msg" )
    private List<Route> Routes;
    private List<String> Cities;
//    private List<TrainCard> trainCards; //This is for the face up train cards
//    private TrainDeck trainDeck;
//    private DestinationDeck destinationDeck;
    private boolean active = false;    //Has the game started

    private GameHistory history;

    public Game(){
        players = new ArrayList<>();
        chats = new ArrayList<>();
        history = new GameHistory();
//        trainDeck = new TrainDeck();
//        destinationDeck = new DestinationDeck();
    }

    //constructor allowing to instantiate new game with given id
    public Game(String id){
        this.id = id;
        players = new ArrayList<>();
        chats = new ArrayList<>();
        history = new GameHistory();
//        trainDeck = new TrainDeck();
//        destinationDeck = new DestinationDeck();
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

    public ArrayList<Player> getPlayers() {
        return players;
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

//    public TrainDeck getTrainDeck() {
//        return trainDeck;
//    }
//
//    public void setTrainDeck(TrainDeck trainDeck) {
//        this.trainDeck = trainDeck;
//    }
//
//    public DestinationDeck getDestinationDeck() {
//        return destinationDeck;
//    }
//
//    public void setDestinationDeck(DestinationDeck destinationDeck) {
//        this.destinationDeck = destinationDeck;
//    }
}
