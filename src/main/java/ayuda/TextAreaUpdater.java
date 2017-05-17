package ayuda;

import java.awt.TextArea;
import java.util.List;

public class TextAreaUpdater implements Runnable {
	
	private TextArea textArea;
	private List<String> text;
	
	public TextAreaUpdater(TextArea textArea, List<String> text) {
		super();
		this.textArea = textArea;
		this.text = text;
	}

	public void run() {
		textArea.setText("");
		for(String textLine : text){
			textArea.append(textLine.concat("\n"));
		}
	}

}
