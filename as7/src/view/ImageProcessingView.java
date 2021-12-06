package view;

import java.io.IOException;

/**
 * An interface that helps present information to the user.
 */
public interface ImageProcessingView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message fails
   */
  void renderMessage(String message) throws IOException;
}
