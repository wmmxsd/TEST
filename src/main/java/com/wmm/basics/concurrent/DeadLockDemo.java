package com.wmm.basics.concurrent;

/**
 * 死锁
 *
 * 产生死锁必须具备以下四个条件：
 *
 * 1.互斥条件：该资源任意一个时刻只由一个线程占用。
 * 2.请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * 3.不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 * 4.循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 */
public class DeadLockDemo {
    //资源 1
    private static Object resource1 = new Object();
    //资源 2
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程1启动");
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                    System.out.println("线程1 TIME_WAITTING OVER");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            System.out.println("线程2启动");
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                    System.out.println("线程2 TIME_WAITTING OVER");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }
}
