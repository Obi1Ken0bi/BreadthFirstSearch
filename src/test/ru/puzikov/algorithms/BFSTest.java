package ru.puzikov.algorithms;

import org.junit.jupiter.api.BeforeEach;

class BFSTest {
    private BFS bfs;
    private int size;

    @BeforeEach
    void init() {
        size = 10;
        bfs = new BFS(size);
    }


}