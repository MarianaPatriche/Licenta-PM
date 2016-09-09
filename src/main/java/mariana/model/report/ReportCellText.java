package mariana.model.report;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportCellText {
	private final String text;
	private final TextColor color;
	private final TextWeight weight;

	public enum TextColor {
		BLACK
	}

	public enum TextWeight {
		NORMAL, BOLD
	}

	public ReportCellText(String text, TextColor color, TextWeight weight){
		this.text = text != null ? text : "";
		this.color = color;
		this.weight = weight;
	}

	public static ReportCellText of(final String text) {
		return new ReportCellText(text, TextColor.BLACK, TextWeight.NORMAL);
	}

	public static ReportCellText of(final String text, boolean bold) {
		return new ReportCellText(text, TextColor.BLACK, bold ? TextWeight.BOLD : TextWeight.NORMAL);
	}

	public String getText() {
		return text;
	}

	public TextColor getColor() {
		return color;
	}

	public TextWeight getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "ReportCellText{" +
				"text='" + text + '\'' +
				", color=" + color +
				", weight=" + weight +
				'}';
	}

}
