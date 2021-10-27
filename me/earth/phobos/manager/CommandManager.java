/*     */ package me.earth.phobos.manager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.command.commands.ClearRamCommand;
/*     */ import me.earth.phobos.features.command.commands.ConfigCommand;
/*     */ import me.earth.phobos.features.command.commands.QueueCommand;
/*     */ import me.earth.phobos.features.command.commands.ReloadCommand;
/*     */ import me.earth.phobos.features.command.commands.ReloadSoundCommand;
/*     */ import me.earth.phobos.features.command.commands.VClipCommand;
/*     */ 
/*     */ public class CommandManager extends Feature {
/*  13 */   private String clientMessage = "<Acid>";
/*  14 */   private String prefix = ".";
/*  15 */   private final ArrayList<Command> commands = new ArrayList<>();
/*     */   
/*     */   public CommandManager() {
/*  18 */     super("Command");
/*  19 */     this.commands.add(new BindCommand());
/*  20 */     this.commands.add(new ModuleCommand());
/*  21 */     this.commands.add(new PrefixCommand());
/*  22 */     this.commands.add(new ConfigCommand());
/*  23 */     this.commands.add(new FriendCommand());
/*  24 */     this.commands.add(new HelpCommand());
/*  25 */     this.commands.add(new ReloadCommand());
/*  26 */     this.commands.add(new UnloadCommand());
/*  27 */     this.commands.add(new ReloadSoundCommand());
/*  28 */     this.commands.add(new PeekCommand());
/*  29 */     this.commands.add(new XrayCommand());
/*  30 */     this.commands.add(new BookCommand());
/*  31 */     this.commands.add(new CrashCommand());
/*  32 */     this.commands.add(new HistoryCommand());
/*  33 */     this.commands.add(new BaritoneNoStop());
/*  34 */     this.commands.add(new IRCCommand());
/*  35 */     this.commands.add(new CoordsCommand());
/*  36 */     this.commands.add(new OpenFolderCommand());
/*  37 */     this.commands.add(new ClearRamCommand());
/*  38 */     this.commands.add(new VClipCommand());
/*  39 */     this.commands.add(new QueueCommand());
/*  40 */     this.commands.add(new DisconnectCommand());
/*  41 */     this.commands.add(new IpCommand());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] removeElement(String[] input, int indexToDelete) {
/*  52 */     LinkedList<String> result = new LinkedList<>();
/*  53 */     for (int i = 0; i < input.length; i++) {
/*  54 */       if (i != indexToDelete)
/*  55 */         result.add(input[i]); 
/*     */     } 
/*  57 */     return result.<String>toArray(input);
/*     */   }
/*     */   
/*     */   private static String strip(String str, String key) {
/*  61 */     if (str.startsWith(key) && str.endsWith(key)) {
/*  62 */       return str.substring(key.length(), str.length() - key.length());
/*     */     }
/*  64 */     return str;
/*     */   }
/*     */   
/*     */   public void executeCommand(String command) {
/*  68 */     String[] parts = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
/*  69 */     String name = parts[0].substring(1);
/*  70 */     String[] args = removeElement(parts, 0);
/*  71 */     for (int i = 0; i < args.length; i++) {
/*  72 */       if (args[i] != null)
/*  73 */         args[i] = strip(args[i], "\""); 
/*     */     } 
/*  75 */     for (Command c : this.commands) {
/*  76 */       if (!c.getName().equalsIgnoreCase(name))
/*  77 */         continue;  c.execute(parts);
/*     */       return;
/*     */     } 
/*  80 */     Command.sendMessage("Unknown command. try 'commands' for a list of commands.");
/*     */   }
/*     */   
/*     */   public Command getCommandByName(String name) {
/*  84 */     for (Command command : this.commands) {
/*  85 */       if (!command.getName().equals(name))
/*  86 */         continue;  return command;
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<Command> getCommands() {
/*  92 */     return this.commands;
/*     */   }
/*     */   
/*     */   public String getClientMessage() {
/*  96 */     return this.clientMessage;
/*     */   }
/*     */   
/*     */   public void setClientMessage(String clientMessage) {
/* 100 */     this.clientMessage = clientMessage;
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 104 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 108 */     this.prefix = prefix;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\manager\CommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */