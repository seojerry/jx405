package javabasic.videomanager;

import javabasic.dbConn.ConnectionMaker;
import javabasic.dbConn.MySqlConnectionMaker;
import javabasic.videomanager.viewer.StaffViewer;

public class ManagerMVC {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        StaffViewer staffViewer = new StaffViewer(connectionMaker);
        staffViewer.showIndex();
    }
}
