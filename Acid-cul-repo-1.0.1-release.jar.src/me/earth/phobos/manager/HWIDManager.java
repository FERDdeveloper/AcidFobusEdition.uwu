/*    */ package me.earth.phobos.manager;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.earth.phobos.util.hwid.DisplayUtil;
/*    */ import me.earth.phobos.util.hwid.NoStackTraceThrowable;
/*    */ import me.earth.phobos.util.hwid.SystemUtil;
/*    */ import me.earth.phobos.util.hwid.URLReader;
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
/*    */ public class HWIDManager
/*    */ {
/*    */   public static final String pastebinURL = "https://gist.githubusercontent.com/kyv3-v2/550c6e1ad212f7aef2e81a3462645af8/raw/6d5add73cf551dfbe77dbaf14d651ff656dfffb9/gistfile1.txt";
/* 22 */   public static List<String> hwids = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public static void hwidCheck() {
/* 26 */     hwids = URLReader.readURL();
/* 27 */     boolean isHwidPresent = hwids.contains(SystemUtil.getSystemInfo());
/* 28 */     if (!isHwidPresent) {
/* 29 */       DisplayUtil.Display();
/* 30 */       throw new NoStackTraceThrowable("");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\manager\HWIDManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */