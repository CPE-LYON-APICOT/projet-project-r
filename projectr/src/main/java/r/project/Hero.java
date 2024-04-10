package r.project;


public class Hero {
    private String nom;
    private String description;
    private String image;
    private Faction faction;
    public Hero(String pNom,String pDescription,String pImage,@SuppressWarnings("exports") Faction pFaction){
        nom=pNom;
        description=pDescription;
        image=pImage;
        faction=pFaction;

    }
    public String GetNom(){
        return nom;
    }
    public String GetDescription(){
        return description;
    }
    public String GetImage(){
        return image;
    }
    @SuppressWarnings("exports")
    public Faction GetFaction(){
        return faction;
    }

    public String afficher() {
        String details = "Nom: " + this.GetNom() + "\n" +
        "Description: " + this.GetDescription() + "\n" +
        "Faction:" + this.GetFaction()+ "\n";
    return details;
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