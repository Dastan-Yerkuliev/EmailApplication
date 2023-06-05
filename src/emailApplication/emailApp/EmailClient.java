package emailApplication.emailApp;

import emailApplication.entity.Email;
import emailApplication.entity.User;

import java.util.*;

public class EmailClient {
    private User currentUser;
    private Scanner scanner;
    private List<User> users;
    private List<Email> emails;


    public EmailClient() {
        users = new ArrayList<>();
        currentUser = null;
        emails = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void registerUser() {
        System.out.print("Enter your name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        String email;
        boolean isEmailValid;

        do {
            System.out.print("Enter email address (name only): ");
            email = scanner.nextLine();

            if (!email.contains("@")) {
                email += "@project.com";
                isEmailValid = true;
            } else {
                System.out.println("Incorrect email entry. Enter only the name.");
                isEmailValid = false;
            }
        } while (!isEmailValid);


        System.out.println("Do you want to generate a password or enter your desired password? " +
                "\n1.Generate a password" +
                "\n2.Enter the desired password");
        System.out.print("Choose an action: ");
        int choice = Integer.parseInt(scanner.nextLine());

        String password = null;
        boolean isPasswordValid;
        switch (choice) {
            case 1 -> {
                password = generatePassword(8);
                System.out.println("Generated password: " + password);
            }
            case 2 -> {
                do {
                    System.out.print("Enter the desired password (minimum 8 characters and 1 capital letter): ");
                    password = scanner.nextLine();

                    if (password.length() >= 8 && containsUppercase(password)) {
                        isPasswordValid = true;
                    } else {
                        System.out.println("The password does not meet the requirements.");
                        isPasswordValid = false;
                    }
                } while (!isPasswordValid);
            }
            default -> System.out.println("Enter the correct choice.");
        }

        User newUser = new User(firstName, lastName, email, password);
        users.add(newUser);
        System.out.println("Registration successfully completed.");
    }

    public void loginUser() {
        String email;
        boolean isEmailValid;

        do {
            System.out.print("Enter email address (name only): ");
            email = scanner.nextLine();

            if (!email.contains("@")) {
                email += "@project.com";
                isEmailValid = true;
            } else {
                System.out.println("Incorrect email entry. Enter only the name.");
                isEmailValid = false;
            }
        } while (!isEmailValid);

        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
                return;
            }
        }
        System.out.println("Incorrect email or password.");
    }

    public void viewCurrentUser() {
        if (currentUser == null) {
            System.out.println("You must be registered to enter the email account.");
            return;
        }

        System.out.println("Current User: " + currentUser.getFirstName() + " " + currentUser.getLastName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Password: " + currentUser.getPassword());
    }

    public boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    public void changePassword() {
        if (currentUser == null) {
           System.out.println("You must be logged into your email account to change the password.");
            return;
        }

        System.out.print("Enter the current password: ");
        String currentPassword = scanner.nextLine();

        if(!currentUser.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect current password.");
            return;
        }

            String newPassword;
            boolean isPasswordValid;

            do {
                System.out.print("Enter a new password (minimum 8 characters, minimum 1 uppercase letter): ");
                newPassword = scanner.nextLine();

                if (newPassword.length() >= 8 && containsUppercase(newPassword)) {
                    isPasswordValid = true;
                } else {
                    System.out.println("The new password does not meet the requirements.");
                    isPasswordValid = false;
                }
            } while (!isPasswordValid);

            currentUser.setPassword(newPassword);
            System.out.println("Password has been successfully changed.");

            System.out.println("Do you want to generate a password or enter your desired password? " +
                    "\n1.Generate a password" +
                    "\n2.Enter the desired password");
            System.out.print("Choose an action: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    newPassword = generatePassword(8);
                    System.out.println("Generated password: " + newPassword);
                }
                case 2 -> {
                    do {
                        System.out.print("Enter the desired password (minimum 8 characters and 1 capital letter): ");
                        newPassword = scanner.nextLine();

                        if (newPassword.length() >= 8 && containsUppercase(newPassword)) {
                            isPasswordValid = true;
                        } else {
                            System.out.println("The password does not meet the requirements.");
                            isPasswordValid = false;
                        }
                    } while (!isPasswordValid);
                }
                default -> System.out.println("Enter the correct choice.");
            }
        }

