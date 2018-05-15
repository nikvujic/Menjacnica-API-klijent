package klase;

public class Zemlja {
	private String id;
	private String name;
	private String alpha3;
	private String currencyId;
	private String currencyName;
	private String currencySymbol;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha2) {
		this.alpha3 = alpha2;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Override
	public String toString() {
		if (currencySymbol == null) {
			return "id: " + id + "\nname: " + name + "\nalpha3: " + alpha3 + "\ncurrencyId: " + currencyId
					+ "\ncurrencyName: " + currencyName + "\ncurrencySymbol: " + "<none provided>";
		} else
			return "id: " + id + "\nname: " + name + "\nalpha3: " + alpha3 + "\ncurrencyId: " + currencyId
					+ "\ncurrencyName: " + currencyName + "\ncurrencySymbol: " + currencySymbol;
	}
}