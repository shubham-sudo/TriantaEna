# CS611 - Assignment #2
## Trianta Ena

---------------------------------------------------------------------------
Shubham Kaushik
kaushiks@bu.edu
U27091218
---------------------------------------------------------------------------

## Object Oriented Design Document
---------------------------------------------------------------------------

The UML diagram (uml.png) shows the relation between below classes
1) We have an abstract Player class which can be inherited to any game player class, since it has only basic details of player.
2) The abstract Inventory generic class which can be used for any other game player which holds the players inventory items.
3) The abstract Card class id defined for generating any game card having faceValue and Suit.
4) The Suit is an enum used for defining all the 52 Deck Card Suits.
5) Deck is a Generic class which extends any Card class or its child classes.
6) Bank is a class which takes care of all the transactions for the player happening in the game.
   The balance can go to negative for any one round. The player won't be able to play in next round.
7) Game is an interface which gives and interface to any Game child class

Now How the Trianta Ena Game formed with the above structure
- Since Trianta Ena is a Cards game so, I used the Deck of TriantaEnaCard (child class of Cards) class.
  TriantaEnaCard have more features which TriantaEna game need like isFaceUp, value, minValue.
- TriantaEnaInventory follows the same structure as a simple game inventory use to follow so, I have just defined a
  child class TriantaEnaInventory which extends the Inventory abstract class and implements all the required methods
  with extra utility methods we have.
- Every player including the banker has lot more features than a normal game Player use to have so,
  I have created a child class TriantaEnaPlayer of Player class and defined the extra properties.
  Every player has the Inventory and a Bank account. This is some sort of one-to-one relation between
  player and inventory or bank object.
- TriantaEnaGame implements Game interface and define all the required methods any game need to be played.
   (a) MAX_PLAYER_LIMIT is final variable which is set 9 (the upper limit of number of players),
   (b) NUMDECK is set to 2 which is the initial value but can be changed as per the requirement of the game.
   (c) Game needs a class Deck object 'deck' which can hold NUMDECK * 52 cards to play this game
   (d) A TriantaEnaPlayer reference object which will point to banker and ArrayList<TriantaEnaPlayer> players for list of players.
   (e) Other methods are hit, stand, isBurst, isNaturalTriantaEna, play, playOneRound, switchBanker, declareWinnerOfRound etc.

Benefits of the adopted object structure
The adopted structure is flexible as any Game which need cards, inventory or any money transaction can build on top of this:
1) The game logic is defined in TriantaEna class which extends Game interface so that every game follows the same structure.
2) I tried to implement things in a bottom up approach where bottom is the objects we need to form any game.
   The Player, Inventory, Card are things which can be required in any game so, these abstract classes can be extended into any other class to reuse the functionalities.
3) The generic class Deck can be of any type of cards so, it can be used to hold any type of cards which are child of Card class.
4) utils is the package which has all the utility classes like PublicScanner singleton class (read input from console) or
   Menu class which asks input from user or print some menu things on the console. These features are very common to any console based game.

Changes Made During This Assignment
The Card, Deck classes are new as they were not part of Tic Tac Toe / Order & Chaos game. The Player of Trianta Ena game has
lot more properties and methods comparative to Tic Tac Toe so, I defined a class TriantaEnaPlayer which extends the same Player class
of Tic Tac Toe game. The Inventory & Bank are newly added as per the requirement of this game. The Utility feature are much similar but,
I added Menu new in this Game since there were lots of printing and need of user inputs. By this I tried to separate the User input part from
the game, and it can be reused to any game now.

