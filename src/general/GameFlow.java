package general;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.GameLevel;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * Game flow class.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int score;
    private int lives;
    private Utils utils;

    /**
     * Constructs a GameFlow by given AnimationRunner and KeyboardSensor.
     *
     * @param animationRunner the given animationRunner.
     * @param keyboardSensor  the given keyBoardSensor.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.utils = new Utils();
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        // -100 because add 100 each level except start
        this.score = -100;
        // -1 because reduce 1 live each oneTurn except start of level
        this.lives = this.utils.getStdLives();
    }

    /**
     * Runs game levels.
     *
     * @param levels the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // adds 100 to score each level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score + 100, this.lives);
            level.initialize();

            // level has more blocks and player has more lives
            while (level.getNumBlocks() > 0 && this.lives > 0) {

                level.playOneTurn();

                // added
                this.lives = level.getNumLives();
                this.score = level.getScore();
            }

            if (level.getNumBlocks() == 0) {
                continue;
            }

            // if no more lives
            if (this.lives < 1) {
                break;
            }
        }

        this.animationRunner.run(new EndScreen(this.keyboardSensor, this.lives, this.score));
    }
}