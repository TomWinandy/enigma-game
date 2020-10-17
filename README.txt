	Mini-projet 2 de programmation - README

Bienvenue dans notre projet.
On n'a pas fait un gameplay extraordinaire, mais on espère que vous apprécierez les extensions que l'on a implémentées ;-)

Pour lancer le jeu, il faut exécuter le fichier Play (package : ch.epfl.cs107.play)
Vous pouvez changer le jeu à lancer en remplaçant “Enigme()” par “Demo1()” ou “Demo2” dans le fichier Play. (Il faudra faire les imports en conséquence).

Commandes :
- flèches directionnelles pour se déplacer
- L pour ramasser / poser un objet
- L pour interagir avec un personnage
- D pour utiliser un objet (potion) (doit être pointé par le curseur)
- TAB pour changer le curseur d'emplacement dans l'inventaire
- ESPACE pour recevoir une potion
- P pour mettre le jeu en pause (et P pour reprendre le jeu)


Précisions :
- si la lance est pointée par le curseur, vous interagissez avec les deux cellules situées devant vous (en appuyant sur L)
- les lingots d'or activent les plaques de pression
- le curseur de l'inventaire ne s'affiche que s'il y a au moins deux objets
- l'inventaire ne peut contenir que 10 éléments (vous pouvez le changer dans le constructeur du EnigmePlayer, mais vous devez mettre une valeur entière entre 1 et 20, sans quoi la taille de l'inventaire sera de 10)
- utiliser une potion augmentera votre vitesse pendant un court instant (+ un effet graphique sympa)
- Lorsque vous interagirez avec le sorcier (le “old man” dans Enigme0 et Enigme1), il vous donnera une potion. Vous pourrez en récupérer une nouvelle autant de fois que vous le voudrez, mais seulement si vous avez utilisé la potion précédente (la poser ne fonctionnera pas, il faut vraiment l'utiliser)


Enigmes :

- La porte 1 envoie vers une aire vide

- La porte 2 envoie vers une aire qui contient une pomme que vous pouvez ramasser

- La porte 3 envoie vers une énigme.
   - Vous pouvez ouvrir la porte en récupérant la clef
   - Vous pouvez enlever la pierre du milieu en activant les sept boutons (en passant dessus)
   - Vous pouvez enlever la pierre de gauche en activant la torche et/ou les leviers pour former le nombre 5 en binaire
   - Vous pouvez enlever la pierre de droite en marchant sur la plaque de pression

- La porte 4 vous envoie vers une zone de test, qui sera réinitialisée à chaque fois que vous entrerez dedans - (Évitez d'utiliser ce que vous y récolterez pour la porte 5)

- La porte 5 envoie vers l'énigme principale
Son but est de passer par la porte du château devant lequel vous apparaissez. Il faut donc récupérer la clef :
   - Pour la première étape, vous devez récupérer une potion d'accélération auprès du sorcier et l'utiliser pour passer la roche avant sa réapparition
   - Pour la deuxième étape, vous devez activer les quatre leviers puis activer la plaque de pression
   - Pour la troisième étape, vous devez activer les deux torches
   - Pour la quatrième étape, vous devez récupérer le lingot et le bâton. Ce dernier vous permettra de récupérer le deuxième lingot, bloqué par des pierres, dans l'étape 3
   - Pour la dernière étape, vous devez
       - Récupérer le lingot et le poser sur la plaque de pression qui fait disparaître le rocher de l'étape quatre
       - Récupérer les deux lingots utilisés pour l'étape quatre
       - Récupérer le troisième lingot que vous venez de poser
       - Déposer les trois lingots sur les trois plaques de pression réparties dans le labyrinthe (ce qui fera disparaître une des pierres qui bloquent l'accès à la clef)
       - Récupérer la clef à l'aide du bâton, en étant face à la pierre restante
   - Ayant récupéré la clef, la porte sera ouverte, et vous pourrez la franchir

- La porte que vous franchirez en ayant résolu l'énigme de la porte 5 vous enverra vers une autre énigme, où vous n'aurez qu'à allumer les quatre torches pour déverrouiller la porte, qui vous donnera accès à nouveau au LevelSelector.
