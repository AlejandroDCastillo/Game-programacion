package recursos.imagenes;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Spritesheet {

    private int counter = -1;       // fotograma actual (empezando desde 0)
    private int frameWidth, frameHeight;   // ancho y alto de cada fotograma (en píxeles)
    private int numFramesX;          // número de fotogramas en la secuencia (horizontal)
    private int numFramesY;          // número de fotogramas en la secuencia (vertical)
    private BufferedImage img;       // la imagen en sí

    // inicializa con la imagen y el número de fotogramas que contiene
    public Spritesheet(BufferedImage img, int numFramesX, int numFramesY) {
        this.img = img;
        this.numFramesX = numFramesX;
        this.numFramesY = numFramesY;
        frameWidth = img.getWidth() / this.numFramesX;   // calcula el ancho de cada fotograma (px)
        frameHeight = img.getHeight() / this.numFramesY; // calcula el alto de cada fotograma (px)
    }

    /**
     * metodo para cargar una imagen de un sprite es decir los pixeles que necesita del sprite
     * @param indexX
     * @param indexY
     * @param tamañoBaldosa
     * @return
     */
    public BufferedImage getImg(int indexX,int indexY,int tamañoBaldosa) {
            return img.getSubimage(indexX * frameWidth, indexY*frameHeight, frameWidth, frameHeight);
    }

    /**
     * sobrecarga del metodo get img para usar el asset de inventario
     * @param indexX
     * @param indexY
     * @return
     */
    public BufferedImage getImg(int indexX,int indexY) {
        return img.getSubimage(indexX * frameWidth, indexY*frameHeight, frameWidth, frameHeight);
    }

    /**
     * metodo que invierte la img del sprite
     * @param imagenInversa
     * @return
     */
    public BufferedImage invertir(BufferedImage imagenInversa) {
        BufferedImage invertedImage = new BufferedImage(frameWidth,frameHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);
        transform.translate(-frameWidth,0);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        invertedImage = op.filter(imagenInversa,invertedImage);
        return invertedImage;
    }

    /**
     * metodo para rotar la img del sprite
     * @param imagenOriginal
     * @param grados
     * @return
     */
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



    /**
     * Calcula un punto de referencia en la imagen original según el ángulo de rotación.
     * Este punto se utiliza como base para determinar cómo se traslada la imagen al rotarla.
     *
     * @param imagenOriginal La imagen original que se va a transformar.
     * @param grados El ángulo de rotación en grados.
     * @return Un punto (Point2D) que representa una esquina de la imagen original,
     *         dependiendo del ángulo de rotación.
     */
    private Point2D hallarPtoATraslacion(BufferedImage imagenOriginal, int grados) {
        Point2D p2din;
        int alturaImagen = imagenOriginal.getHeight();
        int anchoImagen = imagenOriginal.getWidth();
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, 0.0);
        } else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(0.0, alturaImagen);
        } else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        } else {
            p2din = new Point2D.Double(anchoImagen, 0.0);
        }
        return p2din;
    }

    /**
     * Calcula otro punto de referencia en la imagen original, complementario al anterior,
     * según el ángulo de rotación. Este también se utiliza para ajustar correctamente
     * la traslación después de la rotación.
     *
     * @param imagenOriginal La imagen original que se va a transformar.
     * @param grados El ángulo de rotación en grados.
     * @return Un punto (Point2D) diferente al del primer método, usado para calcular
     *         la traslación final de la imagen.
     */
    private Point2D hallarPtoBTraslacion(BufferedImage imagenOriginal, int grados) {
        Point2D p2din;
        int alturaImagen = imagenOriginal.getHeight();
        int anchoImagen = imagenOriginal.getWidth();
        if (grados >= 0 && grados <= 90){
            p2din = new Point2D.Double(0.0, alturaImagen);
        } else if (grados > 90 && grados <= 180){
            p2din = new Point2D.Double(anchoImagen, alturaImagen);
        } else if (grados > 180 && grados <= 270){
            p2din = new Point2D.Double(anchoImagen, 0.0);
        } else {
            p2din = new Point2D.Double(0.0, 0.0);
        }
        return p2din;
    }

    /**
     * Ajusta una transformación afín (AffineTransform) para que la imagen
     * rotada quede bien posicionada en pantalla. Utiliza dos puntos de referencia
     * para calcular la traslación necesaria después de aplicar la rotación.
     *
     * @param at La transformación afín que se va a modificar (normalmente contiene ya una rotación).
     * @param imagenOriginal La imagen que se está rotando y trasladando.
     * @param grados El ángulo de rotación aplicado a la imagen.
     */
    public void findTranslation(AffineTransform at, BufferedImage imagenOriginal, int grados) {
        Point2D p2din, p2dout;
        p2din = hallarPtoATraslacion(imagenOriginal, grados);
        p2dout = at.transform(p2din, null);
        double ytrans = p2dout.getY();

        p2din = hallarPtoBTraslacion(imagenOriginal, grados);
        p2dout = at.transform(p2din, null);
        double xtrans = p2dout.getX();

        AffineTransform tat = new AffineTransform();
        tat.translate(-xtrans, -ytrans);
        at.preConcatenate(tat);
    }

    //getters y setters

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}