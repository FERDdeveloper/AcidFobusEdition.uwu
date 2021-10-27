/*    */ package me.earth.phobos.event.events;
/*    */ 
/*    */ import me.earth.phobos.event.EventStage;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class ChatEvent
/*    */   extends EventStage {
/*    */   private final String msg;
/*    */   
/*    */   public ChatEvent(String msg) {
/* 12 */     this.msg = msg;
/*    */   }
/*    */   
/*    */   public String getMsg() {
/* 16 */     return this.msg;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\ChatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */