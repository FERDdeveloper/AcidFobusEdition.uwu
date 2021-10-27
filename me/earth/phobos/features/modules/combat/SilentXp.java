/*    */ package me.earth.phobos.features.modules.combat;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ public class SilentXp extends Module {
/*    */   public SilentXp() {
/* 13 */     super("SilentXp", "monkes exp", Module.Category.COMBAT, true, false, false);
/*    */ 
/*    */     
/* 16 */     this.lookPitch = register(new Setting("LookPitch", Integer.valueOf(90), Integer.valueOf(0), Integer.valueOf(100)));
/*    */   }
/*    */   Setting<Integer> lookPitch;
/*    */   private int delay_count;
/*    */   int prvSlot;
/*    */   
/*    */   public void onEnable() {
/* 23 */     this.delay_count = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 28 */     if (mc.field_71462_r == null) {
/* 29 */       usedXp();
/*    */     }
/*    */   }
/*    */   
/*    */   private int findExpInHotbar() {
/* 34 */     int slot = 0;
/* 35 */     for (int i = 0; i < 9; i++) {
/* 36 */       if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151062_by) {
/* 37 */         slot = i;
/*    */         break;
/*    */       } 
/*    */     } 
/* 41 */     return slot;
/*    */   }
/*    */   
/*    */   private void usedXp() {
/* 45 */     int oldPitch = (int)mc.field_71439_g.field_70125_A;
/* 46 */     this.prvSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/* 47 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(findExpInHotbar()));
/* 48 */     mc.field_71439_g.field_70125_A = ((Integer)this.lookPitch.getValue()).intValue();
/* 49 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(mc.field_71439_g.field_70177_z, ((Integer)this.lookPitch.getValue()).intValue(), true));
/* 50 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
/* 51 */     mc.field_71439_g.field_70125_A = oldPitch;
/* 52 */     mc.field_71439_g.field_71071_by.field_70461_c = this.prvSlot;
/* 53 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.prvSlot));
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\SilentXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */