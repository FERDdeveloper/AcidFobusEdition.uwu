/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import me.earth.phobos.event.events.ChorusEvent;
/*    */ import me.earth.phobos.event.events.Render3DEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.ColorUtil;
/*    */ import me.earth.phobos.util.RenderUtil;
/*    */ import me.earth.phobos.util.Timer;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class ChorusESP
/*    */   extends Module
/*    */ {
/* 18 */   private final Setting<Integer> time = register(new Setting("Duration", Integer.valueOf(500), Integer.valueOf(50), Integer.valueOf(3000)));
/* 19 */   private final Setting<Boolean> box = register(new Setting("Box", Boolean.valueOf(true)));
/* 20 */   private final Setting<Boolean> outline = register(new Setting("Outline", Boolean.valueOf(true)));
/* 21 */   private final Setting<Integer> boxR = register(new Setting("BoxR", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.box.getValue()).booleanValue()));
/* 22 */   private final Setting<Integer> boxG = register(new Setting("BoxG", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.box.getValue()).booleanValue()));
/* 23 */   private final Setting<Integer> boxB = register(new Setting("BoxB", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.box.getValue()).booleanValue()));
/* 24 */   private final Setting<Integer> boxA = register(new Setting("BoxA", Integer.valueOf(180), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.box.getValue()).booleanValue()));
/* 25 */   private final Setting<Float> lineWidth = register(new Setting("LineWidth", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(5.0F), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/* 26 */   private final Setting<Integer> outlineR = register(new Setting("OutlineR", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/* 27 */   private final Setting<Integer> outlineG = register(new Setting("OutlineG", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/* 28 */   private final Setting<Integer> outlineB = register(new Setting("OutlineB", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/* 29 */   private final Setting<Integer> outlineA = register(new Setting("OutlineA", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/* 30 */   private final Timer timer = new Timer();
/*    */   
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   
/*    */   public ChorusESP() {
/* 37 */     super("ChorusESP", "Renders a chorus sound packet", Module.Category.RENDER, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onChorus(ChorusEvent event) {
/* 43 */     this.x = event.getChorusX();
/* 44 */     this.y = event.getChorusY();
/* 45 */     this.z = event.getChorusZ();
/* 46 */     this.timer.reset();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onRender3D(Render3DEvent render3DEvent) {
/* 52 */     if (this.timer.passedMs(((Integer)this.time.getValue()).intValue()))
/* 53 */       return;  AxisAlignedBB pos = RenderUtil.interpolateAxis(new AxisAlignedBB(this.x - 0.3D, this.y, this.z - 0.3D, this.x + 0.3D, this.y + 1.8D, this.z + 0.3D));
/* 54 */     if (((Boolean)this.outline.getValue()).booleanValue())
/* 55 */       RenderUtil.drawBlockOutline(pos, new Color(((Integer)this.outlineR.getValue()).intValue(), ((Integer)this.outlineG.getValue()).intValue(), ((Integer)this.outlineB.getValue()).intValue(), ((Integer)this.outlineA.getValue()).intValue()), ((Float)this.lineWidth.getValue()).floatValue()); 
/* 56 */     if (((Boolean)this.box.getValue()).booleanValue())
/* 57 */       RenderUtil.drawFilledBox(pos, ColorUtil.toRGBA(((Integer)this.boxR.getValue()).intValue(), ((Integer)this.boxG.getValue()).intValue(), ((Integer)this.boxB.getValue()).intValue(), ((Integer)this.boxA.getValue()).intValue())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\ChorusESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */