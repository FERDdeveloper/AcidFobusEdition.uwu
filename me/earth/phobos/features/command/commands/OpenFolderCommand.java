/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import java.awt.Desktop;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OpenFolderCommand
/*    */   extends Command
/*    */ {
/*    */   public OpenFolderCommand() {
/* 14 */     super("openfolder", new String[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/*    */     try {
/* 21 */       Desktop.getDesktop().open(new File("acid/"));
/* 22 */       Command.sendMessage("Opened config folder!", false);
/* 23 */     } catch (IOException e) {
/* 24 */       Command.sendMessage("Could not open config folder!", false);
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\OpenFolderCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */