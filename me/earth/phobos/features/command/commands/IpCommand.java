/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class IpCommand
/*    */   extends Command
/*    */ {
/*    */   public IpCommand() {
/* 15 */     super("IP", new String[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 22 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 24 */     if (mc.func_147104_D() != null) {
/* 25 */       StringSelection contents = new StringSelection((mc.func_147104_D()).field_78845_b);
/* 26 */       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 27 */       clipboard.setContents(contents, null);
/* 28 */       Command.sendMessage("Copied IP to clipboard");
/*    */     } else {
/* 30 */       Command.sendMessage("Error, Join a server");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\IpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */