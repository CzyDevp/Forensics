package com.example.czydevp.forensics;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
public class FileOperations {
    public FileOperations() {
    }
    public Boolean write(String fname, String fcontent,String name,String Users,String type,String status,String Description,String startedon,String pass)
    {
        try {
           // String fpath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/" + pass + ".pdf";
            String fpath = Environment.DIRECTORY_DOCUMENTS+"/"+ pass+".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it
            if (!file.exists()){
                file.createNewFile();
            }
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer= PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            // step 3
            //setting password for pdf-----first user,Secod Owner
          writer.setEncryption(pass.getBytes(),null, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
            document.open();
            // step 4
            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.restoreState();
            Font largeBold = new Font(Font.FontFamily.COURIER, 32,
                    Font.BOLD);
            BaseColor color = new BaseColor(0,213,255);
            Font largeBold1 = new Font(Font.FontFamily.COURIER, 35,
                    Font.BOLD,color);
            document.add(new Paragraph("Detailed Report of Case"+"\n",largeBold1));
            document.add(new Paragraph(fcontent+"\n",largeBold));
            Chunk chunk = new Chunk(name+"\n", largeBold);
            Phrase phrase = new Phrase(Users+"\n",largeBold);
            Paragraph paragraph =new Paragraph(type+"\n",largeBold);
            Paragraph stts =new Paragraph(status+"\n",largeBold);
            Paragraph desc =new Paragraph(Description+"\n",largeBold);
            Paragraph strtdon =new Paragraph(startedon+"\n",largeBold);
            document.add(chunk);
            document.add(phrase);
            document.add(paragraph);
            document.add(stts);
            document.add(desc);
            document.add(strtdon);
            document.close();
            Log.d("Suceess", "Sucess");
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        catch (DocumentException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public Boolean writeuser(String name,String mobile,String email,String id,Bitmap b1)
    {
        try {
            String fpath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/" +name + ".pdf";
           // String fpath=Environment.DIRECTORY_DOCUMENTS+"/" +name + ".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
                //file.cre
            }
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer= PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            // step 3
            //setting password for pdf-----first user,Secod Owner
           // writer.setEncryption(pass.getBytes(),null, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
            document.open();
            // step 4
            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.restoreState();
            Font largeBold = new Font(Font.FontFamily.COURIER, 32,
                    Font.BOLD);
            BaseColor color = new BaseColor(0,213,255);
            Font largeBold1 = new Font(Font.FontFamily.COURIER, 35,
                    Font.BOLD,color);
            Font smallItalic = new Font(Font.FontFamily.HELVETICA, 10,
                    Font.ITALIC);
            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.ITALIC | Font.UNDERLINE, BaseColor.RED);
            document.add(new Paragraph("Detailed Report of User "+"\n",redFont));
             ByteArrayOutputStream stream = new ByteArrayOutputStream();
            b1.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
           // image.setAlignment(Image.RIGHT);
           // image.setAbsolutePosition(500f, 600f);
            //image.setIndentationLeft(20.5f);
           // image.setIndentationRight(20.5f);
            image.setAlignment(Image.MIDDLE);
            document.add(image);
            Chunk chunk = new Chunk(name+"\n", largeBold);
            Phrase phrase = new Phrase(mobile+"\n",largeBold);
            Paragraph paragraph =new Paragraph(email+"\n",largeBold);
            Paragraph stts =new Paragraph(id+"\n",largeBold);
            document.add(stts);
            document.add(chunk);
            document.add(phrase);
            document.add(paragraph);

            //page2
           /* document.newPage();
            PdfPTable table = new PdfPTable(1);
            PdfPCell cell = new PdfPCell();
            Paragraph p = new Paragraph();
           // p.add(new Phrase("Test "));
           // p.add(new Chunk(image,90,100));
            p.add(new Chunk(image,0,0));
            p.add(new Phrase(" more text "));
            p.add(new Phrase(" end."));
            cell.addElement(p);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            //table.addCell(new PdfPCell(new Phrase("test 2")));
            document.add(table);*/
            document.close();
            Log.d("Suceess", "Sucess");
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public String read(String fname) {
        BufferedReader br = null;
        String response = null;
        try {
            StringBuffer output = new StringBuffer();
            String fpath = Environment.getExternalStorageDirectory()+"/"+ fname + ".pdf";
            PdfReader reader = new PdfReader(new FileInputStream(fpath));
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            StringWriter strW = new StringWriter();
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i,
                        new SimpleTextExtractionStrategy());
                strW.write(strategy.getResultantText());
            }
            response = strW.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public class RedBorder extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
           // PdfContentByte canvas = writer.getDirectContent();
            PdfContentByte cb = writer.getDirectContent();

            Rectangle rect = document.getPageSize();
          //  Rectangle rect = new Rectangle(0,0,100,150);
            rect.setBorder(Rectangle.BOX); // left, right, top, bottom border
            rect.setBorderWidth(2); // a width of 5 user units
            rect.setBorderColor(BaseColor.RED); // a red border
            rect.setUseVariableBorders(true); // the full width will be visible

          //  canvas.rectangle(rect);
            Rectangle rect1 = new Rectangle(rect.getLeft() - 100.5f, rect.getBottom() - 100.5f, rect.getWidth() - 130, rect.getHeight() - 100, 2);
          //  cb.roundRectangle(
                //    rect.getLeft() - 11.5f, rect.getBottom() + 1.5f, rect.getWidth() - 1,
                  //  rect.getHeight() - 10, 4);
            cb.rectangle(rect1);
        }
    }
}