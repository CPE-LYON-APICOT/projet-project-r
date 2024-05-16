package r.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import java.util.Random;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;


public class InventoryController {

    @FXML
    private ComboBox<String> cardComboBox;
    @FXML
    private ComboBox<String> heroComboBox;
    @FXML
    private Label detailsLabel;    
    @FXML
    private Label detailsLabelHere;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageHero;
    @FXML
    private ComboBox<String> comboBoxPioche;
    @FXML
    private Label selectedCardCountLabel;

    Random rand = new Random();
    private ArrayList<carte> cartes = new ArrayList<>();
    private ArrayList<Faction> fact = new ArrayList<>();
    private ArrayList<carte> cartesOrdre = new ArrayList<>();
    private ArrayList<carte> cartesNeant = new ArrayList<>();
    private ArrayList<carte> cartesChaos = new ArrayList<>();
    private ArrayList<carte> DeckPlayer= new ArrayList<>();

    private ArrayList<String> cardNames  = new ArrayList<>();    
    private ArrayList<Hero> LstHeroes= new ArrayList<>();
    private ArrayList<String> heroNames = new ArrayList<>();
    private ArrayList<String> pioche = new ArrayList<>();   

    @FXML
    private ListView<String> selectedCardsListView;
    private static Scene scene;
        

    @FXML
    public void initialize() {
      

        
        setCartesEtFaction();
        
        pioche.add("Pioche Aléatoire");
        pioche.add("Pioche Basique");
        comboBoxPioche.setItems(FXCollections.observableArrayList(pioche));
        comboBoxPioche.setPromptText("Sélectionner une pioche");

         cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
         cardComboBox.setPromptText("Sélectionner une carte");
         heroComboBox.setItems(FXCollections.observableArrayList(heroNames));
         heroComboBox.setPromptText("Sélectionner un hero");
         ImageController imageController = new ImageController();

      
         cardComboBox.setOnAction(event -> {
             String NomDelaCarte = cardComboBox.getValue();
             for (carte carte : cartes) {
                 if (carte.getNom() == NomDelaCarte){
                     String details = carte.afficher();
                     detailsLabel.setText(details);
                     imageController.afficherImage(imageView, carte.getLienImage());
                 }
                }
             
         });

         heroComboBox.setOnAction(event -> {
            String NomDuHero = heroComboBox.getValue();
            for (Hero hero : LstHeroes) {
                if (hero.GetNom() == NomDuHero){
                    String details = hero.afficher();
                    detailsLabelHere.setText(details);
                    imageController.afficherImage(imageHero, hero.GetImage());
                }
               }
            
        });
    }
    
    @FXML
    protected void addToDeck() {
        int indexAleatoire1=0;
        String selectedCard = cardComboBox.getValue();
        ObservableList<String> items = selectedCardsListView.getItems();
        if (selectedCard != null && !selectedCard.isEmpty() && selectedCardsListView.getItems().size() < 30){
            for ( String carte : items ){
                if (carte == selectedCard ){
                    indexAleatoire1+=1;
                }
            }
            if (indexAleatoire1 <3){
                selectedCardsListView.getItems().add(selectedCard);
            }
        }
        NbCarte();



}
    
    @FXML
    protected void removeFromDeck() {
    int selectedIndex = selectedCardsListView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        selectedCardsListView.getItems().remove(selectedIndex);
    }
    NbCarte();
}
    
    @FXML
    protected void clearDeck() {
    selectedCardsListView.getItems().clear();
    NbCarte();
}
    
    @FXML
    protected void saveDeck() throws IOException {
        //debut du code pour crée un joueur et qu'il lance sa partie avec son deck
        if (selectedCardsListView.getItems().size() < 30){
            return;
        }
        Hero heroChoisi = null;
        for (carte carte : cartes) {
            for (String cartep : selectedCardsListView.getItems()){
                if (cartep == carte.getNom()){
                    DeckPlayer.add(new carteMonstre(carte.getNom(), carte.getCout(), carte.getDescription(), carte.getAttaque(), carte.getPV(), carte.getLienImage(), carte.getFaction()));
                }
            }
    
            
           }
        for (Hero hero : LstHeroes) {
            
                if (heroComboBox.getValue() == hero.GetNom()){
                    heroChoisi = hero;
                }
            
            }
        


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Game.fxml"));
        player dataObject =  new player(40,new DeckBuilder(DeckPlayer).build(),heroChoisi); 
        fxmlLoader.setControllerFactory(controllerClass -> {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(player.class,fact.getClass(),comboBoxPioche.getValue().getClass());
         return constructor.newInstance(dataObject,fact,comboBoxPioche.getValue());
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
        });
        scene = new Scene(fxmlLoader.load(), 640, 480);
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();

       
}

    @FXML
    protected void exit() {
    System.exit(0);
}



@FXML
protected void sortCardsByFactionOrdre() {
    cardNames.clear();

    for (carte carte : cartes) {
        if (carte.getFaction().GetNom() == "Ordre"){
            cardNames.add(carte.getNom());

        }
     }
     cardComboBox.getItems().clear();

     cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
     cardComboBox.setPromptText("Sélectionner une carte de l'Ordre");
     
}

@FXML
protected void sortCardsByFactionNeant() {
    cardNames.clear();

    for (carte carte : cartes) {
        if (carte.getFaction().GetNom() == "Neant"){
            cardNames.add(carte.getNom());

        }
     }
     cardComboBox.getItems().clear();

     cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
     cardComboBox.setPromptText("Sélectionner une carte du Neant");}
     



@FXML
protected void creatFactionChaos() {
    int i;
    int index=0;
    ObservableList<String> items = selectedCardsListView.getItems();
    for(i = 0; i < 5; i++){
        int indexAleatoire = rand.nextInt(cartesChaos.size());

        carte objetAleatoire = cartesChaos.get(indexAleatoire);
        if (selectedCardsListView.getItems().size() <30){
            for ( String carte : items ){
                if (carte == objetAleatoire.getNom() ){
                    index+=1;
                }
            }
            if (index <3){
            selectedCardsListView.getItems().add(objetAleatoire.getNom());
            }
        }
    
    }
    NbCarte();
}




@FXML
protected void createFactionNeant() {
    int i;
    int index=0;
    ObservableList<String> items = selectedCardsListView.getItems();

    for(i = 0; i < 5; i++){
    int indexAleatoire = rand.nextInt(cartesNeant.size());

    carte objetAleatoire = cartesNeant.get(indexAleatoire);
    if (selectedCardsListView.getItems().size() <30){
        for ( String carte : items ){
            if (carte == objetAleatoire.getNom() ){
                index+=1;
            }
        }
        if (index <3){
            selectedCardsListView.getItems().add(objetAleatoire.getNom());
        }
    }
}
NbCarte();

}




@FXML
protected void createFactionOrdre() {
    
    int i;
    int index=0;
    ObservableList<String> items = selectedCardsListView.getItems();

    for(i = 0; i < 5; i++){
    int indexAleatoire = rand.nextInt(cartesOrdre.size());

    carte objetAleatoire = cartesOrdre.get(indexAleatoire);

    if (selectedCardsListView.getItems().size() <30){
        for ( String carte : items ){
            if (carte == objetAleatoire.getNom() ){
                index+=1;
            }
        }
        if (index <3){
        selectedCardsListView.getItems().add(objetAleatoire.getNom());}}
    }
    NbCarte();

    }

    protected void NbCarte() {
        selectedCardCountLabel.setText("Nombre de cartes dans le deck: " + selectedCardsListView.getItems().size());
    }


