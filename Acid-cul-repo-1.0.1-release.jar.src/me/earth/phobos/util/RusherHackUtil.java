/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ public class RusherHackUtil
/*    */   implements Util
/*    */ {
/*    */   public static Field renderPosX;
/*    */   public static Field renderPosY;
/*    */   public static Field renderPosZ;
/*    */   
/*    */   public static double getRenderPosX() {
/*    */     try {
/* 15 */       return ((Double)renderPosX.get(Wrapper.mc.func_175598_ae())).doubleValue();
/*    */     }
/* 17 */     catch (Exception e) {
/* 18 */       e.printStackTrace();
/* 19 */       throw new IllegalStateException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static double getRenderPosY() {
/*    */     try {
/* 25 */       return ((Double)renderPosY.get(Wrapper.mc.func_175598_ae())).doubleValue();
/*    */     }
/* 27 */     catch (Exception e) {
/* 28 */       e.printStackTrace();
/* 29 */       throw new IllegalStateException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static double getRenderPosZ() {
/*    */     try {
/* 35 */       return ((Double)renderPosZ.get(Wrapper.mc.func_175598_ae())).doubleValue();
/*    */     }
/* 37 */     catch (Exception e) {
/* 38 */       e.printStackTrace();
/* 39 */       throw new IllegalStateException(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\RusherHackUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */