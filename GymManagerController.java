package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 This is the GymManagerController class, which is the controller for the GUI
 implemented in GymManagerView fxml file.
 This class implements various methods associated with actions on the GUI.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class GymManagerController {
    private static final int familyGuestPass = 1;
    private static final int premiumGuestPass = 3;
    private MemberDatabase memberDatabase = new MemberDatabase();
    private ClassSchedule classSchedule = new ClassSchedule();
    private Date today = new Date();

    @FXML
    private ToggleGroup ToggleGroup11;
    @FXML
    private RadioButton member;
    @FXML
    private RadioButton guest;
    @FXML
    private ToggleGroup ToggleGroup1;
    @FXML
    private Button add;
    @FXML
    private Button checkIn;
    @FXML
    private DatePicker checkInDOB;
    @FXML
    private TextField classLocation;
    @FXML
    private TextArea console;
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton family;
    @FXML
    private TextField firstName;
    @FXML
    private TextField fitnessClass;
    @FXML
    private TextField instructorName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField loc;
    @FXML
    private Button memberDrop;
    @FXML
    private TextField memberFirstName;
    @FXML
    private TextField memberLastName;
    @FXML
    private RadioButton premium;
    @FXML
    private Button remove;
    @FXML
    private RadioButton standard;

    /**
     Checks empty fields for adding members
     * @return String of error or "All good" if no error
     */
    private String addEmptyFields() {
        String fname = firstName.getText();
        String lname = lastName.getText();
        String locationStr = loc.getText();
        if (fname == null || fname.trim().isEmpty()) {
            return ("No first name inputted!\n");
        }
        else if (lname == null || lname.trim().isEmpty()) {
            return ("No last name inputted!\n");
        }
        else if (date.getValue() == null) {
            return ("No date of birth selected!\n");
        }
        else if (locationStr == null || locationStr.trim().isEmpty()) {
            return ("No location inputted!\n");
        }
        else if (!(standard.isSelected()) && !(family.isSelected()) && !(premium.isSelected())) {
            return("No membership type selected!\n");
        }
        else {
            return ("All good\n");
        }
    }

    /**
     Conducts the addition of a member into the member database
     */
    @FXML
    protected void add() {
        String fname = firstName.getText();
        String lname = lastName.getText();
        String locationStr = loc.getText();
        String emptyCheck = addEmptyFields();
        if (emptyCheck.equals("All good\n")) {
            String dobStr = date.getValue().toString();
            Date dob = pickToDate(date);
            if (dob.compareTo(today) == 0 || dob.compareTo(today) == 1) {
                console.appendText("DOB " + dobStr + ": cannot be today or a future date!\n");
            }
            else if (!dob.check18()) {
                console.appendText("DOB " + dobStr + ": must be 18 or older to join!\n");
            }
            else if (checkLocation(locationStr) == 0) {
                console.appendText(locationStr + ": invalid location!\n");
            }
            else {
                final Location location = Location.valueOf(locationStr.toUpperCase());
                Date expiry = today.threeMonths();
                Member member = new Member(fname, lname, dob, expiry, location);
                if (family.isSelected()) {
                    member = new Family(fname, lname, dob, expiry, location, familyGuestPass);
                }
                else if (premium.isSelected()) {
                    expiry = today.oneYear();
                    member = new Premium(fname, lname, dob, expiry, location, premiumGuestPass);
                }
                if (memberDatabase.checkDuplicate(member)) {
                    console.appendText(fname + " " + lname + " is already in the database.\n");
                }
                else {
                    memberDatabase.add(member);
                    console.appendText(fname + " " + lname + " added.\n");
                }
            }
        }
        else {
            console.appendText(emptyCheck);
        }
    }

    /**
     Checks empty fields for removing members
     * @return String of error or "All good" if no error
     */
    private String removeEmptyFields() {
        String fname = firstName.getText();
        String lname = lastName.getText();
        if (fname == null || fname.trim().isEmpty())
        {
            return("No first name inputted!\n");
        }
        else if (lname == null || lname.trim().isEmpty())
        {
            return("No last name inputted!\n");
        }
        else if (date.getValue() == null)
        {
            return("No date of birth selected!\n");
        }
        else {
            return ("All good\n");
        }
    }

    /**
     Conducts the deletion of a member from the member database
     */
    @FXML
    protected void remove() {
        String fname = firstName.getText();
        String lname = lastName.getText();
        String emptyCheck = removeEmptyFields();
        if (emptyCheck.equals("All good\n")) {
            Date dob = pickToDate(date);
            Member member = new Member(fname, lname, dob);
            if(!(memberDatabase.remove(member))) {
                console.appendText(fname + " " + lname + " is not in the database.\n");
            }
            else {
                memberDatabase.remove(member);
                console.appendText(fname + " " + lname + " removed.\n");
            }
        }
        else {
            console.appendText(emptyCheck);
        }
    }

    /**
     Loads members into the member database
     * @throws FileNotFoundException
     */
    @FXML
    protected void loadMembers() throws FileNotFoundException {
        File memberList = new File("src/main/java/com/example/project3/memberList.txt");
        memberDatabase.loadMembers(memberList);
        console.appendText("Members loaded into member database from memberList.txt\n");
    }

    /**
     Loads fitness classes into the class schedule
     * @throws FileNotFoundException
     */
    @FXML
    protected void loadClasses() throws FileNotFoundException {
        File classList = new File ("src/main/java/com/example/project3/classSchedule.txt");
        classSchedule.load(classList);
        console.appendText("Fitness classes loaded into class schedule from classSchedule.txt\n");
    }

    /**
     Prints the class schedule
     */
    @FXML
    protected void printClasses() {
        console.appendText(classSchedule.print());
    }

    /**
     Prints the member database
     */
    @FXML
    protected void printMembers() {
        console.appendText(memberDatabase.print());
    }

    /**
     Prints the members sorted by their gym county and zip code
     */
    @FXML
    protected void printByCounty() {
        console.appendText(memberDatabase.printByCounty());
    }

    /**
     Prints the members sorted by their last name and first name
     */
    @FXML
    protected void printByName() {
        console.appendText(memberDatabase.printByName());
    }

    /**
     Prints the members with their billing cycle
     */
    @FXML
    protected void printByFees() {
        console.appendText(memberDatabase.printFees());
    }

    /**
     Prints the members sorted by their membership expiration date
     */
    @FXML
    protected void printByExpiry() {
        console.appendText(memberDatabase.printByExpirationDate());
    }

    /**
     Checks if the inputted location is part of the 5 locations described in the enum class Location
     * @param locationStr - inputted location as a String
     * @return 1 if inputted string matches the Location, 0 otherwise
     */
    private int checkLocation (String locationStr)
    {
        for (Location location1 : Location.values())
        {
            if(location1.name().equalsIgnoreCase(locationStr)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     Converts a Date Picker object to a Date object
     * @param date - Date picker object
     * @return Date object
     */
    private Date pickToDate (DatePicker date)
    {
        String dobStr = date.getValue().toString();
        String[] datesplit = dobStr.split("-");
        int year = Integer.parseInt(datesplit[0]);
        int month = Integer.parseInt(datesplit[1]);
        int day = Integer.parseInt(datesplit[2]);
        Date res = new Date(year, month, day);
        return res;
    }

    /**
     Conducts the various checks for checking in and checking out of a member
     * @return All good if all checks pass, description of error otherwise
     */
    private String checks() {
        String className = fitnessClass.getText();
        String instructor = instructorName.getText();
        String locationStr = classLocation.getText();
        String fname = memberFirstName.getText();
        String lname = memberLastName.getText();
        Date dob = pickToDate(checkInDOB);
        Member member = new Member(fname, lname, dob);
        if (memberDatabase.checkDatabase(member) == 0)
        {
            Date expiry = memberDatabase.getExpiry(member);
            Location location = memberDatabase.getLocation(member);
            int check = checkLocation(locationStr);
            if (!classSchedule.checkInstructor(instructor)) {
                return (instructor + " - instructor does not exist.\n");
            }
            else if (!classSchedule.checkClass(className)) {
                return (className + " - class does not exist.\n");
            }
            else if (check == 0) {
                return (locationStr + " - invalid location.\n");
            }
            else if (!classSchedule.checkClassAtLocation(className, instructor, Location.valueOf(locationStr.toUpperCase()))) {
                return (className + " by " + instructor + " does not exist at " + locationStr + "\n");
            }
            else if (expiry.compareTo(today) < 0) {
                return (fname + " " + lname + " " + dob.dateToString() + " membership expired.\n");
            }
            else {
                return ("All good\n");
            }
        }
        else {
            return (fname + " " + lname + " " + dob.dateToString() + " is not in the database.\n");
        }
    }

    /**
     Checks if there are empty fields for the fitness class screen on the GUI
     * @return All good if no empty fields, description of empty field otherwise
     */
    private String fitnessEmptyFields() {
        String className = fitnessClass.getText();
        String instructor = instructorName.getText();
        String loc = classLocation.getText();
        String memberfname = memberFirstName.getText();
        String memberlname = memberLastName.getText();
        if (className == null || className.trim().isEmpty()) {
            return ("No class name inputted\n");
        }
        else if (instructor == null || instructor.trim().isEmpty()) {
            return ("No instructor name inputted\n");
        }
        else if (loc == null || loc.trim().isEmpty()) {
            return ("No location inputted\n");
        }
        else if (memberfname == null || memberfname.trim().isEmpty()) {
            return ("No first name inputted\n");
        }
        else if (memberlname == null || memberlname.trim().isEmpty()) {
            return ("No last name inputted\n");
        }
        else if (checkInDOB.getValue() == null) {
            return ("No DOB selected\n");
        }
        else if (!(member.isSelected()) && !(guest.isSelected())) {
            return ("No type of check-in/drop selected\n");
        }
        else {
            return ("All good\n");
        }
    }

    /**
     Checks if member is already checked into fitness class
     * @param className - Name of fitness class
     * @param instructor - Instructor of class
     * @param locationStr - Location of class
     * @param member - Member to check for
     * @return true if already checked in, false otherwise
     */
    private boolean alreadyCheckedIn (String className, String instructor, String locationStr, Member member)
    {
        int classIndex = classSchedule.find(className, instructor, locationStr);
        return classSchedule.classes[classIndex].checkDuplicate(member);
    }

    /**
     Checks if guest already checked into fitness class
     * @param className - Name of fitness class
     * @param instructor - Instructor of class
     * @param locationStr - Location of class
     * @param member - Member guest to check in
     * @return true if already checked in, false otherwise
     */
    private boolean guestAlreadyCheckedIn (String className, String instructor, String locationStr, Member member)
    {
        int classIndex = classSchedule.find(className, instructor, locationStr);
        return classSchedule.classes[classIndex].checkGuestDuplicate(member);
    }

    /**
     Checks if there is a time conflict for the member checking into the fitness class
     * @param member - Member checking in
     * @param className - Name of Fitness Class
     * @param instructor - Instructor of Fitness Class
     * @param locationStr - Location of Fitness Class
     * @return true if time conflict exists, false otherwise
     */
    private boolean checkTimeConflict (Member member, String className, String instructor, String locationStr)
    {
        int classIndex = classSchedule.find(className, instructor, locationStr);
        if (classSchedule.checkTimeConflict(member, classSchedule.classes[classIndex]))
        {
            return true;
        }
        return false;
    }

    /**
     Conducts the checkIn of a Member into a fitness class
     * @param member - Member to check in
     * @param index - Index of fitness class in class schedule
     */
    private void actualCheckIn(Member member, int index) {
        if (checkTimeConflict(member, classSchedule.classes[index].getName(), classSchedule.classes[index].getInstructor(), classSchedule.classes[index].getLocation().name()))
        {
            String location = classSchedule.classes[index].getLocation().name();
            String zip_code = classSchedule.classes[index].getLocation().getZip_code();
            String county = classSchedule.classes[index].getLocation().getCounty();
            console.appendText("Time conflict - " + classSchedule.classes[index].getName().toUpperCase() + " - "
                    + classSchedule.classes[index].getInstructor().toUpperCase() + ", " + classSchedule.classes[index].getTime().toString()
                    + ", " + location + ", " + zip_code + ", " + county +"\n");
        }
        else {
            if (!(member instanceof Family))
            {
                if (member.getLocation().equals(classSchedule.classes[index].getLocation()))
                {
                    classSchedule.classes[index].checkIn(member);
                    console.appendText(member.getFname() + " " + member.getLname() + " checked in " + classSchedule.classes[index].getName().toUpperCase() + " - "
                            + classSchedule.classes[index].getInstructor().toUpperCase() + ", "
                            + classSchedule.classes[index].getTime().toString() + ", " + classSchedule.classes[index].getLocation().name() + "\n");
                }
                else {
                    String location = classSchedule.classes[index].getLocation().name();
                    String zip_code = classSchedule.classes[index].getLocation().getZip_code();
                    String county = classSchedule.classes[index].getLocation().getCounty();
                    console.appendText(member.getFname() + " " + member.getLname() + " checking in " + location + ", " + zip_code + ", " + county + " - standard membership location restriction.\n");
                }
            }
            else {
                classSchedule.classes[index].checkIn(member);
                console.appendText(member.getFname() + " " + member.getLname() + " checked in " + classSchedule.classes[index].getName().toUpperCase() + " - "
                        + classSchedule.classes[index].getInstructor().toUpperCase() + ", "
                        + classSchedule.classes[index].getTime().toString() + ", " + classSchedule.classes[index].getLocation().name() + "\n");
            }
        }
    }

    /**
     Conducts the check in of a guest into a Fitness Class
     * @param member - Member guest to check in
     * @param index - Index of fitness class in class schedule
     */
    private void guestCheckIn (Member member, int index) {
        if (member instanceof Family) {
            Family f = (Family) member;
            int guestPasses = f.getGuestPass();
            if (guestPasses > 0) {
                if (f.getLocation().equals(classSchedule.classes[index].getLocation())) {
                    classSchedule.classes[index].guestCheckIn(f);
                    console.appendText(member.getFname() + " " + member.getLname() + " (guest) checked in " + classSchedule.classes[index].getName().toUpperCase() + " - "
                            + classSchedule.classes[index].getInstructor().toUpperCase() + ", "
                            + classSchedule.classes[index].getTime().toString() + ", " + classSchedule.classes[index].getLocation().name() + "\n");
                }
                else {
                    String location = classSchedule.classes[index].getLocation().name();
                    String zip_code = classSchedule.classes[index].getLocation().getZip_code();
                    String county = classSchedule.classes[index].getLocation().getCounty();
                    console.appendText(member.getFname() + " " + member.getLname()
                            + " Guest checking in " + location + ", " + zip_code + ", " + county + " - guest location restriction.\n");
                }
            }
            else {
                console.appendText(member.getFname() + " " + member.getLname() + " ran out of guest pass.\n");
            }
        }
        else {
            console.appendText("Standard membership - guest check-in is not allowed.\n");
        }
    }

    /**
     Controller method for checking in members and guests
     */
    @FXML
    protected void checkIn () {
        String empty = fitnessEmptyFields();
        if (empty.equals("All good\n"))
        {
            String res = checks();
            String className = fitnessClass.getText();
            String instructor = instructorName.getText();
            String locationStr = classLocation.getText();
            String fname = memberFirstName.getText();
            String lname = memberLastName.getText();
            Date dob = pickToDate(checkInDOB);
            if (res.equals("All good\n"))
            {
                if (member.isSelected())
                {
                    Member member = new Member(fname, lname, dob);
                    if (alreadyCheckedIn(className, instructor, locationStr, member)) {
                        console.appendText(fname + " " + lname + " already checked in.\n");
                    }
                    else {
                        Member m1 = memberDatabase.findByName(fname, lname, dob.dateToString());
                        int classIndex = classSchedule.find(className, instructor, locationStr);
                        actualCheckIn(m1, classIndex);
                    }
                }
                else if (guest.isSelected())
                {
                    Member m1 = memberDatabase.findByName(fname, lname, dob.dateToString());
                    int classIndex = classSchedule.find(className, instructor, locationStr);
                    guestCheckIn(m1, classIndex);
                }
            }
            else {
                console.appendText(res);
            }
        }
        else {
            console.appendText(empty);
        }
    }

    /**
     Conducts the dropping of members and guests from fitness classes
     */
    @FXML
    protected void drop () {
        String className = fitnessClass.getText();
        String instructor = instructorName.getText();
        String locationStr = classLocation.getText();
        String fname = memberFirstName.getText();
        String lname = memberLastName.getText();

        String res = checks();
        if (res.equals("All good\n")) {
            Date dob = pickToDate(checkInDOB);
            if (member.isSelected())
            {
                Member member = new Member(fname, lname, dob);
                if (alreadyCheckedIn(className,instructor,locationStr,member))
                {
                    int classIndex = classSchedule.find(className,instructor,locationStr);
                    classSchedule.classes[classIndex].drop(member);
                    console.appendText(fname + " " + lname + " done with the class.\n");
                }
                else {
                    console.appendText(fname + " " + lname + " did not check in.\n");
                }
            }
            else if (guest.isSelected())
            {
                Family member = new Family(fname, lname, dob);
                if (guestAlreadyCheckedIn(className, instructor, locationStr, member))
                {
                    int classIndex = classSchedule.find(className, instructor, locationStr);
                    classSchedule.classes[classIndex].guestDrop(member);
                    console.appendText(fname + " " + lname + " Guest done with the class.\n");
                    Member m1 = memberDatabase.findByName(fname, lname, dob.dateToString());
                    if (m1 instanceof Family)
                    {
                        ((Family) m1).increaseGuestPass();
                    }
                }
                else {
                    console.appendText(fname + " " + lname + " did not check in.\n");
                }
            }
        }
        else {
            console.appendText(res);
        }

    }


}