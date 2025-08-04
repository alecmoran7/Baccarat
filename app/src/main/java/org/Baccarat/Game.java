package org.Baccarat;

public class Game {

    Deck deck = new Deck();
    double winPercentage = 0.0;
    int numGamesPlayed = 0;

    public Game(){
        double gameResults = 0.0;
        double playerWager = 1.0;

        double numLosses = 0.0;
        double numTies = 0.0;
        double numWins = 0.0;

        while (numGamesPlayed < 10000) {
            try {
                ++numGamesPlayed;
                System.out.println("    Game #" + numGamesPlayed);
                Round round = new Round (deck, gameResults);
                round.initializeRound();
                gameResults = round.startGame();

                if (gameResults == 0) numLosses++;
                if (gameResults == 1) numTies++;
                if (gameResults == 2) numWins++;
            }
            catch (Exception e){
                System.err.println("Error with running Baccarat game");
                e.printStackTrace();
            }
            System.out.println("Win (+) vs Ties vs Loss (-) : " + numWins + ", " + numTies + ", " + numLosses);

            if(numGamesPlayed % 20 == 0){
                System.out.println("Shuffling deck");
                deck = new Deck();
            }
        }

        winPercentage = numWins / (numWins + numLosses);
        System.out.println("Win percentage: " + winPercentage);

    }

    public static void main(String args[]){
        System.out.println("Starting new game:");
        Game game = new Game();
    }

}