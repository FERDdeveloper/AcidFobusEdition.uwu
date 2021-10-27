/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import java.text.DecimalFormat;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CoordsCommand
/*    */   extends Command
/*    */ {
/*    */   public CoordsCommand() {
/* 15 */     super("coords", new String[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 21 */     DecimalFormat format = new DecimalFormat("#");
/* 22 */     StringSelection contents = new StringSelection(format.format(mc.field_71439_g.field_70165_t) + ", " + format.format(mc.field_71439_g.field_70163_u) + ", " + format.format(mc.field_71439_g.field_70161_v));
/* 23 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 24 */     clipboard.setContents(contents, null);
/* 25 */     Command.sendMessage("Saved Coordinates To Clipboard.", false);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\CoordsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */