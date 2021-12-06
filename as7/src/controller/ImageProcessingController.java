package controller;

import java.io.IOException;

/**
 * A controller interface that helps run the program to process images.
 */
public interface ImageProcessingController {

  /**
   * A function that grabs user inputs and does the corresponding operations.
   * @throws IOException if the transmission of the message fails.
   */
  void runImageProcessing() throws IOException;
}
