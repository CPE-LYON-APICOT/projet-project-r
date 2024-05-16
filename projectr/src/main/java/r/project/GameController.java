package r.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class GameController {

    
    @FXML
    private Label manaTour;
    @FXML
    private Label manaJoueur;
    @FXML
    private Button finTour;
    @FXML
    private Label commentaireCombat;
    @FXML
    private HBox selectedCardsContainer;
    @FXML
    private HBox mainDuJoueur;
    @FXML
    private HBox creature;
    @FXML
    private Label playerHealthLabel;
    
    carte carteSelectionne = null;
    CreaBoss bossSelectione = null;

    @FXML
    private ListView<String> selectedCardsListViewPlayer;
    private ArrayList<CreaMonstre> lstMonster = new ArrayList<>();
    private ArrayList<CreaBoss> lstBoss = new ArrayList<>();
    private player JoueurActuel;
    private ArrayList<Faction> fact = new ArrayList<>();
    private List<IObserver> observers = new ArrayList<>();
    private String pioche;
  
    ArrayList<carte> paquet= new ArrayList<>();
    ArrayList<carte> main= new ArrayList<>();
    ArrayList<carte> plateau= new ArrayList<>();
    ArrayList<carte> carteAttaquer= new ArrayList<>();
    private List<IObserver> observers = new ArrayList<>();
    
    private int manaparTour;
    private int manaDuJoueur;

    public GameController(player dataObject,ArrayList<Faction> dataList, String pioche) {
        this.JoueurActuel = dataObject;
        this.fact = dataList;
        this.pioche = pioche;
        
       
        
    }
    
    
    @FXML
    public void initialize(){
        ArrayList<carte> cartesList = new ArrayList<>(JoueurActuel.getLstDeck());

        ArrayList<Integer> chosenIndices = new ArrayList<>(); 
        paquet.addAll(JoueurActuel.getLstDeck());
        setCreature();
       
        playerHealthLabel.setText("Points de vie du joueur : "+JoueurActuel.getPv());

        for (int i = 0; i < 5; i++) {
            int randomIndex;
            do {
                randomIndex = new Random().nextInt(cartesList.size());
            } while (chosenIndices.contains(randomIndex)); 

            chosenIndices.add(randomIndex); 
            carte currentCard = cartesList.get(i);
            main.add(currentCard);
            paquet.remove(currentCard); 
            if (mainDuJoueur.getChildren().size() < 5) {
                // Créez un VBox pour contenir les informations de la carte
                VBox cardInfo = new VBox();
                cardInfo.setAlignment(Pos.CENTER);
                cardInfo.setSpacing(5);
                Label LabelId = new Label(String.valueOf(currentCard.hashCode()));
                LabelId.setId("LabelId_" + i);
                LabelId.setVisible(false);
                Label manaLabel = new Label("Mana: " + currentCard.getCout());
                manaLabel.setId("manaLabel_" + i); 

                Label nomLabel = new Label(currentCard.getNom());
                nomLabel.setId("nomLabel_" + i); 

                Label pvLabel = new Label("PV: " + currentCard.getPV());
                pvLabel.setId("pvLabel_"+ i); 

                Label attaqueLabel = new Label("Attaque: " + currentCard.getAttaque());
                attaqueLabel.setId("attaqueLabel_" + i); 

            
                cardInfo.getChildren().addAll(manaLabel,nomLabel,pvLabel, attaqueLabel, LabelId);
            
                // Chargez l'image de la carte
                Image image = new Image(getClass().getResourceAsStream(currentCard.getLienImage()));
            
                ImageView selectedCardView = new ImageView(image);
            
                selectedCardView.setFitWidth(130); 
                selectedCardView.setFitHeight(150); 
                selectedCardView.setId("selectedCardView_" + i); 
                selectedCardView.setOnMouseClicked(event -> onCardClicked(event));

                VBox cardContainer = new VBox();
                cardContainer.setAlignment(Pos.CENTER);
                cardContainer.setSpacing(5);
            
                cardContainer.getChildren().addAll(selectedCardView, cardInfo);
            
                mainDuJoueur.getChildren().add(cardContainer);
        
            }
        }
        afficherBossAleatoire();
        manaparTour=0;
        incrementationManaTour();   
        new PopUpMusicOservable();
        addObserver(new musicVictoire());

    }

    private void incrementationManaTour(){
        if (manaparTour<10){
            manaparTour+=1;
            manaTour.setText("Tour: "+ String.valueOf(manaparTour));
        }
        manaDuJoueur=manaparTour;
        manaJoueur.setText("Mana Joueur: "+ String.valueOf(manaDuJoueur));
    }

    private Boolean determinerManaJoueur(int coutcarte){
        if (manaDuJoueur-coutcarte>=0){
            manaDuJoueur=manaDuJoueur-coutcarte;
            manaJoueur.setText("Mana Joueur: "+ String.valueOf(manaDuJoueur));
            return true;
        }
        else{
            return false;
        }
    }

    @FXML
    private void handleFinTour(){
        attaqueBoss();
        incrementationManaTour();
        if(pioche.equals("Pioche Basique")){
            piocherCartes();
        }
        else if(pioche.equals("Pioche Aléatoire")){
            piocheAléatoire();
        }
        carteAttaquer.clear();
    }


    public void afficherBossAleatoire() {
        Random random = new Random();
        int randomIndex = random.nextInt(lstBoss.size());
        CreaBoss bossSelectionne = lstBoss.get(randomIndex);
        VBox bossInfo = new VBox();
        bossInfo.setAlignment(Pos.CENTER);
        bossInfo.setSpacing(10);
        Image imageBoss = new Image(getClass().getResourceAsStream(bossSelectionne.getLienImage()));
        ImageView bossImageView = new ImageView(imageBoss);
        
        bossImageView.setFitWidth(200); 
        bossImageView.setFitHeight(200);
        bossImageView.setId("selectedCardBoss_"); 
        bossImageView.setOnMouseClicked(event -> {

            Label monstre = (Label) creature.lookup("#nomLabelBoss");
            bossSelectione = getBossFromLabel(monstre.getText());
            combat();
        }); 

        bossInfo.getChildren().add(bossImageView);
        Label nomLabel = new Label(bossSelectionne.getNom());
        nomLabel.setId("nomLabelBoss");
        nomLabel.setVisible(false);
        Label nomBossLabel = new Label("Nom: " + bossSelectionne.getNom());
        Label pvBossLabel = new Label("PV: " + bossSelectionne.getPv());
        Label attaqueBossLabel = new Label("Attaque: " + bossSelectionne.getAttaque());
        bossInfo.getChildren().addAll(nomBossLabel, pvBossLabel, attaqueBossLabel,nomLabel);
        creature.getChildren().add(bossInfo);


    }
    

    // Méthode pour l'action de jouer une carte
    @FXML
    public void onCardClicked(MouseEvent event) {
        ImageView card = (ImageView) event.getSource();
        carte cartejouer = null;
       
    
        // Déterminez quelle carte a été sélectionnée en fonction de l'identifiant unique de l'ImageView
        switch (card.getId()) {
            case "selectedCardView_0":
                Label nomLabel = (Label) mainDuJoueur.lookup("#LabelId_0");
                cartejouer = getCarteFromHashCode(Integer.parseInt(nomLabel.getText()));
                break;
            case "selectedCardView_1":
                Label nomLabel2 = (Label) mainDuJoueur.lookup("#LabelId_1");
                cartejouer = getCarteFromHashCode(Integer.parseInt(nomLabel2.getText()));
                break;
            case "selectedCardView_2":
                Label nomLabel3 = (Label) mainDuJoueur.lookup("#LabelId_2");
                cartejouer = getCarteFromHashCode(Integer.parseInt(nomLabel3.getText()));
                break;
            case "selectedCardView_3":
                Label nomLabel4 = (Label) mainDuJoueur.lookup("#LabelId_3");
                cartejouer = getCarteFromHashCode(Integer.parseInt(nomLabel4.getText()));
                break;
            case "selectedCardView_4":
                Label nomLabel5 = (Label) mainDuJoueur.lookup("#LabelId_4");
                cartejouer = getCarteFromHashCode(Integer.parseInt(nomLabel5.getText()));
                break;
            default:
                break;
        }
       
        // Mettez à jour la carte sélectionnée
        if (selectedCardsContainer.getChildren().size() < 5 && cartejouer.getCout()<=manaDuJoueur) {

            VBox cardInfo = new VBox();
            cardInfo.setAlignment(Pos.CENTER);
            cardInfo.setSpacing(5);
            Label LabelId = new Label(String.valueOf(cartejouer.hashCode()));
            LabelId.setId("LabelId_" + plateau.size());
            LabelId.setVisible(false);
            Label nomLabel = new Label(cartejouer.getNom());
            nomLabel.setId("nomLabel_"  + plateau.size()); 
            nomLabel.setVisible(false);


            Label manaLabel =new Label("Mana: " +cartejouer.getCout());
            Label pvLabel = new Label("PV: " + cartejouer.getPV());
            Label attaqueLabel = new Label("Attaque: " + cartejouer.getAttaque());
        
            cardInfo.getChildren().addAll(manaLabel,pvLabel, attaqueLabel,nomLabel,LabelId);
        
            Image image = new Image(getClass().getResourceAsStream(cartejouer.getLienImage()));
        
            ImageView selectedCardView = new ImageView(image);
        
            selectedCardView.setId("selectedCardView_" + plateau.size()); 
            selectedCardView.setFitWidth(130);
            selectedCardView.setFitHeight(150); 
            selectedCardView.setOnMouseClicked(ev -> {
                ImageView card2 = (ImageView) ev.getSource();
                carte cartejouer2 = null;
               
            
                switch (card2.getId()) {
                    case "selectedCardView_0":
                        Label nomLabel0 = (Label) selectedCardsContainer.lookup("#LabelId_0");
                        cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel0.getText()));
                        break;
                    case "selectedCardView_1":
                        Label nomLabel20 = (Label) selectedCardsContainer.lookup("#LabelId_1");
                        cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel20.getText()));
                        break;
                    case "selectedCardView_2":
                        Label nomLabel30 = (Label) selectedCardsContainer.lookup("#LabelId_2");
                        cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel30.getText()));
                        break;
                    case "selectedCardView_3":
                        Label nomLabel40 = (Label) selectedCardsContainer.lookup("#LabelId_3");
                        cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel40.getText()));
                        break;
                    case "selectedCardView_4":
                        Label nomLabel50 = (Label) selectedCardsContainer.lookup("#LabelId_4");
                        cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel50.getText()));
                        break;
                    default:
                        break;
                }
                carteSelectionne = cartejouer2;
        
               
                combat();
                
            });
            plateau.add(cartejouer);

            VBox cardContainer = new VBox();
            cardContainer.setAlignment(Pos.CENTER);
            cardContainer.setSpacing(5);
        
            cardContainer.getChildren().addAll(selectedCardView, cardInfo);
        
            selectedCardsContainer.getChildren().add(cardContainer);
            ChargeMain(cartejouer.hashCode());

            determinerManaJoueur(cartejouer.getCout());
        }
       
    }
    
    public void combat (){
  
        if (bossSelectione != null && carteSelectionne != null) {
            for (int i = 0; i < carteAttaquer.size(); i++) {
               if (carteAttaquer.get(i).hashCode() == carteSelectionne.hashCode() ){
                    carteSelectionne = null;
                    bossSelectione = null;
                   return;
               }
            }
            // Une carte monstre est déjà sélectionnée, déclenchez le combat
            // Calculez les dégâts infligés par le joueur et le monstre
            int degatsJoueur = carteSelectionne.getAttaque();
            int degatsMonstre = bossSelectione.getAttaque();
           
            lstBoss.remove(bossSelectione);
            plateau.remove(carteSelectionne);
            
            // Mettez à jour les points de vie des cartes
            bossSelectione.setPv(bossSelectione.getPv() - degatsJoueur);
            carteSelectionne.setPV(carteSelectionne.getPV() - degatsMonstre);

            if (bossSelectione.getPv() <= 0) {
                creature.getChildren().clear();
                afficherPopupVictoire();
            }
            else{
                lstBoss.add(bossSelectione);
                ChargerBoss(bossSelectione);
            }
            if (carteSelectionne.getPV() <= 0) {
                selectedCardsContainer.getChildren().clear();

                for (int i = 0; i < paquet.size(); i++) {
                    System.out.println(paquet.get(i).getNom());

                    if (paquet.get(i).getNom().equals(carteSelectionne.getNom())) {
                        System.out.println(paquet.get(i).getNom());
                        if (paquet.get(i).getPV() <= 0){
                            System.out.println("effacer");

                            paquet.remove(i);

                        }
                    }
                }
                ChargePlateau();
            }else{
                plateau.add(carteSelectionne);
                ChargePlateau();
                
            }
            carteAttaquer.add(carteSelectionne);
            commentaireCombat.setText(carteSelectionne.getNom()+" effectue "+String.valueOf(carteSelectionne.getAttaque()) + " degats à "+bossSelectione.getNom());
            // Réinitialisez les cartes sélectionnées pour la prochaine itération
            carteSelectionne = null;
            bossSelectione = null;
        }
        
    }


    public void addObserver(@SuppressWarnings("exports") IObserver observer) {

        observers.add(observer);
    }

    private void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    private void afficherPopupVictoire() {
        notifyObservers();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Victoire !");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations, vous avez gagné le combat !");
        alert.showAndWait();
        System.exit(0);
    }

    private void afficherPopupDefaite(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Defaite !");
        alert.setHeaderText(null);
        alert.setContentText("Désolé, vous avez perdu le combat !");
        alert.showAndWait();
        System.exit(0);
    }


        

    public void ChargerBoss(CreaBoss bossSelectionne){
        creature.getChildren().clear();
        VBox bossInfo = new VBox();
        bossInfo.setAlignment(Pos.CENTER);
        bossInfo.setSpacing(10);
        Image imageBoss = new Image(getClass().getResourceAsStream(bossSelectionne.getLienImage()));
        ImageView bossImageView = new ImageView(imageBoss);
        
        bossImageView.setFitWidth(200); 
        bossImageView.setFitHeight(200);
        bossImageView.setOnMouseClicked(event1 -> {

            // Obtenez la carte joueur
            Label monstre = (Label) creature.lookup("#nomLabelBoss");
            bossSelectione = getBossFromLabel(monstre.getText());
           
            
            combat();

        }); 
        bossInfo.getChildren().add(bossImageView);
        Label nomLabel = new Label(bossSelectionne.getNom());
        nomLabel.setId("nomLabelBoss");
        nomLabel.setVisible(false);
        Label nomBossLabel = new Label("Nom: " + bossSelectionne.getNom());
        Label pvBossLabel = new Label("PV: " + bossSelectionne.getPv());
        Label attaqueBossLabel = new Label("Attaque: " + bossSelectionne.getAttaque());
        bossInfo.getChildren().addAll(nomBossLabel, pvBossLabel, attaqueBossLabel,nomLabel);
        creature.getChildren().add(bossInfo);
    }
    public void ChargePlateau(){
        selectedCardsContainer.getChildren().clear();
        for (int i = 0; i < plateau.size(); i++) {
        // Créez un VBox pour contenir les informations de la carte

        VBox cardInfo = new VBox();
        cardInfo.setAlignment(Pos.CENTER);
        cardInfo.setSpacing(5);
        Label LabelId = new Label(String.valueOf(plateau.get(i).hashCode()));
        LabelId.setId("LabelId_" + i);
        LabelId.setVisible(false);

        Label nomLabel = new Label(plateau.get(i).getNom());
        nomLabel.setId("nomLabel_"  + i);
        nomLabel.setVisible(false);

        // Affichez les informations de la carte
        Label manaLabel = new Label("Mana: "+ plateau.get(i).getCout());
        Label pvLabel = new Label("PV: " + plateau.get(i).getPV());
        Label attaqueLabel = new Label("Attaque: " + plateau.get(i).getAttaque());
    
        // Ajoutez les labels au VBox
        cardInfo.getChildren().addAll(manaLabel,pvLabel, attaqueLabel,nomLabel,LabelId);
    
        // Chargez l'image de la carte
        Image image = new Image(getClass().getResourceAsStream(plateau.get(i).getLienImage()));
    
        // Créez une ImageView pour afficher l'image de la carte
        ImageView selectedCardView = new ImageView(image);
    
     
        selectedCardView.setId("selectedCardView_" + i); 
        selectedCardView.setFitWidth(130); 
         selectedCardView.setFitHeight(150); 
        selectedCardView.setOnMouseClicked(ev -> {
            ImageView card2 = (ImageView) ev.getSource();
            carte cartejouer2 = null;
           
        
            // Déterminez quelle carte a été sélectionnée en fonction de l'identifiant unique de l'ImageView
            switch (card2.getId()) {
                case "selectedCardView_0":
                    Label nomLabel0 = (Label) selectedCardsContainer.lookup("#LabelId_0");
                    cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel0.getText()));
                    break;
                case "selectedCardView_1":
                    Label nomLabel20 = (Label) selectedCardsContainer.lookup("#LabelId_1");
                    cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel20.getText()));
                    break;
                case "selectedCardView_2":
                    Label nomLabel30 = (Label) selectedCardsContainer.lookup("#LabelId_2");
                    cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel30.getText()));
                    break;
                case "selectedCardView_3":
                    Label nomLabel40 = (Label) selectedCardsContainer.lookup("#LabelId_3");
                    cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel40.getText()));
                    break;
                case "selectedCardView_4":
                    Label nomLabel50 = (Label) selectedCardsContainer.lookup("#LabelId_4");
                    cartejouer2 = getCarteFromHashCode(Integer.parseInt(nomLabel50.getText()));
                    break;
                default:
                    break;
            }
            carteSelectionne = cartejouer2;
    
            combat();
            
        });

        VBox cardContainer = new VBox();
        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setSpacing(5);
    
        cardContainer.getChildren().addAll(selectedCardView, cardInfo);
    
        selectedCardsContainer.getChildren().add(cardContainer);
        }
    }

    public void ChargeMain(int nomcarte){
        if (nomcarte != 0) {

            int cartearetirer = getIndexCarteDansMain(nomcarte);

            if (cartearetirer != -1) {
                main.remove(cartearetirer);
            }
        }
        mainDuJoueur.getChildren().clear();
        for (int i = 0; i < main.size(); i++) {
            carte currentCard = main.get(i);
        
            VBox cardInfo = new VBox();
            cardInfo.setAlignment(Pos.CENTER);
            cardInfo.setSpacing(5);
            Label LabelId = new Label(String.valueOf(currentCard.hashCode()));
            LabelId.setId("LabelId_" + i);
            LabelId.setVisible(false);

            Label manaLabel= new Label("Mana:" + currentCard.getCout());
            manaLabel.setId("manaLabel_" + i);
        
            Label nomLabel = new Label(currentCard.getNom());
            nomLabel.setId("nomLabel_" + i); 
        
            Label pvLabel = new Label("PV: " + currentCard.getPV());
            pvLabel.setId("pvLabel_" + i); 
        
            Label attaqueLabel = new Label("Attaque: " + currentCard.getAttaque());
            attaqueLabel.setId("attaqueLabel_" + i); 
        

            cardInfo.getChildren().addAll(manaLabel,nomLabel, pvLabel, attaqueLabel,LabelId);
        

            Image image = new Image(getClass().getResourceAsStream(currentCard.getLienImage()));
        

            ImageView selectedCardView = new ImageView(image);
        
            selectedCardView.setFitWidth(130); 
            selectedCardView.setFitHeight(150); 
            selectedCardView.setId("selectedCardView_" + i); 
            selectedCardView.setOnMouseClicked(event -> onCardClicked(event));
            

            VBox cardContainer = new VBox();
            cardContainer.setAlignment(Pos.CENTER);
            cardContainer.setSpacing(5);
        

            cardContainer.getChildren().addAll(selectedCardView, cardInfo);
        

            mainDuJoueur.getChildren().add(cardContainer);
        }
    }

     public void attaqueBoss(){
        Label monstre = (Label) creature.lookup("#nomLabelBoss");
        CreaBoss bossSelectione2 = getBossFromLabel(monstre.getText());
        carte carteContreLeboss = null;
        if (plateau.isEmpty()) {
            JoueurActuel.pertePv(bossSelectione2.getAttaque());
            playerHealthLabel.setText("Points de vie du joueur"+JoueurActuel.getPv());
            if (JoueurActuel.getPv()<=0){
                afficherPopupDefaite();

            }
            return;
        }
        else{
            Random rand = new Random();
            int randomNumber = rand.nextInt(100) + 1;
            if (randomNumber>25){

                commentaireCombat.setText(bossSelectione2.getNom()+" effectue une attaque ciblée");
                for (int i = 0; i < plateau.size(); i++) {
                    if (plateau.get(i).getAttaque() < bossSelectione2.getPv() ){
                        if(plateau.get(i).getPV() < bossSelectione2.getAttaque() ){
                            carteContreLeboss = plateau.get(i); 
                            break;
                        }

                    }
                }
                if (carteContreLeboss == null) {
                    carteContreLeboss = plateau.get(0);
                
                }
                plateau.remove(carteContreLeboss);
                

                bossSelectione2.setPv(bossSelectione2.getPv() - carteContreLeboss.getAttaque());
                carteContreLeboss.setPV(carteContreLeboss.getPV() - bossSelectione2.getAttaque()); 

                if (bossSelectione2.getPv() <= 0) {


                    creature.getChildren().clear();
                    afficherPopupVictoire();
                }else{
                    lstBoss.add(bossSelectione2);
                    ChargerBoss(bossSelectione2);
                }
                if (carteContreLeboss.getPV() <= 0) {
                    selectedCardsContainer.getChildren().clear();
                    ChargePlateau();
                }else{
                    plateau.add(carteContreLeboss);

                    ChargePlateau();
                    
                }
            }
            else{
                //attaque de zone
                commentaireCombat.setText(bossSelectione2.getNom()+" effectue une attaque de zone");
                List<carte> copiePlateau = new ArrayList<>(plateau); 
                for ( carte selectCarte : copiePlateau) {
                    selectCarte.setPV(selectCarte.getPV() - bossSelectione2.getAttaque());
                    plateau.remove(selectCarte); 

                    if (selectCarte.getPV() <= 0) {
                      
                        selectedCardsContainer.getChildren().clear();
                        ChargePlateau();
                    } else {
                         plateau.add(selectCarte);

                        ChargePlateau();
                    }
                }
            }

        }

        if (test == null) {
            test = plateau.get(0);
           
        }
        bossSelectione2.setPv(bossSelectione2.getPv() - test.getAttaque());
        test.setPV(test.getPV() - bossSelectione2.getAttaque()); 

        plateau.remove(test);
        if (bossSelectione2.getPv() <= 0) {

            creature.getChildren().clear();
            afficherPopupVictoire();
        }else{
            lstBoss.add(bossSelectione2);
            ChargerBoss(bossSelectione2);
        }
        if (test.getPV() <= 0) {
            
           
            for (int i = 0; i < paquet.size(); i++) {
                System.out.println(paquet.get(i).getNom());

                if (paquet.get(i).getNom().equals(test.getNom())) {
                    System.out.println(paquet.get(i).getNom());
                    if (paquet.get(i).getPV() <= 0){
                        System.out.println("effacer");

                        paquet.remove(i);
                        break;

                    }
                }
            }
            selectedCardsContainer.getChildren().clear();
            
            ChargePlateau();
        }else{
            plateau.add(test);
            ChargePlateau();
            
        }
        

     }
    private int getIndexCarteDansMain(int nomCarte) {
        for (int i = 0; i < main.size(); i++) {
            carte carte = main.get(i);
            if (carte.hashCode() == nomCarte) {
                return i; // Retourne l'index de la carte si le nom correspond
            }
        }
        return -1; // Retourne -1 si la carte n'est pas trouvée dans la main
    }

    

    public void onSelectedCardClicked(MouseEvent event) {
        VBox cardContainer = (VBox) ((ImageView) event.getSource()).getParent();
        mainDuJoueur.getChildren().remove(cardContainer);
    }
    
    private CreaBoss getBossFromLabel(String label) {
        for (CreaBoss monster : lstBoss) {
            if (monster.getNom().equals(label)) {
                return monster;
            }
        }
        return null;
    }

    private carte getCarteFromHashCode(int label) {
        for (carte card : JoueurActuel.getLstDeck()) {
            if (card.hashCode() == label) {

                return card;
            }
        }
        return null;
    }
    
    @FXML
    private void playCardAction() {

        for (carte card : JoueurActuel.getLstDeck()) {
            selectedCardsListViewPlayer.getItems().add(card.getNom());

        }
        
       
        selectedCardsListViewPlayer.getItems().add(JoueurActuel.getHero().GetNom());
       
    }

    public void piocherCartes() {
        if (main.size() < 5) {
            
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);
            
            ChargeMain(0);
        }
        
    }
    public void piocheAléatoire() {
        // Mélanger aléatoirement les cartes
        Collections.shuffle(paquet);
        if (main.size()< 5) {
         
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);
        
            ChargeMain(0);
        }
    
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
        Aria initializeAria=new Aria(30, 15, "", "", "imageProjet/Aria.jpeg", fact.get(0),"");
        lstBoss.add(initializeAria);
        Neron initializeNeron=new Neron(30, 30, "", "", "imageProjet/Neron.jpeg", fact.get(1), "");
        lstBoss.add(initializeNeron);
        Moloch initializeMoloch=new Moloch(30,80,"","","imageProjet/Moloch.jpeg",fact.get(2),"");
        lstBoss.add(initializeMoloch);

    }
}
