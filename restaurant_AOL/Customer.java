package restaurant_AOL;

public class Customer 
{
    private String name;
    private int age;
    private String address;
    private int money;

    public Customer(String name, int age, String address, int money) 
    {
        this.name = name;
        this.age = age;
        this.address = address;
        this.money = money;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public int getMoney() 
    {
        return money;
    }

    public void setMoney(int money) 
    {
        this.money = money;
    }

    public void updateProfile(int age, String address, int money)
    {
        this.age = age;
        this.address = address;
        this.money = money;
    }

    public boolean makePayment(int amount) 
    {
        if (money >= amount) 
        {
            money -= amount;
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public void topUp(int amount) 
    {
        this.money += amount;
    }
}