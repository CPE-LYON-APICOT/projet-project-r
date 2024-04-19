package r.project;

public class RoiOrq extends CreaBoss {
    public RoiOrq(int pPv, int pAttaque,String pNom, String pDescription, String pLienImage, Faction pFaction,String pSousNom) {
        super(pPv, pAttaque,pNom, pDescription, pLienImage, pFaction,pSousNom);
    }

    @Override
    public void competence() {
        System.out.println("double attaque du Roi Orq");
        setAttaque(getAttaque() * 2); 
    }

    public void debuff(){
        DebuffObservable.getInstance().debuff();
    }

}
