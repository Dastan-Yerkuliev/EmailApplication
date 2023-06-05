package emailApplication.emailApp;

import java.util.Scanner;

public class Options {
    private EmailClient emailClient;

    public Options() {
        emailClient = new EmailClient();
    }
    public void optionsForUser() {
        Scanner scanner = new Scanner(System.in);
        boolean isSelectedOption = true;
        while (isSelectedOption) {
        System.out.println("Please select the option you need." +
                "\n1.Registration" +
                "\n2.Login" +
                "\n3.View information about the current user" +
                "\n4.Change password" +
                "\n5.Switch user" +
                "\n6.Send email" +
                "\n7.View Emails" +
                "\n8.Read Email" +
                "\n9.Delete email" +
                "\n10.Exit");
        System.out.println("Choose an action:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> emailClient.registerUser();
            case 2 -> emailClient.loginUser();
            case 3 -> emailClient.viewCurrentUser();
            case 4 -> emailClient.changePassword();
            case 5 -> emailClient.switchUser();
            case 6 -> emailClient.sendEmail();
            case 7 -> emailClient.viewEmails();
            case 8 -> emailClient.readEmail();
            case 9 -> emailClient.deleteEmail();
            case 10 -> {
                System.out.println("Thank you for using application. See you later.");
                isSelectedOption = false;
            }
            default -> System.out.println("Invalid choice. Please try again");
        }
            System.out.println();
    }
}
}
