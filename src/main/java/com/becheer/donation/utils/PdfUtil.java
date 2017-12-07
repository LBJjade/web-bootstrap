package com.becheer.donation.utils;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

/*
* PdfUtil
* Creator : xiaokepu
* Date : 2017-12-06
*/
public class PdfUtil {
    public static byte[] HtmlToPdf(String strHtml) {
        byte [] bytes=null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(strHtml);
            ITextFontResolver fontResolver=renderer.getFontResolver();
            renderer.layout();
            Document document=renderer.getDocument();
            renderer.createPDF(outputStream);
            bytes = outputStream.toByteArray();
            return bytes;
        } catch (Exception ex) {
            throw  ex;
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (java.io.IOException ex) {

            }
            return bytes;
        }
    }
}
