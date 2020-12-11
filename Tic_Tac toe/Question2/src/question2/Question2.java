/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileOutputStream;
import static java.nio.file.StandardOpenOption.CREATE;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
/**
 *
 * @author @RayOmarr
 */
public class Question2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //The Main Method
        String [][] game={{"-","-","-"},{"-","-","-"},{"-","-","-"}};
        String playerX, playerO, selected,string;
        int row, col, round,player=1;
        boolean winner=false,repeat=true;
        Scanner scan=new Scanner(System.in);
        
        System.out.println("WELCOME TO THE X AND O GAME!!!!!!"
                + "\n******************************************"
                + "\nEnter a (1) to start the game or (2) to view the game history");
        selected=scan.nextLine();
        if(selected.equals("1"))
            //while loop
        {
            while (repeat==true)
            {
                System.out.println("PLEASE ENTER PLAYER (X) NAME:");
                playerX=scan.nextLine();
                //scanner class used
                System.out.println("PLEASE ENTER PLAYER (O) NAME:");
                playerO=scan.nextLine();
                round=1;
                
                while(round<11 && winner==false)
                {
                    System.out.println("Round "+round+":"
                    + "\n******************************************"
                   + "\n 0 1 2");
                    for(int y=0;y<3;++y)
                        {
                            System.out.println(y+"|"+game[y][0]+"|"+game[y][1]+"|"+game[y][2]+"|");
                        }
                    
                    if(player==1)
                    {
                        System.out.print("******************************************\n"
                                + playerX+" PLEASE ENTER ROW NUMBER: ");
                        row=scan.nextInt();
                        System.out.print(playerX+" PLEASE ENTER COLUMN NUMBER: ");
                        col=scan.nextInt();
                        game[row][col]="X";
                        player=2;
                    } else if(player==2)
                    {
                        System.out.print("******************************************\n"
                                + playerO+" PLEASE ENTER ROW NUMBER: ");
                        row=scan.nextInt();
                        System.out.print(playerO+" PLEASE ENTER COLUMN NUMBER: ");
                        col=scan.nextInt();
                        game[row][col]="O";
                        player=1;
                    }
                    selected=winner(game);
                    
                    if(selected.equals("") != false )
                    {
                        winner=true;
                    }                   
                    switch (selected) {
                        case "":
                            System.out.println("DRAW!!!");
                            break;
                        case "X":
                            System.out.println("CONGRATULATIONS "+playerX+" YOU ARE THE WINNER!!!"
                                    + "\nWould you like to save the game? Enter a (1) to save the game or any other key to continue");
                            selected=scan.nextLine();
                            player=1;
                            break;
                        case "O":
                            System.out.println("CONGRATULATIONS "+playerO+" YOU ARE THE WINNER!!!"
                                    + "\nWould you like to save the game? Enter a (1) to save the game or any other key to continue");
                            selected=scan.nextLine();
                            player=2;
                            break;
                    }   
                        java.nio.file.Path file =Paths.get("winner.txt");
                        try
                        {
                            OutputStream output = Files.newOutputStream(file, CREATE);
                            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output));
                            if(player==1)
                            {
                                writer.append("\n*****************************************");
                                writer.newLine();
                                writer.append("Player Name: "+playerX);
                                writer.newLine();
                                Calendar Cal=Calendar.getInstance();
                                SimpleDateFormat sdf=new SimpleDateFormat();
                                writer.append("Game Date: "+sdf.format(Cal.getTime()));
                            }else if(player==2)
                            {
                                writer.append("\n*****************************************");
                                writer.newLine();
                                writer.append("Player Name: "+playerO);
                                writer.newLine();
                                Calendar Cal=Calendar.getInstance();
                                SimpleDateFormat sdf=new SimpleDateFormat();
                                writer.append("Game Date: "+sdf.format(Cal.getTime()));
                            }
                            writer.close();
                            output.close();
                            System.out.println("The game has been saved successfully ");
                        }catch(Exception e)
                        {
                            System.out.println(e.toString());
                        }
                    System.out.println("******************************************"
                            + "\nWould you like to play again? Press (x) to exit or any other key to play again");
                    selected=scan.nextLine();
                    if(selected.equals("x"))
                    {
                        repeat=false;
                        winner=true;
                    }
                    round++;
                }
            }
        }else if (selected.equals("2"))
        {
            java.nio.file.Path file =Paths.get("winner.txt");
            try
            {
                InputStream input=Files.newInputStream(file);
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));
                while ((string=reader.readLine())!=null)
                {
                    System.out.println(string);    
                }
                reader.close();
                input.close();
            }catch(Exception e)
            {
                System.out.println("Err:"+e.toString());
            }
        }        
    }
    public static String winner(String [][] a)
    {
        String winner=null;
        if(a[0][0].equals(a[0][1]) && a[0][1].equals(a[0][1]))
        {
            winner=a[0][0];
        }else if(a[1][0].equals(a[1][1]) && a[1][1].equals(a[1][1]))
        {
            winner=a[1][0];
        }else if(a[2][0].equals(a[2][1]) && a[2][1].equals(a[2][1]))
        {
            winner=a[2][0];
        }else if(a[0][0].equals(a[1][0]) && a[1][0].equals(a[2][0]))
        {
            winner=a[0][0];
        }else if(a[0][1].equals(a[1][1]) && a[1][1].equals(a[2][1]))
        {
            winner=a[0][1];
        }else if(a[0][2].equals(a[1][2]) && a[1][2].equals(a[2][2]))
        {
            winner=a[0][2];
        }else if(a[0][0].equals(a[1][1]) && a[1][1].equals(a[2][2]))
        {
            winner=a[0][0];
        }else if(a[2][0].equals(a[1][1]) && a[1][1].equals(a[0][2]))
        {
            winner=a[2][0];
        }
        return winner;
    }
}
