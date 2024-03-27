package r.project;
import java.util.EnumMap;
import java.util.Map;

public class Hero {
    private String nom;
    private String description;
    private String image;
    private Faction faction;
    public Hero(String pNom,String pDescription,String pImage,Faction pFaction){
        nom=pNom;
        description=pDescription;
        image=pImage;
        faction=pFaction;
    }
    @SuppressWarnings("exports")
    public void AjouterInMap(EnumMap<EnumHeroes, Object> pLstHeroes){
        pLstHeroes.put(EnumHeroes.NOM, this.nom);
        pLstHeroes.put(EnumHeroes.DESCRIPTION, this.description);
        pLstHeroes.put(EnumHeroes.IMAGE, this.image);
        pLstHeroes.put(EnumHeroes.FACTION, this.faction);
    }
}

@IaPourFaction(aPourFaction = FOrdre.class)
 class HeroOrdre extends Hero{

    public HeroOrdre(String pNom, String pDescription,String pImage, Faction pFaction) {
        super(pNom, pDescription,pImage, pFaction);
    }
 }

 @IaPourFaction(aPourFaction = FChaos.class)
 class HeroChaos extends Hero{

    public HeroChaos(String pNom, String pDescription,String pImage, Faction pFaction) {
        super(pNom, pDescription,pImage, pFaction);
    }
 }

 @IaPourFaction(aPourFaction = FNeant.class)
 class HeroNeant extends Hero{

    public HeroNeant(String pNom, String pDescription,String pImage, Faction pFaction) {
        super(pNom, pDescription,pImage, pFaction);
    }
 }