package lab2;

public class Matrix {

    double[] data;
    int rows;
    int cols;


    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }


    Matrix(double[][] d) {
        int iterator = 0;
        int iterator1 = 0;
        int iterator2 = 0;

        for (double[] r : d) {
            iterator++;
            for (double c : r) {
                iterator1++;
            }
            if (iterator1 > iterator2) iterator2 = iterator1;
        }

        this.rows = iterator;
        this.cols = iterator2;


        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                data[r * this.cols + c] = d[r][c];
            }
        }
    }

    Matrix(double[] d, int rows) {
        this.rows = rows;
        this.cols = d.length / rows;

        for (int i = 0; i < d.length; i++) {
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


    double get(int r, int c) {
        return data[r * this.cols + c];
    }

    void set(int r, int c, double value) {
        data[r * this.cols + c] = value;
    }


    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int r = 0; r < this.rows; r++) {
            buf.append("[");
            for (int c = 0; c < this.cols; c++) {
                buf.append(c);
            }
            buf.append("]");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(
                    String.format("%d x %d matrix can't be reshaped to %d x %d",
                            rows, cols, newRows, newCols));
        rows = newRows;
        cols = newCols;
    }

    int[] shape() {
        int[] number_of_rows_and_cols = new int[2];
        number_of_rows_and_cols[0] = rows;
        number_of_rows_and_cols[1] = cols;
        return number_of_rows_and_cols;
    }

    Matrix add(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = m.data[r * this.cols + c] + data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    Matrix sub(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.rows != this.rows || m.cols != this.cols)
            return new Matrix(asArray());

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = m.data[r * this.cols + c] - data[r * this.cols + c];
            }
        }

        return new Matrix(answer);
    }

    Matrix mul(Matrix m) {
        double[][] answer = new double[rows][cols];

        if (m.cols != this.rows)
            return new Matrix(asArray());


        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
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

    Matrix add(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] + w;
            }
        }

        return new Matrix(answer);
    }

    Matrix sub(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] - w;
            }
        }

        return new Matrix(answer);
    }

    Matrix mul(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] * w;
            }
        }

        return new Matrix(answer);
    }

    Matrix div(double w) {
        double[][] answer = new double[rows][cols];

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                answer[r][c] = this.data[r * this.cols + c] / w;
            }
        }

        return new Matrix(answer);
    }

    Matrix dot(Matrix m) {
        double[] answer = new double[rows * cols];

        if (m.cols != this.rows)
            return new Matrix(asArray());


        for (int i = 0; i < this.rows * this.cols; i++) {
            for (int j = 0; j < this.cols; j++) {
                answer[i] = this.data[i] + m.data[i];
            }
        }

        return new Matrix(answer, m.rows);
    }

    double frobenius() {
        double answer = 0;

        for (int i = 0; i < this.rows * this.cols; i++) {
            for (int j = 0; j < this.cols; j++) {
                answer += Math.pow(this.data[i], 2);
            }
        }

        answer = Math.sqrt(answer);

        return answer;
    }
}
