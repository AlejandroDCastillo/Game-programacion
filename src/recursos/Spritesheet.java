package recursos;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Spritesheet {

    private int counter = -1;       // current frame we're on (0-indexed)
    private int frameWidth, frameHeight;   // width and height of each frame (px)
    private int numFramesX;          // number of frames in the sequence
    private int numFramesY;
    private BufferedImage img;// the image itself

    // initialize with image and the number of frames it contains
    public Spritesheet(BufferedImage img,int numFramesX, int numFramesY) {
        this.img = img;
        this.numFramesX = numFramesX;
        this.numFramesY = numFramesY;
        frameWidth = img.getWidth() / this.numFramesX;  // calculate width of each frame (px)
        frameHeight = img.getHeight()/this.numFramesY;

    }


    public BufferedImage getImg(int indexX,int indexY) {
        indexX = indexX % numFramesX;
        if (indexX != 0||indexY!=0){
            return img.getSubimage(indexX * frameWidth, indexY*frameHeight, frameWidth, frameHeight);
        }else {
            return null;
        }
    }

    public BufferedImage invertir(BufferedImage imagenInversa) {
        BufferedImage invertedImage = new BufferedImage(frameWidth,frameHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);
        transform.translate(-frameWidth,0);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        invertedImage = op.filter(imagenInversa,invertedImage);
        return invertedImage;

    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}