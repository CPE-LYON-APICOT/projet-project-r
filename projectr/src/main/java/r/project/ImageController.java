package r.project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageController {

    public void afficherImage(ImageView imageView, String lienImage) {
        // Charger l'image depuis les ressources
        Image image = new Image(getClass().getResourceAsStream(lienImage));

        // Afficher l'image dans l'ImageView
        imageView.setImage(image);
    }
}
