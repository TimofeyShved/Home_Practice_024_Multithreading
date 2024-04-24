# Многопоточность

8.0. Состояния

___

Задача
--------

>Выведите состояние потока перед его запуском, после запуска и во время выполнения.

___
Решение:
--------

~~~Java
    Thread thread = new Thread() {
        @Override
        public void run() {
            System.out.println(getState());
        }
    };

    System.out.println(thread.getState());
    thread.start();
    try {
        // Тут маленькая сложность есть только для вывода состояния TERMINATED:
        thread.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println(thread.getState());
~~~

Добавим WAITING и BLOCKED:

~~~Java
    /**
     * Вывод состояния WAITING
     * 
     * @param strings
     * @throws InterruptedException
     */
    public static void main(String[] strings) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.notifyAll();
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        synchronized (lock){
            thread.start(); // Запустим поток
            lock.wait(); // Будем ждать, пока поток не запустится
            System.out.println(thread.getState()); // WAITING
            lock.notifyAll();
            System.out.println(thread.getState()); // BLOCKED
        }
    }
~~~

Для TIMED_WAITING немного изменим тот же код:

~~~Java
    /**
     * Вывод состояния WAITING
     *
     * @param strings
     * @throws InterruptedException
     */
    public static void main(String[] strings) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        lock.notifyAll();
                        lock.wait(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        synchronized (lock) {
            thread.start(); // Запустим поток
            lock.wait(); // Будем ждать, пока поток не запустится
            System.out.println(thread.getState()); // WAITING
        }
    }
~~~
Итог: 
--------

>- [X] Есть готовое решение 
>- [ ] Свой код написан 
