## New for Assignment 7

### We were able to implement Mosaic correctly, both through scripting, text, and with the GUI. 

Implementing the Mosaic function in harmony with the existing program was relatively easy. The first step was to create a new "MosaicModel" class based on the DarkenBrighten class that extends abstract image processing model. After adding the necessary data to the constructor, the bulk of the transformation is written in the transform method. Here, we take the image's data, generate random seeds and clusters, find the average color for each cluster, then finally update the image by setting the image data (with a nearest neighbor helper).

After this, it was as simple as adding a new case to the controller for the "mosaic" command that used the MosaicModel, and adding an option in the GUI in order to allow it to call mosaic and inputting the number of seeds. Our final step was to test everything, and make sure everything worked correctly (by adding the necessary tests and cases to the existing test packages)!
