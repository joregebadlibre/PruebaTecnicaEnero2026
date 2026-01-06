package com.pruebatecnica.report;

import com.pruebatecnica.openapi.model.AccountStatementReport;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfReporteGenerator {

    public byte[] generar(AccountStatementReport reporte) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 14);
                content.newLineAtOffset(50, 740);
                content.showText("Account statement");

                content.setFont(PDType1Font.HELVETICA, 10);
                content.newLineAtOffset(0, -20);
                content.showText("Customer ID: " + reporte.getCustomerId());

                content.newLineAtOffset(0, -15);
                content.showText("Customer: " + reporte.getCustomerName());

                content.newLineAtOffset(0, -15);
                content.showText("From: " + reporte.getFrom() + "  To: " + reporte.getTo());

                content.newLineAtOffset(0, -20);
                content.showText("Total credits: " + reporte.getTotalCredits());

                content.newLineAtOffset(0, -15);
                content.showText("Total debits: " + reporte.getTotalDebits());

                content.endText();
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                document.save(out);
                return out.toByteArray();
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo generar el PDF", e);
        }
    }
}
