package mariana.model.report;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportRow {
	private final List<ReportCell> rowCells;

	public ReportRow(List<ReportCell> rowCells) {
		this.rowCells = rowCells;
	}

	public static ReportRow of(final  ReportCell... cells){
		return new ReportRow(Arrays.asList(cells));
	}

	public List<ReportCell> getRowCells() {
		return rowCells;
	}

	@Override
	public String toString() {
		return "ReportRow{" +
				"rowCells=" + rowCells +
				'}';
	}

}
