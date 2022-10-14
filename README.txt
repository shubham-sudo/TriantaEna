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
3)

The UML diagram (TriantaEnaUML1) clearly shows that we have following relationship between the classes:
1)	We have a Player class which extends the Person class and implements the PlayerEssential class. PlayerEssential class just has some basic methods which are required by every player in all the games. The Person class describes the general attributes associated with a person like his name, username etc. Every player is-a person, hence the Player class extends the Person class.
2)	To play Trianta Ena, we have created another class named TePlayer which further extends the Player class. This TePlayer Class is specific to players who will play Trianta Ena and have the properties and methods of being a player along with attributes and methods required for playing Trianta Ena.
3)	We have a Card class which extends a Piece class because a card is-a piece specific to the card games.
4)	We also have a TriantaEna Class which implements the Game interface. The Game interface declares some basic methods that every game should have. TriantaEna class has properties and methods of Game class and it has other properties and methods which are essential to play Trianta Ena.
5)	Other than these classes we have certain classes which we are using by creating their objects (described in the TriantaEnaUML2). Some of the important classes (described in detail in the FILES section) are as follows:
	a.	GameRoom class: It initiates a common player pool which is shared by all the games. This class also creates the object of type TriantaEna which will be used to play the game.
	b.	Deck class: It contains an arraylist of Card objects and this can be used in any playing card-based games.
	c.	 PlayerBankAccount Class: This class deals with the finances of a player like his bet amount and balance.
	d.	StatsBoard Class: This is used to update the win or loss of players and these stats are shown at the end of the game.
	e.	We have 02 comparators i.e. PlayerBalanceComparator() and PlayerTeamComparator() to sort the player objects according to their balance and team respectively.
	f.	 We have 08 validators, which are used to validate different user inputs to avoid any exception.


Benefits of the adopted object structure
The adopted structure is flexible to create any game and its key features are as follows:
1)	The game logic is defined in one class with the help of mainly 04 methods:
	a.	initialiseGame()
	b.	playGame()
	c.	makeMove()
	d.	convertPlayers()
	e.	Apart from these there are other methods to help with the implementation of the game logic.
All these functions are part of the game interface and are required to be implemented by every game. The same interface was used to create the Tic Tac Toe and Order & Chaos in the last assignment. The convertPlayer() method helps in converting the players (with certain attributes and methods) to make them compatible to play the game whose convertPlayers() method is called. This can be thought of a training of players before the game starts. This gives us freedom to play any game with the same players.
2)	The players are always added to the player pool as the object of Player Class which is very basic and not necessarily have enough attributes and methods to play any game. We can create specific Player class (like TePlayer) for every game that extends the Player Class and includes its own functionalities to it and make the players ready to play the game.
3)	 The Piece Class is the base class for any pieces in the game. The piece may refer to many objects involved in the game like cards, or ‘O’s and ‘X’s in Tic Tac Toe and Order and Chaos. It could be tickets or houses in case of monopoly or any other physical entity that is required to play the game. We can create as many pieces as required by extending the Piece class to that new class which defines the new object (as done in case of Card Class).
4)	The StatsBoard Class is a static class that is shared by all the games and every player gets his own stats table whenever he starts plays a game. This stats table holds the statistics of players win, loss and draws and updates the stats board after one round of every game. The same class is used in case of Tic Tac Toe and O&C.

Changes Made During This Assignment
Most of the components in this assignment are just reused as they were in the previous assignment. Other than few new classes to hold the game logic and supporting objects, the only new major change made in this assignment to facilitate the development of additional game is the addition of convertPlayers() method to the Game interface and subsequently to the TriantaEna Class. This was an essential change as the players are added to the player pool before they decide which game to play. Therefore, it is not possible to know which type of player should be added to the playerPool which is a ArrayList.
To resolve this problem, I created a new interface PlayerEssential and put basic methods that are associated with every player. Now while creating the playerPool ArrayList, I used generics to define that it can hold any player which implements PlayerEssential interface.
Now, the other problem was how can we have a player of one type like players who can play Tic Tac Toe and O&C and add the attributes and methods essential for playing Trianta Ena. I achieved this by adding the convertPlayers() function which converts all the players in the playerPool to the type of players required to play a specific game.



## Files
--------------------------------------------------------------------------

1) src/Common/Card.java
This class extends the Piece Class and defines the basic functionality of a playing card which will compose a deck.

2) src/Common/Deck.java
This Class defines a deck of cards (ArrayList of object of type Card) and defines the basic card operation like shuffleDeck(). This class can be reused for any playing card based game.

