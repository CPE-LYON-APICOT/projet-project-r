package r.project;

public class Moloch extends CreaBoss{
    public Moloch(int pPv, int pAttaque,String pNom, String pDescription, String pLienImage, Faction pFaction,String pSousNom) {
        super(pPv, pAttaque,pNom, pDescription, pLienImage, pFaction,pSousNom);
        setMolock();
    }

    @Override
    public void competence() {
        System.out.println("double attaque du Seigneur Moloch");
        setAttaque(getAttaque() * 2); 
    }

    public void setMolock(){
        super.setNom("Moloch");
        super.setSousNom("le Seigneur des Flammes Abyssales");
        super.SetDescription("Moloch est une créature gigantesque et démoniaque, dont le corps est constitué de flammes noires qui dansent et tourbillonnent autour de lui. Ses yeux brillent d'une lueur malveillante, et des cornes de métal noir émergent de sa tête. Ses griffes acérées sont capables de déchirer l'acier, et il manipule les flammes à volonté, incinérant tout sur son passage. Il règne en maître sur les enfers ardents, n'émergeant que lorsque le chaos doit être semé dans le monde des vivants.");
    }
    
}
