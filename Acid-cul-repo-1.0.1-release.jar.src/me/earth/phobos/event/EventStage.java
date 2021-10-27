/*    */ package me.earth.phobos.event;
/*    */ 
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ public class EventStage
/*    */   extends Event
/*    */ {
/*    */   private int stage;
/*    */   
/*    */   public EventStage() {}
/*    */   
/*    */   public EventStage(int stage) {
/* 13 */     this.stage = stage;
/*    */   }
/*    */   
/*    */   public int getStage() {
/* 17 */     return this.stage;
/*    */   }
/*    */   
/*    */   public void setStage(int stage) {
/* 21 */     this.stage = stage;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\EventStage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */