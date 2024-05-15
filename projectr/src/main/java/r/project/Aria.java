package r.project;

public class Aria extends CreaBoss{
    public Aria(int pPv, int pAttaque,String pNom, String pDescription, String pLienImage, Faction pFaction,String pSousNom) {
        super(pPv, pAttaque,pNom, pDescription, pLienImage, pFaction,pSousNom);
        setAria();
    }

    @Override
    public void competence() {
        System.out.println("double attaque de la reine Aria");
        setAttaque(getAttaque() * 2); 
    }

    public void competence2(){
        System.out.println("La Reine Aria se soigne de 20 pv");
        setPv(getPv()+20);
    }
    


    public void setAria(){
        super.setNom("Aria");
        super.setSousNom("la Reine des Tempêtes Éternelles");
        super.SetDescription("Aria est une entité céleste majestueuse, dont la présence est précédée par des bourrasques de vent et des éclairs déchirant le ciel. Sa peau est constellée de motifs lumineux, rappelant les étoiles, et ses yeux sont aussi éblouissants que le soleil. Elle contrôle les éléments déchaînés, invoquant des tornades, des éclairs et des tempêtes d'une puissance inégalée. Aria se considère comme la gardienne des cieux, défendant farouchement leur intégrité contre toute intrusion.");
    }
    
}
