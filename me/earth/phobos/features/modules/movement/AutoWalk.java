/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.client.event.InputUpdateEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoWalk
/*    */   extends Module {
/*    */   public AutoWalk() {
/* 10 */     super("AutoWalk", "Automatically walks in a straight line", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdateInput(InputUpdateEvent event) {
/* 15 */     (event.getMovementInput()).field_192832_b = 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\AutoWalk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */