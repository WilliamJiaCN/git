package com.hivescm.tms.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * <p>Title: ExcelUtil</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-16-11:56
 */

public class ExcelUtils {

    public static final String HEADERINFO = "headInfo";
    public static final String DATAINFON = "dataInfo";

    /**
     *
     * @Title: writeExcel
     * @Description: TODO(导出Excel表)
     * @param pathname
     *            :导出Excel表的文件路径
     * @param map
     *            ：封装需要导出的数据(HEADERINFO封装表头信息，DATAINFON：封装要导出的数据信息,此处需要使用TreeMap
     *            ) 例如： map.put(ExcelUtil.HEADERINFO,List<String> headList);
     *            map.put(ExcelUtil.DATAINFON,List<TreeMap<String,Object>>
     *            dataList);
     * @param wb
     * @throws IOException
     */
    public static void writeExcel(String pathname, Map<String, Object> map,
                                  Workbook wb) throws IOException {

        if (null != map && null != pathname) {
            List<Object> headList = (List<Object>) map.get(ExcelUtils.HEADERINFO);
            List<TreeMap<String, Object>> dataList = (List<TreeMap<String, Object>>) map.get(ExcelUtils.DATAINFON);
            CellStyle style = getCellStyle(wb);
            Sheet sheet = wb.createSheet();// 在文档对象中创建一个表单..默认是表单名字是Sheet0、Sheet1....


            /**
             * 设置Excel表的第一行即表头
             */
            Row row = sheet.createRow(0);
            for (int i = 0; i < headList.size(); i++) {
                Cell headCell = row.createCell(i);
                headCell.setCellType(Cell.CELL_TYPE_STRING);// 设置这个单元格的数据的类型,是文本类型还是数字类型
                headCell.setCellStyle(style);// 设置表头样式
                headCell.setCellValue(String.valueOf(headList.get(i)));// 给这个单元格设置值
            }


            for (int i = 0; i < dataList.size(); i++) {
                Row rowdata = sheet.createRow(i + 1);// 创建数据行
                TreeMap<String, Object> mapdata = dataList.get(i);
                Iterator it = mapdata.keySet().iterator();
                int j = 0;
                while (it.hasNext()) {
                    String strdata = String.valueOf(mapdata.get(it.next()));
                    Cell celldata = rowdata.createCell(j);// 在一行中创建某列..
                    if(null == celldata) continue;	// 为空时，下一列
                    celldata.setCellType(Cell.CELL_TYPE_STRING);
                    celldata.setCellValue(strdata);
                    j++;
                }
            }
            // 文件流
            File file = new File(pathname);
            OutputStream os = new FileOutputStream(file);
            os.flush();
            wb.write(os);
            os.close();
        }
    }



    /**
     *
     * @Title: getCellStyle
     * @Description: TODO（设置表头样式）
     * @param wb
     * @return
     */
    public static CellStyle getCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        style.setFillForegroundColor(HSSFColor.LIME.index);// 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.SOLID_FOREGROUND);// 让单元格居中
        // style.setWrapText(true);//设置自动换行
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) throws IOException {
        export();
    }

    public static void export() throws IOException {
        Workbook wb = new XSSFWorkbook();// 创建一个新的excel的文档对象
        Map<String, Object> map = new HashMap<>();
        List headList = new ArrayList();// 表头数据
        headList.add("运单号");
        headList.add("库存网点");
        headList.add("仓库名称");
        headList.add("库区");
        headList.add("在库时长");
        headList.add("入库时间");
        headList.add("货物名称");
        headList.add("在库件数");
        headList.add("在库重量");
        headList.add("在库体积");
        headList.add("锁定件数");
        headList.add("锁定重量");
        headList.add("锁定体积");
        headList.add("发货网点");
        headList.add("目的地");
        headList.add("目的网点");
        headList.add("类型");
        headList.add("备注");
        headList.add("业务日期");
        headList.add("录单时间");
        headList.add("预计到达日期");
        headList.add("录单员");
        headList.add("转移说明");
        headList.add("转移人");
        headList.add("转移时间");
        List dataList = new ArrayList();// 表格内的数据

        for (int i = 0; i < 15; i++) {
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.put("m1", "2013-10-" + i + 1);
            treeMap.put("m2", "2013-11-" + i + 1);
            treeMap.put("m3", "20124" + i + 1);
            treeMap.put("m4", 23.5 + i + 1);
            treeMap.put("m5", "张三_" + i);
            dataList.add(treeMap);
        }
        map.put(ExcelUtils.HEADERINFO, headList);
        map.put(ExcelUtils.DATAINFON, dataList);
        writeExcel("在库库存.xlsx",map,wb);
    }
}
