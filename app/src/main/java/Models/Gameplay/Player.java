package Models.Gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;

public class Player {
    private String color;
    private String name;
    private Score score;
    private boolean turn;
    private int numTrains; //will be decremented when a route is claimed
    private Map<Integer, Route> claimedRoutes;
    private ArrayList<TrainCard> hand;
    private ArrayList<DestinationCard> destination_cards;
    private ArrayList<DestinationCard> drawnDestCards;
    private int playerRank;

    public Player() {
        claimedRoutes = new HashMap<>();
        hand = new ArrayList<>();
        destination_cards = new ArrayList<>();
        drawnDestCards = new ArrayList<>();
        numTrains = 45;
        score = new Score();
    }

    public Player(String username){
        this.name = username;
        claimedRoutes = new HashMap<>();
        hand = new ArrayList<>();
        destination_cards = new ArrayList<>();
        drawnDestCards = new ArrayList<>();
        numTrains = 45;
        score = new Score();
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

    public boolean isTurn() {
        return turn;
//        return ActiveGame.getInstance().getActivePlayer().equals(name);
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Map<Integer,Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(Map<Integer, Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public ArrayList<TrainCard> getHand() {
        return hand;
    }

    public void setHand(ArrayList<TrainCard> hand) {
        this.hand = hand;
    }

    public ArrayList<DestinationCard> getDestination_cards() {
        return destination_cards;
    }

    public void setDestination_cards(ArrayList<DestinationCard> destination_cards) {
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
        drawnDestCards.clear();

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

    public ArrayList<DestinationCard> getDrawnDestCards() {
        return drawnDestCards;
    }

    public void setDrawnDestCards(ArrayList<DestinationCard> drawnDestCards) {
        this.drawnDestCards = drawnDestCards;
    }

    public void addDrawnDestCards(ArrayList<DestinationCard> destCards) {
        drawnDestCards = destCards;
        destination_cards.addAll(destCards);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getRoutePoints() {
        return score.getRoutePoints();
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    public void removeTrainCards(Route route){
        int routeLength = route.getLength();
        String routeColor = route.getColor();
        int count = 0;

        for(int i = 0; i < hand.size(); i++){

            if(hand.get(i).getColor().equals(routeColor)){
                hand.remove(i);
                count++;
                i--;

            }else if(hand.get(i).getColor().equals("wild")){
                hand.remove(i);
                count++;
                i--;
            }else if(routeColor.equals("wild")){
                //TODO remove only cards of same color
                hand.remove(i);
                count++;
                i--;

            }
            else if(routeColor.equals("wild"))
            {
                hand.remove(i);
                count++;
                i--;
            }

            if(routeLength == count){
                break;
            }
        }

    }

    //pass in the color and the number of cards
    public ArrayList<TrainCard> discardTrainCards(String color, int num) {
        ArrayList<TrainCard> cards = new ArrayList<>();
        if(getNumColorCards(color) >= num)
        {
            for (int i = 0; i < num; i++)
            {
                if (hand.get(i).getColor().equals(color))
                {
                    cards.add(hand.get(i));
                }
            }
        }
        return cards;
    }
}
