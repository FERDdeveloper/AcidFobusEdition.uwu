/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.client.GuiIngameForge;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class EntityHunger
/*    */   extends Module {
/*    */   public EntityHunger() {
/* 11 */     super("EntityHunger", "Renders hunger while riding entities", Module.Category.RENDER, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderGameOverlay(RenderGameOverlayEvent event) {
/* 16 */     GuiIngameForge.renderFood = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\EntityHunger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */