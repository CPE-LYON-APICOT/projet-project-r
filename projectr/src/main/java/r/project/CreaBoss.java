package r.project;

public abstract class CreaBoss extends Creature{
    private String sousNom;
    public CreaBoss(int pPv, int pAttaque,String pNom, String pDescription, String pLienImage, Faction pFaction,String pSousNom) {
        super(pPv, pAttaque,pNom, pDescription, pLienImage, pFaction);
        sousNom=pSousNom;
    }

    public void competence() {
        System.out.println("Compétence générique du boss");
    }

    public String getSousNom(){
        return sousNom;
    }
    public void setSousNom(String pSousName){
        sousNom=pSousName;
    }
}
