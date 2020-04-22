package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Gantt extends PApplet

{
	private string displayName;
	private int start;
	private int end;
	
	public String  getDisplayName() {
		return displayName;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setStart(int newStart) {
		start = newStart;
	}
	
	public void setEnd(int newEnd) {
		end = newEnd;
	}
	
	public String toString()
	{
		return displayName + "\t" + startPosistion + "\t" + endPostion;
	}
	
	
}