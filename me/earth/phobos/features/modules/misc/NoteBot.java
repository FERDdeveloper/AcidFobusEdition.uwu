/*     */ package me.earth.phobos.features.modules.misc;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.event.events.ClientEvent;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.event.events.UpdateWalkingPlayerEvent;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.BlockUtil;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayer;
/*     */ import net.minecraft.network.play.client.CPacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
/*     */ import net.minecraft.network.play.server.SPacketBlockAction;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class NoteBot extends Module {
/*  42 */   private final Setting<Boolean> tune = register(new Setting("Tune", Boolean.valueOf(false)));
/*  43 */   private final Setting<Boolean> active = register(new Setting("Active", Boolean.valueOf(false)));
/*  44 */   private final Setting<Boolean> downloadSongs = register(new Setting("DownloadSongs", Boolean.valueOf(false)));
/*  45 */   private final Setting<String> loadFileSet = register(new Setting("Load", "Load File..."));
/*  46 */   private final Map<Sound, Byte> soundBytes = new HashMap<>();
/*  47 */   private final List<SoundEntry> soundEntries = new ArrayList<>();
/*  48 */   private final List<BlockPos> posList = new ArrayList<>();
/*  49 */   private final File file = new File(Phobos.fileManager.getNotebot().toString());
/*     */   private IRegister[] registers;
/*     */   private int soundIndex;
/*     */   private int index;
/*     */   private Map<BlockPos, AtomicInteger> posPitch;
/*     */   private Map<Sound, BlockPos[]> soundPositions;
/*     */   private BlockPos currentPos;
/*     */   private BlockPos nextPos;
/*     */   private BlockPos endPos;
/*     */   private int tuneStage;
/*     */   private int tuneIndex;
/*     */   private boolean tuned;
/*     */   
/*     */   public NoteBot() {
/*  63 */     super("NoteBot", "Plays songs.", Module.Category.MISC, true, false, false);
/*     */   }
/*     */   
/*     */   public static Map<Sound, BlockPos[]> setUpSoundMap() {
/*  67 */     BlockPos var0 = mc.field_71439_g.func_180425_c();
/*  68 */     LinkedHashMap<Sound, BlockPos[]> result = (LinkedHashMap)new LinkedHashMap<>();
/*  69 */     HashMap<Object, Object> atomicSounds = new HashMap<>();
/*  70 */     Arrays.<Sound>asList(Sound.values()).forEach(sound -> {
/*     */           BlockPos[] var10002 = new BlockPos[25];
/*     */           result.put(sound, var10002);
/*     */           atomicSounds.put(sound, new AtomicInteger());
/*     */         });
/*  75 */     for (int x = -6; x < 6; x++) {
/*  76 */       for (int y = -1; y < 5; y++) {
/*  77 */         for (int z = -6; z < 6; z++) {
/*     */ 
/*     */           
/*  80 */           BlockPos pos = mc.field_71439_g.func_180425_c().func_177982_a(x, y, z);
/*  81 */           Block block = mc.field_71441_e.func_180495_p(pos).func_177230_c(); Sound sound2; int soundByte;
/*  82 */           if (distanceSqToCenter(pos) < 27.040000000000003D && block == Blocks.field_150323_B && (soundByte = ((AtomicInteger)atomicSounds.get(sound2 = getSoundFromBlockState(mc.field_71441_e.func_180495_p(pos.func_177977_b())))).getAndIncrement()) < 25)
/*     */           {
/*  84 */             ((BlockPos[])result.get(sound2))[soundByte] = pos; } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  88 */     return (Map<Sound, BlockPos[]>)result;
/*     */   }
/*     */   
/*     */   private static double distanceSqToCenter(BlockPos pos) {
/*  92 */     double var1 = Math.abs(mc.field_71439_g.field_70165_t - pos.func_177958_n() - 0.5D);
/*  93 */     double var3 = Math.abs(mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e() - pos.func_177956_o() - 0.5D);
/*  94 */     double var5 = Math.abs(mc.field_71439_g.field_70161_v - pos.func_177952_p() - 0.5D);
/*  95 */     return var1 * var1 + var3 * var3 + var5 * var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IRegister[] createRegister(File file) throws IOException {
/* 100 */     FileInputStream fileInputStream = new FileInputStream(file);
/* 101 */     byte[] arrby = new byte[fileInputStream.available()];
/* 102 */     fileInputStream.read(arrby);
/* 103 */     ArrayList<IRegister> arrayList = new ArrayList<>();
/* 104 */     boolean bl = true;
/* 105 */     byte[] arrby2 = arrby;
/* 106 */     int n4 = arrby2.length;
/* 107 */     for (int i = 0; i < arrby2.length; ) {
/* 108 */       int n2 = arrby2[i];
/* 109 */       if (n2 != 64) { i++; continue; }
/* 110 */        bl = false;
/*     */     } 
/*     */     
/* 113 */     int n = 0;
/* 114 */     int n6 = 0;
/* 115 */     while (n6 < arrby.length) {
/* 116 */       n4 = arrby[n];
/* 117 */       if (n4 == (bl ? 5 : 64)) {
/* 118 */         byte[] arrby3 = { arrby[++n], arrby[++n] };
/* 119 */         byte[] arrby4 = arrby3;
/* 120 */         int n2 = arrby3[0] & 0xFF | (arrby4[1] & 0xFF) << 8;
/* 121 */         arrayList.add(new SimpleRegister(n2));
/*     */       } else {
/* 123 */         arrayList.add(new SoundRegister(Sound.values()[n4], arrby[++n]));
/*     */       } 
/* 125 */       n6 = ++n;
/*     */     } 
/* 127 */     ArrayList<IRegister> arrayList2 = arrayList;
/* 128 */     return arrayList2.<IRegister>toArray(new IRegister[arrayList2.size()]);
/*     */   }
/*     */   
/*     */   public static void unzip(File file1, File fileIn) {
/*     */     ZipEntry zipEntry;
/*     */     ZipInputStream zipInputStream;
/* 134 */     byte[] var2 = new byte[1024];
/*     */     try {
/* 136 */       if (!fileIn.exists()) {
/* 137 */         fileIn.mkdir();
/*     */       }
/* 139 */       zipInputStream = new ZipInputStream(new FileInputStream(file1));
/* 140 */       zipEntry = zipInputStream.getNextEntry();
/* 141 */     } catch (IOException ioe) {
/* 142 */       ioe.printStackTrace();
/*     */       
/*     */       return;
/*     */     } 
/*     */     while (true) {
/*     */       FileOutputStream outputStream;
/*     */       try {
/* 149 */         if (zipEntry == null)
/* 150 */           break;  String fileName = zipEntry.getName();
/* 151 */         File newFile = new File(fileIn, fileName);
/* 152 */         (new File(newFile.getParent())).mkdirs();
/* 153 */         outputStream = new FileOutputStream(newFile); int index;
/* 154 */         while ((index = zipInputStream.read(var2)) > 0) {
/* 155 */           outputStream.write(var2, 0, index);
/*     */         }
/* 157 */       } catch (IOException ioe) {
/* 158 */         ioe.printStackTrace();
/*     */         return;
/*     */       } 
/*     */       try {
/* 162 */         outputStream.close();
/* 163 */         zipEntry = zipInputStream.getNextEntry();
/* 164 */       } catch (IOException ioe) {
/* 165 */         ioe.printStackTrace();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     try {
/* 170 */       zipInputStream.closeEntry();
/* 171 */       zipInputStream.close();
/* 172 */     } catch (IOException ioe) {
/* 173 */       FileOutputStream outputStream; outputStream.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Sound getSoundFromBlockState(IBlockState state) {
/* 178 */     if (state.func_177230_c() == Blocks.field_150435_aG) {
/* 179 */       return Sound.CLAY;
/*     */     }
/* 181 */     if (state.func_177230_c() == Blocks.field_150340_R) {
/* 182 */       return Sound.GOLD;
/*     */     }
/* 184 */     if (state.func_177230_c() == Blocks.field_150325_L) {
/* 185 */       return Sound.WOOL;
/*     */     }
/* 187 */     if (state.func_177230_c() == Blocks.field_150403_cj) {
/* 188 */       return Sound.ICE;
/*     */     }
/* 190 */     if (state.func_177230_c() == Blocks.field_189880_di) {
/* 191 */       return Sound.BONE;
/*     */     }
/* 193 */     if (state.func_185904_a() == Material.field_151576_e) {
/* 194 */       return Sound.ROCK;
/*     */     }
/* 196 */     if (state.func_185904_a() == Material.field_151595_p) {
/* 197 */       return Sound.SAND;
/*     */     }
/* 199 */     if (state.func_185904_a() == Material.field_151592_s) {
/* 200 */       return Sound.GLASS;
/*     */     }
/* 202 */     return (state.func_185904_a() == Material.field_151575_d) ? Sound.WOOD : Sound.NONE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 207 */     if (fullNullCheck()) {
/* 208 */       disable();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 214 */     if (nullCheck()) {
/* 215 */       disable();
/*     */       return;
/*     */     } 
/* 218 */     this.soundEntries.clear();
/* 219 */     getNoteBlocks();
/* 220 */     this.soundIndex = 0;
/* 221 */     this.index = 0;
/* 222 */     resetTuning();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onSettingChange(ClientEvent event) {
/* 227 */     if (event.getStage() == 2 && event.getSetting() != null && equals(event.getSetting().getFeature())) {
/* 228 */       if (event.getSetting().equals(this.loadFileSet)) {
/* 229 */         String file = (String)this.loadFileSet.getPlannedValue();
/*     */         try {
/* 231 */           this.registers = createRegister(new File("phobos/notebot/" + file));
/* 232 */           Command.sendMessage("Loaded: " + file);
/* 233 */         } catch (Exception e) {
/* 234 */           Command.sendMessage("An Error occurred with " + file);
/* 235 */           e.printStackTrace();
/*     */         } 
/* 237 */         event.setCanceled(true);
/* 238 */       } else if (event.getSetting().equals(this.tune) && ((Boolean)this.tune.getPlannedValue()).booleanValue()) {
/* 239 */         resetTuning();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/* 246 */     if (((Boolean)this.tune.getValue()).booleanValue() && event.getPacket() instanceof SPacketBlockAction && this.tuneStage == 0 && this.soundPositions != null) {
/* 247 */       SPacketBlockAction packet = (SPacketBlockAction)event.getPacket();
/* 248 */       Sound sound = Sound.values()[packet.func_148869_g()];
/* 249 */       int pitch = packet.func_148864_h();
/* 250 */       BlockPos[] positions = this.soundPositions.get(sound);
/* 251 */       for (int i = 0; i < 25; ) {
/* 252 */         BlockPos position = positions[i];
/* 253 */         if (!packet.func_179825_a().equals(position)) { i++; continue; }
/* 254 */          if (((AtomicInteger)this.posPitch.get(position)).intValue() != -1)
/* 255 */           break;  int pitchDif = i - pitch;
/* 256 */         if (pitchDif < 0) {
/* 257 */           pitchDif += 25;
/*     */         }
/* 259 */         ((AtomicInteger)this.posPitch.get(position)).set(pitchDif);
/* 260 */         if (pitchDif == 0)
/* 261 */           break;  this.tuned = false;
/*     */       } 
/*     */       
/* 264 */       if (this.endPos.equals(packet.func_179825_a()) && this.tuneIndex >= this.posPitch.values().size()) {
/* 265 */         this.tuneStage = 1;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdateWalkingPlayerEvent(UpdateWalkingPlayerEvent event) {
/* 272 */     if (((Boolean)this.downloadSongs.getValue()).booleanValue()) {
/* 273 */       downloadSongs();
/* 274 */       Command.sendMessage("Songs downloaded");
/* 275 */       this.downloadSongs.setValue(Boolean.valueOf(false));
/*     */     } 
/* 277 */     if (event.getStage() == 0) {
/* 278 */       if (((Boolean)this.tune.getValue()).booleanValue()) {
/* 279 */         tunePre();
/* 280 */       } else if (((Boolean)this.active.getValue()).booleanValue()) {
/* 281 */         noteBotPre();
/*     */       } 
/* 283 */     } else if (((Boolean)this.tune.getValue()).booleanValue()) {
/* 284 */       tunePost();
/* 285 */     } else if (((Boolean)this.active.getValue()).booleanValue()) {
/* 286 */       noteBotPost();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tunePre() {
/* 291 */     this.currentPos = null;
/* 292 */     if (this.tuneStage == 1 && getAtomicBlockPos((BlockPos)null) == null) {
/* 293 */       if (this.tuned) {
/* 294 */         Command.sendMessage("Done tuning.");
/* 295 */         this.tune.setValue(Boolean.valueOf(false));
/*     */       } else {
/* 297 */         this.tuned = true;
/* 298 */         this.tuneStage = 0;
/* 299 */         this.tuneIndex = 0;
/*     */       } 
/*     */     } else {
/* 302 */       if (this.tuneStage != 0) {
/* 303 */         this.nextPos = this.currentPos = getAtomicBlockPos(this.nextPos);
/*     */       } else {
/* 305 */         while (this.tuneIndex < 250 && this.currentPos == null) {
/* 306 */           this.currentPos = ((BlockPos[])this.soundPositions.get(Sound.values()[(int)Math.floor((this.tuneIndex / 25))]))[this.tuneIndex % 25];
/* 307 */           this.tuneIndex++;
/*     */         } 
/*     */       } 
/* 310 */       if (this.currentPos != null) {
/* 311 */         Phobos.rotationManager.lookAtPos(this.currentPos);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tunePost() {
/* 317 */     if (this.tuneStage == 0 && this.currentPos != null) {
/* 318 */       EnumFacing facing = BlockUtil.getFacing(this.currentPos);
/* 319 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.currentPos, facing));
/* 320 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentPos, facing));
/* 321 */     } else if (this.currentPos != null) {
/* 322 */       ((AtomicInteger)this.posPitch.get(this.currentPos)).decrementAndGet();
/* 323 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(this.currentPos, BlockUtil.getFacing(this.currentPos), EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void resetTuning() {
/* 328 */     if (mc.field_71441_e == null || mc.field_71439_g == null) {
/* 329 */       disable();
/*     */       return;
/*     */     } 
/* 332 */     this.tuned = true;
/* 333 */     this.soundPositions = setUpSoundMap();
/* 334 */     this.posPitch = new LinkedHashMap<>();
/* 335 */     this.soundPositions.values().forEach(array -> Arrays.<BlockPos>asList(array).forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 341 */     this.tuneStage = 0;
/* 342 */     this.tuneIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BlockPos getAtomicBlockPos(BlockPos blockPos) {
/* 348 */     Iterator<Map.Entry<BlockPos, AtomicInteger>> iterator = this.posPitch.entrySet().iterator();
/*     */     while (true) {
/* 350 */       if (!iterator.hasNext()) {
/* 351 */         return null;
/*     */       }
/* 353 */       Map.Entry<BlockPos, AtomicInteger> entry = iterator.next();
/* 354 */       BlockPos blockPos2 = entry.getKey();
/* 355 */       AtomicInteger atomicInteger = entry.getValue();
/* 356 */       if (blockPos2 != null && !blockPos2.equals(blockPos) && atomicInteger.intValue() > 0)
/* 357 */         return blockPos2; 
/*     */     } 
/*     */   }
/*     */   private void noteBotPre() {
/* 361 */     this.posList.clear();
/* 362 */     if (this.registers == null) {
/*     */       return;
/*     */     }
/* 365 */     while (this.index < this.registers.length) {
/* 366 */       IRegister register = this.registers[this.index];
/* 367 */       if (register instanceof SimpleRegister) {
/* 368 */         SimpleRegister simpleRegister = (SimpleRegister)register;
/* 369 */         if (++this.soundIndex >= simpleRegister.getSound()) {
/* 370 */           this.index++;
/* 371 */           this.soundIndex = 0;
/*     */         } 
/* 373 */         if (this.posList.size() > 0) {
/* 374 */           BlockPos blockPos = this.posList.get(0);
/* 375 */           Phobos.rotationManager.lookAtPos(blockPos);
/*     */         } 
/*     */         return;
/*     */       } 
/* 379 */       if (!(register instanceof SoundRegister))
/* 380 */         continue;  SoundRegister soundRegister = (SoundRegister)register;
/* 381 */       BlockPos pos = getRegisterPos(soundRegister);
/* 382 */       if (pos != null) {
/* 383 */         this.posList.add(pos);
/*     */       }
/* 385 */       this.index++;
/*     */     } 
/* 387 */     this.index = 0;
/*     */   }
/*     */   
/*     */   private void noteBotPost() {
/* 391 */     for (int i = 0; i < this.posList.size(); i++) {
/* 392 */       BlockPos pos = this.posList.get(i);
/* 393 */       if (pos != null) {
/* 394 */         if (i != 0) {
/* 395 */           float[] rotations = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F)));
/* 396 */           mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.field_71439_g.field_70122_E));
/*     */         } 
/* 398 */         clickNoteBlock(pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void getNoteBlocks() {
/* 403 */     fillSoundBytes();
/* 404 */     for (int x = -6; x < 6; x++) {
/* 405 */       for (int y = -1; y < 5; y++) {
/* 406 */         for (int z = -6; z < 6; z++) {
/*     */ 
/*     */           
/* 409 */           BlockPos pos = mc.field_71439_g.func_180425_c().func_177982_a(x, y, z);
/* 410 */           Block block = mc.field_71441_e.func_180495_p(pos).func_177230_c(); Sound sound; byte soundByte;
/* 411 */           if (pos.func_177957_d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v) < 27.0D && block == Blocks.field_150323_B && (soundByte = ((Byte)this.soundBytes.get(sound = getSoundFromBlockState(mc.field_71441_e.func_180495_p(pos.func_177977_b())))).byteValue()) <= 25) {
/*     */             
/* 413 */             this.soundEntries.add(new SoundEntry(pos, new SoundRegister(sound, soundByte)));
/* 414 */             this.soundBytes.replace(sound, Byte.valueOf((byte)(soundByte + 1)));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void fillSoundBytes() {
/* 421 */     this.soundBytes.clear();
/* 422 */     for (Sound sound : Sound.values()) {
/* 423 */       this.soundBytes.put(sound, Byte.valueOf((byte)0));
/*     */     }
/*     */   }
/*     */   
/*     */   private void clickNoteBlock(BlockPos pos) {
/* 428 */     EnumFacing facing = BlockUtil.getFacing(pos);
/* 429 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
/* 430 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, pos, facing));
/*     */   }
/*     */   
/*     */   private BlockPos getRegisterPos(SoundRegister register) {
/* 434 */     SoundEntry soundEntry = this.soundEntries.stream().filter(entry -> entry.getRegister().equals(register)).findFirst().orElse(null);
/* 435 */     if (soundEntry == null) {
/* 436 */       return null;
/*     */     }
/* 438 */     return soundEntry.getPos();
/*     */   }
/*     */   
/*     */   private void downloadSongs() {
/* 442 */     (new Thread(() -> {
/*     */           try {
/*     */             File songFile = new File(this.file, "songs.zip");
/*     */             FileChannel fileChannel = (new FileOutputStream(songFile)).getChannel();
/*     */             ReadableByteChannel readableByteChannel = Channels.newChannel((new URL("https://www.futureclient.net/future/songs.zip")).openStream());
/*     */             fileChannel.transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
/*     */             unzip(songFile, this.file);
/*     */             songFile.deleteOnExit();
/* 450 */           } catch (IOException ioe) {
/*     */             ioe.printStackTrace();
/*     */           } 
/* 453 */         })).start();
/*     */   }
/*     */   
/*     */   public enum Sound {
/* 457 */     NONE,
/* 458 */     GOLD,
/* 459 */     GLASS,
/* 460 */     BONE,
/* 461 */     WOOD,
/* 462 */     CLAY,
/* 463 */     ICE,
/* 464 */     SAND,
/* 465 */     ROCK,
/* 466 */     WOOL;
/*     */   }
/*     */ 
/*     */   
/*     */   public static interface IRegister {}
/*     */   
/*     */   public static class SoundRegister
/*     */     implements IRegister
/*     */   {
/*     */     private final NoteBot.Sound sound;
/*     */     private final byte soundByte;
/*     */     
/*     */     public SoundRegister(NoteBot.Sound soundIn, byte soundByteIn) {
/* 479 */       this.sound = soundIn;
/* 480 */       this.soundByte = soundByteIn;
/*     */     }
/*     */     
/*     */     public NoteBot.Sound getSound() {
/* 484 */       return this.sound;
/*     */     }
/*     */     
/*     */     public byte getSoundByte() {
/* 488 */       return this.soundByte;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 492 */       if (other instanceof SoundRegister) {
/* 493 */         SoundRegister soundRegister = (SoundRegister)other;
/* 494 */         return (soundRegister.getSound() == getSound() && soundRegister.getSoundByte() == getSoundByte());
/*     */       } 
/* 496 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SimpleRegister
/*     */     implements IRegister {
/*     */     private int sound;
/*     */     
/*     */     public SimpleRegister(int soundIn) {
/* 505 */       this.sound = soundIn;
/*     */     }
/*     */     
/*     */     public int getSound() {
/* 509 */       return this.sound;
/*     */     }
/*     */     
/*     */     public void setSound(int sound) {
/* 513 */       this.sound = sound;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SoundEntry {
/*     */     private final BlockPos pos;
/*     */     private final NoteBot.SoundRegister register;
/*     */     
/*     */     public SoundEntry(BlockPos posIn, NoteBot.SoundRegister soundRegisterIn) {
/* 522 */       this.pos = posIn;
/* 523 */       this.register = soundRegisterIn;
/*     */     }
/*     */     
/*     */     public BlockPos getPos() {
/* 527 */       return this.pos;
/*     */     }
/*     */     
/*     */     public NoteBot.SoundRegister getRegister() {
/* 531 */       return this.register;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\NoteBot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */