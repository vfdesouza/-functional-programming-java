package application;

import entities.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {


        Scanner input = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        String path = "C:\\DEV\\7_DevSuperior\\Curso Java - POO + Projetos\\projects\\in.txt";

        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String vet[] = line.split(",");
                list.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print("Filter by salary: ");
        double inputUser = input.nextDouble();

        List<String> listEmail = list.stream().
                filter(empSalary -> empSalary.getSalary() > inputUser).
                map(empEmail -> empEmail.getEmail())
                .sorted()
                .toList();

        System.out.println("Email of people whose salary is more than: " + inputUser);
        listEmail.forEach(System.out::println);

        double sum = list.stream().
                filter(x -> x.getName().charAt(0) == 'M').
                map(empSalary -> empSalary.getSalary()).
                reduce(0.0, (x, y) -> x + y);

        System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
        input.close();
    }
}