3) src/Common/Person.java
This class describes the attributes of a person like 'First Name' and 'User Name' which is inherited by Player class.

4) src/Common/Piece.java
This class describes the Game Pieces like 'X' and 'O' for Tic Tac Toe and Order And Chaos. We can have different types of pieces by creating different Piece class for each piece. If there are any rules specific to a piece, they can also be described here.

5) src/Common/Player.java
This class holds the data relevant to a Player. It extends Person class, hence, it has attributes of a person as well. There is an additional HashMap playerHand<Integer, Piece> in this class which describes the pieces that a player has. This can be used to give any number of Pieces to the player like cards or currency etc.

6) src/Common/PlayerBalanceComparator.java
This is a class which implements Comparator<Player> which takes players as input and sort them on the basis of the maximum balance in the account of the players. We use this class to find out if the players have balance more than the banker. If yes, then we give the opportunity to the players to be the banker in the descending order of their balance.

7) src/Common/PlayerBankAccount.java
The class object to this class is associated with every player and it holds the financial data relevant to a Player. For example how much balance does the player have and for how much amount he has placed a bet. This class can be used for any game involving gambling or which requires players to have money with them.

8) src/Common/PlayerEssential.java
This is an interface that is implemented by the Player class and defines the basic operations and constants essential for a player.

9) src/Common/PlayerTeamComparator.java
This is a class which implements Comparator<Player> which takes players as input and sort them on the basis of the team to which they belong. This will later help in putting the players in a Queue to control the players turn.

10) src/Common/StatsBoard.java
This class holds the data related to player's statistics specific to a game. It is responsible for managing and updating the statistics for players involved in the game and print the statistics at the end of the game.

11) src/Common/TePlayer.java
This is a special class for a Trianta Ena Player. It extends the Player class which in turn extends the Person Class and implements the Player Essential interface. This class holds all the essential methods and variables associated with a TE player.

12) src/Validators/AmountValidator.java
This class returns a boolean value after checking the user input of the betting amount against the total balance that he has.

13)src/Validators/GameSelectionValidator.java
This class returns a boolean value after checking the user input for game selection (Tic tac toe or Order and chaos or Trianta Ena).

14)src/Validators/HitStandValidator.java
This class returns a boolean value after checking the user input for the value of 'H' for hit and 'S' for stand.

15)src/Validators/MainMenuValidator.java
This class validates the options selection in the main menu.

16)src/Validators/PlayerIdValidator.java
This class validates the IDs of the players when forming team. The player ID should be valid when creating a team i.e. the player with that ID should exist and the player should not already be in a team.

17)src/Validators/PlayerMenuValidator.java
This class validates the menu options for the player options selection like Add Player and View Player.

18)src/Validators/TeamValidator.java
This class validates that the players onces become part of one team, cannot be part of another team.

19)src/Validators/YesNoValidator.java
This class validates the user input for 'Yes' or 'No' type questions.

20)src/Game.java
This is an interface that describes the basic methods that every game should have.

21)src/GameRoom.java
This class is responsible for creating the objects all the games in the program and
 initializing the players and creating the teams of these players which will play
 the game selected later in the program.

22)src/Menu.java
This is the Main class responsible for Menu handling and handling the flow of the program. It is also responsible for calling the initialisePlayers and initialiseTeams methods of the GameRoom class. It also sets up the game for by calling initialiseGame() method of the games and initiate the game play.

23)src/TriantaEna.java
This is the class that implements the Game interface and has functionality specific to the Trianta Ena game like setting up of game and dealing cards and functionality like hit() and stand().

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
***************************** Main Menu ********************************
[1] Player Menu
[2] Game Menu
[3] Exit
************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

[INPUT] Enter Player 1's First Name : Prateek
[INPUT] Enter Player  1's  User Name (If the length is more than 15 characters then only first 15 characters will be kept): Prateek
***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

[INPUT] Enter Player 2's First Name : Dhruv
[INPUT] Enter Player  2's  User Name (If the length is more than 15 characters then only first 15 characters will be kept): Dhruv
***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

[INPUT] Enter Player 3's First Name : Atul
[INPUT] Enter Player  3's  User Name (If the length is more than 15 characters then only first 15 characters will be kept): Atul
***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

[INPUT] Enter Player 4's First Name : Rohit
[INPUT] Enter Player  4's  User Name (If the length is more than 15 characters then only first 15 characters will be kept): Rohit
***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 1

[INPUT] Enter Player 5's First Name : Rohan
[INPUT] Enter Player  5's  User Name (If the length is more than 15 characters then only first 15 characters will be kept): Rohan
***************************** Player Menu ********************************
[1] Add Player
[2] View Player(s)
[3] Continue
**************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 3

***************************** Main Menu ********************************
[1] Player Menu
[2] Game Menu
[3] Exit
************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 2

**********************Game Selection Menu*************************
[1] Trianta Ena
[2] Exit
******************************************************************

[INPUT] Enter the  number corresponding to your choice : 3


************************************ Trianta Ena *****************************************

The objective of the game is to accumulate a hand of cards that equals 31 (Trianta ena!)
or a hand that has a card value greater than that of the Dealers without exceeding 31.
(Source: TriantaEna PDF)

******************************************************************************************


[MESSAGE] Players currently in the player pool :

Player ID : 1	First Name : Prateek	User Name : Prateek	Player Team : 0

Player ID : 2	First Name : Dhruv	User Name : Dhruv	Player Team : 0

Player ID : 3	First Name : Atul	User Name : Atul	Player Team : 0

Player ID : 4	First Name : Rohit	User Name : Rohit	Player Team : 0

Player ID : 5	First Name : Rohan	User Name : Rohan	Player Team : 0

[INPUT] Do you want to add more players - Y/N: n


[MESSAGE] Players currently in the player pool :

Player ID : 1	First Name : Prateek	User Name : Prateek	Player Team : 0

Player ID : 2	First Name : Dhruv	User Name : Dhruv	Player Team : 0

Player ID : 3	First Name : Atul	User Name : Atul	Player Team : 0

Player ID : 4	First Name : Rohit	User Name : Rohit	Player Team : 0

Player ID : 5	First Name : Rohan	User Name : Rohan	Player Team : 0

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 1 : 1

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 2 : 2

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 3 : 3

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 4 : 4

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 5 : 5

[MESSAGE] Enter 0 to continue with the game after creating the teams.
[MESSAGE] Enter the Player IDs separated by a comma to select the players for one TEAM.
For Example : Enter 1,2,3 to select player 1, player 2 and player 3 to be in one TEAM.
[INPUT] Select Players for TEAM 6 : 0

[MESSAGE] Team 1 will be the BANKER in the first game.
[MESSAGE] Cards in Deck for the NEW GAME : 104

************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $100	Bet Amount : $0	Hand : 5	Total : 5


Player : Atul	Balance : $100	Bet Amount : $0	Hand : *

Player : Rohit	Balance : $100	Bet Amount : $0	Hand : *

Player : Rohan	Balance : $100	Bet Amount : $0	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Dhruv enter the amount for which you want to place bet : 50


************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *

Player : Atul	Balance : $100	Bet Amount : $0	Hand : 6	Total : 6


Player : Rohit	Balance : $100	Bet Amount : $0	Hand : *

Player : Rohan	Balance : $100	Bet Amount : $0	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Atul enter the amount for which you want to place bet : 100


************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *

Player : Rohit	Balance : $100	Bet Amount : $0	Hand : A	Total : 11


Player : Rohan	Balance : $100	Bet Amount : $0	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohit enter the amount for which you want to place bet : 20


************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *

Player : Rohan	Balance : $100	Bet Amount : $0	Hand : J	Total : 10


************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohan enter the amount for which you want to place bet : 70


************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : 5	4	9	Total : 18


Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Dhruv : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : 5	4	9	9	Total : 27


Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Dhruv : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : 6	A	3	Total : 20


Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : 6	A	3	K	Total : 30


Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3	K

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : A	T	5	Total : 26


Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3	K

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : A	T	5	K	Total : 26


Player : Rohan	Balance : $30	Bet Amount : $70	Hand : *	K	6

************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3	K

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5	K

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	Total : 26


************************************************************************
[INPUT] Player Rohan : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3	K

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5	K

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	4	Total : 30


************************************************************************
[INPUT] Player Rohan : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	Total : 10
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : *	4	9	9

Player : Atul	Balance : $0	Bet Amount : $100	Hand : *	A	3	K

Player : Rohit	Balance : $80	Bet Amount : $20	Hand : *	T	5	K

Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	4	3	Total : 33
BUSTED!!!!!!!


************************************************************************
[MESSAGE] Press enter to continue.


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	9	Total : 19
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : 5	4	9	9	Total : 27


Player : Atul	Balance : $0	Bet Amount : $100	Hand : 6	A	3	K	Total : 30


Player : Rohit	Balance : $80	Bet Amount : $20	Hand : A	T	5	K	Total : 26


Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	4	3	Total : 33


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	9	7	Total : 26
(Banker)

Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : 5	4	9	9	Total : 27


Player : Atul	Balance : $0	Bet Amount : $100	Hand : 6	A	3	K	Total : 30


Player : Rohit	Balance : $80	Bet Amount : $20	Hand : A	T	5	K	Total : 26


Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	4	3	Total : 33


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $300	Bet Amount : $0	Hand : K	9	7	Q	Total : 36
(Banker)	BUSTED!!!!!!!


Player : Dhruv	Balance : $50	Bet Amount : $50	Hand : 5	4	9	9	Total : 27


Player : Atul	Balance : $0	Bet Amount : $100	Hand : 6	A	3	K	Total : 30


Player : Rohit	Balance : $80	Bet Amount : $20	Hand : A	T	5	K	Total : 26


Player : Rohan	Balance : $30	Bet Amount : $70	Hand : J	K	6	4	3	Total : 33


************************************************************************
[MESSAGE] Press enter to continue.
[MESSAGE] After settling the bets.

************************************************************************
Player : Prateek	Balance : $200	Hand : K	9	7	Q	Total : 36
(Banker)	BUSTED!!!!!!!
WINNER!!!!!!


Player : Dhruv	Balance : $150	Hand : 5	4	9	9	Total : 27
WINNER!!!!!!


Player : Atul	Balance : $200	Hand : 6	A	3	K	Total : 30
WINNER!!!!!!


Player : Rohit	Balance : $120	Hand : A	T	5	K	Total : 26
WINNER!!!!!!


Player : Rohan	Balance : $30	Hand : J	K	6	4	3	Total : 33
BUSTED!!!!!!!


************************************************************************
[INPUT] Player Dhruv : Do you want to cash out (Y)es or (N)o : y


[MESSAGE] Player Dhruv is cashing out with $150
[INPUT] Player Atul : Do you want to cash out (Y)es or (N)o : n


[INPUT] Player Rohit : Do you want to cash out (Y)es or (N)o : n


[INPUT] Player Rohan : Do you want to cash out (Y)es or (N)o : n


[MESSAGE] Players in the game after cashing out.

************************************************************************
Player : Prateek	Balance : $200	Hand : K	9	7	Q	Total : 36
(Banker)	BUSTED!!!!!!!
WINNER!!!!!!


Player : Atul	Balance : $200	Hand : 6	A	3	K	Total : 30
WINNER!!!!!!


Player : Rohit	Balance : $120	Hand : A	T	5	K	Total : 26
WINNER!!!!!!


Player : Rohan	Balance : $30	Hand : J	K	6	4	3	Total : 33
BUSTED!!!!!!!


************************************************************************
[MESSAGE] Cards in Deck for the NEW GAME : 104

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $200	Hand : 4	Total : 4


Player : Rohit	Balance : $120	Hand : *

Player : Rohan	Balance : $30	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Atul enter the amount for which you want to place bet : 10


************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *

Player : Rohit	Balance : $120	Hand : T	Total : 10


Player : Rohan	Balance : $30	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohit enter the amount for which you want to place bet : 10


************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *

Player : Rohit	Balance : $110	Bet Amount : $10	Hand : *

Player : Rohan	Balance : $30	Hand : Q	Total : 10


************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohan enter the amount for which you want to place bet : 30

[MESSAGE] Players in the game.

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *	2	Q

Player : Rohit	Balance : $110	Bet Amount : $10	Hand : *	3	K

Player : Rohan	Balance : $0	Bet Amount : $30	Hand : *	T	9

************************************************************************

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	Total : 16


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : *	3	K

Player : Rohan	Balance : $0	Bet Amount : $30	Hand : *	T	9

************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : *	3	K

Player : Rohan	Balance : $0	Bet Amount : $30	Hand : *	T	9

************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *	2	Q	A

Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	Total : 23


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : *	T	9

************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *	2	Q	A

Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	3	Total : 26


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : *	T	9

************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $200	Hand : 6	Total : 6
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : *	2	Q	A

Player : Rohit	Balance : $110	Bet Amount : $10	Hand : *	3	K	3

Player : Rohan	Balance : $0	Bet Amount : $30	Hand : Q	T	9	Total : 29


************************************************************************
[INPUT] Player Rohan : (H)it or (S)tand : s




[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	3	Total : 9
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	3	Total : 26


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : Q	T	9	Total : 29


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	3	Q	Total : 19
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	3	Total : 26


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : Q	T	9	Total : 29


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	3	Q	5	Total : 24
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	3	Total : 26


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : Q	T	9	Total : 29


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $200	Hand : 6	3	Q	5	6	Total : 30
(Banker)

Player : Atul	Balance : $190	Bet Amount : $10	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Bet Amount : $10	Hand : T	3	K	3	Total : 26


Player : Rohan	Balance : $0	Bet Amount : $30	Hand : Q	T	9	Total : 29


************************************************************************


[MESSAGE] Banker's Turn.
[MESSAGE] Player Rohan has no money in his account to continue.
[MESSAGE] Removing player Rohan from the game.
[MESSAGE] Press enter to continue.

[MESSAGE] After settling the bets.

************************************************************************
Player : Prateek	Balance : $250	Hand : 6	3	Q	5	6	Total : 30
(Banker)	WINNER!!!!!!


Player : Atul	Balance : $190	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Hand : T	3	K	3	Total : 26


************************************************************************
[INPUT] Player Atul : Do you want to cash out (Y)es or (N)o : n


[INPUT] Player Rohit : Do you want to cash out (Y)es or (N)o : n


[MESSAGE] Players in the game after cashing out.

************************************************************************
Player : Prateek	Balance : $250	Hand : 6	3	Q	5	6	Total : 30
(Banker)	WINNER!!!!!!


Player : Atul	Balance : $190	Hand : 4	2	Q	A	Total : 27


Player : Rohit	Balance : $110	Hand : T	3	K	3	Total : 26


************************************************************************
[MESSAGE] Cards in Deck for the NEW GAME : 104

************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $190	Hand : T	Total : 10


Player : Rohit	Balance : $110	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Atul enter the amount for which you want to place bet : 90


************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : *

Player : Rohit	Balance : $110	Hand : K	Total : 10


************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohit enter the amount for which you want to place bet : 10

[MESSAGE] Players in the game.

************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : *	8	9

Player : Rohit	Balance : $100	Bet Amount : $10	Hand : *	6	4

************************************************************************

************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $100	Bet Amount : $10	Hand : *	6	4

************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : *	8	9

Player : Rohit	Balance : $100	Bet Amount : $10	Hand : K	6	4	Total : 20


************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $250	Hand : K	Total : 10
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : *	8	9

Player : Rohit	Balance : $100	Bet Amount : $10	Hand : K	6	4	A	Total : 31
TRIANTA ENA!!!!!!


************************************************************************
[MESSAGE] Press enter to continue.


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $250	Hand : K	2	Total : 12
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $100	Bet Amount : $10	Hand : K	6	4	A	Total : 31


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $250	Hand : K	2	8	Total : 20
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $100	Bet Amount : $10	Hand : K	6	4	A	Total : 31


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $250	Hand : K	2	8	8	Total : 28
(Banker)

Player : Atul	Balance : $100	Bet Amount : $90	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $100	Bet Amount : $10	Hand : K	6	4	A	Total : 31


************************************************************************


[MESSAGE] Banker's Turn.
[MESSAGE] Press enter to continue.
[MESSAGE] After settling the bets.

************************************************************************
Player : Prateek	Balance : $330	Hand : K	2	8	8	Total : 28
(Banker)

Player : Atul	Balance : $100	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $120	Hand : K	6	4	A	Total : 31
TRIANTA ENA!!!!!!
WINNER!!!!!!


************************************************************************
[INPUT] Player Atul : Do you want to cash out (Y)es or (N)o : n


[INPUT] Player Rohit : Do you want to cash out (Y)es or (N)o : n


[MESSAGE] Players in the game after cashing out.

************************************************************************
Player : Prateek	Balance : $330	Hand : K	2	8	8	Total : 28
(Banker)

Player : Atul	Balance : $100	Hand : T	8	9	Total : 27


Player : Rohit	Balance : $120	Hand : K	6	4	A	Total : 31
TRIANTA ENA!!!!!!
WINNER!!!!!!


************************************************************************
[MESSAGE] Cards in Deck for the NEW GAME : 104

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $120	Hand : K	Total : 10


Player : Atul	Balance : $100	Hand : *

************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Rohit enter the amount for which you want to place bet : 120


************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *

Player : Atul	Balance : $100	Hand : 5	Total : 5


************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Atul enter the amount for which you want to place bet : 10

[MESSAGE] Players in the game.

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *	Q	9

Player : Atul	Balance : $90	Bet Amount : $10	Hand : *	5	8

************************************************************************

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : K	Q	9	Total : 29


Player : Atul	Balance : $90	Bet Amount : $10	Hand : *	5	8

************************************************************************
[INPUT] Player Rohit : (H)it or (S)tand : s



************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *	Q	9

Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	Total : 18


************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *	Q	9

Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	Total : 20


************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *	Q	9

Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	4	Total : 24


************************************************************************
[INPUT] Player Atul : (H)it or (S)tand : h



************************************************************************
Player : Prateek	Balance : $330	Hand : 6	Total : 6
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : *	Q	9

Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	4	9	Total : 33
BUSTED!!!!!!!


************************************************************************
[MESSAGE] Press enter to continue.


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	K	Total : 16
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : K	Q	9	Total : 29


Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	4	9	Total : 33


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	K	J	Total : 26
(Banker)

Player : Rohit	Balance : $0	Bet Amount : $120	Hand : K	Q	9	Total : 29


Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	4	9	Total : 33


************************************************************************


[MESSAGE] Banker's Turn.

************************************************************************
Player : Prateek	Balance : $330	Hand : 6	K	J	5	Total : 31
(Banker)	TRIANTA ENA!!!!!!


Player : Rohit	Balance : $0	Bet Amount : $120	Hand : K	Q	9	Total : 29


Player : Atul	Balance : $90	Bet Amount : $10	Hand : 5	5	8	2	4	9	Total : 33


************************************************************************
[MESSAGE] Player Rohit has no money in his account to continue.
[MESSAGE] Removing player Rohit from the game.
[MESSAGE] Press enter to continue.
[MESSAGE] After settling the bets.

************************************************************************
Player : Prateek	Balance : $460	Hand : 6	K	J	5	Total : 31
(Banker)	TRIANTA ENA!!!!!!
WINNER!!!!!!


Player : Atul	Balance : $90	Hand : 5	5	8	2	4	9	Total : 33
BUSTED!!!!!!!


************************************************************************
[INPUT] Player Atul : Do you want to cash out (Y)es or (N)o : n


[MESSAGE] Players in the game after cashing out.

************************************************************************
Player : Prateek	Balance : $460	Hand : 6	K	J	5	Total : 31
(Banker)	TRIANTA ENA!!!!!!
WINNER!!!!!!


Player : Atul	Balance : $90	Hand : 5	5	8	2	4	9	Total : 33
BUSTED!!!!!!!


************************************************************************
[MESSAGE] Cards in Deck for the NEW GAME : 104

************************************************************************
Player : Prateek	Balance : $460	Hand : J	Total : 10
(Banker)

Player : Atul	Balance : $90	Hand : T	Total : 10


************************************************************************
[MESSAGE] Enter 0 to fold.
[INPUT] Player Atul enter the amount for which you want to place bet : 0

[MESSAGE] Players in the game.

************************************************************************
Player : Prateek	Balance : $460	Hand : J	Total : 10
(Banker)

Player : Atul	Balance : $90	Bet Amount : $0	Hand : *	5	2	FOLDED!!!


************************************************************************
[MESSAGE] Press enter to continue.
[MESSAGE] After settling the bets.

************************************************************************
Player : Prateek	Balance : $460	Hand : J	Total : 10
(Banker)

Player : Atul	Balance : $90	Hand : T	5	2	Total : 17
FOLDED!!!


************************************************************************
[INPUT] Player Atul : Do you want to cash out (Y)es or (N)o : y


[MESSAGE] Player Atul is cashing out with $90
[MESSAGE] Players in the game after cashing out.

************************************************************************
Player : Prateek	Balance : $460	Hand : J	Total : 10
(Banker)

************************************************************************
[MESSAGE] Cards in Deck for the NEW GAME : 104
[WARNING] Not enough players to play the game. Exiting now!!

********************************** Final Stats for this session *************************************
Game                 Player User Name         Team   Played      Won     Lost    Draw
TriantaEna           Prateek                     1       11        7        4       0
TriantaEna           Dhruv                       2        1        1        0       0
TriantaEna           Atul                        3        4        1        3       0
TriantaEna           Rohit                       4        4        2        2       0
TriantaEna           Rohan                       5        2        0        2       0
******************************************************************************************************
***************************** Main Menu ********************************
[1] Player Menu
[2] Game Menu
[3] Exit
************************************************************************

[INPUT] Enter the  number corresponding to the your choice : 3


Process finished with exit code 0