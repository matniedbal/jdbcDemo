package eu.mrndesign.matned.jdbc.test;

public class Main {
    public static void main(String[] args) {
        MysqlConnectionParameters mcp = new MysqlConnectionParameters();

        System.out.println("login: "+ mcp.getUsername());
        System.out.println("login: "+ mcp.getPassword());

    }
}
