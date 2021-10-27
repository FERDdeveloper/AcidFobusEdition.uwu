/*     */ package me.earth.phobos.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleGenerator
/*     */ {
/*     */   private final int count;
/*     */   private final int width;
/*     */   private final int height;
/*  15 */   private final ArrayList<Particle> particles = new ArrayList<>();
/*  16 */   private final Random random = new Random();
/*  17 */   int state = 0;
/*  18 */   int a = 255;
/*  19 */   int r = 255;
/*  20 */   int g = 0;
/*  21 */   int b = 0;
/*     */ 
/*     */   
/*     */   public ParticleGenerator(int count, int width, int height) {
/*  25 */     this.count = count;
/*  26 */     this.width = width;
/*  27 */     this.height = height;
/*  28 */     for (int i = 0; i < count; i++) {
/*  29 */       this.particles.add(new Particle(this.random.nextInt(width), this.random.nextInt(height)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawParticles(int mouseX, int mouseY) {
/*  35 */     for (Particle p : this.particles) {
/*  36 */       if (p.reset) {
/*  37 */         p.resetPosSize();
/*  38 */         p.reset = false;
/*     */       } 
/*  40 */       p.draw(mouseX, mouseY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public class Particle
/*     */   {
/*  47 */     private final Random random = new Random();
/*     */     
/*     */     private int x;
/*     */     private int y;
/*     */     private int k;
/*     */     private float size;
/*     */     private boolean reset;
/*     */     
/*     */     public Particle(int x, int y) {
/*  56 */       this.x = x;
/*  57 */       this.y = y;
/*  58 */       this.size = genRandom(1.0F, 4.0F);
/*     */     }
/*     */ 
/*     */     
/*     */     public void draw(int mouseX, int mouseY) {
/*  63 */       if (this.size <= 0.0F) {
/*  64 */         this.reset = true;
/*     */       }
/*  66 */       this.size -= 0.05F;
/*  67 */       this.k++;
/*  68 */       int xx = (int)(MathHelper.func_76134_b(0.1F * (this.x + this.k)) * 10.0F);
/*  69 */       int yy = (int)(MathHelper.func_76134_b(0.1F * (this.y + this.k)) * 10.0F);
/*  70 */       Utils.drawBorderedCircle(this.x + xx, this.y + yy, this.size, 0, 553648127);
/*     */ 
/*     */       
/*  73 */       float distance = (float)Utils.distance((this.x + xx), (this.y + yy), mouseX, mouseY);
/*     */       
/*  75 */       if (distance < 70.0F) {
/*  76 */         float alpha1 = Math.min(1.0F, Math.min(1.0F, 1.0F - distance / 70.0F));
/*     */         
/*  78 */         GL11.glEnable(2848);
/*  79 */         GL11.glDisable(2929);
/*  80 */         GL11.glColor4f(255.0F, 255.0F, 255.0F, 255.0F);
/*  81 */         GL11.glDisable(3553);
/*  82 */         GL11.glDepthMask(false);
/*  83 */         GL11.glBlendFunc(770, 771);
/*  84 */         GL11.glEnable(3042);
/*  85 */         GL11.glLineWidth(0.5F);
/*  86 */         GL11.glBegin(1);
/*     */         
/*  88 */         GL11.glVertex2f((this.x + xx), (this.y + yy));
/*  89 */         GL11.glVertex2f(mouseX, mouseY);
/*  90 */         GL11.glEnd();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetPosSize() {
/*  96 */       this.x = this.random.nextInt(ParticleGenerator.this.width);
/*  97 */       this.y = this.random.nextInt(ParticleGenerator.this.height);
/*  98 */       this.size = genRandom(1.0F, 4.0F);
/*     */     }
/*     */ 
/*     */     
/*     */     public float genRandom(float min, float max) {
/* 103 */       return (float)(min + Math.random() * (max - min + 1.0F));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\ParticleGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */