/*     */ package me.earth.phobos.features.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.event.events.Render3DEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.modules.client.Colors;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import me.earth.phobos.util.DamageUtil;
/*     */ import me.earth.phobos.util.EntityUtil;
/*     */ import me.earth.phobos.util.RotationUtil;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentProtection;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestNametags
/*     */   extends Module
/*     */ {
/*  34 */   private static TestNametags INSTANCE = new TestNametags();
/*  35 */   private final Setting<Boolean> health = register(new Setting("Health", Boolean.valueOf(true)));
/*  36 */   private final Setting<Boolean> armor = register(new Setting("Armor", Boolean.valueOf(true)));
/*  37 */   private final Setting<Mode> mode = register(new Setting("Mode", Mode.MINIMAL));
/*  38 */   private final Setting<Float> scaling = register(new Setting("Size", Float.valueOf(0.3F), Float.valueOf(0.1F), Float.valueOf(20.0F)));
/*  39 */   private final Setting<Boolean> invisibles = register(new Setting("Invisibles", Boolean.valueOf(false)));
/*  40 */   private final Setting<Boolean> ping = register(new Setting("Ping", Boolean.valueOf(true)));
/*  41 */   private final Setting<Boolean> totemPops = register(new Setting("TotemPops", Boolean.valueOf(true)));
/*  42 */   private final Setting<Boolean> gamemode = register(new Setting("Gamemode", Boolean.valueOf(false)));
/*  43 */   private final Setting<Boolean> entityID = register(new Setting("ID", Boolean.valueOf(false)));
/*  44 */   private final Setting<Boolean> rect = register(new Setting("Rectangle", Boolean.valueOf(true)));
/*  45 */   private final Setting<Boolean> outline = register(new Setting("Outline", Boolean.FALSE, v -> ((Boolean)this.rect.getValue()).booleanValue()));
/*  46 */   private final Setting<Boolean> colorSync = register(new Setting("Sync", Boolean.FALSE, v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  47 */   private final Setting<Integer> redSetting = register(new Setting("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  48 */   private final Setting<Integer> greenSetting = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  49 */   private final Setting<Integer> blueSetting = register(new Setting("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  50 */   private final Setting<Integer> alphaSetting = register(new Setting("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  51 */   private final Setting<Float> lineWidth = register(new Setting("LineWidth", Float.valueOf(1.5F), Float.valueOf(0.1F), Float.valueOf(5.0F), v -> ((Boolean)this.outline.getValue()).booleanValue()));
/*  52 */   private final Setting<Boolean> sneak = register(new Setting("SneakColor", Boolean.valueOf(false)));
/*  53 */   private final Setting<Boolean> heldStackName = register(new Setting("StackName", Boolean.valueOf(false)));
/*  54 */   private final Setting<Boolean> whiter = register(new Setting("White", Boolean.valueOf(false)));
/*  55 */   private final Setting<Boolean> onlyFov = register(new Setting("OnlyFov", Boolean.valueOf(false)));
/*  56 */   private final Setting<Boolean> scaleing = register(new Setting("Scale", Boolean.valueOf(false)));
/*  57 */   private final Setting<Float> factor = register(new Setting("Factor", Float.valueOf(0.3F), Float.valueOf(0.1F), Float.valueOf(1.0F), v -> ((Boolean)this.scaleing.getValue()).booleanValue()));
/*  58 */   private final Setting<Boolean> smartScale = register(new Setting("SmartScale", Boolean.FALSE, v -> ((Boolean)this.scaleing.getValue()).booleanValue()));
/*     */ 
/*     */   
/*     */   public TestNametags() {
/*  62 */     super("TestNametagsFix", "Better TestNametags", Module.Category.RENDER, false, false, false);
/*  63 */     setInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   public static TestNametags getInstance() {
/*  68 */     if (INSTANCE == null) {
/*  69 */       INSTANCE = new TestNametags();
/*     */     }
/*  71 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstance() {
/*  76 */     INSTANCE = this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRender3D(Render3DEvent event) {
/*  82 */     if (!fullNullCheck()) {
/*  83 */       for (EntityPlayer player : mc.field_71441_e.field_73010_i) {
/*  84 */         if (player == null || player.equals(mc.field_71439_g) || !player.func_70089_S() || (player.func_82150_aj() && !((Boolean)this.invisibles.getValue()).booleanValue()) || (((Boolean)this.onlyFov.getValue()).booleanValue() && RotationUtil.isInFov((Entity)player)))
/*     */           continue; 
/*  86 */         double x = interpolate(player.field_70142_S, player.field_70165_t, event.getPartialTicks()) - (mc.func_175598_ae()).field_78725_b;
/*  87 */         double y = interpolate(player.field_70137_T, player.field_70163_u, event.getPartialTicks()) - (mc.func_175598_ae()).field_78726_c;
/*  88 */         double z = interpolate(player.field_70136_U, player.field_70161_v, event.getPartialTicks()) - (mc.func_175598_ae()).field_78723_d;
/*  89 */         renderNameTag(player, x, y, z, event.getPartialTicks());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawRect(float x, float y, float w, float h, int color) {
/*  96 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*  97 */     float red = (color >> 16 & 0xFF) / 255.0F;
/*  98 */     float green = (color >> 8 & 0xFF) / 255.0F;
/*  99 */     float blue = (color & 0xFF) / 255.0F;
/* 100 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 101 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 102 */     GlStateManager.func_179147_l();
/* 103 */     GlStateManager.func_179090_x();
/* 104 */     GlStateManager.func_187441_d(((Float)this.lineWidth.getValue()).floatValue());
/* 105 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 106 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 107 */     bufferbuilder.func_181662_b(x, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 108 */     bufferbuilder.func_181662_b(w, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 109 */     bufferbuilder.func_181662_b(w, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 110 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 111 */     tessellator.func_78381_a();
/* 112 */     GlStateManager.func_179098_w();
/* 113 */     GlStateManager.func_179084_k();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawOutlineRect(float x, float y, float w, float h, int color) {
/* 118 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 119 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 120 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 121 */     float blue = (color & 0xFF) / 255.0F;
/* 122 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 123 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 124 */     GlStateManager.func_179147_l();
/* 125 */     GlStateManager.func_179090_x();
/* 126 */     GlStateManager.func_187441_d(((Float)this.lineWidth.getValue()).floatValue());
/* 127 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 128 */     bufferbuilder.func_181668_a(2, DefaultVertexFormats.field_181706_f);
/* 129 */     bufferbuilder.func_181662_b(x, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 130 */     bufferbuilder.func_181662_b(w, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 131 */     bufferbuilder.func_181662_b(w, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 132 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 133 */     tessellator.func_78381_a();
/* 134 */     GlStateManager.func_179098_w();
/* 135 */     GlStateManager.func_179084_k();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderNameTag(EntityPlayer player, double x, double y, double z, float delta) {
/* 140 */     double tempY = y;
/* 141 */     tempY += player.func_70093_af() ? 0.5D : 0.7D;
/* 142 */     Entity camera = mc.func_175606_aa();
/* 143 */     assert camera != null;
/* 144 */     double originalPositionX = camera.field_70165_t;
/* 145 */     double originalPositionY = camera.field_70163_u;
/* 146 */     double originalPositionZ = camera.field_70161_v;
/* 147 */     camera.field_70165_t = interpolate(camera.field_70169_q, camera.field_70165_t, delta);
/* 148 */     camera.field_70163_u = interpolate(camera.field_70167_r, camera.field_70163_u, delta);
/* 149 */     camera.field_70161_v = interpolate(camera.field_70166_s, camera.field_70161_v, delta);
/* 150 */     String displayTag = getDisplayTag(player);
/* 151 */     double distance = camera.func_70011_f(x + (mc.func_175598_ae()).field_78730_l, y + (mc.func_175598_ae()).field_78731_m, z + (mc.func_175598_ae()).field_78728_n);
/* 152 */     int width = this.renderer.getStringWidth(displayTag) / 2;
/* 153 */     double scale = (0.0018D + ((Float)this.scaling.getValue()).floatValue() * distance * ((Float)this.factor.getValue()).floatValue()) / 1000.0D;
/* 154 */     if (distance <= 8.0D && ((Boolean)this.smartScale.getValue()).booleanValue()) {
/* 155 */       scale = 0.0245D;
/*     */     }
/* 157 */     if (!((Boolean)this.scaleing.getValue()).booleanValue()) {
/* 158 */       scale = ((Float)this.scaling.getValue()).floatValue() / 100.0D;
/*     */     }
/* 160 */     GlStateManager.func_179094_E();
/* 161 */     RenderHelper.func_74519_b();
/* 162 */     GlStateManager.func_179088_q();
/* 163 */     GlStateManager.func_179136_a(1.0F, -1500000.0F);
/* 164 */     GlStateManager.func_179140_f();
/* 165 */     GlStateManager.func_179109_b((float)x, (float)tempY + 1.4F, (float)z);
/* 166 */     GlStateManager.func_179114_b(-(mc.func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 167 */     GlStateManager.func_179114_b((mc.func_175598_ae()).field_78732_j, (mc.field_71474_y.field_74320_O == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
/* 168 */     GlStateManager.func_179139_a(-scale, -scale, scale);
/* 169 */     GlStateManager.func_179097_i();
/* 170 */     GlStateManager.func_179147_l();
/* 171 */     GlStateManager.func_179147_l();
/* 172 */     if (((Boolean)this.rect.getValue()).booleanValue()) {
/* 173 */       drawRect((-width - 2), -(this.renderer.getFontHeight() + 1), width + 2.0F, 1.5F, 1426063360);
/* 174 */       if (((Boolean)this.outline.getValue()).booleanValue()) {
/* 175 */         int color = ((Boolean)this.colorSync.getValue()).booleanValue() ? Colors.INSTANCE.getCurrentColorHex() : (new Color(((Integer)this.redSetting.getValue()).intValue(), ((Integer)this.greenSetting.getValue()).intValue(), ((Integer)this.blueSetting.getValue()).intValue(), ((Integer)this.alphaSetting.getValue()).intValue())).getRGB();
/* 176 */         drawOutlineRect((-width - 2), -(mc.field_71466_p.field_78288_b + 1), width + 2.0F, 1.5F, color);
/*     */       } 
/*     */     } 
/* 179 */     GlStateManager.func_179084_k();
/* 180 */     ItemStack renderMainHand = player.func_184614_ca().func_77946_l();
/* 181 */     if (renderMainHand.func_77962_s() && (renderMainHand.func_77973_b() instanceof net.minecraft.item.ItemTool || renderMainHand.func_77973_b() instanceof net.minecraft.item.ItemArmor)) {
/* 182 */       renderMainHand.field_77994_a = 1;
/*     */     }
/* 184 */     if (((Boolean)this.heldStackName.getValue()).booleanValue() && !renderMainHand.field_190928_g && renderMainHand.func_77973_b() != Items.field_190931_a) {
/* 185 */       String stackName = renderMainHand.func_82833_r();
/* 186 */       int stackNameWidth = this.renderer.getStringWidth(stackName) / 2;
/* 187 */       GL11.glPushMatrix();
/* 188 */       GL11.glScalef(0.75F, 0.75F, 0.0F);
/* 189 */       this.renderer.drawStringWithShadow(stackName, -stackNameWidth, -(getBiggestArmorTag(player) + 20.0F), -1);
/* 190 */       GL11.glScalef(1.5F, 1.5F, 1.0F);
/* 191 */       GL11.glPopMatrix();
/*     */     } 
/* 193 */     if (((Boolean)this.armor.getValue()).booleanValue()) {
/* 194 */       GlStateManager.func_179094_E();
/* 195 */       int xOffset = -8;
/* 196 */       for (ItemStack stack : player.field_71071_by.field_70460_b) {
/* 197 */         if (stack == null)
/* 198 */           continue;  xOffset -= 8;
/*     */       } 
/* 200 */       xOffset -= 8;
/* 201 */       ItemStack renderOffhand = player.func_184592_cb().func_77946_l();
/* 202 */       if (renderOffhand.func_77962_s() && (renderOffhand.func_77973_b() instanceof net.minecraft.item.ItemTool || renderOffhand.func_77973_b() instanceof net.minecraft.item.ItemArmor)) {
/* 203 */         renderOffhand.field_77994_a = 1;
/*     */       }
/* 205 */       renderItemStack(renderOffhand, xOffset);
/* 206 */       xOffset += 16;
/* 207 */       for (ItemStack stack : player.field_71071_by.field_70460_b) {
/* 208 */         if (stack == null)
/* 209 */           continue;  ItemStack armourStack = stack.func_77946_l();
/* 210 */         if (armourStack.func_77962_s() && (armourStack.func_77973_b() instanceof net.minecraft.item.ItemTool || armourStack.func_77973_b() instanceof net.minecraft.item.ItemArmor)) {
/* 211 */           armourStack.field_77994_a = 1;
/*     */         }
/* 213 */         renderItemStack(armourStack, xOffset);
/* 214 */         xOffset += 16;
/*     */       } 
/* 216 */       renderItemStack(renderMainHand, xOffset);
/* 217 */       GlStateManager.func_179121_F();
/*     */     } 
/* 219 */     this.renderer.drawStringWithShadow(displayTag, -width, -(this.renderer.getFontHeight() - 1), getDisplayColour(player));
/* 220 */     camera.field_70165_t = originalPositionX;
/* 221 */     camera.field_70163_u = originalPositionY;
/* 222 */     camera.field_70161_v = originalPositionZ;
/* 223 */     GlStateManager.func_179126_j();
/* 224 */     GlStateManager.func_179084_k();
/* 225 */     GlStateManager.func_179113_r();
/* 226 */     GlStateManager.func_179136_a(1.0F, 1500000.0F);
/* 227 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderItemStack(ItemStack stack, int x) {
/* 232 */     GlStateManager.func_179094_E();
/* 233 */     GlStateManager.func_179132_a(true);
/* 234 */     GlStateManager.func_179086_m(256);
/* 235 */     RenderHelper.func_74519_b();
/* 236 */     (mc.func_175599_af()).field_77023_b = -150.0F;
/* 237 */     GlStateManager.func_179118_c();
/* 238 */     GlStateManager.func_179126_j();
/* 239 */     GlStateManager.func_179129_p();
/* 240 */     mc.func_175599_af().func_180450_b(stack, x, -26);
/* 241 */     mc.func_175599_af().func_175030_a(mc.field_71466_p, stack, x, -26);
/* 242 */     (mc.func_175599_af()).field_77023_b = 0.0F;
/* 243 */     RenderHelper.func_74518_a();
/* 244 */     GlStateManager.func_179089_o();
/* 245 */     GlStateManager.func_179141_d();
/* 246 */     GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
/* 247 */     GlStateManager.func_179097_i();
/* 248 */     if (this.mode.getValue() != Mode.NONE)
/* 249 */       renderEnchantmentText(stack, x); 
/* 250 */     GlStateManager.func_179126_j();
/* 251 */     GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
/* 252 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderEnchantmentText(ItemStack stack, int x) {
/* 257 */     int enchantmentY = -34;
/* 258 */     if (stack.func_77973_b() == Items.field_151153_ao && stack.func_77962_s()) {
/* 259 */       this.renderer.drawStringWithShadow("god", (x * 2), enchantmentY, -3977919);
/* 260 */       enchantmentY -= 8;
/*     */     } 
/* 262 */     NBTTagList enchants = stack.func_77986_q();
/* 263 */     for (int index = 0; index < enchants.func_74745_c(); index++) {
/* 264 */       short id = enchants.func_150305_b(index).func_74765_d("id");
/* 265 */       short level = enchants.func_150305_b(index).func_74765_d("lvl");
/* 266 */       Enchantment enc = Enchantment.func_185262_c(id);
/* 267 */       if (enc == null)
/* 268 */         continue;  if (this.mode.getValue() == Mode.MINIMAL) {
/* 269 */         if (!(enc instanceof EnchantmentProtection))
/*     */           continue; 
/* 271 */         EnchantmentProtection e = (EnchantmentProtection)enc;
/* 272 */         if (e.field_77356_a != EnchantmentProtection.Type.EXPLOSION && e.field_77356_a != EnchantmentProtection.Type.ALL) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 276 */       String encName = enc.func_190936_d() ? (TextFormatting.RED + enc.func_77316_c(level).substring(11).substring(0, 1).toLowerCase()) : enc.func_77316_c(level).substring(0, 1).toLowerCase();
/* 277 */       encName = encName + level;
/* 278 */       this.renderer.drawStringWithShadow(encName, (x * 2), enchantmentY, -1);
/* 279 */       enchantmentY -= 8; continue;
/*     */     } 
/* 281 */     if (DamageUtil.hasDurability(stack)) {
/* 282 */       int percent = DamageUtil.getRoundedDamage(stack);
/* 283 */       String color = (percent >= 60) ? "§a" : ((percent >= 25) ? "§e" : "§c");
/* 284 */       this.renderer.drawStringWithShadow(color + percent + "%", (x * 2), enchantmentY, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getBiggestArmorTag(EntityPlayer player) {
/* 293 */     float enchantmentY = 0.0F;
/* 294 */     boolean arm = false;
/* 295 */     for (ItemStack stack : player.field_71071_by.field_70460_b) {
/* 296 */       float encY = 0.0F;
/* 297 */       if (stack != null) {
/* 298 */         NBTTagList enchants = stack.func_77986_q();
/* 299 */         for (int index = 0; index < enchants.func_74745_c(); index++) {
/* 300 */           short id = enchants.func_150305_b(index).func_74765_d("id");
/* 301 */           Enchantment enc = Enchantment.func_185262_c(id);
/* 302 */           if (enc != null) {
/* 303 */             encY += 8.0F;
/* 304 */             arm = true;
/*     */           } 
/*     */         } 
/* 307 */       }  if (encY <= enchantmentY)
/* 308 */         continue;  enchantmentY = encY;
/*     */     } 
/* 310 */     ItemStack renderMainHand = player.func_184614_ca().func_77946_l();
/* 311 */     if (renderMainHand.func_77962_s()) {
/* 312 */       float encY = 0.0F;
/* 313 */       NBTTagList enchants = renderMainHand.func_77986_q();
/* 314 */       for (int index2 = 0; index2 < enchants.func_74745_c(); index2++) {
/* 315 */         short id = enchants.func_150305_b(index2).func_74765_d("id");
/* 316 */         Enchantment enc2 = Enchantment.func_185262_c(id);
/* 317 */         if (enc2 != null) {
/* 318 */           encY += 8.0F;
/* 319 */           arm = true;
/*     */         } 
/* 321 */       }  if (encY > enchantmentY)
/* 322 */         enchantmentY = encY; 
/*     */     } 
/*     */     ItemStack renderOffHand;
/* 325 */     if ((renderOffHand = player.func_184592_cb().func_77946_l()).func_77962_s()) {
/* 326 */       float encY = 0.0F;
/* 327 */       NBTTagList enchants = renderOffHand.func_77986_q();
/* 328 */       for (int index = 0; index < enchants.func_74745_c(); index++) {
/* 329 */         short id = enchants.func_150305_b(index).func_74765_d("id");
/* 330 */         Enchantment enc = Enchantment.func_185262_c(id);
/* 331 */         if (enc != null) {
/* 332 */           encY += 8.0F;
/* 333 */           arm = true;
/*     */         } 
/* 335 */       }  if (encY > enchantmentY) {
/* 336 */         enchantmentY = encY;
/*     */       }
/*     */     } 
/* 339 */     return (arm ? false : 20) + enchantmentY;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getDisplayTag(EntityPlayer player) {
/* 344 */     String name = player.func_145748_c_().func_150254_d();
/* 345 */     if (name.contains(mc.func_110432_I().func_111285_a())) {
/* 346 */       name = "You";
/*     */     }
/* 348 */     if (!((Boolean)this.health.getValue()).booleanValue()) {
/* 349 */       return name;
/*     */     }
/* 351 */     float health = EntityUtil.getHealth((Entity)player);
/* 352 */     String color = (health > 18.0F) ? "§a" : ((health > 16.0F) ? "§2" : ((health > 12.0F) ? "§e" : ((health > 8.0F) ? "§6" : ((health > 5.0F) ? "§c" : "§4"))));
/* 353 */     String pingStr = "";
/* 354 */     if (((Boolean)this.ping.getValue()).booleanValue()) {
/*     */       try {
/* 356 */         int responseTime = ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_175102_a(player.func_110124_au()).func_178853_c();
/* 357 */         pingStr = pingStr + responseTime + "ms ";
/* 358 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 362 */     String popStr = " ";
/* 363 */     if (((Boolean)this.totemPops.getValue()).booleanValue()) {
/* 364 */       popStr = popStr + Phobos.totemPopManager.getTotemPopString(player);
/*     */     }
/* 366 */     String idString = "";
/* 367 */     if (((Boolean)this.entityID.getValue()).booleanValue()) {
/* 368 */       idString = idString + "ID: " + player.func_145782_y() + " ";
/*     */     }
/* 370 */     String gameModeStr = "";
/* 371 */     if (((Boolean)this.gamemode.getValue()).booleanValue()) {
/* 372 */       gameModeStr = player.func_184812_l_() ? (gameModeStr + "[C] ") : ((player.func_175149_v() || player.func_82150_aj()) ? (gameModeStr + "[I] ") : (gameModeStr + "[S] "));
/*     */     }
/* 374 */     name = (Math.floor(health) == health) ? (name + color + " " + ((health > 0.0F) ? (String)Integer.valueOf((int)Math.floor(health)) : "dead")) : (name + color + " " + ((health > 0.0F) ? (String)Integer.valueOf((int)health) : "dead"));
/* 375 */     return pingStr + idString + gameModeStr + name + popStr;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getDisplayColour(EntityPlayer player) {
/* 380 */     int colour = -5592406;
/* 381 */     if (((Boolean)this.whiter.getValue()).booleanValue()) {
/* 382 */       colour = -1;
/*     */     }
/* 384 */     if (Phobos.friendManager.isFriend(player)) {
/* 385 */       return -11157267;
/*     */     }
/* 387 */     if (player.func_82150_aj()) {
/* 388 */       colour = -1113785;
/* 389 */     } else if (player.func_70093_af() && ((Boolean)this.sneak.getValue()).booleanValue()) {
/* 390 */       colour = -6481515;
/*     */     } 
/* 392 */     return colour;
/*     */   }
/*     */ 
/*     */   
/*     */   private double interpolate(double previous, double current, float delta) {
/* 397 */     return previous + (current - previous) * delta;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 402 */     FULL,
/* 403 */     MINIMAL,
/* 404 */     NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\TestNametags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */