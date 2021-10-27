/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class SkyColor
/*    */   extends Module
/*    */ {
/* 12 */   private final Setting<Integer> red = register(new Setting("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 13 */   private final Setting<Integer> green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 14 */   private final Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/*    */ 
/*    */   
/*    */   public SkyColor() {
/* 18 */     super("SkyColor", "Change the sky color.", Module.Category.RENDER, false, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdate(EntityViewRenderEvent.FogColors event) {
/* 24 */     event.setRed(((Integer)this.red.getValue()).intValue() / 255.0F);
/* 25 */     event.setGreen(((Integer)this.green.getValue()).intValue() / 255.0F);
/* 26 */     event.setBlue(((Integer)this.blue.getValue()).intValue() / 255.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 31 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 36 */     MinecraftForge.EVENT_BUS.unregister(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\SkyColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */