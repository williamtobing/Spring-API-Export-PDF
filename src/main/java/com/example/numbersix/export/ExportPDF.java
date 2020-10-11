package com.example.numbersix.export;

import com.example.numbersix.entity.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExportPDF {

    private static Logger logger = LoggerFactory.getLogger(ExportPDF.class);

    public static ByteArrayInputStream userPDFReport(List<User> users) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph( "https://api.github.com/users?per_page=50", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            RestTemplate restTemplate = new RestTemplate();
            String allUsers = restTemplate.getForObject("https://api.github.com/users?per_page=50", String.class);
            Paragraph para2 = new Paragraph(allUsers, font);
            document.add(para2);
            document.close();

//            PdfPTable table = new PdfPTable(3);
//            // Add PDF Table Header ->
//            Stream.of("id", "login", "node_id")
//                    .forEach(headerTitle -> {
//                        PdfPCell header = new PdfPCell();
//                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        header.setBorderWidth(2);
//                        header.setPhrase(new Phrase(headerTitle, headFont));
//                        table.addCell(header);
//                    });

//            for (User user : users) {
//                PdfPCell idCell = new PdfPCell(new Phrase(user.getId()));
//                idCell.setPaddingLeft(4);
//                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(idCell);
//
//                PdfPCell loginCell = new PdfPCell(new Phrase(user.getLogin()));
//                loginCell.setPaddingLeft(4);
//                loginCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                loginCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(loginCell);
//
//                PdfPCell nodeIdCell = new PdfPCell(new Phrase(String.valueOf(user.getNode_id())));
//                nodeIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                nodeIdCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                nodeIdCell.setPaddingRight(4);
//                table.addCell(nodeIdCell);
//            }
//            document.add((Element) allUsers);
//            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}
