/*    */ package me.earth.phobos.util.hwid;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class URLReader
/*    */ {
/*    */   public static List<String> readURL() {
/* 20 */     List<String> s = new ArrayList<>();
/*    */     try {
/* 22 */       URL url = new URL("https://gist.githubusercontent.com/kyv3-v2/550c6e1ad212f7aef2e81a3462645af8/raw/6d5add73cf551dfbe77dbaf14d651ff656dfffb9/gistfile1.txt");
/* 23 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
/*    */       String hwid;
/* 25 */       while ((hwid = bufferedReader.readLine()) != null) {
/* 26 */         s.add(hwid);
/*    */       }
/* 28 */     } catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     return s;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\hwid\URLReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */