package games;

import games.cards.*;
import games.cards.TriantaEnaCard;
import games.players.TriantaEnaPlayer;
import games.utils.Menu;


import java.util.*;

public class TriantaEnaGame implements Game {
    private static final int MAX_PLAYER_LIMIT = 9;
    private static final int NUMDECKS = 2;
    private final int numOfCardDecks;
    private final ArrayList<TriantaEnaPlayer> players;
    private TriantaEnaPlayer banker;
    private final Deck<TriantaEnaCard> deck;

    public TriantaEnaGame(int numOfCardDecks) {
        this.numOfCardDecks = numOfCardDecks;
        players = new ArrayList<>();
        deck = new Deck<>();
    }

    public TriantaEnaGame() {
        this(NUMDECKS);
    }

    public void describe(){
        Menu.display("The objective of the game is to accumulate a hand of cards that equals 31 (Trianta ena!) \n" +
                "or a hand that has a card value greater than that of the Dealers without exceeding 31. ");
    }
    public void initialize() {
        describe();
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
    public void start() {
        play();
    }
    public void end() {
        // TODO Auto-generated method stub
    }

    private void hit(TriantaEnaPlayer player){
        TriantaEnaCard card = deck.drawCard();
        player.inventory.addItem(card);
        card.setFaceUp();
    }

    private void stand(TriantaEnaPlayer player){
        faceUpPlayerCards(player);
    }

    private boolean isBurst(TriantaEnaPlayer player){
        return player.inventory.score() > 31 && player.inventory.minScore() > 31;
    }

    private void faceUpPlayerCards(TriantaEnaPlayer player){
        for (TriantaEnaCard card : player.inventory.cards()){
            card.setFaceUp();
        }
    }
    private boolean isNaturalTriantaEna(TriantaEnaPlayer player) {
        boolean isAce = false;
        boolean isFace = false;
        boolean anotherFace = false;

        for (TriantaEnaCard card : player.inventory.cards()) {
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

    private boolean isBankerScoreHigher(TriantaEnaPlayer player) {
        double playerScore = player.inventory.score() < 31 ? player.inventory.score()
                : player.inventory.minScore();
        double bankerScore = banker.inventory.score() < 31 ? banker.inventory.score()
                : banker.inventory.minScore();
        return bankerScore >= playerScore;
    }
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

    private void collectLastRoundCards() {
        for (TriantaEnaPlayer player : players) {
            deck.addCards(player.inventory.cards());
            player.inventory.reset();
        }
        deck.addCards(banker.inventory.cards());
        banker.inventory.reset();
    }

    private boolean canBePlayed() {
        if (banker.amount() <= 0 || players.size() == 0) {
            return false;
        }
        boolean hasAmount = false;
        for (TriantaEnaPlayer player : players) {
            if (player.amount() > 0) {
                hasAmount = true;
            }
        }
        return hasAmount;
    }

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


    private void playOneRound() {

        // player gets there first card face down
        Menu.header("Drawing face down card to all players");
        for (TriantaEnaPlayer player : players) {
            player.inventory.addItem(deck.drawCard());
        }

        Menu.header("Drawing face up card to banker");
        TriantaEnaCard bankerCard = deck.drawCard();
        bankerCard.setFaceUp();
        banker.inventory.addItem(bankerCard);
        banker.inventory.display(banker.name());

        // placing bet
        Menu.header("Get ready, time to place bets");
        ArrayList<TriantaEnaPlayer> skipPlayers = new ArrayList<>();
        for (TriantaEnaPlayer player : players) {
            Menu.header(player.name() + " your turn!!!");
            player.inventory.playerDisplay(player.name());
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
            Menu.header("No one placed the bet for this round!");
            return;
        }

        // player receiving 2 more Cards
        Menu.header("Time to get more Cards");
        for (TriantaEnaPlayer better : players) {
            if (!skipPlayers.contains(better)){
                TriantaEnaCard card1 = deck.drawCard();
                TriantaEnaCard card2 = deck.drawCard();

                better.inventory.addItem(card1);
                better.inventory.addItem(card2);
                card1.setFaceUp();
                card2.setFaceUp();
                better.inventory.display(better.name());
            }
        }
        Menu.footer();

        // Hit and Stand
        Menu.header("Great! Now time to Hit or Stand");
        for (TriantaEnaPlayer better : players) {
            boolean isHit;
            Menu.header(better.name() + " your turn!!!");
            better.inventory.playerDisplay(better.name());
            isHit = Menu.getUserYesNo(better.name() + ", make your move", Menu.HIT_STAND, Menu.HIT);

            if (isHit){
                while (isHit) {
                    hit(better);
                    better.inventory.playerDisplay(better.name());
                    isHit = Menu.getUserYesNo(better.name() + ", make your move", Menu.HIT_STAND, Menu.HIT);
                }
            } else {
                stand(better);
            }
            if (isBurst(better)) {
                Menu.header("uh-oh! " + better.name() + ", Your hand value burst!!");
                better.inventory.display(better.name());
                banker.win(better.betValue());
                better.loose(better.betValue());
                skipPlayers.add(better);
            }
        }
        Menu.footer();

        // Time to hit/stand for dealer
        Menu.header("Awesome!, Lets see dealers move!");
        while (banker.inventory.score() < 27) {
            Menu.header("Banker Card before every HIT");
            banker.inventory.display(banker.name());
            TriantaEnaCard card = deck.drawCard();
            banker.inventory.addItem(card);
            card.setFaceUp();
        }
        Menu.footer();

        // check if banker burst
        if (isBurst(banker)) {
            Menu.header("uh-oh! " + banker.name() + ", Your hand value burst!!");
            faceUpPlayerCards(banker);
            banker.inventory.display(banker.name());

            for (TriantaEnaPlayer better : players) {
                if (!skipPlayers.contains(better)) {
                    better.win(better.betValue());
                    banker.loose(better.betValue());
                }
            }
        } else {
            // Time to decide the winner of the round
            Menu.header("Lets see who is WINNER of Trianta Ena!!!");
            for (TriantaEnaPlayer player : players){
                faceUpPlayerCards(player);
            }
            faceUpPlayerCards(banker);
            declareWinnerOfRound(skipPlayers);
        }
    }


    private void declareWinnerOfRound(ArrayList<TriantaEnaPlayer> skipPlayers) {
        // If banker has the Natural TriantaEna
        if (isNaturalTriantaEna(banker)) {
            Menu.header("Congratulations Natural TriantaEna!!, " + banker.name() + " You Won");
            banker.inventory.display(banker.name());
            for (TriantaEnaPlayer player : players) {
                if (!skipPlayers.contains(player)) {
                    banker.win(player.betValue());
                    player.loose(player.betValue());
                }
            }
        } else {
            // check for every player
            for (TriantaEnaPlayer player : players) {
                if (isNaturalTriantaEna(player) && !skipPlayers.contains(player)) {
                    skipPlayers.add(player);
                    Menu.header("Congratulations Natural TriantaEna!!, " + player.name() + " You Won");
                    player.inventory.display(player.name());
                    player.win(player.betValue());
                    banker.loose(player.betValue());
                }
            }

            // check for highest value now
            for (TriantaEnaPlayer player : players) {
                if (!skipPlayers.contains(player)) {
                    if (banker.inventory.score() == 31 || banker.inventory.minScore() == 31
                            || isBankerScoreHigher(player)) {
                        skipPlayers.add(player);
                        Menu.header("Congratulations!!, " + banker.name() + " You Won against " + player.name());
                        banker.inventory.display(banker.name());
                        player.inventory.display(player.name());
                        banker.win(player.betValue());
                        player.loose(player.betValue());
                    } else {
                        System.out.println("Congratulations!!, " + player.name() + " You Won against " + banker.name());
                        player.inventory.display(player.name());
                        banker.inventory.display(banker.name());
                        player.win(player.betValue());
                        banker.loose(player.betValue());
                    }
                }
            }
        }
    }

    private void play() {
        while (canBePlayed()) {
            switchBanker(); // this will not happend first time
            collectLastRoundCards(); // do it before every round
            deck.shuffle();
            playOneRound();
//            resetPlayersInventory();  // TODO implement
//            resetPlayerBets();  // TODO implement
            Collections.sort(players); // sort based on amount
        }
    }
}
