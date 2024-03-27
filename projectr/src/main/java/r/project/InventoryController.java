package r.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class InventoryController {

    @FXML
    private ComboBox<String> cardComboBox;

    @FXML
    private Label detailsLabel;
    @FXML
    private ImageView imageView;
    
        private ArrayList<carte> cartes = new ArrayList<>();
        private ArrayList<String> cardNames  = new ArrayList<>();
       

    @FXML
    public void initialize() {
         // Add Carte objects to the ArrayList
         cartes.add(new carteMonstre("test1", 10, "bonsoir description1", 10, 15,"imageProjet/Dragon.jpeg"));
         cartes.add(new carteMonstre("test2", 10, "bonsoir description2", 10, 15,"imageProjet/Demon.jpeg"));
         cartes.add(new carteMonstre("test3", 10, "bonsoir description3", 10, 15,"imageProjet/Renard.jpeg"));

         for (carte carte : cartes) {
            cardNames.add(carte.getNom());
         }

         cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
         cardComboBox.setPromptText("Sélectionner une carte");
         ImageController imageController = new ImageController();

      
         cardComboBox.setOnAction(event -> {
             int selectedIndex = cardComboBox.getSelectionModel().getSelectedIndex();
             if (selectedIndex >= 0 && selectedIndex < cartes.size()) {
                String details = cartes.get(selectedIndex).afficher();
                detailsLabel.setText(details);
                 
                 // Charger l'image depuis les ressources
                imageController.afficherImage(imageView, cartes.get(selectedIndex).getLienImage());

            
             } else {
                 detailsLabel.setText("Détails non disponibles");
             }
         });
        
    }
}


