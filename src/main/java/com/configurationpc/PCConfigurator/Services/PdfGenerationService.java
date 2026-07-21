package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.models.Build;
import com.configurationpc.PCConfigurator.models.components.*;
import lombok.RequiredArgsConstructor;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class PdfGenerationService {

    private final BuildService buildService;

    private static final Font TITLE_FONT = new Font(Font.HELVETICA, 18, Font.BOLD);
    private static final Font SUBTITLE_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.DARK_GRAY);
    private static final Font HEADER_FONT = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);
    private static final Font BODY_FONT = new Font(Font.HELVETICA, 9);
    private static final Font TOTAL_FONT = new Font(Font.HELVETICA, 12, Font.BOLD);
    private static final Color HEADER_BG = new Color(45, 45, 45);

    public byte[] generate(int buildId) {
        Build build = buildService.showBuildById(buildId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("PC Build #" + build.getId(), TITLE_FONT));
            document.add(new Paragraph(
                    "Status: " + (build.isStatus() ? "Complete" : "Incomplete"),
                    SUBTITLE_FONT
            ));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(new float[]{2.2f, 2.8f, 5.5f, 2f});
            table.setWidthPercentage(100);
            table.setSpacingBefore(8);
            table.setHeaderRows(1);

            addHeaderCell(table, "Category");
            addHeaderCell(table, "Name");
            addHeaderCell(table, "Specifications");
            addHeaderCell(table, "Price, KZT");

            boolean alternate = false;
            for (Components component : build.getComponents()) {
                Color rowColor = alternate ? new Color(245, 245, 245) : Color.WHITE;

                table.addCell(bodyCell(component.getCategory(), rowColor));
                table.addCell(bodyCell(component.getName(), rowColor));
                table.addCell(bodyCell(buildSpecs(component), rowColor));
                table.addCell(priceCell(component.getPrice(), rowColor));

                alternate = !alternate;
            }

            document.add(table);

            Paragraph total = new Paragraph(
                    "Total price: " + formatPrice(build.getTotalPrice()) + " KZT",
                    TOTAL_FONT
            );
            total.setSpacingBefore(16);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();

            return outputStream.toByteArray();

        } catch (DocumentException exception) {
            throw new RuntimeException("PDF generation failed", exception);
        }
    }

    private void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, HEADER_FONT));
        cell.setBackgroundColor(HEADER_BG);
        cell.setPadding(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private PdfPCell bodyCell(String text, Color background) {
        PdfPCell cell = new PdfPCell(new Phrase(text, BODY_FONT));
        cell.setBackgroundColor(background);
        cell.setPadding(6);
        return cell;
    }

    private PdfPCell priceCell(double price, Color background) {
        PdfPCell cell = new PdfPCell(new Phrase(formatPrice(price), BODY_FONT));
        cell.setBackgroundColor(background);
        cell.setPadding(6);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private String formatPrice(double price) {
        return String.format("%,.0f", price);
    }

    private String buildSpecs(Components component) {
        if (component instanceof Cpu cpu) {
            return "Socket: " + cpu.getSocket() + ", TDP: " + cpu.getTdp() + "W";
        }
        if (component instanceof Gpu gpu) {
            return "Power: " + gpu.getPowerConsumption() + "W, Connectors: " + gpu.getPowerConnectors()
                    + ", Length: " + gpu.getLengthGpu() + "mm";
        }
        if (component instanceof Motherboard motherboard) {
            return "Socket: " + motherboard.getSocket() + ", RAM type: " + motherboard.getRamType()
                    + ", Form factor: " + motherboard.getFormFactor();
        }
        if (component instanceof Ram ram) {
            return "Type: " + ram.getTypeRam() + ", Capacity: " + ram.getMemory() + "GB";
        }
        if (component instanceof Psu psu) {
            return "Power: " + psu.getTotalPower() + "W, Connectors: " + psu.getAvailableConnectors();
        }
        if (component instanceof Cooler cooler) {
            return "Sockets: " + cooler.getSupportedSockets() + ", Max TDP: " + cooler.getMaxTdp()
                    + "W, Height: " + cooler.getHeightCooler() + "mm";
        }
        if (component instanceof Case pcCase) {
            return "Form factors: " + pcCase.getSupportedFormFactors()
                    + ", Max GPU length: " + pcCase.getMaxGpuLength() + "mm"
                    + ", Max cooler height: " + pcCase.getMaxCoolerHeight() + "mm";
        }
        return "-";
    }
}