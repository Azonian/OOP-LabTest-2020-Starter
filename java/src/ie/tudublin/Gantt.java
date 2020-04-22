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
	    Table taskTable =  ("tasks.csv","header");
        
		
		/*for(TableRow tr:taskTable.rows())
        {
            Task tempTaskStore = new Task(tr);
            tasks.add(tempTaskStore);
        }*/
	
	}

	public void printTasks()
	{
		
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks()
	}
	
	public void draw()
	{			
		background(0);
	}
}
