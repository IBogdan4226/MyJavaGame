package dev.codenmore.tilegame;

import java.sql.*;
public class SQLCLASS {
    public static Connection c=null;
    public static Statement stmt=null;
    public static void doSomething(String s){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            stmt = c.createStatement();
            stmt.executeUpdate(s);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void IterateThroughSQL( ){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Joc;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                int May2019 = rs.getInt("May2019");
                int May2018 = rs.getInt("May2018");
                float change = rs.getFloat("Change");
                String Prog = rs.getString("PrgLang");
                float Ratings = rs.getFloat("Ratings");


                System.out.print(May2019 +", ");
                System.out.print( May2018+", " );
                System.out.print( change+"%, " );
                System.out.print(  Prog+", " );
                System.out.print(  Ratings+"% " );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static int[][] returnMapMatrix(int level){
        int [][]matrix=new int[12][20];
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Map"+level+";" );
            rs.next();
            for(int i=0;i<12;i++){
                matrix[i][0]=rs.getInt("C1");
                matrix[i][1]=rs.getInt("C2");
                matrix[i][2]=rs.getInt("C3");
                matrix[i][3]=rs.getInt("C4");
                matrix[i][4]=rs.getInt("C5");
                matrix[i][5]=rs.getInt("C6");
                matrix[i][6]=rs.getInt("C7");
                matrix[i][7]=rs.getInt("C8");
                matrix[i][8]=rs.getInt("C9");
                matrix[i][9]=rs.getInt("C10");
                matrix[i][10]=rs.getInt("C11");
                matrix[i][11]=rs.getInt("C12");
                matrix[i][12]=rs.getInt("C13");
                matrix[i][13]=rs.getInt("C14");
                matrix[i][14]=rs.getInt("C15");
                matrix[i][15]=rs.getInt("C16");
                matrix[i][16]=rs.getInt("C17");
                matrix[i][17]=rs.getInt("C18");
                matrix[i][18]=rs.getInt("C19");
                matrix[i][19]=rs.getInt("C20");

                rs.next();
            }
            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return matrix;


    }
    public static void addScore(String name,int scor,int nivel,int timp){
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String query = "SELECT COUNT(*) FROM Scor WHERE Name LIKE '"+ name +"'";
            ResultSet rs = stmt.executeQuery(query);
            boolean recordFound =rs.getBoolean(1);
            if (recordFound) {
                System.out.println("Exista");
                String sql = "UPDATE Scor set Score ="+scor+",Level ="+nivel+",Timp ="+timp+" where Name='"+name+"';";
                stmt.executeUpdate(sql);

            }
            else {
                String sql = "INSERT INTO Scor (Name,Score,Level,Timp) " +
                        "VALUES ('"+name+"',"+ scor + "," + nivel + "," + timp + ");";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void addSave(int scor,int nivel,int viata){
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO Save (Scor,Nivel,Health) " +
                    "VALUES ("+ scor + "," + nivel +","+ viata +");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static int[] getScore(){
        int scor=0;
        int level=0;
        int viata=1;
        int a[]=new int[3];
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:JocPaoo.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Save;" );
            rs.next();
            scor = rs.getInt("Scor");
            level = rs.getInt("Nivel");
            viata = rs.getInt("Health");
            rs.close();
            stmt.executeUpdate("DELETE FROM Save;");
            stmt.close();
            c.commit();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        a[0]=scor;
        a[1]=level;
        a[2]=viata;
        return a;

    }

}
