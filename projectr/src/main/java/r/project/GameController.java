package r.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
