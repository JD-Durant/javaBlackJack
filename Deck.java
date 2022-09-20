import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){
        for(Suit cardSuit : Suit.values()){
            for (Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public String toString(){
        String cardList = "";
        for(Card card: this.cards){
            cardList += "["+card.toString()+"]";
        }
        return cardList;
    }

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++){
            randomCardIndex = random.nextInt((this.cards.size()-1-0)+1)+0;
            tempDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards = tempDeck;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckValue(){
        int totalValue = 0;
        int aceCount = 0;
        for(Card card : this.cards){
            totalValue += card.getValue();
            if (card.getValue() == 11){
                aceCount++;
            }
    if (totalValue > 21 && aceCount > 0){
        while(aceCount > 0 && totalValue > 21){
            aceCount--;
            totalValue -= 10;
                }
            }
        }
    return totalValue;
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();
        for (int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        for(int i=0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }
}
