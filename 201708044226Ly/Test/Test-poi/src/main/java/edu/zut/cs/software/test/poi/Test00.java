package edu.zut.cs.software.test.poi;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test00 {
	public static void main(String[] args) throws IOException {
        String filePath="d:sample.xlsx";//文件路径
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        HSSFSheet sheet = workbook.createSheet();//创建工作表(Sheet)
        sheet = workbook.createSheet("Test");//创建工作表(Sheet)
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }
}
