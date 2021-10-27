/*    */ package me.earth.phobos.util.hwid;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public class DisplayUtil
/*    */ {
/*    */   public static void Display() {
/* 13 */     Frame frame = new Frame();
/* 14 */     frame.setVisible(false);
/* 15 */     throw new NoStackTraceThrowable("Verification was unsuccessful!");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Frame
/*    */     extends JFrame
/*    */   {
/*    */     public Frame() {
/* 26 */       setTitle("Verification failed.");
/* 27 */       setDefaultCloseOperation(2);
/* 28 */       setLocationRelativeTo(null);
/* 29 */       copyToClipboard();
/* 30 */       String message = "haha nigga, you are not on the HWID list.\nHWID: " + SystemUtil.getSystemInfo() + "\n(Copied to clipboard.)";
/* 31 */       JOptionPane.showMessageDialog(this, message, "Could not verify your HWID successfully.", -1, UIManager.getIcon("OptionPane.errorIcon"));
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static void copyToClipboard() {
/* 40 */       StringSelection selection = new StringSelection(SystemUtil.getSystemInfo());
/* 41 */       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 42 */       clipboard.setContents(selection, selection);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\hwid\DisplayUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */