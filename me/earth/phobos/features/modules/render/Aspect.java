/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.event.events.PerspectiveEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class Aspect
/*    */   extends Module
/*    */ {
/* 11 */   public Setting<Float> aspect = register(new Setting("Alpha", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(5.0F)));
/*    */ 
/*    */   
/*    */   public Aspect() {
/* 15 */     super("Aspect", "Cool.", Module.Category.RENDER, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPerspectiveEvent(PerspectiveEvent perspectiveEvent) {
/* 21 */     perspectiveEvent.setAspect(((Float)this.aspect.getValue()).floatValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\Aspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */