package model;

/**
 * An abstract class that represents blurring or sharpening an image through filtering.
 */
public abstract class AbstractBlurSharpenModel extends AbstractImageProcessingModel {

  protected double[][] matrix;

  /**
   * Creates an instance of the model using only a 3d int array of pixels.
   * @param data a 3d int array representing height, width, color of an image.
   */
  public AbstractBlurSharpenModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of this abstract blur sharpen model that represents blur sharpen models.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public AbstractBlurSharpenModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Creates an instance of this abstract blur sharpen model that represents blur sharpen models.
   * @param data 3d int array of the image's pixels.
   * @param matrix the 2d int array representing the filtering matrix for the transformation.
   */
  public AbstractBlurSharpenModel(int[][][] data, double[][] matrix) {
    super(data);
    this.matrix = matrix;
  }

  /**
   * Creates an instance of this abstract blur sharpen model that represents blur sharpen models.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   * @param matrix the 2d int array representing the filtering matrix for the transformation.
   */
  public AbstractBlurSharpenModel(int[][][] data, int maxValue, double[][] matrix) {
    super(data, maxValue);
    this.matrix = matrix;
  }

  /**
   * Transform function that takes the 3d int array and does the multiplying filter on it.
   */
  @Override
  public void transform() {
    int[][][] filtered = new int[data.length][data[0].length][data[0][0].length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        for (int k = 0; k < 3; k++) {
          filtered[i][j][k] = getFiltered(i, j, k);
        }
        filtered[i][j][3] = this.data[i][j][3];
      }
    }
    this.data = filtered;
  }

  /**
   * Calculates the desired value of filtered multiplication at that pixel location and color.
   * @param i integer representing the height of desired pixel calculation
   * @param j integer representing the width of desired pixel calculation
   * @param k integer representing the color of desired pixel calculation
   * @return an int with that location's pixel data filtered by matrix.
   */
  protected int getFiltered(int i, int j, int k) {
    int middle = this.matrix.length / 2;
    double sum = 0;
    for (int x = 0; x < this.matrix.length; x++) {
      for (int y = 0; y < this.matrix[x].length; y++) {
        if (i - middle + x >= 0 && j - middle + y >= 0 &&
                i - middle + x < this.data.length && j - middle + y < this.data[i].length) {
          sum += this.matrix[x][y] * (double) this.data[i - middle + x][j - middle + y][k];
        }
      }
    }
    if (sum < 0) {
      return 0;
    } else if (sum > maxValue) {
      return maxValue;
    }
    return (int) sum;
  }
}
