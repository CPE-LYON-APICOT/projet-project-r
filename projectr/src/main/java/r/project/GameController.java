package r.project;

import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

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
    private FlowPane playerCardPane;

    @FXML
    private FlowPane monsterCardPane;

    @FXML
    private StackPane gameBoard;

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
    private Label playerHealthLabel;


    @FXML
    private ListView<String> selectedCardsListViewPlayer;
    private ArrayList<CreaMonstre> lstMonster = new ArrayList<>();
    private ArrayList<CreaBoss> lstBoss = new ArrayList<>();
    private player JoueurActuel;
    private ArrayList<Faction> fact = new ArrayList<>();
  


    public GameController(player dataObject,ArrayList<Faction> dataList) {
        this.JoueurActuel = dataObject;
        this.fact = dataList;
        
       
        
    }

    @FXML
    public void initialize(){
        ArrayList<carte> cartesList = new ArrayList<>(JoueurActuel.getLstDeck());

        playerHealthLabel.setText(String.valueOf(JoueurActuel.getPv()));
        setCreature();
        Image image = new Image(getClass().getResourceAsStream(cartesList.get(0).getLienImage()));
        card1Image.setImage(image);
        
        card1LabelNom.setText("Nom : "+cartesList.get(0).getNom());
        card1LabelAttaque.setText("Attack : "+String.valueOf(lstMonster.get(0).getAttaque()));
        card1LabelPV.setText("Pv : "+String.valueOf(lstMonster.get(0).getPv()));
        Image image2 = new Image(getClass().getResourceAsStream(cartesList.get(1).getLienImage()));
        card2Image.setImage(image2);
        card2LabelNom.setText("Nom : "+cartesList.get(1).getNom());
        card2LabelAttaque.setText("Attack : "+String.valueOf(lstMonster.get(1).getAttaque()));
        card2LabelPV.setText("Pv : "+String.valueOf(lstMonster.get(1).getPv()));
        Image image3 = new Image(getClass().getResourceAsStream(cartesList.get(2).getLienImage()));
        card3Image.setImage(image3);
        card3LabelNom.setText("Nom : "+cartesList.get(2).getNom());
        card3LabelAttaque.setText("Attack : "+String.valueOf(lstMonster.get(2).getAttaque()));
        card3LabelPV.setText("Pv : "+String.valueOf(lstMonster.get(2).getPv()));

        Image image4 = new Image(getClass().getResourceAsStream(cartesList.get(3).getLienImage()));
        card4Image.setImage(image4);
        card4LabelNom.setText("Nom : "+cartesList.get(3).getNom());
        card4LabelAttaque.setText("Attack : "+String.valueOf(lstMonster.get(3).getAttaque()));
        card4LabelPV.setText("Pv : "+String.valueOf(lstMonster.get(3).getPv()));
        Image image5 = new Image(getClass().getResourceAsStream(cartesList.get(4).getLienImage()));
        card5Image.setImage(image5);
        card5LabelNom.setText("Nom : "+cartesList.get(4).getNom());
        card5LabelAttaque.setText("Attack : "+String.valueOf(lstMonster.get(4).getAttaque()));
        card5LabelPV.setText("Pv : "+String.valueOf(lstMonster.get(4).getPv()));

    }

    // Méthode pour l'action de jouer une carte
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
        lstMonster.add(new CreaMonstre(30, 10, "Gobelin des marais", "Un petit être verdâtre, agile et sournois, vivant dans les marais.", "image1", null));
        lstMonster.add(new CreaMonstre(100, 20, "Golem de pierre", "Une créature massive composée de roches géantes. Elle frappe ses ennemis avec une force écrasante.", "image2", null));
        lstMonster.add(new CreaMonstre(50, 15, "Spectre glacé", "Une entité fantomatique qui hante les toundras en gelant tout sur son passage. Ses attaques drainent la chaleur de ses victimes.", "lien_image_spectre_glacé", null));
        lstMonster.add(new CreaMonstre(80, 25, "Chimère démoniaque", "Une abomination combinant les traits d'un lion, d'un serpent et d'un démon. Elle crache du feu et empoisonne ses adversaires.", "lien_image_chimère_démoniaque", null));
        lstMonster.add(new CreaMonstre(60, 18, "Esprit des ombres", "Une entité obscure et insaisissable qui se dissimule dans les ténèbres. Ses attaques sont furtives et drainent l'énergie vitale.", "lien_image_esprit_des_ombres", null));
        lstMonster.add(new CreaMonstre(70, 22, "Araignée géante", "Une araignée monstrueuse aux pattes velues et aux crocs venimeux. Elle tisse des toiles pour capturer ses proies.", "lien_image_araignée_géante", null));
        lstMonster.add(new CreaMonstre(90, 30, "Basilic des abysses", "Une créature reptilienne vivant dans les profondeurs océaniques. Son regard pétrifie ses ennemis, et ses crocs sont acérés comme des lames.", "lien_image_basilic_des_abysses", null));
        lstMonster.add(new CreaMonstre(120, 35, "Behemoth des montagnes", "Une bête titanesque parcourant les sommets en détruisant tout sur son passage. Ses rugissements déclenchent des éboulements.", "lien_image_behemoth_des_montagnes", null));
        lstMonster.add(new CreaMonstre(100, 28, "Liche maudite", "Un sorcier mort-vivant ayant acquis un pouvoir sinistre. Il lance des malédictions et invoque des âmes tourmentées pour combattre.", "lien_image_liche_maudite", null));
    }
    // Autres méthodes nécessaires pour le fonctionnement du jeu
}
