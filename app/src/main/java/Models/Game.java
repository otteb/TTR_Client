package Models;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    //    private Map<String,String> players; // not sure what the key and value are
    private ArrayList<String> players; //list of players' usernames
    private boolean joinable;

    public Game(){
        players = new ArrayList<>();
    }

    //constructor allowing to instantiate new game with given id
    public Game(String id){
        this.id = id;
        players = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Map<String, String> getPlayers() {
//        return players;
//    }

//    public void setPlayers(Map<String, String> players) {
//        this.players = players;
//    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public boolean isJoinable() {
        return joinable;
    }


    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }
}
