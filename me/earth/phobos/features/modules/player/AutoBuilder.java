/*     */ package me.earth.phobos.features.modules.player;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.earth.phobos.event.events.Render3DEvent;
/*     */ import me.earth.phobos.event.events.UpdateWalkingPlayerEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.BlockUtil;
/*     */ import me.earth.phobos.util.InventoryUtil;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.block.BlockObsidian;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class AutoBuilder extends Module {
/*  25 */   private final Setting<Settings> settings = register(new Setting("Settings", Settings.PATTERN));
/*     */   
/*  27 */   private final Setting<Mode> mode = register(new Setting("Mode", Mode.STAIRS, v -> (this.settings.getValue() == Settings.PATTERN)));
/*  28 */   private final Setting<Direction> stairDirection = register(new Setting("Direction", Direction.NORTH, v -> (this.mode.getValue() == Mode.STAIRS && this.settings.getValue() == Settings.PATTERN)));
/*  29 */   private final Setting<Integer> width = register(new Setting("StairWidth", Integer.valueOf(40), Integer.valueOf(1), Integer.valueOf(100), v -> (this.mode.getValue() == Mode.STAIRS && this.settings.getValue() == Settings.PATTERN)));
/*  30 */   private final Setting<Boolean> setPos = register(new Setting("ResetPos", Boolean.valueOf(false), v -> (this.settings.getValue() == Settings.PATTERN && (this.mode.getValue() == Mode.STAIRS || (this.mode.getValue() == Mode.FLAT && !((Boolean)this.dynamic.getValue()).booleanValue())))));
/*  31 */   private final Setting<Boolean> dynamic = register(new Setting("Dynamic", Boolean.valueOf(true), v -> (this.mode.getValue() == Mode.FLAT && this.settings.getValue() == Settings.PATTERN)));
/*     */   
/*  33 */   private final Setting<Float> range = register(new Setting("Range", Float.valueOf(4.0F), Float.valueOf(1.0F), Float.valueOf(6.0F), v -> (this.settings.getValue() == Settings.PLACE)));
/*  34 */   private final Setting<Integer> blocksPerTick = register(new Setting("Blocks/Tick", Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(8), v -> (this.settings.getValue() == Settings.PLACE)));
/*  35 */   private final Setting<Integer> placeDelay = register(new Setting("PlaceDelay", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(500), v -> (this.settings.getValue() == Settings.PLACE)));
/*  36 */   private final Setting<Boolean> rotate = register(new Setting("Rotate", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.PLACE)));
/*  37 */   private final Setting<Boolean> altRotate = register(new Setting("AltRotate", Boolean.valueOf(false), v -> (((Boolean)this.rotate.getValue()).booleanValue() && this.settings.getValue() == Settings.PLACE)));
/*  38 */   private final Setting<Boolean> ground = register(new Setting("NoJump", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.PLACE)));
/*  39 */   private final Setting<Boolean> noMove = register(new Setting("NoMove", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.PLACE)));
/*  40 */   private final Setting<Boolean> packet = register(new Setting("Packet", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.PLACE)));
/*     */   
/*  42 */   private final Setting<Boolean> render = register(new Setting("Render", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.RENDER)));
/*  43 */   private final Setting<Boolean> colorSync = register(new Setting("CSync", Boolean.valueOf(false), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue())));
/*  44 */   private final Setting<Boolean> box = register(new Setting("Box", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue())));
/*  45 */   private final Setting<Integer> bRed = register(new Setting("BoxRed", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.box.getValue()).booleanValue())));
/*  46 */   private final Setting<Integer> bGreen = register(new Setting("BoxGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.box.getValue()).booleanValue())));
/*  47 */   private final Setting<Integer> bBlue = register(new Setting("BoxBlue", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.box.getValue()).booleanValue())));
/*  48 */   private final Setting<Integer> bAlpha = register(new Setting("BoxAlpha", Integer.valueOf(40), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.box.getValue()).booleanValue())));
/*  49 */   private final Setting<Boolean> outline = register(new Setting("Outline", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue())));
/*  50 */   private final Setting<Integer> oRed = register(new Setting("OutlineRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.outline.getValue()).booleanValue())));
/*  51 */   private final Setting<Integer> oGreen = register(new Setting("OutlineGreen", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.outline.getValue()).booleanValue())));
/*  52 */   private final Setting<Integer> oBlue = register(new Setting("OutlineBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.outline.getValue()).booleanValue())));
/*  53 */   private final Setting<Integer> oAlpha = register(new Setting("OutlineAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.outline.getValue()).booleanValue())));
/*  54 */   private final Setting<Float> lineWidth = register(new Setting("LineWidth", Float.valueOf(1.5F), Float.valueOf(0.1F), Float.valueOf(5.0F), v -> (this.settings.getValue() == Settings.RENDER && ((Boolean)this.render.getValue()).booleanValue() && ((Boolean)this.outline.getValue()).booleanValue())));
/*     */   
/*  56 */   private final Setting<Boolean> keepPos = register(new Setting("KeepOldPos", Boolean.valueOf(false), v -> (this.settings.getValue() == Settings.MISC)));
/*  57 */   private final Setting<Updates> updates = register(new Setting("Update", Updates.TICK, v -> (this.settings.getValue() == Settings.MISC)));
/*  58 */   private final Setting<Switch> switchMode = register(new Setting("Switch", Switch.SILENT, v -> (this.settings.getValue() == Settings.MISC)));
/*  59 */   private final Setting<Boolean> allBlocks = register(new Setting("AllBlocks", Boolean.valueOf(true), v -> (this.settings.getValue() == Settings.MISC)));
/*     */   private BlockPos startPos;
/*  61 */   private final Timer timer = new Timer();
/*  62 */   private final List<BlockPos> placepositions = new ArrayList<>();
/*     */   
/*     */   private int blocksThisTick;
/*     */   private int lastSlot;
/*     */   private int blockSlot;
/*     */   
/*     */   public AutoBuilder() {
/*  69 */     super("AutoBuilder", "Auto Builds", Module.Category.PLAYER, true, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onTick() {
/*  75 */     if (this.updates.getValue() == Updates.TICK) {
/*  76 */       doAutoBuilder();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  82 */     if (this.updates.getValue() == Updates.UPDATE) {
/*  83 */       doAutoBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
/*  89 */     if (this.updates.getValue() == Updates.WALKING && event.getStage() != 1) {
/*  90 */       doAutoBuilder();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRender3D(Render3DEvent event) {
/*  96 */     if (this.placepositions == null || !((Boolean)this.render.getValue()).booleanValue())
/*  97 */       return;  Color outline = new Color(((Integer)this.oRed.getValue()).intValue(), ((Integer)this.oGreen.getValue()).intValue(), ((Integer)this.oBlue.getValue()).intValue(), ((Integer)this.oAlpha.getValue()).intValue());
/*  98 */     Color box = new Color(((Integer)this.bRed.getValue()).intValue(), ((Integer)this.bGreen.getValue()).intValue(), ((Integer)this.bBlue.getValue()).intValue(), ((Integer)this.bAlpha.getValue()).intValue());
/*  99 */     this.placepositions.forEach(pos -> RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(new AxisAlignedBB(pos), box, outline, ((Float)this.lineWidth.getValue()).floatValue(), ((Boolean)this.outline.getValue()).booleanValue(), ((Boolean)this.box.getValue()).booleanValue(), ((Boolean)this.colorSync.getValue()).booleanValue(), 1.0F, 1.0F, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 118 */     this.placepositions.clear();
/* 119 */     if (!((Boolean)this.keepPos.getValue()).booleanValue() || this.startPos == null)
/* 120 */       this.startPos = (new BlockPos(mc.field_71439_g.field_70165_t, Math.ceil(mc.field_71439_g.field_70163_u), mc.field_71439_g.field_70161_v)).func_177977_b(); 
/* 121 */     this.blocksThisTick = 0;
/* 122 */     this.lastSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/* 123 */     this.timer.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doAutoBuilder() {
/* 128 */     if (!check())
/* 129 */       return;  for (BlockPos pos : this.placepositions) {
/* 130 */       if (this.blocksThisTick >= ((Integer)this.blocksPerTick.getValue()).intValue()) {
/* 131 */         doSwitch(true);
/*     */         return;
/*     */       } 
/* 134 */       int canPlace = BlockUtil.isPositionPlaceable(pos, false, true);
/* 135 */       if (canPlace == 3) {
/* 136 */         BlockUtil.placeBlockNotRetarded(pos, EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 137 */         this.blocksThisTick++; continue;
/* 138 */       }  if (canPlace == 2 && this.mode.getValue() == Mode.STAIRS) {
/* 139 */         if (BlockUtil.isPositionPlaceable(pos.func_177977_b(), false, true) == 3) {
/* 140 */           BlockUtil.placeBlockNotRetarded(pos.func_177977_b(), EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 141 */           this.blocksThisTick++; continue;
/*     */         } 
/* 143 */         switch ((Direction)this.stairDirection.getValue()) {
/*     */           case NONE:
/* 145 */             if (BlockUtil.isPositionPlaceable(pos.func_177968_d(), false, true) == 3) {
/* 146 */               BlockUtil.placeBlockNotRetarded(pos.func_177968_d(), EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 147 */               this.blocksThisTick++;
/*     */             } 
/*     */           
/*     */           case NORMAL:
/* 151 */             if (BlockUtil.isPositionPlaceable(pos.func_177976_e(), false, true) == 3) {
/* 152 */               BlockUtil.placeBlockNotRetarded(pos.func_177976_e(), EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 153 */               this.blocksThisTick++;
/*     */             } 
/*     */           
/*     */           case SILENT:
/* 157 */             if (BlockUtil.isPositionPlaceable(pos.func_177978_c(), false, true) == 3) {
/* 158 */               BlockUtil.placeBlockNotRetarded(pos.func_177978_c(), EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 159 */               this.blocksThisTick++;
/*     */             } 
/*     */           
/*     */           case null:
/* 163 */             if (BlockUtil.isPositionPlaceable(pos.func_177974_f(), false, true) == 3) {
/* 164 */               BlockUtil.placeBlockNotRetarded(pos.func_177974_f(), EnumHand.MAIN_HAND, ((Boolean)this.rotate.getValue()).booleanValue(), ((Boolean)this.packet.getValue()).booleanValue(), ((Boolean)this.altRotate.getValue()).booleanValue());
/* 165 */               this.blocksThisTick++;
/*     */             } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 170 */     doSwitch(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean doSwitch(boolean back) {
/* 175 */     Item i = mc.field_71439_g.func_184614_ca().func_77973_b();
/* 176 */     switch ((Switch)this.switchMode.getValue()) {
/*     */       case NONE:
/* 178 */         if (i instanceof ItemBlock) {
/* 179 */           if (((Boolean)this.allBlocks.getValue()).booleanValue()) return true; 
/* 180 */           return ((ItemBlock)i).func_179223_d() instanceof BlockObsidian;
/*     */         } 
/* 182 */         return false;
/*     */       case NORMAL:
/* 184 */         if (!back)
/* 185 */           InventoryUtil.switchToHotbarSlot(this.blockSlot, false); 
/*     */         break;
/*     */       case SILENT:
/* 188 */         if (i instanceof ItemBlock && ((
/* 189 */           (Boolean)this.allBlocks.getValue()).booleanValue() || (
/* 190 */           (ItemBlock)i).func_179223_d() instanceof BlockObsidian))
/*     */           break; 
/* 192 */         if (this.lastSlot == -1)
/* 193 */           break;  if (back) { mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.lastSlot)); break; }
/* 194 */          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(this.blockSlot)); break;
/*     */     } 
/* 196 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean check() {
/* 201 */     if (((Boolean)this.setPos.getValue()).booleanValue()) {
/* 202 */       this.startPos = (new BlockPos(mc.field_71439_g.field_70165_t, Math.ceil(mc.field_71439_g.field_70163_u), mc.field_71439_g.field_70161_v)).func_177977_b();
/* 203 */       this.setPos.setValue(Boolean.valueOf(false));
/*     */     } 
/* 205 */     getPositions();
/* 206 */     if (this.placepositions.isEmpty()) return false; 
/* 207 */     if (!this.timer.passedMs(((Integer)this.placeDelay.getValue()).intValue())) return false; 
/* 208 */     this.timer.reset();
/* 209 */     this.blocksThisTick = 0;
/* 210 */     this.lastSlot = mc.field_71439_g.field_71071_by.field_70461_c;
/* 211 */     this.blockSlot = ((Boolean)this.allBlocks.getValue()).booleanValue() ? InventoryUtil.findAnyBlock() : InventoryUtil.findHotbarBlock(BlockObsidian.class);
/* 212 */     if (((Boolean)this.ground.getValue()).booleanValue() && !mc.field_71439_g.field_70122_E) return false; 
/* 213 */     if (this.blockSlot == -1) return false; 
/* 214 */     if (((Boolean)this.noMove.getValue()).booleanValue() && (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F)) return false; 
/* 215 */     return doSwitch(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void getPositions() {
/* 220 */     if (this.startPos == null)
/* 221 */       return;  this.placepositions.clear();
/* 222 */     for (BlockPos pos : BlockUtil.getSphere((new BlockPos(mc.field_71439_g.field_70165_t, Math.ceil(mc.field_71439_g.field_70163_u), mc.field_71439_g.field_70161_v)).func_177984_a(), ((Float)this.range.getValue()).floatValue(), ((Float)this.range.getValue()).intValue(), false, true, 0)) {
/* 223 */       if (this.placepositions.contains(pos) || 
/* 224 */         !mc.field_71441_e.func_175623_d(pos))
/* 225 */         continue;  if (this.mode.getValue() == Mode.STAIRS) {
/* 226 */         switch ((Direction)this.stairDirection.getValue()) {
/*     */           case SILENT:
/* 228 */             if (this.startPos.func_177952_p() - pos.func_177952_p() == pos.func_177956_o() - this.startPos.func_177956_o() && Math.abs(pos.func_177958_n() - this.startPos.func_177958_n()) < ((Integer)this.width.getValue()).intValue() / 2) {
/* 229 */               this.placepositions.add(pos);
/*     */             }
/*     */             continue;
/*     */           case null:
/* 233 */             if (pos.func_177958_n() - this.startPos.func_177958_n() == pos.func_177956_o() - this.startPos.func_177956_o() && Math.abs(pos.func_177952_p() - this.startPos.func_177952_p()) < ((Integer)this.width.getValue()).intValue() / 2) {
/* 234 */               this.placepositions.add(pos);
/*     */             }
/*     */             continue;
/*     */           case NONE:
/* 238 */             if (pos.func_177952_p() - this.startPos.func_177952_p() == pos.func_177956_o() - this.startPos.func_177956_o() && Math.abs(this.startPos.func_177958_n() - pos.func_177958_n()) < ((Integer)this.width.getValue()).intValue() / 2) {
/* 239 */               this.placepositions.add(pos);
/*     */             }
/*     */             continue;
/*     */           case NORMAL:
/* 243 */             if (this.startPos.func_177958_n() - pos.func_177958_n() == pos.func_177956_o() - this.startPos.func_177956_o() && Math.abs(this.startPos.func_177952_p() - pos.func_177952_p()) < ((Integer)this.width.getValue()).intValue() / 2)
/* 244 */               this.placepositions.add(pos);  continue;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 248 */       if (this.mode.getValue() != Mode.FLAT || 
/* 249 */         pos.func_177956_o() != (((Boolean)this.dynamic.getValue()).booleanValue() ? (Math.ceil(mc.field_71439_g.field_70163_u) - 1.0D) : this.startPos.func_177956_o()))
/* 250 */         continue;  this.placepositions.add(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Mode
/*     */   {
/* 257 */     STAIRS,
/* 258 */     FLAT;
/*     */   }
/*     */   
/*     */   public enum Switch
/*     */   {
/* 263 */     NONE,
/* 264 */     NORMAL,
/* 265 */     SILENT;
/*     */   }
/*     */   
/*     */   public enum Updates
/*     */   {
/* 270 */     TICK,
/* 271 */     UPDATE,
/* 272 */     WALKING;
/*     */   }
/*     */   
/*     */   public enum Direction
/*     */   {
/* 277 */     WEST,
/* 278 */     SOUTH,
/* 279 */     EAST,
/* 280 */     NORTH;
/*     */   }
/*     */   
/*     */   public enum Settings
/*     */   {
/* 285 */     MISC,
/* 286 */     PATTERN,
/* 287 */     PLACE,
/* 288 */     RENDER;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\player\AutoBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */