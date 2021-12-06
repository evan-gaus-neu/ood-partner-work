package model;

/**
 * A general image processing model interface that is the start of a model implementation.
 */
public interface ImageProcessingModel {

  /**
   * Represents enums that can be used to represent either greyscale rgb.
   */
  enum RedGreenBlue { R, G, B }

  /**
   * Function that physically allows the model to make the image transformation the model specifies.
   */
  public void transform();

  /**
   * Grabs the data of the image pixels.
   * @return a 3d int array representing the images pixels.
   */
  public int[][][] getData();

  /**
   * Grabs the max pixel value that the image can have.
   * @return an int representing the max pixel that an image can have.
   */
  public int getMaxValue();

  public int[][] getHistograms();
}
