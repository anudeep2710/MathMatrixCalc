import java.util.ArrayList;
import java.util.List;

public class MatrixOperations {
    private static final double EPSILON = 1e-10; // Small value for floating-point comparison

    // Method to calculate the determinant of a matrix
    public static double calculateDeterminant(Matrix matrix) {
        if (matrix.getRows() != matrix.getCols()) {
            throw new IllegalArgumentException("Determinant can only be calculated for square matrices");
        }
        return matrix.calculateDeterminant();
    }

    // Method to calculate the inverse of a matrix
    public static Matrix calculateInverse(Matrix matrix) {
        return matrix.calculateInverse();
    }

    // Method to decompose the eigenvalues of a matrix
    public static List<Double> decomposeEigenvalues(Matrix matrix) {
        // Implementation of eigenvalue decomposition (e.g., using libraries like Apache Commons Math)
        return new ArrayList<>(); // Placeholder
    }

    // Method to multiply a matrix by a scalar
    public static Matrix multiplyByScalar(Matrix matrix, double scalar) {
        return matrix.scalarMultiply(scalar);
    }

    // Method to add two matrices
    public static Matrix addMatrices(Matrix matrix1, Matrix matrix2) {
        return matrix1.add(matrix2);
    }

    // Method to subtract two matrices
    public static Matrix subtractMatrices(Matrix matrix1, Matrix matrix2) {
        return matrix1.subtract(matrix2);
    }

    // Method to multiply two matrices
    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        return matrix1.multiply(matrix2);
    }


}
