/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ 
/*    */ 
/*    */ public class ChorusEvent
/*    */   extends EventStage
/*    */ {
/*    */   private final double chorusX;
/*    */   private final double chorusY;
/*    */   private final double chorusZ;
/*    */   
/*    */   public ChorusEvent(double x, double y, double z) {
/* 14 */     this.chorusX = x;
/* 15 */     this.chorusY = y;
/* 16 */     this.chorusZ = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getChorusX() {
/* 21 */     return this.chorusX;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getChorusY() {
/* 26 */     return this.chorusY;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getChorusZ() {
/* 31 */     return this.chorusZ;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\ChorusEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */