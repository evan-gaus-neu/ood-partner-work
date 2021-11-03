------------------
Design
------------------
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


------------------
Script of Commands
------------------
load <image-path> <image-name>
    - Loads an image into the processor to be used for
    other functions, at the given path and with the
    given name

save <image-path> <image-name>
    - Saves an image from the processor with the given
    name, to the given path

red-component <image-name> <destination-image-name>
    - Creates a red component grayscale image of the
    given image name, and saves it as a new image with
    the destination image name

green-component <image-name> <destination-image-name>
    - Creates a green component grayscale image of the
    given image name, and saves it as a new image with
    the destination image name

blue-component <image-name> <destination-image-name>
    - Creates a blue component grayscale image of the
    given image name, and saves it as a new image with
    the destination image name

value-component <image-name> <destination-image-name>
    - Creates a value component grayscale image of the
    given image name, and saves it as a new image with
    the destination image name

intensity-component <image-name> <destination-image-name>
    - Creates an intensity component grayscale image of
    the given image name, and saves it as a new image
    with the destination image name

luma-component <image-name> <destination-image-name>
    - Creates a luma component grayscale image of the
    given image name, and saves it as a new image with
    the destination image name

horizontal-flip <image-name> <destination-image-name>
    - Creates a horizontal flip of the given image name,
    and saves it as a new image with the destination
    image name

vertical-flip <image-name> <destination-image-name>
    - Creates a vertical flip of the given image name,
    and saves it as a new image with the destination
    image name

brighten <increment> <image-name> <destination-image-name>
    - Creates a brighter or darker image by the
    increment given (negative for darker, positive
    value for brighter) of the given image name,
    and saves the new image at the destination
    image name


------------------
Picture Citation
------------------
The beach image was taken from pixabay.com
The exact url is the following:https://pixabay.com/photos/sunset-beach-sea-waves-shore-sand-6387462/
It was downloaded in a .jpg format and was converted to ppm using a GIMP, a free image processing program.