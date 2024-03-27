package r.project;


    import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class InventoryController {

    @FXML
    private ComboBox<String> cardComboBox;

    @FXML
    private Label detailsLabel;

    private final String[] cardNames = {"Carte 1", "Carte 2", "Carte 3"};
    private final String[] cardDetails = {"Détails de la carte 1", "Détails de la carte 2", "Détails de la carte 3"};

    @FXML
    public void initialize() {
        cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
        cardComboBox.setPromptText("Sélectionner une carte");

        cardComboBox.setOnAction(event -> {
            int selectedIndex = cardComboBox.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < cardDetails.length) {
                detailsLabel.setText(cardDetails[selectedIndex]);
            } else {
                detailsLabel.setText("Détails non disponibles");
            }
        });
    }
}


