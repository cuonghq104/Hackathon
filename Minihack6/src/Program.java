import main.GameWindow;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();

    }
}
