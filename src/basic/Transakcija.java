package basic;

public class Transakcija {
	private int id;
	private String description;
	private String date;
	private String account;
	private double amount;
	private String currency;
	private String category;
	private String type;
	
	public Transakcija(int id, String description, String date, String account, 
			double amount, String currency, String category, String type) {
		this.id = id;
		this.description = description;
		this.date = date;
		this.account = account;
		this.amount = amount;
		this.currency = currency;
		this.category = category;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %.2f %s %s %s%n", this.description, 
				this.date, this.account, this.amount, this.currency, 
				this.category, this.type);
	}
}
