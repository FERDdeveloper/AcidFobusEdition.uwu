/*    */ package me.earth.phobos.features.command;
/*    */ 
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TextComponentString;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CulCommand
/*    */ {
/* 13 */   public static final Minecraft mc = Minecraft.func_71410_x(); public abstract String[] getClientAlias(); public abstract String getClientSyntax();
/* 14 */   public static String prefix = ".";
/*    */   
/*    */   public abstract void onClientCulCommand(String paramString, String[] paramArrayOfString) throws Exception;
/*    */   
/*    */   public static void sendClientSideMessage(String message) {
/* 19 */     if (mc.field_71439_g == null || mc.field_71441_e == null)
/* 20 */       return;  mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString(ChatFormatting.DARK_RED + "[CousinWare] " + ChatFormatting.WHITE + message));
/*    */   }
/*    */   public static String getClientPrefix() {
/* 23 */     return prefix;
/*    */   }
/*    */   
/*    */   public static void setClientPrefix(String p) {
/* 27 */     prefix = p;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\CulCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */