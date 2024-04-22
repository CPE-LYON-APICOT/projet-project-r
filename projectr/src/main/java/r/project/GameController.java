package r.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class GameController {

    @FXML
    private Label card1LabelPV;

    @FXML
    private Label card2LabelPV;
    
    @FXML
    private Label card3LabelPV;
    
    @FXML
    private Label card4LabelPV;
    
    @FXML
    private Label card5LabelPV;
    
    @FXML
    private Label card1LabelAttaque;
    @FXML
    private Label card2LabelAttaque;
    @FXML
    private Label card3LabelAttaque;
    @FXML
    private Label card4LabelAttaque;
    @FXML
    private Label card5LabelAttaque;

    @FXML
    private Label card1LabelNom;
    @FXML
    private Label card2LabelNom;
    @FXML
    private Label card3LabelNom;
    @FXML
    private Label card4LabelNom;
    @FXML
    private Label card5LabelNom;
    
    @FXML
    private StackPane selectedCardPane;
    @FXML
    private FlowPane playerCardPane;

    @FXML
    private FlowPane monsterCardPane;

    @FXML
    private StackPane gameBoard;
    @FXML
    private HBox selectedCardsContainer;
    @FXML
    private HBox mainDuJoueur;
    
    @FXML
    private  ImageView card1Image;
    @FXML
    private  ImageView card2Image;
    @FXML
    private  ImageView card3Image;
    @FXML
    private  ImageView card4Image;
    @FXML
    private  ImageView card5Image;
    
    
 
    @FXML
    private ListView<String> selectedCardsListViewPlayer;
    private ArrayList<CreaMonstre> lstMonster = new ArrayList<>();
    private ArrayList<CreaBoss> lstBoss = new ArrayList<>();
    private player JoueurActuel;
    private ArrayList<Faction> fact = new ArrayList<>();
    ArrayList<carte> paquet= new ArrayList<>();
    ArrayList<carte> main= new ArrayList<>();
    ArrayList<carte> defausse= new ArrayList<>();

    public GameController(player dataObject,ArrayList<Faction> dataList) {
        this.JoueurActuel = dataObject;
        this.fact = dataList;
        
       
        
    }

    @FXML
    public void initialize(){
        ArrayList<carte> cartesList = new ArrayList<>(JoueurActuel.getLstDeck());
        ArrayList<Integer> chosenIndices = new ArrayList<>(); // Keep track of chosen card indices
        setCreature();
        
        for (int i = 0; i < 5; i++) {
            int randomIndex;
            do {
                randomIndex = new Random().nextInt(cartesList.size());
            } while (chosenIndices.contains(randomIndex)); // Check if the card has already been chosen

            chosenIndices.add(randomIndex); // Add the chosen index to the list
            carte currentCard = cartesList.get(i);
            main.add(currentCard);
            if (mainDuJoueur.getChildren().size() < 5) {
                // Créez un VBox pour contenir les informations de la carte
                VBox cardInfo = new VBox();
                cardInfo.setAlignment(Pos.CENTER);
                cardInfo.setSpacing(5);
            
                Label nomLabel = new Label(currentCard.getNom());
                nomLabel.setId("nomLabel_" + i); // Identifiant unique pour nomLabel

                    Label pvLabel = new Label("PV: " + currentCard.getPV());
            pvLabel.setId("pvLabel_"+ i); // Identifiant unique pour pvLabel

                Label attaqueLabel = new Label("Attaque: " + currentCard.getAttaque());
                attaqueLabel.setId("attaqueLabel_" + i); // Identifiant unique pour attaqueLabel

            
                // Ajoutez les labels au VBox
                cardInfo.getChildren().addAll(nomLabel,pvLabel, attaqueLabel);
            
                // Chargez l'image de la carte
                Image image = new Image(getClass().getResourceAsStream(currentCard.getLienImage()));
            
                // Créez une ImageView pour afficher l'image de la carte
                ImageView selectedCardView = new ImageView(image);
            
                // Définissez la taille souhaitée
                selectedCardView.setFitWidth(130); // Largeur souhaitée
                selectedCardView.setFitHeight(150); // Hauteur souhaitée
                selectedCardView.setId("selectedCardView_" + i); // Identifiant unique pour selectedCardView
                selectedCardView.setOnMouseClicked(event -> onCardClicked(event));
                // Créez un VBox pour contenir l'image et les informations de la carte
                VBox cardContainer = new VBox();
                cardContainer.setAlignment(Pos.CENTER);
                cardContainer.setSpacing(5);
            
                // Ajoutez l'image et les informations de la carte au VBox
                cardContainer.getChildren().addAll(selectedCardView, cardInfo);
            
                // Ajoutez le VBox contenant l'image et les informations de la carte à mainDuJoueur
                mainDuJoueur.getChildren().add(cardContainer);
            }
        }
        Button piocherButton = new Button("Piocher");
        piocherButton.setOnAction(event -> piocherCartes());
        
        // Ajoutez le bouton "Piocher" à mainDuJoueur
        mainDuJoueur.getChildren().add(piocherButton);
        Label playerHealthLabel = new Label("Points de vie du joueur: "+JoueurActuel.getPv());

        mainDuJoueur.getChildren().add(playerHealthLabel);
       
        

    }

   
        public void piocheAléatoire(ArrayList<carte> lesCartes) {
            // Mélanger aléatoirement les cartes
            Collections.shuffle(lesCartes);
        
            // Sélectionner les 5 premières cartes de la liste mélangée
            if (lesCartes.size() > 5) {
                lesCartes.subList(0, 5);
            }
        }
    @FXML
    public void piocherCartes() {
            // Vérifier si la main est pleine (contient déjà 5 cartes)
        if (main.size() >= 5) {
                // Ajouter les cartes du paquet dans la défausse
            defausse.addAll(paquet);
             
        } else {
                // Calculer combien de cartes peuvent être piochées
            int cartesRestantes = 5 - main.size();
                // Piocher les cartes restantes du paquet
            for (int i = 0; i < cartesRestantes; i++) {
                    // Vérifier si le paquet est vide
                if (!paquet.isEmpty()) {
                        // Retirer la première carte du paquet et l'ajouter à la main
                    carte cartePiochee = paquet.remove(0);
                    main.add(cartePiochee);
                } else {
                        // Si le paquet est vide, arrêter la pioche
                    break;
                }
            }
        }
    }
        

    

    // Méthode pour l'action de jouer une carte
    @FXML
    public void onCardClicked(MouseEvent event) {
        ImageView card = (ImageView) event.getSource();
        carte cartejouer = null;
       
    
        // Déterminez quelle carte a été sélectionnée en fonction de l'identifiant unique de l'ImageView
        switch (card.getId()) {
            case "selectedCardView_0":
                Label nomLabel = (Label) mainDuJoueur.lookup("#nomLabel_0");
                cartejouer = getCarteFromLabel(nomLabel.getText());
                break;
            case "selectedCardView_1":
                Label nomLabel2 = (Label) mainDuJoueur.lookup("#nomLabel_1");
                cartejouer = getCarteFromLabel(nomLabel2.getText());
                break;
            case "selectedCardView_2":
                Label nomLabel3 = (Label) mainDuJoueur.lookup("#nomLabel_2");
                cartejouer = getCarteFromLabel(nomLabel3.getText());
                break;
            case "selectedCardView_3":
                Label nomLabel4 = (Label) mainDuJoueur.lookup("#nomLabel_3");
                cartejouer = getCarteFromLabel(nomLabel4.getText());
                break;
            case "selectedCardView_4":
                Label nomLabel5 = (Label) mainDuJoueur.lookup("#nomLabel_4");
                cartejouer = getCarteFromLabel(nomLabel5.getText());
                break;
            default:
                break;
        }
    
        // Mettez à jour la carte sélectionnée
        if (selectedCardsContainer.getChildren().size() < 5) {
            // Créez un VBox pour contenir les informations de la carte
            VBox cardInfo = new VBox();
            cardInfo.setAlignment(Pos.CENTER);
            cardInfo.setSpacing(5);
        
            // Affichez les informations de la carte
            Label pvLabel = new Label("PV: " + cartejouer.getPV());
            Label attaqueLabel = new Label("Attaque: " + cartejouer.getAttaque());
        
            // Ajoutez les labels au VBox
            cardInfo.getChildren().addAll(pvLabel, attaqueLabel);
        
            // Chargez l'image de la carte
            Image image = new Image(getClass().getResourceAsStream(cartejouer.getLienImage()));
        
            // Créez une ImageView pour afficher l'image de la carte
            ImageView selectedCardView = new ImageView(image);
        
            // Définissez la taille souhaitée
            selectedCardView.setFitWidth(130); // Largeur souhaitée
            selectedCardView.setFitHeight(150); // Hauteur souhaitée
        
            // Créez un VBox pour contenir l'image et les informations de la carte
            VBox cardContainer = new VBox();
            cardContainer.setAlignment(Pos.CENTER);
            cardContainer.setSpacing(5);
        
            // Ajoutez l'image et les informations de la carte au VBox
            cardContainer.getChildren().addAll(selectedCardView, cardInfo);
        
            // Ajoutez le VBox contenant l'image et les informations de la carte à selectedCardsContainer
            selectedCardsContainer.getChildren().add(cardContainer);
        }
    }
    
    public void onSelectedCardClicked(MouseEvent event) {
        // Action à effectuer lorsqu'une carte sélectionnée est cliquée
        // Vous pouvez mettre ici le code pour supprimer la carte sélectionnée, par exemple
        VBox cardContainer = (VBox) ((ImageView) event.getSource()).getParent();
        mainDuJoueur.getChildren().remove(cardContainer);
    }
    
    
    private carte getCarteFromLabel(String label) {
        for (carte card : JoueurActuel.getLstDeck()) {
            if (card.getNom().equals(label)) {
                return card;
            }
        }
        return null;
    }
    
    @FXML
    private void playCardAction() {

        for (carte card : JoueurActuel.getLstDeck()) {
            // Perform actions for playing the card
            selectedCardsListViewPlayer.getItems().add(card.getNom());

        }
        
       
        selectedCardsListViewPlayer.getItems().add(JoueurActuel.getHero().GetNom());
       
    }

    public void setCreature(){
        //Monster
        lstMonster.add(new CreaMonstre(2, 2, "Gobelin des marais", "Un petit être verdâtre, agile et sournois, vivant dans les marais.", "image1", fact.get(2)));
        lstMonster.add(new CreaMonstre(8, 3, "Golem de pierre", "Une créature massive composée de roches géantes. Elle frappe ses ennemis avec une force écrasante.", "image2", fact.get(0)));
        lstMonster.add(new CreaMonstre(4, 5, "Spectre glacé", "Une entité fantomatique qui hante les toundras en gelant tout sur son passage. Ses attaques drainent la chaleur de ses victimes.", "lien_image_spectre_glacé", fact.get(1)));
        lstMonster.add(new CreaMonstre(7, 6, "Chimère démoniaque", "Une abomination combinant les traits d'un lion, d'un serpent et d'un démon. Elle crache du feu et empoisonne ses adversaires.", "lien_image_chimère_démoniaque", fact.get(2)));
        lstMonster.add(new CreaMonstre(1, 4, "Esprit des ombres", "Une entité obscure et insaisissable qui se dissimule dans les ténèbres. Ses attaques sont furtives et drainent l'énergie vitale.", "lien_image_esprit_des_ombres", fact.get(1)));
        lstMonster.add(new CreaMonstre(7, 2, "Araignée géante", "Une araignée monstrueuse aux pattes velues et aux crocs venimeux. Elle tisse des toiles pour capturer ses proies.", "lien_image_araignée_géante", null));
        lstMonster.add(new CreaMonstre(9, 7, "Basilic des abysses", "Une créature reptilienne vivant dans les profondeurs océaniques. Son regard pétrifie ses ennemis, et ses crocs sont acérés comme des lames.", "lien_image_basilic_des_abysses", null));
        lstMonster.add(new CreaMonstre(10, 4, "Behemoth des montagnes", "Une bête titanesque parcourant les sommets en détruisant tout sur son passage. Ses rugissements déclenchent des éboulements.", "lien_image_behemoth_des_montagnes", fact.get(0)));
        lstMonster.add(new CreaMonstre(3, 4, "Liche maudite", "Un sorcier mort-vivant ayant acquis un pouvoir sinistre. Il lance des malédictions et invoque des âmes tourmentées pour combattre.", "lien_image_liche_maudite", fact.get(1)));
        //Boss
        Aria initializeAria=new Aria(230, 15, "", "", "image", fact.get(0),"");
        lstBoss.add(initializeAria);
        Neron initializeNeron=new Neron(300, 30, "", "", "image2", fact.get(1), "");
        lstBoss.add(initializeNeron);
        Moloch initializeMoloch=new Moloch(130,80,"","","image3",fact.get(2),"");
        lstBoss.add(initializeMoloch);

    }
    // Autres méthodes nécessaires pour le fonctionnement du jeu
    public  List<CreaMonstre> rencontreMonstre(){
        Random rand = new Random();
        List<CreaMonstre> monstresRencontres = new ArrayList<>();
        int nombreAleatoire = rand.nextInt(8);
        monstresRencontres.add(lstMonster.get(nombreAleatoire));
        return monstresRencontres;        
    }

    public boolean combatMonstre(List<CreaMonstre> lstRencontreMonstre){
        int nbMonstreBattu=0;
        while (nbMonstreBattu<lstRencontreMonstre.size()){
            nbMonstreBattu=0;
            for (int i=0;i<lstRencontreMonstre.size();i++){
                if (lstRencontreMonstre.get(i).getPv()==0){
                    nbMonstreBattu+=1;
                }

            }
            if (JoueurActuel.getPv()==0){
                return false;
            }
        }
        return true;
    }

    public boolean combatBoss(CreaBoss pBoss){
        while (pBoss.getPv()!=0 && JoueurActuel.getPv()!=0)
        {
            if (pBoss.getPv()==0){
                return true;
            }
        }
        return false;
        
    }

    public boolean Game(){
        Random rand = new Random();
        int nbBossVaincu=0;
        while (nbBossVaincu!=3){
            for (int i = 0; i < 3; i++) {
                int nbMonstreRencontree = rand.nextInt(3) + 1;
                for(int j=0; j<nbMonstreRencontree;j++){
                    if(combatMonstre(lstMonster)==false){
                        return false;
                    }
                }
                if(combatBoss(lstBoss.get(i))==false){
                    return false;
                }
                else{
                    nbBossVaincu+=1;
                }
            }
        }
        return true;
    }
}
