package model;

/**
 * A class representing abstract color transformations, which multiply rgb values by a matrix.
 */
public abstract class AbstractColorTransformation extends AbstractRGBOnlyModel {

  protected double[][] matrix;

  /**
   * Creates an instance of the class which does not set the matrix to anything here.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public AbstractColorTransformation(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of the class which does not set the matrix to anything here.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public AbstractColorTransformation(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Creates an instance of this abstract blur sharpen model that represents blur sharpen models.
   * @param data 3d int array of the image's pixels.
   * @param matrix 2d array representing the color transformation matrix.
   */
  public AbstractColorTransformation(int[][][] data, double[][] matrix) {
    super(data);
    this.matrix = matrix;
  }

  /**
   * Creates an instance of this abstract blur sharpen model that represents blur sharpen models.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix 2d array representing the color transformation matrix.
   */
  public AbstractColorTransformation(int[][][] data, int maxValue, double[][] matrix) {
    super(data, maxValue);
    this.matrix = matrix;
  }

  /**
   * Multiplies the rgb int values with the corresponding row in the matrix.
   * @param rgb the int array of size 3 which represents the rgb values at a spot.
   * @param k The color index that is of interest in this situation.
   * @return an int representing the multiplication transformation at the color spot.
   */
  @Override
  protected int convertRGB(int[] rgb, int k) {
    int sum = (int) (this.matrix[k][0] * rgb[0]
            + this.matrix[k][1] * rgb[1]
            + this.matrix[k][2] * rgb[2]);
    if (sum > this.maxValue) {
      return this.maxValue;
    } else if (sum < 0) {
      return 0;
    }
    return sum;
  }
}
