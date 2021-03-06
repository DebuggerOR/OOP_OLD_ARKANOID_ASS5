package animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import collisions.Collidable;
import levels.LevelInformation;
import general.Counter;
import general.GameEnvironment;
import general.Utils;
import geometry.Rectangle;
import geometry.Point;
import listeners.BallAdder;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import listeners.LivesTrackingListener;
import sprites.Block;
import sprites.ScoreIndicator;
import sprites.Paddle;
import sprites.Border;
import sprites.SpriteCollection;
import sprites.StartingPoint;
import sprites.Ball;
import sprites.KillingBlock;
import sprites.IntroducingBlock;
import sprites.LivesIndicator;
import sprites.NameIndicator;
import sprites.Sprite;

import java.util.List;

/**
 * Game level class.
 *
 * @author Ori.
 */
public class GameLevel implements Animation {
    // general
    private Utils utils;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation info;
    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;
    // listeners
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private BallAdder ballAdder;
    private ScoreTrackingListener scoreUpdater;
    private LivesTrackingListener livesUpdater;
    // animation
    private boolean running;
    // paddle
    private Paddle paddle;

    /**
     * Constructs a gameLevel.
     *
     * @param info            the info of the level.
     * @param keyboard        the keyboard.
     * @param animationRunner the animation runner.
     * @param score           the score.
     * @param lives           the lives.
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard,
                     AnimationRunner animationRunner, int score, int lives) {
        this.info = info;
        this.utils = new Utils();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(utils.getStdWindowWidth(), utils.getStdWindowHeight(),
                utils.getStdBorderSize());
        this.keyboard = keyboard;
        this.animationRunner = animationRunner;
        this.paddle = new Paddle(info.paddleWidth(), info.paddleHeight(), info.paddleSpeed(), environment, keyboard);
        this.scoreUpdater = new ScoreTrackingListener(new Counter(score));
        this.livesUpdater = new LivesTrackingListener(new Counter(lives));
        this.ballAdder = new BallAdder(this);
        this.blockRemover = new BlockRemover(this, new Counter(info.numberOfBlocksToRemove()));
        this.ballRemover = new BallRemover(this, new Counter(info.numberOfBalls()));
    }

    /**
     * Initializes a new game, creates the blocks, ball, paddle and add them
     * to the game.
     */
    public void initialize() {
        // background with shapes on it
        this.info.getBackground().addToGame(this);

        // borders up, right and left (numbers clockwise 1 - up, 2 - right etc.)
        new Border(1).addToGame(this);
        new Border(2).addToGame(this);
        new Border(4).addToGame(this);
        // killing block instead of down border (small height)
        int downHeight = 3;
        new KillingBlock(new Rectangle(new Point(0, this.utils.getStdWindowHeight() - downHeight),
                this.utils.getStdWindowWidth(), downHeight), this.ballRemover).addToGame(this);

        // killing and introducing blocks
        new KillingBlock(this.info.killingRectangle(), this.ballRemover).addToGame(this);
        new IntroducingBlock(this.info.introducingRectangle(), this.ballAdder).addToGame(this);

        // starting point for the balls
        new StartingPoint(this.utils.getStdBallCircle()).addToGame(this);
        // score indicator, lives and name at the top
        new ScoreIndicator(this).addToGame();
        new LivesIndicator(this).addToGame();
        new NameIndicator(this).addToGame();

        // the blocks with their listeners
        List<Block> blocks = this.info.blocks();
        for (Block block : blocks) {
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreUpdater);
            block.addToGame(this);
        }
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Does one frame of animation.
     *
     * @param surface to draw on.
     */
    public void doOneFrame(DrawSurface surface) {
        // run pause if press p
        if (this.keyboard.isPressed("p")) {
            this.animationRunner.run(new PauseScreen(this.keyboard));
        }

        // stopping condition no balls or no blocks
        if (this.ballRemover.getRemainingBalls().getValue() == 0) {
            this.running = false;
            // decrease life
            this.livesUpdater.getCurrentLives().decrease(1);
            // init remaining balls
            this.ballRemover.getRemainingBalls().increase(this.info.numberOfBalls());
        }
        if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
            this.running = false;
        }

        // draw sprites and notify time passed
        this.sprites.drawAllOn(surface);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Plays one turn.
     */
    public void playOneTurn() {
        // first create paddle and balls
        this.createPaddleAndBalls();
        // run countdown from 3 (3, 2, 1 and GO)
        this.animationRunner.run(new CountDownAnimation(500, 3, this.sprites));
        // is running
        this.running = true;
        // run this
        this.animationRunner.run(this);
    }

    /**
     * Creates a new paddle and balls.
     */
    public void createPaddleAndBalls() {
        // create and add paddle
        this.paddle.removeFromGame(this);
        this.paddle = new Paddle(info.paddleWidth(), info.paddleHeight(), info.paddleSpeed(), environment, keyboard);
        this.paddle.addToGame(this);

        // create and add balls according to numBalls
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            new Ball(this.info.initialBallVelocities().get(i), this.environment).addToGame(this);
        }
    }

    /**
     * Adds a collidable object.
     *
     * @param collidable a collidable object.
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * Adds a sprite object.
     *
     * @param sprite a sprite object.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * Removes collidable.
     *
     * @param collidable the collidable to remove.
     */
    public void removeCollidable(Collidable collidable) {
        this.environment.removeCollidable(collidable);
    }

    /**
     * Removes sprite.
     *
     * @param sprite the sprite to remove.
     */
    public void removeSprite(Sprite sprite) {
        this.sprites.removeSprite(sprite);
    }

    /**
     * Gives the game environment.
     *
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Gives the balls size.
     *
     * @return the balls size.
     */
    public int getBallsSize() {
        return this.utils.getStdBallSize();
    }

    /**
     * Gives the ball remover.
     *
     * @return the ball remover.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * Gives the score updater.
     *
     * @return the score updater.
     */
    public ScoreTrackingListener getScoreUpdater() {
        return this.scoreUpdater;
    }

    /**
     * Gives the lives updater.
     *
     * @return the lives updater.
     */
    public LivesTrackingListener getLivesUpdater() {
        return this.livesUpdater;
    }

    /**
     * Gives the num of the balls.
     *
     * @return the num of the balls.
     */
    public int getNumBalls() {
        return this.ballRemover.getRemainingBalls().getValue();
    }

    /**
     * Gives the speed of the balls.
     *
     * @return the speed of the balls.
     */
    public int getBallsSpeed() {
        if (this.info.initialBallVelocities().size() == 0) {
            return 0;
        }
        return (int) this.info.initialBallVelocities().get(0).getSpeed();
    }

    /**
     * Gives the num of the blocks.
     *
     * @return the num of the blocks.
     */
    public int getNumBlocks() {
        return this.blockRemover.getRemainingBlocks().getValue();
    }

    /**
     * Gives the num of the lives.
     *
     * @return the num of the lives.
     */
    public int getNumLives() {
        return this.livesUpdater.getCurrentLives().getValue();
    }

    /**
     * Gives the score.
     *
     * @return the score.
     */
    public int getScore() {
        return this.scoreUpdater.getCurrentScore().getValue();
    }

    /**
     * Gives the name of the level.
     *
     * @return the name of the level.
     */
    public String getLevelName() {
        return this.info.levelName();
    }
}