package com.example.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 This is the MemberDatabase class that serves as the data structure
 to store members. This class supports adding, removing, printing,
 and sorting members in the database.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class MemberDatabase {
    public static final int NOT_FOUND = -1;
    public static final int INCREMENT = 4;
    public static final int INITIAL_SIZE = 0;
    private Member[] mlist;
    private int size;

    /**
     Initializes a MemberDatabase with an initial capacity of 4.
     Sets the instance variable size to 0, as there are currently
     no members in the database.
     */
    public MemberDatabase()
    {
        final int CAPACITY = INCREMENT;
        mlist = new Member[CAPACITY];
        size = INITIAL_SIZE;
    }

    /**
     Checks if the MemberDatabase is full
     @return true if MemberDatabase is full, false otherwise
     */
    private boolean isFull()
    {
        if (size == mlist.length)
        {
            return true;
        }
        return false;
    }

    /**
     Returns index of member to find in member database
     * @param member to find
     * @return index of member if found, -1 otherwise
     */
    private int find(Member member) {
        for(int i = 0; i < size; i++)
        {
            if(mlist[i] != null) {
                if (mlist[i].equals(member)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     Loads members into member database from the given file
     * @param file - File of members
     * @throws FileNotFoundException
     */
    public void loadMembers (File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if (!(line.isBlank()))
            {
                String [] lineSplit = line.split(" ");
                String firstName = lineSplit[0];
                String lastName = lineSplit[1];
                String dobStr = lineSplit[2];
                String expiryStr = lineSplit[3];
                String locationStr = lineSplit[4];
                Date dob = new Date(dobStr);
                Date expiry = new Date(expiryStr);
                final Location location = Location.valueOf(locationStr.toUpperCase());
                Member member = new Member(firstName, lastName, dob, expiry, location);
                add(member);
            }
        }
    }


    /**
     Increases the capacity of member database by 4
     */
    private void grow() {
        Member [] temp = new Member[mlist.length+INCREMENT];
        for (int i = 0; i < mlist.length; i++)
        {
            temp[i] = mlist[i];
        }
        mlist = temp;
    }

    /**
     Returns the membership expiration date of the given member from the member database
     * @param member to access in database
     * @return Membership expiration date of given member
     */
    public Date getExpiry(Member member)
    {
        int index = find(member);
        if (index != NOT_FOUND)
        {
            return mlist[index].getExpire();
        }
        return null;
    }

    /**
     Returns the gym location of the given member from the member database
     * @param member to access in database
     * @return Location of given member
     */
    public Location getLocation(Member member)
    {
        int index = find(member);
        if (index != NOT_FOUND)
        {
            return mlist[index].getLocation();
        }
        return null;
    }

    /**
     Checks if there are any duplicates of given member in the member database
     * @param member to check for
     * @return true if there are duplicates, false otherwise
     */
    public boolean checkDuplicate(Member member)
    {
        for(int i = 0; i < mlist.length; i++)
        {
            if (mlist[i] != null)
            {
                if (mlist[i].equals(member))
                {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     Returns member with given first name, last name, and date of birth
     * @param fname - First name
     * @param lname - Last name
     * @param dob - Date of birth
     * @return Member if found, null otherwise
     */
    public Member findByName (String fname, String lname, String dob)
    {
        Member m1 = new Member(fname, lname, new Date(dob));
        for (int i = 0; i < size; i++)
        {
            if (mlist[i].equals(m1))
            {
                return mlist[i];
            }
        }
        return null;
    }

    /**
     Checks if member exists in Member Database
     * @param member to check for
     * @return 0 if exists, 1 otherwise
     */
    public int checkDatabase (Member member)
    {
        if(find(member) >= 0)
        {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     Adds member to member database
     * @param member to add
     * @return true if added successfully, false otherwise
     */
    public boolean add(Member member) {
         if(!checkDuplicate(member)) {
             if (isFull()) {
                 grow();
                 for (int i = 0; i < mlist.length; i++) {
                     if (mlist[i] == null) {
                         mlist[i] = member;
                         break;
                     }
                 }
             } else {
                 for (int i = 0; i < mlist.length; i++) {
                     if (mlist[i] == null) {
                         mlist[i] = member;
                         break;
                     }
                 }
             }
             size++;
         }
        return false;
    }

    /**
     Removes given member from member database
     * @param member to remove
     * @return true if removed member successfully, false otherwise
     */
    public boolean remove(Member member) {
       int memberIndex = find(member);
       if (memberIndex != NOT_FOUND)
       {
           mlist[memberIndex] = null;
           size--;
           for(int i = memberIndex; i < mlist.length - 1; i++)
           {
               mlist[i] = mlist[i+1];
               mlist[i+1] = null;
           }
           return true;
       }
       return false;
    }

    /**
     Returns the member database contents as a concatenated string
     * @return string of members
     */
    public String print()
    {
        String res = "";
        if (size > 0) {
            res += "-list of members-\n";
            for (int i = 0; i < mlist.length; i++)
            {
                if(mlist[i] != null) {
                    res += ((mlist[i].toString()) + "\n");
                }
            }
            res += "-end of list-\n";
            return res;
        }
        else {
            return ("Member database is empty!\n");
        }
    }

    /**
     Returns the string of members of the member database sorted by county and then by the zipcode of their location
     * @return String of members sorted by county
     */
    public String printByCounty() {
        String res = "";
        if (size > 0) {
            res += ("-list of members sorted by county and zipcode-\n");
            for (int i = 1; i < size; i++) {
                Member name = mlist[i];
                int j = i - 1;
                while (j >= 0 && mlist[j].getLocation().getCounty().compareTo(name.getLocation().getCounty()) > 0) {
                    mlist[j + 1] = mlist[j];
                    j = j - 1;
                }
                if (j >= 0 && mlist[j].getLocation().getCounty().compareTo(name.getLocation().getCounty()) == 0) {
                    while (j >= 0 && mlist[j].getLocation().getZip_code().compareTo(name.getLocation().getZip_code()) > 0) {
                        mlist[j + 1] = mlist[j];
                        j = j - 1;
                    }
                }
                mlist[j + 1] = name;
            }
            for (int i = 0; i < mlist.length; i++) {
                if(mlist[i] != null) {
                    res += ((mlist[i].toString()) + "\n");
                }
            }
            res += ("-end of list-\n");
            return res;
        }
        else {
            return ("Member database is empty!\n");
        }
    }

    /**
     Returns members of the member database, as sorted by their membership expiration date
     * @return String of sorted members
     */
    public String printByExpirationDate()
    {
        String res = "";
        if (size > 0) {
            res += ("-list of members sorted by membership expiration date-\n");
            for (int i = 0; i < mlist.length - 1; i++) {
                for (int j = i + 1; j < mlist.length; j++) {
                    if (mlist[i] != null && mlist[j] != null) {
                        int compare = mlist[i].getExpire().compareTo(mlist[j].getExpire());
                        if (compare == 1) {
                            Member temp = mlist[i];
                            mlist[i] = mlist[j];
                            mlist[j] = temp;
                        }
                    }
                }
            }
            for (int i = 0; i < mlist.length; i++)
            {
                if(mlist[i] != null) {
                    res += (mlist[i].toString() + "\n");
                }
            }
            res += ("-end of list-\n");
            return res;
        }
        else {
            return ("Member database is empty!\n");
        }
    }

    /**
     Returns the members with their membership fees for the next billing cycle
     * @return String of members with fees
     */
    public String printFees()
    {
        String res = "";
        if (size > 0) {
            res += ("-list of members with membership fees-\n");
            for (int i = 0; i < size; i++)
            {
                res += (mlist[i].toString() + ", Membership fee: $" + mlist[i].membershipFee() + "\n");
            }
            res += ("-end of list-\n");
            return res;
        }
        else {
            return ("Member database is empty!\n");
        }
    }


    /**
     Returns members of the member database, as sorted by their last name, then first name
     * @return String of members sorted by name
     */
    public String printByName()
    {
        String res = "";
        if(size > 0) {
            res += ("-list of members sorted by last name, and first name-\n");
            for (int i = 1; i < size; i++) {
                Member name = mlist[i];
                int j = i - 1;
                while (j >= 0 && mlist[j].getLname().compareTo(name.getLname()) > 0) {
                    mlist[j + 1] = mlist[j];
                    j = j - 1;
                }
                if (j >= 0 && mlist[j].getLname().compareTo(name.getLname()) == 0) {
                    while (j >= 0 && mlist[j].getFname().compareTo(name.getFname()) > 0) {
                        mlist[j + 1] = mlist[j];
                        j = j - 1;
                    }
                }
                mlist[j + 1] = name;
            }
            for (int i = 0; i < mlist.length; i++)
            {
                if(mlist[i] != null) {
                    res += (mlist[i].toString() + "\n");
                }
            }
            res += ("-end of list-\n");
            return res;
        }
        else {
            return ("Member database is empty!\n");
        }
    }
}
