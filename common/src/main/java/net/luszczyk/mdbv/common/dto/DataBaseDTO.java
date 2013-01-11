package net.luszczyk.mdbv.common.dto;

import net.luszczyk.mdbv.common.model.AvailableDataBase;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseDTO {

    private String host;
    private String user;
    private String pass;
    private Integer port;
    private String name;
    private AvailableDataBase type;

    public AvailableDataBase getType() {
        return type;
    }

    public void setType(AvailableDataBase type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
