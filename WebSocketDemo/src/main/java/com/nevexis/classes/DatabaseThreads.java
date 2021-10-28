package com.nevexis.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
@Transactional
public class DatabaseThreads {

	private static final String TXT_TXT = "txt2.txt";

	private static final int NUMBER_OF_PROCESSOR_THREADS = Runtime.getRuntime().availableProcessors();

	private static int pageSize;

	@PersistenceContext
	private EntityManager em;

	private Person[] persons;

	private CyclicBarrier cyclicBarrier;

	// @PostConstruct
	public void putIntoDatabase() {
		for (int i = 0; i < 10000; i++) {
			Person person = new Person();
			person.setName(String.format("ivan%d", i));
			em.persist(person);
		}
	}

	private class DBProcessinghread implements Runnable {
		Integer threadNumber = new Integer(0);

		public DBProcessinghread(Integer i) {
			threadNumber = i;
		}

		@Override
		public void run() {
			int offset = threadNumber * pageSize;
			List<Person> tmp = em.createQuery("SELECT p FROM Person p", Person.class).setFirstResult(offset)
					.setMaxResults(pageSize).getResultList();
			for (int j = offset; j < offset + pageSize; j++) {
				persons[j] = tmp.get(j - offset);
			}

			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
			}
		}
	}

	public void process() {

		// phase1 - get number of rows and initialize runtime structure
		long numberOfRows = (long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
		pageSize = (int) numberOfRows / NUMBER_OF_PROCESSOR_THREADS;

		persons = new Person[(int) numberOfRows];

		// phase 2
		cyclicBarrier = new CyclicBarrier(NUMBER_OF_PROCESSOR_THREADS + 1);
		for (int threadCounter = 0; threadCounter < NUMBER_OF_PROCESSOR_THREADS; threadCounter++) {
			new Thread(new DBProcessinghread(threadCounter)).start();
		}

		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
		}

		// phase 3
		// print xls, pdf & file
//		writeInFile();
	}

	public void writeInFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(TXT_TXT);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for (Person per : persons) {
			oos.writeObject(per); // write MenuArray to ObjectOutputStream
		}
		
		oos.close();
	}

	public void writeInExcel(OutputStream out) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Persons");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);

		Row header =  sheet.createRow(0);

		CellStyle headerStyle = workbook.createCellStyle();
	
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Id");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("Name");
		headerCell.setCellStyle(headerStyle);
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		for (int i = 0; i < persons.length; i++) {
			Row row = sheet.createRow(i + 2);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(persons[i].getName());
			cell.setCellStyle(style);

			cell = row.createCell(1);
			
			for (Method m : this.getClass().getMethods()) {
		          cell.setCellValue(m.invoke( persons[i]).toString());
			}
			cell.setCellStyle(style);
		}

		workbook.write(out);
	}
	  


	public void writeInPdf(OutputStream out)
			throws DocumentException, URISyntaxException, MalformedURLException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		process();
		
		Document document = new Document();

		Path path = Paths.get(ClassLoader.getSystemResource("tumbnail.png").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());

		int indentation = 0;
		float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
				- indentation) / img.getWidth()) * 100;
		img.scalePercent(scaler);
		Font font =  FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk(String.format("Total people: %d",persons.length), (com.itextpdf.text.Font) font);
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table);
		addRows(table);

		PdfWriter.getInstance(document, out);
		document.open();
		document.add(img);
		
		document.add(table);
		document.add(chunk);
		document.close();
	}
	public void writeInZip(OutputStream out) throws IOException, DocumentException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ZipOutputStream zipStream = new ZipOutputStream(out);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		writeInPdf(buffer);
		zipStream.putNextEntry(new ZipEntry("sample.pdf"));
		zipStream.write(buffer.toByteArray());
		zipStream.close();
		
    }

	private void addRows(PdfPTable table) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i=0;i<persons.length;i++)
		{
			for (Method m : this.getClass().getMethods()) {
				table.addCell(m.invoke( persons[i]).toString());
			}
			

		}
		
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Name","Id")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
		
	}


}
