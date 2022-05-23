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



}