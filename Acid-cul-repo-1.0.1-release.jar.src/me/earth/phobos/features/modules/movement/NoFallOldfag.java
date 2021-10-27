/*    */ package me.earth.phobos.features.modules.movement;
/*    */ 
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TextComponentString;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ public class NoFallOldfag
/*    */   extends Module {
/* 18 */   private final Setting<Mode> mode = register(new Setting("Mode", Mode.Predict));
/* 19 */   private final Setting<Boolean> disconnect = register(new Setting("Disconnect", Boolean.valueOf(false)));
/* 20 */   private final Setting<Integer> fallDist = register(new Setting("FallDistance", Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(30), v -> (this.mode.getValue() == Mode.Old)));
/*    */   BlockPos n1;
/*    */   
/*    */   public NoFallOldfag() {
/* 24 */     super("NoFallBypass", "nf", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUpdate(TickEvent.ClientTickEvent event) {
/* 29 */     if (nullCheck()) {
/*    */       return;
/*    */     }
/* 32 */     if (((Mode)this.mode.getValue()).equals("Predict") && mc.field_71439_g.field_70143_R > ((Integer)this.fallDist.getValue()).intValue() && predict(new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v))) {
/* 33 */       mc.field_71439_g.field_70181_x = 0.0D;
/* 34 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, this.n1.func_177956_o(), mc.field_71439_g.field_70161_v, false));
/* 35 */       mc.field_71439_g.field_70143_R = 0.0F;
/* 36 */       if (((Boolean)this.disconnect.getValue()).booleanValue()) {
/* 37 */         mc.field_71439_g.field_71174_a.func_147298_b().func_150718_a((ITextComponent)new TextComponentString(ChatFormatting.GOLD + "NoFall"));
/*    */       }
/*    */     } 
/* 40 */     if (((Mode)this.mode.getValue()).equals("Old") && mc.field_71439_g.field_70143_R > ((Integer)this.fallDist.getValue()).intValue()) {
/* 41 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, 0.0D, mc.field_71439_g.field_70161_v, false));
/* 42 */       mc.field_71439_g.field_70143_R = 0.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean predict(BlockPos blockPos) {
/* 47 */     Minecraft mc = Minecraft.func_71410_x();
/* 48 */     this.n1 = blockPos.func_177982_a(0, -((Integer)this.fallDist.getValue()).intValue(), 0);
/* 49 */     return (mc.field_71441_e.func_180495_p(this.n1).func_177230_c() != Blocks.field_150350_a);
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 53 */     Predict,
/* 54 */     Old;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\movement\NoFallOldfag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */