
# Retro-conception

**Binome 1 : [Languebien Theo]**
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

L'objectif initial du projet de crée un jeux de cartes, avec un systèmes de créateur de deck ou le joueur à le choix de choisir parmis une soisantaine de cartes. Ensuite pouvoir utiliser ses cartes pour combattre un ou plusieur monstre avec des moments contre des boss. Il y a un systemes de tour ainsi qu'un systemes de mana. Nous prevoyons aussi d'avoir un systeme de héro à attribuer à un deck pour pouvoir lui ajouter des bonus.

[Décrivez ici l'objectif initial du projet, ne cherchez pas à le minorer si vous n'avez pas tout fini, décrivez ce que vous avez voulu faire]

## Résultat

Les objectifs atteint sont les suivants:
- inventaire et choix des cartes pour la partie
- systemes de combat opérationnel contre les boss
- systemes de mana et de tour 

[Avez vous atteint votre objectif ?]

Nous avons préféré privilégier avec le temps qu'il nous était alloué la création d'un système de combat opérationnel ainsi que la possibilité de créer son propre deck. Nous avons donc délaissé la partie concernant les héros et ajouter certain talents à nos cartes. Nous ne pouvons aussi nous battre que contre des boss alors qu'à l'origine nous contions nous battre contre des monstres.
Cependant il est important de notifier que nous avons codé la partie sur les héros et les monstres. Ainsi que quelques talents. Nous ne les avons juste pas implémenté dans le rendu final.

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
Nous pouvons aussi notifier notre manque de connaissances sur le design Patern singleton, que nous contions incorporer dans le plateau pour ne pas avoir à le recréer à chaque fois. Une partie du code n'est aussi pas forcément très maintenable car manque de temps par rapport.


### Difficultés rencontrées

#### 1. [Génération des cartes sort et monstre]

Nous avions commencé un jeu avec des cartes sort et des cartes monstre qui héritaient de la classe Carte, mais en fonction des cartes, la gestion n'était pas la même en jeu ou lors du passage dans le deck. Nous avons donc laissé les cartes monstres et mis de côté les cartes sort.

#### 2. [utilisation de javafx]

Notre difficulté principale concernait l'utilisation de javafx dont nous n'avions que très peu pratiqué en cours. Après s'être informé par l'intermédiaire de plusieurs guides et vidéos, nous avons pu surmonter nos difficultés. Bien que notre jeu n'est pas particulièrement beau, le javafx est fonctionnel.

#### 3. [Distribution de cartes]

Nous voulions proposer 2 manières de distribuer à nos joueurs pour rajouter de la diversité à notre jeu. Cependant, nous voulions éviter de faire 2 copier collé de code, nous avons donc opté pour un design patern builder pour régler le problème.

#### 4. [organisation]

Le projet était ambitieux donc nous avons commis une erreur d'organisation en voulant faire trop de fonctionnalités avant de nous occuper du corps du jeu. Ce qui nous a fait prendre du retard. Le corps du jeu est fini cependant un certain nombre de fonctionnalités ont était commencée mais pas implémentée.

### *Design Patterns* mis en oeuvre

#### 1. [builder]
Le design pattern présent ici permet de créer le deck et de l'ajouter à une collection de cartes. Cela facilite le passage des cartes du joueur de manière plus simple et évite de s'emmêler les pinceaux. 
[Décrivez ici brièvement le design pattern utilisé et pourquoi]
[Ajouter éventuellement des exemples de code pour montrer l'élégence de votre solution, pour cela vous pouvez écrire en Markdown votre code ainsi :

<pre>
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
</pre>

]

---
# Partie pédagogique


### En quoi la POO vous a été utile

[Par exemple, expliquez que vous auriez éprouvé des difficultés à gérer les collisions si vous n'aviez pas utilisé la POO, ou que vous avez pu facilement ajouter des fonctionnalités à votre jeu grâce à la POO
Minimum 10 lignes (personnalisé en fonction de votre projet)]

La Poo nous a permis de facilement dupliquer des objets Carte, ainsi que définir différents types de cartes directement grâce à l'héritage. Principe que l'on retrouve aussi dans la classe Creature, qui nous a permit de faire une différenciation entre monstre et boss. Grâce à l'héritage cela nous a permis de créer de nouveaux paramètres qu'on les boss mais pas les créatures. Le polymorphisme, unique à la POO nous a aussi permis de surcharger certaines méthodes comme par exemple le boss Neron qui peut surcharger sa méthode compétence (cela permet donc d'avoir des fonctions ayant des fonctionnalités similaires avec des classes n'ayant pas de rapport entre elles).
La Poo me permet facilement de créer de nouveau boss à volonté, juste en créant une nouvelle classe, ce qui est beaucoup plus long et fastidieux sans.
Au niveau des factions ainsi que pour les héros que nous voulions ajouter, cela aurait été plus difficile de les créer comme dit précédemment, mais aussi plus difficile de les rattacher, car les factions ont des attributs. 
Pour finir, sans la programmation orientée objet, le projet aurait été beaucoup plus difficile et nous aurions certainement dû changer pas mal de choses au fur et à mesure du projet.


### Conclusion

[Décrivez ici si vous avez compris un concept particulier que vous n'aviez pas compris en cours, inversement si vous pensiez qu'il était possible de faire qqchose mais que cela ne s'est pas passé comme prévu]

Thomas: personnellement j'ai compris la plupart des design patern vu en cours. Ainsi que les différences entre un stream, une arraylist et un enum. Car premièrement je voulais faire ma liste de héros en enum, mais il s'est avéré que ce n'était pas possible.

Téo: En cours, j'avais cru comprendre les design patterns et je m'étais dit que ça avait l'air d'être simple. Mais au final, ce n'était pas totalement si simple. Et aussi pour JavaFX, au début, je ne comprenais pas où aller pour coder ou faire la partie graphique.