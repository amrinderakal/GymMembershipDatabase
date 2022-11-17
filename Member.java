package com.example.project3;
/**
 This is the Member class, which supports creating
 a new Member object and comparing Member objects.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public static final double oneTimeFee = 29.99;
    private static final double standardFee = 39.99;
    public static final double quarterly = 3.0;
    public static final double annually = 11.0;

    /**
     Creates a new Member object, given the first name, last name, date of birth, membership expiration date, and gym location
     * @param fname first name of member
     * @param lname last name of member
     * @param dob date of birth of member
     * @param expire membership expiration date
     * @param location gym location
     */
    public Member (String fname, String lname, Date dob, Date expire, Location location)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     Creates a new Member object, given the first name, last name, date of birth
     * @param fname first name of member
     * @param lname last name of member
     * @param dob date of birth of member
     */
    public Member (String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     Returns the first name
     * @return first name of member
     */
    public String getFname()
    {
        return fname;
    }

    /**
     Returns the last name
     * @return last name of member
     */
    public String getLname()
    {
        return lname;
    }
    /**
     Returns the date of birth
     * @return date of birth of member
     */
    public Date getDob()
    {
        return dob;
    }

    /**
     Returns the membership expiration date
     * @return membership expiration date of member
     */
    public Date getExpire()
    {
        return expire;
    }

    /**
     Returns the location
     * @return gym location of member
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     This method converts a Member object to a String representation.
     * @return String representation of Member object
     */
    @Override
    public String toString() {
        String res = fname + " " + lname + ", DOB: " + dob.dateToString() + ", Membership expires "
                + expire.dateToString() + ", Location: " + location + ", " + location.getZip_code() + ", " + location.getCounty();
        return res;
    }

    /**
     Checks if two members are equal to each other, using their first name, last name, and date of birth
     * @param obj
     * @return true if equal to each other, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        Member m = (Member)obj;
        if (m.getFname().equalsIgnoreCase(fname) && m.getLname().equalsIgnoreCase(lname) && m.getDob().dateToString().equalsIgnoreCase(dob.dateToString()))
        {
            return true;
        }
       return false;
    }

    /**
     Compares two Member objects by their names
     * @param member the object to be compared.
     * @return 0 if equal to each other, 1 if current member's name is alphabetically higher than provided member's name, -1 if current member's name is alphabetically lower than provided member's name
     */
    @Override
    public int compareTo(Member member)
    {
        if (lname.equalsIgnoreCase(member.getLname()) && fname.equalsIgnoreCase(member.getFname()))
        {
            return 0;
        }
        else if (lname.compareTo(member.getLname()) > 0)
        {
            return 1;
        }
        else if ((lname.compareTo(member.getLname()) == 0) && (fname.compareTo(member.getFname()) > 0))
        {
            return 1;
        }
        return -1;
    }

    /**
     Computes the membership fee for the next billing cycle for a Member
     * @return total membership fees
     */
    public double membershipFee()
    {
        double membershipFee = oneTimeFee + (standardFee*quarterly);
        return membershipFee;
    }

    /**
     This testbed main implements the test cases to test the compareTo() method.
     * @param args - String array of command line arguments
     */
    public static void main(String[] args) {
        Member m1 = new Member("John", "John", new Date("09/21/2002"));
        Member m2 = new Member("John", "John", new Date("09/18/2002"));


        Member m3 = new Member("John", "Walters", new Date("09/24/1998"));
        Member m4 = new Member("Albert", "Einstein", new Date("09/24/1998"));

        Member m5 = new Member("John", "Walters", new Date("09/24/1998"));
        Member m6 = new Member("Albert", "Walters", new Date("09/24/1998"));

        System.out.println(m1.compareTo(m2)); // Test Case 1
        System.out.println(m3.compareTo(m4)); // Test Case 2
        System.out.println(m4.compareTo(m3)); // Test Case 3
        System.out.println(m5.compareTo(m6)); // Test Case 4
        System.out.println(m6.compareTo(m5)); // Test Case 5

    }
}
