/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kutuphane.mavenproject1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author vektorel
 */
public class ExlUtil {
    FrmKutuphane kutup= new FrmKutuphane();
    
    public void AktarExcel(){
        HSSFWorkbook aktar= new HSSFWorkbook();
        HSSFSheet sayfa= aktar.createSheet("Sayfa");
         Map<String, Object[]> data = new HashMap<String, Object[]>();
         
         for (int i = kutup.kitapListe.size(); i >0; i--) {
             Kitaplar kisi = kutup.kitapListe.get(i-1);
            data.put(""+i, new Object[]{Kitaplar.,kisi.getSoyad(),kisi.getAdres()});
        }
      
        data.put("" + (liste.size()+1), new Object[]{"Adı", "Soyadı", "Adres"});
           
        Set<String> keyset = data.keySet();
        int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:\\TT\\A.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel yazıldı..");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
