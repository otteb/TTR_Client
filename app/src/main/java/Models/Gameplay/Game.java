package Models.Gameplay;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Cards.TrainCard;
import Models.Client;
import activities.R;

public class Game {

    private String id;
    private ArrayList<Player> players; //list of players
    private ArrayList<TrainCard> faceUpCards;
    private GameHistory history;
    private HashMap<Integer, Route> routesMap;
    private HashMap<Integer, Route> masterMap;
    private HashMap<Integer, Route> claimedRoutes;
    private boolean active = false;    //Has the game started

    public Game(){
        players = new ArrayList<>();
        history = new GameHistory();
        routesMap = new HashMap<>();
    }

    //constructor allowing to instantiate new game with given id
    public Game(String id){
        this.id = id;
        players = new ArrayList<>();
        history = new GameHistory();
        routesMap = new HashMap<>();
        masterMap= new HashMap<>();
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

//    public void addChat(String message){
//        chats.add(message);
//    }
//
//    public ArrayList<String> getChats() {
//        return chats;
//    }
//
//    public void setChats(ArrayList<String> chat) {
//        this.chats = chat;
//    }
//
//    public List<Route> getRoutes() {
//        return Routes;
//    }
//
//    public void setRoutes(List<Route> routes) {
//        Routes = routes;
//    }
//
//    public List<String> getCities() {
//        return Cities;
//    }
//
//    public void setCities(List<String> cities) {
//        Cities = cities;
//    }

    public ArrayList<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(ArrayList<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public GameHistory getHistory() {
        return history;
    }

    public void setHistory(GameHistory history) {
        this.history = history;
    }

    public HashMap<Integer, Route> getRoutesMap() {
        return routesMap;
    }

    public void setRoutesMap(HashMap<Integer, Route> routesMap) {
        this.routesMap = routesMap;
    }

    public HashMap<Integer, Route> getMasterMap() {
        return masterMap;
    }

    public void setMasterMap(HashMap<Integer, Route> masterMap) {
        this.masterMap = masterMap;
    }

    public HashMap<Integer, Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(HashMap<Integer, Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }
}
