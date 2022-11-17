package com.example.project3;
/**
 This is the Family class, which extends the Member class.
 This class supports the creation of a Family member object.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class Family extends Member{

    public int guestPass;
    private static final double familyFee = 59.99;

    /**
     Creates a new Family Member object, given the first name, last name, date of birth, membership expiration date, gym location, and number of guest passes
     * @param fname first name of member
     * @param lname last name of member
     * @param dob date of birth of member
     * @param expire membership expiration date
     * @param location gym location
     * @param guestPasses Number of guest passes
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location, int guestPasses) {
        super(fname, lname, dob, expire, location);
        guestPass = guestPasses;
    }

    /**
     Creates a new Family member object, given the first name, last name, and date of birth
     * @param fname first name of member
     * @param lname last name of member
     * @param dob date of birth of member
     */
    public Family(String fname, String lname, Date dob)
    {
        super(fname, lname, dob);
    }

    /**
     Decreases the number of guest passes by 1
     */
    public void decreaseGuestPass()
    {
        guestPass--;
    }

    /**
     Increases the number of guest passes by 1
     */
    public void increaseGuestPass()
    {
        guestPass++;
    }

    /**
     Returns the number of guest passes of a Family or Premium member
     * @return Number of guest passes
     */
    public int getGuestPass() {
        return guestPass;
    }

    /**
     Returns the first name
     * @return first name of Family member
     */
    public String getFname() { return super.getFname();}

    /**
     Returns the last name
     * @return last name of Family member
     */
    public String getLname()
    {
        return super.getLname();
    }
    /**
     Returns the date of birth
     * @return date of birth of Family member
     */
    public Date getDob()
    {
        return super.getDob();
    }

    /**
     Returns the membership expiration date
     * @return membership expiration date of Family member
     */
    public Date getExpire()
    {
        return super.getExpire();
    }

    /**
     Returns the gym location
     * @return gym location of Family member
     */
    public Location getLocation()
    {
        return super.getLocation();
    }


    /**
     Computes the membership fee for the next billing cycle for a Family Member
     * @return total membership fees
     */
    @Override
    public double membershipFee()
    {
        double membershipFee = oneTimeFee + (familyFee*quarterly);
        return membershipFee;
    }

    /**
     This method converts a Family member object to a String representation.
     * @return String representation of Family member
     */
    @Override
    public String toString()
    {
        String res = super.toString() + ", (Family) guest-pass remaining: " + guestPass;
        return res;
    }

}
