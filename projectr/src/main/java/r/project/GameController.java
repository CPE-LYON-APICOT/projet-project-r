package r.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class GameController {

    @FXML
    private FlowPane playerCardPane;

    @FXML
    private FlowPane monsterCardPane;

    @FXML
    private StackPane gameBoard;

    @FXML
    private ListView<String> selectedCardsListViewPlayer;

    private player dataObject;

    public GameController(player dataObject) {
        this.dataObject = dataObject;
    }
    // Méthode pour l'action de jouer une carte
    @FXML
    private void playCardAction() {
        for (carte card : dataObject.getLstDeck()) {
            // Perform actions for playing the card
            selectedCardsListViewPlayer.getItems().add(card.getNom());
        }
       
    }

    // Autres méthodes nécessaires pour le fonctionnement du jeu
}