    public String generatePassword(int length) {
        Random random = new Random();
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        String value = capitalChars + smallChars + numbers + symbols;
        String password = "";
        for(int i = 0; i < length; i++) {
            password = password + value.charAt(random.nextInt(value.length()));
        }
        return password;
    }

    public void switchUser() {
        if (currentUser == null) {
            System.out.println("You must be registered to enter the email account.");
            return;
        }

        String email;
        boolean isEmailValid;
        do {
            System.out.print("Enter the user's email address (name only): ");
            email = scanner.nextLine();

            if (!email.contains("@")) {
                email += "@project.com";
                isEmailValid = true;
            } else {
                System.out.println("Incorrect email entry. Enter only the name.");
                isEmailValid = false;
            }
        } while (!isEmailValid);


        String password;
        boolean isPasswordValid;
        do {
            System.out.print("Enter the user's password (minimum 8 characters and 1 capital letter): ");
            password = scanner.nextLine();

            if (password.length() >= 8 && containsUppercase(password)) {
                isPasswordValid = true;
            } else {
                System.out.println("The password does not meet the requirements.");
                isPasswordValid = false;
            }
        } while (!isPasswordValid);

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Switched to email account: " + email);
                return;
            }
        }
        System.out.println("No user with this email was found.");
    }

    public void sendEmail() {
        if (currentUser == null) {
            System.out.println("You must be logged into your email account to send an email.");
            return;
        }

        System.out.print("Enter the recipient's email address (name only): ");
        String recipientEmail = scanner.nextLine();

        if (!recipientEmail.contains("@")) {
            recipientEmail += "@project.com";
        } else {
            System.out.println("Incorrect email entry. Enter only the name.");
            return;
        }

        System.out.print("Enter the subject line of the email: ");
        String subject = scanner.nextLine();

        System.out.print("Enter the text of the letter: ");
        String body = scanner.nextLine();

        Email email = new Email(currentUser.getEmail(), recipientEmail, subject, body);
        emails.add(email);

        System.out.println("The letter was successfully sent.");
    }

    public void viewEmails() {
        if (currentUser == null) {
            System.out.println("You must be logged into your email account to view an email.");
            return;
        }

        System.out.println("List of letters:");
        for (Email email : emails) {
            System.out.println("Sender: " + email.getSender());
            System.out.println("Subject: " + email.getSubject());
            System.out.println("Body: " + email.getContent());
        }
    }

    public void readEmail() {
        if (currentUser == null) {
            System.out.println("You must be logged into your email account to read an email.");
            return;
        }

        if (emails.isEmpty()) {
            System.out.println("No emails to read.");
            return;
        }

        System.out.print("Enter the letter number: ");
        int emailIndex = Integer.parseInt(scanner.nextLine());
        if (emailIndex >= 1 && emailIndex <= emails.size()) {
            Email email = emails.get(emailIndex - 1);
            System.out.println("Sender: " + email.getSender());
            System.out.println("Subject: " + email.getSubject());
            System.out.println("Message: " + email.getContent());
        } else System.out.println("Incorrect letter number.");
    }

    public void deleteEmail() {
        if (currentUser == null) {
            System.out.println("You must be logged into your email account to delete an email.");
            return;
        }

        if (emails.isEmpty()) {
            System.out.println("No emails to delete.");
            return;
        }

        System.out.print("Enter the letter number: ");
        int emailIndex = Integer.parseInt(scanner.nextLine());
        if (emailIndex >= 1 && emailIndex <= emails.size()) {
            Email email = emails.remove(emailIndex - 1);
            System.out.println("The letter is deleted: " + email.getSubject());
        } else System.out.println("Incorrect letter number.");
    }
}