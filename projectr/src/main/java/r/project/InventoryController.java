package r.project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.util.Random;


import java.util.ArrayList;


public class InventoryController {

    @FXML
    private ComboBox<String> cardComboBox;
    @FXML
    private TextField  cardDescriptionTextField;
    @FXML
    private TextField  cardNameTextField;
    @FXML
    private Label detailsLabel;
    @FXML
    private TextField costTextField;
    @FXML
    private TextField hpTextField;
    @FXML
    private TextField attackTextField;
    @FXML
    private TextField imageNameTextField;
    
    
    

    @FXML
    private ComboBox<String> factionComboBox;
    @FXML
    private ImageView imageView;
    
    Random rand = new Random();
    private ArrayList<carte> cartes = new ArrayList<>();
    private ArrayList<Faction> fact = new ArrayList<>();
    private ArrayList<carte> cartesOrde = new ArrayList<>();
    private ArrayList<carte> cartesNeant = new ArrayList<>();
    private ArrayList<carte> cartesChaos = new ArrayList<>();

    private ArrayList<String> cardNames  = new ArrayList<>();    
    @FXML
    private ListView<String> selectedCardsListView;
    
        

    @FXML
    public void initialize() {
      
        
        setCartesEtFaction();
        
         cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
         cardComboBox.setPromptText("Sélectionner une carte");
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
    }
    
    @FXML
    protected void addToDeck() {
    String selectedCard = cardComboBox.getValue();
    if (selectedCard != null && !selectedCard.isEmpty()) {
        selectedCardsListView.getItems().add(selectedCard);
    }
}
    
    @FXML
    protected void removeFromDeck() {
    int selectedIndex = selectedCardsListView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        selectedCardsListView.getItems().remove(selectedIndex);
    }
}
    
    @FXML
    protected void clearDeck() {
    selectedCardsListView.getItems().clear();
}
    
    @FXML
    protected void saveDeck() {
        
}
    
  
    
    @FXML
    protected void exit() {
    System.exit(0);
}
@FXML
protected void sortCardsByFactionOrde() {
    cardNames.clear();

    for (carte carte : cartes) {
        if (carte.getFaction().GetNom() == "Orde"){
            cardNames.add(carte.getNom());

        }
     }
     cardComboBox.getItems().clear();

     cardComboBox.setItems(FXCollections.observableArrayList(cardNames));
     cardComboBox.setPromptText("Sélectionner une carte de l'Orde");
     
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
    for(i = 0; i < 5; i++){
        int indexAleatoire = rand.nextInt(cartesChaos.size());

        carte objetAleatoire = cartesChaos.get(indexAleatoire);
    
        selectedCardsListView.getItems().add(objetAleatoire.getNom());
    }}




@FXML
protected void createFactionNeant() {
    int i;
    for(i = 0; i < 5; i++){
    int indexAleatoire = rand.nextInt(cartesNeant.size());

    carte objetAleatoire = cartesNeant.get(indexAleatoire);

    selectedCardsListView.getItems().add(objetAleatoire.getNom());}}



