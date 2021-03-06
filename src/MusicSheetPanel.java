
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MusicSheetPanel extends JPanel {

	private BufferedImage picture;

	private MouseLocation ml;

	private JPanel sample;

	private MyScanner ms;

	private BufferedImage imgg;

	private PictureHistory ph;

	private boolean showredline;

	MusicSheetPanel() throws IOException {
		setLayout(null);
	
//		File chosenFile1 = new File("musicSheet3.jpg");
//		BufferedImage picture = ImageIO.read(chosenFile1);
//		start(picture);
//		JFileChooser chooser = new JFileChooser();
//
//		int choice = chooser.showOpenDialog(chooser);
//
//		if (choice != JFileChooser.APPROVE_OPTION)
//			return;
//		File chosenFile = chooser.getSelectedFile();
//	
//		picture = ImageIO.read(chosenFile);
		
	}
	public void start(BufferedImage picture) {
		setImage(picture, 800, 1000);

		ms = new MyScanner(picture, picture.getWidth(), picture.getHeight());
	
		JPanel cp = ms.getConverter().getConvertPanel();
		cp.setBounds(0, 0, 800, 1000);
		add(cp);

		picture = ms.getImage();
		ml = new MouseLocation(this, picture);
		addMouseListener(ml);
		addMouseMotionListener(ml);

		ph = new PictureHistory(this, picture);

		// set vertical scroll bar length as much as music sheet's height
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(300, picture.getHeight()));

		// Sample color you point on
		sample = new JPanel();
		sample.setBackground(Color.GREEN);
		sample.setPreferredSize(new Dimension(25, 25));
		sample.setBorder(BorderFactory.createLineBorder(Color.black));
		add(sample);

		imgg = new BufferedImage(800, 1000, BufferedImage.TYPE_INT_RGB);
		showredline = false;
	}

	public PictureHistory getPh() {
		return ph;
	}

	public void setPicture(BufferedImage p) {
		picture = p;
	}

	public void showColor(int n) {
		int r = n & 0xFF0000, g = n & 0xFF00, b = n & 0xFF;

		sample.setBackground(new Color(n | g | b));

	}

	public void removeListener() {

		removeMouseListener(ml);
		removeMouseMotionListener(ml);

	}

	// set buffered Image and resize. also set JLabel img
	public void setImage(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		picture = dimg;

	}

	public BufferedImage getImage() {
		return picture;
	}

	public void draw(int px, int py, int color) {

		// draw the ellipse
		for (int i = 0; i <= 360; i++) {
			for (int j = 0; j < (SizeManager.getOvalSize() * 3) / 5; j++) {
				for (int k = 0; k < (SizeManager.getOvalSize()) / 2; k++) {

					double x, y;
					x = j * Math.sin(Math.toRadians(i));
					y = k * Math.cos(Math.toRadians(i));

					if (i != 0) {
						// draw a line joining previous and new point .
//                g.drawLine((int)px + cx, (int)py + cy,
//                                (int)x + cx, (int)y + cy);
						int cx = (int) x + px;
						int cy = (int) y + py;
						if (cx >= 0 && cy >= 0 && cx < picture.getWidth() && cy < picture.getHeight()) {
							picture.setRGB(cx, cy, color);
						}
					}

					// store the previous points
					// px = x;
					// py = y;

				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if(imgg!=null) {
		Graphics gCopy = imgg.getGraphics();

		drawOnGraphics(g);
		drawOnGraphics(gCopy);
		}
	}

	private void drawOnGraphics(Graphics g) {
		if (picture != null) {
			picture = ph.getCurr();
			g.drawImage(picture, 0, 0, this);
			g.setColor(Color.BLACK);
			g.drawLine(0, MouseLocation.getMouseR(), picture.getWidth(), MouseLocation.getMouseR());
			g.drawLine(MouseLocation.getMouseC(), 0, MouseLocation.getMouseC(), picture.getHeight());
			g.fillOval(MouseLocation.getMouseC() - (SizeManager.getOvalSize()) / 2,
					MouseLocation.getMouseR() - ((SizeManager.getOvalSize() * 4) / 5) / 2, SizeManager.getOvalSize(),
					(SizeManager.getOvalSize() * 4) / 5);

			if (showredline) {
				showRedLine(g);
			}
		}

		validate();
		repaint();
	}

	public boolean getShowRedLine() {
		return showredline;
	}

	public void setShowRedLine(boolean tf) {
		showredline = tf;
	}

	private void showRedLine(Graphics g) {
		for (Passage p : ms.getPassageList()) {
			for (int r : p.getRows()) {
				g.setColor(Color.RED);
				g.drawLine(0, r, picture.getWidth(), r);
			}
		}

	}

	public BufferedImage getG() {
		return imgg;

	}

	public void setScanner(MyScanner sc) {
		ms = sc;
	}

	public MyScanner getScanner() {
		return ms;
	}

	public MouseLocation getMouseLocation() {
		return ml;
	}

}