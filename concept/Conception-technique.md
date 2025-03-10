
# Retro-conception

**Binome 1 : [Languebien Teo]**
**Binome 2 : [Bourcet Thomas]**

Complétez ce document pour décrire votre projet, les difficultés rencontrées, les design patterns mis en oeuvre, les améliorations possibles, et en quoi la POO vous a été utile.

> **Faites le avec sérieux, ce document ainsi que votre code seront évalués.**
Si vous considérez que votre code est quasi-parfait, je vais chercher les erreurs et en trouver, et cela vous pénalisera.
Si vous êtes critique envers vous-même et que vous expliquez correctement vos difficultés, vous serez "à moitié" pardonné.

Ce n'est pas grave de ne pas avoir été au bout de ce que vous vouliez faire, comportez vous comme un ingénieur qui doit rendre des comptes à son client, et qui doit expliquer pourquoi il n'a pas pu tout faire.
Pour rappel le client n'est pas un developpeur (sinon il ne vous aurait pas payé une fortune pour le faire à sa place), vous devez lui expliquer de manière simple et claire les problèmes rencontrés, en vous justifiant 
>Imaginez que vous avez codé Mortal Kombat 
Ne dites pas "je n'ai pas eu le temps de tout faire", mais plutôt "j'ai préféré me concentrer sur la création des spectaculaires "Finish Him" des personnages car c'est un élément essentiel du gameplay, cependant la difficulté dynamique en fonction de la maîtrise du joueur n'a pas pu être implémentée à temps, compte tenu que les critères de maîtrises sont difficilement modélisables, toutefois nous pourrions envisager d'implémenter que plus le combat dure longtemps, plus les coups portés sont puissants, ce qui est rapide à implémenter et lors d'une mise à jour, nous pourrions remplacer ce système par quelque chose de plus élaboré"

Aussi, en entreprise, vous serez confronté à des programmes très mal codés, et vous allez galérer à les comprendre, vous risquez d'essayer de les corriger et tomber dans les mêmes ecueils que les développeurs précédents.
Pour cela, il est courrant de tenir un jour un Document d'Architecture Technique (DAT) qui explique comment fonctionne le programme, et comment le reprendre ainsi qu'un document de réversibilité qui explique comment reprendre le code de quelqu'un d'autre.
(C'est obligatoire pour les marchés publics de prévoir une réversibilité, c'est à dire que le client peut vous dégager et une autre entreprise doit pouvoir reprendre votre code sans difficulté)
Dans ces documents, il ne s'agit pas de cacher la poussière sous le tapis, il faut être honnête et proposer une vision d'ensemble de votre code, et expliquer pourquoi vous avez fait des choix, et pourquoi vous n'avez pas fait d'autres choix, il est souvent question de compromis (on fait un truc pas ouf pour gagner du temps, mais la qualité du code en pâtit, etc.)
> Vous pouvez dire : "Pour la gestion des collisions, nous utilisons une librairie tierce, toutefois celle-ci ne gère que les collisions entre des rectangles, au fur et à mesure des itérations, des ennemis de grande taille et de forme complexe sont apparus, toutefois, nous avons conservé une hitbox rectangulaire, en résulte que le joueur peut être touché alors que visuellement, il n'est pas en contact avec l'ennemi ; nous avions également envisagé de créer plusieurs hitbox de tailles différentes sur un même ennemi afin de mieux coller à la forme de celui-ci, toutefois, les performances du jeu ont étés trop dégradées"



---
# Partie "Client" (pas trop technique) :

## Objectif du projet

L'objectif initial du projet de crée un jeux de cartes, avec un systèmes de créateur de deck ou le joueur à le choix de choisir parmis une soisantaine de cartes. Ensuite pouvoir utiliser ses cartes pour combattre un ou plusieurs monstres avec en plus quelques combats de boss. Il y a un système de tour ainsi qu'un système de mana. Nous prévoyons aussi d'avoir un système de héros à attribuer à un deck pour pouvoir lui ajouter des bonus.

[Décrivez ici l'objectif initial du projet, ne cherchez pas à le minorer si vous n'avez pas tout fini, décrivez ce que vous avez voulu faire]

## Résultat

Les objectifs atteint sont les suivants:
- inventaire et choix des cartes pour la partie
- systemes de combat opérationnel contre les boss
- systemes de mana et de tour
- Mise en place de nos design pattern 

[Avez vous atteint votre objectif ?]

Nous avons préféré privilégier avec le temps qu'il nous était alloué la création d'un système de combat opérationnel ainsi que la possibilité de créer son propre deck. Nous avons donc délaissé la partie concernant les héros et le fait de pouvoir ajouter certains talents à nos cartes. Actuellement, Nous pouvons nous battre que contre des boss alors qu'à l'origine nous contions aussi nous battre contre des monstres.
Cependant, il est important de notifier que nous avons codé la partie sur les héros et les monstres. Ainsi que quelques talents. Nous ne les avons juste pas implémentés dans le rendu final.

### Améliorations possibles

[Décrivez ici les améliorations que vous auriez pu apporter si vous aviez eu plus de temps]

- Implementer, la partie sur les monstres et les héros
- Ajouter des talents à certaines cartes (piocher des cartes quand jouées, régénération, surpuissance etc) pour rajouter de la fluidité dans le gameplay
- Faire un mode histoire ou le joueur naviguerait sur une carte monde en effectuant divers combats contre des monstres et affronterait les 3 boss du jeu 
- Diversifier le panel d'actions des boss
- Potentiellement des cartes de sort
- Rendre le site plus beau

---
# Partie "Développeur" (plus technique) :


### Implémentations remarquables

[Si pendant votre implémentation, vous trouvez que vous pouvez être particulièrment fiers d'une partie de votre code, décrivez là ici ; par exemple si vous avez généré une carte de manière procédurale, ou à l'aide d'un fichier]

### Faiblesses du code

[C'est ici que vous me dites ce que vous savez que vous avez mal fait, expliquez pourquoi vous avez fait ce choix (manque de temps, manque de compétence, trop pénible à faire, etc.)]

Selon nous, nous aurions dû plus séparer notre code car en prenant l'exemple de notre fichier GameController qui fait 800 lignes, on se rend compte que le code est difficilement reprenable par un développeur externe au projet. Nous contions nettoyer le code à la fin du projet mais nous avons préféré nous concentrer sur d'autres fonctionnalités de notre jeu à la place pour rendre le jeu jouable et plaisant. En enlevant quelques bugs par exemple.
Nous aurions aussi dû dissocier la création des objets de notre code. Ce qui aurait rendu le code plus propre.
 Une partie du code n'est aussi pas forcément très maintenable car manque de temps par rapport.


### Difficultés rencontrées

#### 1. [Génération des cartes sort et monstre]

Nous avions commencé un jeu avec des cartes sort et des cartes monstre qui héritaient de la classe Carte, mais en fonction des cartes, la gestion n'était pas la même en jeu ou lors du passage dans le deck. Nous avons donc laissé les cartes monstres et mis de côté les cartes sorts.

#### 2. [utilisation de javafx]

Notre difficulté principale concernait l'utilisation de javafx dont nous n'avions que très peu pratiqué en cours. Après s'être informé par l'intermédiaire de plusieurs guides et vidéos, nous avons pu surmonter nos difficultés. Bien que notre jeu n'est pas particulièrement beau, le javafx est fonctionnel.

#### 3. [Distribution de cartes]

Nous voulions proposer 2 manières de distribuer à nos joueurs pour rajouter de la diversité à notre jeu. Cependant, nous voulions éviter de faire 2 copier collé de code, nous avons donc opté pour un design patern stratégie pour régler le problème. Ce design pattern a été compliqué à mettre en place.

#### 4. [organisation]

Le projet était ambitieux donc nous avons commis une erreur d'organisation en voulant faire trop de fonctionnalités avant de nous occuper du corps du jeu. Ce qui nous a fait prendre du retard. Le corps du jeu est fini cependant un certains nombres de fonctionnalités ont était commencée mais pas implémentée.

### *Design Patterns* mis en oeuvre

#### 1. [builder]
Le design pattern présent ici permet de créer le deck et de l'ajouter à une collection de cartes. Cela facilite le passage des cartes du joueur de manière plus simple et évite de s'emmêler les pinceaux. 


```java
    public class DeckBuilder {
        private Stream<carte> stream;

        public DeckBuilder(ArrayList<carte> cartesInitiales){
            this.stream = cartesInitiales.stream();
        }
        public DeckBuilder filterByFaction(String faction){
            this.stream = this.stream.filter(carte -> true);
            return this;
        }
        public DeckBuilder filterByType(String type){
            this.stream = this.stream.filter(carte -> true);
            return this;
        }

        public Collection<carte> build(){
            // return this.stream.toList();
            return this.stream.collect(Collectors.toList());
            
        }
    }
```

#### 2. [Observer]
Notre observer nous permet de déclancher une muisque de fin lors de l'apparition de la pop up de victoire

Appele de addobserver dans initialize et scan pour attendre la notification

```java
 public void addObserver(IObserver observer) {
        observers.add(observer);
    }
```
Appelle de addObserver

```java
addObserver(new musicVictoire());
```
Envoie de la notification à l'observer

```java
    private void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
```
Appelle lorsque la pop up apparait

```java
    private void afficherPopupVictoire() {
        notifyObservers();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Victoire !");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations, vous avez gagné le combat !");
        alert.showAndWait();
        System.exit(0);
    }
```

lance la musique

```java
    public class musicVictoire implements IObserver{
        @Override
        public void update() {
            try {
                String musicPath = "projectr/src/main/java/r/project/musique/victoryTheme.mp3";
                Media sound = new Media(new File(musicPath).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Une erreur s'est produite lors de la lecture de la musique : ");
            }
        }
    }
```
#### 3. [Singleton]
Ce design pattern est utilisé dans notre cas pour avoir un joueur unique.

Instance statique unique 

```java
    private static player instance;
```

Constructeur privé

```java
    private player(int pPv, Collection<carte> pLstDeck, Hero pHero) {
            this.pv = pPv;
            this.lstDeck = pLstDeck;
            this.hero = pHero;
        }
```

Méthode publique statique pour vérifier l'instance

```java
    public static player getInstance(int pPv, Collection<carte> pLstDeck, Hero pHero) {
            if (instance == null) {
                instance = new player(pPv, pLstDeck, pHero);
            }
            return instance;
        }
```

Appeler l'instance

```java
    player dataObject = player.getInstance(40, new DeckBuilder(DeckPlayer).build(), heroChoisi);
```

#### 4. [Stratégie]
Le design pattern utilisé ici permet de choisir entre deux méthodes de pioche : soit une pioche aléatoire parmi toutes les cartes du paquet, soit la prise de la première carte du paquet.

Voici la méthode pour piocher la première carte du paquet :

```java
 public class PiocheSimple implements TypePioche{

    @Override
    public void piocherCartes(ArrayList<carte> main, ArrayList<carte> paquet) {
        if (main.size() < 5) {
            
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);
            
        }
    }
}


```

Voici la méthode pour la pioche aléatoire :

```java
   public class PiocheAléatoire implements TypePioche {


    @Override
    public void piocherCartes(ArrayList<carte> main, ArrayList<carte> paquet) {
        Collections.shuffle(paquet);
        if (main.size()< 5) {
         
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);

        
        }
    }
}
```

Voici l'interface qui permet de crée les methodes de pioche :

```java
public interface TypePioche {

    public void piocherCartes(ArrayList<carte> main, ArrayList<carte> paquet);
}

```
---


# Partie pédagogique


### En quoi la POO vous a été utile

[Par exemple, expliquez que vous auriez éprouvé des difficultés à gérer les collisions si vous n'aviez pas utilisé la POO, ou que vous avez pu facilement ajouter des fonctionnalités à votre jeu grâce à la POO
Minimum 10 lignes (personnalisé en fonction de votre projet)]

La Poo nous a permis de facilement dupliquer des objets Carte, ainsi que définir différents types de cartes directement grâce à l'héritage. Principe que l'on retrouve aussi dans la classe Creature, qui nous a permit de faire une différenciation entre monstre et boss. Grâce à l'héritage cela nous a permis de créer de nouveaux paramètres qu'on les boss mais pas les créatures. Le polymorphisme, unique à la POO nous a aussi permis de surcharger certaines méthodes comme par exemple le boss Neron qui peut surcharger sa méthode compétence (cela permet donc d'avoir des fonctions ayant des fonctionnalités similaires avec des classes n'ayant pas de rapport entre elles).
La Poo me permet facilement de créer de nouveau boss à volonté, juste en créant une nouvelle classe, ce qui est beaucoup plus long et fastidieux sans.
Au niveau des factions ainsi que pour les héros que nous voulions ajouter, cela aurait été plus difficile de les créer comme dit précédemment, mais aussi plus difficile de les rattacher, car les factions ont des attributs. 
Pour finir, sans la programmation orientée objet, le projet aurait été beaucoup plus laborieux et nous aurions certainement dû changer pas mal de choses au fur et à mesure du projet.


### Conclusion

[Décrivez ici si vous avez compris un concept particulier que vous n'aviez pas compris en cours, inversement si vous pensiez qu'il était possible de faire qqchose mais que cela ne s'est pas passé comme prévu]

Thomas: personnellement j'ai compris la plupart des design patern vu en cours. Ainsi que les différences entre un stream, une arraylist et un enum. Car premièrement je voulais faire ma liste de héros en enum, mais il s'est avéré que ce n'était pas possible.

Téo: En cours, j'avais cru comprendre les design patterns et je m'étais dit que ça avait l'air d'être simple. Mais au final, ce n'était pas totalement si simple. Et aussi pour JavaFX, au début, je ne comprenais pas où aller pour coder ou faire la partie graphique.