@FXML
protected void sortCardsByFactionChaos() {
        cardNames.clear();

    for (carte carte : cartes) {
        if (carte.getFaction().GetNom() == "Chaos"){
            cardNames.add(carte.getNom());
        }
     }
     cardComboBox.getItems().clear();
     cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
     cardComboBox.setPromptText("Sélectionner une carte du Chaos");}



     public void setCartesEtFaction() {
        Faction Ordre = new FOrdre("Ordre","test","test");
        Faction Neant = new FNeant("Neant","test","test");
        Faction Chaos = new FChaos("Chaos","test","test");
        fact.add(Ordre);
        fact.add(Neant);
        fact.add(Chaos);

        cartes.add(new carteMonstre("Gardien du Temple", 1, "Un gardien mystique protégeant un ancien temple sacré.", 6, 11, "imageProjet/gardien_temple.jpeg", Ordre));
        cartes.add(new carteMonstre("Élémentaire de l'Ombre", 1, "Un être fait d'ombres tourbillonnantes, invoqué des ténèbres elles-mêmes.", 7, 12, "imageProjet/elementaire_ombre.jpeg", Neant));
        cartes.add(new carteMonstre("Démon de l'Abysse", 1, "Un démon maléfique émergeant des profondeurs de l'abysse pour semer le Chaos.", 8, 13, "imageProjet/demon_abysse.jpeg", Chaos));
        cartes.add(new carteMonstre("Chevalier de l'Aube", 1, "Un noble chevalier vêtu d'une armure étincelante, se battant pour la justice et l'honneur.", 9, 14, "imageProjet/chevalier_aube.jpeg", Ordre));
        cartes.add(new carteMonstre("Chimère des Abysses", 1, "Une créature monstrueuse combinant les traits des plus redoutables prédateurs des profondeurs marines.", 10, 15, "imageProjet/chimere_abysses.jpeg", Neant));
        cartes.add(new carteMonstre("Gardien des Montagnes", 2, "Un colosse de pierre, protecteur des montagnes et des vallées.", 11, 16, "imageProjet/gardien_montagnes.jpeg", Ordre));
        cartes.add(new carteMonstre("Spectre des Ombres", 2, "Une entité spectral hantant les recoins sombres du monde, emprisonnée entre les mondes des vivants et des morts.", 12, 17, "imageProjet/spectre_ombres.jpeg", Neant));
        cartes.add(new carteMonstre("Fouet du Désespoir", 2, "Un démoniaque fouet forgé dans les flammes de l'enfer, infligeant la douleur et le désespoir à quiconque ose s'y opposer.", 13, 18, "imageProjet/fouet_desespoir.jpeg", Chaos));
        cartes.add(new carteMonstre("Ange de l'Aurore", 2, "Un ange céleste descendu des cieux pour apporter la lumière et la bénédiction sur le monde des mortels.", 14, 19, "imageProjet/ange_aurore.jpeg", Ordre));
        cartes.add(new carteMonstre("Vampire Sanguinaire", 2, "Un seigneur vampire assoiffé de sang, régnant sur les ténèbres avec une cruauté sans limite.", 15, 20, "imageProjet/vampire_sanguinaire.jpeg", Neant));
        cartes.add(new carteMonstre("Hydre du Marais", 3, "Un monstre légendaire des marécages, avec plusieurs têtes crachant du poison mortel.", 16, 21, "imageProjet/hydre_marais.jpeg", Chaos));
        cartes.add(new carteMonstre("Gardien de la Forêt Éternelle", 3, "Un esprit ancestral veillant sur la forêt éternelle, où le temps ne s'écoule jamais.", 17, 22, "imageProjet/gardien_foret.jpeg", Ordre));
        cartes.add(new carteMonstre("Goule Affamée", 3, "Une créature répugnante, revenant des tombes pour dévorer les vivants.", 18, 23, "imageProjet/goule_affamee.jpeg", Neant));
        cartes.add(new carteMonstre("Kraken des Profondeurs", 3, "Une bête monstrueuse des abysses, terrorisant les océans avec ses tentacules géantes.", 19, 24, "imageProjet/kraken_profondeurs.jpeg", Chaos));
        cartes.add(new carteMonstre("Paladin de l'Aube", 3, "Un chevalier saint, portant une armure dorée et brandissant une épée de lumière contre les forces des ténèbres.", 20, 25, "imageProjet/paladin_aube.jpeg", Ordre));
        cartes.add(new carteMonstre("Sorcière des Ténèbres", 4, "Une puissante sorcière manipulant les ombres et les malédictions pour asservir ses ennemis.", 21, 26, "imageProjet/sorciere_tenebres.jpeg", Neant));
        cartes.add(new carteMonstre("Liche Sombre", 4, "Un ancien sorcier mort-vivant, maître des arts occultes et des morts-vivants.", 22, 27, "imageProjet/liche_sombre.jpeg", Chaos));
        cartes.add(new carteMonstre("Dragon de l'Aube", 4, "Un dragon majestueux, émergeant des premières lueurs du jour pour défendre les innocents contre les ténèbres.", 23, 28, "imageProjet/dragon_aube.jpeg", Ordre));
        cartes.add(new carteMonstre("Champion de la Nuit", 4, "Un guerrier impitoyable, évoluant dans les ombres de la nuit, dévastant ses ennemis avec une force surhumaine.", 24, 29, "imageProjet/champion_nuit.jpeg", Neant));
        cartes.add(new carteMonstre("Archidémon Malveillant", 4, "Un archidémon maléfique, souhaitant plonger le monde dans un abîme de désespoir et de tourments.", 25, 30, "imageProjet/archidemon_malveillant.jpeg", Chaos));
        cartes.add(new carteMonstre("Esprit de la Lune", 5, "Un esprit bienveillant émergeant des douces lueurs de la lune, apportant la sérénité et la guérison à ceux qui croisent son chemin.", 26, 31, "imageProjet/esprit_lune.jpeg", Ordre));
        cartes.add(new carteMonstre("Gargouille de la Nuit", 5, "Une gargouille maléfique, prenant vie sous le couvert de l'obscurité, terrorisant les villages endormis.Une gargouille maléfique, prenant vie sous le couvert de l'obscurité, terrorisant les villages endormis.", 27, 32, "imageProjet/gargouille_nuit.jpeg", Neant));
        cartes.add(new carteMonstre("Banshee Hurleuse", 5, "Une banshee spectral, hurlant des lamentations funèbres qui glacent le sang et annoncent la mort imminente.", 28, 33, "imageProjet/banshee_hurleuse.jpeg", Chaos));
        cartes.add(new carteMonstre("Protecteur Sacré", 5, "Un protecteur céleste, gardien des lieux saints et des âmes pures, défendant les innocents contre les forces du mal.", 29, 34, "imageProjet/protecteur_sacre.jpeg", Ordre));
        cartes.add(new carteMonstre("Revenant Vengeur", 5, "Un revenant revenant d'entre les morts pour rechercher vengeance contre ceux qui l'ont trahi en vie.", 30, 35, "imageProjet/revenant_vengeur.jpeg", Neant));
        cartes.add(new carteMonstre("Maraudeur Infernal", 6, "Un démon féroce, pillant et détruisant tout sur son passage, assoiffé de destruction et de Chaos.", 31, 36, "imageProjet/maraudeur_infernal.jpeg", Chaos));
        cartes.add(new carteMonstre("Sylphe des Bois", 6, "Un esprit des bois, protecteur des écosystèmes forestiers, veillant sur la flore et la faune avec bienveillance.", 32, 37, "imageProjet/sylphe_bois.jpeg", Ordre));
        cartes.add(new carteMonstre("Ombre Éternelle", 6, "Une entité mystérieuse, émergeant des ténèbres éternelles pour semer la terreur et la désolation dans le monde des mortels.", 33, 38, "imageProjet/ombre_eternelle.jpeg", Neant));
        cartes.add(new carteMonstre("Avatar du Désespoir", 6, "Une incarnation divine du désespoir, invoquée par les cœurs brisés et les âmes tourmentées pour apporter le Chaos et la destruction.", 34, 39, "imageProjet/avatar_desespoir.jpeg", Chaos));
        cartes.add(new carteMonstre("Gardien des Étoiles", 6, "Un être céleste, veillant sur les étoiles et les constellations, protégeant les secrets et les mystères des cieux.", 35, 40, "imageProjet/gardien_etoles.jpeg", Ordre));
        cartes.add(new carteMonstre("Élémentaire de Feu", 7, "Un esprit de feu impétueux, consumant tout sur son passage avec sa rage ardente et sa puissance incandescente.", 36, 41, "imageProjet/elementaire_feu.jpeg", Ordre));
        cartes.add(new carteMonstre("Spectre des Ténèbres", 7, "Une entité maléfique, émergeant des ombres pour hanter les esprits et corrompre les âmes.", 37, 42, "imageProjet/spectre_tenebres.jpeg", Neant));
        cartes.add(new carteMonstre("Loup-Garou Sanguinaire", 7, "Une créature mi-homme, mi-loup, frappée par une malédiction lunaire, cherchant à satisfaire sa faim de sang.", 38, 43, "imageProjet/loup_garou.jpeg", Chaos));
        cartes.add(new carteMonstre("Sage de l'Ancien Savoir", 7, "Un érudit mystique, gardien des connaissances anciennes et des secrets perdus dans les âges.", 39, 44, "imageProjet/sage_savoir.jpeg", Ordre));
        cartes.add(new carteMonstre("Champion de la Tempête", 7, "Un guerrier intrépide, maîtrisant les éléments déchaînés de la tempête pour terrasser ses ennemis avec une force dévastatrice.", 40, 45, "imageProjet/champion_tempete.jpeg", Neant));
        cartes.add(new carteMonstre("Reine des Ombres", 8, "Une souveraine impitoyable, régnant sur un royaume d'ombres et de cauchemars, orchestrant les ténèbres avec une main de fer.", 41, 46, "imageProjet/reine_ombres.jpeg", Chaos));
        cartes.add(new carteMonstre("Élémentaire de Terre", 8, "Un titan de terre gigantesque, émergeant des profondeurs de la terre pour défendre les terres sacrées contre toute menace.", 42, 47, "imageProjet/elementaire_terre.jpeg", Ordre));
        cartes.add(new carteMonstre("Esprit Vengeur", 8, "Un esprit tourmenté, revenant des limbes pour traquer et punir ceux qui lui ont fait du tort de son vivant.", 43, 48, "imageProjet/Esprit_Vengeur.jpeg", Neant));
        cartes.add(new carteMonstre("Bête Rampante", 8, "Une créature reptilienne monstrueuse, se glissant dans les ténèbres pour chasser ses proies avec une agilité mortelle.", 44, 49, "imageProjet/bete_rampante.jpeg", Chaos));
        cartes.add(new carteMonstre("Paladin de la Justice", 8, "Un chevalier saint, portant une armure étincelante et brandissant une épée de justice pour punir les méchants et protéger les innocents.", 45, 50, "imageProjet/paladin_justice.jpeg", Ordre));
        cartes.add(new carteMonstre("Ange Déchu", 9, "Un ange autrefois céleste, corrompu par les ténèbres et devenu un être déchu, semant la destruction et la désolation.", 46, 51, "imageProjet/ange_dechu.jpeg", Chaos));
        cartes.add(new carteMonstre("Sorcier Maudit", 9, "Un puissant sorcier maudit par des forces obscures, utilisant ses pouvoirs pour assouvir sa soif de vengeance et de pouvoir.", 47, 52, "imageProjet/sorcier_maudit.jpeg", Ordre));
        cartes.add(new carteMonstre("Banshee Sombre", 9, "Une banshee maléfique, déchirée par la douleur et la colère, hurlant des malédictions mortelles pour ceux qui croisent son chemin.", 48, 53, "imageProjet/banshee_sombre.jpeg", Neant));
        cartes.add(new carteMonstre("Hydre de l'Enfer", 9, "Un monstre infernal à multiples têtes, surgissant des flammes de l'enfer pour semer la destruction et la terreur sur son passage.", 49, 54, "imageProjet/hydre_enfer.jpeg", Chaos));
        cartes.add(new carteMonstre("Mage de l'Arcane", 9, "Un maître des arcanes, manipulant les énergies mystiques pour façonner la réalité selon sa volonté et ses désirs.", 50, 55, "imageProjet/mage_arcane.jpeg", Ordre));
        cartes.add(new carteMonstre("Harpie Maudite", 10, "Une créature aviaire maléfique, sillonnant les cieux pour traquer et déchiqueter ses proies avec ses serres acérées.", 51, 56, "imageProjet/harpie_maudite.jpeg", Neant));
        cartes.add(new carteMonstre("Démon des Abysses", 10, "Un démon primordial, émergeant des abysses les plus profonds pour semer la destruction et le Chaos sur terre et mer.", 52, 57, "imageProjet/demon_abysses.jpeg", Chaos));
        cartes.add(new carteMonstre("Sage de la Sagesse Ancienne", 10, "Un sage vénérable, gardien des connaissances anciennes et des secrets enfouis dans les annales du temps.", 53, 58, "imageProjet/sage_sagesse.jpeg", Ordre));
        cartes.add(new carteMonstre("Spectre Vengeur", 10, "Un spectre revenant d'entre les morts, hantant les vivants pour se venger de ceux qui ont causé sa mort injuste.", 54, 59, "imageProjet/spectre_vengeur.jpeg", Neant));
        cartes.add(new carteMonstre("Dragon des Abysses", 10, "Un dragon colossal, régnant sur les abysses avec une puissance incommensurable, gardien des secrets les plus sombres de l'océan.", 55, 60, "imageProjet/dragon_abysses.jpeg", Chaos));
        cartes.add(new carteMonstre("Igris, le commandant rouge", 10, "Un guerrier féroce et puissant, portant une aura de feu et démontrant une habileté redoutable au combat.", 55, 60, "imageProjet/igris.jpeg", Neant));
       

        //Hero

        LstHeroes.add(new Hero("Aetheris", "Protecteur des royaumes aériens", "imageProjet/Aetheris.jpeg", Ordre));
        LstHeroes.add(new Hero("Sylveria", "Maîtresse de la forêt et des embuscades", "imageProjet/Sylveria.jpeg", Ordre));
        LstHeroes.add(new Hero("Valdar", "Forgeron légendaire et guerrier intrépide", "imageProjet/Valdar.jpeg", Ordre));
        LstHeroes.add(new Hero("Lyra", "Traqueuse des ténèbres et des créatures nocturnes", "imageProjet/Lyra.jpg", Ordre));
        LstHeroes.add(new Hero("Ignatius", "Purificateur des maléfices et chasseur de démons", "imageProjet/Ignatius.jpg", Ordre));
        LstHeroes.add(new Hero("Elara", "Protectrice des constellations et guide des voyageurs", "imageProjet/Elara.jpg", Ordre));
        LstHeroes.add(new Hero("Drakon", "Dompteur de dragons et protecteur des terres reculées", "imageProjet/Drakon.jpg", Ordre));
        LstHeroes.add(new Hero("Aurora", "Porteuse de lumière et championne de l'aube naissante", "imageProjet/Aurora.jpg", Ordre));
        LstHeroes.add(new Hero("Thaldir", "Navigateur des mers brumeuses et protecteur des marins", "imageProjet/Thaldir.jpg", Ordre));
        LstHeroes.add(new Hero("Gaia", "Protectrice de la faune et de la flore, équilibre naturel", "imageProjet/Gaia.jpg", Ordre));
        LstHeroes.add(new Hero("Vesper", "Explorateur des dimensions parallèles et gardien des portails", "imageProjet/Vesper.jpg", Neant));
        LstHeroes.add(new Hero("Argentia", "Protectrice des cieux et des âmes égarées", "imageProjet/Argentia.jpg", Neant));
        LstHeroes.add(new Hero("Hélios", "Répandeur de chaleur et purificateur des ténèbres", "imageProjet/Hélios.jpg", Neant));
        LstHeroes.add(new Hero("Nyx", "Silencieuse et furtive, elle traque les ennemis de la nuit", "imageProjet/Nyx.jpg", Neant));
        LstHeroes.add(new Hero("Ragnar", "Libérateur des opprimés et défenseur des faibles", "imageProjet/Ragnar.jpg", Neant));
        LstHeroes.add(new Hero("Seraphina", "Gardienne du feu sacré et inspiratrice des cœurs", "imageProjet/Seraphina.jpeg", Neant));
        LstHeroes.add(new Hero("Tundra", "Protectrice des terres gelées et maîtresse des tempêtes", "imageProjet/Tundra.jpeg", Neant));
        LstHeroes.add(new Hero("Orion", "Explorateur des galaxies et protecteur des secrets cosmiques", "imageProjet/Orion.jpeg", Neant));
        LstHeroes.add(new Hero("Selene", "Messagère des songes et guide dans l'obscurité", "imageProjet/Selene.jpeg", Neant));
        LstHeroes.add(new Hero("Typhoon", "Maître des vents et déchaîneur de tempêtes", "imageProjet/Typhoon.jpeg", Neant));
        LstHeroes.add(new Hero("Zephyr", "Silhouette éthérée, maître des courants d'air", "imageProjet/Zephyr.jpeg", Chaos));
        LstHeroes.add(new Hero("Cyrus", "Manipulateur des flammes, Chaos incarné", "imageProjet/Cyrus.jpeg", Chaos));
        LstHeroes.add(new Hero("Xanthe", "Illusionniste impitoyable, maître de la confusion", "imageProjet/Xanthe.jpeg", Chaos));
        LstHeroes.add(new Hero("Maelstrom", "Tourbillon destructeur, invoqué par la colère", "imageProjet/Maelstrom.jpeg", Chaos));
        LstHeroes.add(new Hero("Nebula", "Entité cosmique, forgeur de réalités alternatives", "imageProjet/Nebula.jpeg", Chaos));
        LstHeroes.add(new Hero("Eclipse", "Éclipse des âmes, apporteur de désespoir", "imageProjet/Eclipse.jpeg", Chaos));
        LstHeroes.add(new Hero("Oblivion", "Avatar de l'oubli, qui efface toute mémoire", "imageProjet/Oblivion.jpeg", Chaos));
        LstHeroes.add(new Hero("Vortex", "Dévoreur d'énergies, perturbateur des équilibres", "imageProjet/Vortex.jpeg", Chaos));
        LstHeroes.add(new Hero("Nemesis", "Châtiment implacable, destin des déchus", "imageProjet/Nemesis.jpeg", Chaos));
        LstHeroes.add(new Hero("Xeno", "Inconnu aux origines mystérieuses", "imageProjet/Xeno.jpeg", Chaos));
     
        for (Hero hero : LstHeroes) {
            heroNames.add(hero.GetNom());
        }   
        for (carte carte : cartes) {
           cardNames.add(carte.getNom());

           switch (carte.getFaction().GetNom()) {
               case "Ordre":
                   cartesOrdre.add(carte);
                   break;
               case "Neant":
                   cartesNeant.add(carte);
                   break;
               case "Chaos":
                   cartesChaos.add(carte);
                   break;
               default:
                   break;
           }
        } 
    }

}