@FXML
protected void createFactionOrde() {
    
    int i;
    for(i = 0; i < 5; i++){
    int indexAleatoire = rand.nextInt(cartesOrde.size());

    carte objetAleatoire = cartesOrde.get(indexAleatoire);

    selectedCardsListView.getItems().add(objetAleatoire.getNom());}}


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
        Faction orde = new FOrdre("Orde","test","test");
        Faction neant = new FNeant("Neant","test","test");
        Faction chaos = new FChaos("Chaos","test","test");
        fact.add(orde);
        fact.add(neant);
        fact.add(chaos);

        cartes.add(new carteMonstre("Gardien du Temple", 11, "Un gardien mystique protégeant un ancien temple sacré.", 6, 11, "imageProjet/gardien_temple.jpeg", orde));
        cartes.add(new carteMonstre("Élémentaire de l'Ombre", 12, "Un être fait d'ombres tourbillonnantes, invoqué des ténèbres elles-mêmes.", 7, 12, "imageProjet/elementaire_ombre.jpeg", neant));
        cartes.add(new carteMonstre("Démon de l'Abysse", 13, "Un démon maléfique émergeant des profondeurs de l'abysse pour semer le chaos.", 8, 13, "imageProjet/demon_abysse.jpeg", chaos));
        cartes.add(new carteMonstre("Chevalier de l'Aube", 14, "Un noble chevalier vêtu d'une armure étincelante, se battant pour la justice et l'honneur.", 9, 14, "imageProjet/chevalier_aube.jpeg", orde));
        cartes.add(new carteMonstre("Chimère des Abysses", 15, "Une créature monstrueuse combinant les traits des plus redoutables prédateurs des profondeurs marines.", 10, 15, "imageProjet/chimere_abysses.jpeg", neant));
        cartes.add(new carteMonstre("Gardien des Montagnes", 16, "Un colosse de pierre, protecteur des montagnes et des vallées.", 11, 16, "imageProjet/gardien_montagnes.jpeg", orde));
        cartes.add(new carteMonstre("Spectre des Ombres", 17, "Une entité spectral hantant les recoins sombres du monde, emprisonnée entre les mondes des vivants et des morts.", 12, 17, "imageProjet/spectre_ombres.jpeg", neant));
        cartes.add(new carteMonstre("Fouet du Désespoir", 18, "Un démoniaque fouet forgé dans les flammes de l'enfer, infligeant la douleur et le désespoir à quiconque ose s'y opposer.", 13, 18, "imageProjet/fouet_desespoir.jpeg", chaos));
        cartes.add(new carteMonstre("Ange de l'Aurore", 19, "Un ange céleste descendu des cieux pour apporter la lumière et la bénédiction sur le monde des mortels.", 14, 19, "imageProjet/ange_aurore.jpeg", orde));
        cartes.add(new carteMonstre("Vampire Sanguinaire", 20, "Un seigneur vampire assoiffé de sang, régnant sur les ténèbres avec une cruauté sans limite.", 15, 20, "imageProjet/vampire_sanguinaire.jpeg", neant));
        cartes.add(new carteMonstre("Hydre du Marais", 21, "Un monstre légendaire des marécages, avec plusieurs têtes crachant du poison mortel.", 16, 21, "imageProjet/hydre_marais.jpeg", chaos));
        cartes.add(new carteMonstre("Gardien de la Forêt Éternelle", 22, "Un esprit ancestral veillant sur la forêt éternelle, où le temps ne s'écoule jamais.", 17, 22, "imageProjet/gardien_foret.jpeg", orde));
        cartes.add(new carteMonstre("Goule Affamée", 23, "Une créature répugnante, revenant des tombes pour dévorer les vivants.", 18, 23, "imageProjet/goule_affamee.jpeg", neant));
        cartes.add(new carteMonstre("Kraken des Profondeurs", 24, "Une bête monstrueuse des abysses, terrorisant les océans avec ses tentacules géantes.", 19, 24, "imageProjet/kraken_profondeurs.jpeg", chaos));
        cartes.add(new carteMonstre("Paladin de l'Aube", 25, "Un chevalier saint, portant une armure dorée et brandissant une épée de lumière contre les forces des ténèbres.", 20, 25, "imageProjet/paladin_aube.jpeg", orde));
        cartes.add(new carteMonstre("Sorcière des Ténèbres", 26, "Une puissante sorcière manipulant les ombres et les malédictions pour asservir ses ennemis.", 21, 26, "imageProjet/sorciere_tenebres.jpeg", neant));
        cartes.add(new carteMonstre("Liche Sombre", 27, "Un ancien sorcier mort-vivant, maître des arts occultes et des morts-vivants.", 22, 27, "imageProjet/liche_sombre.jpeg", chaos));
        cartes.add(new carteMonstre("Dragon de l'Aube", 28, "Un dragon majestueux, émergeant des premières lueurs du jour pour défendre les innocents contre les ténèbres.", 23, 28, "imageProjet/dragon_aube.jpeg", orde));
        cartes.add(new carteMonstre("Champion de la Nuit", 29, "Un guerrier impitoyable, évoluant dans les ombres de la nuit, dévastant ses ennemis avec une force surhumaine.", 24, 29, "imageProjet/champion_nuit.jpeg", neant));
        cartes.add(new carteMonstre("Archidémon Malveillant", 30, "Un archidémon maléfique, souhaitant plonger le monde dans un abîme de désespoir et de tourments.", 25, 30, "imageProjet/archidemon_malveillant.jpeg", chaos));
        cartes.add(new carteMonstre("Esprit de la Lune", 31, "Un esprit bienveillant émergeant des douces lueurs de la lune, apportant la sérénité et la guérison à ceux qui croisent son chemin.", 26, 31, "imageProjet/esprit_lune.jpeg", orde));
        cartes.add(new carteMonstre("Gargouille de la Nuit", 32, "Une gargouille maléfique, prenant vie sous le couvert de l'obscurité, terrorisant les villages endormis.Une gargouille maléfique, prenant vie sous le couvert de l'obscurité, terrorisant les villages endormis.", 27, 32, "imageProjet/gargouille_nuit.jpeg", neant));
        cartes.add(new carteMonstre("Banshee Hurleuse", 33, "Une banshee spectral, hurlant des lamentations funèbres qui glacent le sang et annoncent la mort imminente.", 28, 33, "imageProjet/banshee_hurleuse.jpeg", chaos));
        cartes.add(new carteMonstre("Protecteur Sacré", 34, "Un protecteur céleste, gardien des lieux saints et des âmes pures, défendant les innocents contre les forces du mal.", 29, 34, "imageProjet/protecteur_sacre.jpeg", orde));
        cartes.add(new carteMonstre("Revenant Vengeur", 35, "Un revenant revenant d'entre les morts pour rechercher vengeance contre ceux qui l'ont trahi en vie.", 30, 35, "imageProjet/revenant_vengeur.jpeg", neant));
        cartes.add(new carteMonstre("Maraudeur Infernal", 36, "Un démon féroce, pillant et détruisant tout sur son passage, assoiffé de destruction et de chaos.", 31, 36, "imageProjet/maraudeur_infernal.jpeg", chaos));
        cartes.add(new carteMonstre("Sylphe des Bois", 37, "Un esprit des bois, protecteur des écosystèmes forestiers, veillant sur la flore et la faune avec bienveillance.", 32, 37, "imageProjet/sylphe_bois.jpeg", orde));
        cartes.add(new carteMonstre("Ombre Éternelle", 38, "Une entité mystérieuse, émergeant des ténèbres éternelles pour semer la terreur et la désolation dans le monde des mortels.", 33, 38, "imageProjet/ombre_eternelle.jpeg", neant));
        cartes.add(new carteMonstre("Avatar du Désespoir", 39, "Une incarnation divine du désespoir, invoquée par les cœurs brisés et les âmes tourmentées pour apporter le chaos et la destruction.", 34, 39, "imageProjet/avatar_desespoir.jpeg", chaos));
        cartes.add(new carteMonstre("Gardien des Étoiles", 40, "Un être céleste, veillant sur les étoiles et les constellations, protégeant les secrets et les mystères des cieux.", 35, 40, "imageProjet/gardien_etoles.jpeg", orde));
        cartes.add(new carteMonstre("Élémentaire de Feu", 41, "Un esprit de feu impétueux, consumant tout sur son passage avec sa rage ardente et sa puissance incandescente.", 36, 41, "imageProjet/elementaire_feu.jpeg", orde));
        cartes.add(new carteMonstre("Spectre des Ténèbres", 42, "Une entité maléfique, émergeant des ombres pour hanter les esprits et corrompre les âmes.", 37, 42, "imageProjet/spectre_tenebres.jpeg", neant));
        cartes.add(new carteMonstre("Loup-Garou Sanguinaire", 43, "Une créature mi-homme, mi-loup, frappée par une malédiction lunaire, cherchant à satisfaire sa faim de sang.", 38, 43, "imageProjet/loup_garou.jpeg", chaos));
        cartes.add(new carteMonstre("Sage de l'Ancien Savoir", 44, "Un érudit mystique, gardien des connaissances anciennes et des secrets perdus dans les âges.", 39, 44, "imageProjet/sage_savoir.jpeg", orde));
        cartes.add(new carteMonstre("Champion de la Tempête", 45, "Un guerrier intrépide, maîtrisant les éléments déchaînés de la tempête pour terrasser ses ennemis avec une force dévastatrice.", 40, 45, "imageProjet/champion_tempete.jpeg", neant));
        cartes.add(new carteMonstre("Reine des Ombres", 46, "Une souveraine impitoyable, régnant sur un royaume d'ombres et de cauchemars, orchestrant les ténèbres avec une main de fer.", 41, 46, "imageProjet/reine_ombres.jpeg", chaos));
        cartes.add(new carteMonstre("Élémentaire de Terre", 47, "Un titan de terre gigantesque, émergeant des profondeurs de la terre pour défendre les terres sacrées contre toute menace.", 42, 47, "imageProjet/elementaire_terre.jpeg", orde));
        cartes.add(new carteMonstre("Esprit Vengeur", 48, "Un esprit tourmenté, revenant des limbes pour traquer et punir ceux qui lui ont fait du tort de son vivant.", 43, 48, "imageProjet/  .jpeg", neant));
        cartes.add(new carteMonstre("Bête Rampante", 49, "Une créature reptilienne monstrueuse, se glissant dans les ténèbres pour chasser ses proies avec une agilité mortelle.", 44, 49, "imageProjet/bete_rampante.jpeg", chaos));
        cartes.add(new carteMonstre("Paladin de la Justice", 50, "Un chevalier saint, portant une armure étincelante et brandissant une épée de justice pour punir les méchants et protéger les innocents.", 45, 50, "imageProjet/paladin_justice.jpeg", orde));
        cartes.add(new carteMonstre("Ange Déchu", 51, "Un ange autrefois céleste, corrompu par les ténèbres et devenu un être déchu, semant la destruction et la désolation.", 46, 51, "imageProjet/ange_dechu.jpeg", chaos));
        cartes.add(new carteMonstre("Sorcier Maudit", 52, "Un puissant sorcier maudit par des forces obscures, utilisant ses pouvoirs pour assouvir sa soif de vengeance et de pouvoir.", 47, 52, "imageProjet/sorcier_maudit.jpeg", orde));
        cartes.add(new carteMonstre("Banshee Sombre", 53, "Une banshee maléfique, déchirée par la douleur et la colère, hurlant des malédictions mortelles pour ceux qui croisent son chemin.", 48, 53, "imageProjet/banshee_sombre.jpeg", neant));
        cartes.add(new carteMonstre("Hydre de l'Enfer", 54, "Un monstre infernal à multiples têtes, surgissant des flammes de l'enfer pour semer la destruction et la terreur sur son passage.", 49, 54, "imageProjet/hydre_enfer.jpeg", chaos));
        cartes.add(new carteMonstre("Mage de l'Arcane", 55, "Un maître des arcanes, manipulant les énergies mystiques pour façonner la réalité selon sa volonté et ses désirs.", 50, 55, "imageProjet/mage_arcane.jpeg", orde));
        cartes.add(new carteMonstre("Harpie Maudite", 56, "Une créature aviaire maléfique, sillonnant les cieux pour traquer et déchiqueter ses proies avec ses serres acérées.", 51, 56, "imageProjet/harpie_maudite.jpeg", neant));
        cartes.add(new carteMonstre("Démon des Abysses", 57, "Un démon primordial, émergeant des abysses les plus profonds pour semer la destruction et le chaos sur terre et mer.", 52, 57, "imageProjet/demon_abysses.jpeg", chaos));
        cartes.add(new carteMonstre("Sage de la Sagesse Ancienne", 58, "Un sage vénérable, gardien des connaissances anciennes et des secrets enfouis dans les annales du temps.", 53, 58, "imageProjet/sage_sagesse.jpeg", orde));
        cartes.add(new carteMonstre("Spectre Vengeur", 59, "Un spectre revenant d'entre les morts, hantant les vivants pour se venger de ceux qui ont causé sa mort injuste.", 54, 59, "imageProjet/spectre_vengeur.jpeg", neant));
        cartes.add(new carteMonstre("Dragon des Abysses", 60, "Un dragon colossal, régnant sur les abysses avec une puissance incommensurable, gardien des secrets les plus sombres de l'océan.", 55, 60, "imageProjet/dragon_abysses.jpeg", chaos));
        cartes.add(new carteMonstre("Igris, le commandant rouge", 60, "Un guerrier féroce et puissant, portant une aura de feu et démontrant une habileté redoutable au combat.", 55, 60, "imageProjet/igris.jpeg", neant));
       
        for (carte carte : cartes) {
           cardNames.add(carte.getNom());

           switch (carte.getFaction().GetNom()) {
               case "Orde":
                   cartesOrde.add(carte);
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


