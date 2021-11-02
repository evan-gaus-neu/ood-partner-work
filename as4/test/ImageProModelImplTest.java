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

    ImageProModel model = new ImageProModelImpl();
    ImageProView view = new ImageProViewImpl(model, out);
    ImageProController controller = new ImageProControllerImpl(model, view, in);

    assertEquals("Nothing", out.toString());

  }

}