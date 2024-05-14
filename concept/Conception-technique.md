
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

L'objectif initial du projet de crée un jeux de cartes, avec un systèmes d'inventaire pour pouvoir choisir parmis une soisantaine de cartes. Ensuite pouvoir utiliser ses cartes pour combattre un ou plusieur monstre avec des moments contre des boss. Il y a un systemes de tour ainsi qu'un systemes de mana.

[Décrivez ici l'objectif initial du projet, ne cherchez pas à le minorer si vous n'avez pas tout fini, décrivez ce que vous avez voulu faire]

## Résultat

Les objectifs atteint sont les suivants:
- inventaire et choix des cartes pour la partie
- systemes de combat contre les boss
- systemes de mana et de tour 

[Avez vous atteint votre objectif ?]

### Améliorations possibles

Il est possible d'ajouter les combats de monstres entre deux boss et de gagner qu'à la fin d'avoir tué trois boss. Il faudrait ajouter des cartes de sorts avec un système de héros qui changerait un peu certaines cartes en fonction de leur faction.
[Décrivez ici les améliorations que vous auriez pu apporter si vous aviez eu plus de temps]

---
# Partie "Développeur" (plus technique) :


### Implémentations remarquables

[Si pendant votre implémentation, vous trouvez que vous pouvez être particulièrment fiers d'une partie de votre code, décrivez là ici ; par exemple si vous avez généré une carte de manière procédurale, ou à l'aide d'un fichier]

### Faiblesses du code

Aux niveau de la création des objets qui sont les cartes et la création des héros, il faudrait peut etre les faire la création de celle ci dans un fichier a part. 
Une partie du code n'est pas forcement très maintenable car manque de temps part rapport au ambition du projet.  
[C'est ici que vous me dites ce que vous savez que vous avez mal fait, expliquez pourquoi vous avez fait ce choix (manque de temps, manque de compétence, trop pénible à faire, etc.)]

### Difficultés rencontrées

#### 1. [Génération dynamique des ... pour ...]

[Expliquez ici la difficulté rencontrée et comment vous l'avez contournée]

#### 2. [Gestion des collisions]

[Exemple : Nous n'avons pas réussi à gérer les collisions, PARCE QUE, mes objets n'héritaient pas d'une classe commune, car nos objets héirtaient de ... et nos personnages de ...]


### *Design Patterns* mis en oeuvre

#### 1. [builder]
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

### Conclusion

[Décrivez ici si vous avez compris un concept particulier que vous n'aviez pas compris en cours, inversement si vous pensiez qu'il était possible de faire qqchose mais que cela ne s'est pas passé comme prévu]