package view;

import java.io.IOException;

/**
 * This interface represents the expected operations of a
 * view used to visualize the image processing program.
 */
public interface ImageProView {

  /**
   * Renders the specific given string message to the appendable.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message to the appendable fails
   */
  void renderMessage(String message) throws IOException;

}
