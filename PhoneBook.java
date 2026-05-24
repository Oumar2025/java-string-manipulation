import java.io.*;
import java.util.*;

public class PhoneBook {

    // File name
    static final String FILE_NAME = "phone.txt";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {

            // Display menu
            System.out.println("\n===== PHONE CONTACT MENU =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            if (input.hasNextInt()) {

                choice = input.nextInt();
                input.nextLine();

            } else {

                System.out.println("Invalid input! Please enter a number.");
                input.nextLine();
                choice = 0;
            }

            switch (choice) {

                case 1:
                    addContact(input);
                    break;

                case 2:
                    viewContacts();
                    break;

                case 3:
                    searchContact(input);
                    break;

                case 4:
                    deleteContact(input);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        input.close();
    }

    // Function to add new contact
    public static void addContact(Scanner input) {

        try {

            System.out.print("Enter name: ");
            String name = input.nextLine();

            System.out.print("Enter phone number: ");
            String phone = input.nextLine();

            // Check duplicate phone number
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.contains(phone)) {
                    System.out.println("Phone number already exists!");
                    reader.close();
                    return;
                }
            }

            reader.close();

            // Save contact
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));

            File file = new File(FILE_NAME);

            // Add new line only if file already has content
            if (file.length() > 0) {
                writer.newLine();
            }

            writer.write(name + " - " + phone);

            writer.close();

            System.out.println("Contact added successfully!");

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    // Function to display all contacts
    public static void viewContacts() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            String line;

            System.out.println("\n===== CONTACT LIST =====");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Function to search contact
    public static void searchContact(Scanner input) {

        try {

            System.out.print("Enter name or phone number to search: ");
            String keyword = input.nextLine().toLowerCase();

            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {

                if (line.toLowerCase().contains(keyword)) {

                    System.out.println("Found: " + line);
                    found = true;
                }
            }

            reader.close();

            if (!found) {
                System.out.println("Contact not found.");
            }

        } catch (IOException e) {
            System.out.println("Error searching file.");
        }
    }

    // Function to delete contact
    public static void deleteContact(Scanner input) {

        try {

            System.out.print("Enter name or phone number to delete: ");
            String keyword = input.nextLine().toLowerCase();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean deleted = false;

            while ((line = reader.readLine()) != null) {

                // Skip matching contact
                if (line.toLowerCase().contains(keyword)) {

                    deleted = true;
                    continue;
                }

                writer.write(line);
                writer.newLine();
            }

            writer.close();
            reader.close();

            // Replace old file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (deleted) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Contact not found.");
            }

        } catch (IOException e) {
            System.out.println("Error deleting contact.");
        }
    }
}