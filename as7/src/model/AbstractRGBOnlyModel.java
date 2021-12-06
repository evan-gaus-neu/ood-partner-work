package model;

/**
 * An abstract model which focuses on assigning all color values at a location to one value.
 */
public abstract class AbstractRGBOnlyModel extends AbstractImageProcessingModel {

  /**
   * Creates a general instance of the greyscale model.
   * @param data @param data the 3d int array that represents the image's pixel data.
   */
  public AbstractRGBOnlyModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates a general instance of the greyscale model.
   * @param data @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public AbstractRGBOnlyModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Transforms the image by assigning all color values at a spot to be one "colorValue" int.
   */
  @Override
  public void transform() {
    int[][][] copy = new int[this.data.length][this.data[0].length][this.data[0][0].length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        for (int k = 0; k < 3; k++) {
          copy[i][j][k] = convertRGB(data[i][j], k);
        }
        copy[i][j][3] = data[i][j][3];
      }
    }

    this.data = copy;
  }

  /**
   * Helps find the same shared value to assign to the color values.
   * @param rgb the int array of size 3 which represents the rgb values at a spot.
   * @return the int that will be assigned to all three of the color spots.
   */
  protected abstract int convertRGB(int[] rgb, int k);
}
