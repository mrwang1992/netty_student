package wang.huaiting.mastering_concurrency_programming_with_java9.p_2_thread_and_runnable;

import java.util.Date;

public class ParallelRowMain {
    public static void main(String[] args) {
        double matrix1[][] = MatrixGenerator.generate(2000, 2000);
        double matrix2[][] = MatrixGenerator.generate(2000, 2000);

        double resultSerial[][] = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();
        ParallelRowMutiplier.multiply(matrix1, matrix2, resultSerial);


        Date end = new Date();
        System.out.printf("ParallelRowMutiplier : %d%n", end.getTime() - start.getTime());
    }
}
