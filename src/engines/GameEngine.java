package engines;

import entities.Player;
import items.Item;
import items.Weapon;
import world.Direction;
import world.Room;
import java.util.Scanner;



public class GameEngine {
    private Player player;
    private boolean isRunning;
    private Scanner scanner;

    public GameEngine()
    {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        initializeWorld();
    }

    private void initializeWorld()
    {
        //fix
        Room entrance = new Room("Entrance Hall", "An old stone hallway filled with moss lit with torches.");
        Room library = new Room("Library", "Filled with dusty shelves full of rotting books.");
        Room armory = new Room("Armory", "A stack of weapons line the walls.");
        Room courtyard = new Room("Courtyard", "An open sky above filled with gray fog.");

        //exits
        entrance.setExits(library, null, armory, null);
        library.setExits(null, entrance, courtyard, null);
        armory.setExits(courtyard, null, null, entrance);
        courtyard.setExits(null, armory, null, library);


        Item key = new Item("Key", "A rusty iron key.");
        Weapon dagger = new Weapon("Dagger", "A sharp steel dagger", 15);

        entrance.addItem(key);
        armory.addItem(dagger);



       this.player = new Player("Explorer", 100, entrance);

    }

    public void start()
    {
        System.out.println("Welcome to the Rooms... the Shadow Rooms");
        System.out.println("Type 'help' for a list of available commands. \n");

        while(this.isRunning)
        {
            Room current = this.player.getCurrentRoom();
            current.printRoomDetails();

            System.out.print("\n> ");
            String input = this.scanner.nextLine().trim();

            processCommand(input);
            System.out.println("-------------------------------"); 
        }

        System.out.println("Game Over. Thank you for playing Shadow Rooms!");
    }

    private void processCommand(String input)
    {
        if (input.equalsIgnoreCase("quit"))
        {
            this.isRunning = false;
        }
        else if (input.equalsIgnoreCase("help"))
        {
            printHelp();
        }
        else if(input.equalsIgnoreCase("north") || input.equalsIgnoreCase("n"))
        {
            move(Direction.NORTH);
        }
        else if(input.equalsIgnoreCase("south") || input.equalsIgnoreCase("s"))
        {
            move(Direction.SOUTH);
        }
        else if(input.equalsIgnoreCase("east") || input.equalsIgnoreCase("e"))
        {
            move(Direction.EAST);
        }
        else if(input.equalsIgnoreCase("west") || input.equalsIgnoreCase("w"))
        {
            move(Direction.WEST);
        }
        else if(input.equalsIgnoreCase("inventory") || input.equalsIgnoreCase("inv"))
        {
            this.player.getInventory().printInventory();

        }
        else if(input.startsWith("take "))
        {
            String itemName = input.substring(5).trim();
            takeItem(itemName);
        }
        else if(input.startsWith("equip "))
        {
            String weaponName = input.substring(6).trim();
            equipWeapon(weaponName);
        }
        else
        {
            System.out.println("Unknown Command. Type 'help' to see a list of available commands");
        }

    }


    private void move(Direction direction)
    {
        Room currentRoom = this.player.getCurrentRoom();
        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom != null)
        {
            this.player.setCurrentRoom(nextRoom);
            System.out.println("You walked " + direction.name().toLowerCase() + ".");

        }
        else
        {
            System.out.println("You can't go that way bro");
        }

    }
    private void takeItem(String itemName)
    {
        Room currentRoom = this.player.getCurrentRoom();
        Item foundItem = currentRoom.findItemByName(itemName);

        if(foundItem != null)
        {
            currentRoom.removeItem(foundItem);
            this.player.getInventory().addItem(foundItem);
            System.out.println("You picked up the " + foundItem.getName()+ ".");
        }
        else
        {
            System.out.println("There is no " + itemName + " in this room.");
        }
    }

    private void equipWeapon(String weaponName)
    {
        Item item = this.player.getInventory().findItemByName(weaponName);

        if(item != null)
        {
            if (item instanceof Weapon)
            {
                Weapon weapon = (Weapon) item;
                this.player.setEquippedWeapon(weapon);
                System.out.println("You equipped the " + weapon.getName() + ".");
            }
            else
            {
                System.out.println("That's not a weapon");
            }
        }
        else
        {
            System.out.println("You don't have that in your inventory");
        }
    }

    private void printHelp()
    {
        System.out.println("Commands:");
        System.out.println("- north / south / east / west (or n / s / e / w)");
        System.out.println("- take [item name]");
        System.out.println("- equip [weapon name]");
        System.out.println("- inventory (or inv)");
        System.out.println("- help");
        System.out.println("- quit");
        
    }
}
