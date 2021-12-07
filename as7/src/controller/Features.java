package controller;

import java.io.IOException;

import view.JFrameView;

/**
 * Represents all the Features our GUI should be able to do.
 */
public interface Features {

  /**
   * Sends these features to the view.
   *
   * @param v view to be sent to
   */
  void setView(JFrameView v);

  /**
   * Brighten an image.
   *
   * @param factor factor to be brightened by
   * @throws IOException If there is no image loaded or invalid factor.
   */
  void brighten(String factor) throws IOException;

  /**
   * Darken an image.
   *
   * @param factor factor to be darkened by
   * @throws IOException If there is no image loaded or an invalid factor.
   */
  void darken(String factor) throws IOException;

  /**
   * Mosaic an image.
   *
   * @param numSeeds the number of seeds for the mosaic
   * @throws IOException If there is no image loaded or an invalid number of seeds.
   */
  void mosaic(String numSeeds) throws IOException;

  /**
   * Load an image.
   *
   * @param path Source path
   */
  void load(String path);

  /**
   * Save an image.
   *
   * @param path Source path user would like to save as, including extension.
   * @throws IOException If there is no image loaded.
   */
  void save(String path) throws IOException;

  /**
   * Blur an image.
   *
   * @throws IOException If there is no image loaded.
   */
  void blur() throws IOException;

  /**
   * Sharpen an image.
   *
   * @throws IOException If there is no image loaded.
   */
  void sharpen() throws IOException;

  /**
   * Make every component like the red one.
   *
   * @throws IOException If there is no image loaded.
   */
  void redComponent() throws IOException;

  /**
   * Make every component like the green one.
   *
   * @throws IOException If there is no image loaded.
   */
  void greenComponent() throws IOException;

  /**
   * Make every component like the blue one.
   *
   * @throws IOException If there is no image loaded.
   */
  void blueComponent() throws IOException;

  /**
   * Greyscale using value.
   *
   * @throws IOException If there is no image loaded.
   */
  void valueComponent() throws IOException;

  /**
   * Greyscale using luma.
   *
   * @throws IOException If there is no image loaded.
   */
  void lumaComponent() throws IOException;

  /**
   * Greyscale using intensity.
   *
   * @throws IOException If there is no image loaded.
   */
  void intensityComponent() throws IOException;

  /**
   * Flip the image horizontally.
   *
   * @throws IOException If there is no image loaded.
   */
  void horizontalFlip() throws IOException;

  /**
   * Flip the image vertically.
   *
   * @throws IOException If there is no image loaded.
   */
  void verticalFlip() throws IOException;

  /**
   * Give the image a sepia shade.
   *
   * @throws IOException If there is no image loaded.
   */
  void sepia() throws IOException;
}
