public class GameObjects {
    Deck playingDeck = new Deck();
    Deck playerDeck = new Deck();
    Deck dealerDeck = new Deck();
    double playerMoney = 100.00;
    double handsWon = 0;
    double handsLost = 0;
    int reasonForExit = 0;

    public void playerLose(double playerBet) {
        playerMoney -=playerBet;
        handsLost++;
    }
    
    public void playerWin(double playerBet){
        playerMoney+=playerBet;
        handsWon++;
    }

    public void gameResults(double playerBet){
        if(dealerDeck.deckValue() > 21){
            System.out.println("Dealer Busts! You win!");
            playerWin(playerBet);
        }
        else if(dealerDeck.deckValue() > playerDeck.deckValue()){
            System.out.println("House Wins : Dealers Hand Value ["+dealerDeck.deckValue()+"] Your Hand ["+playerDeck.deckValue()+"]");
            playerLose(playerBet);

        }
        else if(playerDeck.deckValue() == dealerDeck.deckValue()){
            System.out.println("Draw / Push");
        }
        else if(playerDeck.deckValue() > dealerDeck.deckValue()) {
            System.out.println("You win! : Dealers Hand Value ["+dealerDeck.deckValue()+"] Your Hand ["+playerDeck.deckValue()+"]");
            playerWin(playerBet);
        }
        else{
            System.out.println("House wins : Dealers Hand Value ["+dealerDeck.deckValue()+"] Your Hand ["+playerDeck.deckValue()+"]");
            playerLose(playerBet);
        }
    }
    public void createHands(){
        playerDeck.draw(playingDeck);
        playerDeck.draw(playingDeck);
        dealerDeck.draw(playingDeck);
        dealerDeck.draw(playingDeck);
    }
}
