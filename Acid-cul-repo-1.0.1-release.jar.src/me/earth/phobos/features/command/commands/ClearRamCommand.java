/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ 
/*    */ 
/*    */ public class ClearRamCommand
/*    */   extends Command
/*    */ {
/*    */   public ClearRamCommand() {
/* 10 */     super("clearram");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 16 */     System.gc();
/* 17 */     Command.sendMessage("Finished clearing the ram.", false);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\ClearRamCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */