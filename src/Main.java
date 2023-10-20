import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        File info = new File("INFO.txt");
        ArrayList<String> users = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(info);
            while(fileScanner.hasNextLine())
            {
                users.add(fileScanner.nextLine());
            }
        }
        catch (IOException ex)
        {
           ex.printStackTrace();
        }


        boolean exitB = false;
        String choose;
        String ADMIN = "";

        while (!exitB) {
            choose = JOptionPane.showInputDialog("menu\n1. REGISTRATE\n2. INICIAR SESION\n3. LISTA DE USUARIOS\n4. EXIT");
            switch (choose) {
                case "1":
                    registration(users);
                    break;
                case "2":
                    logIn(users);
                    break;
                case "3":
                    userList(users);
                    break;
                case "4":
                    exitB=exit(users, exitB,info);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR");
            }
            System.out.println(users);
        }//while finish
        JOptionPane.showMessageDialog(null, "EXITING PROGRAM");

}

    public static String paswd() {
        String a = JOptionPane.showInputDialog("introducca la contrasena porfavor");
        String b = JOptionPane.showInputDialog("vuelva a introducir la contrasena");
        if (a.equals(b)) {
            return a;
        } else {
            JOptionPane.showMessageDialog(null, "pasword incorrect");
            return paswd();
        }
    }

    public static int coincidencia(ArrayList<String> users, String logIn) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(logIn)) {
                return i;

            }
        }
        return -1;
    }
        public static void registration(ArrayList<String> users) {
        String logIn = "";
        int check;

        if (coincidencia(users,"a")==-1)
        {
            logIn = JOptionPane.showInputDialog("introduzca su log In");
            int coinc = coincidencia(users, logIn);
            if (coinc >= 0) {

                String choice = JOptionPane.showInputDialog("user is already registered\ndo you want to log in\n1.yes\n2.no");
                if (choice.equals("1")) {
                    logIn(users, logIn);
                } else if (choice.equals("2")) {
                    JOptionPane.showMessageDialog(null,"operation cancelled");
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR");
                }
            } else {
                users.add(logIn);
                users.add(paswd());

                String chose =JOptionPane.showInputDialog("want to register as Admin y/n");
                if (chose.equals("y")) {
                    users.add("a");
                } else {
                    users.add("x");
                }
                JOptionPane.showMessageDialog(null,"user " + logIn + " registred");
            }
        }
        else {
            logIn = JOptionPane.showInputDialog("introduzca su log In");
            int coinc = coincidencia(users, logIn);
            if (coinc >= 0) {

                String choice = JOptionPane.showInputDialog("user is already registered\ndo you want to log in\n1.yes\n2.no");
                if (choice.equals("1")) {
                    logIn(users, logIn);
                } else if (choice.equals("2")) {
                    JOptionPane.showMessageDialog(null, "operation cancelled");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR");
                }
            } else {
                users.add(logIn);
                users.add(paswd());

                String chose = JOptionPane.showInputDialog("want to register as mod y/n");
                if (chose.equals("y"))
                {
                    String adminU = JOptionPane.showInputDialog("introduce el usuario de ADMIN");
                    int cell=coincidencia(users,adminU);
                    if (cell==-1) {
                        JOptionPane.showMessageDialog(null,"user dont exists");
                        users.add("x");
                    }
                    else {
                        check=coincidencia(users,adminU);
                        if (!users.get(check+2).equals("a"))
                        {
                            JOptionPane.showMessageDialog(null, "user isnt Admin");
                            users.add("x");
                        }
                        else
                        {
                            String adminP = JOptionPane.showInputDialog("introduce la contrasena de "+adminU);

                            if (users.get(check+1).equals(adminP))
                            {
                                users.add("m");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"la contrasena no es correcta");
                                users.add("x");
                            }

                        }
                    }


                } else {
                    users.add("x");
                }
                JOptionPane.showMessageDialog(null, "user " + logIn + " registred");
            }
        }
    }

    public static void registration(ArrayList<String> users, String logIn) {
        users.add(logIn);
        users.add(paswd());
        String chose = JOptionPane.showInputDialog("want to register as mod y/n");

        if (chose.equals("y")) {
            users.add("m");
        } else {
            users.add("x");
        }
        JOptionPane.showMessageDialog(null,"user " + logIn + " registred");

    }

    public static void logIn(ArrayList<String> users) {
        String userName = "";
        String pswd = "";
        int coincRow = 0;
        userName = JOptionPane.showInputDialog("introduzca su usuario");
        coincRow = coincidencia(users, userName);
        if (coincRow == -1) {
            String choice =JOptionPane.showInputDialog("usuario no registrado\n want to register it\n1.yes\n2.no");

            if (choice.equals("1")) {
                registration(users);
            } else if (choice.equals("2")) {
                JOptionPane.showMessageDialog(null,"operation cancelled");
            } else {
                System.out.println("ERROR");
            }
        } else {
            pswd =JOptionPane.showInputDialog("introduzca la contrasena");
            if (pswd.equals(users.get(coincRow+1))) {
                JOptionPane.showMessageDialog(null,"you are in");
            } else {
                JOptionPane.showMessageDialog(null,"contrasea incorrecta");
            }
        }
    }

    public static void logIn(ArrayList<String> users,String logIn) {
        String pswd =JOptionPane.showInputDialog("introduzca la contrasena");

        int coincRow = coincidencia(users, logIn);
        if (pswd.equals(users.get(coincRow+1))) {
            JOptionPane.showMessageDialog(null,"you are in");
        } else {
            JOptionPane.showMessageDialog(null,"la contrasena no es correcta");
        }

    }

    public static void userList(ArrayList<String> users) {
        int coinc = 0;
        String pasw = "";
        String choose = JOptionPane.showInputDialog("do you want to have SUPER MEGA ULTRA HYPER ADMIN acces y/n");
        switch (choose) {
            case ("y"):
                String user =JOptionPane.showInputDialog("introduzca su usuario");
                coinc = coincidencia(users, user);
                if (coinc == -1) {
                    JOptionPane.showMessageDialog(null,"user dont exist");
                } else {
                    if (users.get(coinc+2).equals("x")) {
                        JOptionPane.showMessageDialog(null,"user isn't a mod");
                            nArray(users);
                    } else {
                        pasw =JOptionPane.showInputDialog("introduzca su contrasena");
                        if (pasw.equals(users.get(coinc+1))) {
                            superArray(users);
                        } else {
                            JOptionPane.showMessageDialog(null,"password incorrect");
                        }
                    }
                }

                break;
            case ("n"):
                nArray(users);
                break;
            default:
                JOptionPane.showMessageDialog(null,"ERROR");
        }
    }
    public static void nArray(ArrayList<String>users)
    {
        String arraySim="";
        for (int i = 0; i <users.size() ; i+=3)
        {
            if (users.get(i)==null)
            {
                break;
            }
            else {
                arraySim += users.get(i) + "\n";
            }
        }
        JOptionPane.showMessageDialog(null,arraySim);
    }
    public static void superArray(ArrayList<String> users)
    {
        String arraySim="";
        for (int i = 0; i <users.size() ; i++)
        {
            if (users.get(i)==null)
            {
                break;
            }
            arraySim+= users.get(i)+"\n";
        }
        JOptionPane.showMessageDialog(null,arraySim);
    }

    public static boolean exit (ArrayList<String>users,boolean exitB,File info)
    {
        try {
            FileWriter fw =new FileWriter(info);
            FileWriter cleaner =new FileWriter(info);
            String check = "";
            check = JOptionPane.showInputDialog(null, "sure you want to exit?  y/n");
            if (check.equals("y")) {
                check = JOptionPane.showInputDialog(null, "save information?  y/n");
                if (check.equals("y")) {
                    JOptionPane.showMessageDialog(null, "new information saved");
                    cleaner.write("");
                    cleaner.close();
                    for (int i = 0; i < users.size(); i++) {
                        fw.write(users.get(i)+"\n");
                    }
                    fw.close();
                    exitB = true;
                    return exitB;
                } else {
                    exitB = true;
                    return exitB;
                }
            } else {
                return exitB;
            }
        }
        catch(IOException a)
        {
            a.printStackTrace();
        }
        return exitB;
    }
}