package matrix;


/* The test code of partI*/
public class test {
	public static void main(String[] arg) {
		/* Initialize the matrixes*/
		double[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[] rowPtr = {0, 3, 6, 9, 10, 12};
		int[] colInd = {0, 1, 4, 0 ,1, 2, 1, 2, 4, 3, 0, 4};
		double[][] fullMat = {{1, 2, 0, 0, 3}, {4, 5, 6, 0, 0}, {0, 7, 8, 0, 9}, {0, 0, 0, 10, 0}, {11, 0, 0, 0, 12}};
		double[] x = {5, 4, 3, 2, 1};
		
		/* Test permute*/
		SparseMatrix A1 = new SparseMatrix(value, rowPtr, colInd, 5);
		FullMatrix B1 = new FullMatrix(fullMat);
		int a11 = A1.permute(1, 3);
		int a12 = A1.permute(1, 5);
		B1.permute(1, 3);
		B1.permute(1, 5);
		if(a11 == 0 && a12 == 0) {
			System.out.println("First test is");
			System.out.println(testMat(A1.matrixA(), B1.matrixB));
		}
		
		/* Test Scaling */
		SparseMatrix A2 = new SparseMatrix(value, rowPtr, colInd, 5);
		FullMatrix B2 = new FullMatrix(fullMat);
		int a2 = A2.scaling(1, 4, 3.0);
		B2.scaling(1, 4, 3.0);
		if(a2 == 0) {
			System.out.println("Second test is");
			System.out.println(testMat(A2.matrixA(), B2.matrixB));
		}
		SparseMatrix A3 = new SparseMatrix(value, rowPtr, colInd, 5);
		FullMatrix B3 = new FullMatrix(fullMat);
		int a3 = A3.scaling(5, 2, -4.4);
		B3.scaling(5, 2, -4.4);
		if(a3 == 0) {
			System.out.println("Third test is");
			System.out.println(testMat(A3.matrixA(), B3.matrixB));
		}
		
		/* Test product*/
		SparseMatrix A4 = new SparseMatrix(value, rowPtr, colInd, 5);
		FullMatrix B4 = new FullMatrix(fullMat);
		double[] b = new double[5];
		int a4 = A4.productAx(x, b);
		if(a4 == 0) {
			System.out.println("Fourth test is");
			System.out.println(testVec(b, B4.productAx(x)));
		}
	}
	
	/* The fuction to test the difference between matrixes*/
	public static double testMat(double[][] A, double[][] B) {
		if(A.length != B.length) {
			System.out.println("Cannot be tested");
			return -1;
		}
		double res = 0;
		int rank = A.length;
		for(int i = 0; i < rank; i++)
			for(int j = 0; j < rank; j++) {
				res += (B[i][j] - A[i][j]) * (B[i][j] - A[i][j]);
			}
		return res;
	}
	
	/* The fuction to test the difference between vectors*/
	public static double testVec(double[] x, double[] y) {
		if(x.length != y.length) {
			System.out.println("Cannot be tested");
			return -1;
		}
		double res = 0;
		int len = x.length;
		for(int i = 0; i < len; i++) {
			res += (x[i] - y[i]) * (x[i] - y[i]);
		}
		return res;
	}
	
	/* Permute vector x*/
	public static int permuteVec(double[] x, int i, int j) {
		double m = x[i - 1];
		x[i - 1] = x[j - 1];
		x[j - 1] = m;
		return 0;
	}
}
