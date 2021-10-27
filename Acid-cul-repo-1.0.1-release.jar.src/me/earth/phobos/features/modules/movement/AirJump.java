/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.event.events.PacketEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AirJump
/*    */   extends Module {
/*    */   public AirJump() {
/* 10 */     super("AirJump", "AirJump", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacketSend(PacketEvent event) {
/* 15 */     if (mc.field_71439_g != null)
/* 16 */       mc.field_71439_g.field_70122_E = (mc.field_71439_g.field_70173_aa % 2 == 0); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\AirJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */