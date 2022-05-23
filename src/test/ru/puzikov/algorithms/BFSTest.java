package ru.puzikov.algorithms;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {
    private BFS bfs;
    private int size;
    @BeforeEach
    void init(){
        size = 10;
        bfs =new BFS(size);
    }

    @Test
    void search() {

        assertEquals(bfs.search(new Point(0,0)), new Point(size-1,size-1));
    }
    @Test
    void searchAndPrint(){
        assertEquals(bfs.search(new Point(0,0), System.out::println), new Point(size-1,size-1));

    }

    @Test
    void searchStep() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method init = BFS.class.getDeclaredMethod("init", Point.class);
        init.setAccessible(true);
        init.invoke(bfs,new Point(0,0));
        while (!bfs.step())
            System.out.println(Arrays.deepToString(bfs.getVisited()));
    }
}