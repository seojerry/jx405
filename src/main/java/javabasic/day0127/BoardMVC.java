package javabasic.day0127;

import javabasic.dbConn.ConnectionMaker;
import javabasic.dbConn.MySqlConnectionMaker;
import javabasic.dbViewer.UserViewer;

public class BoardMVC {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        UserViewer userViewer = new UserViewer(connectionMaker);
        userViewer.showIndex();
    }
}
