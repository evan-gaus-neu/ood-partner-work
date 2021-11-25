import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.*;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import model.IPMV2;
import model.IPModelV2;
import view.ImageProView;
import view.ImageProViewImpl;
import view.SwingFrame;

/**
 * Class that acts as a runner for the Model, View, and Controller.
 * The main method runs the whole process.
 */
public class ImagePro {

  /**
   * This is the main method that acts as a runner for all the code.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {

    // Readable
    Readable rd = new InputStreamReader(System.in);

    if (args.length > 0) {
      if (args[0].equals("-text")) {
        // Do the normal text view
        rd = new InputStreamReader(System.in);
      }
      else if (args[0].equals("-file")) {
        // Do the file stuff
        if (args.length <= 1) {
          System.out.println("Error: invalid command line input. No file path found.");
          System.exit(1);
        }
        // Now we know it starts with file, call this to open the file and read it
        String commands = new String();
        try {
          commands = getFileInputAsString(args[1]);
        }
        catch (IllegalArgumentException e) {
          System.out.println("Error: " + e.getMessage());
          System.exit(1);
        }
        // Set rd to the commands
        rd = new StringReader(commands);
      }
      else {
        System.out.println("Error: invalid command line input, must start with '-file' or '-text'");
        System.exit(1);
      }

      // Set up stuff with for file or text
      // Do all the normal set up things
      IPMV2 model = new IPModelV2();
      Appendable ap = System.out;
      ImageProView view = new ImageProViewImpl(model, ap);
      ImageProController controller = new ImageProControllerImpl(model, view, rd);
      controller.run();
    }
    else {
      // GUI

      // Create the GUI frame
      SwingFrame.setDefaultLookAndFeelDecorated(false);
      SwingFrame frame = new SwingFrame();

      // Set up the GUI frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        // This calls the GUI
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (UnsupportedLookAndFeelException e) {
        // Don't do anything if it breaks
      }
      catch (ClassNotFoundException e) {
        // Don't do anything if it breaks
      }
      catch (InstantiationException e) {
        // Don't do anything if it breaks
      }
      catch (IllegalAccessException e) {
        // Don't do anything if it breaks
      }
      catch (Exception e) {
        // Don't do anything if it breaks
      }
    }

  }

  static protected String getFileInputAsString(String path) throws IllegalArgumentException {
    // Scanner for file
    Scanner scan;
    // Try to load the file
    try {
      // Set up the file and scanner
      File file = new File(path);
      scan = new Scanner(file);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Failed to open and read file");
    }
    // Add the file contents to a string and return it
    String returnStr = new String();
    while (scan.hasNext()) {
      returnStr += " " + scan.next();
    }
    return returnStr;
  }

}
