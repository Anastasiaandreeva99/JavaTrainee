import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrixClass {
	public static MyPair startCell = new MyPair(0, 0, null);
	public static MyPair finalCell = new MyPair(4, 4, null);
	public static int iLenght=5;
	public static int jLenght=5;

	public static void main(String[] args) {
		int[][] matrix = new int[5][5];
		makeMatrix(matrix,5);
		printResult(matrix);
		System.out.println("-------------------------");
		int[][] copyMatrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
		if (hasPath(copyMatrix)) {
			updateMatrix(matrix);
			printResult(matrix);
		}
		else
		{
			System.out.println("no path");
		}
	}

	private static void printResult(int[][] matrix) {
		for (int i = 0; i < iLenght; i++) {
			for (int j = 0; j < jLenght; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}

	private static void updateMatrix(int[][] matrix) {
		MyPair current = finalCell;
		while (null != current) {
			matrix[current.getI()][current.getJ()] = 8;
			current = current.getParent();
		}
	}

	private static void makeMatrix(int[][] matrix,int n) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (n <= 0 || (startCell.getI() == i && startCell.getJ() == j)
						|| (finalCell.getI() == i && finalCell.getJ() == j)) {
					matrix[i][j] = 1;
				} else {
					int number = (int) Math.round(Math.random());
					if (number == 0)
						n--;
					matrix[i][j] = number;
				}
			}
		}
	}

	static boolean hasPath(int[][] matrix) {
		Queue<MyPair> queue = new LinkedList<MyPair>();
		queue.add(startCell);

		while (queue.size() != 0) {
			MyPair current = queue.peek();
			queue.remove();
			int currentI = current.getI();
			int currentJ = current.getJ();
			if (currentI == finalCell.getI() && currentJ == finalCell.getJ()) {
				finalCell.setParent(current.getParent());
				return true;
			}
			if (currentI >= 0 && currentI < iLenght && currentJ >= 0 && currentJ < jLenght
					&& matrix[currentI][currentJ] != 0) {

				matrix[currentI][currentJ] = 0;
				for (int p = -1; p <= 1; p += 2) {
					queue.add(new MyPair(currentI + p, currentJ, current));
					queue.add(new MyPair(currentI, currentJ + p, current));
				}
			}
		}
		return false;
	}

}
