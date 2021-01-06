package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

public class PrintUtils {

    public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18.0F, 1);
    public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.RED);
    public static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.BLACK);
    public static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.BLUE);
    public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16.0F, 1);
    public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 1);

    public static Font setUpFont(float size, int style, BaseColor color) {
        Font font = new Font();
        font.setStyle(style);
        font.setSize(size);
        font.setColor(color);
        return font;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, int position, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, int position) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell));
        cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }

        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, int colspan, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, int colspan, int rowspan, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell, font));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position) {
        PdfPCell cell = new PdfPCell(new Paragraph(sCell));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

}
