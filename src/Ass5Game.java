
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.Sleeper;
import general.Utils;
import general.GameFlow;
import levels.DirectHit;
import levels.WideEasy;
import levels.Green3;
import levels.FinalFour;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class.
 */
public class Ass5Game {

    /**
     * Runs the game.
     *
     * @param args of user input. no use.
     */
    public static void main(String[] args) {
        Utils utils = new Utils();

        GUI gui = new GUI("Arkanoid", utils.getStdWindowWidth(), utils.getStdWindowHeight());

        Sleeper sleeper = new Sleeper();

        AnimationRunner animationRunner = new AnimationRunner(sleeper, gui);

        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor());

        List<LevelInformation> levels = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "1":
                    levels.add(new DirectHit());
                    break;
                case "2":
                    levels.add(new WideEasy());
                    break;
                case "3":
                    levels.add(new Green3());
                    break;
                case "4":
                    levels.add(new FinalFour());
                    break;
                default:
            }
        }

        // if no input levels run 1 2 3 4
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }

        gameFlow.runLevels(levels);

        gui.close();
    }
}