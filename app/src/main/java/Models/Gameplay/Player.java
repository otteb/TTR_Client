package Models.Gameplay;

import java.util.ArrayList;
import java.util.List;

import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;

public class Player {
    private String color;
    private String name;
    private int points;
    private boolean turn;
    private int numTrains; //will be decremented when a route is claimed
    private List<Route> claimedRoutes;
    private ArrayList<TrainCard> hand;
    private List<DestinationCard> destination_cards;

    public Player() {
        claimedRoutes = new ArrayList<>();
        hand = new ArrayList<>();
        destination_cards = new ArrayList<>();
        numTrains = 45;
    }

    public Player(String username){
        this.name = username;
        claimedRoutes = new ArrayList<>();
        hand = new ArrayList<>();
        destination_cards = new ArrayList<>();
        numTrains = 45;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int pts) {
        points += pts;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isTurn() {
        return turn;
//        return ActiveGame.getInstance().getActivePlayer().equals(name);
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public List<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(List<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public ArrayList<TrainCard> getHand() {
        return hand;
    }

    public void setHand(ArrayList<TrainCard> hand) {
        this.hand = hand;
    }

    public List<DestinationCard> getDestination_cards() {
        return destination_cards;
    }

    public void setDestination_cards(List<DestinationCard> destination_cards) {
        this.destination_cards = destination_cards;
    }

    public int getNumTrains() {
        return numTrains;
    }

    public void setNumTrains(int numTrains) {
        this.numTrains = numTrains;
    }

    public void decNumTrains(int num) {
        numTrains -= num;
    }

    public void discardDestCards(ArrayList<DestinationCard> cards) {
        for(DestinationCard discard : cards)
        {
            for(DestinationCard card : destination_cards)
            {
                if(discard.isEqual(card))
                {
                    destination_cards.remove(card);
                    break;
                }
            }
        }

//        destination_cards.removeAll(cards);
    }

    public int getNumColorCards(String color) {
        int count = 0;
        for(TrainCard card : hand)
        {
            if(card.getColor().equals(color))
            {
                count++;
            }
        }
        return count;
    }
}
