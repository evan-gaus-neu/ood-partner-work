package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to view.test the TextView class.
 */
public class ImageProcessingTextViewTest {
  private ImageProcessingTextView imageProcessingTextView;
  private StringBuilder out;

  @Before
  public void setUp() {
    out = new StringBuilder();
    imageProcessingTextView = new ImageProcessingTextView(out);
  }

  @Test
  public void testValidMessage() throws IOException {
    imageProcessingTextView.renderMessage("Testing the message transmitter");
    assertEquals("Testing the message transmitter", out.toString());
  }

  class TestClass implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    public Appendable append(char c) throws IOException {
      throw new IOException();
    }

  }

  @Test(expected = IOException.class)
  public void testRenderMessageException() throws IOException {
    imageProcessingTextView = new ImageProcessingTextView(new TestClass());
    imageProcessingTextView.renderMessage("Try with invalid appendable");
  }
}
