import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import model.IPMV2;
import model.IPModelV2;
import model.ImageProModel;
import model.ImageProModelImpl;
import view.ImageProView;
import view.ImageProViewImpl;

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

    // Check if we have command line input
    if (args.length == 1) {
      System.out.println("Error: invalid command line input. No file path found.");
      System.exit(1);
    }
    else if (args.length > 1) {
      // This means we have arguments
      if (!args[0].equals("-file")) {
        System.out.println("Error: invalid command line input, must start with '-file'");
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

      //
      rd = new StringReader(commands);
    }
    // Don't have to have an else because if I don't
    // overwrite rd, it'll be system in by default

    // Do all the normal set up things
    IPMV2 model = new IPModelV2();
    Appendable ap = System.out;
    ImageProView view = new ImageProViewImpl(model, ap);
    ImageProController controller = new ImageProControllerImpl(model, view, rd);
    controller.run();
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
