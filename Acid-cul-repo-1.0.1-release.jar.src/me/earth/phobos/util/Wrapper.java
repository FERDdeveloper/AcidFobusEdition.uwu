/*    */ package me.earth.phobos.util;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ public class Wrapper
/*    */ {
/* 21 */   public static Minecraft mc = Minecraft.func_71410_x();
/* 22 */   public static FontRenderer fontRenderer = mc.field_71466_p;
/* 23 */   public static FontRenderer fr = (Minecraft.func_71410_x()).field_71466_p;
/* 24 */   public static volatile Wrapper INSTANCE = new Wrapper();
/*    */   
/*    */   @Nullable
/*    */   public static EntityPlayerSP getSexyPlayer() {
/* 28 */     return mc.field_71439_g;
/*    */   }
/*    */   
/*    */   public static EntityPlayerSP getPlayer() {
/* 32 */     return (getMinecraft()).field_71439_g;
/*    */   }
/*    */   
/*    */   public static Minecraft getMinecraft() {
/* 36 */     return mc;
/*    */   }
/*    */   
/*    */   public static World getWorld() {
/* 40 */     return (World)(getMinecraft()).field_71441_e;
/*    */   }
/*    */   
/*    */   public static int getKey(String keyname) {
/* 44 */     return Keyboard.getKeyIndex(keyname.toUpperCase());
/*    */   }
/*    */   
/*    */   public Minecraft mc() {
/* 48 */     return Minecraft.func_71410_x();
/*    */   }
/*    */   
/*    */   public EntityPlayerSP player() {
/* 52 */     return (INSTANCE.mc()).field_71439_g;
/*    */   }
/*    */   
/*    */   public WorldClient world() {
/* 56 */     return (INSTANCE.mc()).field_71441_e;
/*    */   }
/*    */   
/*    */   public GameSettings mcSettings() {
/* 60 */     return (INSTANCE.mc()).field_71474_y;
/*    */   }
/*    */   
/*    */   public FontRenderer fontRenderer() {
/* 64 */     return (INSTANCE.mc()).field_71466_p;
/*    */   }
/*    */   
/*    */   public void sendPacket(Packet packet) {
/* 68 */     (player()).field_71174_a.func_147297_a(packet);
/*    */   }
/*    */   
/*    */   public PlayerControllerMP controller() {
/* 72 */     return (INSTANCE.mc()).field_71442_b;
/*    */   }
/*    */   
/*    */   public void swingArm() {
/* 76 */     player().func_184609_a(EnumHand.MAIN_HAND);
/*    */   }
/*    */   
/*    */   public void attack(Entity entity) {
/* 80 */     controller().func_78764_a((EntityPlayer)player(), entity);
/*    */   }
/*    */   
/*    */   public void copy(String content) {
/* 84 */     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(content), null);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\Wrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */