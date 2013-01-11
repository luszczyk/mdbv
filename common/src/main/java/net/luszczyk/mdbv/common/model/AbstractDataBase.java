package net.luszczyk.mdbv.common.model;

import net.luszczyk.mdbv.common.dto.DataBaseDTO;
import net.luszczyk.mdbv.common.model.impl.DataBaseMysql;
import net.luszczyk.mdbv.common.model.impl.DataBasePostgres;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: luszczyk
 * Date: 11.01.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDataBase {

    private static final Logger logger = Logger
            .getLogger(AbstractDataBase.class);

    private DataBaseDTO dataBaseDTO;
    protected DataBaseQueryExecutor dataBaseQueryExecutor;

    private AbstractDataBase() {
    }

    public AbstractDataBase(DataBaseDTO dataBaseDTO) {
        super();
        this.dataBaseDTO = dataBaseDTO;
    }

    public static DataBase createDataBase(DataBaseDTO databaseDTO) {

        switch (databaseDTO.getType()) {
            case MYSQL:
                return new DataBaseMysql(databaseDTO);
            case POSTGRES:
                return new DataBasePostgres(databaseDTO);
            default:
                logger.error("This database type is not available " + databaseDTO);
                return null;
        }
    }

    public String getName() {
        return dataBaseDTO.getName();
    }

    public String getHost() {
        return dataBaseDTO.getHost();
    }

    public String getUser() {
        return dataBaseDTO.getUser();
    }

    public String getPass() {
        return dataBaseDTO.getPass();
    }

    public Integer getPort() {
        return dataBaseDTO.getPort();
    }

    public String getDbName() {
        return dataBaseDTO.getName();
    }

    public abstract String getDriverName();

    public abstract String getDriverPackage();


    public DataBaseQueryExecutor getDataBaseQueryExecutor() {
        return dataBaseQueryExecutor;
    }

    public DataBaseDTO getDataBaseDTO() {
        return dataBaseDTO;
    }
}
