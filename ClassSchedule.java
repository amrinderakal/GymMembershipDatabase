package com.example.project3;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 This is the ClassSchedule class that serves as the data structure
 to store fitness classes. This class supports adding, removing, and printing
 fitness classes in the database.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class ClassSchedule {
    public static final int NOT_FOUND = -1;
    public static final int INCREMENT = 4;
    public static final int INITIAL_SIZE = 0;
    public static final int INITIAL_CAPACITY = 4;
    public static FitnessClass [] classes;
    private int numClasses;


    /**
     Initializes a Fitness Class array with an initial capacity of 4.
     Sets the instance variable numClasses to 0, as there are currently
     no classes in the array.
     */
    public ClassSchedule ()
    {
        classes = new FitnessClass[INITIAL_CAPACITY];
        numClasses = INITIAL_SIZE;
    }

    /**
     Checks if the classSchedule is full
     @return true if classSchedule is full, false otherwise
     */
    private boolean isFull()
    {
        if (numClasses == classes.length)
        {
            return true;
        }
        return false;
    }

    /**
     Increases the capacity of class schedule array by set increment
     */
    private void grow() {
        FitnessClass [] temp = new FitnessClass[classes.length + INCREMENT];
        for (int i = 0; i < classes.length; i++)
        {
            temp[i] = classes[i];
        }
        classes = temp;
    }

    /**
     Returns index of fitness class in class Schedule
     * @param className - Name of Fitness Class
     * @param instructor - Instructor of Fitness Class
     * @param locationStr - Location of Fitness Class
     * @return index of fitness class if found, -1 otherwise
     */
    public int find (String className, String instructor, String locationStr)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if (classes[i].getName().equalsIgnoreCase(className) && classes[i].getInstructor().equalsIgnoreCase(instructor) && classes[i].getLocation().name().equalsIgnoreCase(locationStr))
            {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     Checks if there are any duplicates of given fitness class in the class schedule
     * @param fitnessClass - to check for
     * @return true if there are duplicates, false otherwise
     */
    public boolean checkDuplicate(FitnessClass fitnessClass)
    {
        for(int i = 0; i < classes.length; i++)
        {
            if (classes[i] != null)
            {
                if (classes[i].equals(fitnessClass))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     Checks if given class name exists in the class schedule
     * @param className - to check for
     * @return true if yes, false otherwise
     */
    public boolean checkClass (String className)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if(classes[i].getName().equalsIgnoreCase(className))
            {
                return true;
            }
        }
        return false;
    }

    /**
     Checks if given class instructor exists in the class schedule
     * @param instructor - to check for
     * @return true if yes, false otherwise
     */
    public boolean checkInstructor (String instructor)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if(classes[i].getInstructor().equalsIgnoreCase(instructor))
            {
                return true;
            }
        }
        return false;
    }

    /**
     Checks if fitness class by instructor exists at given location
     * @param className - name of Fitness class
     * @param instructor - Instructor of fitness class
     * @param location - Location to check
     * @return true if yes, false otherwise
     */
    public boolean checkClassAtLocation (String className, String instructor, Location location)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if((classes[i].getName().equalsIgnoreCase(className)) && (classes[i].getInstructor().equalsIgnoreCase(instructor))
                    && (classes[i].getLocation().equals(location)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     Adds a fitness class to the class schedule
     * @param fitnessClass - to add
     */
    public void add(FitnessClass fitnessClass)
    {
        if(!checkDuplicate(fitnessClass)) {
            if (isFull()) {
                grow();
                for (int i = 0; i < classes.length; i++) {
                    if (classes[i] == null) {
                        classes[i] = fitnessClass;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < classes.length; i++) {
                    if (classes[i] == null) {
                        classes[i] = fitnessClass;
                        break;
                    }
                }
            }
            numClasses++;
        }
    }

    /**
     Checks if member is checked into any class with the same time as fitness class
     * @param member - to check for
     * @param fitnessClass - to check for
     * @return true if yes, false otherwise
     */
    public boolean checkTimeConflict (Member member, FitnessClass fitnessClass)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if (classes[i].checkTimeMember(member, fitnessClass.getTime()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     Loads fitness classes into class schedule from given file
     * @param file - of Fitness classes
     * @throws FileNotFoundException
     */
    public void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (!(line.isBlank()))
            {
                String [] lineSplit = line.split(" ");
                String name = lineSplit[0];
                String instructor = lineSplit[1];
                String time = lineSplit[2].substring(0, 1).toUpperCase() + lineSplit[2].substring(1);
                String location = lineSplit[3];
                final Location location1 = Location.valueOf(location.toUpperCase());
                Time time1 = Time.valueOf(time);
                FitnessClass fitnessClass = new FitnessClass(name, instructor, time1, location1);
                add(fitnessClass);
            }
        }
    }

    /**
     Returns the fitness class schedule as a String
     * @return String of fitness class schedule
     */
    public String print()
    {
        String res = "";
        if(numClasses > 0)
        {
            res += ("-Fitness classes-\n");
            for (int i = 0; i < numClasses; i++)
            {
                res += (classes[i].getName().toUpperCase() + " - " + classes[i].getInstructor().toUpperCase()
                        + ", " + classes[i].getTime().toString() + ", " + classes[i].getLocation().name() + "\n");
                if ((classes[i].checkInList.size() > 0) && (classes[i].guestCheckInList.size() == 0))
                {
                    res += (classes[i].printCheckInList());
                }
                else if ((classes[i].checkInList.size() == 0) && (classes[i].guestCheckInList.size() > 0))
                {
                    res += (classes[i].printGuestCheckInList());
                }
                else if ((classes[i].checkInList.size() > 0) && (classes[i].guestCheckInList.size() > 0)) {
                    res += (classes[i].printCheckInList());
                    res += (classes[i].printGuestCheckInList());
                }
            }
            res += ("-end of class list-\n");
            return res;
        }
        else {
            return ("Fitness class schedule is empty.\n");
        }
    }

}
