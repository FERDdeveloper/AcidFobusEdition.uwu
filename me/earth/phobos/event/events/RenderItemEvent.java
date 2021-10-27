/*     */ package me.earth.phobos.event.events;
/*     */ 
/*     */ import me.earth.phobos.event.EventStage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderItemEvent
/*     */   extends EventStage
/*     */ {
/*     */   double mainX;
/*     */   double mainY;
/*     */   double mainZ;
/*     */   double offX;
/*     */   double offY;
/*     */   double offZ;
/*     */   double mainRotX;
/*     */   double mainRotY;
/*     */   double mainRotZ;
/*     */   
/*     */   public RenderItemEvent(double mainX, double mainY, double mainZ, double offX, double offY, double offZ, double mainRotX, double mainRotY, double mainRotZ, double offRotX, double offRotY, double offRotZ, double mainHandScaleX, double mainHandScaleY, double mainHandScaleZ, double offHandScaleX, double offHandScaleY, double offHandScaleZ) {
/*  22 */     this.mainX = mainX;
/*  23 */     this.mainY = mainY;
/*  24 */     this.mainZ = mainZ;
/*  25 */     this.offX = offX;
/*  26 */     this.offY = offY;
/*  27 */     this.offZ = offZ;
/*  28 */     this.mainRotX = mainRotX;
/*  29 */     this.mainRotY = mainRotY;
/*  30 */     this.mainRotZ = mainRotZ;
/*  31 */     this.offRotX = offRotX;
/*  32 */     this.offRotY = offRotY;
/*  33 */     this.offRotZ = offRotZ;
/*  34 */     this.mainHandScaleX = mainHandScaleX;
/*  35 */     this.mainHandScaleY = mainHandScaleY;
/*  36 */     this.mainHandScaleZ = mainHandScaleZ;
/*     */     
/*  38 */     this.offHandScaleX = offHandScaleX;
/*  39 */     this.offHandScaleY = offHandScaleY;
/*  40 */     this.offHandScaleZ = offHandScaleZ;
/*     */   }
/*     */   double offRotX; double offRotY; double offRotZ; double mainHandScaleX; double mainHandScaleY; double mainHandScaleZ; double offHandScaleX; double offHandScaleY;
/*     */   double offHandScaleZ;
/*     */   
/*     */   public double getMainX() {
/*  46 */     return this.mainX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainX(double v) {
/*  51 */     this.mainX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainY() {
/*  56 */     return this.mainY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainY(double v) {
/*  61 */     this.mainY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainZ() {
/*  66 */     return this.mainZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainZ(double v) {
/*  71 */     this.mainZ = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffX() {
/*  76 */     return this.offX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffX(double v) {
/*  81 */     this.offX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffY() {
/*  86 */     return this.offY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffY(double v) {
/*  91 */     this.offY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffZ() {
/*  96 */     return this.offZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffZ(double v) {
/* 101 */     this.offZ = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainRotX() {
/* 106 */     return this.mainRotX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainRotX(double v) {
/* 111 */     this.mainRotX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainRotY() {
/* 116 */     return this.mainRotY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainRotY(double v) {
/* 121 */     this.mainRotY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainRotZ() {
/* 126 */     return this.mainRotZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainRotZ(double v) {
/* 131 */     this.mainRotZ = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffRotX() {
/* 136 */     return this.offRotX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffRotX(double v) {
/* 141 */     this.offRotX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffRotY() {
/* 146 */     return this.offRotY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffRotY(double v) {
/* 151 */     this.offRotY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffRotZ() {
/* 156 */     return this.offRotZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffRotZ(double v) {
/* 161 */     this.offRotZ = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainHandScaleX() {
/* 166 */     return this.mainHandScaleX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainHandScaleX(double v) {
/* 171 */     this.mainHandScaleX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainHandScaleY() {
/* 176 */     return this.mainHandScaleY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainHandScaleY(double v) {
/* 181 */     this.mainHandScaleY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMainHandScaleZ() {
/* 186 */     return this.mainHandScaleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainHandScaleZ(double v) {
/* 191 */     this.mainHandScaleZ = v;
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
/*     */   public double getOffHandScaleX() {
/* 206 */     return this.offHandScaleX;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffHandScaleX(double v) {
/* 211 */     this.offHandScaleX = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffHandScaleY() {
/* 216 */     return this.offHandScaleY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffHandScaleY(double v) {
/* 221 */     this.offHandScaleY = v;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOffHandScaleZ() {
/* 226 */     return this.offHandScaleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOffHandScaleZ(double v) {
/* 231 */     this.offHandScaleZ = v;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\RenderItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */