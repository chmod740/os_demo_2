import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by HUPENG on 2016/12/13.
 */
public class Board {
    private int p1;
    private int p2;
    private int p3;

    private BufferedImage bufferedImage;
    private ImageIcon icon;
    /**
     * 监听器
     * */
    private SimpleListener simpleListener;

    public Board(SimpleListener simpleListener){
        p1 = 0;
        p2 = 0;
        p3 = 0;
        bufferedImage = ImageUtil.scale("back.png",850,600);
        icon = new ImageIcon(bufferedImage);
        this.simpleListener = simpleListener;
    }

    public void add(int processId,String msg){
        Graphics g = bufferedImage.getGraphics();
        int x = 0;
        int y = 0;
        x = 240*(processId-1) + 130;
        if (processId == 1){
            y = 285 + 30 * p1;
            p1 ++;
            msg = p1 + ":" + msg;
        }
        if (processId == 2){
            y = 285 + 30 * p2;
            p2 ++;
            msg = p2 + ":" + msg;
        }
        if (processId == 3){
            y = 285 + 30 * p3;
            p3 ++;
            msg = p3 + ":" + msg;
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("黑体", Font.BOLD, 20));
        g.drawString(msg,x,y);

        BufferedImage tmpBufferedImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),bufferedImage.getType());
        tmpBufferedImage.setData(bufferedImage.getData());
        x = 245*(processId-1) + 50;
        y = 0;
        BufferedImage cpuBufferedImage = ImageUtil.scale("cpu.png",250,200);
        tmpBufferedImage.getGraphics().drawImage(cpuBufferedImage,x,y,null);
        simpleListener.onDraw(new ImageIcon(tmpBufferedImage));

    }

}
