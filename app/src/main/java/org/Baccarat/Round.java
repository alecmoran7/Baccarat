package org.Baccarat;

import java.util.Vector;
import java.util.Collections;

public class Round {

    private Deck deck;
    private double gameResults = 0.0;
    private Vector<Card> playerHand;
    private Vector<Card> bankerHand;

    private enum Status {DRAW, STAND, NATURAL_STAND};
    private Status playerStatus;
    private Status bankerStatus;

    private int bankerTotal = 0;
    private int playerTotal = 0;


    public Round(Deck deck, double gameResults){
        this.deck = deck;
        this.gameResults = gameResults;
        this.playerHand = new Vector<Card>();
        this.bankerHand = new Vector<Card>();
    }


    public void initializeRound(){
        // Deal one card to player, then one to banker
        // Deal second card to player, then another to the banker
        playerHand.add(deck.getCard());
        System.out.println("Player now has:" + playerHand.get(0).getIcon());
        bankerHand.add(deck.getCard());
        System.out.println("Banker now has:" + bankerHand.get(0).getIcon());
        playerHand.add(deck.getCard());
        System.out.println("Player now has:" + playerHand.get(0).getIcon() + " and " + playerHand.get(1).getIcon());
        bankerHand.add(deck.getCard());
        System.out.println("Banker now has:" + bankerHand.get(0).getIcon() + " and " + bankerHand.get(1).getIcon());

    }

    public double startGame(){
        playerTotal = getHandTotal(playerHand);
        bankerTotal = getHandTotal(bankerHand);
        System.out.println("Totals: Player: " + playerTotal + " Banker: " + bankerTotal);

        switch(playerTotal){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: playerStatus = Status.DRAW;
                    break;
            case 6:
            case 7: playerStatus = Status.STAND;
                    break;
            case 8:
            case 9: playerStatus = Status.NATURAL_STAND;
                return decideWinner();
        }

        // TODO: Possibly consolidate both switch statements into one
        switch(bankerTotal){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: bankerStatus = Status.DRAW;
                break;
            case 6:
            case 7: bankerStatus = Status.STAND;
                break;
            case 8:
            case 9: bankerStatus = Status.NATURAL_STAND;
                return decideWinner();
        }

        if (playerStatus == Status.DRAW){
            playerHand = drawCard(playerHand);
            System.out.println("Player draws a: " + playerHand.get(2).getIcon());
            playerTotal = getHandTotal(playerHand);
        }

        if (bankerStatus == Status.DRAW){
            bankerHand = drawCard(bankerHand);
            System.out.println("Banker draws a: " + bankerHand.get(2).getIcon());
            bankerTotal = getHandTotal(bankerHand);
        }

        return decideWinner();
    }

    private int getHandTotal(Vector<Card> hand){
        int total = 0;
        for (int i = 0; i < hand.size(); i++){
            total += hand.get(i).getValue();
        }
        System.out.println("Total is: " + total + " % 10 = " + total % 10);
        return total % 10;
    }

    private double decideWinner(){

        System.out.println("Deciding the winner:");
        System.out.println("Banker has :" + bankerTotal);
        System.out.println("Player has :" + playerTotal);

        if (bankerTotal > playerTotal){
            System.out.println("Banker wins");
            return 2;
        }
        else if (bankerTotal == playerTotal){
            System.out.println("Tie");
            return 1;
        }
        else if (bankerTotal < playerTotal){
            System.out.println("Player wins");
            return 0;
        }
        System.err.println("Error, should not reach this line");
        return -999999999;
    }

    private Vector<Card> drawCard(Vector <Card> handToDraw){
        handToDraw.add(deck.getCard());
        return handToDraw;
    }
}