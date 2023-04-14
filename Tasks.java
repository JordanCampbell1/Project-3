import java.util.ArrayList;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;



public class Tasks 
{
    public static ArrayList<Tasks> ArrofTasks = new ArrayList<Tasks>();

    public static ArrayList<Person> ArrofNames = new ArrayList<Person>(); //adjust the program to implement the arraylist names in it to allow for 2 dependent lists
    private String name, taskOutline,endTime;
    private boolean completed;
    LocalTime startTime;

    private int ETF; //Expected time to finish the tasks in days


    public Tasks(){}

    public Tasks(String name, String taskOutline, int ETF)
    {
        this.name = name;
        this.taskOutline = taskOutline;
        this.startTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.ETF = ETF;
        setEndTime();
        this.completed = false;
    }

    public Tasks(String name, String taskOutline, int ETF, boolean completed)
    {
        this.name = name;
        this.taskOutline = taskOutline;
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

    public String getStartTime()
    {
        return ""+startTime;
    }

    public int getExpectedTime()
    {
        return ETF;
    }
    public String getEndTime(){
        return endTime;
    }

    public boolean getCompleted()
    {
        return completed;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTaskOutline(String taskOutline)
    {
        this.taskOutline = taskOutline;
    }

    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public void setEndTime() //assumes the start date and the expected time to finish are correctly entered beforehand
    {
        this.endTime = ""+this.startTime.plusMinutes(ETF);
    }

    public void setExpectedTime(int ETF)
    {
        this.ETF = ETF;
    }

    public static int ratioOfTasksCompleted()
    {
        if(ArrofTasks.size() == 0)
        {
            return 0;
        }

        int count = 0;

        for(Tasks t : ArrofTasks)
        {
            if(t.getCompleted() == true)
            {
                count++;
            }
        }

        return (int) count / ArrofTasks.size(); 
    }
   
    
}
