/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import com.mojang.realmsclient.gui.ChatFormatting;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import me.earth.phobos.event.events.PacketEvent;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.play.server.SPacketChunkData;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TextComponentString;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class StashLogger
/*    */   extends Module
/*    */ {
/* 24 */   private final Setting<Integer> chestsToImportantNotify = register(new Setting("Chests", Integer.valueOf(15), Integer.valueOf(1), Integer.valueOf(30)));
/* 25 */   private final Setting<Boolean> chests = register(new Setting("Chests", Boolean.valueOf(true)));
/* 26 */   private final Setting<Boolean> Shulkers = register(new Setting("Shulkers", Boolean.valueOf(true)));
/* 27 */   private final Setting<Boolean> donkeys = register(new Setting("Donkeys", Boolean.valueOf(false)));
/* 28 */   private final Setting<Boolean> writeToFile = register(new Setting("CoordsSaver", Boolean.valueOf(true)));
/*    */   File mainFolder;
/*    */   final Iterator<NBTTagCompound> iterator;
/*    */   
/*    */   public StashLogger() {
/* 33 */     super("StashLogger", "logs stashes", Module.Category.MISC, true, false, false);
/* 34 */     this.mainFolder = new File((Minecraft.func_71410_x()).field_71412_D + File.separator + "Phobos");
/* 35 */     this.iterator = null;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPacket(PacketEvent event) {
/* 40 */     if (nullCheck()) {
/*    */       return;
/*    */     }
/* 43 */     if (event.getPacket() instanceof SPacketChunkData) {
/* 44 */       SPacketChunkData l_Packet = (SPacketChunkData)event.getPacket();
/* 45 */       int l_ChestsCount = 0;
/* 46 */       int shulkers = 0;
/* 47 */       for (NBTTagCompound l_Tag : l_Packet.func_189554_f()) {
/* 48 */         String l_Id = l_Tag.func_74779_i("id");
/* 49 */         if (l_Id.equals("minecraft:chest") && ((Boolean)this.chests.getValue()).booleanValue()) {
/* 50 */           l_ChestsCount++;
/*    */           continue;
/*    */         } 
/* 53 */         if (!l_Id.equals("minecraft:shulker_box") || !((Boolean)this.Shulkers.getValue()).booleanValue())
/* 54 */           continue;  shulkers++;
/*    */       } 
/* 56 */       if (l_ChestsCount >= ((Integer)this.chestsToImportantNotify.getValue()).intValue()) {
/* 57 */         SendMessage(String.format("%s chests located at X: %s, Z: %s", new Object[] { Integer.valueOf(l_ChestsCount), Integer.valueOf(l_Packet.func_149273_e() * 16), Integer.valueOf(l_Packet.func_149271_f() * 16) }), true);
/*    */       }
/* 59 */       if (shulkers > 0) {
/* 60 */         SendMessage(String.format("%s shulker boxes at X: %s, Z: %s", new Object[] { Integer.valueOf(shulkers), Integer.valueOf(l_Packet.func_149273_e() * 16), Integer.valueOf(l_Packet.func_149271_f() * 16) }), true);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void SendMessage(String message, boolean save) {
/* 67 */     String server = Minecraft.func_71410_x().func_71356_B() ? "singleplayer".toUpperCase() : (mc.func_147104_D()).field_78845_b, string = server;
/* 68 */     if (((Boolean)this.writeToFile.getValue()).booleanValue() && save) {
/*    */       try {
/* 70 */         FileWriter writer = new FileWriter(this.mainFolder + "/stashes.txt", true);
/* 71 */         writer.write("[" + server + "]: " + message + "\n");
/* 72 */         writer.close();
/*    */       }
/* 74 */       catch (IOException e) {
/* 75 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 78 */     mc.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.field_187604_bf, 1.0F, 1.0F));
/* 79 */     mc.field_71439_g.func_145747_a((ITextComponent)new TextComponentString(ChatFormatting.RED + "[StashLogger] " + ChatFormatting.GREEN + message));
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\StashLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */