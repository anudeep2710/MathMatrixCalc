import javax.swing.JTextField;

public class UserMatrixFactory {
    // Method to create a matrix from user input
    public Matrix createMatrixFromUserInput(JTextField[][] matrixFields) {
        // Get the dimensions of the matrix from the number of rows and columns of the matrixFields array
        int rows = matrixFields.length;
        int cols = matrixFields[0].length;

        // Create a new matrix with the given dimensions
        Matrix matrix = new Matrix(rows, cols);

        // Extract the elements from the text fields
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String text = matrixFields[i][j].getText();
                double element = Double.parseDouble(text);
                matrix.setElement(i, j, element);
            }
        }

        return matrix;
    }
}
