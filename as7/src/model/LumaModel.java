package model;

/**
 * A greyscale luma model which assigns the spots colors to an equation of the color values.
 */
public class LumaModel extends AbstractColorTransformation {

  /**
   * Creates an instance of the luma calculation color values model.
   * Defaults the matrix to the luma matrix.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public LumaModel(int[][][] data) {
    super(data);
    this.matrix = new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
  }

  /**
   * Creates an instance of the luma calculation color values model.
   * Defaults the matrix to the luma matrix.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public LumaModel(int[][][] data, int maxValue) {
    super(data, maxValue);
    this.matrix = new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
  }

  /**
   * Creates an instance of the luma calculation color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param matrix the 2d int array of the transformation. It must be a 3 by 3
   */
  public LumaModel(int[][][] data, double[][] matrix) {
    super(data, matrix);
    if (matrix.length != 3) {
      throw new IllegalArgumentException("Matrix must have 3 by 3 dimensions");
    }
  }

  /**
   * Creates an instance of the luma calculation color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix the 2d int array of the transformation. It must be a 3 by 3
   */
  public LumaModel(int[][][] data, int maxValue, double[][] matrix) {
    super(data, maxValue, matrix);
    if (matrix.length != 3) {
      throw new IllegalArgumentException("Matrix must have 3 by 3 dimensions");
    }
  }

}
