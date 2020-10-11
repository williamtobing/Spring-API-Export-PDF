package com.example.numbersix.export;

import com.example.numbersix.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExportPDF {

    private static Logger logger = LoggerFactory.getLogger(ExportPDF.class);

    public static ByteArrayInputStream userPDFReport(List<User> users) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Data to PDF file
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph( "https://api.github.com/users?per_page=50", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            Font font2 = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
            RestTemplate restTemplate = new RestTemplate();
            String allUsers = restTemplate.getForObject("https://api.github.com/users?per_page=50", String.class);
            Paragraph para2 = new Paragraph(allUsers, font2);
            document.add(para2);

            document.close();

        }catch (DocumentException e) {

            logger.error(e.toString());

        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}
