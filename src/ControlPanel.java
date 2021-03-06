import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ControlPanel extends JPanel {

	ControlPanel(MyPanel panel, MusicSheetPanel mp, MyScanner sc) {

		JButton rescan = new JButton("Rescan");
		rescan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mp.getImage() != null) {
					BufferedImage newPicture = mp.getImage();
//				
					for (int x = 0; x < newPicture.getWidth(); x++) {
						for (int y = 0; y < newPicture.getHeight(); y++) {
							if (newPicture.getRGB(x, y) > -20000000 && newPicture.getRGB(x, y) < -15000000) {

								newPicture.setRGB(x, y, 0xFF000000);
							}
						}
					}

					// sc.setImage(newPicture);

					// BufferedImage newImage = sc.getImage();

					MyScanner newScanner = new MyScanner(newPicture, newPicture.getWidth(), newPicture.getHeight());
					mp.setScanner(newScanner);
					BufferedImage newImage = newScanner.getImage();

					mp.getMouseLocation().setImage(newImage);
					panel.refreshLyrics(newScanner.getConverter().getLyrics());

				}
			}
		});

		JButton ibr = new JButton("Increase BlackRecognizer");
		ibr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setBlackRecognizerHelper(SizeManager.getBlackRecognizerHelper() + 1000000);
			}

		});
		JButton dbr = new JButton("Decrease BlackRecognizer");
		dbr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setBlackRecognizerHelper(SizeManager.getBlackRecognizerHelper() - 1000000);
			}

		});
		JButton ios = new JButton("Increase OvalSize");
		ios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setOvalSize(SizeManager.getOvalSize() + 1);
			}

		});
		JButton dos = new JButton("Decrease OvalSize");
		dos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setOvalSize(SizeManager.getOvalSize() - 1);
			}

		});

		JButton inr = new JButton("Increase NoteRecognizer");
		JButton dnr = new JButton("Decrease NoteRecognizer");
		inr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setFullNoteRecognizer(SizeManager.getFullNoteRecognizer() + 1);
			}

		});
		dnr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setFullNoteRecognizer(SizeManager.getFullNoteRecognizer() - 1);
			}

		});

		JButton prev = new JButton("<<");
		JButton next = new JButton(">>");
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.getPh().prev();
			}

		});

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.getPh().next();
			}

		});

		JButton showred = new JButton("Show RedLine");
		showred.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.setShowRedLine(!mp.getShowRedLine());
			}

		});

		JButton help = new JButton("?");

		String howtouse = "\r\n" + "??? ??????????????? ???????????? ????????? ?????? ??????????????????.\n" + "1. Upload????????? ?????? ????????? ????????? ????????????. ????????? 1???????????? ??????????????? ????????? ???????????????.\r\n"
				+ "2. ????????? ???????????? ?????? 4????????? ?????? 8??????????????? ????????????? ????????? 3?????????.. ????????????..\r\n"
				+ "2-1. BlackRecognizer??? ????????? ???????????? rescan??? ???????????????. ??? ?????? ???????????? ????????????????????? ??? ???????????? ??? ??? ???????????????. ????????? ????????? ??????????????? ??????????????? ??? ????????????. \r\n"
				+ "2-2. NoteRecognizer??? ????????? ???????????? rescan??? ???????????????. ??? ?????? ???????????? ??? ?????? ????????? ?????????????????? ???????????? ???????????????. [??????????????? ??? ??????????????? ??????????????? ?????????????????? ????????? ???????????? ????????? ????????? ????????????.] \r\n"
				+ "3. 2???????????? 4???????????? ???????????? ????????? ???????????? (?????????????????? ???????????????!!) ????????????????????????. \r\n"
				+ "???????????? ???????????? ????????? ???????????? ????????? ??????????????? ???????????????. ?????? 2?????????/4????????? ?????? ????????? ?????? ????????? ??? rescan??? ???????????????.\r\n"
				+ "4. ????????? ????????? ?????? ????????? ???????????? ???????????????? ???????????? 7?????????...?????????...\n?????????????????? ????????? ???????????? ????????? ???????????? ?????? ??? ????????????. ????????? ????????? ?????? ????????? ??? rescan??? ???????????????.\r\n"
				+ "Show Red Line??? ?????? ??????????????? ????????? ????????? ????????? 5????????? ??????????????? ??????????????????.\r\n"
				+ "4-1. ovalSize??? ???????????? ???????????? ???????????? ?????? ??? ????????????. ?????? ????????? ????????? ???????????? ???????????? ??????????????? ?????? ????????? ???????????? ????????? ?????? ???????????? ??????????????????.\r\n"
				+ "5.??????????????? ????????? ????????? ????????????. ????????? ????????? ????????? ???????????? ????????????????????? ???????????? ????????? ???????????? ????????? ???????????????.\r\n"
				+ "6. ????????? ?????? ????????? << ??? >>???????????? ??????????????? ?????? ??? ??? ????????????. \r\n"
				+ "7. ????????? ????????? ?????? ????????? ??????????????? ????????????. ????????? ??????????????? ??????????????????????????? ????????????????????? ??????????????????????????? ???????????? ??? ????????????. \r\n" 
				
				+ "\r\n" + "????????? ????????? https://github.com/msunglim/KalimbaMusicSheet\r\n??? ????????? ????????? https://blog.naver.com/gtmim30/222595698411\r\n" + "Progress and Service 	  \r\n";
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame f = new JFrame();
				f.setTitle("How to Use");
				JTextArea ta = new JTextArea(howtouse);
				ta.setLineWrap(true);
				f.add(ta);
				f.setSize(1000, 400);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}

		});

		JButton upload = new JButton("Upload");
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();

				int choice = chooser.showOpenDialog(chooser);

				if (choice != JFileChooser.APPROVE_OPTION)
					return;
				File chosenFile = chooser.getSelectedFile();

				try {
//					File chosenFile1 = new File("musicSheet1.jpg");
//					BufferedImage picture = ImageIO.read(chosenFile1);
					BufferedImage picture = ImageIO.read(chosenFile);
					for (int x = 0; x < picture.getWidth(); x++) {
						for (int y = 0; y < picture.getHeight(); y++) {
							if (picture.getRGB(x, y) > -20000000 && picture.getRGB(x, y) < -15000000) {

								picture.setRGB(x, y, 0xFF000000);
							}
						}
					}

					mp.start(picture);
					panel.refreshLyrics(mp.getScanner().getConverter().getLyrics());
					panel.refreshMagnifying(mp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		add(prev);
		add(next);
		add(rescan);
		add(upload);
		add(ibr);
		add(dbr);
		add(ios);
		add(dos);
		add(inr);
		add(dnr);
		add(showred);
		add(help);
	}
}
