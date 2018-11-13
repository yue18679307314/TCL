package com.kuyu.util;

import java.awt.AlphaComposite;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.RenderingHints;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.OutputStream;  
  
import javax.imageio.ImageIO;  
import javax.swing.ImageIcon;  
    
/**    
 * 图片水印    
 * @blog http://sjsky.iteye.com    
 * @author Michael    
 */    
public class ImageMarkLogoByIcon {     
    
    /**    
     * @param args    
     */    
    public static void main(String[] args) {     
        String srcImgPath = "e:/imagetest/1.jpg";     
        String iconPath = "e:/imagetest/2.png";     
        String targerPath = "e:/imagetest/tmp_mark_icon.jpg";     
        String targerPath2 = "e:/imagetest/tmp_mark_icon_rotate.jpg";     
//        // 给图片添加水印     
//        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath);     
        // 给图片添加水印,水印旋转-45     
        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2,45);     
    
    }     
    
    public static File markFileLogo(File file,String iconPath){
		String icon = iconPath + "white.png";
		String fName = file.getName();
		String absolutePath = file.getAbsolutePath();
		String fileEx = fName.substring(fName.lastIndexOf("."));
		String filename = absolutePath.substring(0,absolutePath.lastIndexOf("."));
		String targetPath =filename+"logo"+fileEx;
		File iconfile = new File(icon);
		if(iconfile.exists()){
			ImageMarkLogoByIcon.markImageByIcon(icon, absolutePath, targetPath,45);
			File re = new File(targetPath);
			if(re.exists()){
				file.delete();
				return re;
			}			
		}
		return file;
	}
    
    /**    
     * 给图片添加水印    
     * @param iconPath 水印图片路径    
     * @param srcImgPath 源图片路径    
     * @param targerPath 目标图片路径    
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath) {     
        markImageByIcon(iconPath, srcImgPath, targerPath, null);     
    }     
    
    /**    
     * 给图片添加水印、可设置水印图片旋转角度    
     * @param iconPath 水印图片路径    
     * @param srcImgPath 源图片路径    
     * @param targerPath 目标图片路径    
     * @param degree 水印图片旋转角度    
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath, Integer degree) {     
        OutputStream os = null;     
        try {     
            Image srcImg = ImageIO.read(new File(srcImgPath));     
    
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),     
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);     
    
            // 得到画笔对象     
            // Graphics g= buffImg.getGraphics();     
            Graphics2D g = buffImg.createGraphics();     
    
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg     
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);     
    
            if (null != degree) {     
                // 设置水印旋转     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg     
                                .getHeight() / 2);     
            }     
    
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度     
            ImageIcon imgIcon = new ImageIcon(iconPath);     
    
            // 得到Image对象。     
            Image img = imgIcon.getImage();     
    
            float alpha = 1f; // 透明度     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
                    alpha));     
            int width = srcImg.getWidth(null);
            int height = srcImg.getHeight(null);
            int width_times = width/450;
            int height_times = height/200;
            
            System.out.println("width="+width_times+";;height="+height_times );
            for(int i = 0;i<width_times;i++){
            	for(int j=0;j<height_times;j++){
            		// 表示水印图片的位置     
            		g.drawImage(img,i*570 , j*260, null);     
            	}
            }
    
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));     
    
            g.dispose();     
    
            os = new FileOutputStream(targerPath);     
    
            // 生成图片     
            ImageIO.write(buffImg, "JPG", os);     
    
            System.out.println("图片完成添加Icon印章。。。。。。");     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }     
}    