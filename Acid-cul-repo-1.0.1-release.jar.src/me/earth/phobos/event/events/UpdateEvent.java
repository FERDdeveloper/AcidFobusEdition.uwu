/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ 
/*    */ public class UpdateEvent
/*    */   extends EventStage {
/*    */   private final int stage;
/*    */   
/*    */   public UpdateEvent(int stage) {
/* 10 */     this.stage = stage;
/*    */   }
/*    */   
/*    */   public final int getStage() {
/* 14 */     return this.stage;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\UpdateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */