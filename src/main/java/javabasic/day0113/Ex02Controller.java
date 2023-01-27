package javabasic.day0113;

import javabasic.dbConn.ConnectionMaker;
import javabasic.dbConn.MySqlConnectionMaker;
import javabasic.dbConn.OracleConnectionMaker;

import java.sql.Connection;

public class Ex02Controller {
    public static void main(String[] args) {
        ConnectionMaker maker = new OracleConnectionMaker();
        Connection connection = maker.makeConnection();
    }
}
