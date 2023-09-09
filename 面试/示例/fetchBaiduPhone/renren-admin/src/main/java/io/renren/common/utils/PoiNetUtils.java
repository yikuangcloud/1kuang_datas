package io.renren.common.utils;



import io.renren.modules.sys.entity.BaiduGetinfoEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import java.util.List;
public class PoiNetUtils {
    /**
     *    生成对应excel
     * @param excelName 文件名
     * @param dataList  数据集
     * @return  工作簿
     */
    public static HSSFWorkbook export(String excelName, List<BaiduGetinfoEntity> dataList) {
        HSSFWorkbook wb = null;
        try {
            // 第一步，创建一个webbook，对应一个Excel文件
            wb = new HSSFWorkbook();
            //生成一个表格
            HSSFSheet sheet = wb.createSheet(excelName);
            // 第三步，在sheet中添加表头第0行
            HSSFRow row0 = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
            HSSFCell cell1 = row0.createCell(0);
            cell1.setCellValue("标题");
            cell1.setCellStyle(style);
            HSSFCell cell2 = row0.createCell(1);
            cell2.setCellValue("链接");
            cell2.setCellStyle(style);
            //第二行开始加载数据
            for (int i = 0;i<dataList.size();i++){
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(dataList.get(i).getTitle());
                row.createCell(1).setCellValue(dataList.get(i).getInfoUrl());
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return wb;
    }



    /**
     *    生成对应excel-联系方式版本
     * @param excelName 文件名
     * @param dataList  数据集
     * @return  工作簿
     */
    public static HSSFWorkbook exportContact(String excelName, List<BaiduGetinfoEntity> dataList) {
        HSSFWorkbook wb = null;
        try {
            // 第一步，创建一个webbook，对应一个Excel文件
            wb = new HSSFWorkbook();
            //生成一个表格
            HSSFSheet sheet = wb.createSheet(excelName);
            // 第三步，在sheet中添加表头第0行
            HSSFRow row0 = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
            HSSFCell cell1 = row0.createCell(0);
            cell1.setCellValue("链接标题");
            cell1.setCellStyle(style);
            HSSFCell cell2 = row0.createCell(1);
            cell2.setCellValue("链接");
            cell2.setCellStyle(style);
            HSSFCell cell3 = row0.createCell(2);
            cell3.setCellValue("号码");
            cell3.setCellStyle(style);
            HSSFCell cell4 = row0.createCell(3);
            cell4.setCellValue("公司名称");
            cell4.setCellStyle(style);

            HSSFCell cell5 = row0.createCell(4);
            cell5.setCellValue("地址");
            cell5.setCellStyle(style);

            HSSFCell cell6 = row0.createCell(5);
            cell6.setCellValue("联系信息");
            cell6.setCellStyle(style);

            //第二行开始加载数据
            for (int i = 0;i<dataList.size();i++){
                HSSFRow row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(dataList.get(i).getTitle());
                row.createCell(1).setCellValue(dataList.get(i).getInfoUrl());
                row.createCell(2).setCellValue(dataList.get(i).getTel());
                row.createCell(3).setCellValue(dataList.get(i).getCompany());
                row.createCell(4).setCellValue(dataList.get(i).getAddress());
                row.createCell(5).setCellValue(dataList.get(i).getContact());
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return wb;
    }
}


