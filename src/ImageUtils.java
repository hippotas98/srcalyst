import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class ImageUtils {
	static final int FONT_HEIGHT = 20;
	public static void createImagefromString(List<String> information, String name) throws IOException {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, FONT_HEIGHT);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = maxLength(information,fm);
        int height = 500;
        g2d.dispose();
        img = new BufferedImage(width+100, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        draw(g2d,information,fm.getAscent(),width);
        g2d.dispose();
        String path = "./infor/" + name + ".png";
        System.out.println(path);
        File file = new File(path);
        file.getParentFile().mkdirs(); 
        file.createNewFile();
        try {
            ImageIO.write(img, "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	static int maxLength(List<String> lsString, FontMetrics fm) {
		int max = fm.stringWidth(lsString.get(0));
		for(String string : lsString) {
			if(max < fm.stringWidth(string)) {
				max = fm.stringWidth(string);
			}
		}
		return max;
	}
	static void draw(Graphics2D g2d, List<String> lsString, int y,int width) {
		int start = y-FONT_HEIGHT;
		int end = y-FONT_HEIGHT;
		int c = 0;
		Color color;
		for(String i : lsString) {
			if(i.equals("\n") || lsString.indexOf(i)==lsString.size()-1) {
				//end+=FONT_HEIGHT;
				if(lsString.indexOf(i)==lsString.size()-1) end += 5 + FONT_HEIGHT;
				if(c%3 == 0) color = Color.CYAN;
				else if(c%3==1) color = Color.GREEN;
				else color = Color.YELLOW;
				c++;
				changeColor(g2d, color,0,start,width+10,end-start+15);
				start = end+15;
			}
			else end+=FONT_HEIGHT;
		}
		y-=5;
		for(String string : lsString) {
			if(!string.equals("\n")) {
				g2d.drawString(string, 5, y+10);
				y += FONT_HEIGHT;
			}
			if(lsString.indexOf(string)==0) y+=10;
		}
	}
	static void changeColor(Graphics2D g2d, Color c, int x, int y, int width, int height) {
		g2d.setColor(c);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(x, y, width, height);
	}
}
