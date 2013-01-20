package net.luszczyk.mdbv.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import java.io.*;
import java.sql.*;

public class PostgresSaveTest {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mdbvdb";
    private static final String DB_USER = "mdbv";
    private static final String DB_PASS = "mdbvdupa";
    private static final String FILE_NAME = "gizmo.ogv";

    private Connection conn;


    @Test
    public void testTrue() {

    }

    @Before
    public void connect() {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

    @Test
    public void saveImage() {


        String[] files = new String[]{"008.jpg", "Cobalt_Drafting.ogv", "demo.mp4", "gizmo.ogv",
                "Regular_Show_Trash_Boat.ogv", "SpaceX_Falcon_9.ogv"};

        for (String f : files) {


            try {

                conn.setAutoCommit(false);

                LargeObjectManager lobj = ((org.postgresql.PGConnection) conn)
                        .getLargeObjectAPI();

                long oid = lobj.createLO(LargeObjectManager.READ
                        | LargeObjectManager.WRITE);

                LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);

                File file = new File(getClass().getClassLoader()
                        .getResource(f).getFile());

                FileInputStream fis = new FileInputStream(file);

                OutputStream out = obj.getOutputStream();
                int c;

                while ((c = fis.read()) != -1) {
                    out.write(c);
                }

                obj.close();

                PreparedStatement ps = conn
                        .prepareStatement("INSERT INTO md (name,data,createdate,ext)VALUES (?, ?, ?, ?)");

                ps.setString(1, file.getName());
                ps.setLong(2, oid);
                ps.setDate(3, new Date(file.lastModified()));
                String[] ext = f.split("\\.");
                ps.setString(4, ext[ext.length-1]);
                ps.executeUpdate();
                ps.close();
                fis.close();

                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void getImage() {

        LargeObjectManager lobj = null;
        try {
            conn.setAutoCommit(false);
            lobj = ((org.postgresql.PGConnection) conn).getLargeObjectAPI();
            PreparedStatement ps = conn
                    .prepareStatement("SELECT data FROM md WHERE name = ?");
            ps.setString(1, FILE_NAME);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long oid = rs.getInt(1);
                LargeObject obj = lobj.open(oid, LargeObjectManager.READ);

                byte buf[] = new byte[obj.size()];
                obj.read(buf, 0, obj.size());

                try {
                    FileOutputStream file = new FileOutputStream("new_"
                            + FILE_NAME);
                    try {
                        file.write(buf);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Assert.fail();
                    }
                    try {
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Assert.fail();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Assert.fail("Problem with create file: " + e);
                }

                obj.close();
            }
            rs.close();
            ps.close();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
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

