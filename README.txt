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
1. Navigate to the directory "code/pa2" after unzipping the files
2. Run the following instructions:
javac -d bin src/Common/*.java src/Validators/*.java src/*.java
java -cp bin Main

## Input/Output Example
---------------------------------------------------------------------------
