/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Utils
/*    */ {
/*    */   public static void drawBorderedCircle(int x, int y, float radius, int outsideC, int insideC) {
/* 10 */     GL11.glEnable(3042);
/* 11 */     GL11.glDisable(3553);
/* 12 */     GL11.glBlendFunc(770, 771);
/* 13 */     GL11.glEnable(2848);
/* 14 */     GL11.glPushMatrix();
/* 15 */     float scale = 0.1F;
/* 16 */     GL11.glScalef(scale, scale, scale);
/* 17 */     x = (int)(x * 1.0F / scale);
/* 18 */     y = (int)(y * 1.0F / scale);
/* 19 */     radius *= 1.0F / scale;
/* 20 */     drawCircle(x, y, radius, insideC);
/* 21 */     drawUnfilledCircle(x, y, radius, 1.0F, outsideC);
/* 22 */     GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
/* 23 */     GL11.glPopMatrix();
/* 24 */     GL11.glEnable(3553);
/* 25 */     GL11.glDisable(3042);
/* 26 */     GL11.glDisable(2848);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void drawUnfilledCircle(int x, int y, float radius, float lineWidth, int color) {
/* 31 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 32 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 33 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 34 */     float blue = (color & 0xFF) / 255.0F;
/* 35 */     GL11.glColor4f(red, green, blue, alpha);
/* 36 */     GL11.glLineWidth(lineWidth);
/* 37 */     GL11.glEnable(2848);
/* 38 */     GL11.glBegin(2);
/* 39 */     for (int i = 0; i <= 360; i++) {
/* 40 */       GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius);
/*    */     }
/* 42 */     GL11.glEnd();
/* 43 */     GL11.glDisable(2848);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void drawCircle(int x, int y, float radius, int color) {
/* 48 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 49 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 50 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 51 */     float blue = (color & 0xFF) / 255.0F;
/* 52 */     GL11.glColor4f(red, green, blue, alpha);
/* 53 */     GL11.glBegin(9);
/* 54 */     for (int i = 0; i <= 360; i++) {
/* 55 */       GL11.glVertex2d(x + Math.sin(i * 3.141526D / 180.0D) * radius, y + Math.cos(i * 3.141526D / 180.0D) * radius);
/*    */     }
/* 57 */     GL11.glEnd();
/*    */   }
/*    */ 
/*    */   
/*    */   public static double distance(float x, float y, float x1, float y1) {
/* 62 */     return Math.sqrt(((x - x1) * (x - x1) + (y - y1) * (y - y1)));
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */