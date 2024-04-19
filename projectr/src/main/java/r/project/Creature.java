package r.project;

public abstract class Creature {
    private int pv;
    private int attaque;
    private String description;
    private String lienImage;
    private Faction faction;
    private String nom;
    public Creature(int pPv, int pAttaque,String pNom,String pDescription, String pLienImage,Faction pFaction){
        pv=pPv;
        attaque=pAttaque;
        description=pDescription;
        lienImage=pLienImage;
        faction=pFaction;
        nom=pNom;
    }

    public int getPv(){
        return pv;
    }
    public void setPv(int pPv)
    {
        pv=pPv;
    }
    public int getAttaque(){
        return attaque;
    }
    public void setAttaque(int pAttaque){
        attaque=pAttaque;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String pNom){
        nom=pNom;
    }
    public String getDescription(){
        return description;
    }
    public void SetDescription(String pDescription){
        description=pDescription;
    }
    @SuppressWarnings("exports")
    public Faction getFaction(){
        return faction;
    }

    public void subirDamage(int damage){
        int nvPv=this.pv-damage;
        pv=nvPv;
    }
}
