package Models;

import java.util.ArrayList;

import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;
import Models.Gameplay.Chat;
import Models.Gameplay.Game;

public class Request {

    private String username;
    private String password;
    private String authToken;
    private String gameId;
    private boolean status; //Game started or not?, etc.
    private int commandNum;

    //Gameplay request features
    private Game game;      //Pass back Game object
    private String play; //Game history entry
    private ArrayList<DestinationCard> discardDest;
    private ArrayList<TrainCard> discardTrains;

    //Chat request Features:
    private Chat chat;
    private int chatNum;
    private String chatMessage;

    public Request(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commmandNum) {
        this.commandNum = commmandNum;
    }

    public String getChatMessage() {
        return chatMessage;
    }


    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public ArrayList<DestinationCard> getDiscardDest() {
        return discardDest;
    }

    public void setDiscardDest(ArrayList<DestinationCard> discardDest) {
        this.discardDest = discardDest;
    }

    public ArrayList<TrainCard> getDiscardTrains() {
        return discardTrains;
    }

    public void setDiscardTrains(ArrayList<TrainCard> discardTrains) {
        this.discardTrains = discardTrains;
    }
}
