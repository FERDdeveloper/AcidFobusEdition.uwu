/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ 
/*    */ public class Render3DEvent
/*    */   extends EventStage {
/*    */   private final float partialTicks;
/*    */   
/*    */   public Render3DEvent(float partialTicks) {
/* 10 */     this.partialTicks = partialTicks;
/*    */   }
/*    */   
/*    */   public float getPartialTicks() {
/* 14 */     return this.partialTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\Render3DEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */