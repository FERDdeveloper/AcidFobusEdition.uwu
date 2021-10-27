/*    */ package me.earth.phobos.util.hwid;
/*    */ 
/*    */ import me.earth.phobos.Phobos;
/*    */ 
/*    */ 
/*    */ public class NoStackTraceThrowable
/*    */   extends RuntimeException
/*    */ {
/*    */   public NoStackTraceThrowable(String msg) {
/* 10 */     super(msg);
/* 11 */     setStackTrace(new StackTraceElement[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "" + Phobos.getVersion();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 23 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\hwid\NoStackTraceThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */