
public class Main {

	public static void main(String[] args) throws InterruptedException {
	ThreadCoordinator myCoordinator = new ThreadCoordinator(1, 8,8000);
	myCoordinator.Calculate();

	}

}
