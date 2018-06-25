//package com.hivescm.tms.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.jxls.area.Area;
//import org.jxls.builder.AreaBuilder;
//import org.jxls.builder.xls.XlsCommentAreaBuilder;
//import org.jxls.command.CellDataUpdater;
//import org.jxls.common.CellData;
//import org.jxls.common.CellRef;
//import org.jxls.common.Context;
//import org.jxls.reader.ReaderBuilder;
//import org.jxls.reader.XLSReader;
//import org.jxls.transform.Transformer;
//import org.jxls.transform.poi.PoiTransformer;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.hivescm.common.exception.SystemException;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 使用jxls2 操作 excel 包括导出和导入
// *
// */
//@Slf4j
//public class ExcelUtil {
//	
//    static class CellRefUpdater implements CellDataUpdater{
//        @Override
//        public void updateCellData(CellData cellData, CellRef targetCell, Context context) {
//            if( cellData.isFormulaCell() && cellData.getFormula() != null ){
//                cellData.setEvaluationResult(cellData.getFormula().replaceAll("(?<=[A-Za-z])\\d", Integer.toString(targetCell.getRow()+1)));
//            }
//        }
//    }
//	/**
//     * 导出excel
//     * @param path        导出原始模板
//     * @param fileName    导出文件名称
//     * @param map         导出数据
//     * @param response    响应对象
//     * @throws Exception  抛出异常
//     */
//	public static void exportExcelUtil(String path, String fileName, Map<String,Object> map,HttpServletResponse response) throws Exception{
//		//设置响应头信息
//		response.setCharacterEncoding("utf-8");
//        response.setContentType("application/vnd.ms-excel");        
//        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
//        //获取输出流
//        OutputStream os = response.getOutputStream();
//        // 将输入流is写入文件输出流fos中       
//		InputStream is = new FileInputStream(path);
//		//将输入流转换位工作簿对象
//        Workbook workbook = WorkbookFactory.create(is);
//        //获取模板中的jx标签
//        Transformer transformer = PoiTransformer.createSxssfTransformer(workbook, 10, false);
//        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
//        //获取模板中药解析的区域
//        List<Area> xlsAreaList = areaBuilder.build();
//        Area xlsArea = xlsAreaList.get(0);
//        //创建Context对象并且添加要导出的数据
//        Context context = new Context();
//        context.putVar("cellRefUpdater", new CellRefUpdater());
//        context.putVar("list", map.get("list"));
//        xlsArea.applyAt(new CellRef("Result!A1"), context);
//        context.getConfig().setIsFormulaProcessingRequired(false); // with SXSSF you cannot use normal formula processing
//        workbook.setForceFormulaRecalculation(true);
//        workbook.setActiveSheet(1);        
//        ((PoiTransformer) transformer).getWorkbook().write(os);
//    }
//	/**
//	 * 
//	 * @param file
//	 * @param in
//	 * @return
//	 * @throws Exception
//	 */
//	public static Map<String,Object> importExcelUtil(MultipartFile file,InputStream in) throws Exception {
//		Map<String, Object> map = new HashMap<>();
//		//获取路径"D:/workspace-TMS/TMS/tms-address-server/target/classes/"
////		File configFile = new File(path);
////		InputStream xmlInputStream = new FileInputStream(configFile);//CommonUtils.class.getResourceAsStream(xmlConfig);
//		InputStream fileStream = null;
//    	try {
//			XLSReader reader = ReaderBuilder.buildFromXML(in);
//			fileStream = file.getInputStream();//CommonUtils.class.getResourceAsStream(dataFile);
//			List list = new ArrayList();
//			map.put("list", list);
//			log.info("Reading the data...");
//			reader.read(fileStream, map);
//			log.info("List size is " + list.size());
//		} catch (Exception e) {
//			log.error("导入excel文件异常:",e);
//			e.printStackTrace();
//			new SystemException(-1, "importExcelUtil--->导入excel文件异常");
//		}finally {
//			if(null!=in){
//				in.close();
//			}
//			if(null!=fileStream){
//				fileStream.close();
//			}
//		}
//		return map;		
//	}
//	
//}