## Files
--------------------------------------------------------------------------
├── src
|  └── games
|     ├── bank
|     |     ├── Bank.java  // This is a Standalone class which does all the transaction related job for any players account.
|     |     └── Transaction.java  // This is used for Transactions happening during the game.
|     ├── cards
|     |     ├── Card.java  // An abstract class which defines the basic card structure to be used for any Game Card
|     |     ├── Deck.java  // A generic class for all type of game cards be used in card game. This sets the upper bound to Card.
|     |     ├── Suit.java  // An enum which gives the value for suits to be used in cards.
|     |     └── TriantaEnaCard.java  // Game specific card which is used for Trianta Ena Game as of now.
|     ├── Game.java  // An interface for initializing & playing a game. This is implemented in TriantaEnaGame class.
|     ├── inventory
|     |     ├── Inventory.java  // An abstract class which provides a basic structure for the inventory of any Game.
|     |     └── TriantaEnaPlayerInventory.java  // This is TriantaEna game specific Inventory which is used in this game to hold player cards and compute their values.
|     ├── Main.java  // Main class to run the Game which creates an object of TriantaEna Game and run it.
|     ├── players
|     |     ├── Player.java  // An abstract class which is very basic for all type of player of any Game.
|     |     └── TriantaEnaPlayer.java  // TriantaEnaPlayer extends the Player class and build on top of it to define game specific player.
|     ├── TriantaEnaGame.java  // This defines the TriantaEnaGame which implements the Game Interface to define all basic methods required to start and play the game.
|     └── utils
|         ├── Menu.java  // Menu is like the Game Menu, The class offers several static functions to display message or get the user input from console.
|         └── PublicScanner.java  // A singleton class for scanner which helps in reading user inputs from the console.

## Notes
---------------------------------------------------------------------------
1. Bonus Done
2. Notes to grader

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "TriantaEnaGame" after unzipping the files
2. Run the following instructions:
javac -d bin src/games/bank/*.java src/games/cards/*.java src/games/inventory/*.java src/games/players/*.java src/games/utils/*.java src/games/*.java
java -cp bin games.Main

## Input/Output Example
---------------------------------------------------------------------------
Welcome!
Lets play TriantaEnaGame Today
************************************************
The objective of the game is to accumulate a hand of cards that equals 31 (Trianta ena!)
or a hand that has a card value greater than that of the Dealers without exceeding 31.
************************************************

**************** Enter valid amount for one player ****************
Please enter : 100
************************************************

**************** Enter banker name ****************
Please enter : Shubham
************************************************

**************** Enter player [1] name ****************
Please enter : John
************************************************

**************** Do You wanna add more players? ****************
Please enter YES/NO : yes
************************************************

**************** Enter player [2] name ****************
Please enter : Jancy
************************************************

**************** Do You wanna add more players? ****************
Please enter YES/NO : no
************************************************

**************** Drawing face down card to all players ****************

**************** Drawing face up card to banker ****************
**************** Shubham Cards ****************
	Card[id=86, faceValue=9, suit=♦]
**********************************************

**************** Get ready, time to place bets ****************

**************** John your turn!!! ****************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
**********************************************

**************** John, Do you wanna bet? ****************
Please enter YES/NO : yes
************************************************

**************** Enter the valid bet amount ****************
Please enter : 200
************************************************
Invalid amount!, Try again

**************** Enter the valid bet amount ****************
Please enter : 80
************************************************

**************** Jancy your turn!!! ****************
**************** Jancy Cards ****************
	Card[id=73, faceValue=6, suit=♣]
**********************************************

**************** Jancy, Do you wanna bet? ****************
Please enter YES/NO : yes
************************************************

**************** Enter the valid bet amount ****************
Please enter : 50
************************************************
************************************************

**************** Time to get more Cards ****************
**************** John Cards ****************
	Hidden Card[id=13, faceValue=***, suit=***]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
**********************************************
**************** Jancy Cards ****************
	Hidden Card[id=73, faceValue=***, suit=***]
	Card[id=9, faceValue=3, suit=♣]
	Card[id=60, faceValue=2, suit=♠]
**********************************************
************************************************

**************** Great! Now time to Hit or Stand ****************

**************** John your turn!!! ****************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
**********************************************

**************** John, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
	Card[id=15, faceValue=4, suit=♥]
**********************************************

**************** John, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
	Card[id=15, faceValue=4, suit=♥]
	Card[id=2, faceValue=A, suit=♦]
**********************************************

**************** John, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
	Card[id=15, faceValue=4, suit=♥]
	Card[id=2, faceValue=A, suit=♦]
	Card[id=103, faceValue=K, suit=♥]
**********************************************

**************** John, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
	Card[id=15, faceValue=4, suit=♥]
	Card[id=2, faceValue=A, suit=♦]
	Card[id=103, faceValue=K, suit=♥]
	Card[id=66, faceValue=4, suit=♦]
**********************************************

**************** John, make your move ****************
Please enter HIT/STAND : stand
************************************************

**************** Jancy your turn!!! ****************
**************** Jancy Cards ****************
	Card[id=73, faceValue=6, suit=♣]
	Card[id=9, faceValue=3, suit=♣]
	Card[id=60, faceValue=2, suit=♠]
**********************************************

**************** Jancy, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** Jancy Cards ****************
	Card[id=73, faceValue=6, suit=♣]
	Card[id=9, faceValue=3, suit=♣]
	Card[id=60, faceValue=2, suit=♠]
	Card[id=44, faceValue=Q, suit=♠]
**********************************************

**************** Jancy, make your move ****************
Please enter HIT/STAND : hit
************************************************
**************** Jancy Cards ****************
	Card[id=73, faceValue=6, suit=♣]
	Card[id=9, faceValue=3, suit=♣]
	Card[id=60, faceValue=2, suit=♠]
	Card[id=44, faceValue=Q, suit=♠]
	Card[id=92, faceValue=10, suit=♠]
**********************************************

**************** Jancy, make your move ****************
Please enter HIT/STAND : stand
************************************************
************************************************

**************** Awesome!, Lets see dealers move! ****************

**************** Banker Card before every HIT ****************
**************** Shubham Cards ****************
	Card[id=86, faceValue=9, suit=♦]
**********************************************

**************** Banker Card before every HIT ****************
**************** Shubham Cards ****************
	Card[id=86, faceValue=9, suit=♦]
	Card[id=96, faceValue=Q, suit=♠]
**********************************************
************************************************

**************** Lets see who is WINNER of Trianta Ena!!! ****************

**************** Congratulations!!, Shubham You Won against John ****************
**************** Shubham Cards ****************
	Card[id=86, faceValue=9, suit=♦]
	Card[id=96, faceValue=Q, suit=♠]
	Card[id=45, faceValue=J, suit=♣]
**********************************************
**************** John Cards ****************
	Card[id=13, faceValue=4, suit=♣]
	Card[id=7, faceValue=2, suit=♥]
	Card[id=57, faceValue=2, suit=♣]
	Card[id=15, faceValue=4, suit=♥]
	Card[id=2, faceValue=A, suit=♦]
	Card[id=103, faceValue=K, suit=♥]
	Card[id=66, faceValue=4, suit=♦]
**********************************************
80.0 $ credited to Shubham's Account
Congratulations!!, Jancy You Won against Shubham
**************** Jancy Cards ****************
	Card[id=73, faceValue=6, suit=♣]
	Card[id=9, faceValue=3, suit=♣]
	Card[id=60, faceValue=2, suit=♠]
	Card[id=44, faceValue=Q, suit=♠]
	Card[id=92, faceValue=10, suit=♠]
**********************************************
**************** Shubham Cards ****************
	Card[id=86, faceValue=9, suit=♦]
	Card[id=96, faceValue=Q, suit=♠]
	Card[id=45, faceValue=J, suit=♣]
**********************************************
100.0 $ credited to Jancy's Account
50.0 $ deducted from Shubham's Account

**************** John, Wanna Cash Out? ****************
Please enter YES/NO : yes
************************************************

**************** Jancy, Wanna Cash Out? ****************
Please enter YES/NO : no
************************************************

**************** John : dropping off! ****************

**************** John transactions ****************
Transaction[Action:  Initial Deposit Value: 100.0]
Transaction[Action:  Debited Value: -80.0]
************************************************

**************** Drawing face down card to all players ****************

**************** Drawing face up card to banker ****************
**************** Shubham Cards ****************
	Card[id=38, faceValue=10, suit=♦]
**********************************************

**************** Get ready, time to place bets ****************

**************** Jancy your turn!!! ****************
**************** Jancy Cards ****************
	Card[id=32, faceValue=8, suit=♠]
**********************************************

**************** Jancy, Do you wanna bet? ****************
Please enter YES/NO : no
************************************************
************************************************

**************** No one placed the bet for this round! ****************

**************** Jancy, Wanna Cash Out? ****************
Please enter YES/NO : yes
************************************************

**************** Jancy : dropping off! ****************

**************** Jancy transactions ****************
Transaction[Action:  Initial Deposit Value: 100.0]
Transaction[Action:  Debited Value: -50.0]
Transaction[Action:  Credited Value: 100.0]
************************************************

**************** Either banker is out of cash or all players left!! ****************

**************** Thanks for Playing !!! ****************

**************** Shubham transactions ****************
Transaction[Action:  Initial Deposit Value: 300.0]
Transaction[Action:  Credited Value: 80.0]
Transaction[Action:  Debited Value: -50.0]
************************************************