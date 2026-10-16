package entities;

public class Entity {
    private String name;
    private int health;
    private int maxHealth;

    public Entity(String name, int health)
    {
        this.name = name;
        this.health = health;
        this.maxHealth =  health;
    }

    public String getName()
    {
        return this.name;
    }

    public int getHealth()
    {
        return this.health;
    }

    public int getMaxHealth()
    {
        return this.maxHealth;
    }

    public  boolean isAlive()
    {
        if(this.health > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void takeDamage(int amount)
    {
        this.health = this.health - amount;
        if(this.health < 0 )
        {
            this.health = 0;
        }
    }

    public void heal(int amount)
    {
        this.health = this.health + amount;
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
    }
}
