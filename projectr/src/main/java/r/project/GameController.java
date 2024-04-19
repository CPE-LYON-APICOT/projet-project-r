package r.project;

import java.util.ArrayList;

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

    private player JoueurActuel;
    private ArrayList<Faction> fact = new ArrayList<>();

    public GameController(player dataObject,ArrayList<Faction> dataList) {
        this.JoueurActuel = dataObject;
        this.fact = dataList;
    }
    // Méthode pour l'action de jouer une carte
    @FXML
    private void playCardAction() {
        for (carte card : JoueurActuel.getLstDeck()) {
            // Perform actions for playing the card
            selectedCardsListViewPlayer.getItems().add(card.getNom());
        }
        selectedCardsListViewPlayer.getItems().add(fact.get(0).GetNom());
       
    }

    // Autres méthodes nécessaires pour le fonctionnement du jeu
}
