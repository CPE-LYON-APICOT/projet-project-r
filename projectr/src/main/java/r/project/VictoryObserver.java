package r.project;
import java.util.Timer;
import java.util.TimerTask;

public class VictoryObserver implements IKeyObserver {
    private static final String SEQUENCE = "victory";
    private StringBuilder inputSequence = new StringBuilder();
    private Timer timer;
    private static final int TIMEOUT = 20000; // 20 secondes en millisecondes

    public VictoryObserver() {
        resetTimer();
    }

    @Override
    public void update(char key) {
        inputSequence.append(key);

        if (inputSequence.length() > SEQUENCE.length()) {
            inputSequence.deleteCharAt(0);
        }

        if (inputSequence.toString().equals(SEQUENCE)) {
            afficherPopupVictoire();
            resetSequence();
        }

        resetTimer();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                resetSequence();
            }
        }, TIMEOUT);
    }

    private void resetSequence() {
        inputSequence.setLength(0);
    }

    private void afficherPopupVictoire() {
        System.out.println("Victoire !");
        // Ici vous pouvez ajouter le code pour afficher un popup de victoire
    }
}