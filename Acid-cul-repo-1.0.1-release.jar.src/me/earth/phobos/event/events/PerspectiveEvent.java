/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ 
/*    */ 
/*    */ public class PerspectiveEvent
/*    */   extends EventStage
/*    */ {
/*    */   private float aspect;
/*    */   
/*    */   public PerspectiveEvent(float aspect) {
/* 12 */     this.aspect = aspect;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAspect() {
/* 17 */     return this.aspect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAspect(float aspect) {
/* 22 */     this.aspect = aspect;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\PerspectiveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */