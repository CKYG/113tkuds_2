import java.util.Arrays;

public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };

        int[][] sum = add(A, B);
        int[][] product = multiply(A, B);
        int[][] transposed = transpose(A);
        int[] minMax = minMax(A);

        System.out.println("加法結果：" + Arrays.deepToString(sum));
        System.out.println("乘法結果：" + Arrays.deepToString(product));
        System.out.println("轉置結果：" + Arrays.deepToString(transposed));
        System.out.println("最大值：" + minMax[1] + "，最小值：" + minMax[0]);
    }

    static int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    static int[][] transpose(int[][] a) {
        int[][] result = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                result[j][i] = a[i][j];
        return result;
    }

    static int[] minMax(int[][] a) {
        int min = a[0][0], max = a[0][0];
        for (int[] row : a)
            for (int val : row) {
                if (val < min) min = val;
                if (val > max) max = val;
            }
        return new int[]{min, max};
    }
}
