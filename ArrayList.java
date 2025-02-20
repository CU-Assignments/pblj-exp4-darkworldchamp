//Create a program to collect and store all the cards to assist the users in finding all the cards in a given symbol using Collection interface.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Card class to represent a card with a symbol and a value
class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

// Main class to manage the collection of cards
public class CardCollection {
    private List<Card> cards;

    public CardCollection() {
        cards = new ArrayList<>();
    }

    // Method to add a card to the collection
    public void addCard(Card card) {
        cards.add(card);
    }

    // Method to find all cards with a given symbol
    public List<Card> findCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CardCollection cardCollection = new CardCollection();

        // Adding some cards to the collection
        cardCollection.addCard(new Card("Hearts", "Ace"));
        cardCollection.addCard(new Card("Hearts", "King"));
        cardCollection.addCard(new Card("Spades", "Queen"));
        cardCollection.addCard(new Card("Diamonds", "Jack"));
        cardCollection.addCard(new Card("Clubs", "10"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the symbol to find cards (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        List<Card> foundCards = cardCollection.findCardsBySymbol(symbol);

        if (foundCards.isEmpty()) {
            System.out.println("No cards found with the symbol: " + symbol);
        } else {
            System.out.println("Cards found with the symbol " + symbol + ":");
            for (Card card : foundCards) {
                System.out.println(card);
            }
        }

        scanner.close();
    }
}
