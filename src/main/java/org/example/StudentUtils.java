package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

class StudentUtils {
    public static void getMarks(ResultSet rs, HashMap<String, Object> studentMap) throws SQLException {
        studentMap.put("NAME", rs.getString("NAME"));
        studentMap.put("ADMISSION_NUMBER", rs.getString("ADMISSION_NUMBER"));
        studentMap.put("Percentage", String.format("%.2f", calculatePercentage(rs)));

        int physics = rs.getInt("MARKS_PHYSICS");
        int chemistry = rs.getInt("MARKS_CHEMISTRY");
        int maths = rs.getInt("MARKS_MATHS");

        studentMap.put("MARKS_PHYSICS", physics);
        studentMap.put("GRADE_PHYSICS", gradereturn(physics));
        studentMap.put("GRADEPOINT_PHYSICS", gradepoint(physics));

        studentMap.put("MARKS_CHEMISTRY", chemistry);
        studentMap.put("GRADE_CHEMISTRY", gradereturn(chemistry));
        studentMap.put("GRADEPOINT_CHEMISTRY", gradepoint(chemistry));

        studentMap.put("MARKS_MATHS", maths);
        studentMap.put("GRADE_MATHS", gradereturn(maths));
        studentMap.put("GRADEPOINT_MATHS", gradepoint(maths));

        printPrettyJson(studentMap);

//        studentMap.put("MARKS_PHYSICS", rs.getInt("MARKS_PHYSICS"));
//        studentMap.put("GRADE_PHYSICS", rs.getString("GRADE_PHYSICS"));
//        studentMap.put("GRADEPOINT_PHYSICS", rs.getString("GRADEPOINT_PHYSICS"));
//
//        studentMap.put("MARKS_CHEMISTRY", rs.getInt("MARKS_CHEMISTRY"));
//        studentMap.put("GRADE_CHEMISTRY", rs.getString("GRADE_CHEMISTRY"));
//        studentMap.put("GRADEPOINT_CHEMISTRY", rs.getString("GRADEPOINT_CHEMISTRY"));
//
//        studentMap.put("MARKS_MATHS", rs.getInt("MARKS_MATHS"));
//        studentMap.put("GRADE_MATHS", rs.getString("GRADE_MATHS"));
//        studentMap.put("GRADEPOINT_MATHS", rs.getString("GRADEPOINT_MATHS"));

    }

    public static double calculatePercentage(ResultSet rs) throws SQLException {
        int physics = rs.getInt("MARKS_PHYSICS");
        int chemistry = rs.getInt("MARKS_CHEMISTRY");
        int maths = rs.getInt("MARKS_MATHS");
        return (double) (physics + chemistry + maths) / 3;
    }

    private static void printPrettyJson(HashMap<String, Object> studentMap) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty Print
            String prettyJson = mapper.writeValueAsString(studentMap);
            System.out.println(prettyJson);
        } catch (Exception e) {
            System.err.println("Error converting to JSON: " + e.getMessage());
        }
    }

    public static String gradereturn(Integer marks) {
        if (marks >= 91) {
            return "A1";
        } else if (marks >= 81 && marks <= 90) {
            return "A2";
        } else if (marks >= 71 && marks <= 80) {
            return "B1";
        } else if (marks >= 61 && marks <= 70) {
            return "B2";
        } else if (marks >= 51 && marks <= 60) {
            return "C1";
        } else if (marks >= 41 && marks <= 50) {
            return "C2";
        } else if (marks >= 33 && marks <= 40) {
            return "D";
        } else if (marks >= 21 && marks <= 32) {
            return "E1";
        } else if (marks >= 0 && marks <= 20) {
            return "E2";
        } else {
            return "invalid marks";
        }
    }

    public static String gradepoint(Integer marks) {
        if (marks >= 91) {
            return "10.0";
        } else if (marks >= 81 && marks <= 90) {
            return "9.0";
        } else if (marks >= 71 && marks <= 80) {
            return "8.0";
        } else if (marks >= 61 && marks <= 70) {
            return "7.0";
        } else if (marks >= 51 && marks <= 60) {
            return "6.0";
        } else if (marks >= 41 && marks <= 50) {
            return "5.0";
        } else if (marks >= 33 && marks <= 40) {
            return "4.0";
        } else if (marks >= 21 && marks <= 32) {
            return "C";
        } else if (marks >= 0 && marks <= 20) {
            return "C";
        } else {
            return "invalid marks";
        }
    }

}
