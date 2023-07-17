package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

public class Controller_4_1  {
    private static Connection connection;

    static void Write_txt(List<String[]> list) {
        for(String[] question : list){
            String insert = "Insert into quest_list(name,text,choice1,grade1,choice2,grade2,choice3,grade3,choice4,grade4,choice5,grade5) values (";
            String insert_cate = "Insert into cate_ques(cate,ques,text) values ('default',N'"+question[0]+"',N'"+question[1]+"')";
            for (int i = 0; i < question.length; i++) {
                if (i != question.length - 1) {
                    if (i % 2 == 1 && i != 1)
                        insert = insert + question[i] + ",";
                    else
                        insert = insert + "N'<p>" + question[i] + "</p>',";
                } else
                    insert = insert + question[i] + ")";
            }

            try {
                // Connection code from the previous step
                Statement statement = connection.createStatement();
                statement.executeQuery(insert);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                // Connection code from the previous step
                Statement statement = connection.createStatement();
                statement.executeQuery(insert_cate);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    static String Read_txt(File myfile) {
        try {
            Scanner myReader = new Scanner(myfile);
            int cnt = 0, ques_line = 0, ques_id = 1, check = 100000;
            List<String[]> list = new ArrayList<>();
            String[] question = {"","","","0","","0","","0","","0","","0"};
            char num_ques = 'A';
            while (myReader.hasNextLine()) {
                cnt++;
                ques_line++;
                String text = myReader.nextLine();
                if(text.length() == 0 ) {
                    if(ques_line == 0)
                        continue;
                    else
                        check = Math.min(check, cnt);
                }


                if(ques_line == 1) {
                    question[0] = "Question " + ques_id;
                    question[1] = text;
                }
                else {
                    if(text.length() < 4)
                        check = Math.min(check,cnt);
                    if(text.substring(0,3).equals(num_ques+". ")) {
                        if(text.charAt(3)==' ')
                            check = Math.min(check,cnt);
                        else{
                            question[(ques_line-1)*2] = text.substring(3);
                        }
                    }
                    else {
                        if(text.length() != 9 || !text.substring(0,8).equals("ANSWER: ") || !( text.charAt(8) >= 'A' && text.charAt(8) < num_ques) || num_ques<'C' )
                            check = Math.min(check,cnt);
                        else {
                            int correct_ans = text.charAt(8) - 'A' + 1;
                            question[correct_ans*2 + 1] = String.valueOf(1);
                            list.add(question);
                            question = new String[]{"","","","0","","0","","0","","0","","0"};
                            ques_line = -1;
                            num_ques = 'A';
                            ques_id ++;
                            continue;
                        }
                    }
                    num_ques++;
                }
                if(check != 100000)
                    break;
            }
            myReader.close();
            if(check != 100000)
                return ("Error at " + check);
            else {
                Write_txt(list);
                return ("Success "+list.size());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred.";
        }
    }

    static String Read_docx(File myfile) {
        try {
            FileInputStream fis = new FileInputStream(myfile.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            int cnt = 0, ques_line = 0, ques_id = 1, check = 100000;
            List<String[]> list = new ArrayList<>();
            String[] question = {"","","","0","","0","","0","","0","","0"};
            char num_ques = 'A';
            for (XWPFParagraph para : paragraphs) {
                cnt++;
                ques_line++;
                String text = para.getText();
                if(text.length() == 0 ) {
                    if(ques_line == 0)
                        continue;
                    else
                        check = Math.min(check, cnt);
                }


                if(ques_line == 1) {
                    question[0] = "Question " + ques_id;
                    question[1] = text;
                }
                else {
                    if(text.length() < 4)
                        check = Math.min(check,cnt);
                    if(text.substring(0,3).equals(num_ques+". ")) {
                        if(text.charAt(3)==' ')
                            check = Math.min(check,cnt);
                        else{
                            question[(ques_line-1)*2] = text.substring(3);
                        }
                    }
                    else {
                        if(text.length() != 9 || !text.substring(0,8).equals("ANSWER: ") || !( text.charAt(8) >= 'A' && text.charAt(8) < num_ques) || num_ques<'C' )
                            check = Math.min(check,cnt);
                        else {
                            int correct_ans = text.charAt(8) - 'A' + 1;
                            question[correct_ans*2 + 1] = String.valueOf(1);
                            list.add(question);
                            question = new String[]{"","","","0","","0","","0","","0","","0"};
                            ques_line = -1;
                            num_ques = 'A';
                            ques_id ++;
                            continue;
                        }
                    }
                    num_ques++;
                }
                if(check != 100000)
                    break;
            }
            fis.close();
            if(check != 100000)
                return ("Error at " + check);
            else {
                Write_txt(list);
                return ("Success "+list.size());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void Read_img(String folder_path) {
        try {
            File myObj = new File(folder_path);
            FileInputStream fis = new FileInputStream(myObj.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFPictureData> piclist = document.getAllPictures();

            Iterator<XWPFPictureData> iterator=piclist.iterator();
            int i=0;
            while(iterator.hasNext()){
                XWPFPictureData pic=iterator.next();
                byte[] bytepic=pic.getData();
                BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytepic));
                System.out.println("abc");
                ImageIO.write(imag, "jpg", new File("D:/Work/Java/OOP/Ques_list/abc"+i+".jpg"));
                i++;
            }

            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   

    static String Check_aiken(File myfile) {
        connectDatabase();
        String name = myfile.getName();
        int lastIndexOf = name.lastIndexOf(".");

        //Crete_txt(folder_path);
        //Write_txt(folder_path);
        if(name.substring(lastIndexOf).equals(".txt"))
            return Read_txt(myfile);
        if(name.substring(lastIndexOf).equals(".docx"))
            return Read_docx(myfile);
        return "Sai định dạng";
    }
    static void connectDatabase() {
        String connectionString = "jdbc:sqlserver://localhost:1433;Database=BTL;encrypt=true;trustServerCertificate=true;";
        try
        {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString,"sa","123456");
            System.out.println("OK");
        }
        catch(Exception e)
        {
            System.out.println("error connect sql");
            e.printStackTrace();
        }
    }
}
