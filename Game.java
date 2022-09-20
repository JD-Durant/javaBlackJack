import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    public static void main(String[] args) { //Yes this is a bit of a god method
        System.out.println("Welcome to Blackjack!");
        GameObjects go = new GameObjects();
        go.playingDeck.createFullDeck();
        go.playingDeck.shuffle();
        Scanner userInput = new Scanner(System.in);
        while (go.playerMoney > 0){
            boolean endRound = false;
            System.out.println("You have £"+go.playerMoney+", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > go.playerMoney) {
                go.reasonForExit = 1;
                break;
            }
            if (go.handsWon != 0){
                int randomShuffle = ThreadLocalRandom.current().nextInt(1, 10 + 1);
                if (randomShuffle == 10) {
                    System.out.println("House has shuffled current deck!");
                    go.playingDeck.shuffle();
                }
            }
            go.createHands();
            while(endRound == false){
                if ((go.handsWon+go.handsLost) != 0){
                    System.out.println("You have ["+go.handsWon+"]Win ["+go.handsLost+"]Loss ["+(go.handsWon/(go.handsLost+go.handsWon))*100+"%]Win Percentage ");
                }
                System.out.println("Your Hand : "+go.playerDeck.toString()+" Total Value : ["+go.playerDeck.deckValue()+"]");
                System.out.println("Dealer Hand : ["+go.dealerDeck.getCard(0).toString()+"] [Hidden]");
                System.out.println("Would you like to [1]Hit [2]Stand ?");
                int response = userInput.nextInt();
                if (response == 1){
                    go.playerDeck.draw(go.playingDeck);
                    System.out.println("You drew a ["+go.playerDeck.getCard(go.playerDeck.deckSize()-1).toString()+"]");
                    if(go.playerDeck.deckValue() > 21){
                        System.out.println("Bust! Your hand is currently valued at "+go.playerDeck.deckValue());
                        go.playerLose(playerBet);
                        endRound = true;
                    }
                }
                if(response==2){
                    break;
                }
            }
            if (endRound == false){
                System.out.println("Dealer Hand : "+go.dealerDeck.toString()+" Value : ["+go.dealerDeck.deckValue()+"]");
                while(go.dealerDeck.deckValue() < 17) {
                    go.dealerDeck.draw(go.playingDeck);
                    System.out.println("Dealer draws : ["+go.dealerDeck.getCard(go.dealerDeck.deckSize()-1).toString()+"] Value : ["+go.dealerDeck.deckValue()+"]");
                }
                go.gameResults(playerBet);
                endRound = true;
            }
            go.playerDeck.moveAllToDeck(go.playingDeck);
            go.dealerDeck.moveAllToDeck(go.playingDeck);
            System.out.println("End of hand");
        }
        userInput.close(); //To keep the Java gods happy
        switch(go.reasonForExit) {
        case (0): System.out.println("Game over! Sorry, but the house always wins!"); break;
        case (1): System.out.println("Game Over! You were thrown out for illegal bettings"); break;
        }
        System.out.println("["+go.handsWon+"]Win ["+go.handsLost+"]Loss ["+(go.handsWon/(go.handsWon+go.handsLost))*100+"%]Win Percentage ");
    }
}
