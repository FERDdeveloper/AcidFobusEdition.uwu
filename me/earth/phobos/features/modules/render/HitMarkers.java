/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import me.earth.phobos.event.events.Render2DEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.RenderUtil;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public final class HitMarkers extends Module {
/*    */   public final ResourceLocation image;
/*    */   private int renderTicks;
/*    */   public Setting<Integer> red;
/*    */   public Setting<Integer> green;
/*    */   
/*    */   public HitMarkers() {
/* 22 */     super("HitMarkers", "hitmarker thingys", Module.Category.RENDER, false, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.red = register(new Setting("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 28 */     this.green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 29 */     this.blue = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 30 */     this.alpha = register(new Setting("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
/* 31 */     this.thickness = register(new Setting("Thickness", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(6)));
/* 32 */     this.time = register(new Setting("Time", Double.valueOf(20.0D), Double.valueOf(1.0D), Double.valueOf(50.0D)));
/*    */     this.image = new ResourceLocation("hitmarker.png");
/*    */     this.renderTicks = 100;
/*    */   } public Setting<Integer> blue; public Setting<Integer> alpha; public Setting<Integer> thickness; public Setting<Double> time; public void onRender2D(Render2DEvent event) {
/* 36 */     if (this.renderTicks < ((Double)this.time.getValue()).doubleValue()) {
/* 37 */       ScaledResolution resolution = new ScaledResolution(mc);
/* 38 */       drawHitMarkers();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 43 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 47 */     MinecraftForge.EVENT_BUS.unregister(this);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onAttackEntity(AttackEntityEvent event) {
/* 52 */     if (!event.getEntity().equals(mc.field_71439_g)) {
/*    */       return;
/*    */     }
/* 55 */     this.renderTicks = 0;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTickClientTick(TickEvent event) {
/* 60 */     this.renderTicks++;
/*    */   }
/*    */   
/*    */   public void drawHitMarkers() {
/* 64 */     ScaledResolution resolution = new ScaledResolution(mc);
/* 65 */     RenderUtil.drawLine(resolution.func_78326_a() / 2.0F - 4.0F, resolution.func_78328_b() / 2.0F - 4.0F, resolution.func_78326_a() / 2.0F - 8.0F, resolution.func_78328_b() / 2.0F - 8.0F, ((Integer)this.thickness.getValue()).intValue(), (new Color(((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue())).getRGB());
/* 66 */     RenderUtil.drawLine(resolution.func_78326_a() / 2.0F + 4.0F, resolution.func_78328_b() / 2.0F - 4.0F, resolution.func_78326_a() / 2.0F + 8.0F, resolution.func_78328_b() / 2.0F - 8.0F, ((Integer)this.thickness.getValue()).intValue(), (new Color(((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue())).getRGB());
/* 67 */     RenderUtil.drawLine(resolution.func_78326_a() / 2.0F - 4.0F, resolution.func_78328_b() / 2.0F + 4.0F, resolution.func_78326_a() / 2.0F - 8.0F, resolution.func_78328_b() / 2.0F + 8.0F, ((Integer)this.thickness.getValue()).intValue(), (new Color(((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue())).getRGB());
/* 68 */     RenderUtil.drawLine(resolution.func_78326_a() / 2.0F + 4.0F, resolution.func_78328_b() / 2.0F + 4.0F, resolution.func_78326_a() / 2.0F + 8.0F, resolution.func_78328_b() / 2.0F + 8.0F, ((Integer)this.thickness.getValue()).intValue(), (new Color(((Integer)this.red.getValue()).intValue(), ((Integer)this.green.getValue()).intValue(), ((Integer)this.blue.getValue()).intValue())).getRGB());
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\HitMarkers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */