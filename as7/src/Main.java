import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Features;
import controller.GUIController;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;
import view.JFrameView;

/**
 * Main class to run the image processing program script.
 */
public class Main {

  /**
   * Main functionality that helps run the image processing program.
   *
   * @param args represents all the command line arguments
   */
  public static void main(String[] args) throws IOException {
    ImageProcessingView imageProcessingView = new ImageProcessingTextView(System.out);
    if (args.length > 1 && args[0].equals("-file")) {
      try {
        ImageProcessingController imageProcessingController =
                new ImageProcessingControllerImpl(
                        imageProcessingView, new FileReader(args[1]));
        imageProcessingController.runImageProcessing();
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    } else if (args.length == 1 && args[0].equals("-text")) {
      ImageProcessingController imageProcessingController =
              new ImageProcessingControllerImpl(
                      imageProcessingView, new InputStreamReader(System.in));
      imageProcessingController.runImageProcessing();
    } else if (args.length == 0) {
      Features controller = new GUIController();
      JFrameView view = new JFrameView();
      controller.setView(view);
    } else {
      System.out.println("Invalid command line argument");
    }
  }
}
