// ID: 315789461

import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import game.GameFlow;
import levels.LevelInformation;
import levels.FirstLevel;
import levels.SecondLevel;
import levels.ThirdLevel;
import levels.FourthLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game class:
 * includes only the main method that creates levels, initializes and runs it.
 *
 * @author Yuval Alroy
 */
public class Ass6Game {

    /**
     * main method that creates levels in the game, initializes and runs it according to the order we get in args.
     *
     * @param args represents command-line arguments.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keyboard = runner.getGui().getKeyboardSensor();
        GameFlow g = new GameFlow(runner, keyboard);
        List<LevelInformation> levels = new ArrayList<>();

        int flag = 0;
        for (String arg : args) {
            try {
                int levelNumber = Integer.parseInt(arg);
                if (levelNumber <= 4 && levelNumber >= 1) {
                    flag = 1;
                    if (levelNumber == 1) {
                        levels.add(new FirstLevel());
                    }
                    if (levelNumber == 2) {
                        levels.add(new SecondLevel());
                    }
                    if (levelNumber == 3) {
                        levels.add(new ThirdLevel());
                    }
                    if (levelNumber == 4) {
                        levels.add(new FourthLevel());
                    }
                }
            } catch (Exception ignored) { }
        }

        // if the arguments was illegal (all of them) - run the game in the default order.
        if (flag == 0) {
            levels.add(new FirstLevel());
            levels.add(new SecondLevel());
            levels.add(new ThirdLevel());
            levels.add(new FourthLevel());
        }
        g.runLevels(levels);
    }
}
