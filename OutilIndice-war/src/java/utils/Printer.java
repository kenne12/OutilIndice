package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class Printer {

    private static final ResourceBundle rs = ResourceBundle.getBundle("langues/langue", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    private static JasperPrint jasperPrint;
    private static final String user = "postgres";
    private static final String password = "batrapi";
    private static final String driver = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/outil_indice_db";
    private static Connection con;

    public static void print(String path, Map parameters)
            throws Exception {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

            parameters.put("REPORT_LOCALE", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            parameters.put("REPORT_RESOURCE_BUNDLE", rs);
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, con);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + path.substring(path.lastIndexOf("/"), path.lastIndexOf(".")) + ".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            throw new IOException(e);
        } catch (JRException e) {
            throw new JRException(e);
        }
    }

    public static void DOCX(String path, Map parameters) throws JRException, IOException, ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        JRDocxExporter docxExporter = new JRDocxExporter();
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
        jasperPrint = JasperFillManager.fillReport(reportPath, parameters, con);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.reset();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=" + path.substring(path.lastIndexOf("/"), path.lastIndexOf(".")) + ".docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
        con.close();
    }

    public static void XLSX(String path, Map parameters) throws JRException, IOException, ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
        jasperPrint = JasperFillManager.fillReport(reportPath, parameters, con);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.reset();
        httpServletResponse.addHeader("Content-disposition", "inline; filename=" + path.substring(path.lastIndexOf("/"), path.lastIndexOf(".")) + ".xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        xlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        xlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        xlsxExporter.exportReport();
        con.close();
    }
}
