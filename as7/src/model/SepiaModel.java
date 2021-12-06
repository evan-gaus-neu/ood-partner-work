package model;

/**
 * A sepia model which assigns the spots colors to an equation of the color values.
 */
public class SepiaModel extends AbstractColorTransformation {

  /**
   * Creates an instance of the sepia calculation color values model.
   * Defaults the matrix to the sepia matrix.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public SepiaModel(int[][][] data) {
    super(data);
    this.matrix = new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
  }

  /**
   * Creates an instance of the sepia calculation color values model.
   * Defaults the matrix to the sepia matrix.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public SepiaModel(int[][][] data, int maxValue) {
    super(data, maxValue);
    this.matrix = new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
  }

  /**
   * Creates an instance of the sepia calculation color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param matrix the 2d int array of the transformation. It must be a 3 by 3
   */
  public SepiaModel(int[][][] data, double[][] matrix) throws IllegalArgumentException {
    super(data, matrix);
    if (matrix.length != 3) {
      throw new IllegalArgumentException("Matrix must have 3 by 3 dimensions");
    }
  }

  /**
   * Creates an instance of the sepia calculation color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix the 2d int array of the transformation. It must be a 3 by 3
   */
  public SepiaModel(int[][][] data, int maxValue, double[][] matrix)
          throws IllegalArgumentException {
    super(data, maxValue, matrix);
    if (matrix.length % 2 != 1) {
      throw new IllegalArgumentException("Matrix must have 3 by 3 dimensions");
    }
  }

}
