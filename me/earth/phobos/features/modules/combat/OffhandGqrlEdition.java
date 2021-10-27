/*     */ package me.earth.phobos.features.modules.combat;
/*     */ 
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.event.events.ProcessRightClickBlockEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.EntityUtil;
/*     */ import me.earth.phobos.util.InventoryUtil;
/*     */ import me.earth.phobos.util.Timer;
/*     */ import net.minecraft.block.BlockObsidian;
/*     */ import net.minecraft.block.BlockWeb;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
/*     */ import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class OffhandGqrlEdition
/*     */   extends Module
/*     */ {
/*     */   private static OffhandGqrlEdition instance;
/*  32 */   private final Queue<InventoryUtil.Task> taskList = new ConcurrentLinkedQueue<>();
/*  33 */   private final Timer timer = new Timer();
/*  34 */   private final Timer secondTimer = new Timer();
/*  35 */   public Setting<Boolean> crystal = register(new Setting("Crystal", Boolean.valueOf(true)));
/*  36 */   public Setting<Float> crystalHealth = register(new Setting("CrystalHP", Float.valueOf(13.0F), Float.valueOf(0.1F), Float.valueOf(36.0F)));
/*  37 */   public Setting<Float> crystalHoleHealth = register(new Setting("CrystalHoleHP", Float.valueOf(3.5F), Float.valueOf(0.1F), Float.valueOf(36.0F)));
/*  38 */   public Setting<Boolean> gapple = register(new Setting("Gapple", Boolean.valueOf(true)));
/*  39 */   public Setting<Boolean> armorCheck = register(new Setting("ArmorCheck", Boolean.valueOf(true)));
/*  40 */   public Setting<Integer> actions = register(new Setting("Actions", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(4)));
/*  41 */   public Mode2 currentMode = Mode2.TOTEMS;
/*     */   public int totems;
/*     */   public int crystals;
/*     */   public int gapples;
/*  45 */   public int lastTotemSlot = -1;
/*  46 */   public int lastGappleSlot = -1;
/*  47 */   public int lastCrystalSlot = -1;
/*  48 */   public int lastObbySlot = -1;
/*  49 */   public int lastWebSlot = -1;
/*     */   
/*     */   public boolean holdingCrystal;
/*     */   public boolean holdingTotem;
/*     */   public boolean holdingGapple;
/*     */   public boolean didSwitchThisTick;
/*     */   private boolean second;
/*     */   private boolean switchedForHealthReason;
/*     */   
/*     */   public OffhandGqrlEdition() {
/*  59 */     super("Offhand+", "Better Offhand.", Module.Category.COMBAT, true, false, false);
/*  60 */     instance = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public static OffhandGqrlEdition getInstance() {
/*  65 */     if (instance == null) {
/*  66 */       instance = new OffhandGqrlEdition();
/*     */     }
/*  68 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUpdateWalkingPlayer(ProcessRightClickBlockEvent event) {
/*  74 */     if (event.hand == EnumHand.MAIN_HAND && event.stack.func_77973_b() == Items.field_185158_cP && mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao && mc.field_71476_x != null && event.pos == mc.field_71476_x.func_178782_a()) {
/*  75 */       event.setCanceled(true);
/*  76 */       mc.field_71439_g.func_184598_c(EnumHand.OFF_HAND);
/*  77 */       mc.field_71442_b.func_187101_a((EntityPlayer)mc.field_71439_g, (World)mc.field_71441_e, EnumHand.OFF_HAND);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  84 */     if (this.timer.passedMs(50L)) {
/*  85 */       if (mc.field_71439_g != null && mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP && Mouse.isButtonDown(1)) {
/*  86 */         mc.field_71439_g.func_184598_c(EnumHand.OFF_HAND);
/*  87 */         mc.field_71474_y.field_74313_G.field_74513_e = Mouse.isButtonDown(1);
/*     */       } 
/*  89 */     } else if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP) {
/*  90 */       mc.field_71474_y.field_74313_G.field_74513_e = false;
/*     */     } 
/*  92 */     if (nullCheck()) {
/*     */       return;
/*     */     }
/*  95 */     doOffhand();
/*  96 */     if (this.secondTimer.passedMs(50L) && this.second) {
/*  97 */       this.second = false;
/*  98 */       this.timer.reset();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketSend(PacketEvent.Send event) {
/* 105 */     if (!fullNullCheck() && mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP && mc.field_71474_y.field_74313_G.func_151470_d()) {
/* 106 */       if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
/* 107 */         CPacketPlayerTryUseItemOnBlock packet2 = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
/* 108 */         if (packet2.func_187022_c() == EnumHand.MAIN_HAND) {
/* 109 */           if (this.timer.passedMs(50L)) {
/* 110 */             mc.field_71439_g.func_184598_c(EnumHand.OFF_HAND);
/* 111 */             mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(EnumHand.OFF_HAND));
/*     */           } 
/* 113 */           event.setCanceled(true);
/*     */         } 
/* 115 */       } else if (event.getPacket() instanceof CPacketPlayerTryUseItem && ((CPacketPlayerTryUseItem)event.getPacket()).func_187028_a() == EnumHand.OFF_HAND && !this.timer.passedMs(50L)) {
/* 116 */         event.setCanceled(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayInfo() {
/* 124 */     if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP) {
/* 125 */       return "Crystals";
/*     */     }
/* 127 */     if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190929_cY) {
/* 128 */       return "Totems";
/*     */     }
/* 130 */     if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao) {
/* 131 */       return "Gapples";
/*     */     }
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doOffhand() {
/* 138 */     this.didSwitchThisTick = false;
/* 139 */     this.holdingCrystal = (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP);
/* 140 */     this.holdingTotem = (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190929_cY);
/* 141 */     this.holdingGapple = (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151153_ao);
/* 142 */     this.totems = mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_190929_cY)).mapToInt(ItemStack::func_190916_E).sum();
/* 143 */     if (this.holdingTotem) {
/* 144 */       this.totems += mc.field_71439_g.field_71071_by.field_184439_c.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_190929_cY)).mapToInt(ItemStack::func_190916_E).sum();
/*     */     }
/* 146 */     this.crystals = mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_185158_cP)).mapToInt(ItemStack::func_190916_E).sum();
/* 147 */     if (this.holdingCrystal) {
/* 148 */       this.crystals += mc.field_71439_g.field_71071_by.field_184439_c.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_185158_cP)).mapToInt(ItemStack::func_190916_E).sum();
/*     */     }
/* 150 */     this.gapples = mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_151153_ao)).mapToInt(ItemStack::func_190916_E).sum();
/* 151 */     if (this.holdingGapple) {
/* 152 */       this.gapples += mc.field_71439_g.field_71071_by.field_184439_c.stream().filter(itemStack -> (itemStack.func_77973_b() == Items.field_151153_ao)).mapToInt(ItemStack::func_190916_E).sum();
/*     */     }
/* 154 */     doSwitch();
/*     */   }
/*     */   
/*     */   public void doSwitch() {
/*     */     int lastSlot;
/* 159 */     this.currentMode = Mode2.TOTEMS;
/* 160 */     if (((Boolean)this.gapple.getValue()).booleanValue() && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword && mc.field_71474_y.field_74313_G.func_151470_d()) {
/* 161 */       this.currentMode = Mode2.GAPPLES;
/* 162 */     } else if (this.currentMode != Mode2.CRYSTALS && ((Boolean)this.crystal.getValue()).booleanValue() && ((EntityUtil.isSafe((Entity)mc.field_71439_g) && EntityUtil.getHealth((Entity)mc.field_71439_g, true) > ((Float)this.crystalHoleHealth.getValue()).floatValue()) || EntityUtil.getHealth((Entity)mc.field_71439_g, true) > ((Float)this.crystalHealth.getValue()).floatValue())) {
/* 163 */       this.currentMode = Mode2.CRYSTALS;
/*     */     } 
/* 165 */     if (this.currentMode == Mode2.CRYSTALS && this.crystals == 0) {
/* 166 */       setMode(Mode2.TOTEMS);
/*     */     }
/* 168 */     if (this.currentMode == Mode2.CRYSTALS && ((!EntityUtil.isSafe((Entity)mc.field_71439_g) && EntityUtil.getHealth((Entity)mc.field_71439_g, true) <= ((Float)this.crystalHealth.getValue()).floatValue()) || EntityUtil.getHealth((Entity)mc.field_71439_g, true) <= ((Float)this.crystalHoleHealth.getValue()).floatValue())) {
/* 169 */       if (this.currentMode == Mode2.CRYSTALS) {
/* 170 */         this.switchedForHealthReason = true;
/*     */       }
/* 172 */       setMode(Mode2.TOTEMS);
/*     */     } 
/* 174 */     if (this.switchedForHealthReason && ((EntityUtil.isSafe((Entity)mc.field_71439_g) && EntityUtil.getHealth((Entity)mc.field_71439_g, true) > ((Float)this.crystalHoleHealth.getValue()).floatValue()) || EntityUtil.getHealth((Entity)mc.field_71439_g, true) > ((Float)this.crystalHealth.getValue()).floatValue())) {
/* 175 */       setMode(Mode2.CRYSTALS);
/* 176 */       this.switchedForHealthReason = false;
/*     */     } 
/* 178 */     if (this.currentMode == Mode2.CRYSTALS && ((Boolean)this.armorCheck.getValue()).booleanValue() && (mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() == Items.field_190931_a || mc.field_71439_g.func_184582_a(EntityEquipmentSlot.HEAD).func_77973_b() == Items.field_190931_a || mc.field_71439_g.func_184582_a(EntityEquipmentSlot.LEGS).func_77973_b() == Items.field_190931_a || mc.field_71439_g.func_184582_a(EntityEquipmentSlot.FEET).func_77973_b() == Items.field_190931_a)) {
/* 179 */       setMode(Mode2.TOTEMS);
/*     */     }
/* 181 */     if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer && !(mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
/*     */       return;
/*     */     }
/* 184 */     Item currentOffhandGqrlEditionItem = mc.field_71439_g.func_184592_cb().func_77973_b();
/* 185 */     switch (this.currentMode) {
/*     */       case TOTEMS:
/* 187 */         if (this.totems <= 0 || this.holdingTotem)
/* 188 */           break;  this.lastTotemSlot = InventoryUtil.findItemInventorySlot(Items.field_190929_cY, false);
/* 189 */         lastSlot = getLastSlot(currentOffhandGqrlEditionItem, this.lastTotemSlot);
/* 190 */         putItemInOffhandGqrlEdition(this.lastTotemSlot, lastSlot);
/*     */         break;
/*     */       
/*     */       case GAPPLES:
/* 194 */         if (this.gapples <= 0 || this.holdingGapple)
/* 195 */           break;  this.lastGappleSlot = InventoryUtil.findItemInventorySlot(Items.field_151153_ao, false);
/* 196 */         lastSlot = getLastSlot(currentOffhandGqrlEditionItem, this.lastGappleSlot);
/* 197 */         putItemInOffhandGqrlEdition(this.lastGappleSlot, lastSlot);
/*     */         break;
/*     */       
/*     */       default:
/* 201 */         if (this.crystals <= 0 || this.holdingCrystal)
/* 202 */           break;  this.lastCrystalSlot = InventoryUtil.findItemInventorySlot(Items.field_185158_cP, false);
/* 203 */         lastSlot = getLastSlot(currentOffhandGqrlEditionItem, this.lastCrystalSlot);
/* 204 */         putItemInOffhandGqrlEdition(this.lastCrystalSlot, lastSlot);
/*     */         break;
/*     */     } 
/* 207 */     for (int i = 0; i < ((Integer)this.actions.getValue()).intValue(); i++) {
/* 208 */       InventoryUtil.Task task = this.taskList.poll();
/* 209 */       if (task != null) {
/* 210 */         task.run();
/* 211 */         if (task.isSwitching())
/* 212 */           this.didSwitchThisTick = true; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getLastSlot(Item item, int slotIn) {
/* 218 */     if (item == Items.field_185158_cP) {
/* 219 */       return this.lastCrystalSlot;
/*     */     }
/* 221 */     if (item == Items.field_151153_ao) {
/* 222 */       return this.lastGappleSlot;
/*     */     }
/* 224 */     if (item == Items.field_190929_cY) {
/* 225 */       return this.lastTotemSlot;
/*     */     }
/* 227 */     if (InventoryUtil.isBlock(item, BlockObsidian.class)) {
/* 228 */       return this.lastObbySlot;
/*     */     }
/* 230 */     if (InventoryUtil.isBlock(item, BlockWeb.class)) {
/* 231 */       return this.lastWebSlot;
/*     */     }
/* 233 */     if (item == Items.field_190931_a) {
/* 234 */       return -1;
/*     */     }
/* 236 */     return slotIn;
/*     */   }
/*     */ 
/*     */   
/*     */   private void putItemInOffhandGqrlEdition(int slotIn, int slotOut) {
/* 241 */     if (slotIn != -1 && this.taskList.isEmpty()) {
/* 242 */       this.taskList.add(new InventoryUtil.Task(slotIn));
/* 243 */       this.taskList.add(new InventoryUtil.Task(45));
/* 244 */       this.taskList.add(new InventoryUtil.Task(slotOut));
/* 245 */       this.taskList.add(new InventoryUtil.Task());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMode(Mode2 mode) {
/* 251 */     this.currentMode = (this.currentMode == mode) ? Mode2.TOTEMS : mode;
/*     */   }
/*     */   
/*     */   public enum Mode2
/*     */   {
/* 256 */     TOTEMS,
/* 257 */     GAPPLES,
/* 258 */     CRYSTALS;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\combat\OffhandGqrlEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */