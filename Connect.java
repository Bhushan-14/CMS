package JDBC.CMS;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Connect {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/CMS";
        String username = "root";
        String password = "tiger";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database");

            insertUserData(connection);
            retrieveUserData(connection);
            insertPoliceStationData(connection);
            retrievePoliceStationData(connection);
            insertCriminalsData(connection);
            retrieveCriminalsData(connection);
            insertCriminalCaseData(connection);
            retrieveCriminalCaseData(connection);
            insertEvidenceData(connection);
            retrieveEvidenceData(connection);
            insertWitnessData(connection);
            retrieveEvidenceData(connection);
            insertCaseDetailsData(connection);
            retrieveCaseDetailsData(connection);
            insertWantedCriminalsData(connection);
            retrieveWantedCriminalsData(connection);

            System.out.println("Connection closed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertUserData(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String usernameInput = sc.nextLine();
        System.out.println("Enter Email: ");
        String emailInput = sc.nextLine();
        System.out.println("Enter Password: ");
        String passwordInput = sc.nextLine();
        System.out.println("Enter Role (user, admin, police_station): ");
        String roleInput = sc.nextLine();

        String insertQuery = "INSERT INTO Users (Username, Email, Password, Role) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, usernameInput);
            preparedStatement.setString(2, emailInput);
            preparedStatement.setString(3, passwordInput);
            preparedStatement.setString(4, roleInput);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted into Users successfully.");
            } else {
                System.out.println("Failed to insert data into Users.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void retrieveUserData(Connection connection) {
        String query = "SELECT * FROM Users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\nUsers Table Data:");
            while (resultSet.next()) {
                System.out.println("UserID: " + resultSet.getInt("UserID"));
                System.out.println("Username: " + resultSet.getString("Username"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Role: " + resultSet.getString("Role"));
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertPoliceStationData(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Station Name: ");
        String stationName = sc.nextLine();
        System.out.println("Enter Station Location: ");
        String location = sc.nextLine();
        System.out.println("Enter Station Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter Station Email: ");
        String email = sc.nextLine();
        System.out.println("Enter Station Chief Officer: ");
        String chiefOfficer = sc.nextLine();

        String insertQuery = "INSERT INTO PoliceStations (StationName, Location, PhoneNumber, Email, ChiefOfficer) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, stationName);
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, chiefOfficer);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted into PoliceStations successfully.");
            } else {
                System.out.println("Failed to insert data into PoliceStations.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void retrievePoliceStationData(Connection connection) {
        String query = "SELECT * FROM PoliceStations";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\nPoliceStations Table Data:");
            while (resultSet.next()) {
                System.out.println("StationID: " + resultSet.getInt("StationID"));
                System.out.println("StationName: " + resultSet.getString("StationName"));
                System.out.println("Location: " + resultSet.getString("Location"));
                System.out.println("PhoneNumber: " + resultSet.getString("PhoneNumber"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("ChiefOfficer: " + resultSet.getString("ChiefOfficer"));
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertCriminalsData(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CriminalID: ");
        String CriminalID = sc.nextLine();
        System.out.println("Enter First Name: ");
        String FirstName = sc.nextLine();
        System.out.println("Enter Last Name: ");
        String LastName = sc.nextLine();
        System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
        String DateOfBirth = sc.nextLine();
        System.out.println("Enter Gender ('Male', 'Female', 'Other'): ");
        String Gender = sc.nextLine();
        System.out.println("Enter Nationality: ");
        String Nationality = sc.nextLine();
        System.out.println("Enter Crime Description: ");
        String CrimeDescription = sc.nextLine();
        System.out.println("Enter Arrest Date (YYYY-MM-DD): ");
        String ArrestDate = sc.nextLine();
        System.out.println("Enter Prison Location: ");
        String PrisonLocation = sc.nextLine();
        System.out.println("Enter the path to the image file: ");
        String imagePath = sc.nextLine();

        try (FileInputStream criminalImage = new FileInputStream(imagePath)) {
            String insertQuery = "INSERT INTO Criminals (CriminalID, FirstName, LastName, DateOfBirth, Gender, Nationality, CrimeDescription, ArrestDate, PrisonLocation, Photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, CriminalID);
                preparedStatement.setString(2, FirstName);
                preparedStatement.setString(3, LastName);
                preparedStatement.setString(4, DateOfBirth);
                preparedStatement.setString(5, Gender);
                preparedStatement.setString(6, Nationality);
                preparedStatement.setString(7, CriminalID);
                preparedStatement.setString(8, ArrestDate);
                preparedStatement.setString(9, PrisonLocation);
                preparedStatement.setBinaryStream(10, criminalImage);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into Criminals successfully.");
                } else {
                    System.out.println("Failed to insert data into Criminals.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void retrieveCriminalsData(Connection connection) {
        String query = "SELECT * FROM Criminals";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Criminals Data are: ");
            while (resultSet.next()) {
                System.out.println("CriminalID: " + resultSet.getString("CriminalID"));
                System.out.println("FirstName: " + resultSet.getString("FirstName"));
                System.out.println("LastName: " + resultSet.getString("LastName"));
                System.out.println("DateOfBirth: " + resultSet.getString("DateOfBirth"));
                System.out.println("Gender: " + resultSet.getString("Gender"));
                System.out.println("Nationality: " + resultSet.getString("Nationality"));
                System.out.println("CrimeDescription: " + resultSet.getString("CrimeDescription"));
                System.out.println("PrisonLocation: " + resultSet.getString("PrisonLocation"));
                System.out.println("Status: " + resultSet.getString("Status"));
                System.out.println("Photo: " + resultSet.getString("imagePath"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertCriminalCaseData(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CaseNumber: ");
        String caseNumber = sc.nextLine();
        System.out.println("Enter CaseName: ");
        String caseName = sc.nextLine();
        System.out.println("Enter CaseDescription: ");
        String caseDescription = sc.nextLine();
        System.out.println("Enter CaseStatus ('Open', 'Closed', 'In Progress', 'Solved', 'Other'): ");
        String caseStatus = sc.nextLine();
        System.out.println("Enter OpenDate (YYYY-MM-DD): ");
        String openDate = sc.nextLine();
        System.out.println("Enter CloseDate (YYYY-MM-DD): ");
        String closeDate = sc.nextLine();
        System.out.println("Enter LeadInvestigatorID: ");
        int leadInvestigatorID = sc.nextInt();
        System.out.println("Enter PoliceStationID: ");
        int policeStationID = sc.nextInt();

        try {
            String insertQuery = "INSERT INTO CriminalCases (CaseNumber, CaseName, CaseDescription, CaseStatus, OpenDate, CloseDate, LeadInvestigatorID, PoliceStationID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, caseNumber);
                preparedStatement.setString(2, caseName);
                preparedStatement.setString(3, caseDescription);
                preparedStatement.setString(4, caseStatus);
                preparedStatement.setString(5, openDate);
                preparedStatement.setString(6, closeDate);
                preparedStatement.setInt(7, leadInvestigatorID);
                preparedStatement.setInt(8, policeStationID);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into CriminalCases successfully.");
                } else {
                    System.out.println("Failed to insert data into CriminalCases.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void retrieveCriminalCaseData(Connection connection) {
        String query = "SELECT * FROM CriminalCases";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Criminal Cases Data are: ");
            while (resultSet.next()) {
                System.out.println("CaseID: " + resultSet.getInt("CaseID"));
                System.out.println("CaseNumber: " + resultSet.getString("CaseNumber"));
                System.out.println("CaseName: " + resultSet.getString("CaseName"));
                System.out.println("CaseDescription: " + resultSet.getString("CaseDescription"));
                System.out.println("CaseStatus: " + resultSet.getString("CaseStatus"));
                System.out.println("OpenDate: " + resultSet.getString("OpenDate"));
                System.out.println("CloseDate: " + resultSet.getString("CloseDate"));
                System.out.println("LeadInvestigatorID: " + resultSet.getInt("LeadInvestigatorID"));
                System.out.println("PoliceStationID: " + resultSet.getInt("PoliceStationID"));
                System.out.println("LastUpdated: " + resultSet.getTimestamp("LastUpdated"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertEvidenceData(Connection connection) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter CaseID: ");
            String CaseId = sc.nextLine();
            System.out.println("Enter ItemName: ");
            String ItemName = sc.nextLine();
            System.out.println("Enter Description: ");
            String Description = sc.nextLine();
            System.out.println("Enter EvidenceType: ");
            String EvidenceType = sc.nextLine();
            System.out.println("Enter StorageLocation: ");
            String StorageLocation = sc.nextLine();
            System.out.println("Enter ChainOfCustody: ");
            String ChainOfCustody = sc.nextLine();
            System.out.println("Enter ReceivedDate (YYYY-MM-DD): ");
            String ReceivedDate = sc.nextLine();
            System.out.println("Enter ExpiryDate (YYYY-MM-DD): ");
            String ExpiryDate = sc.nextLine();

            String insertQuery = "INSERT INTO Evidence (CaseID, ItemName, Description, EvidenceType, StorageLocation, ChainOfCustody, ReceivedDate, ExpiryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, CaseId);
                preparedStatement.setString(2, ItemName);
                preparedStatement.setString(3, Description);
                preparedStatement.setString(4, EvidenceType);
                preparedStatement.setString(5, StorageLocation);
                preparedStatement.setString(6, ChainOfCustody);
                preparedStatement.setString(7, ReceivedDate);
                preparedStatement.setString(8, ExpiryDate);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into Evidence successfully.");
                } else {
                    System.out.println("Failed to insert data into Evidence.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                sc.close();
            }
        }
    }
    private static void retrieveEvidenceData(Connection connection) {
        String query = "SELECT * FROM Evidence";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Evidence Data are: ");
            while (resultSet.next()) {
                System.out.println("EvidenceID: " + resultSet.getInt("EvidenceID"));
                System.out.println("CaseID: " + resultSet.getInt("CaseID"));
                System.out.println("EvidenceName: " + resultSet.getString("EvidenceName"));
                System.out.println("EvidenceDescription: " + resultSet.getString("EvidenceDescription"));
                System.out.println("EvidenceStatus: " + resultSet.getString("EvidenceStatus"));
                System.out.println("EvidenceLocation: " + resultSet.getString("EvidenceLocation"));
                System.out.println("DateCollected: " + resultSet.getString("DateCollected"));
                System.out.println("CollectedBy: " + resultSet.getString("CollectedBy"));
                System.out.println("LastUpdated: " + resultSet.getTimestamp("LastUpdated"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static void insertWitnessData(Connection connection) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter FirstName: ");
            String firstName = sc.nextLine();
            System.out.println("Enter LastName: ");
            String lastName = sc.nextLine();
            System.out.println("Enter DateOfBirth (YYYY-MM-DD): ");
            String dateOfBirth = sc.nextLine();
            System.out.println("Enter Gender (Male/Female/Other): ");
            String gender = sc.nextLine();
            System.out.println("Enter Nationality: ");
            String nationality = sc.nextLine();
            System.out.println("Enter ContactInfo: ");
            String contactInfo = sc.nextLine();
            System.out.println("Enter Statement: ");
            String statement = sc.nextLine();
            System.out.println("Enter CaseID: ");
            int caseId = sc.nextInt();

            String insertQuery = "INSERT INTO Witnesses (FirstName, LastName, DateOfBirth, Gender, Nationality, ContactInfo, Statement, CaseID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, dateOfBirth);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, nationality);
                preparedStatement.setString(6, contactInfo);
                preparedStatement.setString(7, statement);
                preparedStatement.setInt(8, caseId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into Witnesses successfully.");
                } else {
                    System.out.println("Failed to insert data into Witnesses.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                sc.close();
            }
        }
    }

    private static void retrieveWitnessData(Connection connection) {
        String query = "SELECT * FROM Witnesses";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Witness Data are: ");
            while (resultSet.next()) {
                System.out.println("WitnessID: " + resultSet.getInt("WitnessID"));
                System.out.println("FirstName: " + resultSet.getString("FirstName"));
                System.out.println("LastName: " + resultSet.getString("LastName"));
                System.out.println("DateOfBirth: " + resultSet.getDate("DateOfBirth"));
                System.out.println("Gender: " + resultSet.getString("Gender"));
                System.out.println("Nationality: " + resultSet.getString("Nationality"));
                System.out.println("ContactInfo: " + resultSet.getString("ContactInfo"));
                System.out.println("Statement: " + resultSet.getString("Statement"));
                System.out.println("CaseID: " + resultSet.getInt("CaseID"));
                System.out.println("LastUpdated: " + resultSet.getTimestamp("LastUpdated"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertCaseDetailsData(Connection connection) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter CaseID: ");
            int caseId = sc.nextInt();
            System.out.println("Enter PoliceStationID: ");
            int policeStationId = sc.nextInt();
            System.out.println("Enter RegistrationDate (YYYY-MM-DD): ");
            String registrationDate = sc.next();
            sc.nextLine(); // Consume the newline character
            System.out.println("Enter ReportingOfficer: ");
            String reportingOfficer = sc.nextLine();
            System.out.println("Enter Notes: ");
            String notes = sc.nextLine();

            String insertQuery = "INSERT INTO CaseDetails (CaseID, PoliceStationID, RegistrationDate, ReportingOfficer, Notes) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, caseId);
                preparedStatement.setInt(2, policeStationId);
                preparedStatement.setString(3, registrationDate);
                preparedStatement.setString(4, reportingOfficer);
                preparedStatement.setString(5, notes);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into CaseDetails successfully.");
                } else {
                    System.out.println("Failed to insert data into CaseDetails.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                sc.close();
            }
        }
    }

    private static void retrieveCaseDetailsData(Connection connection) {
        String query = "SELECT * FROM CaseDetails";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("CaseDetails Data are: ");
            while (resultSet.next()) {
                System.out.println("CaseDetailID: " + resultSet.getInt("CaseDetailID"));
                System.out.println("CaseID: " + resultSet.getInt("CaseID"));
                System.out.println("PoliceStationID: " + resultSet.getInt("PoliceStationID"));
                System.out.println("RegistrationDate: " + resultSet.getDate("RegistrationDate"));
                System.out.println("ReportingOfficer: " + resultSet.getString("ReportingOfficer"));
                System.out.println("Notes: " + resultSet.getString("Notes"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertWantedCriminalsData(Connection connection) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter CriminalID: ");
            int criminalId = sc.nextInt();
            System.out.println("Enter PoliceStationID: ");
            int policeStationId = sc.nextInt();
            System.out.println("Enter WantedDate (YYYY-MM-DD): ");
            String wantedDate = sc.next();
            System.out.println("Enter Reward: ");
            double reward = sc.nextDouble();
            System.out.println("Enter Status (Active/Captured/Removed): ");
            String status = sc.next();

            String insertQuery = "INSERT INTO WantedCriminals (CriminalID, PoliceStationID, WantedDate, Reward, Status) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, criminalId);
                preparedStatement.setInt(2, policeStationId);
                preparedStatement.setString(3, wantedDate);
                preparedStatement.setDouble(4, reward);
                preparedStatement.setString(5, status);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted into WantedCriminals successfully.");
                } else {
                    System.out.println("Failed to insert data into WantedCriminals.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                sc.close();
            }
        }
    }

    private static void retrieveWantedCriminalsData(Connection connection) {
        String query = "SELECT * FROM WantedCriminals";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("WantedCriminals Data are: ");
            while (resultSet.next()) {
                System.out.println("WantedID: " + resultSet.getInt("WantedID"));
                System.out.println("CriminalID: " + resultSet.getInt("CriminalID"));
                System.out.println("PoliceStationID: " + resultSet.getInt("PoliceStationID"));
                System.out.println("WantedDate: " + resultSet.getDate("WantedDate"));
                System.out.println("Reward: " + resultSet.getBigDecimal("Reward"));
                System.out.println("Status: " + resultSet.getString("Status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
