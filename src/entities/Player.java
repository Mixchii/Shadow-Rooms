package entities;

import items.Inventory;
import items.Weapon;
import world.Room;

public class Player extends Entity {
    private Room currentRoom;
    private Inventory inventory;
    private Weapon equippedWeapon;

    public Player(String name, int health, Room startingRoom)
    {
        super(name, health);
        this.currentRoom = startingRoom;
        this.inventory = new Inventory();
        this.equippedWeapon = null;
    }

    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room)
    {
        this.currentRoom = room;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public Weapon getEquippedWeapon()
    {
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(Weapon weapon)
    {
        this.equippedWeapon = weapon;
    }

    public int calAttackPower()
    {
        if(this.equippedWeapon != null)
        {
            return 10 + this.equippedWeapon.getDamage();
        }
        else
        {
            return 10;
        }
        
    }


}
