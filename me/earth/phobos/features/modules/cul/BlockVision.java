/*    */ package me.earth.phobos.features.modules.cul;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.client.event.RenderBlockOverlayEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class BlockVision
/*    */   extends Module {
/*    */   public BlockVision() {
/* 10 */     super("BlockVision", "Lets u see when inside blocks", Module.Category.CUL, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderBlockOverlayEvent(RenderBlockOverlayEvent event) {
/* 15 */     if (event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.BLOCK)
/* 16 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\BlockVision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */