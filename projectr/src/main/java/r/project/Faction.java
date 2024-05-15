package r.project;


public abstract class Faction {
    private String nom;
    private String description;
    private String couleur;
    public Faction(String pNom, String pDescrition, String pCouleur)
    {
        nom=pNom;
        description=pDescrition;
        couleur= pCouleur;
    }

    public String GetNom(){
        return nom;
    }
    
    public void SetNom(String pNom){
        this.nom=pNom;
    }

    public String GetDescription(){
        return description;
    }

    public void SetDescription(String pDescription){
        description=pDescription;
    }

    public String GetCouleur(){
        return couleur;
    }

    public void SetCouleur(String pCouleur)
    {
        couleur=pCouleur;
    }
}
