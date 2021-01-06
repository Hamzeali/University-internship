package code;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.html2pdf.HtmlConverter;

public class ConverteHTMLtoPDF {

	public void converte(String title, String content, String subsection, String from) {
		String headTitle = "<h1 style=\"text-align: center;\"><span style=\"color: #800000;\"><strong>"
				+ title.toUpperCase() + "</strong></span></h1>\n" + "<p><span style=\"color: #800000;\"><strong>"
				+ subsection
				+ "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
				+ from + "</strong></span></p>\n" + "<hr />\n" + "<h3>&nbsp;</h3>";
		try {

//			File theDir = new File(
//					"/Users/mohammedalianis/git/github-propra/Projectphase/Group 2/IELTS/ELBIS/PDF/" + subsection);
			
			String path = "src/pdf";

			File file = new File(path);
			String absolutePath = file.getAbsolutePath();

			File theDir = new File(absolutePath +"/"+ subsection);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}
			HtmlConverter.convertToPdf(headTitle + "\n " + content,
					new FileOutputStream(theDir + "/" + title + ".pdf"));
			System.out.println("PDF file has been created !");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}