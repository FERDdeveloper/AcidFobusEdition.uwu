/*     */ package me.earth.phobos.features.modules.render;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.gui.BossInfoClient;
/*     */ import net.minecraft.client.gui.GuiBossOverlay;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.GameType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NoRender
/*     */   extends Module
/*     */ {
/*  37 */   private static NoRender INSTANCE = new NoRender();
/*     */   
/*     */   static {
/*  40 */     INSTANCE = new NoRender();
/*     */   }
/*     */   
/*  43 */   public Setting<Boolean> fire = register(new Setting("Fire", Boolean.FALSE, "Removes the portal overlay."));
/*  44 */   public Setting<Boolean> portal = register(new Setting("Portal", Boolean.FALSE, "Removes the portal overlay."));
/*  45 */   public Setting<Boolean> pumpkin = register(new Setting("Pumpkin", Boolean.FALSE, "Removes the pumpkin overlay."));
/*  46 */   public Setting<Boolean> totemPops = register(new Setting("TotemPop", Boolean.FALSE, "Removes the Totem overlay."));
/*  47 */   public Setting<Boolean> items = register(new Setting("Items", Boolean.FALSE, "Removes items on the ground."));
/*  48 */   public Setting<Boolean> nausea = register(new Setting("Nausea", Boolean.FALSE, "Removes Portal Nausea."));
/*  49 */   public Setting<Boolean> hurtcam = register(new Setting("HurtCam", Boolean.FALSE, "Removes shaking after taking damage."));
/*  50 */   public Setting<Boolean> explosions = register(new Setting("Explosions", Boolean.FALSE, "Removes crystal explosions."));
/*  51 */   public Setting<Fog> fog = register(new Setting("Fog", Fog.NONE, "Removes Fog."));
/*  52 */   public Setting<Boolean> noWeather = register(new Setting("Weather", Boolean.FALSE, "AntiWeather"));
/*  53 */   public Setting<Boss> boss = register(new Setting("BossBars", Boss.NONE, "Modifies the bossbars."));
/*  54 */   public Setting<Float> scale = register(new Setting("Scale", Float.valueOf(0.5F), Float.valueOf(0.5F), Float.valueOf(1.0F), v -> (this.boss.getValue() == Boss.MINIMIZE || this.boss.getValue() == Boss.STACK), "Scale of the bars."));
/*  55 */   public Setting<Boolean> bats = register(new Setting("Bats", Boolean.FALSE, "Removes bats."));
/*  56 */   public Setting<NoArmor> noArmor = register(new Setting("NoArmor", NoArmor.NONE, "Doesnt Render Armor on players."));
/*  57 */   public Setting<Skylight> skylight = register(new Setting("Skylight", Skylight.NONE));
/*  58 */   public Setting<Boolean> barriers = register(new Setting("Barriers", Boolean.FALSE, "Barriers"));
/*  59 */   public Setting<Boolean> blocks = register(new Setting("Blocks", Boolean.FALSE, "Blocks"));
/*  60 */   public Setting<Boolean> advancements = register(new Setting("Advancements", Boolean.valueOf(false)));
/*  61 */   public Setting<Boolean> pigmen = register(new Setting("Pigmen", Boolean.valueOf(false)));
/*  62 */   public Setting<Boolean> timeChange = register(new Setting("TimeChange", Boolean.valueOf(false)));
/*  63 */   public Setting<Integer> time = register(new Setting("Time", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(23000), v -> ((Boolean)this.timeChange.getValue()).booleanValue()));
/*     */ 
/*     */   
/*     */   public NoRender() {
/*  67 */     super("NoRender", "Allows you to stop rendering stuff", Module.Category.RENDER, true, false, false);
/*  68 */     setInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   public static NoRender getInstance() {
/*  73 */     if (INSTANCE == null) {
/*  74 */       INSTANCE = new NoRender();
/*     */     }
/*  76 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInstance() {
/*  81 */     INSTANCE = this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  87 */     if (((Boolean)this.items.getValue()).booleanValue()) {
/*  88 */       mc.field_71441_e.field_72996_f.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::func_70106_y);
/*     */     }
/*  90 */     if (((Boolean)this.noWeather.getValue()).booleanValue() && mc.field_71441_e.func_72896_J()) {
/*  91 */       mc.field_71441_e.func_72894_k(0.0F);
/*     */     }
/*  93 */     if (((Boolean)this.timeChange.getValue()).booleanValue()) {
/*  94 */       mc.field_71441_e.func_72877_b(((Integer)this.time.getValue()).intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/* 101 */     if ((event.getPacket() instanceof net.minecraft.network.play.server.SPacketTimeUpdate & ((Boolean)this.timeChange.getValue()).booleanValue()) != 0) {
/* 102 */       event.setCanceled(true);
/*     */     }
/* 104 */     if ((event.getPacket() instanceof net.minecraft.network.play.server.SPacketExplosion & ((Boolean)this.explosions.getValue()).booleanValue()) != 0) {
/* 105 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doVoidFogParticles(int posX, int posY, int posZ) {
/* 111 */     Random random = new Random();
/* 112 */     ItemStack itemstack = mc.field_71439_g.func_184614_ca();
/* 113 */     boolean flag = (!((Boolean)this.barriers.getValue()).booleanValue() || (mc.field_71442_b.func_178889_l() == GameType.CREATIVE && !itemstack.func_190926_b() && itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_180401_cv)));
/* 114 */     BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
/* 115 */     for (int j = 0; j < 667; j++) {
/* 116 */       showBarrierParticles(posX, posY, posZ, 16, random, flag, blockpos$mutableblockpos);
/* 117 */       showBarrierParticles(posX, posY, posZ, 32, random, flag, blockpos$mutableblockpos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void showBarrierParticles(int x, int y, int z, int offset, Random random, boolean holdingBarrier, BlockPos.MutableBlockPos pos) {
/* 123 */     int i = x + mc.field_71441_e.field_73012_v.nextInt(offset) - mc.field_71441_e.field_73012_v.nextInt(offset);
/* 124 */     int j = y + mc.field_71441_e.field_73012_v.nextInt(offset) - mc.field_71441_e.field_73012_v.nextInt(offset);
/* 125 */     int k = z + mc.field_71441_e.field_73012_v.nextInt(offset) - mc.field_71441_e.field_73012_v.nextInt(offset);
/* 126 */     pos.func_181079_c(i, j, k);
/* 127 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p((BlockPos)pos);
/* 128 */     iblockstate.func_177230_c().func_180655_c(iblockstate, (World)mc.field_71441_e, (BlockPos)pos, random);
/* 129 */     if (!holdingBarrier && iblockstate.func_177230_c() == Blocks.field_180401_cv) {
/* 130 */       mc.field_71441_e.func_175688_a(EnumParticleTypes.BARRIER, (i + 0.5F), (j + 0.5F), (k + 0.5F), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderPre(RenderGameOverlayEvent.Pre event) {
/* 137 */     if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
/* 138 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderPost(RenderGameOverlayEvent.Post event) {
/* 145 */     if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
/* 146 */       if (this.boss.getValue() == Boss.MINIMIZE) {
/* 147 */         Map<UUID, BossInfoClient> map = (mc.field_71456_v.func_184046_j()).field_184060_g;
/* 148 */         if (map == null) {
/*     */           return;
/*     */         }
/* 151 */         ScaledResolution scaledresolution = new ScaledResolution(mc);
/* 152 */         int i = scaledresolution.func_78326_a();
/* 153 */         int j = 12;
/* 154 */         for (Map.Entry<UUID, BossInfoClient> entry : map.entrySet()) {
/* 155 */           BossInfoClient info = entry.getValue();
/* 156 */           String text = info.func_186744_e().func_150254_d();
/* 157 */           int k = (int)(i / ((Float)this.scale.getValue()).floatValue() / 2.0F - 91.0F);
/* 158 */           GL11.glScaled(((Float)this.scale.getValue()).floatValue(), ((Float)this.scale.getValue()).floatValue(), 1.0D);
/* 159 */           if (!event.isCanceled()) {
/* 160 */             GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 161 */             mc.func_110434_K().func_110577_a(GuiBossOverlay.field_184058_a);
/* 162 */             mc.field_71456_v.func_184046_j().func_184052_a(k, j, (BossInfo)info);
/* 163 */             mc.field_71466_p.func_175063_a(text, i / ((Float)this.scale.getValue()).floatValue() / 2.0F - (mc.field_71466_p.func_78256_a(text) / 2), (j - 9), 16777215);
/*     */           } 
/* 165 */           GL11.glScaled(1.0D / ((Float)this.scale.getValue()).floatValue(), 1.0D / ((Float)this.scale.getValue()).floatValue(), 1.0D);
/* 166 */           j += 10 + mc.field_71466_p.field_78288_b;
/*     */         } 
/* 168 */       } else if (this.boss.getValue() == Boss.STACK) {
/* 169 */         Map<UUID, BossInfoClient> map = (mc.field_71456_v.func_184046_j()).field_184060_g;
/* 170 */         HashMap<String, Pair<BossInfoClient, Integer>> to = new HashMap<>();
/* 171 */         for (Map.Entry<UUID, BossInfoClient> entry2 : map.entrySet()) {
/* 172 */           String s = ((BossInfoClient)entry2.getValue()).func_186744_e().func_150254_d();
/* 173 */           if (to.containsKey(s)) {
/* 174 */             Pair<BossInfoClient, Integer> pair = to.get(s);
/* 175 */             pair = new Pair<>(pair.getKey(), Integer.valueOf(((Integer)pair.getValue()).intValue() + 1));
/* 176 */             to.put(s, pair); continue;
/*     */           } 
/* 178 */           Pair<BossInfoClient, Integer> p = new Pair<>(entry2.getValue(), Integer.valueOf(1));
/* 179 */           to.put(s, p);
/*     */         } 
/*     */         
/* 182 */         ScaledResolution scaledresolution2 = new ScaledResolution(mc);
/* 183 */         int l = scaledresolution2.func_78326_a();
/* 184 */         int m = 12;
/* 185 */         for (Map.Entry<String, Pair<BossInfoClient, Integer>> entry3 : to.entrySet()) {
/* 186 */           String text = entry3.getKey();
/* 187 */           BossInfoClient info2 = (BossInfoClient)((Pair)entry3.getValue()).getKey();
/* 188 */           int a = ((Integer)((Pair)entry3.getValue()).getValue()).intValue();
/* 189 */           text = text + " x" + a;
/* 190 */           int k2 = (int)(l / ((Float)this.scale.getValue()).floatValue() / 2.0F - 91.0F);
/* 191 */           GL11.glScaled(((Float)this.scale.getValue()).floatValue(), ((Float)this.scale.getValue()).floatValue(), 1.0D);
/* 192 */           if (!event.isCanceled()) {
/* 193 */             GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 194 */             mc.func_110434_K().func_110577_a(GuiBossOverlay.field_184058_a);
/* 195 */             mc.field_71456_v.func_184046_j().func_184052_a(k2, m, (BossInfo)info2);
/* 196 */             mc.field_71466_p.func_175063_a(text, l / ((Float)this.scale.getValue()).floatValue() / 2.0F - (mc.field_71466_p.func_78256_a(text) / 2), (m - 9), 16777215);
/*     */           } 
/* 198 */           GL11.glScaled(1.0D / ((Float)this.scale.getValue()).floatValue(), 1.0D / ((Float)this.scale.getValue()).floatValue(), 1.0D);
/* 199 */           m += 10 + mc.field_71466_p.field_78288_b;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderLiving(RenderLivingEvent.Pre<?> event) {
/* 208 */     if (((Boolean)this.bats.getValue()).booleanValue() && event.getEntity() instanceof net.minecraft.entity.passive.EntityBat) {
/* 209 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlaySound(PlaySoundAtEntityEvent event) {
/* 216 */     if ((((Boolean)this.bats.getValue()).booleanValue() && event.getSound().equals(SoundEvents.field_187740_w)) || event.getSound().equals(SoundEvents.field_187742_x) || event.getSound().equals(SoundEvents.field_187743_y) || event.getSound().equals(SoundEvents.field_189108_z) || event.getSound().equals(SoundEvents.field_187744_z)) {
/* 217 */       event.setVolume(0.0F);
/* 218 */       event.setPitch(0.0F);
/* 219 */       event.setCanceled(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Skylight
/*     */   {
/* 225 */     NONE,
/* 226 */     WORLD,
/* 227 */     ENTITY,
/* 228 */     ALL;
/*     */   }
/*     */   
/*     */   public enum Fog
/*     */   {
/* 233 */     NONE,
/* 234 */     AIR,
/* 235 */     NOFOG;
/*     */   }
/*     */   
/*     */   public enum Boss
/*     */   {
/* 240 */     NONE,
/* 241 */     REMOVE,
/* 242 */     STACK,
/* 243 */     MINIMIZE;
/*     */   }
/*     */   
/*     */   public enum NoArmor
/*     */   {
/* 248 */     NONE,
/* 249 */     ALL,
/* 250 */     HELMET;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Pair<T, S>
/*     */   {
/*     */     private T key;
/*     */     private S value;
/*     */     
/*     */     public Pair(T key, S value) {
/* 260 */       this.key = key;
/* 261 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public T getKey() {
/* 266 */       return this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setKey(T key) {
/* 271 */       this.key = key;
/*     */     }
/*     */ 
/*     */     
/*     */     public S getValue() {
/* 276 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(S value) {
/* 281 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\render\NoRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */