package lab2;

public class Matrix {

    public double[] data;
    public int rows;
    public int cols;


    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }


    public Matrix(double[][] d) {
        int rows = 0;
        int cols = 0;

        for (double[] r : d) {
            rows++;
            int cols_help = 0;
            for (double c : r) {
                cols_help++;
            }
            if (cols_help > cols) cols = cols_help;
        }

        this.rows = rows;
        this.cols = cols;

        this.data = new double[this.rows * this.cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                this.data[r * this.cols + c] = d[r][c];
            }
        }
    }

    public Matrix(double[] d, int rows) {
        this.rows = rows;
        this.cols = d.length / rows;

        this.data = new double[this.rows * this.cols];

        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = d[i];
        }
    }


    public double[][] asArray() {

        double[][] d = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                d[r][c] = data[r * this.cols + c];
            }
        }
        return d;
    }


    public double get(int r, int c) {
        return data[r * this.cols + c];
    }

    public void set(int r, int c, double value) {
        data[r * this.cols + c] = value;
    }


    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int r = 0; r < this.rows; r++) {
            buf.append("[");
            for (int c = 0; c < this.cols; c++) {
                buf.append(c);
                buf.append(',');
            }
            buf.setLength(buf.length() - 1);
            buf.append("]");
            buf.append(',');
        }
        buf.setLength(buf.length() - 1);
        buf.append("]");
        return buf.toString();
    }

    public void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(
                    String.format("%d x %d matrix can't be reshaped to %d x %d",
                            rows, cols, newRows, newCols));
        rows = newRows;
        cols = newCols;
    }

    public int[] shape() {
        int[] number_of_rows_and_cols = new int[2];
        number_of_rows_and_cols[0] = rows;
        number_of_rows_and_cols[1] = cols;
        return number_of_rows_and_cols;
    }

    public Matrix add(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] + m.data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    public Matrix sub(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] - m.data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    public Matrix mul(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] * m.data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    public Matrix div(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] / m.data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    public Matrix add(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] + w;
            }
        }

        return new Matrix(answer);
    }

    public Matrix sub(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] - w;
            }
        }

        return new Matrix(answer);
    }

    public Matrix mul(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] * w;
            }
        }

        return new Matrix(answer);
    }

    public Matrix div(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] / w;
            }
        }

        return new Matrix(answer);
    }


    public Matrix dot(Matrix m) {
        double[][] answer = new double[this.rows][m.cols];

        if (this.cols != m.rows)
            return new Matrix(asArray());

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                answer[i][j] = 0;
            }
        }

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < m.cols; c++) {
                double tmp = 0;
                for (int k = 0; k < this.cols; k++) {
                    tmp = tmp + this.data[r * this.cols + k] * m.data[k * this.cols + c];
                }
                answer[r][c] = tmp;
            }
        }

        return new Matrix(answer);
    }

    public double frobenius() {
        double answer = 0;

        for (int i = 0; i < this.rows * this.cols; i++) {
            for (int j = 0; j < this.cols; j++) {
                answer += Math.pow(this.data[i], 2);
            }
        }

        answer = Math.sqrt(answer);

        return answer;
    }

    //////// GRUPA C /////////
    public Matrix sumRows() {

        double[][] arr = this.asArray();
        Matrix m = new Matrix(1, this.cols);

        double sum = 0;
        for (int c = 0; c < this.cols; c++) {
            sum = 0;
            for (int r = 0; r < this.rows; r++) {
                sum += arr[r][c];
            }
            m.data[c] = sum;
        }

        return m;
    }
    /////////////////////////
}