public class MatrixTransformer {
    private Matrix matrix;

    // Constructor
    public MatrixTransformer(Matrix matrix) {
        this.matrix = matrix;
    }

    // Method to multiply the matrix by a scalar
    public Matrix multiplyByScalar(double scalar) {
        return MatrixOperations.multiplyByScalar(matrix, scalar);
    }

    // Method to add another matrix to the current matrix
    public Matrix add(Matrix other) {
        return MatrixOperations.addMatrices(matrix, other);
    }

    // Method to multiply the current matrix by another matrix
    public Matrix multiply(Matrix other) {
        return MatrixOperations.multiplyMatrices(matrix, other);
    }

    // Method to transpose the current matrix
    public Matrix transpose() {
        return matrix.transpose();
    }
}
