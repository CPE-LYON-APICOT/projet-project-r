package r.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override

    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("inventory"), 640, 480);

        stage.setScene(scene);
        stage.show();
       
    }

   

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();
        ArrayList<Hero> LstHeroes = new ArrayList<>();
        Faction Chaos=new FChaos("Chaos", "Que du sale", "rouge");
        Faction Neant=new FNeant("Neant", "Que du Vide", "Violet");
        Faction Ordre=new FOrdre("Ordre", "Que du propre", "Bleu");

        Hero Hero1=new Hero("Aetheris", "Protecteur des royaumes aériens", "Image1.png", Ordre);
        Hero Hero2=new Hero("Sylveria", "Maîtresse de la forêt et des embuscades", "Image2.png", Ordre);
        Hero Hero3=new Hero("Valdar", "Forgeron légendaire et guerrier intrépide", "Image3.png", Ordre);
        Hero Hero4=new Hero("Lyra", "Traqueuse des ténèbres et des créatures nocturnes", "Image4.png", Ordre);
        Hero Hero5=new Hero("Ignatius", "Purificateur des maléfices et chasseur de démons", "Image5.png", Ordre);
        Hero Hero6=new Hero("Elara", "Protectrice des constellations et guide des voyageurs", "Image6.png", Ordre);
        Hero Hero7=new Hero("Drakon", "Dompteur de dragons et protecteur des terres reculées", "Image7.png", Ordre);
        Hero Hero8=new Hero("Aurora", "Porteuse de lumière et championne de l'aube naissante", "Image8.png", Ordre);
        Hero Hero9=new Hero("Thaldir", "Navigateur des mers brumeuses et protecteur des marins", "Image9.png", Ordre);
        Hero Hero10=new Hero("Gaia", "Protectrice de la faune et de la flore, équilibre naturel", "Image10.png", Ordre);
        Hero Hero11=new Hero("Vesper", "Explorateur des dimensions parallèles et gardien des portails", "Image11.png", Neant);
        Hero Hero12=new Hero("Argentia", "Protectrice des cieux et des âmes égarées", "Image12.png", Neant);
        Hero Hero13=new Hero("Hélios", "Répandeur de chaleur et purificateur des ténèbres", "Image13.png", Neant);
        Hero Hero14=new Hero("Nyx", "Silencieuse et furtive, elle traque les ennemis de la nuit", "Image14.png", Neant);
        Hero Hero15=new Hero("Ragnar", "Libérateur des opprimés et défenseur des faibles", "Image15.png", Neant);
        Hero Hero16=new Hero("Seraphina", "Gardienne du feu sacré et inspiratrice des cœurs", "Image16.png", Neant);
        Hero Hero17=new Hero("Tundra", "Protectrice des terres gelées et maîtresse des tempêtes", "Image17.png", Neant);
        Hero Hero18=new Hero("Orion", "Explorateur des galaxies et protecteur des secrets cosmiques", "Image18.png", Neant);
        Hero Hero19=new Hero("Selene", "Messagère des songes et guide dans l'obscurité", "Image19.png", Neant);
        Hero Hero20=new Hero("Typhoon", "Maître des vents et déchaîneur de tempêtes", "Image20.png", Neant);
        Hero Hero21=new Hero("Zephyr", "Silhouette éthérée, maître des courants d'air", "Image21.png", Chaos);
        Hero Hero22=new Hero("Cyrus", "Manipulateur des flammes, chaos incarné", "Image22.png", Chaos);
        Hero Hero23=new Hero("Xanthe", "Illusionniste impitoyable, maître de la confusion", "Image23.png", Chaos);
        Hero Hero24=new Hero("Maelstrom", "Tourbillon destructeur, invoqué par la colère", "Image24.png", Chaos);
        Hero Hero25=new Hero("Nebula", "Entité cosmique, forgeur de réalités alternatives", "Image25.png", Chaos);
        Hero Hero26=new Hero("Eclipse", "Éclipse des âmes, apporteur de désespoir", "Image26.png", Chaos);
        Hero Hero27=new Hero("Oblivion", "Avatar de l'oubli, qui efface toute mémoire", "Image27.png", Chaos);
        Hero Hero28=new Hero("Vortex", "Dévoreur d'énergies, perturbateur des équilibres", "Image28.png", Chaos);
        Hero Hero29=new Hero("Nemesis", "Châtiment implacable, destin des déchus", "Image29.png", Chaos);
        Hero Hero30=new Hero("Xeno", "Inconnu aux origines mystérieuses", "Image30.png", Chaos);
        LstHeroes.add(Hero1);
        LstHeroes.add(Hero2);
        LstHeroes.add(Hero3);
        LstHeroes.add(Hero4);
        LstHeroes.add(Hero5);
        LstHeroes.add(Hero6);
        LstHeroes.add(Hero7);
        LstHeroes.add(Hero8);
        LstHeroes.add(Hero9);
        LstHeroes.add(Hero10);
        LstHeroes.add(Hero11);
        LstHeroes.add(Hero12);
        LstHeroes.add(Hero13);
        LstHeroes.add(Hero14);
        LstHeroes.add(Hero15);
        LstHeroes.add(Hero16);
        LstHeroes.add(Hero17);
        LstHeroes.add(Hero18);
        LstHeroes.add(Hero19);
        LstHeroes.add(Hero20);
        LstHeroes.add(Hero21);
        LstHeroes.add(Hero22);
        LstHeroes.add(Hero23);
        LstHeroes.add(Hero24);
        LstHeroes.add(Hero25);
        LstHeroes.add(Hero26);
        LstHeroes.add(Hero27);
        LstHeroes.add(Hero28);
        LstHeroes.add(Hero29);
        LstHeroes.add(Hero30);




        for (Hero unHero : LstHeroes) {
            System.err.println("Nom: "+unHero.GetNom());
            System.err.println("Description: "+unHero.GetDescription());
            System.err.println("Image: "+unHero.GetImage());
            System.err.println("Faction: "+unHero.GetFaction().GetNom()+"\n");
        }

        /*for (EnumMap.Entry<EnumHeroes, Object> entry : LstHeroes.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }*/
    }



}