package com.wyf.base.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Random generator = new Random();
	
	//瀛椾綋楂樺害
	private int x=0;
	//瀛椾綋楂樺害
	private int fontHeight;
	private int codeY;
	
	//楠岃瘉鐮佸浘鐗囩殑瀹藉害銆�
	private int width=120;
	//楠岃瘉鐮佸浘鐗囩殑楂樺害銆�
	private int height=40;
	//楠岃瘉鐮佸瓧绗︿釜鏁�
	private int codeCount=4;	

	private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e', '2', '3', '4', '5', '6', '7', '8', 'g', 'f', 'y', 'n', 'm', 'n', 'p', 'w', 'x' };

	public AuthCodeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 瀹氫箟鍥惧儚buffer
				BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = buffImg.createGraphics();

				// 鍒涘缓涓�涓殢鏈烘暟鐢熸垚鍣ㄧ被
				Random random = new Random();

				// 灏嗗浘鍍忓～鍏呬负鐧借壊
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, width, height);

				// 鍒涘缓瀛椾綋锛屽瓧浣撶殑澶у皬搴旇鏍规嵁鍥剧墖鐨勯珮搴︽潵瀹氥��
				Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
				// 璁剧疆瀛椾綋銆�
				g.setFont(font);

				// 闅忔満浜х敓160鏉″共鎵扮嚎锛屼娇鍥捐薄涓殑璁よ瘉鐮佷笉鏄撹鍏跺畠绋嬪簭鎺㈡祴鍒般��
				//g.setColor(Color.BLACK);
				for (int i = 0; i < 10; i++) {
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
				}

				// randomCode鐢ㄤ簬淇濆瓨闅忔満浜х敓鐨勯獙璇佺爜锛屼互渚跨敤鎴风櫥褰曞悗杩涜楠岃瘉銆�
				StringBuffer randomCode = new StringBuffer();
				@SuppressWarnings("unused")
				int red = 0, car = captchars.length - 1;

				// 闅忔満浜х敓codeCount鏁板瓧鐨勯獙璇佺爜銆�
				for (int i = 0; i < codeCount; i++) {
					// 寰楀埌闅忔満浜х敓鐨勯獙璇佺爜鏁板瓧銆�
					String strRand = String.valueOf(captchars[generator.nextInt(car) + 1]);
					// 浜х敓闅忔満鐨勯鑹插垎閲忔潵鏋勯�犻鑹插�硷紝杩欐牱杈撳嚭鐨勬瘡浣嶆暟瀛楃殑棰滆壊鍊奸兘灏嗕笉鍚屻��
					red = random.nextInt(255);
					// 鐢ㄩ殢鏈轰骇鐢熺殑棰滆壊灏嗛獙璇佺爜缁樺埗鍒板浘鍍忎腑銆�
					//g.setColor(new Color(red,(20+red)%255, (20+red)%255));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.drawString(strRand, x/2 + i*x, codeY);

					// 灏嗕骇鐢熺殑鍥涗釜闅忔満鏁扮粍鍚堝湪涓�璧枫��
					randomCode.append(strRand);
				}
				
				// 鍥惧舰鎵洸
				shear(g, buffImg.getWidth(), buffImg.getHeight(), Color.white);	
				
				// 鐢昏竟妗嗐��
				//g.setColor(Color.BLACK);
				//g.drawRect(0, 0, width - 1, height - 1);		
				
				// 灏嗗洓浣嶆暟瀛楃殑楠岃瘉鐮佷繚瀛樺埌Session涓��
				String sessionKey = getSessionKey(request.getRequestURI());
				request.getSession().setAttribute(sessionKey, randomCode.toString());

				// 绂佹鍥惧儚缂撳瓨銆�
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setContentType("image/jpeg");

				// 灏嗗浘鍍忚緭鍑哄埌Servlet杈撳嚭娴佷腑銆�
				ServletOutputStream sos = response.getOutputStream();
				ImageIO.write(buffImg, "jpeg", sos);
				sos.close();
	}

	public void init() throws ServletException {
		x=width/(codeCount+1);
		fontHeight=height-2;
		codeY=height-4;
	}
	
	/**
	 * 浜х敓闅忔満瀛椾綋
	 * 
	 * @return
	 */
	public Font getFont() {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, 45);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, 45);
		font[2] = new Font("Forte", Font.PLAIN, 45);
		font[3] = new Font("Wide Latin", Font.PLAIN, 40);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 45);
		return font[random.nextInt(5)];
	}

	/**
	 * 鍥惧舰鎵洸
	 * @param g 鍥惧舰瀵瑰儚
	 * @param w1 瀹�
	 * @param h1 楂�
	 * @param color 棰滆壊
	 */
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	/**
	 * 鍥惧舰姘村钩鎵洸
	 * @param g 鍥惧舰瀵瑰儚
	 * @param w1 瀹�
	 * @param h1 楂�
	 * @param color 棰滆壊
	 */
	public void shearX(Graphics g, int w1, int h1, Color color) {

		int period = generator.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = generator.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	/**
	 * 鍥惧舰鍨傜洿鎵洸
	 * @param g 鍥惧舰瀵瑰儚
	 * @param w1 瀹�
	 * @param h1 楂�
	 * @param color
	 */
	public void shearY(Graphics g, int w1, int h1, Color color) {

		int period = generator.nextInt(8) + 2; // 50;//generator.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}

	/**
	 * 鑾峰彇URL涓殑session key
	 * @author Mao 
	 * @date Aug 17, 2009
	 * @return 
	 */
	private String getSessionKey(String url){
		String key ="default";
		Pattern pattern = Pattern.compile("(?:.*)\\/([^\\/\\.]*)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			key = matcher.group(1);
		}
		return key;
	}

}
