package net.luszczyk.mdbv.common.model.impl;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.model.AbstractDataBase;
import net.luszczyk.mdbv.common.model.DataBase;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class DataBaseMysql extends AbstractDataBase implements DataBase {

    public DataBaseMysql(DataBaseDTO dataBaseDTO) {
        super(dataBaseDTO);
        this.dataBaseQueryExecutor = new DataBaseMysqlQueryExecutor();
    }

    @Override
    public String getDriverName() {
        return "mysql";
    }

    @Override
    public String getDriverPackage() {
        return "com.mysql.jdbc.Driver";
    }
}
