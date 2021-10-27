/*    */ package me.earth.phobos.features.command.commands;
/*    */ 
/*    */ import me.earth.phobos.Phobos;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.client.ClickGui;
/*    */ 
/*    */ public class PrefixCommand
/*    */   extends Command {
/*    */   public PrefixCommand() {
/* 10 */     super("prefix", new String[] { "<char>" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 15 */     if (commands.length == 1) {
/* 16 */       Command.sendMessage("§cSpecify a new prefix.");
/*    */       return;
/*    */     } 
/* 19 */     ((ClickGui)Phobos.moduleManager.getModuleByClass(ClickGui.class)).prefix.setValue(commands[0]);
/* 20 */     Command.sendMessage("Prefix set to §a" + Phobos.commandManager.getPrefix());
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\PrefixCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */