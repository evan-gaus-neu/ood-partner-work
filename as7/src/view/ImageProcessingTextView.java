package view;

import java.io.IOException;

/**
 * A text view version of the output to the user in the view.
 */
public class ImageProcessingTextView implements ImageProcessingView {

  private Appendable appendable;

  /**
   * Creates the text view with the text based appendable object.
   * @param appendable the output method of the object and representing the display
   */
  public ImageProcessingTextView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Renders the message to the output of the view.
   * @param message the message to be transmitted
   * @throws IOException throws if the message and the io have an exception
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IOException();
    }
  }
}
