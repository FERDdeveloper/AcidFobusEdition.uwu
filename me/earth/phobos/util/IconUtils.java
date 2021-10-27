/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.util.Arrays;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class IconUtils
/*    */ {
/* 12 */   public static final IconUtils INSTANCE = new IconUtils();
/*    */ 
/*    */   
/*    */   public ByteBuffer readImageToBuffer(InputStream inputStream) throws IOException {
/* 16 */     BufferedImage bufferedimage = ImageIO.read(inputStream);
/* 17 */     int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
/* 18 */     ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
/* 19 */     Arrays.stream(aint).map(i -> i << 8 | i >> 24 & 0xFF).forEach(bytebuffer::putInt);
/* 20 */     bytebuffer.flip();
/* 21 */     return bytebuffer;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\IconUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */