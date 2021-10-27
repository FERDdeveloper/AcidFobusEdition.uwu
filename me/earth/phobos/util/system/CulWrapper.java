/*    */ package me.earth.phobos.util.system;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.network.Packet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CulWrapper
/*    */ {
/*    */   public GameSettings mcSettings() {
/* 20 */     return (INSTANCE.mc()).field_71474_y;
/*    */   }
/*    */   
/*    */   public WorldClient world() {
/* 24 */     return (INSTANCE.mc()).field_71441_e;
/*    */   }
/*    */   
/*    */   public Minecraft mc() {
/* 28 */     return Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public EntityPlayerSP player() {
/* 32 */     return (INSTANCE.mc()).field_71439_g;
/*    */   }
/*    */ 
/*    */   
/* 36 */   public static CulWrapper INSTANCE = new CulWrapper();
/*    */ 
/*    */   
/*    */   public void sendPacket(Packet packet) {
/* 40 */     (player()).field_71174_a.func_147297_a(packet);
/*    */   }
/*    */   
/*    */   public void copy(String data) {
/* 44 */     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(data), null);
/*    */   }
/*    */   
/*    */   public InventoryPlayer inventory() {
/* 48 */     return (player()).field_71071_by;
/*    */   }
/*    */   
/*    */   public FontRenderer fontRenderer() {
/* 52 */     return (INSTANCE.mc()).field_71466_p;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\system\CulWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */