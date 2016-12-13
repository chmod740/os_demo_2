import java.util.Random;

public class Process {

    private static CPUListener cpuListener;

    public static Integer S1, S2,s1,s2;

    public static String P1,P2,P3;

    static {
        S1 = 1;
        S2 = 2;
        s1 = 1;
        s2 = 1;

        P1 = "1";
        P2 = "2";
        P3 = "3";
    }
    private Process(){

    }

    public static void setListener(CPUListener cpuListener){
        Process.cpuListener = cpuListener;
    }


    public static void P1() {


        wait(P1,S2);
        wait(P1,S1);

        // 主要的业务逻辑代码


        cpuListener.service(P1,"正在执行逻辑");
        long beginTime = System.currentTimeMillis();
        Integer integer = new Random().nextInt(3000);
        try {
            Thread.sleep(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        cpuListener.service(P1,"逻辑执行完成");
        System.out.println("。。。进程P1执行结束,进程执行时间：" + (endTime - beginTime) +"ms");

        signal(P1,S2);
        signal(P1,S1);
    }

    public static void P2() {


        wait(P2,S2);
        wait(P2,S1);

        // 主要的业务逻辑代码

        cpuListener.service(P2,"正在执行逻辑");
        long beginTime = System.currentTimeMillis();
        Integer integer = new Random().nextInt(3000);
        try {
            Thread.sleep(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        cpuListener.service(P2,"逻辑执行完成");


        signal(P2,S2);
        signal(P2,S1);


    }

    public static void P3() {



        wait(P3,S2);

        // 主要的业务逻辑代码

        cpuListener.service(P3,"正在执行逻辑");
        long beginTime = System.currentTimeMillis();
        Integer integer = new Random().nextInt(3000);
        try {
            Thread.sleep(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        cpuListener.service(P3,"逻辑执行完成");
        signal(P3,S2);


    }

    /**
     * 原子操作，不可分割，使用synchronized保证
     * */
    public static void signal(String processId,Integer arg) {
        synchronized (new Object()) {


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuListener.service(processId,"正在Signal S" + arg);



            if (arg == 1) {
                s1 ++;
            }else {
                if (arg == 2) {
                    s2 ++;
                }
            }


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuListener.service(processId,"Signal S" + arg + "完成");

        }


    }

    /**
     * 原子操作，不可分割
     * */
    public static void wait(String processId,Integer arg) {
        synchronized (new Object()) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuListener.service(processId,"正在wait S" + arg);



            while ((arg==1?s1:s2 ) == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (arg == 1) {
                s1 --;
            }else {
                if (arg == 2) {
                    s2 --;
                }
            }



            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuListener.service(processId,"wait S" + arg + "完成");

        }

    }

}