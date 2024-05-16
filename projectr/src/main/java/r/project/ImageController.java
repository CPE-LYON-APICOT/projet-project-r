package r.project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageController {

    public void afficherImage(ImageView imageView, String lienImage) {
        Image image = new Image(getClass().getResourceAsStream(lienImage));
        imageView.setImage(image);
    }
}
