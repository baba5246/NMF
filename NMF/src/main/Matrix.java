package main;

/**
 * An instance of this class represents a dense matrix of floats in column major
 * order. This matrix is backed by a 1-D array of floats. Column major storage is
 * used for compatability with native BLAS libraries.
 *
 * @author Brian K. Vogel (brian@brianvogel.com)
 */
public class Matrix {

    /**
     * Create a new <i>rows</i> by <i>cols</i> matrix, initialized
     * to all zeros.
     *
     * @param rows
     * @param cols
     */
    public Matrix(int rows, int cols) {

        height = rows;
        width = cols;
        values = new float[rows*cols];

    }

    // Public members

    // The number of rows in this matrix.
    public int height;

    // The number of columns in this matrix.
    public int width;

    // The backing array for this matrix. The components of the matrix are stored in this
    // array in column-major ordering.
    public float[] values;

    /**
     * Place <i>val</i> into the element at row = <i>row</i> and column = <i>column</i>.
     *
     * @param row The row index to set.
     * @param column The column index to set.
     * @param val The value to set.
     */
    public void set(int row, int column, float val) {
        values[row + height*column] = val;
    }


    /**
     * Get the element at the specified row and column.
     *
     * @param row
     * @param column
     * @return
     */
    public float get(int row, int column) {
        return values[row + height*column];
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                out += get(i,j) + "   ";
            }
            out += "\n";
        }
        return out;
    }

}

