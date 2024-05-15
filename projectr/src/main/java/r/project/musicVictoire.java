package r.project;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class musicVictoire implements IObserver{
    @Override
    public void update() {
        try {
            String musicPath = "projectr/src/main/java/r/project/musique/victoryTheme.mp3";
            Media sound = new Media(new File(musicPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la lecture de la musique : ");
        }
    }
}
