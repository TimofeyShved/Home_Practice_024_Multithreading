package com.company;

public class Main {

    public static void main(String[] args) {
        MyThread thread_1 = new MyThread();
        MyThread thread_2 = new MyThread();
        MyThread thread_3 = new MyThread();
        System.out.println(thread_1.getName()+" "+thread_1.getState());
        System.out.println(thread_2.getName()+" "+thread_2.getState());
        System.out.println(thread_3.getName()+" "+thread_3.getState());
        thread_1.start();
        thread_2.start();
        thread_3.start();
        try {
            thread_1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread_1.getName()+" "+thread_1.getState());
        System.out.println(thread_2.getName()+" "+thread_2.getState());
        System.out.println(thread_3.getName()+" "+thread_3.getState());
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < (int)(Math.random()*100); i++) {
            try {
                sleep(1000); //Ожидание в течении 1 сек.
                this.yield(); //Передать управление другим потокам
            } catch (Exception e) {}
        }
        System.out.println(this.getName()+" "+this.getState());
    }

}
