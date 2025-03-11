package org.example;

// Factory class to create StudentSearch objects
class StudentSearchFactory {
    public static StudentSearch getSearchMethod(String searchType) {
        if (searchType.equalsIgnoreCase("admission")) {
            return new SearchByAdmissionNumber();
        } else if (searchType.equalsIgnoreCase("name")) {
            return new SearchByName();
        }
        return null;
    }
}