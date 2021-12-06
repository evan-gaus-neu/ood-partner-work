package model;

/**
 * A class representing any sort of flipping modification.
 */
public abstract class AbstractFlippingModel extends AbstractImageProcessingModel {

  /**
   * Creates an instance of this abstract flipping model that represents flipping models.
   * @param data 3d int array of the image's pixels.
   */
  public AbstractFlippingModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of this abstract flipping model that represents flipping models.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public AbstractFlippingModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Transforms the original image by grabbing the flipped pixel.
   */
  @Override
  public void transform() {
    int[][][] flipped = new int[data.length][data[0].length][data[0][0].length];

    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        for (int k = 0; k < data[i][j].length; k++) {
          flipped[i][j][k] = getFlippedPixel(i, j, k);
        }
      }
    }

    this.data = flipped;
  }

  /**
   * Gets the corresponding flipped pixel given the current pixel's location.
   * @param i height of the pixel.
   * @param j width of the pixel.
   * @param k color spot of the pixel.
   * @return an int representing the flipped color pixel.
   */
  protected abstract int getFlippedPixel(int i, int j, int k);
}
