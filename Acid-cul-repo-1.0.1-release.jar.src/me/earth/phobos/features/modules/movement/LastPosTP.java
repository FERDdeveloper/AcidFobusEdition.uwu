/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import me.earth.phobos.features.Feature;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.Util;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ 
/*    */ public class LastPosTP
/*    */   extends Module {
/* 12 */   private final Setting<RubbeMode> mode = register(new Setting("Mode", RubbeMode.Motion));
/* 13 */   private final Setting<Integer> Ym = register(new Setting("Motion", Integer.valueOf(5), Integer.valueOf(1), Integer.valueOf(15), v -> (this.mode.getValue() == RubbeMode.Motion)));
/*    */   
/*    */   public LastPosTP() {
/* 16 */     super("LastPosTP", "Tp u to the last ground pos", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 21 */     if (Feature.fullNullCheck()) {
/*    */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 28 */     switch ((RubbeMode)this.mode.getValue()) {
/*    */       case Motion:
/* 30 */         Util.mc.field_71439_g.field_70181_x = ((Integer)this.Ym.getValue()).intValue();
/*    */         break;
/*    */       
/*    */       case Packet:
/* 34 */         mc.func_147114_u().func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + ((Integer)this.Ym.getValue()).intValue(), mc.field_71439_g.field_70161_v, true));
/*    */         break;
/*    */       
/*    */       case Teleport:
/* 38 */         mc.field_71439_g.func_70634_a(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + ((Integer)this.Ym.getValue()).intValue(), mc.field_71439_g.field_70161_v);
/*    */         break;
/*    */     } 
/* 41 */     toggle();
/*    */   }
/*    */   
/*    */   public enum RubbeMode {
/* 45 */     Motion,
/* 46 */     Teleport,
/* 47 */     Packet;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\LastPosTP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */