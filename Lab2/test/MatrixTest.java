import lab2.Matrix;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MatrixTest {

//    @Before
//    public void setUp() {
//        double[][] d = {{1,2,0},{4,5,6}};
//    }

    @Test
    public void testMatrix() throws Exception {
        Matrix m = new Matrix(3, 4);
        assertEquals(m.rows, 3);
        assertEquals(m.cols, 4);
    }

    @Test
    public void testdoubleMatrix() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        assertEquals(2, m.rows);
        assertEquals(3, m.cols);
        assertEquals(1.0, m.data[0], 0);
        assertEquals(0.0, m.data[2], 0);
        assertEquals(6.0, m.data[5], 0);
        assertEquals(m.asArray(), d);
    }

    @Test
    public void asArray() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        for (int r = 0; r < m.rows; r++) {
            for (int c = 0; c < m.cols; c++) {
                assertEquals(d[r][c], m.data[m.cols * r + c], 0);
            }
        }
    }

    @Test
    public void get() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        assertEquals(2, m.get(0, 1), 0);
    }

    @Test
    public void set() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        m.set(1, 1, 5.5);
        assertEquals(5.5, m.get(1, 1), 0);
    }

    @Test
    public void testToString() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        String s = m.toString();
        int num_of_brackets = 0, num_of_commons = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') num_of_brackets++;
            if (s.charAt(i) == ',') num_of_commons++;
        }
        assertEquals(3, num_of_brackets);
        assertEquals(5, num_of_commons);
    }

    @Test(expected = RuntimeException.class)
    public void reshape() throws Exception {
        double[][] d = {{1, 2, 0}, {4, 5, 6}};
        Matrix m = new Matrix(d);
        m.reshape(2, 4);
    }

    @Test
    public void add() throws Exception {
        double[][] d = {{0, 0, 0}, {1, 1, 1}};
        double[][] extra = {{1, 1, 1}, {1, 1, 1}};
        Matrix m = new Matrix(d);
        Matrix result = m.add(new Matrix(extra));

        int sum = 0;
        for (int i = 0; i < result.data.length; i++) {
            sum += result.data[i];
        }
        assertEquals(9, sum);
    }

    @Test
    public void sub() throws Exception {
        double[][] d = {{0, 0, 0}, {1, 1, 1}};
        double[][] extra = {{1, 1, 1}, {1, 1, 1}};
        Matrix m = new Matrix(d);
        Matrix result = m.sub(new Matrix(extra));

        int sum = 0;
        for (int i = 0; i < result.data.length; i++) {
            sum += result.data[i];
        }
        assertEquals("Should return -3 when those arrays are given.", -3, sum);
    }

    @Test
    public void mul() throws Exception {
        double[][] d = {{0, 0, 0}, {1, 1, 1}};
        double[][] extra = {{1, 1, 1}, {1, 1, 1}};
        Matrix m = new Matrix(d);
        Matrix result = m.mul(new Matrix(extra));

        int sum = 0;
        for (int i = 0; i < result.data.length; i++) {
            sum += result.data[i];
        }
        assertEquals("Should return 3 when those arrays are given.", 3, sum);
    }

    @Test
    public void div() throws Exception {
        double[][] d = {{0, 0, 0}, {1, 1, 1}};
        double[][] extra = {{1, 1, 1}, {1, 1, 1}};
        Matrix m = new Matrix(d);
        Matrix result = m.div(new Matrix(extra));

        int sum = 0;
        for (int i = 0; i < result.data.length; i++) {
            sum += result.data[i];
        }
        assertEquals("Should return 3 when those arrays are given.", 3, sum);
    }

    @Test
    public void dot() throws Exception {
        double[][] d = {{0, 0}, {1, 1}, {2, 3}};
        double[][] extra = {{0, 1, 2}, {0, 1, 2}};
        Matrix m = new Matrix(d);
        Matrix result = m.dot(new Matrix(extra));

        int sum = 0;
        for (int i = 0; i < result.data.length; i++) {
            sum += result.data[i];
        }
        assertEquals("Should return 21 when those arrays are given.", 21, sum);
    }


    ///// GRUPA C /////
    @Test
    public void testSumRows() {
        //  1
        Random random = new Random();
        int rows = random.nextInt(100) + 1;
        int cols = random.nextInt(100) + 1;

        //  2
        double delta = random.nextInt(1) + 0.1;


        //  3
        Matrix matrix = new Matrix(rows, cols);
        int i = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix.set(r, c, delta * i);
                i++;
            }
        }

        //  4
        Matrix sumR = matrix.sumRows();

        //  5

        assertEquals(matrix.shape(), sumR.shape());

        //  6
        double[] suma = new double[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                suma[c] += matrix.get(r, c);
            }
        }
        for (int c = 0; c < cols; c++) {
            assertEquals(sumR.get(0, c), suma[c], 0.1);
        }

    }
    ///////////////////////////////////
}