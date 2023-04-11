import java.util.ArrayList;

import java.text.SimpleDateFormat;  
import java.util.Calendar; 
import java.text.ParseException;  


public class Tasks 
{
    public static ArrayList<Tasks> ArrofTasks = new ArrayList<Tasks>();

    public static ArrayList<String> ArrofNames = new ArrayList<String>(); //adjust the program to implement the arraylist names in it to allow for 2 dependent lists

    private String name, taskOutline;
    private boolean completed = false;
    
    private String startDate, endDate; //all dates are stored in the format of MM/DD/YYYY

    private int ETF; //Expected time to finish the tasks in days


    public Tasks(){}

    public Tasks(String name, String taskOutline, String startDate, int ETF, boolean completed)
    {
        this.name = name;
        this.taskOutline = taskOutline;
        this.startDate = startDate;
        endDate = "";
        this.ETF = ETF;
        this.completed = completed;
    }

    public String getName()
    {
        return name;
    }

    public String getTaskOutline()
    {
        return taskOutline;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public int getExpectedTime()
    {
        return ETF;
    }
    public boolean getCompleted(){
        return completed;}

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTaskOutline(String taskOutline)
    {
        this.taskOutline = taskOutline;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public void setCompleted(boolean completed){
           this.completed = completed;
    }

    public void setEndDate() //assumes the start date and the expected time to finish are correctly entered beforehand
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  

        Calendar cal = Calendar.getInstance();  

        try
        {  
           cal.setTime(sdf.parse(startDate));  

        }catch(ParseException e)
        {  
            e.printStackTrace();  
        }  

        cal.add(Calendar.DAY_OF_MONTH, ETF);  

        String result = sdf.format(cal.getTime());  

        this.endDate = result;
    }

    public void setExpectedTime(int ETF)
    {
        this.ETF = ETF;
    }

    
   
    
}
