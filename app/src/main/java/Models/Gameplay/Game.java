package Models.Gameplay;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String id;
    //    private Map<String,String> players; // not sure what the key and value are
    private ArrayList<String> players; //list of players' usernames
    private boolean active;

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
            players = new ArrayList<String>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean addPlayer(String p){
        return players.size() < 5 && !players.contains(p) && players.add(p);
//        if(players.size() < 5)
//        {
//            return players.add(p);
//        }
//        else
//        {
//            return false;
////            return "the player could not be added, the Game is full";
//        }
//        return "";
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public boolean isJoinable() {
        return (players.size()<5 && !active);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive()
    {return active;}

}
