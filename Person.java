import java.util.Comparator;

public class Person implements Comparable<Person>, Comparator<Person>{
    private String name;
    private int taskComplete;
    private int estTaskTimeLeft;
    /**
     * Default constructor for Person class.
     */
    public Person(){}
    /**
     *Constructor for Person class.
     * @param name is the name of the person being added.
     * @param taskComplete is the number of  minutes of task work completed.
     * @param estTaskTimeLeft is the number of minutes of task work not yet completed.
     */
    public Person (String name, int taskComplete, int estTaskTimeLeft)
    {
        this.name = name;
        this.taskComplete = taskComplete;
        this.estTaskTimeLeft = estTaskTimeLeft;
    }

    /**
     * Getter Method for name of person.
     * @return the name of the person.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for the number of minute of task work completed.
     * @return the number of minutes of task work completed.
     */
    public int getTaskComplete()
    {
        return taskComplete;
    }

    /**
     * Getter method for estTaskTimeLeft.
     * @return the number of minutes of task work not yet completed.
     */
    public int getEstTaskTimeLeft()
    {
        return estTaskTimeLeft;
    }

    /**
     * Setter method for the name.
     * @param name the new name to be set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter method for taskComplete
     * @param taskComplete the new value for taskComplete to be set.
     */
    public void setTaskComplete(int taskComplete)
    {
        this.taskComplete = taskComplete;
    }

    /**
     * Setter Method estTaskTimeLeft
     * @param estTaskTimeLeft the new value for estTaskTimeLeft
     * to be set
     */
    public void setEstTaskTimeLeft(int estTaskTimeLeft)
    {
        this.estTaskTimeLeft = estTaskTimeLeft;
    }
    /**
     * compareTo compares the task completion of 2 person objects.
     * @param pele other person object to compare with.
     * @return 0 if the task amount is the same, 1 if the
     * other person is greater than this object and -1 otherwise.
     */
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