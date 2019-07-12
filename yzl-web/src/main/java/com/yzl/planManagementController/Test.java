package com.yzl.planManagementController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Decimal;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;



public class Test {

	public static void main(String[] args) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();//��������������wxcel�ĵ�����
		
		HSSFSheet sheet = workbook.createSheet("����������������");//����������
		sheet.setColumnWidth(0, 5*256);//���õ�Ԫ��Ŀ�
		
		HSSFRow row = sheet.createRow(1);//��sheet�ﴴ����
		HSSFCell cell0 = row.createCell(0);//�����ﴴ����
		row.setHeightInPoints(30);//�и�
		//���õ�Ԫ������
		cell0.setCellValue("���ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ���");
		//�ϲ���Ԫ��//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 35));
		
		HSSFRow row1 = sheet.createRow(3);//��sheet�ﴴ����
		
		//�����ﴴ����
		HSSFCell cell1 = row1.createCell(1);//������
		cell1.setCellValue("��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 1, 1));
		
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellValue("��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 2, 2));
		
		HSSFCell cell3 = row1.createCell(3);
		cell3.setCellValue("������� ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 3, 3));
		
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellValue("������� ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 4, 4));
		
		HSSFCell cell5 = row1.createCell(5);
		cell5.setCellValue("ʱ��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 5, 5));
		
		HSSFCell cell6 = row1.createCell(6);
		cell6.setCellValue("������");//�Լ��ϱ����
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 6, 16));//�ϲ���
		
		HSSFCell cell7 = row1.createCell(17);
		cell7.setCellValue("�˲��������");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 17, 19));
		
		HSSFCell cell8 = row1.createCell(20);
		cell8.setCellValue("����������");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 20, 26));
		
		HSSFCell cell9 = row1.createCell(27);
		cell9.setCellValue("����ָ��");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 27, 35));
		
		//�ڽ�һ��
		HSSFRow row2 = sheet.createRow(5);//��sheet�ﴴ����
		
		HSSFCell cell10 = row2.createCell(6);
		cell10.setCellValue("�Լ��ϱ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 6, 6));
		
		HSSFCell cell11 = row2.createCell(7);
		cell11.setCellValue("��ʵ���  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 7, 7));
		
		HSSFCell cell12 = row2.createCell(8);
		cell12.setCellValue("��ʵ�ϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 8, 8));
		
		HSSFCell cell13 = row2.createCell(9);
		cell13.setCellValue("��ҵ������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));
		
		HSSFCell cell14 = row2.createCell(10);
		cell14.setCellValue("����ҵ���ʩ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 10, 10));
		
		HSSFCell cell15 = row2.createCell(11);
		cell15.setCellValue("�е������");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 11, 11));
		
		HSSFCell cell16 = row2.createCell(12);
		cell16.setCellValue("��չ�ؼ��Լ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 12, 12));
		
		HSSFCell cell17 = row2.createCell(13);
		cell17.setCellValue("���������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 13, 13));
		
		HSSFCell cell18 = row2.createCell(14);
		cell18.setCellValue("��ʵ������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 14, 14));
		
		HSSFCell cell19 = row2.createCell(15);
		cell19.setCellValue("�������  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 15, 15));
		
		HSSFCell cell20 = row2.createCell(16);
		cell20.setCellValue("�ܻ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 16, 16));
		
		
		HSSFCell cell21 = row2.createCell(17);
		cell21.setCellValue("�����ʵ��  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 17, 17));
		
		HSSFCell cell22 = row2.createCell(18);
		cell22.setCellValue("��ʵ����ϸ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 18, 18));
		
		HSSFCell cell23 = row2.createCell(19);
		cell23.setCellValue("�ϱ�����ϸ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 19, 19));
		
		
		HSSFCell cell24 = row2.createCell(20);
		cell24.setCellValue("�����ϱ�������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 20, 20));
		
		HSSFCell cell25 = row2.createCell(21);
		cell25.setCellValue("���ֵ����ϱ��ϸ� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 21, 21));
		
		HSSFCell cell26 = row2.createCell(22);
		cell26.setCellValue("ȫ���Լ�ϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 22, 22));
		
		HSSFCell cell27 = row2.createCell(23);
		cell27.setCellValue("�����ʵ���");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 23, 23));
		
		HSSFCell cell28 = row2.createCell(24);
		cell28.setCellValue("������ɺϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 24, 24));
		
		HSSFCell cell29 = row2.createCell(25);
		cell29.setCellValue("�ƻ�����  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 25, 25));
		
		HSSFCell cell30 = row2.createCell(26);
		cell30.setCellValue("��������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 26, 26));
		
		
		HSSFCell cell31 = row2.createCell(27);
		cell31.setCellValue("��ҵ�����");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 27, 27));
		
		HSSFCell cell32 = row2.createCell(28);
		cell32.setCellValue("�����ʩ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 28, 28));
		
		HSSFCell cell33 = row2.createCell(29);
		cell33.setCellValue("������  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 29, 29));
		
		HSSFCell cell34 = row2.createCell(30);
		cell34.setCellValue("�Բ���");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 30, 30));
		
		HSSFCell cell35 = row2.createCell(31);
		cell35.setCellValue("�Լ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 31, 31));
		
		HSSFCell cell36 = row2.createCell(32);
		cell36.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 32, 32));
		
		HSSFCell cell37 = row2.createCell(33);
		cell37.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 33, 33));
		
		HSSFCell cell38 = row2.createCell(34);
		cell38.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 34, 34));
		
		HSSFCell cell39 = row2.createCell(35);
		cell39.setCellValue("�ܻ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 35, 35));
		
		HSSFCellStyle style = workbook.createCellStyle();//������ʽ
		HSSFFont font = workbook.createFont();//������ʽ
		
		//�ӱ߿�
//		style.setBorderBottom(BorderStyle.THIN);//�±߿� 
//		style.setBorderLeft(BorderStyle.THIN);//��߿� 
//		style.setBorderRight(BorderStyle.THIN);//�ұ߿� 
//		style.setBorderTop(BorderStyle.THIN); //�ϱ߿�
		
		//����
		style.setVerticalAlignment(VerticalAlignment.CENTER);// ��ֱ���У����¾��У�
		style.setAlignment(HorizontalAlignment.CENTER);// ���Ҿ���
		
		//��������
		font.setFontName("����_GB2312");//��������
		font.setFontHeightInPoints((short) 12);//����Ĵ�С
		font.setItalic(false);//�����Ƿ�Ϊб�� 
		font.setBold(true);//����Ӵ�
		style.setWrapText(true);//�Զ�����
		
		style.setFont(font);//������Ҫ�õ���������ʽ
		
		List<HSSFCell> list = new ArrayList<>();
		list.add(cell0);list.add(cell1);list.add(cell2);list.add(cell3);list.add(cell4);list.add(cell5);list.add(cell6);list.add(cell7);list.add(cell8);list.add(cell9);list.add(cell10);
		list.add(cell11);list.add(cell12);list.add(cell13);list.add(cell14);list.add(cell15);list.add(cell16);list.add(cell17);list.add(cell18);list.add(cell19);list.add(cell20);
		list.add(cell21);list.add(cell22);list.add(cell23);list.add(cell24);list.add(cell25);list.add(cell26); list.add(cell27); list.add(cell28);list.add(cell29);list.add(cell30);
		list.add(cell31);list.add(cell32);list.add(cell33);list.add(cell34);list.add(cell35);list.add(cell36);list.add(cell37);list.add(cell38);list.add(cell39);
		
		//��Ⱦ��Ԫ��
		for (HSSFCell hssfCell : list) {
			hssfCell.setCellStyle(style);
		}
		
		
		//OutputStream outputStream = response.getOutputStream();
		OutputStream os=new FileOutputStream("D://EXXXX4.xls");
		
		workbook.write(os);
		
		workbook.close();
		os.close();
		
		
	}

}
