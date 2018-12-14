package com.java.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestRun {

    public static void main(String[] args) {
        try {

            System.out.println("Working well . . .");

            Profile profile =  new Profile();
            profile.setGender("MALE");
            profile.setLastName("Test Company");
            profile.setFirstName("Administrator");

            User user = new User();
            user.setUsername("admin");
            user.setPassword("pass123");
            user.setProfile(profile);

            ObjectMapper objectMapper = new ObjectMapper();

            // Writing Object into JSON String
            String jsonString = objectMapper.writeValueAsString(user);

            // Modified JSON String with "myValue", which is not property of User class
            String stringJSON = "{\"username\":\"admin\", \"myValue\":\"abc123\",\"password\":\"pass123\",\"profile\":{\"lastName\":\"Test Company\",\"firstName\":\"Administrator\",\"gender\":\"MALE\"},\"active\":true}";

            // It handles unknown properties, ignore all unknown properties
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            // JSON string map into User class object with child class Profile
            User user1 = objectMapper.readValue(stringJSON, User.class);

            System.out.println(jsonString);
            System.out.println(user1.toString());
            System.out.println("### Done ###");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
