package test;

public class regexText {
	public static void main(String[] args) {
		String str = "4501A";
		String regex = "[0-9]+";
		System.out.println(str.matches(regex));
	}
}
