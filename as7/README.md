# Assignment6

### Design
We designed the first stage of our ImageProcessing project
using MVC design, and wanted to make it as open to extension as possible.
To design the model, we have an ImageProcessingModel interface that we use
to represent anything we will do to an image. Right below that interface is our abstract class.
This includes generic data types and same functions that would be seen in any sort of image processing.
Extending from that class we have 2 abstract classes and 1 class that separate operations. One is an abstract flipping class
that represents both a horizontal flip and a vertical flip. Another is an abstract
greyscale class that represents all operations that greyscale an image
which in our case thus far is Luma, Intensity, Value, and the RGBModel. 
We decided to combine the RGB model since the code for each is very similar, except the difference is only the index of the RGB value.
Our brighten and darken model were also combined into one since darken is just negative brighten.
This brighten/darken model doesn't share too much in common, so it directly extends our abstract image processing model.
In our controller, we have a switch statement
that goes through every valid input and what to do in each case. As well as what should
happen if the user enters an invalid command.
Our view implementation helps display outputs to the user through text messages.
The image util helps with image reading for both pixel data and max possible pixel values, and the main works with our script to run commands.

### Assignment5 Update
From assignment 4, we did not change too much in our design. After we received feedback we moved our save method to our
ImageUtils class. We learned that it is not the responsibility of the model to save images. For Assignment 5, we created
a new AbstractBlurSharpenModel which handles common operations between blurring an image and sharpening an image.
We also created an AbstractColorTransformationModel that works not only for sepia and greyscale tones, but all filters
that could be applied to an image. That is the advantage to our design is it is very much open to extension. 
We then abstracted Luma model into the AbstractColorTransformationModel. We renamed the AbstractGreyscaleModel to AbstractRGBOnlyModel since 
the operations were not confined to only greyscale.
We also updated a new model called ModelStored which essentially is a hashmap. 
This was after received feedback about how the controller only deals with models and not any other data types, such as hashmaps.

### Assignment6 Update
The main thing for this assignment is the GUI. The new GUIController implements the old ImageProcessingController,
as well as the Features interface. This controller focuses on calling the correct modifications to the model,
and it sends that information to the view to display. In terms of the view, there is a new JFrameView that 
implements the old ImageProcessingView interface. This view creates the GUI, and it also listens for events, which
we then delegate the logic that happens back to the controller. This way, the controller handles our model logic.
There exists another class that extends JPanel, which we use to generate histograms that display image data.
The actual calculation of the histograms is now done in the AbstractImageProcessingModel, since it is the same for all our image model types.
Additionally, we changed the models so that they support intensity values.
The Main.java file was updated so that it supports loading scripts, using the text version, and using the GUI version.

### Script
The list of commands can be found in src/script.txt. 
This script is also used to generate the example ppm images,
where we have a greyscaled, brighter, darker and flipped just like the assignment asks.
In order to run the script, simply open src/Main.java, 
and it will load src/script.txt and run the commands.
At the same time, the images should be stored in res, and
the original "Tree" image should also be in res to start.

### Image Citation
This image was taken by one of the group partners on his iPhone.
The member of this team that owns this image authorizes the use of this image in this project.