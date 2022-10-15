package games;

import games.cards.*;
import games.cards.TriantaEnaCard;
import games.players.TriantaEnaPlayer;
import games.utils.Menu;

import java.util.*;

public class TriantaEnaGame implements Game {
    private static final int MAX_PLAYER_LIMIT = 9;
    private static final int NUMDECKS = 2;
    private static final int BURST_VALUE = 31;
    private final int numOfCardDecks;
    private final ArrayList<TriantaEnaPlayer> players;
    private TriantaEnaPlayer banker;
    private final Deck<TriantaEnaCard> deck;

    /**
     * Create an object of TriantaEnaGame class
     *
     * @param numOfCardDecks the size of deck to be used
     */
    public TriantaEnaGame(int numOfCardDecks) {
        this.numOfCardDecks = numOfCardDecks;
        players = new ArrayList<>();
        deck = new Deck<>();
    }

    /**
     * Create an object of TriantaEnaGame
     * using default size of deck
     */
    public TriantaEnaGame() {
        this(NUMDECKS);
    }

    /**
     * Print a brief description about the game
     */
    public void introduce(){
        Menu.display("The objective of the game is to accumulate a hand of cards that equals 31 (Trianta ena!) \n" +
                "or a hand that has a card value greater than that of the Dealers without exceeding 31. ");
    }

    /**
     * Initialize the Players and fill the deck with cards
     */
    public void initialize() {
        double eachPlayerAmount;

        eachPlayerAmount = Menu.getIntegerInput("Enter valid amount for one player");
        double bankerAmount = 3 * eachPlayerAmount;

        banker = new TriantaEnaPlayer(Menu.getStringInput("Enter banker name"), bankerAmount);
        int playerNum = 1;
        while (playerNum <= MAX_PLAYER_LIMIT ){
            players.add(new TriantaEnaPlayer(Menu.getStringInput("Enter player ["+playerNum+"] name"), eachPlayerAmount));
            boolean isYes = Menu.getUserYesNo("Do You wanna add more players?", Menu.YES_NO_CHOICE, Menu.YES);
            if (!isYes){
                break;
            }
            playerNum++;
        }
        fillDeckWithCards();
    }

    /**
     * Start the game
     */
    public void start() {
        play();
    }

    /**
     * End the game and print all the transactions
     * happened for the existing players
     */
    public void end() {
        Menu.message("Thanks for Playing !!!");
        for (TriantaEnaPlayer player : players){
            Menu.message(player.name() + " transactions");
            player.transactions();
            Menu.footer();
        }
    }

    /**
     * Draw a new card and add to players inventory when play make a hit
     *
     * @param player player who want to make a hit move
     */
    private void hit(TriantaEnaPlayer player){
        TriantaEnaCard card = deck.drawCard();
        player.inventory().addItem(card);
        card.setFaceUp();
    }

    /**
     * Face up the cards of the player who want to stand
     *
     * @param player player who want to make stand move
     */
    private void stand(TriantaEnaPlayer player){
        faceUpPlayerCards(player);
    }

    /**
     * Check if the player total value is above the BURST_VALUE, to check burst
     *
     * @param player player
     * @return true if bursting, false otherwise
     */
    private boolean isBurst(TriantaEnaPlayer player){
        return player.inventory().score() > BURST_VALUE && player.inventory().minScore() > BURST_VALUE;
    }

    /**
     * Face up all the cards of the player
     *
     * @param player player
     */
    private void faceUpPlayerCards(TriantaEnaPlayer player){
        for (TriantaEnaCard card : player.inventory().cards()){
            card.setFaceUp();
        }
    }

    /**
     * Check if the player has NaturalTriantaEna cards in Inventory
     *
     * @param player player
     * @return true of NaturalTriantaEna condition satisfied, false otherwise
     */
    private boolean isNaturalTriantaEna(TriantaEnaPlayer player) {
        boolean isAce = false;
        boolean isFace = false;
        boolean anotherFace = false;

        for (TriantaEnaCard card : player.inventory().cards()) {
            if (card.isAce()) {
                isAce = true;
            }
            if (card.value() == 10 && !isFace) {
                isFace = true;
            } else if (card.value() == 10 && isFace) {
                anotherFace = true;
            }
        }

        return isAce && isFace && anotherFace;
    }

    /**
     * Check if banker score is higher than the player
     *
     * @param player player playing against banker
     * @return true if banker score value is higher, false otherwise
     */
    private boolean isBankerScoreHigher(TriantaEnaPlayer player) {
        double playerScore = player.inventory().score() < BURST_VALUE ? player.inventory().score()
                : player.inventory().minScore();
        double bankerScore = banker.inventory().score() < BURST_VALUE ? banker.inventory().score()
                : banker.inventory().minScore();
        return bankerScore >= playerScore;
    }

    /**
     * Fill the deck with cards (count will be equals to numOfCardDecks)
     */
    private void fillDeckWithCards() {
        for (int i = 0; i < numOfCardDecks; i++) {
            Stack<TriantaEnaCard> oneDeck = new Stack<>();
            for (String ch : TriantaEnaCard.faceValues) {
                oneDeck.push(new TriantaEnaCard(ch, Suit.CLUB));
                oneDeck.push(new TriantaEnaCard(ch, Suit.DIAMOND));
                oneDeck.push(new TriantaEnaCard(ch, Suit.HEART));
                oneDeck.push(new TriantaEnaCard(ch, Suit.SPADE));
            }
            deck.addCards(oneDeck);
        }
    }

    /**
     * Collect all previous round cards from players
     */
    private void collectLastRoundCards() {
        for (TriantaEnaPlayer player : players) {
            deck.addCards(player.inventory().cards());
            player.inventory().reset();
        }
        deck.addCards(banker.inventory().cards());
        banker.inventory().reset();
    }

    /**
     * Check if game can be played for next round
     * This check for banker & players bank balance
     *
     * @return true if it can be played for next round, false otherwise
     */
    private boolean canBePlayed() {
        double totalAmount = 0;
        boolean hasAmount = false;

        if (banker.amount() <= 0 || players.size() == 0) {
            return false;
        }

        // check if banker has enough total amount
        for (TriantaEnaPlayer player : players){
            totalAmount += player.amount();
        }

        // if not then return false
        if (totalAmount > banker.amount()){
            return false;
        }

        // check if any player has amount more than zero
        for (TriantaEnaPlayer player : players) {
            if (player.amount() > 0) {
                hasAmount = true;
            }
        }
        return hasAmount;
    }

    /**
     * Ask player who have more money to be the banker
     * If yes switch banker and that player
     */
    private void switchBanker() {
        for(TriantaEnaPlayer player : players){
            if (player.amount() < banker.amount()){
                break;
            } else {
                boolean playerWantToBeBanker = Menu.getUserYesNo(
                        "Hey " + player.name() + ", Do you want to be banker?", Menu.YES_NO_CHOICE, Menu.YES);
                if (playerWantToBeBanker){
                    players.add(banker);
                    banker = player;
                    players.remove(player);
                    break;
                }
            }
        }
    }

    /**
     * Play one full round of TriantaEna
     */
    private void playOneRound() {

        // player gets there first card face down
        Menu.message("Drawing face down card to all players");
        for (TriantaEnaPlayer player : players) {
            player.inventory().addItem(deck.drawCard());
        }

        Menu.message("Drawing face up card to banker");
        TriantaEnaCard bankerCard = deck.drawCard();
        bankerCard.setFaceUp();
        banker.inventory().addItem(bankerCard);
        banker.inventory().display(banker.name());

        // placing bet
        Menu.message("Get ready, time to place bets");
        ArrayList<TriantaEnaPlayer> skipPlayers = new ArrayList<>();
        for (TriantaEnaPlayer player : players) {
            Menu.message(player.name() + " your turn!!!");
            player.inventory().playerDisplay(player.name());
            boolean wannaBet = Menu.getUserYesNo(
                    player.name() + ", Do you wanna bet?", Menu.YES_NO_CHOICE, Menu.YES);
            if (wannaBet) {
                while (true) {
                    try {
                        player.placeBet(Menu.getDoubleInput("Enter the valid bet amount"));
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid amount!, Try again");
                    }
                }
            } else {
                skipPlayers.add(player);
            }
        }
        Menu.footer();

        if (players.size() == skipPlayers.size()) {
            Menu.message("No one placed the bet for this round!");
            return;
        }

        // player receiving 2 more Cards
        Menu.message("Time to get more Cards");
        for (TriantaEnaPlayer better : players) {
            if (!skipPlayers.contains(better)){
                TriantaEnaCard card1 = deck.drawCard();
                TriantaEnaCard card2 = deck.drawCard();

                better.inventory().addItem(card1);
                better.inventory().addItem(card2);
                card1.setFaceUp();
                card2.setFaceUp();
                better.inventory().display(better.name());
            }
        }
        Menu.footer();

        // Hit and Stand
        Menu.message("Great! Now time to Hit or Stand");
        for (TriantaEnaPlayer better : players) {
            boolean isHit;
            Menu.message(better.name() + " your turn!!!");
            better.inventory().playerDisplay(better.name());
            isHit = Menu.getUserYesNo(better.name() + ", make your move", Menu.HIT_STAND, Menu.HIT);

            if (isHit){
                while (isHit && !isBurst(better)) {
                    hit(better);
                    better.inventory().playerDisplay(better.name());
                    isHit = Menu.getUserYesNo(better.name() + ", make your move", Menu.HIT_STAND, Menu.HIT);
                }
            } else {
                stand(better);
            }
            if (isBurst(better)) {
                Menu.message("uh-oh! " + better.name() + ", Your hand value burst!!");
                faceUpPlayerCards(better);
                better.inventory().display(better.name());
                banker.win(better.betValue());
                better.resetBetValue();
                skipPlayers.add(better);
            }
        }
        Menu.footer();

        // If all players are burst
        if (skipPlayers.size() == players.size()){
            return;
        }

        // Time to hit/stand for dealer
        Menu.message("Awesome!, Lets see dealers move!");
        while (banker.inventory().score() < 27) {
            Menu.message("Banker Card before every HIT");
            banker.inventory().display(banker.name());
            TriantaEnaCard card = deck.drawCard();
            banker.inventory().addItem(card);
            card.setFaceUp();
        }
        Menu.footer();

        // check if banker burst
        if (isBurst(banker)) {
            Menu.message("uh-oh! " + banker.name() + ", Your hand value burst!!");
            faceUpPlayerCards(banker);
            banker.inventory().display(banker.name());

            for (TriantaEnaPlayer better : players) {
                if (!skipPlayers.contains(better)) {
                    better.win(better.betValue()*2);
                    banker.loose(better.betValue());
                }
            }
        } else {
            // Time to decide the winner of the round
            Menu.message("Lets see who is WINNER of Trianta Ena!!!");
            for (TriantaEnaPlayer player : players){
                faceUpPlayerCards(player);
            }
            faceUpPlayerCards(banker);
            declareWinnerOfRound(skipPlayers);
        }
    }

