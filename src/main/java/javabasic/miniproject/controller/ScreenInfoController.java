package javabasic.miniproject.controller;

import javabasic.miniproject.model.ScreenInfo;
import javabasic.miniproject.model.Theater;
import javabasic.model.BoardDTO;

import java.util.ArrayList;
import java.util.Date;

public class ScreenInfoController {
    private ArrayList<ScreenInfo> list;
    private int nextId;

    public ScreenInfoController() {
        list = new ArrayList<>();
        nextId = 1;

        ScreenInfo s = new ScreenInfo();
        s.setTheaterId(1);
        s.setMovieId(1);
        s.setRunningTime("13:45 ~ 15:35");
        add(s);
    }

    public void add(ScreenInfo screenInfo) {
        screenInfo.setScreenInpoId(nextId++);
        list.add(screenInfo);
    }

    public ScreenInfo selectOne(int id) {
        ScreenInfo temp = new ScreenInfo(id);
        if (list.contains(temp)) {
            return new ScreenInfo(list.get(list.indexOf(temp)));
        }
        return null;
    }

    public ArrayList<ScreenInfo> selectAll() {
        ArrayList<ScreenInfo> temp = new ArrayList<>();
        for (ScreenInfo b : list) {
            temp.add(new ScreenInfo(b));
        }
        return temp;
    }

    public void update(ScreenInfo screenInfo) {
        list.set(list.indexOf(screenInfo), screenInfo);
    }

    public void delete(int id) {
        list.remove(new ScreenInfo(id));
    }
}
