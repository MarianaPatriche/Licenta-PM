package mariana.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import mariana.model.report.ReportCell;
import mariana.model.report.ReportCellText;
import mariana.model.report.ReportModel;
import mariana.model.report.ReportRow;
import mariana.model.report.ReportSheet;

/**
 * Created by mariana on 05.09.2016.
 */
@Service
public class CreateReportService {
	public static final String DEFAULT_FONT = "Omnes";
	public static final int DEFAULT_TITLE_ROW_NUMBER = 0;

	public Workbook generateReport(final ReportModel report) {
		Workbook workbook = new XSSFWorkbook();
		createSheet(workbook, report.getSheet());
		return workbook;
	}

	private void createSheet(final Workbook workbook, final ReportSheet reportSheet) {
		int rowNo;
		final CellStyle cellStyleHeader = getCellStyle(workbook, Boolean.TRUE);
		final CellStyle cellStyle = getCellStyle(workbook, Boolean.FALSE);
		final CellStyle cellStyleTitle = getCellStyleTitle(workbook);
		final CellStyle cellStyleRow = getRowStyle(workbook);

		final Sheet sheet = workbook.createSheet(reportSheet.getName());

		createSheetTitle(DEFAULT_TITLE_ROW_NUMBER, sheet, reportSheet.getTitle(), cellStyleTitle, workbook);
		rowNo = 2;
		setColumnsWidth(reportSheet, sheet);
		createSheetTitle(rowNo, sheet, "", cellStyleTitle, workbook);
		rowNo++;
		sheet.addMergedRegion(new CellRangeAddress(
				0, //first row (0-based)
				1, //last row  (0-based)
				0, //first column (0-based)
				reportSheet.getHeaders().getRowCells().size() - 1  //last column  (0-based)
		));

		createRow(rowNo, sheet, reportSheet.getHeaders(), cellStyleHeader, cellStyleRow, workbook);
		rowNo++;
		for (ReportRow reportRow : reportSheet.getRowList()) {
			createRow(rowNo, sheet, reportRow, cellStyle, cellStyleRow, workbook);
			rowNo++;
		}
	}

	private void setColumnsWidth(ReportSheet reportSheet, Sheet sheet) {
		int i = 0;
		for (ReportCell cell : reportSheet.getHeaders().getRowCells()) {
			sheet.setColumnWidth(i, cell.getWidth().getWidth());
			i++;
		}
	}

	private void createSheetTitle(final int rowNo, final Sheet sheet, final String title, final CellStyle cellStyle, final Workbook workbook) {
		Row row = sheet.createRow(rowNo);
		row.setHeightInPoints(25);
		createCellFromText(0, row, cellStyle, workbook.getCreationHelper(), title);
	}

	private void createCellFromText(final int column, final Row row, final CellStyle cellStyle, final CreationHelper creationHelper, final String text) {
		createCell(column, row, cellStyle, creationHelper.createRichTextString(text));
	}

	private void createCell(final int column, final Row row, final CellStyle cellStyle, final RichTextString text) {
		final Cell cell = row.createCell(column);
		cell.setCellValue(text);
		cell.setCellStyle(cellStyle);
	}

	private void createRow(final int rowNo, final Sheet sheet, final ReportRow reportRow, final CellStyle cellStyle, final CellStyle rowStyle, final Workbook workbook) {
		Row row = sheet.createRow(rowNo);
		row.setHeightInPoints(23);
		row.setRowStyle(rowStyle);
		int colNo = 0;
		for (ReportCell reportCell : reportRow.getRowCells()) {
			createCellFromReportCell(workbook, colNo, row, cellStyle, reportCell);
			colNo++;
		}
	}

	private void createCellFromReportCell(final Workbook workbook, final int column, final Row row, final CellStyle cellStyle, final ReportCell reportCell) {
		createCell(column, row, cellStyle, computeRichTextString(reportCell, workbook, cellStyle));
	}


	private RichTextString computeRichTextString(final ReportCell reportCell, final Workbook workbook, final CellStyle cellStyle) {
		final RichTextString richTextString = workbook.getCreationHelper().createRichTextString(reportCell.getCellText().getText());
		richTextString.applyFont(0, reportCell.getCellText().getText().length(), computeDinamicFont(workbook, reportCell.getCellText(), cellStyle));
		return richTextString;
	}

	private Font computeDinamicFont(final Workbook workbook,
									final ReportCellText cellText,
									final CellStyle cellStyle) {
		final short bold = cellText.getWeight().equals(ReportCellText.TextWeight.BOLD) ? Font.BOLDWEIGHT_BOLD : Font.BOLDWEIGHT_NORMAL;
		final short color = IndexedColors.BLACK.getIndex();
		final Font fontCell = workbook.getFontAt(cellStyle.getFontIndex());
		final Font font = findFont(workbook, bold, color, fontCell);
		return font != null ? font : getNewFont(workbook, bold, color, fontCell);
	}

	private Font findFont(final Workbook workbook,
						  final short bold,
						  final short color,
						  final Font fontCell) {
		return workbook.findFont(bold, color, fontCell.getFontHeight(), fontCell.getFontName(), fontCell.getItalic(), fontCell.getStrikeout(), fontCell.getTypeOffset(), fontCell.getUnderline());
	}

	private Font getNewFont(final Workbook workbook,
							final short bold,
							final short color,
							final Font fontCell) {
		final Font font = workbook.createFont();
		font.setBoldweight(bold);
		font.setColor(color);
		font.setFontHeight(fontCell.getFontHeight());
		font.setFontName(fontCell.getFontName());
		font.setItalic(fontCell.getItalic());
		font.setStrikeout(fontCell.getStrikeout());
		font.setTypeOffset(fontCell.getTypeOffset());
		font.setUnderline(fontCell.getUnderline());
		return font;
	}

	private CellStyle getCellStyle(final Workbook workbook, final boolean isHeader) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
		cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillForegroundColor(isHeader ? IndexedColors.AQUA.getIndex() : IndexedColors.WHITE.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName(DEFAULT_FONT);
		cellStyle.setFont(font);
		return cellStyle;
	}

	private CellStyle getRowStyle(final Workbook workbook) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		return cellStyle;
	}

	private CellStyle getCellStyleTitle(final Workbook workbook) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 20);
		font.setFontName(DEFAULT_FONT);
		font.setBold(true);
		cellStyle.setFont(font);
		return cellStyle;
	}
}
