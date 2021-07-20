package sql.dao;

import sql.util.DatabaseUtil;
import java.sql.Connection;

public class Database {
    public Connection connection = new DatabaseUtil().getConnection();
}