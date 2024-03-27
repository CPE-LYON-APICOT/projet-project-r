package r.project;

public abstract class carte {
    // Properties
    private String nom;
    private int cout;
    private String description;
    private int attaque;
    private String lienImage;

    
    // Constructor
    public carte(String name, int cost, String desc, int attack, String lienImage) {
        this.nom = name;
        this.cout = cost;
        this.description = desc;
        this.attaque = attack;
        this.lienImage = lienImage;
    }
    
    // Getter and Setter methods
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getAttaque() {
        return attaque;
    }
    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public String getLienImage() {
        return lienImage;
    }

    public void setLienImage(String lienImage) {
        this.lienImage = lienImage;
    }
    
    // Common methods
    public abstract void jouer();
    public abstract String afficher();
}



