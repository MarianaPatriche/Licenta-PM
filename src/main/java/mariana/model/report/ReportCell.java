package mariana.model.report;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportCell {public static final int CHAR_WIDTH = 128;
	private final CellWidth width;
	private final ReportCellText cellText;

	public enum CellWidth {
		XSMALL(CHAR_WIDTH * 10),
		SMALL(CHAR_WIDTH * 30),
		MEDIUM(CHAR_WIDTH * 40),
		LARGE(CHAR_WIDTH * 50),
		XLARGE(CHAR_WIDTH * 60);
		private final int width;

		CellWidth(int width) {
			this.width = width;
		}

		public int getWidth() {
			return width;
		}
	}

	public ReportCell(final CellWidth width, final ReportCellText cellText) {
		this.width = width;
		this.cellText = cellText;
	}

	public static ReportCell of(final int text) {
		return ReportCell.of(ReportCellText.of("" + text));
	}

	public static ReportCell of(final String text) {
		return ReportCell.of(ReportCellText.of(text));
	}

	public static ReportCell of(final ReportCellText cellTexts) {
		return new ReportCell(CellWidth.MEDIUM, cellTexts);
	}

	public CellWidth getWidth() {
		return width;
	}

	public ReportCellText getCellText() {
		return cellText;
	}
}
