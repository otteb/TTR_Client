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

    public Game(ArrayList<String> p, String i)
    {
        id = i;
        if(p != null) {
            players = p;
        }
        else {
            players= new ArrayList<String>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String addPlayer(String p){
        if(players.size() <= 5)
        {
            players.add(p);
        }
        else
        {
            return "the player could not be added the Game is full";
        }
        return null;
    }

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
