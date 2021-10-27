/*    */ package me.earth.phobos.features.command.commands;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import me.earth.phobos.features.command.Command;
/*    */ 
/*    */ public class QueueCommand extends Command {
/*    */   public QueueCommand() {
/* 14 */     super("queue", new String[] { "priority, regular" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(String[] commands) {
/* 19 */     if (commands.length == 1 || commands.length == 0) {
/* 20 */       sendMessage("bruh, specify the type! (priority/regular)");
/*    */       return;
/*    */     } 
/* 23 */     String sURL = "https://2bqueue.info/*";
/*    */     
/* 25 */     String adjsaofj = commands[0];
/* 26 */     if (adjsaofj.equalsIgnoreCase("regular")) {
/*    */       try {
/* 28 */         URL url = new URL(sURL);
/* 29 */         URLConnection request = url.openConnection();
/* 30 */         request.connect();
/* 31 */         JsonParser jp = new JsonParser();
/* 32 */         JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
/* 33 */         JsonObject rootobj = root.getAsJsonObject();
/* 34 */         String aaaaaa = rootobj.get("regular").getAsString();
/* 35 */         sendMessage("Regular queue currently have: " + aaaaaa);
/* 36 */       } catch (IOException e) {
/* 37 */         e.printStackTrace();
/*    */       } 
/* 39 */     } else if (adjsaofj.equalsIgnoreCase("priority")) {
/*    */       try {
/* 41 */         URL url = new URL(sURL);
/* 42 */         URLConnection request = url.openConnection();
/* 43 */         request.connect();
/* 44 */         JsonParser jp = new JsonParser();
/* 45 */         JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
/* 46 */         JsonObject rootobj = root.getAsJsonObject();
/* 47 */         String aaaaaa = rootobj.get("prio").getAsString();
/* 48 */         sendMessage("Priority queue currently have: " + aaaaaa);
/* 49 */       } catch (IOException e) {
/* 50 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\command\commands\QueueCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */