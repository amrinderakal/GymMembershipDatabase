package com.example.project3;
/**
 This is the Premium class, which extends the Family class.
 This class supports the creation of a Premium member object.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class Premium extends Family{

    private static final double premiumFee = 59.99;

    /**
     Creates a new Premium Member object, given the first name, last name, date of birth, membership expiration date, gym location, and number of guest passes
     * @param fname first name of member
     * @param lname last name of member
     * @param dob date of birth of member
     * @param expire membership expiration date
     * @param location gym location
     * @param guestPasses Number of guest passes
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location, int guestPasses) {
        super(fname, lname, dob, expire, location, guestPasses);
    }

    /**
     Returns number of guest passes of Premium member
     * @return number of guest passes
     */
    public int getGuestPass()
    {
        return super.getGuestPass();
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
     Returns the location
     * @return gym location of Family member
     */
    public Location getLocation()
    {
        return super.getLocation();
    }

    /**
     Decreases the number of guest passes by 1
     */
    @Override
    public void decreaseGuestPass()
    {
        guestPass--;
    }

    /**
     Increases the number of guest passes by 1
     */
    @Override
    public void increaseGuestPass()
    {
        guestPass++;
    }

    /**
     Computes the membership fee for the next billing cycle for a Premium Member
     * @return total membership fees
     */
    @Override
    public double membershipFee()
    {
        double membershipFee = premiumFee*annually;
        return membershipFee;
    }

    /**
     This method converts a Premium member object to a String representation.
     * @return String representation of Premium member
     */
    @Override
    public String toString()
    {
        String res = getFname() + " " + getLname() + ", DOB: " + getDob().dateToString() + ", Membership expires "
                + getExpire().dateToString() + ", Location: " + getLocation() + ", " + getLocation().getZip_code() + ", " + getLocation().getCounty()
                + ", (Premium) Guess-pass remaining: " + guestPass;
        return res;
    }
}
