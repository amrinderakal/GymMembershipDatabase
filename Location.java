package com.example.project3;
/**
 This is the Location enum class, which specifies the
 five possible gym locations, as well as their counties
 and zip codes.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public enum Location {
    BRIDGEWATER ("08807", "SOMERSET"),
    EDISON ("08837", "MIDDLESEX"),
    FRANKLIN ("08873", "SOMERSET"),
    PISCATAWAY ("08854", "MIDDLESEX"),
    SOMERVILLE ("08876", "SOMERSET");

    private final String zip_code;
    private final String county;

    /**
     Initializes a Location object with given zip code and county as Strings
     * @param zip_code - Zip code String
     * @param county - County string
     */
    Location(String zip_code, String county)
    {
        this.zip_code = zip_code;
        this.county = county;
    }

    /**
     Returns zip code of location
     * @return zip code
     */
    public String getZip_code()
    {
        return zip_code;
    }

    /**
     Returns county of location
     * @return county
     */
    public String getCounty()
    {
        return county;
    }
}
