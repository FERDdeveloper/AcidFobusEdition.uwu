/*    */ package me.earth.phobos.features.gui.components.items;
/*    */ 
/*    */ import me.earth.phobos.features.Feature;
/*    */ 
/*    */ 
/*    */ public class Item
/*    */   extends Feature
/*    */ {
/*    */   protected float x;
/*    */   protected float y;
/*    */   protected int width;
/*    */   protected int height;
/*    */   private boolean hidden;
/*    */   
/*    */   public Item(String name) {
/* 16 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLocation(float x, float y) {
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int releaseButton) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onKeyTyped(char typedChar, int keyCode) {}
/*    */ 
/*    */   
/*    */   public float getX() {
/* 47 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getY() {
/* 52 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWidth() {
/* 57 */     return this.width;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWidth(int width) {
/* 62 */     this.width = width;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 67 */     return this.height;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHeight(int height) {
/* 72 */     this.height = height;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHidden() {
/* 77 */     return this.hidden;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setHidden(boolean hidden) {
/* 82 */     this.hidden = hidden;
/* 83 */     return this.hidden;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\gui\components\items\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */