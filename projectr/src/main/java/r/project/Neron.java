package r.project;

public class Neron extends CreaBoss{
    public Neron(int pPv, int pAttaque,String pNom, String pDescription, String pLienImage, Faction pFaction,String pSousNom) {
        super(pPv, pAttaque,pNom, pDescription, pLienImage, pFaction,pSousNom);
        setMolock();
    }

    @Override
    public void competence() {
        System.out.println("double attaque de l'emprereur Neron");
        setAttaque(getAttaque() * 2); 
    }

    public void setMolock(){
        super.setNom("Néron");
        super.setSousNom("l'empereur des Ombres Éternelles");
        super.SetDescription("Néron est une silhouette sinistre et mystérieuse, enveloppée dans un manteau d'ombres qui se tordent et se déplacent autour de lui. Son visage est dissimulé derrière un masque de ténèbres, et son regard transperce l'âme de ceux qui osent croiser sa route. Il manie les pouvoirs de l'obscurité avec une habileté déconcertante, se fondant dans les ombres pour attaquer ses ennemis par surprise. Néron est le souverain des royaumes obscurs, cherchant à étendre son influence sur toutes les terres plongées dans l'ombre.");
    }
    
}
