/*     */ package me.earth.phobos.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import me.earth.phobos.Phobos;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.ClickType;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.CPacketHeldItemChange;
/*     */ 
/*     */ public class InventoryUtil
/*     */   implements Util {
/*     */   public static void switchToHotbarSlot(int slot, boolean silent) {
/*  29 */     if (mc.field_71439_g.field_71071_by.field_70461_c == slot || slot < 0) {
/*     */       return;
/*     */     }
/*  32 */     if (silent) {
/*  33 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
/*  34 */       mc.field_71442_b.func_78765_e();
/*     */     } else {
/*  36 */       mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
/*  37 */       mc.field_71439_g.field_71071_by.field_70461_c = slot;
/*  38 */       mc.field_71442_b.func_78765_e();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void switchToHotbarSlot(Class clazz, boolean silent) {
/*  43 */     int slot = findHotbarBlock(clazz);
/*  44 */     if (slot > -1) {
/*  45 */       switchToHotbarSlot(slot, silent);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isNull(ItemStack stack) {
/*  50 */     return (stack == null || stack.func_77973_b() instanceof net.minecraft.item.ItemAir);
/*     */   }
/*     */   
/*     */   public static int findHotbarBlock(Class clazz) {
/*  54 */     for (int i = 0; i < 9; i++) {
/*     */       
/*  56 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
/*  57 */       if (stack != ItemStack.field_190927_a) {
/*  58 */         if (clazz.isInstance(stack.func_77973_b()))
/*  59 */           return i; 
/*     */         Block block;
/*  61 */         if (stack.func_77973_b() instanceof ItemBlock && clazz.isInstance(block = ((ItemBlock)stack.func_77973_b()).func_179223_d()))
/*     */         {
/*  63 */           return i; } 
/*     */       } 
/*  65 */     }  return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int findAnyBlock() {
/*  70 */     for (int i = 0; i < 9; ) {
/*  71 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
/*  72 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof ItemBlock)) {
/*     */         i++; continue;
/*  74 */       }  return i;
/*     */     } 
/*  76 */     return -1;
/*     */   }
/*     */   
/*     */   public static int findHotbarBlock(Block blockIn) {
/*  80 */     for (int i = 0; i < 9; ) {
/*     */       
/*  82 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i); Block block;
/*  83 */       if (stack == ItemStack.field_190927_a || !(stack.func_77973_b() instanceof ItemBlock) || (block = ((ItemBlock)stack.func_77973_b()).func_179223_d()) != blockIn) {
/*     */         i++; continue;
/*  85 */       }  return i;
/*     */     } 
/*  87 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getItemHotbar(Item input) {
/*  91 */     for (int i = 0; i < 9; ) {
/*  92 */       Item item = mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
/*  93 */       if (Item.func_150891_b(item) != Item.func_150891_b(input)) { i++; continue; }
/*  94 */        return i;
/*     */     } 
/*  96 */     return -1;
/*     */   }
/*     */   
/*     */   public static int findStackInventory(Item input) {
/* 100 */     return findStackInventory(input, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int findStackInventory(Item input, boolean withHotbar) {
/* 105 */     int i = withHotbar ? 0 : 9, n = i;
/* 106 */     while (i < 36) {
/* 107 */       Item item = mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
/* 108 */       if (Item.func_150891_b(input) == Item.func_150891_b(item)) {
/* 109 */         return i + ((i < 9) ? 36 : 0);
/*     */       }
/* 111 */       i++;
/*     */     } 
/* 113 */     return -1;
/*     */   }
/*     */   
/*     */   public static int findItemInventorySlot(Item item, boolean offHand) {
/* 117 */     AtomicInteger slot = new AtomicInteger();
/* 118 */     slot.set(-1);
/* 119 */     for (Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
/* 120 */       if (((ItemStack)entry.getValue()).func_77973_b() != item || (((Integer)entry.getKey()).intValue() == 45 && !offHand))
/* 121 */         continue;  slot.set(((Integer)entry.getKey()).intValue());
/* 122 */       return slot.get();
/*     */     } 
/* 124 */     return slot.get();
/*     */   }
/*     */   
/*     */   public static List<Integer> findEmptySlots(boolean withXCarry) {
/* 128 */     ArrayList<Integer> outPut = new ArrayList<>();
/* 129 */     for (Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
/* 130 */       if (!((ItemStack)entry.getValue()).field_190928_g && ((ItemStack)entry.getValue()).func_77973_b() != Items.field_190931_a)
/* 131 */         continue;  outPut.add(entry.getKey());
/*     */     } 
/* 133 */     if (withXCarry)
/* 134 */       for (int i = 1; i < 5; i++) {
/* 135 */         Slot craftingSlot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 136 */         ItemStack craftingStack = craftingSlot.func_75211_c();
/* 137 */         if (craftingStack.func_190926_b() || craftingStack.func_77973_b() == Items.field_190931_a) {
/* 138 */           outPut.add(Integer.valueOf(i));
/*     */         }
/*     */       }  
/* 141 */     return outPut;
/*     */   }
/*     */   
/*     */   public static int findInventoryBlock(Class clazz, boolean offHand) {
/* 145 */     AtomicInteger slot = new AtomicInteger();
/* 146 */     slot.set(-1);
/* 147 */     for (Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
/* 148 */       if (!isBlock(((ItemStack)entry.getValue()).func_77973_b(), clazz) || (((Integer)entry.getKey()).intValue() == 45 && !offHand))
/* 149 */         continue;  slot.set(((Integer)entry.getKey()).intValue());
/* 150 */       return slot.get();
/*     */     } 
/* 152 */     return slot.get();
/*     */   }
/*     */   
/*     */   public static int findInventoryWool(boolean offHand) {
/* 156 */     AtomicInteger slot = new AtomicInteger();
/* 157 */     slot.set(-1);
/* 158 */     for (Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
/* 159 */       if (!(((ItemStack)entry.getValue()).func_77973_b() instanceof ItemBlock))
/* 160 */         continue;  ItemBlock wool = (ItemBlock)((ItemStack)entry.getValue()).func_77973_b();
/* 161 */       if ((wool.func_179223_d()).field_149764_J != Material.field_151580_n || (((Integer)entry.getKey()).intValue() == 45 && !offHand))
/* 162 */         continue;  slot.set(((Integer)entry.getKey()).intValue());
/* 163 */       return slot.get();
/*     */     } 
/* 165 */     return slot.get();
/*     */   }
/*     */   
/*     */   public static int findEmptySlot() {
/* 169 */     AtomicInteger slot = new AtomicInteger();
/* 170 */     slot.set(-1);
/* 171 */     for (Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
/* 172 */       if (!((ItemStack)entry.getValue()).func_190926_b())
/* 173 */         continue;  slot.set(((Integer)entry.getKey()).intValue());
/* 174 */       return slot.get();
/*     */     } 
/* 176 */     return slot.get();
/*     */   }
/*     */   
/*     */   public static boolean isBlock(Item item, Class clazz) {
/* 180 */     if (item instanceof ItemBlock) {
/* 181 */       Block block = ((ItemBlock)item).func_179223_d();
/* 182 */       return clazz.isInstance(block);
/*     */     } 
/* 184 */     return false;
/*     */   }
/*     */   
/*     */   public static void confirmSlot(int slot) {
/* 188 */     mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(slot));
/* 189 */     mc.field_71439_g.field_71071_by.field_70461_c = slot;
/* 190 */     mc.field_71442_b.func_78765_e();
/*     */   }
/*     */   
/*     */   public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
/* 194 */     if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiCrafting) {
/* 195 */       return fuckYou3arthqu4kev2(10, 45);
/*     */     }
/* 197 */     return getInventorySlots(9, 44);
/*     */   }
/*     */   
/*     */   private static Map<Integer, ItemStack> getInventorySlots(int currentI, int last) {
/* 201 */     HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<>();
/* 202 */     for (int current = currentI; current <= last; current++) {
/* 203 */       fullInventorySlots.put(Integer.valueOf(current), mc.field_71439_g.field_71069_bz.func_75138_a().get(current));
/*     */     }
/* 205 */     return fullInventorySlots;
/*     */   }
/*     */   
/*     */   private static Map<Integer, ItemStack> fuckYou3arthqu4kev2(int currentI, int last) {
/* 209 */     HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<>();
/* 210 */     for (int current = currentI; current <= last; current++) {
/* 211 */       fullInventorySlots.put(Integer.valueOf(current), mc.field_71439_g.field_71070_bA.func_75138_a().get(current));
/*     */     }
/* 213 */     return fullInventorySlots;
/*     */   }
/*     */   
/*     */   public static boolean[] switchItem(boolean back, int lastHotbarSlot, boolean switchedItem, Switch mode, Class clazz) {
/* 217 */     boolean[] switchedItemSwitched = { switchedItem, false };
/* 218 */     switch (mode) {
/*     */       case NORMAL:
/* 220 */         if (!back && !switchedItem) {
/* 221 */           switchToHotbarSlot(findHotbarBlock(clazz), false);
/* 222 */           switchedItemSwitched[0] = true;
/* 223 */         } else if (back && switchedItem) {
/* 224 */           switchToHotbarSlot(lastHotbarSlot, false);
/* 225 */           switchedItemSwitched[0] = false;
/*     */         } 
/* 227 */         switchedItemSwitched[1] = true;
/*     */         break;
/*     */       
/*     */       case SILENT:
/* 231 */         if (!back && !switchedItem) {
/* 232 */           switchToHotbarSlot(findHotbarBlock(clazz), true);
/* 233 */           switchedItemSwitched[0] = true;
/* 234 */         } else if (back && switchedItem) {
/* 235 */           switchedItemSwitched[0] = false;
/* 236 */           Phobos.inventoryManager.recoverSilent(lastHotbarSlot);
/*     */         } 
/* 238 */         switchedItemSwitched[1] = true;
/*     */         break;
/*     */       
/*     */       case NONE:
/* 242 */         switchedItemSwitched[1] = (back || mc.field_71439_g.field_71071_by.field_70461_c == findHotbarBlock(clazz));
/*     */         break;
/*     */     } 
/* 245 */     return switchedItemSwitched;
/*     */   }
/*     */   
/*     */   public static boolean[] switchItemToItem(boolean back, int lastHotbarSlot, boolean switchedItem, Switch mode, Item item) {
/* 249 */     boolean[] switchedItemSwitched = { switchedItem, false };
/* 250 */     switch (mode) {
/*     */       case NORMAL:
/* 252 */         if (!back && !switchedItem) {
/* 253 */           switchToHotbarSlot(getItemHotbar(item), false);
/* 254 */           switchedItemSwitched[0] = true;
/* 255 */         } else if (back && switchedItem) {
/* 256 */           switchToHotbarSlot(lastHotbarSlot, false);
/* 257 */           switchedItemSwitched[0] = false;
/*     */         } 
/* 259 */         switchedItemSwitched[1] = true;
/*     */         break;
/*     */       
/*     */       case SILENT:
/* 263 */         if (!back && !switchedItem) {
/* 264 */           switchToHotbarSlot(getItemHotbar(item), true);
/* 265 */           switchedItemSwitched[0] = true;
/* 266 */         } else if (back && switchedItem) {
/* 267 */           switchedItemSwitched[0] = false;
/* 268 */           Phobos.inventoryManager.recoverSilent(lastHotbarSlot);
/*     */         } 
/* 270 */         switchedItemSwitched[1] = true;
/*     */         break;
/*     */       
/*     */       case NONE:
/* 274 */         switchedItemSwitched[1] = (back || mc.field_71439_g.field_71071_by.field_70461_c == getItemHotbar(item));
/*     */         break;
/*     */     } 
/* 277 */     return switchedItemSwitched;
/*     */   }
/*     */   
/*     */   public static boolean holdingItem(Class clazz) {
/* 281 */     boolean result = false;
/* 282 */     ItemStack stack = mc.field_71439_g.func_184614_ca();
/* 283 */     result = isInstanceOf(stack, clazz);
/* 284 */     if (!result) {
/* 285 */       ItemStack offhand = mc.field_71439_g.func_184592_cb();
/* 286 */       result = isInstanceOf(stack, clazz);
/*     */     } 
/* 288 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean isInstanceOf(ItemStack stack, Class clazz) {
/* 292 */     if (stack == null) {
/* 293 */       return false;
/*     */     }
/* 295 */     Item item = stack.func_77973_b();
/* 296 */     if (clazz.isInstance(item)) {
/* 297 */       return true;
/*     */     }
/* 299 */     if (item instanceof ItemBlock) {
/* 300 */       Block block = Block.func_149634_a(item);
/* 301 */       return clazz.isInstance(block);
/*     */     } 
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public static int getEmptyXCarry() {
/* 307 */     for (int i = 1; i < 5; ) {
/* 308 */       Slot craftingSlot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 309 */       ItemStack craftingStack = craftingSlot.func_75211_c();
/* 310 */       if (!craftingStack.func_190926_b() && craftingStack.func_77973_b() != Items.field_190931_a) { i++; continue; }
/* 311 */        return i;
/*     */     } 
/* 313 */     return -1;
/*     */   }
/*     */   
/*     */   public static boolean isSlotEmpty(int i) {
/* 317 */     Slot slot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 318 */     ItemStack stack = slot.func_75211_c();
/* 319 */     return stack.func_190926_b();
/*     */   }
/*     */   
/*     */   public static int convertHotbarToInv(int input) {
/* 323 */     return 36 + input;
/*     */   }
/*     */   
/*     */   public static boolean areStacksCompatible(ItemStack stack1, ItemStack stack2) {
/* 327 */     if (!stack1.func_77973_b().equals(stack2.func_77973_b())) {
/* 328 */       return false;
/*     */     }
/* 330 */     if (stack1.func_77973_b() instanceof ItemBlock && stack2.func_77973_b() instanceof ItemBlock) {
/* 331 */       Block block1 = ((ItemBlock)stack1.func_77973_b()).func_179223_d();
/* 332 */       Block block2 = ((ItemBlock)stack2.func_77973_b()).func_179223_d();
/* 333 */       if (!block1.field_149764_J.equals(block2.field_149764_J)) {
/* 334 */         return false;
/*     */       }
/*     */     } 
/* 337 */     if (!stack1.func_82833_r().equals(stack2.func_82833_r())) {
/* 338 */       return false;
/*     */     }
/* 340 */     return (stack1.func_77952_i() == stack2.func_77952_i());
/*     */   }
/*     */   
/*     */   public static EntityEquipmentSlot getEquipmentFromSlot(int slot) {
/* 344 */     if (slot == 5) {
/* 345 */       return EntityEquipmentSlot.HEAD;
/*     */     }
/* 347 */     if (slot == 6) {
/* 348 */       return EntityEquipmentSlot.CHEST;
/*     */     }
/* 350 */     if (slot == 7) {
/* 351 */       return EntityEquipmentSlot.LEGS;
/*     */     }
/* 353 */     return EntityEquipmentSlot.FEET;
/*     */   }
/*     */   
/*     */   public static int findArmorSlot(EntityEquipmentSlot type, boolean binding) {
/* 357 */     int slot = -1;
/* 358 */     float damage = 0.0F;
/* 359 */     for (int i = 9; i < 45; i++) {
/*     */       
/* 361 */       ItemStack s = (Minecraft.func_71410_x()).field_71439_g.field_71069_bz.func_75139_a(i).func_75211_c();
/* 362 */       if (s.func_77973_b() != Items.field_190931_a && s.func_77973_b() instanceof ItemArmor) {
/* 363 */         ItemArmor armor = (ItemArmor)s.func_77973_b();
/* 364 */         if (armor.field_77881_a == type)
/* 365 */         { float currentDamage = (armor.field_77879_b + EnchantmentHelper.func_77506_a(Enchantments.field_180310_c, s));
/* 366 */           boolean cursed = (binding && EnchantmentHelper.func_190938_b(s)), bl = cursed;
/* 367 */           if (currentDamage > damage && !cursed)
/* 368 */           { damage = currentDamage;
/* 369 */             slot = i; }  } 
/*     */       } 
/* 371 */     }  return slot;
/*     */   }
/*     */   
/*     */   public static int findArmorSlot(EntityEquipmentSlot type, boolean binding, boolean withXCarry) {
/* 375 */     int slot = findArmorSlot(type, binding);
/* 376 */     if (slot == -1 && withXCarry) {
/* 377 */       float damage = 0.0F;
/* 378 */       for (int i = 1; i < 5; i++) {
/*     */         
/* 380 */         Slot craftingSlot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 381 */         ItemStack craftingStack = craftingSlot.func_75211_c();
/* 382 */         if (craftingStack.func_77973_b() != Items.field_190931_a && craftingStack.func_77973_b() instanceof ItemArmor) {
/* 383 */           ItemArmor armor = (ItemArmor)craftingStack.func_77973_b();
/* 384 */           if (armor.field_77881_a == type)
/* 385 */           { float currentDamage = (armor.field_77879_b + EnchantmentHelper.func_77506_a(Enchantments.field_180310_c, craftingStack));
/* 386 */             boolean cursed = (binding && EnchantmentHelper.func_190938_b(craftingStack)), bl = cursed;
/* 387 */             if (currentDamage > damage && !cursed)
/* 388 */             { damage = currentDamage;
/* 389 */               slot = i; }  } 
/*     */         } 
/*     */       } 
/* 392 */     }  return slot;
/*     */   }
/*     */   
/*     */   public static int findItemInventorySlot(Item item, boolean offHand, boolean withXCarry) {
/* 396 */     int slot = findItemInventorySlot(item, offHand);
/* 397 */     if (slot == -1 && withXCarry)
/* 398 */       for (int i = 1; i < 5; i++) {
/*     */         
/* 400 */         Slot craftingSlot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 401 */         ItemStack craftingStack = craftingSlot.func_75211_c(); Item craftingStackItem;
/* 402 */         if (craftingStack.func_77973_b() != Items.field_190931_a && (craftingStackItem = craftingStack.func_77973_b()) == item)
/*     */         {
/* 404 */           slot = i;
/*     */         }
/*     */       }  
/* 407 */     return slot;
/*     */   }
/*     */   
/*     */   public static int findItemInHotbar(Item itemToFind) {
/* 411 */     int slot = -1;
/* 412 */     for (int i = 0; i < 9; i++) {
/* 413 */       ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 414 */       if (stack != ItemStack.field_190927_a) {
/* 415 */         stack.func_77973_b();
/* 416 */         Item item = stack.func_77973_b();
/* 417 */         if (item.equals(itemToFind)) {
/* 418 */           slot = i; break;
/*     */         } 
/*     */       } 
/* 421 */     }  return slot;
/*     */   }
/*     */   
/*     */   public static int findBlockSlotInventory(Class clazz, boolean offHand, boolean withXCarry) {
/* 425 */     int slot = findInventoryBlock(clazz, offHand);
/* 426 */     if (slot == -1 && withXCarry)
/* 427 */       for (int i = 1; i < 5; i++) {
/*     */         
/* 429 */         Slot craftingSlot = mc.field_71439_g.field_71069_bz.field_75151_b.get(i);
/* 430 */         ItemStack craftingStack = craftingSlot.func_75211_c();
/* 431 */         if (craftingStack.func_77973_b() != Items.field_190931_a) {
/* 432 */           Item craftingStackItem = craftingStack.func_77973_b();
/* 433 */           if (clazz.isInstance(craftingStackItem)) {
/* 434 */             slot = i;
/*     */           } else {
/*     */             Block block;
/* 437 */             if (craftingStackItem instanceof ItemBlock && clazz.isInstance(block = ((ItemBlock)craftingStackItem).func_179223_d()))
/*     */             {
/* 439 */               slot = i; } 
/*     */           } 
/*     */         } 
/* 442 */       }   return slot;
/*     */   }
/*     */   
/*     */   public enum Switch {
/* 446 */     NORMAL,
/* 447 */     SILENT,
/* 448 */     NONE;
/*     */   }
/*     */   
/*     */   public static class Task
/*     */   {
/*     */     private final int slot;
/*     */     private final boolean update;
/*     */     private final boolean quickClick;
/*     */     
/*     */     public Task() {
/* 458 */       this.update = true;
/* 459 */       this.slot = -1;
/* 460 */       this.quickClick = false;
/*     */     }
/*     */     
/*     */     public Task(int slot) {
/* 464 */       this.slot = slot;
/* 465 */       this.quickClick = false;
/* 466 */       this.update = false;
/*     */     }
/*     */     
/*     */     public Task(int slot, boolean quickClick) {
/* 470 */       this.slot = slot;
/* 471 */       this.quickClick = quickClick;
/* 472 */       this.update = false;
/*     */     }
/*     */     
/*     */     public void run() {
/* 476 */       if (this.update) {
/* 477 */         Util.mc.field_71442_b.func_78765_e();
/*     */       }
/* 479 */       if (this.slot != -1) {
/* 480 */         Util.mc.field_71442_b.func_187098_a(Util.mc.field_71439_g.field_71069_bz.field_75152_c, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Util.mc.field_71439_g);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isSwitching() {
/* 485 */       return !this.update;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\InventoryUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */