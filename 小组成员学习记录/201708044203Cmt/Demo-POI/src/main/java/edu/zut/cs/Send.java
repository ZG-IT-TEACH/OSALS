package edu.zut.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Workbook;

public class Send {
	public static void main(String[] args) throws Exception {
		try {	
			File excelFile=new File("C:\\Users\\CuiMengtin\\Desktop\\Software17_Student_JavaEE.xlsx");//创建文件对象
			FileInputStream in=new FileInputStream(excelFile);//文件流
			Excel.checkExcelVaild(excelFile);//判断文件是否是Excel文件
			Workbook workbook=Excel.getWorkbook(in,excelFile);
			Excel.disPlayRow(workbook);
			Producer cd=new Producer();
			cd.sendMessageExcel(Excel.getCellNum()*Excel.getRowNum(), Excel.getExcelCell());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
