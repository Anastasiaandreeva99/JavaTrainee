public class MatrixClass {
	public static MyPair startCell = new MyPair(0, 0);
	public static MyPair finalCell = new MyPair(4, 4);

	public static void main(String[] args) {
		int[][] matrix = new int[5][5];
		int n = 5;
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
		getPath(matrix);
	}

	static boolean isValidMove(int i, int j, int[][] matrix) {
		int lenght = matrix.length;
		if ((i < 0 || i >= lenght) || (j < 0 || j >= lenght) || matrix[i][j] == 0) {
			return false;
		}
		return true;
	}

	static boolean hasPath(int[][] matrix, int[][] visited, int i, int j) {
		// if i or j is invalid,or the cell is already visited
		if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || visited[i][j] > -1)
			return false;
		visited[i][j] = 0;
		if (matrix[i][j] == 0) {
			return false;
		}
		if (finalCell.getI() == i && finalCell.getJ() == j || hasPath(matrix, visited, i + 1, j)
				|| hasPath(matrix, visited, i - 1, j) || hasPath(matrix, visited, i, j + 1)
				|| hasPath(matrix, visited, i, j - 1)) {
			visited[i][j] = 1;
		}

		return visited[i][j] == 1;
	}

	public static void getPath(int[][] matrix) {
		int iLenght = matrix.length;
		int jLenght = matrix[0].length;
		int[][] visited = new int[iLenght][jLenght];
		for (int i = 0; i < iLenght; i++) {
			for (int j = 0; j < jLenght; j++) {
				visited[i][j] = -1;
			}
		}
		if (hasPath(matrix, visited, startCell.getI(), startCell.getJ())) {
			for (int i = 0; i < iLenght; i++) {
				for (int j = 0; j < jLenght; j++) {
					if (visited[i][j] == -1) {
						System.out.print("NE ");
					}
					if (visited[i][j] == 0) {
						System.out.print("|| ");
					}
					if (visited[i][j] == 1) {
						System.out.print("DA ");
					}
				}
				System.out.println();
			}
		} else {
			System.out.println("Problem");
		}

	}
}
