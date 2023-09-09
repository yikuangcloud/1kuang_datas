package com.ruoyi.project.datav.util;

import com.ruoyi.project.datav.util.readexcel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.project.datav.util.readexcel.ReadBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadBarpieExcel;
import com.ruoyi.project.datav.util.readexcel.ReadBoxLineExcel;
import com.ruoyi.project.datav.util.readexcel.ReadCircleExcel;
import com.ruoyi.project.datav.util.readexcel.ReadColorBlockExcel;
import com.ruoyi.project.datav.util.readexcel.ReadDoubleBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadDoubleBarTimeExcel;
import com.ruoyi.project.datav.util.readexcel.ReadDoubleDirectionBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadDoubleyAxisExcel;
import com.ruoyi.project.datav.util.readexcel.ReadFunnelExcel;
import com.ruoyi.project.datav.util.readexcel.ReadKnowledgeExcel;
import com.ruoyi.project.datav.util.readexcel.ReadLineExcel;
import com.ruoyi.project.datav.util.readexcel.ReadLinebarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadMapExcel;
import com.ruoyi.project.datav.util.readexcel.ReadMapbarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadParalleBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadPieExcel;
import com.ruoyi.project.datav.util.readexcel.ReadPieMoreExcel;
import com.ruoyi.project.datav.util.readexcel.ReadPielineExcel;
import com.ruoyi.project.datav.util.readexcel.ReadProgressExcel;
import com.ruoyi.project.datav.util.readexcel.ReadRadarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadRainExcel;
import com.ruoyi.project.datav.util.readexcel.ReadRingPieExcel;
import com.ruoyi.project.datav.util.readexcel.ReadScatterExcel;
import com.ruoyi.project.datav.util.readexcel.ReadShadowLineExcel;
import com.ruoyi.project.datav.util.readexcel.ReadSingleColumnExcel;
import com.ruoyi.project.datav.util.readexcel.ReadStackedBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadStaggeredLabelExcel;
import com.ruoyi.project.datav.util.readexcel.ReadTableExcel;
import com.ruoyi.project.datav.util.readexcel.ReadThreeDBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadThreeDMapExcel;
import com.ruoyi.project.datav.util.readexcel.ReadTopBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadWordCloudExcel;
import com.ruoyi.project.datav.util.readexcel.ReadPictorialBarExcel;
import com.ruoyi.project.datav.util.readexcel.ReadCustomThreeDBarExcel;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: FormAnalysisUtil
 * @Description: 表格模板解析工具类
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/6/15 14:19
 */

public class FormAnalysisUtil {

    public static String readFile(String chartType, MultipartFile file) {

        String excelData = null;

        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        try {
            //判断什么类型文件
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(0);

        //基本柱状图
        if (chartType.equals("bar")) {
            excelData = ReadBarExcel.read(sheet);
        }
        //堆叠图
        else if(chartType.equals("stackedBar")){
        	excelData = ReadStackedBarExcel.read(sheet);
        }
        //交错正负轴标签
        else if(chartType.equals("staggeredLabel")){
        	excelData = ReadStaggeredLabelExcel.read(sheet);
        }
        //双柱状图
        else if(chartType.equals("doublebar")){
        	excelData = ReadDoubleBarExcel.read(sheet);
        }
        //排行榜
        else if(chartType.equals("topbar")){
        	excelData = ReadTopBarExcel.read(sheet);
        }
        //水平柱图
        else if(chartType.equals("parallebar")){
        	excelData = ReadParalleBarExcel.read(sheet);
        }
        //双Y轴柱图
        else if(chartType.equals("doubleyAxis")){
        	excelData = ReadDoubleyAxisExcel.read(sheet);
        }
        //双向柱形图
        else if(chartType.equals("doubleDirectionBar")){
        	excelData = ReadDoubleDirectionBarExcel.read(sheet);
        }
        //动态双向柱状图
        else if(chartType.equals("doublebartime")){
        	excelData = ReadDoubleBarTimeExcel.read(sheet);
        }
        //单柱图
        else if(chartType.equals("singleColumn")){
        	excelData = ReadSingleColumnExcel.read(sheet);
        }
        //伪立体柱状图
        else if(chartType.equals("threeDBar")){
        	excelData = ReadThreeDBarExcel.read(sheet);
        }
        //比例柱状图
        else if(chartType.equals("rateBar")){
            excelData = ReadRateBarExcel.read(sheet);
        }
        //散点图
        else if(chartType.equals("scatter")){
        	excelData = ReadScatterExcel.read(sheet);
        }
        //雷达图
        else if(chartType.equals("radar")){
        	excelData = ReadRadarExcel.read(sheet);
        }
        //饼图折线图
        else if(chartType.equals("pieline")){
        	excelData = ReadPielineExcel.read(sheet);
        }
        //柱状图饼图
        else if(chartType.equals("barpie")){
        	excelData = ReadBarpieExcel.read(sheet);
        }
        //折线图柱状图
        else if(chartType.equals("linebar")){
        	excelData = ReadLinebarExcel.read(sheet);
        }
        //地图柱状图
        else if(chartType.equals("mapbar")){
        	excelData = ReadMapbarExcel.read(sheet);
        }
        //3D地图
        else if(chartType.equals("threedMap")){
        	excelData = ReadThreeDMapExcel.read(sheet);
        }
        //关系图
        else if(chartType.equals("knowledge")){
        	excelData = ReadKnowledgeExcel.read(sheet);
        }
        //颜色块
        else if(chartType.equals("colorBlock")){
        	excelData = ReadColorBlockExcel.read(sheet);
        }
        //字符云
        else if(chartType.equals("wordcloud")){
        	excelData = ReadWordCloudExcel.read(sheet);
        }
        //分页表格、滚动表格、排行表格
        else if(chartType.equals("taBle") || chartType.equals("tablerotation") || chartType.equals("tabletop")){
        	excelData = ReadTableExcel.read(sheet);
        }
        //折线图
        else if(chartType.equals("line")){
        	excelData = ReadLineExcel.read(sheet);
        }
        //阴影折线图
        else if(chartType.equals("shadowLine")){
        	excelData = ReadShadowLineExcel.read(sheet);
        }
        //雨量流量关系图
        else if(chartType.equals("rain")){
        	excelData = ReadRainExcel.read(sheet);
        }
        //饼图
        else if(chartType.equals("pie")){
        	excelData = ReadPieExcel.read(sheet);
        }
        //环饼图
        else if(chartType.equals("ringPie")){
        	excelData = ReadRingPieExcel.read(sheet);
        }
        //漏斗图
        else if(chartType.equals("funnel")){
        	excelData = ReadFunnelExcel.read(sheet);
        }
        //环形图
        else if(chartType.equals("circle")){
        	excelData = ReadCircleExcel.read(sheet);
        }
        //多环形图
        else if(chartType.equals("pieMore")){
        	excelData = ReadPieMoreExcel.read(sheet);
        }
        //进度图表
        else if(chartType.equals("progress")){
        	excelData = ReadProgressExcel.read(sheet);
        }
        //地图
        else if(chartType.equals("map")){
        	excelData = ReadMapExcel.read(sheet);
        }
        //气泡图
        else if(chartType.equals("pops")){
        	excelData = ReadMapExcel.read(sheet);
        }
        //箱线图
        else if(chartType.equals("boxline")){
        	excelData = ReadBoxLineExcel.read(sheet);
        }
        //象形柱图
        else if(chartType.equals("pictorialBar")){
            excelData = ReadPictorialBarExcel.read(sheet);
        }
        //横向立体图
        else if (chartType.equals("CustomThreeDBar")){
            excelData = ReadCustomThreeDBarExcel.read(sheet);
        }
        //坐标系热力图
        else if (chartType.equals("heatMap")){
            excelData = ReadHeatMapExcel.read(sheet);
        }
        
        return excelData;
    }

}
