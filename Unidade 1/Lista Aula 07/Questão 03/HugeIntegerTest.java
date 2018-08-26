public class HugeIntegerTest {

	public static void main(String[] args) {
		HugeInteger num1 = new HugeInteger("123456789");
		HugeInteger num2 = new HugeInteger("123456789");
		num1.print();
		num1.addHugeIntegers(num2);
		num1.print();
	}
}