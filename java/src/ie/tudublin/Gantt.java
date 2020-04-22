package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
	    Table taskTable = loadTable("tasks.csv","header");
        
		for(TableRow tr:taskTable.rows())
        {
            Task tempTaskStore = new Task(tr);
            tasks.add(tempTaskStore);
        }
	
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	public void drawLabledMarkers() {
		/*
		using a ruler heres the aprox distaces ratios of the screen:
		
		witdth:42
		highth:31
		each gap distace: 1
		top+bottom+right buffer:2
		left buffer 8
		text:midle is at 1 from top
		*/
		
		float border = width * 0.05f;
		float leftBorder = width * 0.2f;
		/*using 0.05f rather than 0.04761904761904761904761904761905
		like the math sugests cause im assuming it was a mesurment error
		and that indeded value was 0.05*/
		
		stroke(100,100,100);
		textAlign(CENTER,CENTER);
		for(int i = 1;i <= 30;i++)
		{
			float xCord = map(i,1,30,leftBorder,width - border);
			line(xCord,border,xCord,height - border);
			
			text(i,xCord,border/2);
		}
	}
	
	public void setup() 
	{
		//colorMode()
		loadTasks();
		printTasks();
	}
	
	public void printTasks()
	{
		for(Task t:tasks)
		{
			println(t);
		}
	}

	public void draw()
	{			
		background(0);
		drawLabledMarkers();
	}
}
