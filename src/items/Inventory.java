package items; 

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    
    }

    public void addItem(Item item)
    {
        this.items.add(item);
    }

    public boolean removeItem(Item item)
    {
        return this.items.remove(item);
    }

    public Item findItemByName(String itemName)
    {
        int index = 0;
        while(index < this.items.size())
        {
            Item current = this.items.get(index);

            if(current.getName().equalsIgnoreCase(itemName))
            {
                return current;
            }
            index = index + 1;
        }
        return null;

    }

    public List<Item> getItems()
    {
        return this.items;
    }

    public void printInventory()
    {
        if(this.items.isEmpty())
        {
            System.out.println("Your inventory is currently enpty.");
        }
        else
        {
            System.out.println("----- Your Inventory -----");
            int index = 0;
            while(index < items.size())
            {
                Item current = this.items.get(index);
                System.out.println("- " + current.getName() + ": " + current.getDescription());
                index++;
            }       
        }
    }
}
