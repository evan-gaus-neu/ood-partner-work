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
    // load res/sun.ppm sun vertical-flip sun flip save res/flip4.ppm flip quit
    // load res/sun.ppm sun   red-component sun red   green-component sun green    blue-component sun blue      save res/red.ppm red   save res/green.ppm green   save res/blue.ppm blue        quit
    // load res/sun.ppm sun   value-component sun value   intensity-component sun intensity   luma-component sun luma      save res/value.ppm value    save res/intensity.ppm intensity    save res/luma.ppm luma       quit
    // load res/sun.ppm sun   vertical-flip sun vflip   horizontal-flip sun hflip     save res/vflip.ppm vflip    save res/hflip.ppm hflip      quit
    // load res/sun.ppm sun    brighten 200 sun bright    brighten -200 sun dark     save res/bright.ppm bright    save res/dark.ppm dark    quit
    // load res/sun.ppm sun    brighten -100 sun dark    save res/dark.ppm dark    quit
    // load res/sun.ppm sun   vertical-flip sun vflip   horizontal-flip sun hflip    brighten 100 sun bright    brighten -100 sun dark    save res/vflip.ppm vflip    save res/hflip.ppm hflip    save res/bright.ppm bright    save res/dark.ppm dark    quit
  }
}
