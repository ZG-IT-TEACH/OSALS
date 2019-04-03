package edu.zut.cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Workbook;

public class Send {
	public static void main(String[] args) throws Exception {
		try {	
			File excelFile=new File("C:\\Users\\CuiMengtin\\Desktop\\Software17_Student_JavaEE.xlsx");//�����ļ�����
			FileInputStream in=new FileInputStream(excelFile);//�ļ���
			Excel.checkExcelVaild(excelFile);//�ж��ļ��Ƿ���Excel�ļ�
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
