import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lower = 1;
        int upper = 0;
        boolean validInput = false;
        boolean playAgain = true;
        boolean changeDifficulty = false;

        while(playAgain) {
            if (changeDifficulty) {
                upper = 0;
                changeDifficulty = false;
                validInput = false;
            }

            byte currentPlayer;
            label127:
            for(; !validInput; System.out.println("Grattis, spelare " + currentPlayer + ", du har vunnit!")) {
                System.out.println("Singleplayer/ multiplayer?");
                String gameMode = sc.nextLine();
                if (gameMode.equals("multiplayer")) {
                    System.out.println("Välj svårighetsgrad? (lätt, mellan, svår)");
                    switch (sc.nextLine()) {
                        case "lätt":
                            upper = 10;
                            validInput = true;
                            break;
                        case "mellan":
                            upper = 100;
                            validInput = true;
                            break;
                        case "svår":
                            upper = 1000;
                            validInput = true;
                            break;
                        default:
                            upper = 0;
                            System.out.println("Ogiltig inmatning. Försök igen.");
                    }
                }

                currentPlayer = 1;

                while(true) {
                    label122:
                    while(true) {
                        int tries;
                        int randomNumber;
                        int guess;
                        long startTime;
                        do {
                            System.out.println("Spelare " + currentPlayer + ", det är din tur!");
                            int score = true;
                            startTime = System.currentTimeMillis();
                            tries = 1;
                            randomNumber = (int)Math.floor(Math.random() * (double)(upper - lower + 1) + (double)lower);
                            System.out.println("Gissa på ett tal mellan " + lower + "-" + upper);
                            guess = 0;
                            validInput = false;

                            while(!validInput) {
                                if (sc.hasNextInt()) {
                                    guess = sc.nextInt();
                                    sc.nextLine();
                                    validInput = true;
                                } else {
                                    System.out.println("Ogiltig inmatning. Försök igen.");
                                    sc.next();
                                }
                            }

                            while(guess != randomNumber) {
                                if (guess < randomNumber) {
                                    System.out.println("Det är för lågt, gissa igen");
                                } else {
                                    System.out.println("Det är för högt, gissa igen");
                                }

                                ++tries;
                                validInput = false;

                                while(!validInput) {
                                    if (sc.hasNextInt()) {
                                        guess = sc.nextInt();
                                        sc.nextLine();
                                        validInput = true;
                                    } else {
                                        System.out.println("Ogiltig inmatning. Försök igen.");
                                        sc.next();
                                    }
                                }
                            }
                        } while(guess != randomNumber);

                        long endTime = System.currentTimeMillis();
                        long timeTaken = endTime - startTime;
                        System.out.println("Du gissade rätt på " + tries + " försök");
                        System.out.println("Det tog dig " + timeTaken / 1000L + " sekunder att gissa rätt nummer!");
                        if (guess == randomNumber) {
                            continue label127;
                        }

                        validInput = false;

                        while(true) {
                            while(true) {
                                if (validInput) {
                                    continue label122;
                                }

                                System.out.println("Vill du spela igen? (ja/nej");
                                String answerAgain = sc.nextLine();
                                if (answerAgain.equals("ja")) {
                                    playAgain = true;
                                    System.out.println("Vill du byta svårighetsgrad? (ja/nej)");
                                    validInput = false;

                                    while(!validInput) {
                                        String answerDifficulty = sc.nextLine();
                                        if (answerDifficulty.equals("ja")) {
                                            changeDifficulty = true;
                                            validInput = true;
                                            if (changeDifficulty) {
                                            }
                                        } else if (answerDifficulty.equals("nej")) {
                                            changeDifficulty = false;
                                            validInput = true;
                                        } else {
                                            validInput = false;
                                            System.out.println("Ogiltig inmatning. (ja/nej).");
                                        }
                                    }
                                } else {
                                    if (answerAgain.equals("nej")) {
                                        playAgain = false;
                                        validInput = true;
                                        System.out.println("Tack för att du spelat!");
                                        continue label122;
                                    }

                                    System.out.println("Ogiltig inmatning. Svara ja eller nej");
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}