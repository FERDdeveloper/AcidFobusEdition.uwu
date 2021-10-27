/*     */ package me.earth.phobos.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.util.UUIDTypeAdapter;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Scanner;
/*     */ import java.util.UUID;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import net.minecraft.advancements.AdvancementManager;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.json.simple.JSONValue;
/*     */ 
/*     */ public class PlayerUtil
/*     */   implements Util
/*     */ {
/*  40 */   public static Timer timer = new Timer();
/*  41 */   private static JsonParser PARSER = new JsonParser();
/*     */ 
/*     */   
/*     */   public static String getNameFromUUID(UUID uuid) {
/*     */     try {
/*  46 */       lookUpName process = new lookUpName(uuid);
/*  47 */       Thread thread = new Thread(process);
/*  48 */       thread.start();
/*  49 */       thread.join();
/*  50 */       return process.getName();
/*  51 */     } catch (Exception e) {
/*  52 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getNameFromUUID(String uuid) {
/*     */     try {
/*  58 */       lookUpName process = new lookUpName(uuid);
/*  59 */       Thread thread = new Thread(process);
/*  60 */       thread.start();
/*  61 */       thread.join();
/*  62 */       return process.getName();
/*  63 */     } catch (Exception e) {
/*  64 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static UUID getUUIDFromName(String name) {
/*     */     try {
/*  70 */       lookUpUUID process = new lookUpUUID(name);
/*  71 */       Thread thread = new Thread(process);
/*  72 */       thread.start();
/*  73 */       thread.join();
/*  74 */       return process.getUUID();
/*  75 */     } catch (Exception e) {
/*  76 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String requestIDs(String data) {
/*     */     try {
/*  82 */       String query = "https://api.mojang.com/profiles/minecraft";
/*  83 */       URL url = new URL("https://api.mojang.com/profiles/minecraft");
/*  84 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/*  85 */       conn.setConnectTimeout(5000);
/*  86 */       conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
/*  87 */       conn.setDoOutput(true);
/*  88 */       conn.setDoInput(true);
/*  89 */       conn.setRequestMethod("POST");
/*  90 */       OutputStream os = conn.getOutputStream();
/*  91 */       os.write(data.getBytes(StandardCharsets.UTF_8));
/*  92 */       os.close();
/*  93 */       InputStream in = new BufferedInputStream(conn.getInputStream());
/*  94 */       String res = convertStreamToString(in);
/*  95 */       in.close();
/*  96 */       conn.disconnect();
/*  97 */       return res;
/*  98 */     } catch (Exception e) {
/*  99 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static BlockPos getPlayerPos() {
/* 104 */     return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
/*     */   }
/*     */   
/*     */   public static BlockPos getPlayerPos(double pY) {
/* 108 */     return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u + pY), Math.floor(mc.field_71439_g.field_70161_v));
/*     */   }
/*     */   
/*     */   public static String convertStreamToString(InputStream is) {
/* 112 */     Scanner s = (new Scanner(is)).useDelimiter("\\A");
/* 113 */     return s.hasNext() ? s.next() : "/";
/*     */   }
/*     */   
/*     */   public static List<String> getHistoryOfNames(UUID id) {
/*     */     try {
/* 118 */       JsonArray array = getResources(new URL("https://api.mojang.com/user/profiles/" + getIdNoHyphens(id) + "/names"), "GET").getAsJsonArray();
/* 119 */       ArrayList<String> temp = Lists.newArrayList();
/*     */       
/* 121 */       for (JsonElement e : array) {
/* 122 */         JsonObject node = e.getAsJsonObject();
/* 123 */         String name = node.get("name").getAsString();
/* 124 */         long changedAt = node.has("changedToAt") ? node.get("changedToAt").getAsLong() : 0L;
/* 125 */         temp.add(name + "§8" + (new Date(changedAt)).toString());
/*     */       } 
/* 127 */       Collections.sort(temp);
/* 128 */       return temp;
/* 129 */     } catch (Exception ignored) {
/* 130 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getIdNoHyphens(UUID uuid) {
/* 135 */     return uuid.toString().replaceAll("-", "");
/*     */   }
/*     */   
/*     */   private static JsonElement getResources(URL url, String request) throws Exception {
/* 139 */     return getResources(url, request, null);
/*     */   }
/*     */   
/*     */   private static JsonElement getResources(URL url, String request, JsonElement element) throws Exception {
/* 143 */     HttpsURLConnection connection = null;
/*     */     try {
/* 145 */       connection = (HttpsURLConnection)url.openConnection();
/* 146 */       connection.setDoOutput(true);
/* 147 */       connection.setRequestMethod(request);
/* 148 */       connection.setRequestProperty("Content-Type", "application/json");
/* 149 */       if (element != null) {
/* 150 */         DataOutputStream output = new DataOutputStream(connection.getOutputStream());
/* 151 */         output.writeBytes(AdvancementManager.field_192783_b.toJson(element));
/* 152 */         output.close();
/*     */       } 
/* 154 */       Scanner scanner = new Scanner(connection.getInputStream());
/* 155 */       StringBuilder builder = new StringBuilder();
/* 156 */       while (scanner.hasNextLine()) {
/* 157 */         builder.append(scanner.nextLine());
/* 158 */         builder.append('\n');
/*     */       } 
/* 160 */       scanner.close();
/* 161 */       String json = builder.toString();
/* 162 */       JsonElement data = PARSER.parse(json);
/* 163 */       return data;
/*     */     } finally {
/* 165 */       if (connection != null)
/* 166 */         connection.disconnect(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class lookUpUUID
/*     */     implements Runnable {
/*     */     private final String name;
/*     */     private volatile UUID uuid;
/*     */     
/*     */     public lookUpUUID(String name) {
/* 176 */       this.name = name;
/*     */     }
/*     */     
/*     */     public static boolean isInHole() {
/* 180 */       BlockPos player_block = PlayerUtil.getPlayerPos();
/* 181 */       return (PlayerUtil.mc.field_71441_e.func_180495_p(player_block.func_177974_f()).func_177230_c() != Blocks.field_150350_a && PlayerUtil.mc.field_71441_e.func_180495_p(player_block.func_177976_e()).func_177230_c() != Blocks.field_150350_a && PlayerUtil.mc.field_71441_e.func_180495_p(player_block.func_177978_c()).func_177230_c() != Blocks.field_150350_a && PlayerUtil.mc.field_71441_e.func_180495_p(player_block.func_177968_d()).func_177230_c() != Blocks.field_150350_a);
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       NetworkPlayerInfo profile;
/*     */       try {
/* 188 */         ArrayList<NetworkPlayerInfo> infoMap = new ArrayList<>(((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(Util.mc.func_147114_u())).func_175106_d());
/* 189 */         profile = infoMap.stream().filter(networkPlayerInfo -> networkPlayerInfo.func_178845_a().getName().equalsIgnoreCase(this.name)).findFirst().orElse(null);
/* 190 */         assert profile != null;
/* 191 */         this.uuid = profile.func_178845_a().getId();
/* 192 */       } catch (Exception e2) {
/* 193 */         profile = null;
/*     */       } 
/* 195 */       if (profile == null) {
/* 196 */         Command.sendMessage("Player isn't online. Looking up UUID..");
/* 197 */         String s = PlayerUtil.requestIDs("[\"" + this.name + "\"]");
/* 198 */         if (s == null || s.isEmpty()) {
/* 199 */           Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
/*     */         } else {
/* 201 */           JsonElement element = (new JsonParser()).parse(s);
/* 202 */           if (element.getAsJsonArray().size() == 0) {
/* 203 */             Command.sendMessage("Couldn't find player ID. (1)");
/*     */           } else {
/*     */             try {
/* 206 */               String id = element.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
/* 207 */               this.uuid = UUIDTypeAdapter.fromString(id);
/* 208 */             } catch (Exception e) {
/* 209 */               e.printStackTrace();
/* 210 */               Command.sendMessage("Couldn't find player ID. (2)");
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public UUID getUUID() {
/* 218 */       return this.uuid;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 222 */       return this.name;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class lookUpName implements Runnable {
/*     */     private final String uuid;
/*     */     private final UUID uuidID;
/*     */     private volatile String name;
/*     */     
/*     */     public lookUpName(String input) {
/* 232 */       this.uuid = input;
/* 233 */       this.uuidID = UUID.fromString(input);
/*     */     }
/*     */     
/*     */     public lookUpName(UUID input) {
/* 237 */       this.uuidID = input;
/* 238 */       this.uuid = input.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 243 */       this.name = lookUpName();
/*     */     }
/*     */     
/*     */     public String lookUpName() {
/* 247 */       EntityPlayer player = null;
/* 248 */       if (Util.mc.field_71441_e != null) {
/* 249 */         player = Util.mc.field_71441_e.func_152378_a(this.uuidID);
/*     */       }
/* 251 */       if (player == null) {
/* 252 */         String url = "https://api.mojang.com/user/profiles/" + this.uuid.replace("-", "") + "/names";
/*     */         try {
/* 254 */           String nameJson = IOUtils.toString(new URL(url));
/* 255 */           JSONArray nameValue = (JSONArray)JSONValue.parseWithException(nameJson);
/* 256 */           String playerSlot = nameValue.get(nameValue.size() - 1).toString();
/* 257 */           JSONObject nameObject = (JSONObject)JSONValue.parseWithException(playerSlot);
/* 258 */           return nameObject.get("name").toString();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 265 */         catch (IOException|org.json.simple.parser.ParseException e) {
/* 266 */           e.printStackTrace();
/* 267 */           return null;
/*     */         } 
/*     */       } 
/* 270 */       return player.func_70005_c_();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 274 */       return this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\PlayerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */