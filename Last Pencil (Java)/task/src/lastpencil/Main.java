package lastpencil;
import java.util.Scanner;

public class Main {

    public Scanner scanner = new Scanner(System.in);
    public int count;
    public String possibleWinner;
    public int take;
    public String takeStr;

    public String firstPlayer;
    public String secondPlayer;
    public String player1;
    public String player2;


    public String pencils;
    public String str = "|";
    public String bot;


    public void playGame() {
        // gameplay methods
        numberOfPencils();
        defineFirstPlayer();
        playerCheck();
        turns();
    }

    public void takeCheck(String takeStr) {
        take = 0;
        try {
            take = Integer.parseInt(takeStr);
        } catch (Exception e) {
            System.out.println("Possible values: '1', '2' or '3' ");
            takeStr = scanner.nextLine();
            takeCheck(takeStr);
        }

        if ((take <= 0) || (take > 3)) {
            System.out.println("Possible values: '1', '2' or '3' ");
            takeStr = scanner.nextLine();
            takeCheck(takeStr);
        } else if (take > count) {
            System.out.println("Too many pencils were taken ");
            takeStr = scanner.nextLine();
            takeCheck(takeStr);
        }
    }

    public void countCheck(String str) {
        if (count < 0) {
            System.out.println("Too many pencils were taken");
        } else if (count == 0) {
            System.out.println(possibleWinner + " won!");
            System.exit(0);
        } else {
            System.out.println(str.repeat(count));
        }
    }

    public void inputCheck(String pencils) {
        if (pencils.equals(" ") || (pencils.isEmpty()) ) {
            System.out.println("The number of pencils should be numeric");
            pencils = scanner.nextLine();
            inputCheck(pencils);
        }

        try {
            count = Integer.parseInt(pencils);
        } catch (Exception e) {
            System.out.println("The number of pencils should be numeric");
            pencils = scanner.nextLine();
            inputCheck(pencils);
        }

       if (count < 0) {
            System.out.println("The number of pencils should be numeric");
            pencils = scanner.nextLine();
            inputCheck(pencils);
        } else if (count == 0) {
            System.out.println("The number of pencils should be positive");
            pencils = scanner.nextLine();
            inputCheck(pencils);
        }
    }

    public void numberOfPencils() {
        System.out.println("How many pencils would you like to use: ");
        pencils = scanner.nextLine();
        count = 0;
        inputCheck(pencils);
        str = "|";
    }
     public void defineFirstPlayer() {
        player1 = "John";
        player2 = "Jack";
        bot = "Jack";
        System.out.println("Who will be the first (" + player1 + ", " + player2 + "): ");
        firstPlayer = scanner.nextLine();
    }

    public void playerCheck() {
        if (firstPlayer.equals(player1) || (firstPlayer.equals(player2))) {
            System.out.println(str.repeat(count));
        } else {
            System.out.println("Choose between " + player1 + " and " + player2);
            firstPlayer = scanner.nextLine();
            playerCheck();
        }

        // define secondPlayer
        if (firstPlayer.equals(player1)) {
            secondPlayer = player2;
        } else if (firstPlayer.equals(player2)) {
            secondPlayer = player1;
        } else {
            secondPlayer = "";
        }
    }

    public void turns() {
        while (count > 0) {
            firstPlayerTurn();
            secondPlayerTurn();
        }
    }

    public void firstPlayerTurn() {
        System.out.println(firstPlayer + "'s turn: ");
        if (firstPlayer.equals(bot)) {
            takeStr = botTurn();
            System.out.println(takeStr);
        } else {
            takeStr = scanner.nextLine();
        }
        takeCheck(takeStr);
        count = count - take;
        possibleWinner = secondPlayer;
        countCheck(str);
    }

    public void secondPlayerTurn() {
        System.out.println(secondPlayer + "'s turn: ");
        if (secondPlayer.equals(bot)) {
            takeStr = botTurn();
            System.out.println(takeStr);
        } else {
            takeStr = scanner.nextLine();
        }
        takeCheck(takeStr);
        count = count - take;
        possibleWinner = firstPlayer;
        countCheck(str);
    }

    public String botTurn() {
        String botTurn = "";
        if (count % 4 == 0) {
            botTurn = "3";
        } else if (((count - 3) % 4) == 0) {
            botTurn = "2";
        } else if (((count - 2) % 4) == 0) {
            botTurn = "1";
        } else if (count == 1) {
            botTurn = "1";
        } else {
            botTurn = "1";
        }
            return botTurn;
    }

    public static void main(String[] args) {

        Main main = new Main();
        main.playGame();
        main.scanner.close();

    }

}