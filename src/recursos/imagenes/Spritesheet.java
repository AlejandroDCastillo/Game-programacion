package recursos.imagenes;

import java.awt.geom.Point2D;
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


    public BufferedImage getImg(int indexX,int indexY,int tamañoBaldosa) {
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

    public BufferedImage rotarImagen(BufferedImage imagenOriginal, double grados) {
        BufferedImage imagenRotada = new BufferedImage(frameWidth,frameHeight, BufferedImage.TYPE_INT_ARGB);
        int ancho = imagenOriginal.getWidth();
        int altura = imagenOriginal.getHeight();
        // Crear transformación de rotación
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(grados), ancho/2.0, altura/2.0);
        findTranslation(transform,imagenOriginal, (int) grados);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
       imagenRotada = op.filter(imagenOriginal, imagenRotada);
        return imagenRotada;
    }

    private Point2D hallarPtoATraslacion (BufferedImage imagenOriginal,int grados) {
        Point2D p2din;
        int alturaImagen = imagenOriginal.getHeight();
        int anchoImagen = imagenOriginal.getWidth();
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, 0.0);
        }else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(0.0, alturaImagen);
        }else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        }else{
            p2din = new Point2D.Double(anchoImagen, 0.0);
        }
        return p2din;
    }

    private Point2D hallarPtoBTraslacion (BufferedImage imagenOriginal,int grados) {
        Point2D p2din;
        int alturaImagen = imagenOriginal.getHeight();
        int anchoImagen = imagenOriginal.getWidth();
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, alturaImagen);
        }else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        }else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, 0.0);
        }else{
            p2din = new Point2D.Double(0.0, 0.0);
        }
        return p2din;
    }

    public void findTranslation (AffineTransform at,BufferedImage imagenOriginal,int grados) {
        Point2D p2din, p2dout;
        p2din = hallarPtoATraslacion(imagenOriginal,grados);
        p2dout = at.transform(p2din, null);
        double ytrans = p2dout.getY();
        p2din = hallarPtoBTraslacion(imagenOriginal,grados);
        p2dout = at.transform(p2din, null);
        double xtrans = p2dout.getX();
        AffineTransform tat = new AffineTransform();
        tat.translate(-xtrans, -ytrans);
        at.preConcatenate(tat);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}