import java.util.Comparator;

public class Person implements Comparable<Person>, Comparator<Person>{
    private String name;
    private int taskComplete;
    private int estTaskTimeLeft;

    public Person(){}

    public Person (String name, int taskComplete, int estTaskTimeLeft)
    {
        this.name = name;
        this.taskComplete = taskComplete;
        this.estTaskTimeLeft = estTaskTimeLeft;
    }

    public String getName()
    {
        return name;
    }

    public int getTaskComplete()
    {
        return taskComplete;
    }

    public int getEstTaskTimeLeft()
    {
        return estTaskTimeLeft;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTaskComplete(int taskComplete)
    {
        this.taskComplete = taskComplete;
    }

    public void setEstTaskTimeLeft(int estTaskTimeLeft)
    {
        this.estTaskTimeLeft = estTaskTimeLeft;
    }

    public int compareTo(Person pele){
        if(this.getTaskComplete() == pele.getTaskComplete()){
            return 0;
        }
        
        else{
            if(this.getTaskComplete() > pele.getTaskComplete())
            {  
            return -1;
            }
        } 
        return 1;
    }
    
    public int compare(Person o1, Person o2) {
        return (o2.getEstTaskTimeLeft() - o1.getEstTaskTimeLeft());

    }

}