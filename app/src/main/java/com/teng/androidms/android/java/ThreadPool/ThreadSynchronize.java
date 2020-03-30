package com.teng.androidms.android.java.ThreadPool;

import android.graphics.Canvas;
import android.support.annotation.WorkerThread;
import android.view.View;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSynchronize{

    /**
     * 计数器
     * 让当前的线程等待，其他的线程都结束后继续当前线程
     * 不可复用
     */
    private static CountDownLatch countDownLatch;
    /**
     * 回环栅栏
     * 所有的线程分别执行都达到一个状态后，然后一起开始后续各自的任务
     * 可复用
     */
    private static CyclicBarrier cyclicBarrier;
    /**
     * 信号量
     * 控制同时访问资源的个数，通过acquire()获取一个许可，如果没有就等待，而release()释放一个许可
     */
    private Semaphore semaphore;

    public static void main(String[] args) throws InterruptedException {

        ThreadSynchronize threadSynchronize = new ThreadSynchronize();
         threadSynchronize.countDownLatchTest();
         countDownLatch.await();
         System.out.println("worker结束， 继续执行主线程");

//        threadSynchronize.cyclicBarrierTest();


//        ReentrantLock

//        threadSynchronize.semaphoreTest();

    }

    /******************** Semaphore 测试 ********************/
    private void semaphoreTest() {
        int N = 8;
        semaphore = new Semaphore(1);
        for (int i = 0; i < N; i++) {
            new SemaphoreThread(i).start();
        }
    }

    class SemaphoreThread extends Thread {
        int index;

        SemaphoreThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            super.run();
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + index + " 开始");
            try {
                if (index == 0) {
                    sleep(100);
                } else {
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + index + " 结束");
            semaphore.release();
        }
    }


    /******************** CyclicBarrier 测试 ********************/

    private void cyclicBarrierTest() {

        cyclicBarrier = new CyclicBarrier(4);

        for (int i = 0; i < 4; i++) {
            new CyclicBarrierThread(i).start();
        }

    }

    class CyclicBarrierThread extends Thread {
        int index;

        CyclicBarrierThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("线程" + index + " 开始");
            try {
                sleep(1000);
                System.out.println("线程" + index + " 结束");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程都sleep(1000), 线程" + index + " 继续执行后续任务");
        }
    }


    /******************** CountDownLatch 测试 ********************/
    private void countDownLatchTest() {
        countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            new CountDownLatchThread(i).start();
        }

        countDownLatch.getCount();
    }

    class CountDownLatchThread extends Thread {
        int index;

        CountDownLatchThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("线程" + index + " 开始");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + index + " 结束");
            countDownLatch.countDown();
        }
    }

}
