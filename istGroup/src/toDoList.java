import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class toDoList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("to_do_list.txt"));
        String line = br.readLine();

        List<String> todoList = new ArrayList<>();

        while (line!=null)
        {
            todoList.add(line);
            line = br.readLine();
        }

        Scanner input = new Scanner(System.in);

        boolean repeat = true;
        String option;
        while (repeat)
        {
            option = JOptionPane.showInputDialog("What do you want to do?\n" +
                    "Enter '1' to add things to the do list\n" +
                    "Enter '2' to remove something from the list\n" +
                    "Enter '3' to view the list\n" +
                    "Enter '4' to sort the list\n" +
                    "Enter '-1' to save and quit\n" +
                    "Enter your Option: \n");


//            System.out.println("What do you want to do?");
//            System.out.println("Enter '1' to add things to the do list");
//            System.out.println("Enter '2' to remove something from the list");
//            System.out.println("Enter '3' to view the list");
//            System.out.println("Enter '4' to sort the list");
//            System.out.println("Enter '-1' to save and quit");
//            System.out.print("Enter your Option: ");
//            option = input.next();
            if(option.equals("1"))
            {
                //System.out.println("What do you want to add to the list (enter -1 to stop adding) ");
                String addString = addItem();

                while(!addString.equals("-1"))
                {
                    todoList.add(addString);
                    addString = addItem();
                }

            }
            else if(option.equals("2"))
            {

                todoList.remove(removeList(todoList));
            }
            else if(option.equals("3"))
            {
                JOptionPane.showMessageDialog(null,printTodoList(todoList));
            }
            else if (option.equals("4"))
            {
                todoList.equals(sortList(todoList));
            }
            else if(option.equals("-1"))
            {
                saveList(todoList);
                repeat=false;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"You have entered an incorrect option");
                //System.out.println("You have entered an incorrect option");
            }
        }
    }


    private static void saveList(List<String> todoList) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("to_do_list.txt");
        for (int x =0;x<todoList.size();x++ )
        {
            writer.println(todoList.get(x));
        }
        writer.close();

    }

    //sorts the list alphabetically
    private static List<String> sortList(List<String> todoList) {
        Collections.sort(todoList);
        return todoList;
    }

    //prints the list
    private static String printTodoList(List<String> todoList) {
        //System.out.println("Your To Do List");
        String list = "Your To Do List \n";
        for (int x =0;x<todoList.size();x++)
        {
            list = list + (x+1) + ") " + todoList.get(x) +"\n";

            //System.out.println(todoList.get(x));
        }
        return list;


    }

    //removes item from the list
    private static String removeList(List<String> todoList) {
        Scanner input = new Scanner(System.in);
        String removeList = null;
        boolean validInput;
        do {
            removeList = JOptionPane.showInputDialog(printTodoList(todoList)+"\nWhat do you want to remove from your list: ");

            if(todoList.contains(removeList)) {
                validInput = true;
            }
            else if(removeList.equals("2")){
                validInput = true;
            }
            else {
                validInput = false;
                JOptionPane.showMessageDialog(null,"The item entered is not in the list try again or hit '2' to go back");
                //System.out.println("The item entered is not in the list try again or hit '2' to go back");
            }
        }while (validInput==false);
        return removeList;
    }


    //adds item to the list
    private static String addItem()  {
        Scanner input = new Scanner(System.in);
        String addtoList = JOptionPane.showInputDialog("What do you want to add to the list (enter -1 to stop adding) ");
        //addtoList = input.nextLine();
        return addtoList;

    }
}
