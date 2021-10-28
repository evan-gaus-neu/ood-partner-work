import java.io.InputStreamReader;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import model.ImageProModel;
import model.ImageProModelImpl;
import view.ImageProView;
import view.ImageProViewImpl;

public class ImagePro {
  public static void main(String[] args) {
    ImageProModel model = new ImageProModelImpl();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    ImageProView view = new ImageProViewImpl(model, ap);
    ImageProController controller = new ImageProControllerImpl(model, view, rd);
    controller.run();
  }
}
