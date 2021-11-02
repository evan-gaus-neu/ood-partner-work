import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProController;
import controller.ImageProControllerImpl;
import model.ImageProModel;
import model.ImageProModelImpl;
import view.ImageProView;
import view.ImageProViewImpl;

import static org.junit.Assert.*;

public class ImageProModelImplTest {

  @Test
  public void sampleTest() {

    // This string is what input we are giving it
    Reader in = new StringReader("q");
    StringBuilder out = new StringBuilder();

    // Set up the model, view, and controller
    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    // Run the controller
    controller.run();

    assertEquals("Welcome to the Image Processor!\n" +
            "Type instruction: \n" +
            "Program quit. Thanks!", out.toString());

  }

}