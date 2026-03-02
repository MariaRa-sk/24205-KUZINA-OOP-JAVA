package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
    private NumberGenerator generator;
    private Validator validator;
    private Console console;
    private String secretNumber;

    public Game() {
        generator = new NumberGenerator();
        validator = new Validator();
        console = new Console();
    }

    private int[] calculateBullsAndCows(String guess, String secret) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == secret.charAt(i)) {
                bulls++;
            }
            else if (secret.indexOf(guessChar) != -1) {
                cows++;
            }
        }

        return new int[]{bulls, cows};
    }

    /**
     * Запускает игру
     */
    public void start() {
        secretNumber = generator.generateNumber();
        logger.info("----НОВАЯ ИГРА----");
        logger.info("Загадано число: {}", secretNumber);
        console.println("Попробуй угадать 4-значное число с разными цифрами!");

        while (true) {
            console.print("Твой вариант: ");
            String guess = console.read();

            if (!validator.isValid(guess)) {
                console.println("Неверный формат ввода! Нужно 4 разных цифры. Попробуй еще раз.");
                continue;
            }

            int[] result = calculateBullsAndCows(guess, secretNumber);
            int bulls = result[0];
            int cows = result[1];

            console.println("Результат: " + bulls + " бык(а) и " + cows + " коров(ы)");
            logger.info("Результат: {} быков, {} коров", bulls, cows);

            if (bulls == 4) {
                console.println("Победа! Ты угадал(а) число " + secretNumber);
                logger.info("Победа!");
                break;
            }
        }
        logger.info("----ИГРА ЗАКОНЧЕНА----");
    }
}

