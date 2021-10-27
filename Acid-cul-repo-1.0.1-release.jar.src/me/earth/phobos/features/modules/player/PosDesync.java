/*    */ package me.earth.phobos.features.modules.player;
/*    */ 
/*    */ import me.earth.phobos.event.events.PacketEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class PosDesync
/*    */   extends Module
/*    */ {
/*    */   public PosDesync() {
/* 12 */     super("PosDesync", "yes, pos desync", Module.Category.PLAYER, true, false, false);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacketSend(PacketEvent.Send event) {
/* 17 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayer.Position || event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayer.PositionRotation || event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayer.Rotation || event.getPacket() instanceof net.minecraft.network.play.client.CPacketConfirmTeleport)
/* 18 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\PosDesync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */