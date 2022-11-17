package com.example.project3;
import java.util.ArrayList;
/**
 This is the FitnessClass class, which supports creating a new
 Fitness Class object. Each fitness class has a Name, instructor,
 time, location, a check in list, and a guest check in list.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class FitnessClass {

    private String instructor;
    private Time time;
    private String name;
    private Location location;
    public ArrayList<Member> checkInList = new ArrayList<Member>(0);

    public ArrayList<Family> guestCheckInList = new ArrayList<Family>(0);
    public static final int NOT_FOUND = -1;

    /**
     Initializes a fitness class with given name, given instructor, given time, and given location
     * @param name - Name of fitness class
     * @param instructor - Instructor of fitness class
     * @param time - Time of fitness class
     * @param location - Location of fitness class
     */
    public FitnessClass(String name, String instructor, Time time, Location location)
    {
        this.instructor = instructor;
        this.time = time;
        this.name = name;
        this.location = location;
    }

    /**
     Finds a given member in a fitness class's check in list and returns the index
     * @param member - Member to be found
     * @return index of member if found, -1 otherwise
     */
    private int find (Member member)
    {
        return checkInList.indexOf(member);
    }

    /**
     Finds a given member in a fitness class's guest check in list and returns the index
     * @param family - Family member to be found
     * @return index of member if found, -1 otherwise
     */
    private int guestFind(Family family)
    {
        return guestCheckInList.indexOf(family);
    }

    /**
     Returns the instructor of a fitness class
     * @return instructor - String instructor of a fitness class
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     Returns the time of a fitness class
     * @return time - Time of a fitness class
     */
    public Time getTime() {
        return time;
    }

    /**
     Returns the name of a fitness class
     * @return name of fitness class
     */
    public String getName() {
        return name;
    }

    /**
     Returns the location of a fitness class
     * @return location of fitness class
     */
    public Location getLocation() {
        return location;
    }

    /**
     Checks if there are any duplicates of the given member in a fitness class' check in list
     * @param member to check for
     * @return true if there is a duplicate, false otherwise
     */
    public boolean checkDuplicate (Member member)
    {
        if (checkInList.contains(member))
        {
            return true;
        }
        return false;
    }

    /**
     Checks if there are any duplicates of the given member in a fitness class' guest check in list
     * @param member to check for
     * @return true if there is a duplicate, false otherwise
     */
    public boolean checkGuestDuplicate (Member member)
    {
        if (guestCheckInList.contains(member))
        {
            return true;
        }
        return false;
    }

    /**
     Checks if two fitness classes are equal to each other, using their name, instructor, and location
     * @param obj to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals (Object obj)
    {
        FitnessClass f = (FitnessClass) obj;
        if (f.getName().equals(name) && f.getInstructor().equals(instructor) && f.getLocation().equals(location))
        {
            return true;
        }
        return false;
    }

    /**
     Return String of fitness class check in list
     * @return String of check in list
     */
    public String printCheckInList()
    {
        String res = "";
        if (checkInList.size() > 0) {
            res += ("- Participants -\n");
            for (int i = 0; i < checkInList.size(); i++) {
                res += ("\t" + checkInList.get(i).toString() + "\n");
            }
            return res;
        }
        return null;
    }

    /**
     Return String of fitness class guest check in list
     * @return String of guest check in list
     */
    public String printGuestCheckInList()
    {
        String res = "";
        if (guestCheckInList.size() > 0)
        {
            res += ("- Guests -\n");
            for (int i = 0; i < guestCheckInList.size(); i++)
            {
                Family f = (Family) guestCheckInList.get(i);
                res += ("\t" + f.toString() + "\n");
            }
            return res;
        }
        return null;
    }

    /**
     Checks if a fitness class with the given time contains the given member
     * @param member - to check for
     * @param time1 - to check for
     * @return true if yes, false otherwise
     */
    public boolean checkTimeMember (Member member, Time time1)
    {
        if (time.equals(time1))
        {
            if (find(member) != -1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     Checks in the given member to a fitness class
     * @param member to check in
     */
    public boolean checkIn(Member member)
    {
        Date today = new Date();
        if(!checkDuplicate(member))
        {
            checkInList.add(member);
            if (member.getExpire().compareTo(today) < 0) {
                return false;
            }
            else if (!(member.getDob().isValid())){
                return false;
            }
            else if (!(member instanceof Family))
            {
                if (member.getLocation().equals(location))
                {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     Checks in a member guest to a fitness class
     * @param member - guest to check in
     */
    public boolean guestCheckIn(Family member)
    {
        Date today = new Date();
        guestCheckInList.add(member);
        member.decreaseGuestPass();
        if (member.getExpire().compareTo(today) < 0) {
            return false;
        }
        else if (!(member.getDob().isValid()))
        {
            return false;
        }
        else if (member.getLocation().equals(location))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     Drops a member from a fitness class
     * @param member to drop
     */
    public boolean drop(Member member)
    {
        int memberIndex = find(member);
        if (memberIndex != NOT_FOUND)
        {
            checkInList.remove(member);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     Drops a guest from a fitness class
     * @param member - guest to drop
     */
    public boolean guestDrop(Family member)
    {
        int memberIndex = guestFind(member);
        if (memberIndex != NOT_FOUND)
        {
            guestCheckInList.remove(member);
            return true;
        }
        else {
            return false;
        }
    }

}
