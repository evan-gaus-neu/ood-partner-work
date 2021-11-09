import java.io.InputStreamReader;

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
    IPMV2 model = new IPModelV2();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    ImageProView view = new ImageProViewImpl(model, ap);
    ImageProController controller = new ImageProControllerImpl(model, view, rd);
    controller.run();
  }
}
