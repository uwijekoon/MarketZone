package com.ci6225.marketzone.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


@Service("pdfGenerator")
public class PDFGenerator  extends PdfPageEventHelper{
	
	private final float DEFAULT_DOCUMENT_LEFT_MARGIN = 55f;
	private final float DEFAULT_DOCUMENT_RIGHT_MARGIN = 50f;
	private final float DEFAULT_DOCUMENT_BOTTOM_MARGIN = 75f;
	private final float DEFAULT_DOCUMENT_TOP_MARGIN = 50f;
	private final Font font = FontFactory.getFont("sans-serif", 10, Font.NORMAL, BaseColor.BLACK);
	private final Font titlefont = FontFactory.getFont("sans-serif", 16, Font.BOLD, BaseColor.BLACK);
	private final Font subtitlefont = FontFactory.getFont("sans-serif", 10, Font.BOLD, BaseColor.BLACK);

	public String generateOrderPdf(String rootUrl) throws Exception{
		String directory = Properties.getProperty("FILE_STORE_PATH");
		String strFileName = getUniqueFileName(directory + "/pdf","pdf");
		File outDir = new File(directory);
		if(!outDir.exists()){
			outDir.mkdirs();
		}
		File pdffile = new File(outDir,strFileName);


		Document document = new Document(PageSize.A4, this.DEFAULT_DOCUMENT_LEFT_MARGIN,
				this.DEFAULT_DOCUMENT_BOTTOM_MARGIN,
				this.DEFAULT_DOCUMENT_RIGHT_MARGIN,
				this.DEFAULT_DOCUMENT_TOP_MARGIN);
		PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(pdffile));
		writer.setPageEvent(new PDFGenerator());

		document.open();

		PDFHeader(document, "11111", "CONFIRMED", "Clementi Avenue 5, Singapore", rootUrl);
		PDFDetails(document);

		document.close();

		return pdffile.getAbsolutePath() ;
	}
	
	private void PDFDetails(Document document) throws Exception{
		float[] colsWidth = {1f,3f,1f};
		PdfPTable formattedTable = new PdfPTable(colsWidth);
		formattedTable.setWidthPercentage(100);
		formattedTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		formattedTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		formattedTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		formattedTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

		formattedTable.addCell(getImageCell(Properties.getProperty("FILE_STORE_PATH")+"images/16/1489210988934.s7.jpeg"));
		formattedTable.addCell(getTextCell("Item name 1"));
		formattedTable.addCell(getAmountCell("SGD 37.00"));
		formattedTable.addCell(getTextCell("Qty: 2"));
		formattedTable.addCell(getTextCell("Seller: Seller 1"));
		
		
		formattedTable.addCell(getImageCell(Properties.getProperty("FILE_STORE_PATH")+"images/18/1489231256138.specs_gold.jpg"));
		formattedTable.addCell(getTextCell("Item name 2"));
		formattedTable.addCell(getAmountCell("SGD 37.00"));
		formattedTable.addCell(getTextCell("Qty: 1"));
		formattedTable.addCell(getTextCell("Seller: Seller 2"));
		
		formattedTable.addCell("");
		formattedTable.addCell(getRightAlignedCell("Sub Total:"));
		formattedTable.addCell(getTextCell("SGD 80.00"));
		formattedTable.addCell("");
		formattedTable.addCell(getRightAlignedCell("Admin Fee:"));
		formattedTable.addCell(getTextCell("SGD 0.80"));
		formattedTable.addCell("");
		formattedTable.addCell(getRightAlignedCell("Total:"));
		formattedTable.addCell(getTextCell("SGD 80.80"));
		
		document.add(formattedTable);
	}
	
	private PdfPCell getRightAlignedCell(String text){
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(10f);
		return cell;
	}
	
	private PdfPCell getTextCell(String text){
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	private PdfPCell getImageCell(String imagePath) throws Exception{
		Image image = Image.getInstance(imagePath);
		image.setScaleToFitLineWhenOverflow(true);
		PdfPCell cell = new PdfPCell(image);
		cell.setPaddingRight(10f);
		cell.setRowspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(20f);
		return cell;
	}
	
	private PdfPCell getAmountCell(String text){
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(3);
		return cell;
	}
	
	private static String getUniqueFileName(String outputDir, String type) {
		String fileName = "";
		boolean keepTrying = true;
		type.trim();
		while ( keepTrying ) {  // keep going tell get a unique file name
			// generate a random number between 0 and FFFFF hex  (five hex decimals because of html graphics file names)
			fileName = Integer.toHexString((int)Math.rint(new SecureRandom().nextDouble()*1048574))+"."+ type;
			for ( int i = 0; i < ( 5 - fileName.length()); i++ )
				fileName = "0" + fileName;
			File fileCheck = new File ( outputDir + File.separator + fileName );
			if ( fileCheck.exists() )
				keepTrying = true;
			else
				keepTrying = false;
		}
		return fileName;
	}
	
	private void PDFHeader(Document document, String orderNo, String status, String address, String rootUrl) throws Exception{
		PdfPTable headerTable = new PdfPTable(2);
		headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);


		Image image = Image.getInstance(new URL(rootUrl+"/themes/images/logo.png"));
		image.setScaleToFitLineWhenOverflow(true);
		PdfPCell title1 = new PdfPCell(image);
		//title1.setHorizontalAlignment(Element.ALIGN_CENTER);
		title1.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(title1);
		
		PdfPCell title = new PdfPCell(new Paragraph("Order No: " + orderNo, titlefont));
		title.setHorizontalAlignment(Element.ALIGN_CENTER);
		title.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(title);
		headerTable.setSpacingAfter(15f);
		document.add(headerTable);
		
		float[] f = {0.35f,1f};
		PdfPTable subHeaderTable = new PdfPTable(f);
		subHeaderTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		//subHeaderTable.setTotalWidth(1f);
		subHeaderTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		subHeaderTable.getDefaultCell().setPaddingBottom(5f);
		subHeaderTable.addCell(new Paragraph("Status: ", subtitlefont));
		subHeaderTable.addCell(new Paragraph(status, subtitlefont));
		subHeaderTable.addCell(new Paragraph("Shipping Address: ", subtitlefont));
		subHeaderTable.addCell(new Paragraph(address, font));
		subHeaderTable.addCell("");
		subHeaderTable.addCell("");
		subHeaderTable.addCell(new Paragraph("Order Items", subtitlefont));
		subHeaderTable.addCell("");
		document.add(subHeaderTable);
	}
}
