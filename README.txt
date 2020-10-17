Enigma Game - README

This project was realized by Elior Papiernik & Tom Winandy in the context of the course CS-107/

To launch the game, you must run the Play file (package: ch.epfl.cs107.play)
You can change the game to be started by replacing “Enigma ()” with “Demo1 ()” or “Demo2” in the Play file. (You will have to do the imports accordingly).

Orders :
- directional arrows to move
- L to pick up / put down an object
- L to interact with a character
- D to use an item (potion) (must be pointed by the cursor)
- TAB to change the location cursor in the inventory
- SPACE to receive a potion
- P to pause the game (and P to resume the game)

Details:
- if the spear is pointed by the cursor, you interact with the two cells in front of you (by pressing L)
- gold bars activate the pressure plates
- the inventory cursor is only displayed if there are at least two objects
- the inventory can only contain 10 items (you can change it in the EnigmePlayer constructor, but you must put an integer value between 1 and 20, otherwise the inventory size will be 10)
- using a potion will increase your speed for a short time (+ a nice graphic effect)
- When you interact with the sorcerer (the “old man” in Enigme0 and Enigme1), he will give you a potion. You can pick up a new one as many times as you want, but only if you used the previous potion (putting it down won't work, you really have to use it)

Riddles:
- Door 1 leads to an empty area
- Door 2 leads to an area that contains an apple that you can pick up
- Door 3 leads to a riddle.
   - You can open the door by collecting the key
   - You can remove the middle stone by activating the seven buttons (by passing over it)
   - You can remove the left stone by activating the torch and / or the levers to form the number 5 in binary
   - You can remove the right stone by stepping on the pressure plate
- Door 4 sends you to a test area, which will be reset each time you enter it - (Avoid using what you collect there for door 5)
- Door 5 leads to the main puzzle
Its goal is to go through the door of the castle in front of which you appear. We must therefore recover the key:
   - For the first step, you must collect an acceleration potion from the sorcerer and use it to pass the rock before it reappears
   - For the second step, you need to activate the four levers and then activate the pressure plate
   - For the third step, you must activate the two torches
   - For the fourth step, you must collect the ingot and the stick. This will allow you to recover the second ingot, blocked by stones, in step 3
   - For the last step, you must
       - Collect the ingot and place it on the pressure plate which makes the rock of step four disappear
       - Collect the two ingots used for step four
       - Collect the third ingot you just placed
       - Place the three ingots on the three pressure plates distributed in the labyrinth (which will remove one of the stones blocking access to the key)
       - Retrieve the key using the stick, facing the remaining stone
   - Having retrieved the key, the door will be opened, and you can go through it
- The door that you will pass by having solved the riddle of door 5 will send you to another riddle, where you will only have to light the four torches to unlock the door, which will give you access to the LevelSelector again.
