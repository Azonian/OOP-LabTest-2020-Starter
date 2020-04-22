package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	
	ArrayList<Task> tasks = new ArrayList<Task>();
	float leftBorder;
	float border;
	
	public void settings() {
		size(800, 600);
	}

	public void loadTasks() {
	    Table taskTable = loadTable("tasks.csv","header");
        
		for(TableRow tr:taskTable.rows())
        {
            Task tempTaskStore = new Task(tr);
            tasks.add(tempTaskStore);
        }
	
	}
	
	public void mousePressed() {
		println("Mouse pressed");	
	}

	public void mouseDragged() {
		println("Mouse dragged");
	}

	public void displayLabledMarkers() {
		/*
		using a ruler heres the aprox distaces ratios of the screen:
		
		witdth:42
		highth:31
		each gap distace: 1
		top+bottom+right buffer:2
		left buffer 8
		text:midle is at 1 from top
		*/
		
		
		/*using 0.05f rather than 0.04761904761904761904761904761905
		like the math sugests cause im assuming it was a mesurment error
		and that indeded value was 0.05*/
		
		
		stroke(0,0,100);
		fill(0,0,100);
		textAlign(CENTER,CENTER);
		for(int i = 1;i <= 30;i++)
		{
			float xCord = map(i,1,30,leftBorder,width - border);
			line(xCord,border,xCord,height - border);
			
			text(i,xCord,border/2);
		}
	}

	public void displayTasks() {//this is a mess clean it up
		//maybe break into multiple functions to clean up mabye "displayTaskName" and "displayTaskRect"?
		
		//rename these varibles to what they actulaty do
		float textXcord = width * 0.1f;
		float textYCord = width * 0.1f;
		float textYOffset = width * 0.05f;
		
		float rectYcord = width * 0.075f;
		float rectHiegth = width * 0.05f;
		
		float rectRaius = 5;
		textAlign(LEFT,CENTER);

		for(int i = 0; i < tasks.size();i++)
		{
			Task currentTask = tasks.get(i);
			//stroke(0,100,0);
			fill(0,0,100);
			text(currentTask.getDisplayName(),textXcord,textYCord + i * textYOffset); //display task text on left side of marker lines
			
			noStroke();
			fill(100,map(i,0,tasks.size(),0,100),100);
			//VV this is all a disaster zone clean it
			rect(map(currentTask.getStart(),1,30,leftBorder,width - border),
			rectYcord + (rectHiegth * i),
			map(currentTask.getEnd(),1,30,leftBorder,width - border) - map(currentTask.getStart(),1,30,leftBorder,width - border),
			rectHiegth,
			rectRaius);
		}
	}
	
	public void setup() 
	{
		border = width * 0.05f;
		leftBorder = width * 0.2f;
		
		colorMode(HSB, 100);
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
		displayLabledMarkers();
		displayTasks();
	}
}
