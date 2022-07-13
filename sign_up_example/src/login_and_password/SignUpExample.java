package login_and_password;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SignUpExample {

    private final Scanner scanner = new Scanner(System.in);
    private String login;
    private String scLogin;
    private String password;
    private String scPassword;
    private final static String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    private final static String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";


    private final Set<String> logAndPass = new TreeSet<>(Comparator.comparingInt(String::length));


    private SignUpExample() {
        setLogin();
        setPassword();
        SignUp();
        writeLogAndPassInfo();
    }

    public static SignUpExample openSignUpWindow() {

        return new SignUpExample();

    }
    private boolean getValidLogin() {

        System.out.println("Please enter login: ");
        scLogin = scanner.next();


        Pattern pLogin = Pattern.compile("^[a-zA-Z]*$", Pattern.CASE_INSENSITIVE); /*Было: [a-z]{8,12}*/

        Matcher mLogin = pLogin.matcher(scLogin);
        mLogin.find();

        return mLogin.matches();
    }


    private void setLogin() {

        if (getValidLogin()) {
            this.login = scLogin.toLowerCase();

        } else {
            this.login = INCORRECT_LOGIN;
            System.out.println("Incorrect format");
        }


    }

    private boolean getValidPassword() {

        System.out.println("Please enter password: ");
        scPassword = scanner.next();


        Pattern pPass = Pattern.compile("[A-Za-z0-9]+");

        Matcher mPass = pPass.matcher(scPassword);

        mPass.find();

        return mPass.matches();
    }


    private void setPassword() {

        if (getValidPassword()) {
            this.password = scPassword;

        } else {
            this.password = INCORRECT_PASSWORD;
            System.out.println("Incorrect format");

        }

    }


    @Override
    public String toString() {
        return '\n' + "Login: " + login + '\n' + "Password: " + password;
    }

    private void SignUp() {


        if (login.equals(INCORRECT_LOGIN) && password.equals(INCORRECT_PASSWORD)||
        login.equals(INCORRECT_LOGIN)||password.equals(INCORRECT_PASSWORD)) {


            System.out.println("\nYour login or password incorrect");
            openSignUpWindow();

        } else {

            logAndPass.add(toString());
        }


    }



    private void writeLogAndPassInfo() {
        File f = new File("src/login_and_password/loginAndPassword.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {

            logAndPass.forEach(accountInfo -> {

                try {
                    bw.write(accountInfo);
                    bw.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

        } catch (IOException ioE) {
            System.out.println(ioE);
        } catch (Exception ex) {
            System.out.println("Exception");
        }


    }


}
