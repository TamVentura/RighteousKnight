/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ThreadPool extends ThreadGroup {

    private static IDAssigner poolID = new IDAssigner(1);

    private boolean alive;
    private List<Runnable> taskQueue;
    private int id;

    public ThreadPool(int numThreads) {
        super("ThreadPool" + poolID.next());
        setDaemon(true);
        this.id = poolID.getCurrentID();
        taskQueue = new LinkedList<>();
        alive = true;
        for (int i = 0; i < numThreads; i++) {
            new PooledThread(this).start();
        }
    }

    public synchronized void runTask(Runnable task) {
        if (!alive) {
            throw new IllegalStateException("ThreadPool-" + id + " is dead");
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }
    }
    
    public synchronized void close(){
        if(!alive) return;
        alive = false;
        taskQueue.clear();
        interrupt();
    }
    
    public void join(){
        synchronized(this){
            alive=false;
            notifyAll();
        }
        
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
//                Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.size() == 0) {
            if (!alive) {
                return null;
            }
            wait();
        }
        return taskQueue.remove(0);
    }

}
