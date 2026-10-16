package world;

import items.Item;
import java.util.ArrayList;
import java.util.List;

public class Room
{
    private String name;
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private List<Item> itemsInRoom;

    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.northExit = null;
        this.southExit = null;
        this.eastExit = null;
        this.westExit = null;
        this.itemsInRoom = new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setExits(Room north, Room south, Room east, Room west)
    {
        this.northExit = north;
        this.southExit = south;
        this.eastExit = east;
        this.westExit = west; 
    }

    public Room getExit(Direction direction)
    {
        if(direction == Direction.NORTH)
        {
            return this.northExit;
        }

        else if(direction == Direction.SOUTH)
        {
            return this.southExit;
        }

        else if(direction == Direction.EAST)
        {
            return this.eastExit;
        }

        else if(direction == Direction.WEST)
        {
            return this.westExit;
        }
        else 
        {
            return null;
        }
    }

    public void addItem(Item item)
    {
        this.itemsInRoom.add(item);
    } 

    public boolean removeItem(Item item)
    {
        return this.itemsInRoom.remove(item);
    }
    
    public Item findItemByName(String itemName)
    {
        int index = 0;
        while (index < itemsInRoom.size())
        {
            Item current = itemsInRoom.get(index);
            if(current.getName().equalsIgnoreCase(itemName))
            {
                return current;
            }
            index++;
        }
        return null;
        
    }

    public void printRoomDetails()
    {
        System.out.println("=== " + name + " ===");
        System.out.println(this.description);

        System.out.print("Exits available: ");
        if(this.northExit != null)
        {
            System.out.print("NORTH ");
        }
        if(this.southExit != null)
        {
            System.out.print("SOUTH ");
        }
        if(this.eastExit != null)
        {
            System.out.print("EAST ");
        }
        if(this.westExit != null)
        {
            System.out.print("WEST ");
        }
        System.out.println();

        if(this.itemsInRoom.isEmpty() == false)
        {
            System.out.println("Items in this room: ");
            int index = 0;
            while(index < this.itemsInRoom.size())
            {
                System.out.println("- " + this.itemsInRoom.get(index).getName());
                index++;
            }
        }
    }

    
}