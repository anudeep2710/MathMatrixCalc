import java.util.Arrays;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    // Constructor
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }


    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }


    // Getter methods
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Method to set a specific element in the matrix
    public void setElement(int row, int col, double value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            data[row][col] = value;
        } else {
            throw new IllegalArgumentException("Invalid row or column index");
        }
    }

    // Method to get a specific element from the matrix
    public double getElement(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return data[row][col];
        } else {
            throw new IllegalArgumentException("Invalid row or column index");
        }
    }

    // Method to add another matrix to this matrix
    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.data[i][j] + other.getElement(i, j));
            }
        }
        return result;
    }

    // Method to display the matrix as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : data) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for subtraction");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.data[i][j] - other.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix scalarMultiply(double scalar) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.data[i][j] * scalar);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Number of columns of first matrix must be equal to the number of rows of the second matrix for multiplication");
        }
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * other.data[k][j]; // Corrected multiplication here
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }
    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result.setElement(i, j, this.data[j][i]);
            }
        }
        return result;
    }


// Additional methods for advanced operations like decomposition, solving systems of equations, etc.
public double calculateDeterminant() {
    if (rows != cols) {
        throw new IllegalStateException("Cannot calculate determinant for non-square matrix");
    }
    if (rows == 1) {
        return data[0][0]; // Base case: determinant of 1x1 matrix
    }

    double determinant = 0;
    for (int j = 0; j < cols; j++) {
        determinant += data[0][j] * cofactor(0, j);
    }

    return determinant;
}

    private double cofactor(int row, int col) {
        int sign = (row + col) % 2 == 0 ? 1 : -1;
        return sign * minor(row, col).calculateDeterminant();
    }

    private Matrix minor(int row, int col) {
        double[][] minorData = new double[rows - 1][cols - 1];
        for (int i = 0, r = 0; i < rows; i++) {
            if (i == row) continue;
            for (int j = 0, c = 0; j < cols; j++) {
                if (j == col) continue;
                minorData[r][c] = data[i][j];
                c++;
            }
            r++;
        }
        return new Matrix(minorData);
    }




    public Matrix calculateInverse() {
        if (rows != cols) {
            throw new IllegalStateException("Cannot calculate inverse for non-square matrix");
        }

        // Create an augmented matrix [A | I] where I is the identity matrix
        Matrix augmentedMatrix = new Matrix(rows, cols * 2);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                augmentedMatrix.setElement(i, j, data[i][j]);
            }
            augmentedMatrix.setElement(i, i + cols, 1); // Set identity matrix elements
        }

        // Apply Gaussian elimination to get the reduced row echelon form
        Matrix reducedRowEchelonForm = augmentedMatrix.reduceToRREF();

        // Extract the inverse matrix from the reduced row echelon form
        Matrix inverseMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                inverseMatrix.setElement(i, j, reducedRowEchelonForm.getElement(i, j + cols));
            }
        }

        return inverseMatrix;
    }

    // Helper method to convert the matrix to upper triangular form
    private Matrix upperTriangular() {
        Matrix upperTriangle = new Matrix(rows, cols);
        upperTriangle.data = Arrays.copyOf(data, data.length); // Create a copy of the matrix
        for (int k = 0; k < rows - 1; k++) {
            for (int i = k + 1; i < rows; i++) {
                double factor = upperTriangle.data[i][k] / upperTriangle.data[k][k];
                for (int j = k; j < cols; j++) {
                    upperTriangle.data[i][j] -= factor * upperTriangle.data[k][j];
                }
            }
        }
        return upperTriangle;
    }
    public Matrix copy() {
        Matrix copiedMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copiedMatrix.setElement(i, j, this.data[i][j]);
            }
        }
        return copiedMatrix;
    }

    // Helper method to reduce the matrix to reduced row echelon form (RREF)
    private Matrix reduceToRREF() {
        Matrix rrefMatrix = this.copy(); // Create a copy of the matrix to avoid modifying the original
        int lead = 0;
        int rowCount = rrefMatrix.rows;
        int colCount = rrefMatrix.cols;

        for (int r = 0; r < rowCount; r++) {
            if (lead >= colCount) {
                break;
            }
            int i = r;
            while (rrefMatrix.data[i][lead] == 0) {
                i++;
                if (i == rowCount) {
                    i = r;
                    lead++;
                    if (lead == colCount) {
                        break;
                    }
                }
            }
            double[] temp = rrefMatrix.data[i];
            rrefMatrix.data[i] = rrefMatrix.data[r];
            rrefMatrix.data[r] = temp;
            double div = rrefMatrix.data[r][lead];
            for (int j = 0; j < colCount; j++) {
                rrefMatrix.data[r][j] /= div;
            }
            for (int k = 0; k < rowCount; k++) {
                if (k != r) {
                    double mult = rrefMatrix.data[k][lead];
                    for (int j = 0; j < colCount; j++) {
                        rrefMatrix.data[k][j] -= mult * rrefMatrix.data[r][j];
                    }
                }
            }
            lead++;
        }
        return rrefMatrix;
    }



    // Other methods for matrix operations (e.g., multiplication, transpose, determinant, etc.) can be added here
}
