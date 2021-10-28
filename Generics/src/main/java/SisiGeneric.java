import java.util.List;

public class SisiGeneric<T> {
	private T variable;

	public T getVariable() {
		return variable;
	}

	public boolean compareClasses(T variable) {
		return this.variable.getClass().equals(variable.getClass());
	}

	public static <Param extends Comparable<Param>> int compare(Param el1, Param el2) {
		return el1.compareTo(el2);
	}

	public void printToString(T variable) {
		System.out.println(this.variable.toString() + variable.toString());
	}

	public static <Param> void printList(List<Param> elements) {

		elements.stream().forEach(System.out::println);
	}

	@Override
	public String toString() {
		return super.toString() + "- sisi's override";
	}

	@Override
	public int hashCode() {
		return 31 * 17 + variable.hashCode();

	}
}
