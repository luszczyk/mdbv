package net.luszczyk.mdbv.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class MysqlSaveTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mdbvdb";
    private static final String DB_USER = "mdbv";
    private static final String DB_PASS = "mdbvdupa";
    private static final String FILE_NAME = "gizmo.ogv";

    private Connection conn;


    @Test
    public void testTrue() {

    }

    //@Before
    public void connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

    //@Test
    public void saveImage() {


        String[] files = new String[]{"008.jpg", "Cobalt_Drafting.ogv", "demo.mp4", "gizmo.ogv",
                "Regular_Show_Trash_Boat.ogv", "SpaceX_Falcon_9.ogv"};

        for (String f : files) {


            try {

                conn.setAutoCommit(false);

                File file = new File(getClass().getClassLoader()
                        .getResource(f).getFile());

                FileInputStream fis = new FileInputStream(file);


                PreparedStatement ps = conn.prepareStatement("INSERT INTO image (name, data, day) VALUES (?, ?, ?)");

                ps.setString(1, file.getName());
                ps.setBinaryStream(2, fis, (int) file.length());
                ps.setDate(3, new Date(file.lastModified()));
                ps.executeUpdate();
                ps.close();
                fis.close();

                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //@Test
    public void getImage() {

        try {

            PreparedStatement ps = conn
                    .prepareStatement("SELECT data FROM image WHERE name = ?");
            ps.setString(1, FILE_NAME);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                InputStream is = rs.getBinaryStream(1);

                try {
                    FileOutputStream file = new FileOutputStream("new_"
                            + FILE_NAME);
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = is.read(bytes)) != -1) {
                        file.write(bytes, 0, read);
                    }

                    try {
                        file.flush();
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Assert.fail();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Assert.fail("Problem with create file: " + e);
                }

                is.close();
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //@After
    public void remove() {

        /*try {
              conn.setAutoCommit(false);
              PreparedStatement ps = conn
                      .prepareStatement("DELETE FROM image where name = ?");
              ps.setString(1, FILE_NAME);
              ps.execute();
              conn.commit();
          } catch (SQLException e) {
              e.printStackTrace();
              Assert.fail();
          }*/

        File file = new File("new_" + FILE_NAME);
        if (file.exists()) {
            Assert.assertEquals(true, file.delete());
        }
    }

}

