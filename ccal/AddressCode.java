public class AddressCode {
	String ac1 = "";
	String ac2 = "";
	String ac3 = "";
	String ac4 = "";

	public AddressCode() {}

	public AddressCode(String x) {
		ac1 = x;
	}

	public AddressCode(String x, String y) {
		ac1 = x;
		ac2 = y;
	}

	public AddressCode(String x, String y, String z) {
		ac1 = x;
		ac2 = y;
		ac3 = z;
	}

	public AddressCode(String x, String y, String z, String w) {
		ac1 = x;
		ac2 = y;
		ac3 = z;
		ac4 = w;
	}

	public void printAC() {
			System.out.println("|" + ac1 + " " + ac2 + " " + ac3 + " " + ac4 + "|");
    }
}
