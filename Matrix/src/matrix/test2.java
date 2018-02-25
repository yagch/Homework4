package matrix;
import java.util.*;
import java.lang.*;
import java.io.*;

/* The test code of partII*/
public class test2 {
	public static void main(String[] arg) throws FileNotFoundException {
		/*Read the data from memplus and preprocess*/
		Scanner input = new Scanner (new File("/Users/chenhaohan/Desktop/ece4960/hw/memplus.txt"));;
		for(int i = 0; i < 5; i++) {
			input.next();
			}
		int rank = input.nextInt();
		input.nextInt();
		int count = input.nextInt();
		int size = 0;
		double[] in = new double[rank * rank];
		while(count > 0) {
			int i = input.nextInt();
			int j = input.nextInt();
			double m = input.nextDouble();
			if(m != 0) {
				in[(i - 1) * (j - 1)] = m;
				size++;
			}
			count--;
		}
		
		SparseMatrix matrix = new SparseMatrix(in, rank, size);
		double memory1 = Runtime.getRuntime().totalMemory();
		/* (1) Test permute*/
		double time11 = System.currentTimeMillis();
		System.out.println(matrix.permute(1, 3));
		System.out.println(matrix.permute(1, 5));
		System.out.println(matrix.permute(10, 3000));
		System.out.println(matrix.permute(5000, 10000));
		System.out.println(matrix.permute(6, 15000));
		double time12 = System.currentTimeMillis(); 
		System.out.println("The cpu time of part(1) is" + (time12 - time11));
		
		/* (2) Test scale and permute*/
		double time21 = System.currentTimeMillis();
		System.out.println(matrix.scaling(2, 4, 3.0));
		System.out.println(matrix.permute(2, 5));
		System.out.println(matrix.scaling(5, 4, -3.0));
		double time22 = System.currentTimeMillis();
		System.out.println("The cpu time of part(2) is" + (time22 - time21));
		
		/* (3) Test product*/
		double time31 = System.currentTimeMillis();
		double[] x = new double[rank];
		double[] b = new double[rank];
		for(int i = 0; i < rank; i++) {
			x[i] = 1.0;
		}
        System.out.println(matrix.productAx(x, b));
        double time32 = System.currentTimeMillis();
        System.out.println("The cpu time of part(3) is" + (time32 - time31));
        
        /* (4) Test the product result*/
        double time41 = System.currentTimeMillis();
        double suma = 0;
        double sumb = 0;
        for(int i = 0; i < size; i++) {
        	    suma += matrix.value[i];
        }
        for(int i = 0; i < rank; i++) {
        	    sumb += b[i];
        }
        System.out.println(Math.abs(suma - sumb));
        double time42 = System.currentTimeMillis();
        System.out.println("The cpu time of part(4) is" + (time42 - time41));
        double memory2 = Runtime.getRuntime().totalMemory();
        System.out.println("The memory usage of partII is" + (memory2 - memory1));
	}
			 
		 
}