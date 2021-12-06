package model;


/**
 * A model that represents the ability to keep only red or green or blue pixels.
 */
public class RGBModel extends AbstractRGBOnlyModel {
  private int index;

  /**
   * Assigns the image data as well as which color to keep in the field index.
   * @param rgb the enum representing which specific color to keep.
   * @param data data the 3d int array that represents the image's pixel data.
   */
  public RGBModel(RedGreenBlue rgb, int[][][] data) {
    super(data);
    if (rgb == RedGreenBlue.R) {
      index = 0;
    } else if (rgb == RedGreenBlue.G) {
      index = 1;
    } else if (rgb == RedGreenBlue.B) {
      index = 2;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Assigns the image data as well as which color to keep in the field index.
   * @param rgb the enum representing which specific color to keep.
   * @param data data the 3d int array that represents the image's pixel data.
   * @param maxValue which represents the max value a pixel can be.
   */
  public RGBModel(RedGreenBlue rgb, int[][][] data, int maxValue) {
    super(data, maxValue);
    if (rgb == RedGreenBlue.R) {
      index = 0;
    } else if (rgb == RedGreenBlue.G) {
      index = 1;
    } else if (rgb == RedGreenBlue.B) {
      index = 2;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns the value of the color that the user wants to keep.
   * @param rgb the int array of size 3 which represents the rgb values at a spot.
   * @return an int representing the value of the color to keep.
   */
  @Override
  public int convertRGB(int[] rgb, int k) {
    return rgb[this.index];
  }
}
