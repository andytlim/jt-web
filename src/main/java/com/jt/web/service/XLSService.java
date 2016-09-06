package com.jt.web.service;

public class XLSService {
	
//	public List<CNModel> readXLS(InputStream inputStream) {
//		List<CNModel> cnList = new LinkedList<CNModel>();
//		
//		try {
//		    POIFSFileSystem fs = new POIFSFileSystem(inputStream);
//		    HSSFWorkbook wb = new HSSFWorkbook(fs);
//		    HSSFSheet sheet = wb.getSheetAt(0);
//		    HSSFRow row;
//
//		    for(int r = 1; r < 6; r++) {
//		        row = sheet.getRow(r);
//		        if(row != null) {
//		        	CNModel cn = new CNModel();
//		        	
//		        	cn.fileName = row.getCell(0).getStringCellValue();
//		        	switch (row.getCell(1).getCellType()) {
//		        		case Cell.CELL_TYPE_NUMERIC:
//		        			cn.planNumber = String.valueOf((int)row.getCell(1).getNumericCellValue());
//		        			break;
//		        		case Cell.CELL_TYPE_STRING:
//		        			cn.planNumber = row.getCell(1).getStringCellValue();
//		        			break;
//		        	}
//		        	switch (row.getCell(2).getCellType()) {
//		        		case Cell.CELL_TYPE_NUMERIC:
//		        			cn.effectiveDate = String.valueOf(row.getCell(2).getNumericCellValue());
//		        			break;
//		        		case Cell.CELL_TYPE_STRING:
//		        			cn.effectiveDate = row.getCell(2).getStringCellValue();
//		        			break;
//		        	}
//		        	cn.noticeName = row.getCell(3).getStringCellValue();
//		        	
//		        	if (!cn.fileName.isEmpty())
//		        		cnList.add(cn);
//		        }
//		    }
//		} catch(Exception e) {
//		    System.out.println("XLSService.readXLS() error: " + e);
//		}
//		
//		return cnList;
//	}
}
