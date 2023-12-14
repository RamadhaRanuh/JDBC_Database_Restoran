package restaurant_AOL;

public class Order {
    private int id;
    private String customerName;
    private String menuName;
    private int quantity;
    private int totalPrice;

    public Order(int id, String customerName, String menuName, int quantity, int totalPrice) {
        this.setId(id);
        this.setCustomerName(customerName);
        this.setMenuName(menuName);
        this.setQuantity(quantity);
        this.setTotalPrice(totalPrice);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

  
}
