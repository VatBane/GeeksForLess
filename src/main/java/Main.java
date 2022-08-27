import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int key = 0;
        Scanner sc = new Scanner(System.in);
        String expr = "";
        float result;


        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GeeksForLess", "postgres", "Strikeball35");
            do {
                if (key == 1) {
                    System.out.println("Enter expression: ");
                    expr = sc.nextLine();
                    result= calculator.solve(expr);
                    PreparedStatement check = connection.prepareStatement("SELECT * FROM expressions where expr = ?");
                    check.setString(1, expr);
                    ResultSet rs = check.executeQuery();
                    if (!rs.next()) {
                        PreparedStatement prs = connection.prepareStatement("INSERT INTO expressions(expr, res) VALUES (?, ?)");
                        prs.setString(1, expr);
                        prs.setFloat(2, result);
                        prs.execute();
                    }
                } else if (key == 2) {
                    System.out.println("Enter a number:");
                    result = sc.nextFloat();
                    PreparedStatement prs = connection.prepareStatement("SELECT expr FROM expressions WHERE res = ?");
                    prs.setFloat(1, result);
                    ResultSet rs = prs.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString("expr"));
                    }
                }
                System.out.println("Choose action:\n1. Solve expression;\n2. Check existing solutions;\n0. Exit;");
                try {
                    key = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                    e.printStackTrace();
                    key = 3;
                }

            } while (key !=0 );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
