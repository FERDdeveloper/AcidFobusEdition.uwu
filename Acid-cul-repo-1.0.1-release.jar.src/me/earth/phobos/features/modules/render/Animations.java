/*    */ package me.earth.phobos.features.modules.render;
/*    */ 
/*    */ import me.earth.phobos.event.events.PacketEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class Animations
/*    */   extends Module
/*    */ {
/* 15 */   private final Setting<Mode> mode = register(new Setting("OldAnimations", Mode.OneDotEight));
/* 16 */   private final Setting<Swing> swing = register(new Setting("Swing", Swing.Mainhand));
/* 17 */   private final Setting<Boolean> slow = register(new Setting("Slow", Boolean.valueOf(false)));
/*    */ 
/*    */   
/*    */   public Animations() {
/* 21 */     super("Animations", "Change animations.", Module.Category.RENDER, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 27 */     if (nullCheck()) {
/*    */       return;
/*    */     }
/* 30 */     if (this.swing.getValue() == Swing.Offhand) {
/* 31 */       mc.field_71439_g.field_184622_au = EnumHand.OFF_HAND;
/*    */     }
/* 33 */     if (this.mode.getValue() == Mode.OneDotEight && mc.field_71460_t.field_78516_c.field_187470_g >= 0.9D) {
/* 34 */       mc.field_71460_t.field_78516_c.field_187469_f = 1.0F;
/* 35 */       mc.field_71460_t.field_78516_c.field_187467_d = mc.field_71439_g.func_184614_ca();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 42 */     if (((Boolean)this.slow.getValue()).booleanValue()) {
/* 43 */       mc.field_71439_g.func_70690_d(new PotionEffect(MobEffects.field_76419_f, 255000));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 50 */     if (((Boolean)this.slow.getValue()).booleanValue()) {
/* 51 */       mc.field_71439_g.func_184589_d(MobEffects.field_76419_f);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacketSend(PacketEvent.Send send) {
/* 58 */     Object t = send.getPacket();
/* 59 */     if (t instanceof net.minecraft.network.play.client.CPacketAnimation && 
/* 60 */       this.swing.getValue() == Swing.Disable) {
/* 61 */       send.setCanceled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private enum Mode
/*    */   {
/* 68 */     Normal,
/* 69 */     OneDotEight;
/*    */   }
/*    */ 
/*    */   
/*    */   private enum Swing
/*    */   {
/* 75 */     Mainhand,
/* 76 */     Offhand,
/* 77 */     Disable;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\Animations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */