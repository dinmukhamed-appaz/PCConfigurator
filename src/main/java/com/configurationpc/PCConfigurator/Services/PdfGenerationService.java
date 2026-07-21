package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.models.Build;
import com.configurationpc.PCConfigurator.models.components.Components;
import lombok.RequiredArgsConstructor;
import org.openpdf.text.Document;
import org.openpdf.text.DocumentException;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class PdfGenerationService {

    private final BuildService buildService;

    public byte[] generate(int buildId) {
        Build build = buildService.showBuildById(buildId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("PC Build #" + build.getId()));
            document.add(new Paragraph("Components:"));

            for (Components component : build.getComponents()) {
                document.add(new Paragraph(
                        component.getCategory()
                                + " - "
                                + component.getName()
                                + " - "
                                + component.getPrice()
                                + " KZT"
                ));
            }

            document.add(new Paragraph(
                    "Total price: " + build.getTotalPrice() + " KZT"
            ));

            document.close();

            return outputStream.toByteArray();

        } catch (DocumentException exception) {
            throw new RuntimeException("PDF generation failed", exception);
        }
    }
}