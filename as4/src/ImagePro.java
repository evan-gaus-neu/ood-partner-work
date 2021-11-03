import java.io.InputStreamReader;

import controller.ImageProController;
import controller.ImageProControllerImpl;
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
    ImageProModel model = new ImageProModelImpl();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    ImageProView view = new ImageProViewImpl(model, ap);
    ImageProController controller = new ImageProControllerImpl(model, view, rd);
    controller.run();
  }
}