    /**
     * Decide who is the winner for this one round and
     * do the transactions wherever required
     *
     * @param skipPlayers skip players who were burst or denied for bet
     */
    private void declareWinnerOfRound(ArrayList<TriantaEnaPlayer> skipPlayers) {
        // If banker has the Natural TriantaEna
        if (isNaturalTriantaEna(banker)) {
            Menu.message("Congratulations Natural TriantaEna!!, " + banker.name() + " You Won");
            banker.inventory().display(banker.name());
            for (TriantaEnaPlayer player : players) {
                if (!skipPlayers.contains(player)) {
                    banker.win(player.betValue());
                    player.resetBetValue();
                }
            }
        } else {
            // check for every player
            for (TriantaEnaPlayer player : players) {
                if (isNaturalTriantaEna(player) && !skipPlayers.contains(player)) {
                    skipPlayers.add(player);
                    Menu.message("Congratulations Natural TriantaEna!!, " + player.name() + " You Won");
                    player.inventory().display(player.name());
                    player.win(player.betValue()*2);
                    banker.loose(player.betValue());
                }
            }

            // check for highest value now
            for (TriantaEnaPlayer player : players) {
                if (!skipPlayers.contains(player)) {
                    if (banker.inventory().score() == BURST_VALUE || banker.inventory().minScore() == BURST_VALUE
                            || isBankerScoreHigher(player)) {
                        skipPlayers.add(player);
                        Menu.message("Congratulations!!, " + banker.name() + " You Won against " + player.name());
                        banker.inventory().display(banker.name());
                        player.inventory().display(player.name());
                        banker.win(player.betValue());
                        player.resetBetValue();
                    } else {
                        System.out.println("Congratulations!!, " + player.name() + " You Won against " + banker.name());
                        player.inventory().display(player.name());
                        banker.inventory().display(banker.name());
                        player.win(player.betValue()*2);
                        banker.loose(player.betValue());
                    }
                }
            }
        }
    }

    /**
     * Reset every player inventory
     */
    private void resetPlayersInventory(){
        banker.inventory().reset();
        for(TriantaEnaPlayer player : players){
            player.inventory().reset();
        }
    }

    /**
     * Reset every player bet
     */
    private void resetPlayersBet(){
        banker.resetBetValue();
        for (TriantaEnaPlayer player : players){
            player.resetBetValue();
        }
    }

    /**
     * Ask if anyone want to cash out or drop the game
     */
    private void cashOutCheck(){
        ArrayList<TriantaEnaPlayer> cashOutPlayers = new ArrayList<>();
        for (TriantaEnaPlayer player : players){
            boolean wannaCashOut = Menu.getUserYesNo(player.name() + ", Wanna Cash Out?", Menu.YES_NO_CHOICE, Menu.YES);
            if (wannaCashOut){
                cashOutPlayers.add(player);
            }
        }
        for (TriantaEnaPlayer cashOutPlayer : cashOutPlayers){
            Menu.message(cashOutPlayer.name() + " : dropping off!");
            Menu.message(cashOutPlayer.name() + " transactions");
            cashOutPlayer.transactions();
            Menu.footer();
            players.remove(cashOutPlayer);
        }
    }

    /**
     * Play several rounds of the game
     */
    private void play() {
        while (canBePlayed()) { // check if banker & players has enough amount
            switchBanker(); // this will not happen first time
            collectLastRoundCards(); // do it before every round
            deck.shuffle(); // shuffle the deck before each round
            playOneRound();  // play one round of Trianta Ena.
            resetPlayersInventory();  // reset everyone's inventory after each round
            resetPlayersBet();  // reset every player bet value to zero.
            Collections.sort(players); // sort based on amount
            cashOutCheck(); // check if player want to cash out
        }
    }
}
