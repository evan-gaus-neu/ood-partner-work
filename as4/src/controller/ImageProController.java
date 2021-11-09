package controller;

/**
 * Interface to represent a controller for the image
 * processing program, which handles all inputs and
 * utilizes a model and view to run and visualize
 * the program.
 */
public interface ImageProController {

  /**
   * Runs the image processing program.
   * @throws IllegalStateException if the controller can't read input or output
   */
  void run() throws IllegalStateException;
  // Test Comment
  // tTest 2
}




