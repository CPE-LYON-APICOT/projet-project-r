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
    private ArrayList<CreaMonstre> lstMonster = new ArrayList<>();
    private ArrayList<CreaBoss> lstBoss = new ArrayList<>();

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
