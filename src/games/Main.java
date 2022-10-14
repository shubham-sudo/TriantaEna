package games;

public class Main {

    /**
     * Main method of the application
     * 
     * @param args command arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome!\nLets play TriantaEnaGame Today");
        TriantaEnaGame game = new TriantaEnaGame();
        game.introduce();
        game.initialize();
        game.start();
        game.end();
    }
}
