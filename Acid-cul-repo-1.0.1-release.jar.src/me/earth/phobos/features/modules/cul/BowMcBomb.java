/*    */ package me.earth.phobos.features.modules.cul;
/*    */ import me.earth.phobos.event.events.PacketEvent;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketEntityAction;
/*    */ import net.minecraft.network.play.client.CPacketPlayer;
/*    */ import net.minecraft.network.play.client.CPacketPlayerDigging;
/*    */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class BowMcBomb extends Module {
/*    */   private boolean shooting;
/*    */   private long lastShootTime;
/*    */   public Setting<Boolean> Bows;
/*    */   public Setting<Boolean> pearls;
/*    */   public Setting<Boolean> eggs;
/*    */   
/*    */   public BowMcBomb() {
/* 23 */     super("BowMcBomb", "Uno hitter w bows", Module.Category.CUL, true, false, false);
/*    */     
/* 25 */     this.Bows = register(new Setting("Bows", Boolean.valueOf(true)));
/* 26 */     this.pearls = register(new Setting("Pearls", Boolean.valueOf(true)));
/* 27 */     this.eggs = register(new Setting("Eggs", Boolean.valueOf(true)));
/* 28 */     this.snowballs = register(new Setting("SnowBallz", Boolean.valueOf(true)));
/* 29 */     this.Timeout = register(new Setting("Timeout", Integer.valueOf(5000), Integer.valueOf(100), Integer.valueOf(20000)));
/* 30 */     this.spoofs = register(new Setting("Spoofs", Integer.valueOf(10), Integer.valueOf(1), Integer.valueOf(300)));
/* 31 */     this.bypass = register(new Setting("Bypass", Boolean.valueOf(false)));
/* 32 */     this.debug = register(new Setting("Debug", Boolean.valueOf(false)));
/*    */   }
/*    */   public Setting<Boolean> snowballs; public Setting<Integer> Timeout; public Setting<Integer> spoofs; public Setting<Boolean> bypass; public Setting<Boolean> debug;
/*    */   
/*    */   public void onEnable() {
/* 37 */     if (isEnabled()) {
/* 38 */       this.shooting = false;
/* 39 */       this.lastShootTime = System.currentTimeMillis();
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacketSend(PacketEvent.Send event) {
/* 45 */     if (event.getStage() != 0)
/*    */       return; 
/* 47 */     if (event.getPacket() instanceof CPacketPlayerDigging) {
/* 48 */       CPacketPlayerDigging packet = (CPacketPlayerDigging)event.getPacket();
/*    */       
/* 50 */       if (packet.func_180762_c() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
/* 51 */         ItemStack handStack = mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND);
/*    */         
/* 53 */         if (!handStack.func_190926_b() && handStack.func_77973_b() != null && handStack.func_77973_b() instanceof net.minecraft.item.ItemBow && ((Boolean)this.Bows.getValue()).booleanValue()) {
/* 54 */           doSpoofs();
/* 55 */           if (((Boolean)this.debug.getValue()).booleanValue()) Command.sendMessage("trying to spoof");
/*    */         
/*    */         } 
/*    */       } 
/* 59 */     } else if (event.getPacket() instanceof CPacketPlayerTryUseItem) {
/* 60 */       CPacketPlayerTryUseItem packet2 = (CPacketPlayerTryUseItem)event.getPacket();
/*    */       
/* 62 */       if (packet2.func_187028_a() == EnumHand.MAIN_HAND) {
/* 63 */         ItemStack handStack = mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND);
/*    */         
/* 65 */         if (!handStack.func_190926_b() && handStack.func_77973_b() != null) {
/* 66 */           if (handStack.func_77973_b() instanceof net.minecraft.item.ItemEgg && ((Boolean)this.eggs.getValue()).booleanValue()) {
/* 67 */             doSpoofs();
/* 68 */           } else if (handStack.func_77973_b() instanceof net.minecraft.item.ItemEnderPearl && ((Boolean)this.pearls.getValue()).booleanValue()) {
/* 69 */             doSpoofs();
/* 70 */           } else if (handStack.func_77973_b() instanceof net.minecraft.item.ItemSnowball && ((Boolean)this.snowballs.getValue()).booleanValue()) {
/* 71 */             doSpoofs();
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void doSpoofs() {
/* 79 */     if (System.currentTimeMillis() - this.lastShootTime >= ((Integer)this.Timeout.getValue()).intValue()) {
/* 80 */       this.shooting = true;
/* 81 */       this.lastShootTime = System.currentTimeMillis();
/*    */       
/* 83 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SPRINTING));
/*    */       
/* 85 */       for (int index = 0; index < ((Integer)this.spoofs.getValue()).intValue(); index++) {
/* 86 */         if (((Boolean)this.bypass.getValue()).booleanValue()) {
/* 87 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0E-10D, mc.field_71439_g.field_70161_v, false));
/* 88 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.0E-10D, mc.field_71439_g.field_70161_v, true));
/*    */         } else {
/* 90 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u - 1.0E-10D, mc.field_71439_g.field_70161_v, true));
/* 91 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0E-10D, mc.field_71439_g.field_70161_v, false));
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 96 */       if (((Boolean)this.debug.getValue()).booleanValue()) Command.sendMessage("Spoofed");
/*    */       
/* 98 */       this.shooting = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\BowMcBomb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */