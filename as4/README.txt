------------------
Design
------------------

===== NEW FOR ASSIGNMENT #6 =====
The GUI basically acts as the view and the controller, because all
of the controlling is done within the GUI by clicking buttons.
Nothing was changed about our other implementation, and we
basically just added a GUI JFrame in the the view folder. The only
thing that was changed, was the simple handling of the arguments
in the main method was changed to add the "-text", starting up the
GUI on the default case with no arguments now.

===== GENERAL =====
The image processing program features a model, view, and controller
implementation. The model handles the brains of the operations,
handling all of the image processing functions, storing a map of
all of the loaded images, and being able to load and save images.
The view for the program visualizes messages sent from the
controller about the commands, and their success (or failure).
Finally, the controller handles all inputs and outputs for the
program, allowing users to interact with the model.

In the model, images are represented by an array of ColorPixel, a
class that has a Red, Green, and Blue value (and could easily be
extended to add a value for opacity). The images that have been
loaded into the model are stored in a Hash map, using their name
as their unique ID. This way, any image by name can easily be
accessed in order to run commands using that image. When a
function is run using the model, the image being used is accessed
from the Map, and a new (altered) image is then added to the map
using the destination name.

Finally, the controller uses a switch to handle all user input,
and make calls to the model.


===== NEW FOR ASSIGNMENT #5 =====
Per feedback we received on assignment 4, we moved the input
output control (the load and save image commands) to the controller.

The load and save methods were also changed in order to include
functionality for conventional image files. The loadImage method now
acts as a handler, if the path is a .ppm, it calls the ppm helper,
if not, it calls the conventional load image helper. Similarly, the
saveImage method acts as a handler, calling the ppm save method if
the path is .ppm, and the conventional image save method if it's
not a ppm. All of this input and output of files happens in the
controller, per the feedback we received on the previous assignment.

The main method is now changed so that if the program is run with
arguments, it checks if they're in the correct format, and can open
a script text file and run those commands, before closing the
program. (if the program is run with no arguments, the program runs
like before, with System in and out as inputs and outputs)

We extended the model interface (IPMV2.java) and created an
implementation (IPModelV2.java) of that new interface that extends
the old model implementation. This adds the functionality for blue,
sharpen, greyscale, and sepia.

The code we wrote for these methods would allow us to easily add
new filtering methods. For blur and sharpen, we created a helper
method that takes in a kernel and the image (the kernel being an
array of double), and the helper then uses the kernel to generate
a new image (and store it in the map). This way, we don't have to
write duplicated code for blur and sharpen, and we just call the
helper with a different kernel.

Similarly, for greyscale and sepia, we used the same design
strategy: a helper with a kernel. The kernel is a 3x3 array of
double that represents how we filter the image. The helper then
uses the kernel and the original image to generate the newly
filtered image, with no duplication.

Added functionality was also added to the controller to allow
users to call blur, sharpen, greyscale, and sepia as commands.
Because of our previous implementation, this was as simple as
adding cases to the switch for user input.




------------------
Picture Citation
------------------
The beach image was taken from pixabay.com
The exact url is the following:https://pixabay.com/photos/sunset-beach-sea-waves-shore-sand-6387462/
It was downloaded in a .jpg format and was converted to ppm (and other formats) using a GIMP, a free image processing program.