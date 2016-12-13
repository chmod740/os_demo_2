import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by HUPENG on 2016/12/13.
 */
public class Main extends JFrame{
    private JPanel jPanel = null;
    private JLabel jLabel = null;
    private Board board;


    public static void main(String[] args){
        new Main();
    }

    class MySimpleListener implements SimpleListener{

        @Override
        public void onDraw(ImageIcon icon) {
            jLabel.setIcon(icon);
        }
    }

    public Main(){
        //界面初始化
        this.setTitle("操作系统PV操作演示Demo");
        this.setSize(new Dimension(850, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        BufferedImage bufferedImage = ImageUtil.scale("back.png",850,600);
        ImageIcon icon = new ImageIcon(bufferedImage);

        jPanel = new JPanel();
        jLabel = new JLabel();
        jLabel.setIcon(icon);
        jLabel.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());
        jPanel.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());
        jPanel.add(jLabel);
        this.add(jPanel);
        this.setVisible(true);

        board = new Board(new MySimpleListener());

        Process.setListener(new CPUListener() {
            @Override
            public void service(String processId, String msg) {
                System.out.println(processId + ":" + msg);
                board.add(Integer.parseInt(processId),msg);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Process.P1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Process.P2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Process.P3();
            }
        }).start();



//        board.add(2,"等待S2");
//        board.add(3,"等待S3");
//        board.add(1,"等待S1");
//        board.add(2,"等待S2");
//        board.add(3,"等待S3");
//        board.add(1,"等待S1");
//        board.add(2,"等待S2");
//        board.add(3,"等待S3");
    }


}
