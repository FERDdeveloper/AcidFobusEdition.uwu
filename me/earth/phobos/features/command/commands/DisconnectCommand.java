/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.util.WorldUtil;
/*    */ 
/*    */ public class DisconnectCommand
/*    */   extends Command {
/*    */   public DisconnectCommand() {
/*  9 */     super("Disconnect");
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 14 */     WorldUtil.disconnectFromWorld(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\DisconnectCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */