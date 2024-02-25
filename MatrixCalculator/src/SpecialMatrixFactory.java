public class SpecialMatrixFactory {
    // Method to create special matrices based on type
    public Matrix createSpecialMatrix(int rows, int cols, String type) {
        switch (type) {
            case "identity":
                return createIdentityMatrix(rows, cols);
            case "zero":
                return createZeroMatrix(rows, cols);
            case "random":
                return createRandomMatrix(rows, cols);
            default:
                throw new IllegalArgumentException("Invalid matrix type");
        }
    }

    // Method to create an identity matrix
    private Matrix createIdentityMatrix(int rows, int cols) {
        Matrix identityMatrix = new Matrix(rows, cols);
        for (int i = 0; i < Math.min(rows, cols); i++) {
            identityMatrix.setElement(i, i, 1);
        }
        return identityMatrix;
    }

    // Method to create a zero matrix
    private Matrix createZeroMatrix(int rows, int cols) {
        return new Matrix(rows, cols); // Matrix constructor initializes elements to zero
    }

    // Method to create a random matrix
    private Matrix createRandomMatrix(int rows, int cols) {
        Matrix randomMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                randomMatrix.setElement(i, j, Math.random());
            }
        }
        return randomMatrix;
    }
}
