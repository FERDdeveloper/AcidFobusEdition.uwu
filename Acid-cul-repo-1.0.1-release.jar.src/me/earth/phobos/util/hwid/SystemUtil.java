/*    */ package me.earth.phobos.util.hwid;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.apache.commons.codec.digest.DigestUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemUtil
/*    */ {
/*    */   public static String getSystemInfo() {
/* 23 */     return DigestUtils.sha256Hex(DigestUtils.sha256Hex(System.getenv("os") + 
/* 24 */           System.getProperty("os.name") + 
/* 25 */           System.getProperty("os.arch") + 
/* 26 */           System.getProperty("user.name") + 
/* 27 */           System.getenv("SystemRoot") + 
/* 28 */           System.getenv("HOMEDRIVE") + 
/* 29 */           System.getenv("PROCESSOR_LEVEL") + 
/* 30 */           System.getenv("PROCESSOR_REVISION") + 
/* 31 */           System.getenv("PROCESSOR_IDENTIFIER") + 
/* 32 */           System.getenv("PROCESSOR_ARCHITECTURE") + 
/* 33 */           System.getenv("PROCESSOR_ARCHITEW6432") + 
/* 34 */           System.getenv("NUMBER_OF_PROCESSORS"))) + 
/* 35 */       (Minecraft.func_71410_x()).field_71449_j.func_111285_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\hwid\SystemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */