package ie.tudublin;

import java.util.ArrayList;

import java.lang.Math;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {	
	ArrayList<Task> tasks = new ArrayList<Task>();
	float leftBorder;
	float border;
	boolean startOrEnd = false;
	Task nullTask = new Task();
	Task taskToEdit = nullTask;
	
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
		
		float rectYcord = width * 0.075f;
		float rectHiegth = width * 0.05f;
		
		println("Mouse pressed");
		for(int i = 0;i < tasks.size();i++) {
			Task curentTask = tasks.get(i);
			if(mouseInBoxAt(map(curentTask.getStart(),1,30,leftBorder,width - border),rectYcord + (rectHiegth * i)))
			{
				println("mouseInBoxAt returned true");
				startOrEnd = true;
				taskToEdit = curentTask;
				i = tasks.size();//this is janky but works
			}
			else if(mouseInBoxAt(map(curentTask.getEnd() - 1,1,30,leftBorder,width - border),rectYcord + (rectHiegth * i)))
			{
				println("mouseInBoxAt returned true");
				startOrEnd = false;
				taskToEdit = curentTask;
				i = tasks.size();//this is janky but works
			}
			else
			{
				taskToEdit = nullTask;
			}
		}
	}

	public void mouseDragged() {
		println("Mouse dragged");
		if(!(taskToEdit == nullTask))
		{
			if(startOrEnd)//if where editing the start
			{
				//println("start");
				taskToEdit.setStart(
				Math.min((int)(taskToEdit.getEnd() - 1),(int)Math.max(1,(int)(map(mouseX,leftBorder,width - border,1,30))))
				);
			}
			else//if where editing the end
			{
				//println("end");
				taskToEdit.setEnd(
				Math.max((int)(taskToEdit.getStart() + 1),(int)Math.min(30,(int)(map(mouseX,leftBorder,width - border,1,30))))
				);
			}
		}
	}
	
	public boolean mouseInBoxAt(float X, float Y){
		float boxW = map(2,1,30,leftBorder,width - border) - map(1,1,30,leftBorder,width - border);
		float boxH = width * 0.05f;
		/*println("mouseInBoxAt called--------------------------------------------------------");
		println("mouseX>X " + mouseX + " > "+ X + " :" + (mouseX>X) + "");
		println("mouseX<(X+boxW) " + mouseX + " < "+ (X+boxW) + " :" + (mouseX < (X+boxW))+ "");
		println("mouseX>Y " + mouseY + " > "+ Y + " :" + (mouseY>Y) + "");
		println("mouseY<(X+boxH) " + mouseY + " < "+ (Y+boxH) + " :" + (mouseY < (Y+boxH)) + "");*/
		if((mouseX>X)&&(mouseX<(X+boxW))&&(mouseY>Y)&&(mouseY<Y+boxH))
		{
			return true;
		}
		return false;
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
			fill(map(i,0,tasks.size(),0,100),100,100);
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
