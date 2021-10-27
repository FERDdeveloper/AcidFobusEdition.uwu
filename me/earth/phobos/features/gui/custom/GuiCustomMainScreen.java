/*     */ package me.earth.phobos.features.gui.custom;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.net.URI;
/*     */ import me.earth.phobos.Phobos;
/*     */ import me.earth.phobos.util.ParticleGenerator;
/*     */ import me.earth.phobos.util.RenderUtil;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiMultiplayer;
/*     */ import net.minecraft.client.gui.GuiOptions;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiWorldSelection;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiCustomMainScreen extends GuiScreen {
/*  22 */   public static Minecraft mc = Minecraft.func_71410_x();
/*  23 */   public static ParticleGenerator particleGenerator = new ParticleGenerator(100, mc.field_71443_c, mc.field_71440_d);
/*  24 */   private final ResourceLocation resourceLocation = new ResourceLocation("textures/background.png");
/*     */   
/*     */   private int y;
/*     */   private int x;
/*     */   private int singleplayerWidth;
/*     */   private int multiplayerWidth;
/*     */   private int settingsWidth;
/*     */   private int exitWidth;
/*     */   private int textHeight;
/*     */   private float xOffset;
/*     */   private float yOffset;
/*     */   
/*     */   public static void drawCompleteImage(float posX, float posY, float width, float height) {
/*  37 */     GL11.glPushMatrix();
/*  38 */     GL11.glTranslatef(posX, posY, 0.0F);
/*  39 */     GL11.glBegin(7);
/*  40 */     GL11.glTexCoord2f(0.0F, 0.0F);
/*  41 */     GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  42 */     GL11.glTexCoord2f(0.0F, 1.0F);
/*  43 */     GL11.glVertex3f(0.0F, height, 0.0F);
/*  44 */     GL11.glTexCoord2f(1.0F, 1.0F);
/*  45 */     GL11.glVertex3f(width, height, 0.0F);
/*  46 */     GL11.glTexCoord2f(1.0F, 0.0F);
/*  47 */     GL11.glVertex3f(width, 0.0F, 0.0F);
/*  48 */     GL11.glEnd();
/*  49 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
/*  54 */     return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY < y + height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  59 */     this.x = this.field_146294_l / 4;
/*  60 */     this.y = this.field_146295_m / 4 + 48;
/*  61 */     this.field_146292_n.add(new TextButton(0, this.x, this.y + 20, "OneMonke"));
/*  62 */     this.field_146292_n.add(new TextButton(2, this.x, this.y + 44, "MultyMonke"));
/*  63 */     this.field_146292_n.add(new TextButton(2, this.x, this.y + 66, "Settings"));
/*  64 */     this.field_146292_n.add(new TextButton(2, this.x, this.y + 88, "Discord"));
/*  65 */     this.field_146292_n.add(new TextButton(2, this.x, this.y + 132, "Quit"));
/*  66 */     this.field_146292_n.add(new TextButton(2, this.x, this.y + 110, "Website"));
/*  67 */     GlStateManager.func_179090_x();
/*  68 */     GlStateManager.func_179147_l();
/*  69 */     GlStateManager.func_179118_c();
/*  70 */     GlStateManager.func_179103_j(7425);
/*  71 */     GlStateManager.func_179103_j(7424);
/*  72 */     GlStateManager.func_179084_k();
/*  73 */     GlStateManager.func_179141_d();
/*  74 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/*  79 */     super.func_73876_c();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
/*  84 */     if (isHovered(this.x, this.y + 20, Phobos.textManager.getStringWidth("onemonke"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*  85 */       mc.func_147108_a((GuiScreen)new GuiWorldSelection(this));
/*  86 */     } else if (isHovered(this.x, this.y + 44, Phobos.textManager.getStringWidth("multymonke"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*  87 */       mc.func_147108_a((GuiScreen)new GuiMultiplayer(this));
/*  88 */     } else if (isHovered(this.x, this.y + 66, Phobos.textManager.getStringWidth("settings"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*  89 */       mc.func_147108_a((GuiScreen)new GuiOptions(this, mc.field_71474_y));
/*  90 */     } else if (isHovered(this.x, this.y + 132, Phobos.textManager.getStringWidth("quit"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*  91 */       mc.func_71400_g();
/*  92 */     } else if (isHovered(this.x, this.y + 110, Phobos.textManager.getStringWidth("Website"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*     */       try {
/*  94 */         if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
/*  95 */           Desktop.getDesktop().browse(new URI("https://acidclient.ferder.repl.co/"));
/*     */         }
/*  97 */       } catch (Exception e) {
/*  98 */         e.printStackTrace();
/*     */       } 
/* 100 */     } else if (isHovered(this.x, this.y + 88, Phobos.textManager.getStringWidth("discord"), Phobos.textManager.getFontHeight(), mouseX, mouseY)) {
/*     */       try {
/* 102 */         if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
/* 103 */           Desktop.getDesktop().browse(new URI("https://discord.gg/YEJNx7SSfV"));
/*     */         }
/* 105 */       } catch (Exception e) {
/* 106 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 114 */     this.x = this.field_146294_l / 4;
/* 115 */     this.y = this.field_146295_m / 4 + 48;
/* 116 */     GlStateManager.func_179098_w();
/* 117 */     GlStateManager.func_179084_k();
/* 118 */     mc.func_110434_K().func_110577_a(this.resourceLocation);
/* 119 */     drawCompleteImage(-16.0F + this.xOffset, -9.0F + this.yOffset, (this.field_146294_l + 32), (this.field_146295_m + 18));
/* 120 */     particleGenerator.drawParticles(mouseX, mouseY);
/* 121 */     Phobos.textManager.drawStringBig("Acid 1.0.1 :^]", this.x, this.y - 20.0F, Color.white.getRGB(), true);
/* 122 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage parseBackground(BufferedImage background) {
/* 128 */     int width = 1920;
/* 129 */     int srcWidth = background.getWidth();
/* 130 */     int srcHeight = background.getHeight(); int height;
/* 131 */     for (height = 1080; width < srcWidth || height < srcHeight; ) { width *= 2; height *= 2; }
/*     */     
/* 133 */     BufferedImage imgNew = new BufferedImage(width, height, 2);
/* 134 */     Graphics g = imgNew.getGraphics();
/* 135 */     g.drawImage(background, 0, 0, null);
/* 136 */     g.dispose();
/* 137 */     return imgNew;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class TextButton
/*     */     extends GuiButton
/*     */   {
/*     */     public TextButton(int buttonId, int x, int y, String buttonText) {
/* 145 */       super(buttonId, x, y, Phobos.textManager.getStringWidth(buttonText), Phobos.textManager.getFontHeight(), buttonText);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
/* 150 */       if (this.field_146125_m) {
/* 151 */         this.field_146124_l = true;
/* 152 */         this.field_146123_n = (mouseX >= this.field_146128_h - Phobos.textManager.getStringWidth(this.field_146126_j) / 2.0F && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/* 153 */         Phobos.textManager.drawStringWithShadow(this.field_146126_j, this.field_146128_h - Phobos.textManager.getStringWidth(this.field_146126_j) / 2.0F, this.field_146129_i, Color.WHITE.getRGB());
/* 154 */         if (this.field_146123_n) {
/* 155 */           RenderUtil.drawLine(this.field_146128_h - 5.0F - Phobos.textManager.getStringWidth(this.field_146126_j) / 2.0F, (this.field_146129_i + 2 + Phobos.textManager.getFontHeight()), this.field_146128_h + Phobos.textManager.getStringWidth(this.field_146126_j) / 2.0F + 1.0F, (this.field_146129_i + 2 + Phobos.textManager.getFontHeight()), 1.0F, Color.GREEN.getRGB());
/* 156 */           Phobos.textManager.drawStringSmall("Monke", this.field_146128_h, this.field_146129_i - 10.0F, Color.white.getRGB(), false);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 163 */       return (this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h - Phobos.textManager.getStringWidth(this.field_146126_j) / 2.0F && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\custom\GuiCustomMainScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */