## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

Objects
- games.cards.Suit  [value] (getValue)
- abstract Card.Card  [faceValue, games.cards.Suit, isAvailable] (moveInDeck, moveOutFromDeck, toString, equals, value)
- TriantaCard extends Card.Card (isfaceUp, setFaceUp, isAce, value, minValue)
- Deck  [games.games.cards, remainingCards] (shuffle, getCard, addCard, addCards, reset)
- abstract games.players.Player  [name]
- games.players.TriantaEnaPlayer [playerBank, playingHand] (score)
- PlayerHand [games.games.cards] (score, minScore, addCard)
- PlayerBankAccount [totalAmount, betAmount] (creditAccount, debitAccount, placeBet)
- Menu [listOfChoices] (display, getChoice)
- GameMenu extends Menu
- PlayerMenu extends Menu
- Game (setUpGame, initializePlayers, startGame, endGame, startMessage)
- TriantaEnaGame extends Game [players, banker, deck] (isBurst, isNaturalTriantaEna, hit, stand, play, playRound, checkBlackJack)