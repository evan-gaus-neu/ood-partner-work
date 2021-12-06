package model;

/**
 * A greyscale model which assigns the spots colors to the max of the color values.
 */
public class ValueModel extends AbstractRGBOnlyModel {

  /**
   * Creates an instance of the max color values model.
   * @param data the 3d int array that represents the image's pixel data.
   */
  public ValueModel(int[][][] data) {
    super(data);
  }

  /**
   * Creates an instance of the max color values model.
   * @param data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public ValueModel(int[][][] data, int maxValue) {
    super(data, maxValue);
  }

  /**
   * Finds the max of a spots color values and grabs that max value.
   * @param rgb the int array of size 3 which represents the rgb values at a spot.
   * @return the int representing the max color value at the spot.
   */
  @Override
  public int convertRGB(int[] rgb, int k) {
    return Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
  }
}
