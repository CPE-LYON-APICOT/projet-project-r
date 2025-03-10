package r.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override

    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("inventory"), 640, 480);

        stage.setScene(scene);
        stage.show(); 
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();


        /*for (Hero unHero : LstHeroes) {
            System.err.println("Nom: "+unHero.GetNom());
            System.err.println("Description: "+unHero.GetDescription());
            System.err.println("Image: "+unHero.GetImage());
            System.err.println("Faction: "+unHero.GetFaction().GetNom()+"\n");
        }*/


    }



}