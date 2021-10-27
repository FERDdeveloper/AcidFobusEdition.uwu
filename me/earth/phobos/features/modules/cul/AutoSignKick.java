/*    */ package me.earth.phobos.features.modules.cul;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import me.earth.phobos.util.BlockUtil;
/*    */ import me.earth.phobos.util.InventoryUtil;
/*    */ import me.earth.phobos.util.MathUtil;
/*    */ import me.earth.phobos.util.Timer;
/*    */ import net.minecraft.item.ItemSign;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketPlayerDigging;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class AutoSignKick extends Module {
/* 17 */   Setting<Integer> switchDelay = register(new Setting("SwitchDelay", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(5000)));
/* 18 */   Setting<Integer> placeDelay = register(new Setting("PlaceDelay", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(5000)));
/* 19 */   Setting<Integer> mineDelay = register(new Setting("MineDelay", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(5000)));
/* 20 */   Setting<Integer> range = register(new Setting("Range", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(20)));
/* 21 */   Timer placeTimer = new Timer();
/* 22 */   Timer switchTimer = new Timer();
/* 23 */   Timer mineTimer = new Timer(); private boolean hadBreak;
/*    */   public AutoSignKick() {
/* 25 */     super("AutoSignKick", "stop being lazy lmao", Module.Category.CUL, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 32 */     this.hadBreak = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 37 */     this.hadBreak = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 42 */     for (TileEntity tileEntity : mc.field_71441_e.field_147482_g) {
/* 43 */       if (!(tileEntity instanceof net.minecraft.tileentity.TileEntitySign) || 
/* 44 */         mc.field_71439_g.func_174818_b(tileEntity.func_174877_v()) > MathUtil.square(((Integer)this.range.getValue()).intValue()))
/* 45 */         continue;  Command.sendMessage("Sign located at X: " + tileEntity.func_174877_v().func_177958_n() + ", Y: " + tileEntity.func_174877_v().func_177956_o() + ", Z: " + tileEntity.func_174877_v().func_177952_p());
/* 46 */       BlockPos posTile = tileEntity.func_174877_v();
/* 47 */       if (!this.hadBreak) {
/* 48 */         axeSwitch();
/* 49 */         mineBlock(posTile);
/* 50 */         InventoryUtil.switchToHotbarSlot(ItemSign.class, false);
/* 51 */         Command.sendMessage("Changed to sign hotbar.");
/* 52 */         this.switchTimer.reset();
/* 53 */         place(posTile);
/* 54 */         Command.sendMessage("Done!");
/* 55 */         disable();
/* 56 */         this.hadBreak = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void axeSwitch() {
/* 62 */     if (this.switchTimer.passedMs(((Integer)this.switchDelay.getValue()).longValue() * 3L)) {
/* 63 */       InventoryUtil.switchToHotbarSlot(ItemAxe.class, false);
/* 64 */       Command.sendMessage("Switched to Axe");
/* 65 */       this.switchTimer.reset();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void mineBlock(BlockPos pos) {
/* 70 */     if (this.mineTimer.passedMs(((Integer)this.mineDelay.getValue()).longValue() * 3L)) {
/* 71 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
/* 72 */       Command.sendMessage("Mined");
/* 73 */       this.mineTimer.reset();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void place(BlockPos pos) {
/* 78 */     if (this.placeTimer.passedMs(((Integer)this.placeDelay.getValue()).longValue() * 3L)) {
/* 79 */       BlockUtil.placeBlockSmartRotate(pos, EnumHand.MAIN_HAND, true, true, false);
/* 80 */       Command.sendMessage("Placed sign!");
/* 81 */       this.placeTimer.reset();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\cul\AutoSignKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */