package games.utils;

import java.util.*;

public class Menu {
    public static final String NO = "NO";
    public static final String HIT = "HIT";
    public static final String YES = "YES";
    public static final String STAND = "STAND";
    private static final String INVALID_INPUT_TRY_AGAIN = "Invalid Input! Try again.";
    private static final String INVALID_CHOICE_TRY_AGAIN = "Invalid Choice! Try again.";
    public static final ArrayList<String> YES_NO_CHOICE = new ArrayList<>(Arrays.asList(YES, NO));
    public static final ArrayList<String> HIT_STAND = new ArrayList<>(Arrays.asList(HIT, STAND));
    private static final Scanner scanner = PublicScanner.getScanner();

    public static void message(String message){
        System.out.println("\n**************** " + message + " ****************");
    }

    public static void footer(){
        System.out.println("************************************************");
    }

    /**
     * Ask user for selecting from the given options and compare with true value
     *
     * @param message message to print to ask for input
     * @param options options to show or validate the entered value
     * @param trueChoice true value to return true
     * @return true if value matches with trueChoice, false otherwise
     */
    public static boolean getUserYesNo(String message, ArrayList<String> options, String trueChoice){
        String choice = null;

        message(message);
        do{
            try{
                System.out.print("Please enter " + String.join("/", options) + " : ");
                choice = scanner.nextLine().trim().toUpperCase();
            }catch (Exception e){
                System.out.println(INVALID_INPUT_TRY_AGAIN);
            }
            if (!options.contains(choice)){
                System.out.println(INVALID_CHOICE_TRY_AGAIN);
            }
        } while (!options.contains(choice));
        footer();
        return choice != null && choice.equals(trueChoice);
    }

    public static void display(String message){
        footer();
        System.out.println(message);
        footer();
    }

    /**
     * Get integer input from the user
     *
     * @param message message to show for asking input
     * @return entered value by the user
     */
    public static int getIntegerInput(String message){
        int option = 0;
        message(message);
        while (option <= 0){
            try{
                System.out.print("Please enter : ");
                option = Integer.parseInt(scanner.nextLine().trim());
            }catch (Exception e){
                System.out.println(INVALID_INPUT_TRY_AGAIN);
            }
            if (option <= 0){
                System.out.println(INVALID_CHOICE_TRY_AGAIN);
            }
        }
        footer();
        return option;
    }

    /**
     * Ask for double type value from the user
     *
     * @param message message to show for asking input
     * @return value entered by user.
     */
    public static double getDoubleInput(String message){
        double option = 0;
        message(message);
        while (option <= 0){
            try{
                System.out.print("Please enter : ");
                option = Double.parseDouble(scanner.nextLine().trim());
            }catch (Exception e){
                System.out.println(INVALID_INPUT_TRY_AGAIN);
            }
            if (option <= 0){
                System.out.println(INVALID_CHOICE_TRY_AGAIN);
            }
        }
        footer();
        return option;
    }

    /**
     * Ask for String input from the user
     *
     * @param message message to show for asking input from user
     * @return valid value entered by user
     */
    public static String getStringInput(String message){
        String input;
        message(message);
        while (true){
            System.out.print("Please enter : ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()){
                System.out.println(INVALID_INPUT_TRY_AGAIN);
            } else {
                break;
            }
        }
        footer();
        return input;
    }
}
