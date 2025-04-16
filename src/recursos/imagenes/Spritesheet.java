package recursos.imagenes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageFilter;

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
            return img.getSubimage(indexX * frameWidth, indexY*frameHeight, frameWidth, frameHeight);
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

    public BufferedImage rotarimagen90grados(BufferedImage imagenOriginal) {
        BufferedImage imagenRotada = new BufferedImage(frameWidth,frameHeight, BufferedImage.TYPE_INT_ARGB);
        int ancho = imagenOriginal.getWidth();
        int altura = imagenOriginal.getHeight();
        // Crear transformación de rotación
        AffineTransform transform = new AffineTransform();
        // Rotar 90 grados (π/2 rad) alrededor del origen (0,0)
        transform.translate(altura, 0);  // Mover imagen para que quepa en el nuevo canvas
        transform.rotate(Math.toRadians(90));
        // Aplicar transformación
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
       imagenRotada = op.filter(imagenOriginal, imagenRotada);
        return imagenRotada;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}