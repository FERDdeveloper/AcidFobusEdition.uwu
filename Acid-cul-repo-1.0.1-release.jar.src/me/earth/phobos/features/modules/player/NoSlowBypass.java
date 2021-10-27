/*    */ package me.earth.phobos.features.modules.player;
/*    */ 
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.util.Wrapper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketEntityAction;
/*    */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class NoSlowBypass
/*    */   extends Module {
/*    */   private boolean sneaking;
/*    */   
/*    */   public NoSlowBypass() {
/* 17 */     super("NoSlow2b", "noslow bypass", Module.Category.MOVEMENT, true, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 24 */     if (Wrapper.mc.field_71441_e != null) {
/* 25 */       Item item = Wrapper.getPlayer().func_184607_cu().func_77973_b();
/* 26 */       if (this.sneaking && ((!Wrapper.getPlayer().func_184587_cr() && item instanceof net.minecraft.item.ItemFood) || item instanceof net.minecraft.item.ItemBow || item instanceof net.minecraft.item.ItemPotion || !(item instanceof net.minecraft.item.ItemFood) || !(item instanceof net.minecraft.item.ItemBow) || !(item instanceof net.minecraft.item.ItemPotion))) {
/* 27 */         (Wrapper.getPlayer()).field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Wrapper.getPlayer(), CPacketEntityAction.Action.STOP_SNEAKING));
/* 28 */         this.sneaking = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onUseItem(LivingEntityUseItemEvent event) {
/* 35 */     if (!this.sneaking) {
/* 36 */       (Wrapper.getPlayer()).field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)Wrapper.getPlayer(), CPacketEntityAction.Action.START_SNEAKING));
/* 37 */       this.sneaking = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\NoSlowBypass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */