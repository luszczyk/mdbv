package net.luszczyk.mdbv.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public enum AvailableDataBase {
   MYSQL(3306, "MySQL", "mysql"), POSTGRES(5432, "PostgreSQL", "postgres");

    private int port;
    private String name;
    private String defaultDb;

    private AvailableDataBase(int port, String name, String defaultDb) {
        this.port = port;
        this.name = name;
        this.defaultDb = defaultDb;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getDefaultDb() {
        return defaultDb;
    }
}
