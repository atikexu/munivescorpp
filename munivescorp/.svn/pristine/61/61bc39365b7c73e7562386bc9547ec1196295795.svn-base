
package pe.dataimagenes.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {
	
	
	/**
	 * AGREGA IMÁGEN AL PDF
	 * @param pathImagen	-RUTA DE LA IMAGEN A AGREGAR
	 * @param document		-Document ITEXT
	 * @param ancho			-ANCHO DE LA IAMGEN
	 * @param alto			-ALTO DE LA IMAGEN
	 * @param posX			-COORDENADA X
	 * @param posY			-COORDENADA Y
	 * @param horizontal	-HORIZONTAL = TRUE - VERTICAL = FALSE
	 * @throws Exception
	 */
	public static void agregaImgen(String pathImagen, Document document , float ancho, float alto, float posX, float posY , boolean horizontal) throws Exception{
		Image imagen = Image.getInstance(pathImagen);
		//ESCALAR PLANTILLA
		 imagen.scaleAbsolute(ancho, alto);
		//POSICIONAR IMAGEN
		 imagen.setAbsolutePosition(posX, posY);
		 //HORIZONTAL??
		 if (!horizontal) {
			 imagen.setRotation(1.57f);	
		 }		 
		 document.add(imagen);
	}
	
	
	/**
	 * AGREGA TEXTO AL PDF.
	 * @param writer		-PDFWRITER.
	 * @param text			-TEXTO A AGREGAR.
	 * @param x				-UBICACIÓN EJE X.
	 * @param y				-UBICACIÓN EJE Y.
	 * @param rotate		-TRUE - TEXTO VERTICAL (ROTADO 90°) - FALSE TEXTO HORIZONTAL.
	 * @param fontSize		-TAMAÑO DE FUENTE.
	 * @param colorText		-COLOR DE TEXTO  EJM - BaseColor.BLUE - BaseColor.MAGENTA.
	 * @throws IOException.
	 */
	public static void absText(PdfWriter writer, String text, float x, float y, boolean rotate, float fontSize, BaseColor colorText) throws IOException {
	    try {
		      PdfContentByte cb = writer.getDirectContent();
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);		      
		      cb.saveState();
		      cb.beginText();
		      cb.moveText(x, y);
		      cb.setFontAndSize(bf, fontSize);
		      cb.setColorFill(colorText);
		      if (rotate) {
		    	  cb.showTextAligned(0, text, x, y, 90f);
		      }else{
		    	  cb.showText(text);
		      }
		      cb.endText();
		      cb.restoreState();
		    } catch (DocumentException e) {
		      e.printStackTrace();	    
		    }
	}
	
	public static void pintarLinea(PdfWriter writer, float inix, float iniy, float longitud , boolean horizontal, BaseColor color){
		try {
			String linea = "";
			for (int i = 0; i < longitud; i++) {
				linea += ".";
			}
			
			absText(writer, linea , inix, iniy, horizontal, Constantes.sizeTextFechaGestion, color);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void concatenatePdfs(List<File> listOfPdfFiles, File outputFile) throws DocumentException, IOException {
        Document document = new Document();
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        for (File inFile : listOfPdfFiles) {
            PdfReader reader = new PdfReader(inFile.getAbsolutePath());
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                PdfImportedPage page = writer.getImportedPage(reader, i);
                cb.addTemplate(page, 0, 0);
                PDFUtil.absText(writer, "TEXTO AGREGADO DESDE PDF UTIL", 100f, 100f, false, Constantes.sizeTextPriRei, Constantes.colorLineasGuia2);
            }
        }
        outputStream.flush();
        document.close();
        outputStream.close();
    }
	
	
	

}
