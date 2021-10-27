/*     */ package me.earth.phobos.features.modules.client;
/*     */ 
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.util.EntityUtil;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Cosmetics
/*     */   extends Module {
/*     */   public static Cosmetics INSTANCE;
/*  21 */   public final ModelBetterPhysicsCape betterPhysicsCape = new ModelBetterPhysicsCape();
/*  22 */   public final ModelCloutGoggles cloutGoggles = new ModelCloutGoggles();
/*  23 */   public final ModelPhyscisCapes capesModel = new ModelPhyscisCapes();
/*  24 */   public final ModelSquidFlag flag = new ModelSquidFlag();
/*  25 */   public final TopHatModel hatModel = new TopHatModel();
/*  26 */   public final GlassesModel glassesModel = new GlassesModel();
/*  27 */   public final SantaHatModel santaHatModel = new SantaHatModel();
/*  28 */   public final ModelHatFez fezModel = new ModelHatFez();
/*  29 */   public final ModelSquidLauncher squidLauncher = new ModelSquidLauncher();
/*  30 */   private final HatGlassesModel hatGlassesModel = new HatGlassesModel();
/*  31 */   private final ResourceLocation hatTexture = new ResourceLocation("textures/tophat.png");
/*  32 */   private final ResourceLocation fezTexture = new ResourceLocation("textures/fez.png");
/*  33 */   private final ResourceLocation glassesTexture = new ResourceLocation("textures/sunglasses.png");
/*  34 */   private final ResourceLocation santaHatTexture = new ResourceLocation("textures/santahat.png");
/*  35 */   private final ResourceLocation capeTexture = new ResourceLocation("textures/cape.png");
/*  36 */   private final ResourceLocation squidTexture = new ResourceLocation("textures/squid.png");
/*  37 */   private final ResourceLocation cloutGoggleTexture = new ResourceLocation("textures/cloutgoggles.png");
/*  38 */   private final ResourceLocation squidLauncherTexture = new ResourceLocation("textures/squidlauncher.png");
/*  39 */   private final ResourceLocation rickrollcapeTexture = new ResourceLocation("textures/rickrollcape.gif");
/*     */   
/*     */   public Cosmetics() {
/*  42 */     super("Cosmetics", "Bitch", Module.Category.CLIENT, true, false, false);
/*  43 */     INSTANCE = this;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderPlayer(RenderPlayerEvent.Post event) {
/*  48 */     if (!Phobos.cosmeticsManager.hasCosmetics(event.getEntityPlayer()) || EntityUtil.isFakePlayer(event.getEntityPlayer())) {
/*     */       return;
/*     */     }
/*  51 */     for (ModelBase model : Phobos.cosmeticsManager.getRenderModels(event.getEntityPlayer())) {
/*  52 */       GlStateManager.func_179094_E();
/*  53 */       RenderManager renderManager = mc.func_175598_ae();
/*  54 */       GlStateManager.func_179137_b(event.getX(), event.getY(), event.getZ());
/*  55 */       double scale = 1.0D;
/*  56 */       double rotate = interpolate((event.getEntityPlayer()).field_70758_at, (event.getEntityPlayer()).field_70759_as, event.getPartialRenderTick());
/*  57 */       double rotate1 = interpolate((event.getEntityPlayer()).field_70127_C, (event.getEntityPlayer()).field_70125_A, event.getPartialRenderTick());
/*  58 */       double rotate3 = event.getEntityPlayer().func_70093_af() ? 22.0D : 0.0D;
/*  59 */       float limbSwingAmount = interpolate((event.getEntityPlayer()).field_184618_aE, (event.getEntityPlayer()).field_70721_aZ, event.getPartialRenderTick());
/*  60 */       float rotate2 = MathHelper.func_76134_b((event.getEntityPlayer()).field_184619_aG * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount / 1.0F;
/*  61 */       GL11.glScaled(-scale, -scale, scale);
/*  62 */       GL11.glTranslated(0.0D, -((event.getEntityPlayer()).field_70131_O - (event.getEntityPlayer().func_70093_af() ? 0.25D : 0.0D) - 0.38D) / scale, 0.0D);
/*  63 */       GL11.glRotated(180.0D + rotate, 0.0D, 1.0D, 0.0D);
/*  64 */       if (!(model instanceof ModelSquidLauncher)) {
/*  65 */         GL11.glRotated(rotate1, 1.0D, 0.0D, 0.0D);
/*     */       }
/*  67 */       if (model instanceof ModelSquidLauncher) {
/*  68 */         GL11.glRotated(rotate3, 1.0D, 0.0D, 0.0D);
/*     */       }
/*  70 */       GlStateManager.func_179137_b(0.0D, -0.45D, 0.0D);
/*  71 */       if (model instanceof ModelSquidLauncher) {
/*  72 */         GlStateManager.func_179137_b(0.15D, 1.3D, 0.0D);
/*  73 */         for (ModelRenderer renderer : this.squidLauncher.field_78092_r) {
/*  74 */           renderer.field_78795_f = rotate2;
/*     */         }
/*     */       } 
/*  77 */       if (model instanceof TopHatModel) {
/*  78 */         mc.func_110434_K().func_110577_a(this.hatTexture);
/*  79 */         this.hatModel.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  80 */         mc.func_110434_K().func_147645_c(this.hatTexture);
/*  81 */       } else if (model instanceof GlassesModel) {
/*  82 */         if (event.getEntityPlayer().func_175148_a(EnumPlayerModelParts.HAT)) {
/*  83 */           mc.func_110434_K().func_110577_a(this.glassesTexture);
/*  84 */           this.hatGlassesModel.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  85 */           mc.func_110434_K().func_147645_c(this.glassesTexture);
/*     */         } else {
/*  87 */           mc.func_110434_K().func_110577_a(this.glassesTexture);
/*  88 */           this.glassesModel.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  89 */           mc.func_110434_K().func_147645_c(this.glassesTexture);
/*     */         } 
/*  91 */       } else if (model instanceof SantaHatModel) {
/*  92 */         mc.func_110434_K().func_110577_a(this.santaHatTexture);
/*  93 */         this.santaHatModel.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  94 */         mc.func_110434_K().func_147645_c(this.santaHatTexture);
/*  95 */       } else if (model instanceof ModelCloutGoggles) {
/*  96 */         mc.func_110434_K().func_110577_a(this.cloutGoggleTexture);
/*  97 */         this.cloutGoggles.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  98 */         mc.func_110434_K().func_147645_c(this.cloutGoggleTexture);
/*  99 */       } else if (model instanceof ModelSquidFlag) {
/* 100 */         mc.func_110434_K().func_110577_a(this.squidTexture);
/* 101 */         this.flag.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 102 */         mc.func_110434_K().func_147645_c(this.squidTexture);
/* 103 */       } else if (model instanceof ModelSquidLauncher) {
/* 104 */         mc.func_110434_K().func_110577_a(this.squidLauncherTexture);
/* 105 */         this.squidLauncher.func_78088_a(event.getEntity(), 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0325F);
/* 106 */         mc.func_110434_K().func_147645_c(this.squidLauncherTexture);
/*     */       } 
/* 108 */       GlStateManager.func_179121_F();
/*     */     } 
/*     */   }
/*     */   
/*     */   public float interpolate(float yaw1, float yaw2, float percent) {
/* 113 */     float rotation = (yaw1 + (yaw2 - yaw1) * percent) % 360.0F;
/* 114 */     if (rotation < 0.0F) {
/* 115 */       rotation += 360.0F;
/*     */     }
/* 117 */     return rotation;
/*     */   }
/*     */   
/*     */   public static class ModelHatFez
/*     */     extends ModelBase {
/*     */     private final ModelRenderer baseLayer;
/*     */     private final ModelRenderer topLayer;
/*     */     private final ModelRenderer stringLayer;
/*     */     private final ModelRenderer danglingStringLayer;
/*     */     private final ModelRenderer otherDanglingStringLayer;
/*     */     
/*     */     public ModelHatFez() {
/* 129 */       this.field_78090_t = 64;
/* 130 */       this.field_78089_u = 32;
/* 131 */       this.baseLayer = new ModelRenderer(this, 1, 1);
/* 132 */       this.baseLayer.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 4, 6);
/* 133 */       this.baseLayer.func_78793_a(0.0F, -4.0F, 0.0F);
/* 134 */       this.baseLayer.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 135 */       this.baseLayer.field_78809_i = true;
/* 136 */       setRotation(this.baseLayer, 0.0F, 0.12217305F, 0.0F);
/* 137 */       this.topLayer = new ModelRenderer(this, 1, 1);
/* 138 */       this.topLayer.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
/* 139 */       this.topLayer.func_78793_a(-0.5F, -4.75F, -0.5F);
/* 140 */       this.topLayer.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 141 */       this.topLayer.field_78809_i = true;
/* 142 */       setRotation(this.topLayer, 0.0F, 0.0F, 0.0F);
/* 143 */       this.stringLayer = new ModelRenderer(this, 25, 1);
/* 144 */       this.stringLayer.func_78789_a(-0.5F, -0.5F, -0.5F, 3, 1, 1);
/* 145 */       this.stringLayer.func_78793_a(0.5F, -3.75F, 0.0F);
/* 146 */       this.stringLayer.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 147 */       this.stringLayer.field_78809_i = true;
/* 148 */       setRotation(this.stringLayer, 0.7853982F, 0.0F, 0.0F);
/* 149 */       this.danglingStringLayer = new ModelRenderer(this, 41, 1);
/* 150 */       this.danglingStringLayer.func_78789_a(-0.5F, -0.5F, -0.5F, 3, 1, 1);
/* 151 */       this.danglingStringLayer.func_78793_a(3.0F, -3.5F, 0.0F);
/* 152 */       this.danglingStringLayer.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 153 */       this.danglingStringLayer.field_78809_i = true;
/* 154 */       setRotation(this.danglingStringLayer, 0.2268928F, 0.7853982F, 1.2042772F);
/* 155 */       this.otherDanglingStringLayer = new ModelRenderer(this, 33, 9);
/* 156 */       this.otherDanglingStringLayer.func_78789_a(-0.5F, -0.5F, -0.5F, 3, 1, 1);
/* 157 */       this.otherDanglingStringLayer.func_78793_a(3.0F, -3.5F, 0.0F);
/* 158 */       this.otherDanglingStringLayer.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 159 */       this.otherDanglingStringLayer.field_78809_i = true;
/* 160 */       setRotation(this.otherDanglingStringLayer, 0.2268928F, -0.9250245F, 1.2042772F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 164 */       super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 165 */       setRotationAngles(f, f1, f2, f3, f4, f5);
/* 166 */       this.baseLayer.func_78785_a(f5);
/* 167 */       this.topLayer.func_78785_a(f5);
/* 168 */       this.stringLayer.func_78785_a(f5);
/* 169 */       this.danglingStringLayer.func_78785_a(f5);
/* 170 */       this.otherDanglingStringLayer.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 174 */       model.field_78795_f = x;
/* 175 */       model.field_78796_g = y;
/* 176 */       model.field_78808_h = z;
/*     */     }
/*     */     
/*     */     public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 180 */       func_78087_a(f, f1, f2, f3, f4, f5, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public class ModelBetterPhysicsCape
/*     */     extends ModelBase
/*     */   {
/*     */     public ModelRenderer segment1;
/*     */     
/*     */     public ModelBetterPhysicsCape() {
/* 191 */       for (int i = 0; i < 160; i++) {
/* 192 */         ModelRenderer segment = new ModelRenderer(this, 0, i);
/* 193 */         segment.func_78793_a(0.0F, 0.0F, 0.0F);
/* 194 */         segment.func_78790_a(-5.0F, 0.0F + i, 0.0F, 10, 1, 1, 0.0F);
/* 195 */         this.field_78092_r.add(segment);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 200 */       for (ModelRenderer model : this.field_78092_r) {
/* 201 */         GlStateManager.func_179094_E();
/* 202 */         GlStateManager.func_179109_b(model.field_82906_o, model.field_82908_p, model.field_82907_q);
/* 203 */         GlStateManager.func_179109_b(model.field_78800_c * f5, model.field_78797_d * f5, model.field_78798_e * f5);
/* 204 */         GlStateManager.func_179139_a(1.0D, 0.1D, 1.0D);
/* 205 */         GlStateManager.func_179109_b(-model.field_82906_o, -model.field_82908_p, -model.field_82907_q);
/* 206 */         GlStateManager.func_179109_b(-model.field_78800_c * f5, -model.field_78797_d * f5, -model.field_78798_e * f5);
/* 207 */         model.func_78785_a(f5);
/* 208 */         GlStateManager.func_179121_F();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 213 */       modelRenderer.field_78795_f = x;
/* 214 */       modelRenderer.field_78796_g = y;
/* 215 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ModelCloutGoggles
/*     */     extends ModelBase {
/*     */     public ModelRenderer leftGlass;
/*     */     public ModelRenderer topLeftFrame;
/*     */     public ModelRenderer bottomLeftFrame;
/*     */     public ModelRenderer leftLeftFrame;
/*     */     public ModelRenderer rightLeftFrame;
/*     */     public ModelRenderer rightGlass;
/*     */     public ModelRenderer topRightFrame;
/*     */     public ModelRenderer bottomLeftFrame_1;
/*     */     public ModelRenderer leftRightFrame;
/*     */     public ModelRenderer rightRightFrame;
/*     */     public ModelRenderer leftEar;
/*     */     public ModelRenderer rightEar;
/*     */     
/*     */     public ModelCloutGoggles() {
/* 235 */       this.field_78090_t = 64;
/* 236 */       this.field_78089_u = 32;
/* 237 */       this.rightLeftFrame = new ModelRenderer(this, 18, 0);
/* 238 */       this.rightLeftFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 239 */       this.rightLeftFrame.func_78790_a(0.0F, 2.0F, 0.0F, 2, 1, 1, 0.0F);
/* 240 */       this.bottomLeftFrame_1 = new ModelRenderer(this, 26, 5);
/* 241 */       this.bottomLeftFrame_1.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 242 */       this.bottomLeftFrame_1.func_78790_a(4.0F, 2.0F, 0.0F, 2, 1, 1, 0.0F);
/* 243 */       this.leftLeftFrame = new ModelRenderer(this, 10, 5);
/* 244 */       this.leftLeftFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 245 */       this.leftLeftFrame.func_78790_a(2.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
/* 246 */       this.rightGlass = new ModelRenderer(this, 18, 5);
/* 247 */       this.rightGlass.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 248 */       this.rightGlass.func_78790_a(4.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 249 */       this.rightRightFrame = new ModelRenderer(this, 10, 11);
/* 250 */       this.rightRightFrame.func_78793_a(3.0F, 3.0F, -4.0F);
/* 251 */       this.rightRightFrame.func_78790_a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
/* 252 */       this.leftEar = new ModelRenderer(this, 18, 11);
/* 253 */       this.leftEar.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 254 */       this.leftEar.func_78790_a(-1.2F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 255 */       this.topRightFrame = new ModelRenderer(this, 26, 0);
/* 256 */       this.topRightFrame.func_78793_a(1.0F, 3.0F, -4.0F);
/* 257 */       this.topRightFrame.func_78790_a(0.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
/* 258 */       this.topLeftFrame = new ModelRenderer(this, 0, 5);
/* 259 */       this.topLeftFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 260 */       this.topLeftFrame.func_78790_a(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
/* 261 */       this.rightEar = new ModelRenderer(this, 28, 11);
/* 262 */       this.rightEar.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 263 */       this.rightEar.func_78790_a(6.2F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 264 */       this.leftGlass = new ModelRenderer(this, 0, 0);
/* 265 */       this.leftGlass.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 266 */       this.leftGlass.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 267 */       this.bottomLeftFrame = new ModelRenderer(this, 10, 0);
/* 268 */       this.bottomLeftFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 269 */       this.bottomLeftFrame.func_78790_a(0.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
/* 270 */       this.leftRightFrame = new ModelRenderer(this, 0, 11);
/* 271 */       this.leftRightFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 272 */       this.leftRightFrame.func_78790_a(3.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 276 */       this.rightLeftFrame.func_78785_a(f5);
/* 277 */       this.bottomLeftFrame_1.func_78785_a(f5);
/* 278 */       this.leftLeftFrame.func_78785_a(f5);
/* 279 */       this.rightGlass.func_78785_a(f5);
/* 280 */       this.rightRightFrame.func_78785_a(f5);
/* 281 */       this.leftEar.func_78785_a(f5);
/* 282 */       this.topRightFrame.func_78785_a(f5);
/* 283 */       this.topLeftFrame.func_78785_a(f5);
/* 284 */       this.rightEar.func_78785_a(f5);
/* 285 */       this.leftGlass.func_78785_a(f5);
/* 286 */       this.bottomLeftFrame.func_78785_a(f5);
/* 287 */       this.leftRightFrame.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 291 */       modelRenderer.field_78795_f = x;
/* 292 */       modelRenderer.field_78796_g = y;
/* 293 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ModelCosmetic
/*     */     extends ModelBase {
/*     */     public ResourceLocation texture;
/*     */   }
/*     */   
/*     */   public class ModelSquidLauncher
/*     */     extends ModelBase {
/*     */     public ModelRenderer barrel;
/*     */     public ModelRenderer squid;
/*     */     public ModelRenderer secondBarrel;
/*     */     public ModelRenderer barrelSide1;
/*     */     public ModelRenderer barrelSide2;
/*     */     public ModelRenderer barrelSide3;
/*     */     public ModelRenderer barrelSide4;
/*     */     public ModelRenderer stock;
/*     */     public ModelRenderer stockEnd;
/*     */     public ModelRenderer trigger;
/*     */     
/*     */     public ModelSquidLauncher() {
/* 316 */       this.field_78090_t = 64;
/* 317 */       this.field_78089_u = 32;
/* 318 */       this.barrelSide4 = new ModelRenderer(this, 0, 0);
/* 319 */       this.barrelSide4.func_78793_a(0.5F, 0.0F, 0.0F);
/* 320 */       this.barrelSide4.func_78790_a(0.0F, -2.0F, 0.2F, 4, 5, 1, 0.0F);
/* 321 */       setRotateAngle(this.barrelSide4, 0.091106184F, 0.0F, 0.0F);
/* 322 */       this.stock = new ModelRenderer(this, 0, 24);
/* 323 */       this.stock.func_78793_a(0.0F, 0.0F, 0.0F);
/* 324 */       this.stock.func_78790_a(1.5F, 3.0F, 1.5F, 2, 4, 2, 0.0F);
/* 325 */       this.squid = new ModelRenderer(this, 0, 16);
/* 326 */       this.squid.func_78793_a(0.0F, 0.0F, 0.0F);
/* 327 */       this.squid.func_78790_a(1.2F, -11.5F, 0.8F, 3, 4, 3, 0.0F);
/* 328 */       setRotateAngle(this.squid, 0.0F, -0.091106184F, 0.0F);
/* 329 */       this.barrelSide2 = new ModelRenderer(this, 18, 14);
/* 330 */       this.barrelSide2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 331 */       this.barrelSide2.func_78790_a(3.8F, -2.5F, 0.5F, 1, 5, 4, 0.0F);
/* 332 */       setRotateAngle(this.barrelSide2, 0.0F, 0.0F, 0.091106184F);
/* 333 */       this.secondBarrel = new ModelRenderer(this, 32, 14);
/* 334 */       this.secondBarrel.func_78793_a(0.0F, 0.0F, 0.0F);
/* 335 */       this.secondBarrel.func_78790_a(0.5F, -2.0F, 0.5F, 4, 5, 4, 0.0F);
/* 336 */       this.stockEnd = new ModelRenderer(this, 18, 26);
/* 337 */       this.stockEnd.func_78793_a(0.0F, 0.0F, 0.0F);
/* 338 */       this.stockEnd.func_78790_a(2.0F, 7.0F, 1.5F, 1, 1, 4, 0.0F);
/* 339 */       this.barrelSide1 = new ModelRenderer(this, 18, 14);
/* 340 */       this.barrelSide1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 341 */       this.barrelSide1.func_78790_a(0.2F, -2.0F, 0.5F, 1, 5, 4, 0.0F);
/* 342 */       setRotateAngle(this.barrelSide1, 0.0F, 0.0F, -0.091106184F);
/* 343 */       this.barrelSide3 = new ModelRenderer(this, 0, 0);
/* 344 */       this.barrelSide3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 345 */       this.barrelSide3.func_78790_a(0.5F, -2.5F, 3.8F, 4, 5, 1, 0.0F);
/* 346 */       setRotateAngle(this.barrelSide3, -0.091106184F, 0.0F, 0.0F);
/* 347 */       this.trigger = new ModelRenderer(this, 40, 0);
/* 348 */       this.trigger.func_78793_a(0.0F, 0.0F, 0.0F);
/* 349 */       this.trigger.func_78790_a(12.0F, 6.6F, 5.4F, 1, 1, 1, 0.0F);
/* 350 */       this.barrel = new ModelRenderer(this, 18, 0);
/* 351 */       this.barrel.func_78793_a(0.0F, 0.0F, 0.0F);
/* 352 */       this.barrel.func_78790_a(0.0F, -8.0F, 0.0F, 5, 6, 5, 0.0F);
/* 353 */       this.field_78092_r.add(this.barrel);
/* 354 */       this.field_78092_r.add(this.squid);
/* 355 */       this.field_78092_r.add(this.secondBarrel);
/* 356 */       this.field_78092_r.add(this.barrelSide1);
/* 357 */       this.field_78092_r.add(this.barrelSide2);
/* 358 */       this.field_78092_r.add(this.barrelSide3);
/* 359 */       this.field_78092_r.add(this.barrelSide4);
/* 360 */       this.field_78092_r.add(this.stock);
/* 361 */       this.field_78092_r.add(this.stockEnd);
/* 362 */       this.field_78092_r.add(this.trigger);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 366 */       this.stock.func_78785_a(f5);
/* 367 */       this.barrelSide1.func_78785_a(f5);
/* 368 */       this.stockEnd.func_78785_a(f5);
/* 369 */       this.secondBarrel.func_78785_a(f5);
/* 370 */       this.barrelSide3.func_78785_a(f5);
/* 371 */       this.squid.func_78785_a(f5);
/* 372 */       this.barrelSide4.func_78785_a(f5);
/* 373 */       this.barrel.func_78785_a(f5);
/* 374 */       this.barrelSide2.func_78785_a(f5);
/* 375 */       GlStateManager.func_179094_E();
/* 376 */       GlStateManager.func_179109_b(this.trigger.field_82906_o, this.trigger.field_82908_p, this.trigger.field_82907_q);
/* 377 */       GlStateManager.func_179109_b(this.trigger.field_78800_c * f5, this.trigger.field_78797_d * f5, this.trigger.field_78798_e * f5);
/* 378 */       GlStateManager.func_179139_a(0.2D, 1.0D, 0.8D);
/* 379 */       GlStateManager.func_179109_b(-this.trigger.field_82906_o, -this.trigger.field_82908_p, -this.trigger.field_82907_q);
/* 380 */       GlStateManager.func_179109_b(-this.trigger.field_78800_c * f5, -this.trigger.field_78797_d * f5, -this.trigger.field_78798_e * f5);
/* 381 */       this.trigger.func_78785_a(f5);
/* 382 */       GlStateManager.func_179121_F();
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 386 */       modelRenderer.field_78795_f = x;
/* 387 */       modelRenderer.field_78796_g = y;
/* 388 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ModelSquidFlag
/*     */     extends ModelBase {
/*     */     public ModelRenderer flag;
/*     */     
/*     */     public ModelSquidFlag() {
/* 397 */       this.field_78090_t = 64;
/* 398 */       this.field_78089_u = 32;
/* 399 */       this.flag = new ModelRenderer(this, 0, 0);
/* 400 */       this.flag.func_78793_a(0.0F, 0.0F, 0.0F);
/* 401 */       this.flag.func_78790_a(-5.0F, -16.0F, 0.0F, 10, 16, 1, 0.0F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 405 */       this.flag.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 409 */       modelRenderer.field_78795_f = x;
/* 410 */       modelRenderer.field_78796_g = y;
/* 411 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ModelPhyscisCapes
/*     */     extends ModelBase {
/*     */     public ModelRenderer shape1;
/*     */     public ModelRenderer shape2;
/*     */     public ModelRenderer shape3;
/*     */     public ModelRenderer shape4;
/*     */     public ModelRenderer shape5;
/*     */     public ModelRenderer shape6;
/*     */     public ModelRenderer shape7;
/*     */     public ModelRenderer shape8;
/*     */     public ModelRenderer shape9;
/*     */     public ModelRenderer shape10;
/*     */     public ModelRenderer shape11;
/*     */     public ModelRenderer shape12;
/*     */     public ModelRenderer shape13;
/*     */     public ModelRenderer shape14;
/*     */     public ModelRenderer shape15;
/*     */     public ModelRenderer shape16;
/*     */     
/*     */     public ModelPhyscisCapes() {
/* 435 */       this.field_78090_t = 64;
/* 436 */       this.field_78089_u = 32;
/* 437 */       this.shape9 = new ModelRenderer(this, 0, 8);
/* 438 */       this.shape9.func_78793_a(-5.0F, 8.0F, -1.0F);
/* 439 */       this.shape9.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 440 */       this.shape15 = new ModelRenderer(this, 0, 14);
/* 441 */       this.shape15.func_78793_a(-5.0F, 14.0F, -1.0F);
/* 442 */       this.shape15.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 443 */       this.shape3 = new ModelRenderer(this, 0, 2);
/* 444 */       this.shape3.func_78793_a(-5.0F, 2.0F, -1.0F);
/* 445 */       this.shape3.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 446 */       this.shape7 = new ModelRenderer(this, 0, 6);
/* 447 */       this.shape7.func_78793_a(-5.0F, 6.0F, -1.0F);
/* 448 */       this.shape7.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 449 */       this.shape1 = new ModelRenderer(this, 0, 0);
/* 450 */       this.shape1.func_78793_a(-5.0F, 0.0F, -1.0F);
/* 451 */       this.shape1.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 452 */       this.shape6 = new ModelRenderer(this, 0, 5);
/* 453 */       this.shape6.func_78793_a(-5.0F, 5.0F, -1.0F);
/* 454 */       this.shape6.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 455 */       this.shape14 = new ModelRenderer(this, 0, 13);
/* 456 */       this.shape14.func_78793_a(-5.0F, 13.0F, -1.0F);
/* 457 */       this.shape14.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 458 */       this.shape10 = new ModelRenderer(this, 0, 9);
/* 459 */       this.shape10.func_78793_a(-5.0F, 9.0F, -1.0F);
/* 460 */       this.shape10.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 461 */       this.shape13 = new ModelRenderer(this, 0, 12);
/* 462 */       this.shape13.func_78793_a(-5.0F, 12.0F, -1.0F);
/* 463 */       this.shape13.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 464 */       this.shape4 = new ModelRenderer(this, 0, 3);
/* 465 */       this.shape4.func_78793_a(-5.0F, 3.0F, -1.0F);
/* 466 */       this.shape4.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 467 */       this.shape8 = new ModelRenderer(this, 0, 7);
/* 468 */       this.shape8.func_78793_a(-5.0F, 7.0F, -1.0F);
/* 469 */       this.shape8.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 470 */       this.shape16 = new ModelRenderer(this, 0, 15);
/* 471 */       this.shape16.func_78793_a(-5.0F, 15.0F, -1.0F);
/* 472 */       this.shape16.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 473 */       this.shape12 = new ModelRenderer(this, 0, 11);
/* 474 */       this.shape12.func_78793_a(-5.0F, 11.0F, -1.0F);
/* 475 */       this.shape12.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 476 */       this.shape5 = new ModelRenderer(this, 0, 4);
/* 477 */       this.shape5.func_78793_a(-5.0F, 4.0F, -1.0F);
/* 478 */       this.shape5.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 479 */       this.shape11 = new ModelRenderer(this, 0, 10);
/* 480 */       this.shape11.func_78793_a(-5.0F, 10.0F, -1.0F);
/* 481 */       this.shape11.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 482 */       this.shape2 = new ModelRenderer(this, 0, 1);
/* 483 */       this.shape2.func_78793_a(-5.0F, 1.0F, -1.0F);
/* 484 */       this.shape2.func_78790_a(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
/* 485 */       this.field_78092_r.add(this.shape1);
/* 486 */       this.field_78092_r.add(this.shape2);
/* 487 */       this.field_78092_r.add(this.shape3);
/* 488 */       this.field_78092_r.add(this.shape4);
/* 489 */       this.field_78092_r.add(this.shape5);
/* 490 */       this.field_78092_r.add(this.shape6);
/* 491 */       this.field_78092_r.add(this.shape7);
/* 492 */       this.field_78092_r.add(this.shape8);
/* 493 */       this.field_78092_r.add(this.shape9);
/* 494 */       this.field_78092_r.add(this.shape10);
/* 495 */       this.field_78092_r.add(this.shape11);
/* 496 */       this.field_78092_r.add(this.shape12);
/* 497 */       this.field_78092_r.add(this.shape13);
/* 498 */       this.field_78092_r.add(this.shape14);
/* 499 */       this.field_78092_r.add(this.shape15);
/* 500 */       this.field_78092_r.add(this.shape16);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 504 */       this.shape9.func_78785_a(f5);
/* 505 */       this.shape15.func_78785_a(f5);
/* 506 */       this.shape3.func_78785_a(f5);
/* 507 */       this.shape7.func_78785_a(f5);
/* 508 */       this.shape1.func_78785_a(f5);
/* 509 */       this.shape6.func_78785_a(f5);
/* 510 */       this.shape14.func_78785_a(f5);
/* 511 */       this.shape10.func_78785_a(f5);
/* 512 */       this.shape13.func_78785_a(f5);
/* 513 */       this.shape4.func_78785_a(f5);
/* 514 */       this.shape8.func_78785_a(f5);
/* 515 */       this.shape16.func_78785_a(f5);
/* 516 */       this.shape12.func_78785_a(f5);
/* 517 */       this.shape5.func_78785_a(f5);
/* 518 */       this.shape11.func_78785_a(f5);
/* 519 */       this.shape2.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 523 */       modelRenderer.field_78795_f = x;
/* 524 */       modelRenderer.field_78796_g = y;
/* 525 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SantaHatModel
/*     */     extends ModelBase {
/*     */     public ModelRenderer baseLayer;
/*     */     public ModelRenderer baseRedLayer;
/*     */     public ModelRenderer midRedLayer;
/*     */     public ModelRenderer topRedLayer;
/*     */     public ModelRenderer lastRedLayer;
/*     */     public ModelRenderer realFinalLastLayer;
/*     */     public ModelRenderer whiteLayer;
/*     */     
/*     */     public SantaHatModel() {
/* 540 */       this.field_78090_t = 64;
/* 541 */       this.field_78089_u = 32;
/* 542 */       this.topRedLayer = new ModelRenderer(this, 46, 0);
/* 543 */       this.topRedLayer.func_78793_a(0.5F, -8.4F, -1.5F);
/* 544 */       this.topRedLayer.func_78790_a(0.0F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
/* 545 */       setRotateAngle(this.topRedLayer, 0.0F, 0.0F, 0.5009095F);
/* 546 */       this.baseLayer = new ModelRenderer(this, 0, 0);
/* 547 */       this.baseLayer.func_78793_a(-4.0F, -1.0F, -4.0F);
/* 548 */       this.baseLayer.func_78790_a(0.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F);
/* 549 */       this.midRedLayer = new ModelRenderer(this, 28, 0);
/* 550 */       this.midRedLayer.func_78793_a(-1.2F, -6.8F, -2.0F);
/* 551 */       this.midRedLayer.func_78790_a(0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F);
/* 552 */       setRotateAngle(this.midRedLayer, 0.0F, 0.0F, 0.22759093F);
/* 553 */       this.realFinalLastLayer = new ModelRenderer(this, 46, 8);
/* 554 */       this.realFinalLastLayer.func_78793_a(4.0F, -10.4F, 0.0F);
/* 555 */       this.realFinalLastLayer.func_78790_a(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
/* 556 */       setRotateAngle(this.realFinalLastLayer, 0.0F, 0.0F, 1.0016445F);
/* 557 */       this.lastRedLayer = new ModelRenderer(this, 34, 8);
/* 558 */       this.lastRedLayer.func_78793_a(2.0F, -9.4F, 0.0F);
/* 559 */       this.lastRedLayer.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
/* 560 */       setRotateAngle(this.lastRedLayer, 0.0F, 0.0F, 0.8196066F);
/* 561 */       this.whiteLayer = new ModelRenderer(this, 0, 22);
/* 562 */       this.whiteLayer.func_78793_a(4.1F, -9.7F, -0.5F);
/* 563 */       this.whiteLayer.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
/* 564 */       setRotateAngle(this.whiteLayer, -0.091106184F, 0.0F, 0.18203785F);
/* 565 */       this.baseRedLayer = new ModelRenderer(this, 0, 11);
/* 566 */       this.baseRedLayer.func_78793_a(-3.0F, -4.0F, -3.0F);
/* 567 */       this.baseRedLayer.func_78790_a(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
/* 568 */       setRotateAngle(this.baseRedLayer, 0.0F, 0.0F, 0.045553092F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 572 */       this.topRedLayer.func_78785_a(f5);
/* 573 */       this.baseLayer.func_78785_a(f5);
/* 574 */       this.midRedLayer.func_78785_a(f5);
/* 575 */       this.realFinalLastLayer.func_78785_a(f5);
/* 576 */       this.lastRedLayer.func_78785_a(f5);
/* 577 */       this.whiteLayer.func_78785_a(f5);
/* 578 */       this.baseRedLayer.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 582 */       modelRenderer.field_78795_f = x;
/* 583 */       modelRenderer.field_78796_g = y;
/* 584 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class HatGlassesModel
/*     */     extends ModelBase {
/* 590 */     public final ResourceLocation glassesTexture = new ResourceLocation("textures/sunglasses.png");
/*     */     public ModelRenderer firstLeftFrame;
/*     */     public ModelRenderer firstRightFrame;
/*     */     public ModelRenderer centerBar;
/*     */     public ModelRenderer farLeftBar;
/*     */     public ModelRenderer farRightBar;
/*     */     public ModelRenderer leftEar;
/*     */     public ModelRenderer rightEar;
/*     */     
/*     */     public HatGlassesModel() {
/* 600 */       this.field_78090_t = 64;
/* 601 */       this.field_78089_u = 64;
/* 602 */       this.farLeftBar = new ModelRenderer(this, 0, 13);
/* 603 */       this.farLeftBar.func_78793_a(-4.0F, 3.5F, -5.0F);
/* 604 */       this.farLeftBar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/* 605 */       this.rightEar = new ModelRenderer(this, 10, 0);
/* 606 */       this.rightEar.func_78793_a(3.2F, 3.5F, -5.0F);
/* 607 */       this.rightEar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 608 */       this.centerBar = new ModelRenderer(this, 0, 9);
/* 609 */       this.centerBar.func_78793_a(-1.0F, 3.5F, -5.0F);
/* 610 */       this.centerBar.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
/* 611 */       this.firstLeftFrame = new ModelRenderer(this, 0, 0);
/* 612 */       this.firstLeftFrame.func_78793_a(-3.0F, 3.0F, -5.0F);
/* 613 */       this.firstLeftFrame.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 614 */       this.firstRightFrame = new ModelRenderer(this, 0, 5);
/* 615 */       this.firstRightFrame.func_78793_a(1.0F, 3.0F, -5.0F);
/* 616 */       this.firstRightFrame.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 617 */       this.leftEar = new ModelRenderer(this, 20, 0);
/* 618 */       this.leftEar.func_78793_a(-4.2F, 3.5F, -5.0F);
/* 619 */       this.leftEar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 620 */       this.farRightBar = new ModelRenderer(this, 0, 17);
/* 621 */       this.farRightBar.func_78793_a(3.0F, 3.5F, -5.0F);
/* 622 */       this.farRightBar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 626 */       this.farLeftBar.func_78785_a(f5);
/* 627 */       this.rightEar.func_78785_a(f5);
/* 628 */       this.centerBar.func_78785_a(f5);
/* 629 */       this.firstLeftFrame.func_78785_a(f5);
/* 630 */       this.firstRightFrame.func_78785_a(f5);
/* 631 */       this.leftEar.func_78785_a(f5);
/* 632 */       this.farRightBar.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 636 */       modelRenderer.field_78795_f = x;
/* 637 */       modelRenderer.field_78796_g = y;
/* 638 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class GlassesModel
/*     */     extends ModelBase {
/* 644 */     public final ResourceLocation glassesTexture = new ResourceLocation("textures/sunglasses.png");
/*     */     public ModelRenderer firstLeftFrame;
/*     */     public ModelRenderer firstRightFrame;
/*     */     public ModelRenderer centerBar;
/*     */     public ModelRenderer farLeftBar;
/*     */     public ModelRenderer farRightBar;
/*     */     public ModelRenderer leftEar;
/*     */     public ModelRenderer rightEar;
/*     */     
/*     */     public GlassesModel() {
/* 654 */       this.field_78090_t = 64;
/* 655 */       this.field_78089_u = 64;
/* 656 */       this.farLeftBar = new ModelRenderer(this, 0, 13);
/* 657 */       this.farLeftBar.func_78793_a(-4.0F, 3.5F, -4.0F);
/* 658 */       this.farLeftBar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/* 659 */       this.rightEar = new ModelRenderer(this, 10, 0);
/* 660 */       this.rightEar.func_78793_a(3.2F, 3.5F, -4.0F);
/* 661 */       this.rightEar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 662 */       this.centerBar = new ModelRenderer(this, 0, 9);
/* 663 */       this.centerBar.func_78793_a(-1.0F, 3.5F, -4.0F);
/* 664 */       this.centerBar.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
/* 665 */       this.firstLeftFrame = new ModelRenderer(this, 0, 0);
/* 666 */       this.firstLeftFrame.func_78793_a(-3.0F, 3.0F, -4.0F);
/* 667 */       this.firstLeftFrame.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 668 */       this.firstRightFrame = new ModelRenderer(this, 0, 5);
/* 669 */       this.firstRightFrame.func_78793_a(1.0F, 3.0F, -4.0F);
/* 670 */       this.firstRightFrame.func_78790_a(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
/* 671 */       this.leftEar = new ModelRenderer(this, 20, 0);
/* 672 */       this.leftEar.func_78793_a(-4.2F, 3.5F, -4.0F);
/* 673 */       this.leftEar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
/* 674 */       this.farRightBar = new ModelRenderer(this, 0, 17);
/* 675 */       this.farRightBar.func_78793_a(3.0F, 3.5F, -4.0F);
/* 676 */       this.farRightBar.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 680 */       this.farLeftBar.func_78785_a(f5);
/* 681 */       this.rightEar.func_78785_a(f5);
/* 682 */       this.centerBar.func_78785_a(f5);
/* 683 */       this.firstLeftFrame.func_78785_a(f5);
/* 684 */       this.firstRightFrame.func_78785_a(f5);
/* 685 */       this.leftEar.func_78785_a(f5);
/* 686 */       this.farRightBar.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 690 */       modelRenderer.field_78795_f = x;
/* 691 */       modelRenderer.field_78796_g = y;
/* 692 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */   
/*     */   public class TopHatModel
/*     */     extends ModelBase {
/* 698 */     public final ResourceLocation hatTexture = new ResourceLocation("textures/tophat.png");
/*     */     public ModelRenderer bottom;
/*     */     public ModelRenderer top;
/*     */     
/*     */     public TopHatModel() {
/* 703 */       this.field_78090_t = 64;
/* 704 */       this.field_78089_u = 32;
/* 705 */       this.top = new ModelRenderer(this, 0, 10);
/* 706 */       this.top.func_78790_a(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
/* 707 */       this.top.func_78793_a(-2.0F, -11.0F, -2.0F);
/* 708 */       this.bottom = new ModelRenderer(this, 0, 0);
/* 709 */       this.bottom.func_78790_a(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
/* 710 */       this.bottom.func_78793_a(-4.0F, -1.0F, -4.0F);
/*     */     }
/*     */     
/*     */     public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 714 */       this.top.func_78785_a(f5);
/* 715 */       this.bottom.func_78785_a(f5);
/*     */     }
/*     */     
/*     */     public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 719 */       modelRenderer.field_78795_f = x;
/* 720 */       modelRenderer.field_78796_g = y;
/* 721 */       modelRenderer.field_78808_h = z;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\client\Cosmetics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */