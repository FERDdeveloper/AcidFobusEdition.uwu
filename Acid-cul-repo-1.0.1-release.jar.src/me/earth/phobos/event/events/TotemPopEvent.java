/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class TotemPopEvent
/*    */   extends EventStage {
/*    */   private final EntityPlayer entity;
/*    */   
/*    */   public TotemPopEvent(EntityPlayer entity) {
/* 11 */     this.entity = entity;
/*    */   }
/*    */   
/*    */   public EntityPlayer getEntity() {
/* 15 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\TotemPopEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */