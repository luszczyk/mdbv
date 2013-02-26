package net.luszczyk.mdbv.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresSaveTest {

    private static final String DB_URL = "jdbc:postgresql://kapitanlamp:5432/mdbvdb";
    private static final String DB_USER = "mdbv";
    private static final String DB_PASS = "mdbvhaslo";
    private static final String FILE_NAME = "nowak_movie.ogv";

    private Connection conn;


    @Test
    public void testTrue() {

    }

    //@Before
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


    private Long getOidForFile(Connection conn, String path) {

        Long oid = null;

        try {
            LargeObjectManager lobj = ((org.postgresql.PGConnection) conn)
                    .getLargeObjectAPI();

            oid = lobj.createLO(LargeObjectManager.READ
                    | LargeObjectManager.WRITE);

            LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);

            File file = new File(getClass().getClassLoader()
                    .getResource(path).getFile());

            FileInputStream fis = new FileInputStream(file);

            OutputStream out = obj.getOutputStream();
            int c;

            while ((c = fis.read()) != -1) {
                out.write(c);
            }

            obj.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return oid;
    }


    private List<Person> getTestPersons() {

        List<Person> result = new ArrayList<Person>(20);

        result.add(new Person("Jan", "Kowalski", "kowalski_avatar.jpeg", "kowalski_movie.ogv", "MazurekDabrowskiegoInstr.mp3", "POINT(19.920278 50.063729)"));
        result.add(new Person("John", "Doe", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(-73.968887 40.771009)"));
        result.add(new Person("Piotr", "Nowak", "nowak_avatar.jpeg", "nowak_movie.ogv", "MazurekDabrowskiegoInstr.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Grzegorz", "Nowak", "avatar.gif", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Piotr", "Kowalski", "avatar.png", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Arletta", "Szykuła", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Maria", "Kolasa", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Kazierz", "Wywrocki", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Ewelina", "Moskwa", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Marta", "Kolasa", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Henryk", "Czarny", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Robert", "Wronka", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Ryszard", "Latawiec", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Andrzej", "Czobot", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Roman", "Dacyna", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Anna", "Dziedzic", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Beata", "Nowak", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Roman", "Kaleta", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));
        result.add(new Person("Mateusz", "Zawadzki", "doe_avatar.jpeg", "doe_movie.ogv", "usa_anthem.mp3", "POINT(23.55474 50.26663)"));


        return result;
    }


    //@Test
    public void saveTestPersons() {


        for (Person p : getTestPersons()) {

            try {

                conn.setAutoCommit(false);

                long avatarOid = getOidForFile(conn, p.getAvatar());
                long bestMovieOid = getOidForFile(conn, p.getBest_movie());
                long anthemOid = getOidForFile(conn, p.getAnthem());
                String point = p.getPlace_birth();

                PreparedStatement ps = conn
                        .prepareStatement("INSERT INTO person (first_name, last_name, avatar, best_movie, anthem, place_birth) VALUES" +
                                " (?, ?, ?, ?, ?, ST_Transform(GeomFromText(?, 4326), 900913))");

                ps.setString(1, p.getFirstName());
                ps.setString(2, p.getLastName());
                ps.setLong(3, avatarOid);
                ps.setLong(4, bestMovieOid);
                ps.setLong(5, anthemOid);
                ps.setString(6, point);

                ps.executeUpdate();
                ps.close();

                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    //@Test
    public void saveImage() {


        String[] files = new String[]{"008.jpg", "kowalski_movie.ogv", "demo.mp4", "nowak_movie.ogv",
                "Regular_Show_Trash_Boat.ogv", "SpaceX_Falcon_9.ogv", "chorus.ogg", "MazurekDabrowskiegoInstr.mp3"};

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
                ps.setString(4, ext[ext.length - 1]);
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

