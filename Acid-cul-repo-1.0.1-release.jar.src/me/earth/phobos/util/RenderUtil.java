/*      */ package me.earth.phobos.util;
/*      */ import java.awt.Color;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Objects;
/*      */ import me.earth.phobos.Phobos;
/*      */ import me.earth.phobos.features.modules.client.Colors;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.model.ModelBiped;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.culling.Frustum;
/*      */ import net.minecraft.client.renderer.culling.ICamera;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.shader.Framebuffer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.Vec2f;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.world.World;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.EXTFramebufferObject;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.util.glu.Disk;
/*      */ import org.lwjgl.util.glu.Sphere;
/*      */ 
/*      */ public class RenderUtil implements Util {
/*   41 */   private static final Frustum frustrum = new Frustum();
/*   42 */   private static final FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
/*   43 */   private static final IntBuffer viewport = BufferUtils.createIntBuffer(16);
/*   44 */   private static final FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
/*   45 */   private static final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
/*   46 */   public static RenderItem itemRender = mc.func_175599_af();
/*   47 */   public static ICamera camera = (ICamera)new Frustum();
/*   48 */   private static boolean depth = GL11.glIsEnabled(2896);
/*   49 */   private static boolean texture = GL11.glIsEnabled(3042);
/*   50 */   private static boolean clean = GL11.glIsEnabled(3553);
/*   51 */   private static boolean bind = GL11.glIsEnabled(2929);
/*   52 */   private static boolean override = GL11.glIsEnabled(2848);
/*      */   
/*      */   public static void updateModelViewProjectionMatrix() {
/*   55 */     GL11.glGetFloat(2982, modelView);
/*   56 */     GL11.glGetFloat(2983, projection);
/*   57 */     GL11.glGetInteger(2978, viewport);
/*   58 */     ScaledResolution res = new ScaledResolution(Minecraft.func_71410_x());
/*   59 */     GLUProjection.getInstance().updateMatrices(viewport, modelView, projection, (res.func_78326_a() / (Minecraft.func_71410_x()).field_71443_c), (res.func_78328_b() / (Minecraft.func_71410_x()).field_71440_d));
/*      */   }
/*      */   
/*      */   public static void drawRectangleCorrectly(int x, int y, int w, int h, int color) {
/*   63 */     GL11.glLineWidth(1.0F);
/*   64 */     Gui.func_73734_a(x, y, x + w, y + h, color);
/*      */   }
/*      */   
/*      */   public static void drawWaypointImage(BlockPos pos, GLUProjection.Projection projection, Color color, String name, boolean rectangle, Color rectangleColor) {
/*   68 */     GlStateManager.func_179094_E();
/*   69 */     GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0D);
/*   70 */     String text = name + ": " + Math.round(mc.field_71439_g.func_70011_f(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p())) + "M";
/*   71 */     float textWidth = Phobos.textManager.getStringWidth(text);
/*   72 */     Phobos.textManager.drawString(text, -(textWidth / 2.0F), -(Phobos.textManager.getFontHeight() / 2.0F) + Phobos.textManager.getFontHeight() / 2.0F, color.getRGB(), false);
/*   73 */     GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0D);
/*   74 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static AxisAlignedBB interpolateAxis(AxisAlignedBB bb) {
/*   78 */     return new AxisAlignedBB(bb.field_72340_a - (mc.func_175598_ae()).field_78730_l, bb.field_72338_b - (mc.func_175598_ae()).field_78731_m, bb.field_72339_c - (mc.func_175598_ae()).field_78728_n, bb.field_72336_d - (mc.func_175598_ae()).field_78730_l, bb.field_72337_e - (mc.func_175598_ae()).field_78731_m, bb.field_72334_f - (mc.func_175598_ae()).field_78728_n);
/*      */   }
/*      */   
/*      */   public static void drawTexturedRect(int x, int y, int textureX, int textureY, int width, int height, int zLevel) {
/*   82 */     Tessellator tessellator = Tessellator.func_178181_a();
/*   83 */     BufferBuilder BufferBuilder2 = tessellator.func_178180_c();
/*   84 */     BufferBuilder2.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*   85 */     BufferBuilder2.func_181662_b((x + 0), (y + height), zLevel).func_187315_a(((textureX + 0) * 0.00390625F), ((textureY + height) * 0.00390625F)).func_181675_d();
/*   86 */     BufferBuilder2.func_181662_b((x + width), (y + height), zLevel).func_187315_a(((textureX + width) * 0.00390625F), ((textureY + height) * 0.00390625F)).func_181675_d();
/*   87 */     BufferBuilder2.func_181662_b((x + width), (y + 0), zLevel).func_187315_a(((textureX + width) * 0.00390625F), ((textureY + 0) * 0.00390625F)).func_181675_d();
/*   88 */     BufferBuilder2.func_181662_b((x + 0), (y + 0), zLevel).func_187315_a(((textureX + 0) * 0.00390625F), ((textureY + 0) * 0.00390625F)).func_181675_d();
/*   89 */     tessellator.func_78381_a();
/*      */   }
/*      */   
/*      */   public static void drawOpenGradientBox(BlockPos pos, Color startColor, Color endColor, double height) {
/*   93 */     for (EnumFacing face : EnumFacing.values()) {
/*   94 */       if (face != EnumFacing.UP)
/*   95 */         drawGradientPlane(pos, face, startColor, endColor, height); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawClosedGradientBox(BlockPos pos, Color startColor, Color endColor, double height) {
/*  100 */     for (EnumFacing face : EnumFacing.values()) {
/*  101 */       drawGradientPlane(pos, face, startColor, endColor, height);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void drawTricolorGradientBox(BlockPos pos, Color startColor, Color midColor, Color endColor) {
/*  106 */     for (EnumFacing face : EnumFacing.values()) {
/*  107 */       if (face != EnumFacing.UP)
/*  108 */         drawGradientPlane(pos, face, startColor, midColor, true, false); 
/*      */     } 
/*  110 */     for (EnumFacing face : EnumFacing.values()) {
/*  111 */       if (face != EnumFacing.DOWN)
/*  112 */         drawGradientPlane(pos, face, midColor, endColor, true, true); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawGradientPlane(BlockPos pos, EnumFacing face, Color startColor, Color endColor, boolean half, boolean top) {
/*  117 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  118 */     BufferBuilder builder = tessellator.func_178180_c();
/*  119 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/*  120 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/*  121 */     AxisAlignedBB bb = iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c);
/*  122 */     float red = startColor.getRed() / 255.0F;
/*  123 */     float green = startColor.getGreen() / 255.0F;
/*  124 */     float blue = startColor.getBlue() / 255.0F;
/*  125 */     float alpha = startColor.getAlpha() / 255.0F;
/*  126 */     float red1 = endColor.getRed() / 255.0F;
/*  127 */     float green1 = endColor.getGreen() / 255.0F;
/*  128 */     float blue1 = endColor.getBlue() / 255.0F;
/*  129 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  130 */     double x1 = 0.0D;
/*  131 */     double y1 = 0.0D;
/*  132 */     double z1 = 0.0D;
/*  133 */     double x2 = 0.0D;
/*  134 */     double y2 = 0.0D;
/*  135 */     double z2 = 0.0D;
/*  136 */     if (face == EnumFacing.DOWN) {
/*  137 */       x1 = bb.field_72340_a;
/*  138 */       x2 = bb.field_72336_d;
/*  139 */       y1 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  140 */       y2 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  141 */       z1 = bb.field_72339_c;
/*  142 */       z2 = bb.field_72334_f;
/*  143 */     } else if (face == EnumFacing.UP) {
/*  144 */       x1 = bb.field_72340_a;
/*  145 */       x2 = bb.field_72336_d;
/*  146 */       y1 = bb.field_72337_e / (half ? 2 : true);
/*  147 */       y2 = bb.field_72337_e / (half ? 2 : true);
/*  148 */       z1 = bb.field_72339_c;
/*  149 */       z2 = bb.field_72334_f;
/*  150 */     } else if (face == EnumFacing.EAST) {
/*  151 */       x1 = bb.field_72336_d;
/*  152 */       x2 = bb.field_72336_d;
/*  153 */       y1 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  154 */       y2 = bb.field_72337_e / (half ? 2 : true);
/*  155 */       z1 = bb.field_72339_c;
/*  156 */       z2 = bb.field_72334_f;
/*  157 */     } else if (face == EnumFacing.WEST) {
/*  158 */       x1 = bb.field_72340_a;
/*  159 */       x2 = bb.field_72340_a;
/*  160 */       y1 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  161 */       y2 = bb.field_72337_e / (half ? 2 : true);
/*  162 */       z1 = bb.field_72339_c;
/*  163 */       z2 = bb.field_72334_f;
/*  164 */     } else if (face == EnumFacing.SOUTH) {
/*  165 */       x1 = bb.field_72340_a;
/*  166 */       x2 = bb.field_72336_d;
/*  167 */       y1 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  168 */       y2 = bb.field_72337_e / (half ? 2 : true);
/*  169 */       z1 = bb.field_72334_f;
/*  170 */       z2 = bb.field_72334_f;
/*  171 */     } else if (face == EnumFacing.NORTH) {
/*  172 */       x1 = bb.field_72340_a;
/*  173 */       x2 = bb.field_72336_d;
/*  174 */       y1 = bb.field_72338_b + (top ? 0.5D : 0.0D);
/*  175 */       y2 = bb.field_72337_e / (half ? 2 : true);
/*  176 */       z1 = bb.field_72339_c;
/*  177 */       z2 = bb.field_72339_c;
/*      */     } 
/*  179 */     GlStateManager.func_179094_E();
/*  180 */     GlStateManager.func_179097_i();
/*  181 */     GlStateManager.func_179090_x();
/*  182 */     GlStateManager.func_179147_l();
/*  183 */     GlStateManager.func_179118_c();
/*  184 */     GlStateManager.func_179132_a(false);
/*  185 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*  186 */     if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
/*  187 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  188 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  189 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  190 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  191 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  192 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  193 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  194 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  195 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  196 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  197 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  198 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  199 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  200 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  201 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  202 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  203 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  204 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  205 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  206 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  207 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  208 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  209 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  210 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  211 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  212 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  213 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  214 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  215 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  216 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  217 */     } else if (face == EnumFacing.UP) {
/*  218 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  219 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  220 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  221 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  222 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  223 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  224 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  225 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  226 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  227 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  228 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  229 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  230 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  231 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  232 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  233 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  234 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  235 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  236 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  237 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  238 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  239 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  240 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  241 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  242 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  243 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  244 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  245 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  246 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  247 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  248 */     } else if (face == EnumFacing.DOWN) {
/*  249 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  250 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  251 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  252 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  253 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  254 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  255 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  256 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  257 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  258 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  259 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  260 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  261 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  262 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  263 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  264 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  265 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  266 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  267 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  268 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  269 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  270 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  271 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  272 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  273 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  274 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  275 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  276 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  277 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  278 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*      */     } 
/*  280 */     tessellator.func_78381_a();
/*  281 */     GlStateManager.func_179132_a(true);
/*  282 */     GlStateManager.func_179084_k();
/*  283 */     GlStateManager.func_179141_d();
/*  284 */     GlStateManager.func_179098_w();
/*  285 */     GlStateManager.func_179126_j();
/*  286 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawGradientPlane(BlockPos pos, EnumFacing face, Color startColor, Color endColor, double height) {
/*  290 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  291 */     BufferBuilder builder = tessellator.func_178180_c();
/*  292 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/*  293 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/*  294 */     AxisAlignedBB bb = iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c).func_72321_a(0.0D, height, 0.0D);
/*  295 */     float red = startColor.getRed() / 255.0F;
/*  296 */     float green = startColor.getGreen() / 255.0F;
/*  297 */     float blue = startColor.getBlue() / 255.0F;
/*  298 */     float alpha = startColor.getAlpha() / 255.0F;
/*  299 */     float red1 = endColor.getRed() / 255.0F;
/*  300 */     float green1 = endColor.getGreen() / 255.0F;
/*  301 */     float blue1 = endColor.getBlue() / 255.0F;
/*  302 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  303 */     double x1 = 0.0D;
/*  304 */     double y1 = 0.0D;
/*  305 */     double z1 = 0.0D;
/*  306 */     double x2 = 0.0D;
/*  307 */     double y2 = 0.0D;
/*  308 */     double z2 = 0.0D;
/*  309 */     if (face == EnumFacing.DOWN) {
/*  310 */       x1 = bb.field_72340_a;
/*  311 */       x2 = bb.field_72336_d;
/*  312 */       y1 = bb.field_72338_b;
/*  313 */       y2 = bb.field_72338_b;
/*  314 */       z1 = bb.field_72339_c;
/*  315 */       z2 = bb.field_72334_f;
/*  316 */     } else if (face == EnumFacing.UP) {
/*  317 */       x1 = bb.field_72340_a;
/*  318 */       x2 = bb.field_72336_d;
/*  319 */       y1 = bb.field_72337_e;
/*  320 */       y2 = bb.field_72337_e;
/*  321 */       z1 = bb.field_72339_c;
/*  322 */       z2 = bb.field_72334_f;
/*  323 */     } else if (face == EnumFacing.EAST) {
/*  324 */       x1 = bb.field_72336_d;
/*  325 */       x2 = bb.field_72336_d;
/*  326 */       y1 = bb.field_72338_b;
/*  327 */       y2 = bb.field_72337_e;
/*  328 */       z1 = bb.field_72339_c;
/*  329 */       z2 = bb.field_72334_f;
/*  330 */     } else if (face == EnumFacing.WEST) {
/*  331 */       x1 = bb.field_72340_a;
/*  332 */       x2 = bb.field_72340_a;
/*  333 */       y1 = bb.field_72338_b;
/*  334 */       y2 = bb.field_72337_e;
/*  335 */       z1 = bb.field_72339_c;
/*  336 */       z2 = bb.field_72334_f;
/*  337 */     } else if (face == EnumFacing.SOUTH) {
/*  338 */       x1 = bb.field_72340_a;
/*  339 */       x2 = bb.field_72336_d;
/*  340 */       y1 = bb.field_72338_b;
/*  341 */       y2 = bb.field_72337_e;
/*  342 */       z1 = bb.field_72334_f;
/*  343 */       z2 = bb.field_72334_f;
/*  344 */     } else if (face == EnumFacing.NORTH) {
/*  345 */       x1 = bb.field_72340_a;
/*  346 */       x2 = bb.field_72336_d;
/*  347 */       y1 = bb.field_72338_b;
/*  348 */       y2 = bb.field_72337_e;
/*  349 */       z1 = bb.field_72339_c;
/*  350 */       z2 = bb.field_72339_c;
/*      */     } 
/*  352 */     GlStateManager.func_179094_E();
/*  353 */     GlStateManager.func_179097_i();
/*  354 */     GlStateManager.func_179090_x();
/*  355 */     GlStateManager.func_179147_l();
/*  356 */     GlStateManager.func_179118_c();
/*  357 */     GlStateManager.func_179132_a(false);
/*  358 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*  359 */     if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
/*  360 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  361 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  362 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  363 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  364 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  365 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  366 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  367 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  368 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  369 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  370 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  371 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  372 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  373 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  374 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  375 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  376 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  377 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  378 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  379 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  380 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  381 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  382 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  383 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  384 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  385 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  386 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  387 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  388 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  389 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  390 */     } else if (face == EnumFacing.UP) {
/*  391 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  392 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  393 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  394 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  395 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  396 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  397 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  398 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  399 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  400 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  401 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  402 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  403 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  404 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  405 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  406 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  407 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  408 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  409 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  410 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  411 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  412 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  413 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  414 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  415 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  416 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  417 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  418 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  419 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  420 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  421 */     } else if (face == EnumFacing.DOWN) {
/*  422 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  423 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  424 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  425 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  426 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  427 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  428 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  429 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  430 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  431 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  432 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  433 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  434 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  435 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  436 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  437 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  438 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  439 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  440 */       builder.func_181662_b(x1, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  441 */       builder.func_181662_b(x2, y1, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  442 */       builder.func_181662_b(x1, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  443 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  444 */       builder.func_181662_b(x2, y1, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  445 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  446 */       builder.func_181662_b(x1, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  447 */       builder.func_181662_b(x1, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  448 */       builder.func_181662_b(x2, y2, z1).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  449 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  450 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  451 */       builder.func_181662_b(x2, y2, z2).func_181666_a(red, green, blue, alpha).func_181675_d();
/*      */     } 
/*  453 */     tessellator.func_78381_a();
/*  454 */     GlStateManager.func_179132_a(true);
/*  455 */     GlStateManager.func_179084_k();
/*  456 */     GlStateManager.func_179141_d();
/*  457 */     GlStateManager.func_179098_w();
/*  458 */     GlStateManager.func_179126_j();
/*  459 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawGradientRect(int x, int y, int w, int h, int startColor, int endColor) {
/*  463 */     float f = (startColor >> 24 & 0xFF) / 255.0F;
/*  464 */     float f1 = (startColor >> 16 & 0xFF) / 255.0F;
/*  465 */     float f2 = (startColor >> 8 & 0xFF) / 255.0F;
/*  466 */     float f3 = (startColor & 0xFF) / 255.0F;
/*  467 */     float f4 = (endColor >> 24 & 0xFF) / 255.0F;
/*  468 */     float f5 = (endColor >> 16 & 0xFF) / 255.0F;
/*  469 */     float f6 = (endColor >> 8 & 0xFF) / 255.0F;
/*  470 */     float f7 = (endColor & 0xFF) / 255.0F;
/*  471 */     GlStateManager.func_179090_x();
/*  472 */     GlStateManager.func_179147_l();
/*  473 */     GlStateManager.func_179118_c();
/*  474 */     GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  475 */     GlStateManager.func_179103_j(7425);
/*  476 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  477 */     BufferBuilder vertexbuffer = tessellator.func_178180_c();
/*  478 */     vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/*  479 */     vertexbuffer.func_181662_b(x + w, y, 0.0D).func_181666_a(f1, f2, f3, f).func_181675_d();
/*  480 */     vertexbuffer.func_181662_b(x, y, 0.0D).func_181666_a(f1, f2, f3, f).func_181675_d();
/*  481 */     vertexbuffer.func_181662_b(x, y + h, 0.0D).func_181666_a(f5, f6, f7, f4).func_181675_d();
/*  482 */     vertexbuffer.func_181662_b(x + w, y + h, 0.0D).func_181666_a(f5, f6, f7, f4).func_181675_d();
/*  483 */     tessellator.func_78381_a();
/*  484 */     GlStateManager.func_179103_j(7424);
/*  485 */     GlStateManager.func_179084_k();
/*  486 */     GlStateManager.func_179141_d();
/*  487 */     GlStateManager.func_179098_w();
/*      */   }
/*      */   
/*      */   public static void drawGradientBlockOutline(BlockPos pos, Color startColor, Color endColor, float linewidth, double height) {
/*  491 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/*  492 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/*  493 */     drawGradientBlockOutline(iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c).func_72321_a(0.0D, height, 0.0D), startColor, endColor, linewidth);
/*      */   }
/*      */   
/*      */   public static void drawProperGradientBlockOutline(BlockPos pos, Color startColor, Color midColor, Color endColor, float linewidth) {
/*  497 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/*  498 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/*  499 */     drawProperGradientBlockOutline(iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), startColor, midColor, endColor, linewidth);
/*      */   }
/*      */   
/*      */   public static void drawProperGradientBlockOutline(AxisAlignedBB bb, Color startColor, Color midColor, Color endColor, float linewidth) {
/*  503 */     float red = endColor.getRed() / 255.0F;
/*  504 */     float green = endColor.getGreen() / 255.0F;
/*  505 */     float blue = endColor.getBlue() / 255.0F;
/*  506 */     float alpha = endColor.getAlpha() / 255.0F;
/*  507 */     float red1 = midColor.getRed() / 255.0F;
/*  508 */     float green1 = midColor.getGreen() / 255.0F;
/*  509 */     float blue1 = midColor.getBlue() / 255.0F;
/*  510 */     float alpha1 = midColor.getAlpha() / 255.0F;
/*  511 */     float red2 = startColor.getRed() / 255.0F;
/*  512 */     float green2 = startColor.getGreen() / 255.0F;
/*  513 */     float blue2 = startColor.getBlue() / 255.0F;
/*  514 */     float alpha2 = startColor.getAlpha() / 255.0F;
/*  515 */     double dif = (bb.field_72337_e - bb.field_72338_b) / 2.0D;
/*  516 */     GlStateManager.func_179094_E();
/*  517 */     GlStateManager.func_179147_l();
/*  518 */     GlStateManager.func_179097_i();
/*  519 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/*  520 */     GlStateManager.func_179090_x();
/*  521 */     GlStateManager.func_179132_a(false);
/*  522 */     GL11.glEnable(2848);
/*  523 */     GL11.glHint(3154, 4354);
/*  524 */     GL11.glLineWidth(linewidth);
/*  525 */     GL11.glBegin(1);
/*  526 */     GL11.glColor4d(red, green, blue, alpha);
/*  527 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
/*  528 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
/*  529 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
/*  530 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
/*  531 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
/*  532 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
/*  533 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
/*  534 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
/*  535 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
/*  536 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  537 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72339_c);
/*  538 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72339_c);
/*  539 */     GL11.glColor4f(red2, green2, blue2, alpha2);
/*  540 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
/*  541 */     GL11.glColor4d(red, green, blue, alpha);
/*  542 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
/*  543 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  544 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72334_f);
/*  545 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b + dif, bb.field_72334_f);
/*  546 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  547 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
/*  548 */     GL11.glColor4d(red, green, blue, alpha);
/*  549 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
/*  550 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  551 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72334_f);
/*  552 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72334_f);
/*  553 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  554 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
/*  555 */     GL11.glColor4d(red, green, blue, alpha);
/*  556 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
/*  557 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  558 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72339_c);
/*  559 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b + dif, bb.field_72339_c);
/*  560 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  561 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
/*  562 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  563 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
/*  564 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
/*  565 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
/*  566 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
/*  567 */     GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
/*  568 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
/*  569 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
/*  570 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
/*  571 */     GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
/*  572 */     GL11.glEnd();
/*  573 */     GL11.glDisable(2848);
/*  574 */     GlStateManager.func_179132_a(true);
/*  575 */     GlStateManager.func_179126_j();
/*  576 */     GlStateManager.func_179098_w();
/*  577 */     GlStateManager.func_179084_k();
/*  578 */     GlStateManager.func_179121_F();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawSexyBoxPhobosIsRetardedFuckYouESP(AxisAlignedBB a, Color boxColor, Color outlineColor, float lineWidth, boolean outline, boolean box, boolean colorSync, float alpha, float scale, float slab) {
/*  583 */     double f = 0.5D * (1.0F - scale);
/*  584 */     AxisAlignedBB bb = interpolateAxis(new AxisAlignedBB(a.field_72340_a + f, a.field_72338_b + f + (1.0F - slab), a.field_72339_c + f, a.field_72336_d - f, a.field_72337_e - f, a.field_72334_f - f));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  592 */     float rB = boxColor.getRed() / 255.0F;
/*  593 */     float gB = boxColor.getGreen() / 255.0F;
/*  594 */     float bB = boxColor.getBlue() / 255.0F;
/*  595 */     float aB = boxColor.getAlpha() / 255.0F;
/*  596 */     float rO = outlineColor.getRed() / 255.0F;
/*  597 */     float gO = outlineColor.getGreen() / 255.0F;
/*  598 */     float bO = outlineColor.getBlue() / 255.0F;
/*  599 */     float aO = outlineColor.getAlpha() / 255.0F;
/*  600 */     if (colorSync) {
/*  601 */       rB = Colors.INSTANCE.getCurrentColor().getRed() / 255.0F;
/*  602 */       gB = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0F;
/*  603 */       bB = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0F;
/*  604 */       rO = Colors.INSTANCE.getCurrentColor().getRed() / 255.0F;
/*  605 */       gO = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0F;
/*  606 */       bO = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0F;
/*      */     } 
/*  608 */     if (alpha > 1.0F) alpha = 1.0F; 
/*  609 */     aB *= alpha;
/*  610 */     aO *= alpha;
/*  611 */     if (box) {
/*  612 */       GlStateManager.func_179094_E();
/*  613 */       GlStateManager.func_179147_l();
/*  614 */       GlStateManager.func_179097_i();
/*  615 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/*  616 */       GlStateManager.func_179090_x();
/*  617 */       GlStateManager.func_179132_a(false);
/*  618 */       GL11.glEnable(2848);
/*  619 */       GL11.glHint(3154, 4354);
/*  620 */       RenderGlobal.func_189696_b(bb, rB, gB, bB, aB);
/*  621 */       GL11.glDisable(2848);
/*  622 */       GlStateManager.func_179132_a(true);
/*  623 */       GlStateManager.func_179126_j();
/*  624 */       GlStateManager.func_179098_w();
/*  625 */       GlStateManager.func_179084_k();
/*  626 */       GlStateManager.func_179121_F();
/*      */     } 
/*  628 */     if (outline) {
/*  629 */       GlStateManager.func_179094_E();
/*  630 */       GlStateManager.func_179147_l();
/*  631 */       GlStateManager.func_179097_i();
/*  632 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/*  633 */       GlStateManager.func_179090_x();
/*  634 */       GlStateManager.func_179132_a(false);
/*  635 */       GL11.glEnable(2848);
/*  636 */       GL11.glHint(3154, 4354);
/*  637 */       GL11.glLineWidth(lineWidth);
/*  638 */       Tessellator tessellator = Tessellator.func_178181_a();
/*  639 */       BufferBuilder bufferbuilder = tessellator.func_178180_c();
/*  640 */       bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/*  641 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  642 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  643 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  644 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  645 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  646 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  647 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  648 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  649 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  650 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  651 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  652 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  653 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  654 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  655 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  656 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(rO, gO, bO, aO).func_181675_d();
/*  657 */       tessellator.func_78381_a();
/*  658 */       GL11.glDisable(2848);
/*  659 */       GlStateManager.func_179132_a(true);
/*  660 */       GlStateManager.func_179126_j();
/*  661 */       GlStateManager.func_179098_w();
/*  662 */       GlStateManager.func_179084_k();
/*  663 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawGradientBlockOutline(AxisAlignedBB bb, Color startColor, Color endColor, float linewidth) {
/*  668 */     float red = startColor.getRed() / 255.0F;
/*  669 */     float green = startColor.getGreen() / 255.0F;
/*  670 */     float blue = startColor.getBlue() / 255.0F;
/*  671 */     float alpha = startColor.getAlpha() / 255.0F;
/*  672 */     float red1 = endColor.getRed() / 255.0F;
/*  673 */     float green1 = endColor.getGreen() / 255.0F;
/*  674 */     float blue1 = endColor.getBlue() / 255.0F;
/*  675 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  676 */     GlStateManager.func_179094_E();
/*  677 */     GlStateManager.func_179147_l();
/*  678 */     GlStateManager.func_179097_i();
/*  679 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/*  680 */     GlStateManager.func_179090_x();
/*  681 */     GlStateManager.func_179132_a(false);
/*  682 */     GL11.glEnable(2848);
/*  683 */     GL11.glHint(3154, 4354);
/*  684 */     GL11.glLineWidth(linewidth);
/*  685 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  686 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/*  687 */     bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/*  688 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  689 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  690 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  691 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  692 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  693 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  694 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  695 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  696 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  697 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  698 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  699 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  700 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  701 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  702 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  703 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  704 */     tessellator.func_78381_a();
/*  705 */     GL11.glDisable(2848);
/*  706 */     GlStateManager.func_179132_a(true);
/*  707 */     GlStateManager.func_179126_j();
/*  708 */     GlStateManager.func_179098_w();
/*  709 */     GlStateManager.func_179084_k();
/*  710 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawGradientFilledBox(BlockPos pos, Color startColor, Color endColor) {
/*  714 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/*  715 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/*  716 */     drawGradientFilledBox(iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), startColor, endColor);
/*      */   }
/*      */   
/*      */   public static void drawGradientFilledBox(AxisAlignedBB bb, Color startColor, Color endColor) {
/*  720 */     GlStateManager.func_179094_E();
/*  721 */     GlStateManager.func_179147_l();
/*  722 */     GlStateManager.func_179097_i();
/*  723 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/*  724 */     GlStateManager.func_179090_x();
/*  725 */     GlStateManager.func_179132_a(false);
/*  726 */     float alpha = endColor.getAlpha() / 255.0F;
/*  727 */     float red = endColor.getRed() / 255.0F;
/*  728 */     float green = endColor.getGreen() / 255.0F;
/*  729 */     float blue = endColor.getBlue() / 255.0F;
/*  730 */     float alpha1 = startColor.getAlpha() / 255.0F;
/*  731 */     float red1 = startColor.getRed() / 255.0F;
/*  732 */     float green1 = startColor.getGreen() / 255.0F;
/*  733 */     float blue1 = startColor.getBlue() / 255.0F;
/*  734 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  735 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/*  736 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/*  737 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  738 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  739 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  740 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  741 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  742 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  743 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  744 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  745 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  746 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  747 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  748 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  749 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  750 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  751 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  752 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  753 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  754 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  755 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  756 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  757 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  758 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  759 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  760 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  761 */     tessellator.func_78381_a();
/*  762 */     GlStateManager.func_179132_a(true);
/*  763 */     GlStateManager.func_179126_j();
/*  764 */     GlStateManager.func_179098_w();
/*  765 */     GlStateManager.func_179084_k();
/*  766 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawGradientRect(float x, float y, float w, float h, int startColor, int endColor) {
/*  770 */     float f = (startColor >> 24 & 0xFF) / 255.0F;
/*  771 */     float f1 = (startColor >> 16 & 0xFF) / 255.0F;
/*  772 */     float f2 = (startColor >> 8 & 0xFF) / 255.0F;
/*  773 */     float f3 = (startColor & 0xFF) / 255.0F;
/*  774 */     float f4 = (endColor >> 24 & 0xFF) / 255.0F;
/*  775 */     float f5 = (endColor >> 16 & 0xFF) / 255.0F;
/*  776 */     float f6 = (endColor >> 8 & 0xFF) / 255.0F;
/*  777 */     float f7 = (endColor & 0xFF) / 255.0F;
/*  778 */     GlStateManager.func_179090_x();
/*  779 */     GlStateManager.func_179147_l();
/*  780 */     GlStateManager.func_179118_c();
/*  781 */     GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  782 */     GlStateManager.func_179103_j(7425);
/*  783 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  784 */     BufferBuilder vertexbuffer = tessellator.func_178180_c();
/*  785 */     vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/*  786 */     vertexbuffer.func_181662_b(x + w, y, 0.0D).func_181666_a(f1, f2, f3, f).func_181675_d();
/*  787 */     vertexbuffer.func_181662_b(x, y, 0.0D).func_181666_a(f1, f2, f3, f).func_181675_d();
/*  788 */     vertexbuffer.func_181662_b(x, y + h, 0.0D).func_181666_a(f5, f6, f7, f4).func_181675_d();
/*  789 */     vertexbuffer.func_181662_b(x + w, y + h, 0.0D).func_181666_a(f5, f6, f7, f4).func_181675_d();
/*  790 */     tessellator.func_78381_a();
/*  791 */     GlStateManager.func_179103_j(7424);
/*  792 */     GlStateManager.func_179084_k();
/*  793 */     GlStateManager.func_179141_d();
/*  794 */     GlStateManager.func_179098_w();
/*      */   }
/*      */   
/*      */   public static void drawFilledCircle(double x, double y, double z, Color color, double radius) {
/*  798 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  799 */     BufferBuilder builder = tessellator.func_178180_c();
/*  800 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawGradientBoxTest(BlockPos pos, Color startColor, Color endColor) {}
/*      */   
/*      */   public static void blockESP(BlockPos b, Color c, double length, double length2) {
/*  807 */     blockEsp(b, c, length, length2);
/*      */   }
/*      */   
/*      */   public static void drawBoxESP(BlockPos pos, Color color, boolean secondC, Color secondColor, float lineWidth, boolean outline, boolean box, int boxAlpha, boolean air) {
/*  811 */     if (box) {
/*  812 */       drawBox(pos, new Color(color.getRed(), color.getGreen(), color.getBlue(), boxAlpha));
/*      */     }
/*  814 */     if (outline) {
/*  815 */       drawBlockOutline(pos, secondC ? secondColor : color, lineWidth, air);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void drawBoxESP(BlockPos pos, Color color, boolean secondC, Color secondColor, float lineWidth, boolean outline, boolean box, int boxAlpha, boolean air, double height, boolean gradientBox, boolean gradientOutline, boolean invertGradientBox, boolean invertGradientOutline, int gradientAlpha) {
/*  820 */     if (box) {
/*  821 */       drawBox(pos, new Color(color.getRed(), color.getGreen(), color.getBlue(), boxAlpha), height, gradientBox, invertGradientBox, gradientAlpha);
/*      */     }
/*  823 */     if (outline) {
/*  824 */       drawBlockOutline(pos, secondC ? secondColor : color, lineWidth, air, height, gradientOutline, invertGradientOutline, gradientAlpha);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void glScissor(float x, float y, float x1, float y1, ScaledResolution sr) {
/*  829 */     GL11.glScissor((int)(x * sr.func_78325_e()), (int)(mc.field_71440_d - y1 * sr.func_78325_e()), (int)((x1 - x) * sr.func_78325_e()), (int)((y1 - y) * sr.func_78325_e()));
/*      */   }
/*      */   
/*      */   public static void drawLine(float x, float y, float x1, float y1, float thickness, int hex) {
/*  833 */     float red = (hex >> 16 & 0xFF) / 255.0F;
/*  834 */     float green = (hex >> 8 & 0xFF) / 255.0F;
/*  835 */     float blue = (hex & 0xFF) / 255.0F;
/*  836 */     float alpha = (hex >> 24 & 0xFF) / 255.0F;
/*  837 */     GlStateManager.func_179094_E();
/*  838 */     GlStateManager.func_179090_x();
/*  839 */     GlStateManager.func_179147_l();
/*  840 */     GlStateManager.func_179118_c();
/*  841 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/*  842 */     GlStateManager.func_179103_j(7425);
/*  843 */     GL11.glLineWidth(thickness);
/*  844 */     GL11.glEnable(2848);
/*  845 */     GL11.glHint(3154, 4354);
/*  846 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  847 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/*  848 */     bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/*  849 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  850 */     bufferbuilder.func_181662_b(x1, y1, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  851 */     tessellator.func_78381_a();
/*  852 */     GlStateManager.func_179103_j(7424);
/*  853 */     GL11.glDisable(2848);
/*  854 */     GlStateManager.func_179084_k();
/*  855 */     GlStateManager.func_179141_d();
/*  856 */     GlStateManager.func_179098_w();
/*  857 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawBox(BlockPos pos, Color color) {
/*  861 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/*  862 */     camera.func_78547_a(((Entity)Objects.requireNonNull((T)mc.func_175606_aa())).field_70165_t, (mc.func_175606_aa()).field_70163_u, (mc.func_175606_aa()).field_70161_v);
/*  863 */     if (camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + (mc.func_175598_ae()).field_78730_l, bb.field_72338_b + (mc.func_175598_ae()).field_78731_m, bb.field_72339_c + (mc.func_175598_ae()).field_78728_n, bb.field_72336_d + (mc.func_175598_ae()).field_78730_l, bb.field_72337_e + (mc.func_175598_ae()).field_78731_m, bb.field_72334_f + (mc.func_175598_ae()).field_78728_n))) {
/*  864 */       GlStateManager.func_179094_E();
/*  865 */       GlStateManager.func_179147_l();
/*  866 */       GlStateManager.func_179097_i();
/*  867 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/*  868 */       GlStateManager.func_179090_x();
/*  869 */       GlStateManager.func_179132_a(false);
/*  870 */       GL11.glEnable(2848);
/*  871 */       GL11.glHint(3154, 4354);
/*  872 */       RenderGlobal.func_189696_b(bb, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*  873 */       GL11.glDisable(2848);
/*  874 */       GlStateManager.func_179132_a(true);
/*  875 */       GlStateManager.func_179126_j();
/*  876 */       GlStateManager.func_179098_w();
/*  877 */       GlStateManager.func_179084_k();
/*  878 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawBetterGradientBox(BlockPos pos, Color startColor, Color endColor) {
/*  883 */     float red = startColor.getRed() / 255.0F;
/*  884 */     float green = startColor.getGreen() / 255.0F;
/*  885 */     float blue = startColor.getBlue() / 255.0F;
/*  886 */     float alpha = startColor.getAlpha() / 255.0F;
/*  887 */     float red1 = endColor.getRed() / 255.0F;
/*  888 */     float green1 = endColor.getGreen() / 255.0F;
/*  889 */     float blue1 = endColor.getBlue() / 255.0F;
/*  890 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  891 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/*  892 */     double offset = (bb.field_72337_e - bb.field_72338_b) / 2.0D;
/*  893 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  894 */     BufferBuilder builder = tessellator.func_178180_c();
/*  895 */     GlStateManager.func_179094_E();
/*  896 */     GlStateManager.func_179147_l();
/*  897 */     GlStateManager.func_179097_i();
/*  898 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/*  899 */     GlStateManager.func_179090_x();
/*  900 */     GlStateManager.func_179132_a(false);
/*  901 */     GL11.glEnable(2848);
/*  902 */     GL11.glHint(3154, 4354);
/*  903 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*  904 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  905 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  906 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  907 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  908 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  909 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  910 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  911 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  912 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  913 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  914 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  915 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  916 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  917 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  918 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  919 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  920 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  921 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  922 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  923 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  924 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  925 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  926 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  927 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  928 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  929 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  930 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  931 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  932 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  933 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*      */   }
/*      */   
/*      */   public static void drawBetterGradientBox(BlockPos pos, Color startColor, Color midColor, Color endColor) {
/*  937 */     float red = startColor.getRed() / 255.0F;
/*  938 */     float green = startColor.getGreen() / 255.0F;
/*  939 */     float blue = startColor.getBlue() / 255.0F;
/*  940 */     float alpha = startColor.getAlpha() / 255.0F;
/*  941 */     float red1 = endColor.getRed() / 255.0F;
/*  942 */     float green1 = endColor.getGreen() / 255.0F;
/*  943 */     float blue1 = endColor.getBlue() / 255.0F;
/*  944 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  945 */     float red2 = midColor.getRed() / 255.0F;
/*  946 */     float green2 = midColor.getGreen() / 255.0F;
/*  947 */     float blue2 = midColor.getBlue() / 255.0F;
/*  948 */     float alpha2 = midColor.getAlpha() / 255.0F;
/*  949 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/*  950 */     double offset = (bb.field_72337_e - bb.field_72338_b) / 2.0D;
/*  951 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  952 */     BufferBuilder builder = tessellator.func_178180_c();
/*  953 */     GlStateManager.func_179094_E();
/*  954 */     GlStateManager.func_179147_l();
/*  955 */     GlStateManager.func_179097_i();
/*  956 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/*  957 */     GlStateManager.func_179090_x();
/*  958 */     GlStateManager.func_179132_a(false);
/*  959 */     GL11.glEnable(2848);
/*  960 */     GL11.glHint(3154, 4354);
/*  961 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/*  962 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  963 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  964 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  965 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  966 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  967 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  968 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  969 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  970 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  971 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/*  972 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  973 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  974 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72339_c).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  975 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  976 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  977 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  978 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  979 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  980 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/*  981 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b + offset, bb.field_72334_f).func_181666_a(red2, green2, blue2, alpha2).func_181675_d();
/*  982 */     tessellator.func_78381_a();
/*  983 */     GL11.glDisable(2848);
/*  984 */     GlStateManager.func_179132_a(true);
/*  985 */     GlStateManager.func_179126_j();
/*  986 */     GlStateManager.func_179098_w();
/*  987 */     GlStateManager.func_179084_k();
/*  988 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawEvenBetterGradientBox(BlockPos pos, Color startColor, Color midColor, Color endColor) {
/*  992 */     float red = startColor.getRed() / 255.0F;
/*  993 */     float green = startColor.getGreen() / 255.0F;
/*  994 */     float blue = startColor.getBlue() / 255.0F;
/*  995 */     float alpha = startColor.getAlpha() / 255.0F;
/*  996 */     float red1 = endColor.getRed() / 255.0F;
/*  997 */     float green1 = endColor.getGreen() / 255.0F;
/*  998 */     float blue1 = endColor.getBlue() / 255.0F;
/*  999 */     float alpha1 = endColor.getAlpha() / 255.0F;
/* 1000 */     float red2 = midColor.getRed() / 255.0F;
/* 1001 */     float green2 = midColor.getGreen() / 255.0F;
/* 1002 */     float blue2 = midColor.getBlue() / 255.0F;
/* 1003 */     float alpha2 = midColor.getAlpha() / 255.0F;
/* 1004 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/* 1005 */     double offset = (bb.field_72337_e - bb.field_72338_b) / 2.0D;
/* 1006 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1007 */     BufferBuilder builder = tessellator.func_178180_c();
/* 1008 */     GlStateManager.func_179094_E();
/* 1009 */     GlStateManager.func_179147_l();
/* 1010 */     GlStateManager.func_179097_i();
/* 1011 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1012 */     GlStateManager.func_179090_x();
/* 1013 */     GlStateManager.func_179132_a(false);
/* 1014 */     GL11.glEnable(2848);
/* 1015 */     GL11.glHint(3154, 4354);
/* 1016 */     builder.func_181668_a(5, DefaultVertexFormats.field_181706_f);
/* 1017 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1018 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1019 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1020 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1021 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1022 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1023 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1024 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1025 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1026 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1027 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1028 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1029 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1030 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1031 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1032 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1033 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1034 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1035 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1036 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1037 */     builder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1038 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1039 */     builder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red1, green1, blue1, alpha1).func_181675_d();
/* 1040 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1041 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1042 */     builder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1043 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1044 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1045 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1046 */     builder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1047 */     tessellator.func_78381_a();
/* 1048 */     GL11.glDisable(2848);
/* 1049 */     GlStateManager.func_179132_a(true);
/* 1050 */     GlStateManager.func_179126_j();
/* 1051 */     GlStateManager.func_179098_w();
/* 1052 */     GlStateManager.func_179084_k();
/* 1053 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawBox(BlockPos pos, Color color, double height, boolean gradient, boolean invert, int alpha) {
/* 1057 */     if (gradient) {
/* 1058 */       Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
/* 1059 */       drawOpenGradientBox(pos, invert ? endColor : color, invert ? color : endColor, height);
/*      */       return;
/*      */     } 
/* 1062 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m + height, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/* 1063 */     camera.func_78547_a(((Entity)Objects.requireNonNull((T)mc.func_175606_aa())).field_70165_t, (mc.func_175606_aa()).field_70163_u, (mc.func_175606_aa()).field_70161_v);
/* 1064 */     if (camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + (mc.func_175598_ae()).field_78730_l, bb.field_72338_b + (mc.func_175598_ae()).field_78731_m, bb.field_72339_c + (mc.func_175598_ae()).field_78728_n, bb.field_72336_d + (mc.func_175598_ae()).field_78730_l, bb.field_72337_e + (mc.func_175598_ae()).field_78731_m, bb.field_72334_f + (mc.func_175598_ae()).field_78728_n))) {
/* 1065 */       GlStateManager.func_179094_E();
/* 1066 */       GlStateManager.func_179147_l();
/* 1067 */       GlStateManager.func_179097_i();
/* 1068 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1069 */       GlStateManager.func_179090_x();
/* 1070 */       GlStateManager.func_179132_a(false);
/* 1071 */       GL11.glEnable(2848);
/* 1072 */       GL11.glHint(3154, 4354);
/* 1073 */       RenderGlobal.func_189696_b(bb, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 1074 */       GL11.glDisable(2848);
/* 1075 */       GlStateManager.func_179132_a(true);
/* 1076 */       GlStateManager.func_179126_j();
/* 1077 */       GlStateManager.func_179098_w();
/* 1078 */       GlStateManager.func_179084_k();
/* 1079 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawBlockOutline(BlockPos pos, Color color, float linewidth, boolean air) {
/* 1084 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/* 1085 */     if ((air || iblockstate.func_185904_a() != Material.field_151579_a) && mc.field_71441_e.func_175723_af().func_177746_a(pos)) {
/* 1086 */       Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/* 1087 */       drawBlockOutline(iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), color, linewidth);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawBlockOutline(BlockPos pos, Color color, float linewidth, boolean air, double height, boolean gradient, boolean invert, int alpha) {
/* 1092 */     if (gradient) {
/* 1093 */       Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
/* 1094 */       drawGradientBlockOutline(pos, invert ? endColor : color, invert ? color : endColor, linewidth, height);
/*      */       return;
/*      */     } 
/* 1097 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/* 1098 */     if ((air || iblockstate.func_185904_a() != Material.field_151579_a) && mc.field_71441_e.func_175723_af().func_177746_a(pos)) {
/* 1099 */       AxisAlignedBB blockAxis = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m + height, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/* 1100 */       drawBlockOutline(blockAxis.func_186662_g(0.0020000000949949026D), color, linewidth);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawBlockOutline(AxisAlignedBB bb, Color color, float linewidth) {
/* 1105 */     float red = color.getRed() / 255.0F;
/* 1106 */     float green = color.getGreen() / 255.0F;
/* 1107 */     float blue = color.getBlue() / 255.0F;
/* 1108 */     float alpha = color.getAlpha() / 255.0F;
/* 1109 */     GlStateManager.func_179094_E();
/* 1110 */     GlStateManager.func_179147_l();
/* 1111 */     GlStateManager.func_179097_i();
/* 1112 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1113 */     GlStateManager.func_179090_x();
/* 1114 */     GlStateManager.func_179132_a(false);
/* 1115 */     GL11.glEnable(2848);
/* 1116 */     GL11.glHint(3154, 4354);
/* 1117 */     GL11.glLineWidth(linewidth);
/* 1118 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1119 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1120 */     bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/* 1121 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1122 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1123 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1124 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1125 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1126 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1127 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1128 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1129 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1130 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1131 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1132 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1133 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1134 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1135 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1136 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1137 */     tessellator.func_78381_a();
/* 1138 */     GL11.glDisable(2848);
/* 1139 */     GlStateManager.func_179132_a(true);
/* 1140 */     GlStateManager.func_179126_j();
/* 1141 */     GlStateManager.func_179098_w();
/* 1142 */     GlStateManager.func_179084_k();
/* 1143 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawBoxESP(BlockPos pos, Color color, float lineWidth, boolean outline, boolean box, int boxAlpha) {
/* 1147 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/* 1148 */     camera.func_78547_a(((Entity)Objects.requireNonNull((T)mc.func_175606_aa())).field_70165_t, (mc.func_175606_aa()).field_70163_u, (mc.func_175606_aa()).field_70161_v);
/* 1149 */     if (camera.func_78546_a(new AxisAlignedBB(bb.field_72340_a + (mc.func_175598_ae()).field_78730_l, bb.field_72338_b + (mc.func_175598_ae()).field_78731_m, bb.field_72339_c + (mc.func_175598_ae()).field_78728_n, bb.field_72336_d + (mc.func_175598_ae()).field_78730_l, bb.field_72337_e + (mc.func_175598_ae()).field_78731_m, bb.field_72334_f + (mc.func_175598_ae()).field_78728_n))) {
/* 1150 */       GlStateManager.func_179094_E();
/* 1151 */       GlStateManager.func_179147_l();
/* 1152 */       GlStateManager.func_179097_i();
/* 1153 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1154 */       GlStateManager.func_179090_x();
/* 1155 */       GlStateManager.func_179132_a(false);
/* 1156 */       GL11.glEnable(2848);
/* 1157 */       GL11.glHint(3154, 4354);
/* 1158 */       GL11.glLineWidth(lineWidth);
/* 1159 */       double dist = mc.field_71439_g.func_70011_f((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F)) * 0.75D;
/* 1160 */       if (box) {
/* 1161 */         RenderGlobal.func_189696_b(bb, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, boxAlpha / 255.0F);
/*      */       }
/* 1163 */       if (outline) {
/* 1164 */         RenderGlobal.func_189694_a(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*      */       }
/* 1166 */       GL11.glDisable(2848);
/* 1167 */       GlStateManager.func_179132_a(true);
/* 1168 */       GlStateManager.func_179126_j();
/* 1169 */       GlStateManager.func_179098_w();
/* 1170 */       GlStateManager.func_179084_k();
/* 1171 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawText(BlockPos pos, String text) {
/* 1176 */     if (pos == null || text == null) {
/*      */       return;
/*      */     }
/* 1179 */     GlStateManager.func_179094_E();
/* 1180 */     glBillboardDistanceScaled(pos.func_177958_n() + 0.5F, pos.func_177956_o() + 0.5F, pos.func_177952_p() + 0.5F, (EntityPlayer)mc.field_71439_g, 1.0F);
/* 1181 */     GlStateManager.func_179097_i();
/* 1182 */     GlStateManager.func_179137_b(-(Phobos.textManager.getStringWidth(text) / 2.0D), 0.0D, 0.0D);
/* 1183 */     Phobos.textManager.drawStringWithShadow(text, 0.0F, 0.0F, -5592406);
/* 1184 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawOutlinedBlockESP(BlockPos pos, Color color, float linewidth) {
/* 1188 */     IBlockState iblockstate = mc.field_71441_e.func_180495_p(pos);
/* 1189 */     Vec3d interp = EntityUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
/* 1190 */     drawBoundingBox(iblockstate.func_185918_c((World)mc.field_71441_e, pos).func_186662_g(0.0020000000949949026D).func_72317_d(-interp.field_72450_a, -interp.field_72448_b, -interp.field_72449_c), linewidth, ColorUtil.toRGBA(color));
/*      */   }
/*      */   
/*      */   public static void blockEsp(BlockPos blockPos, Color c, double length, double length2) {
/* 1194 */     double x = blockPos.func_177958_n() - mc.field_175616_W.field_78725_b;
/* 1195 */     double y = blockPos.func_177956_o() - mc.field_175616_W.field_78726_c;
/* 1196 */     double z = blockPos.func_177952_p() - mc.field_175616_W.field_78723_d;
/* 1197 */     GL11.glPushMatrix();
/* 1198 */     GL11.glBlendFunc(770, 771);
/* 1199 */     GL11.glEnable(3042);
/* 1200 */     GL11.glLineWidth(2.0F);
/* 1201 */     GL11.glDisable(3553);
/* 1202 */     GL11.glDisable(2929);
/* 1203 */     GL11.glDepthMask(false);
/* 1204 */     GL11.glColor4d((c.getRed() / 255.0F), (c.getGreen() / 255.0F), (c.getBlue() / 255.0F), 0.25D);
/* 1205 */     drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length), 0.0F, 0.0F, 0.0F, 0.0F);
/* 1206 */     GL11.glColor4d(0.0D, 0.0D, 0.0D, 0.5D);
/* 1207 */     drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length));
/* 1208 */     GL11.glLineWidth(2.0F);
/* 1209 */     GL11.glEnable(3553);
/* 1210 */     GL11.glEnable(2929);
/* 1211 */     GL11.glDepthMask(true);
/* 1212 */     GL11.glDisable(3042);
/* 1213 */     GL11.glPopMatrix();
/* 1214 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public static void drawRect(float x, float y, float w, float h, int color) {
/* 1218 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1219 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1220 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1221 */     float blue = (color & 0xFF) / 255.0F;
/* 1222 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1223 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1224 */     GlStateManager.func_179147_l();
/* 1225 */     GlStateManager.func_179090_x();
/* 1226 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1227 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 1228 */     bufferbuilder.func_181662_b(x, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1229 */     bufferbuilder.func_181662_b(w, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1230 */     bufferbuilder.func_181662_b(w, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1231 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1232 */     tessellator.func_78381_a();
/* 1233 */     GlStateManager.func_179098_w();
/* 1234 */     GlStateManager.func_179084_k();
/*      */   }
/*      */   
/*      */   public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
/* 1238 */     Tessellator ts = Tessellator.func_178181_a();
/* 1239 */     BufferBuilder vb = ts.func_178180_c();
/* 1240 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1241 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1242 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1243 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1244 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1245 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1246 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1247 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1248 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1249 */     ts.func_78381_a();
/* 1250 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1251 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1252 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1253 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1254 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1255 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1256 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1257 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1258 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1259 */     ts.func_78381_a();
/* 1260 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1261 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1262 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1263 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1264 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1265 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1266 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1267 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1268 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1269 */     ts.func_78381_a();
/* 1270 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1271 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1272 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1273 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1274 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1275 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1276 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1277 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1278 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1279 */     ts.func_78381_a();
/* 1280 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1281 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1282 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1283 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1284 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1285 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1286 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1287 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1288 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1289 */     ts.func_78381_a();
/* 1290 */     vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/* 1291 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1292 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1293 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1294 */     vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1295 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1296 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1297 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1298 */     vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1299 */     ts.func_78381_a();
/*      */   }
/*      */   
/*      */   public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
/* 1303 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1304 */     BufferBuilder vertexbuffer = tessellator.func_178180_c();
/* 1305 */     vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/* 1306 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
/* 1307 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
/* 1308 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
/* 1309 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
/* 1310 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
/* 1311 */     tessellator.func_78381_a();
/* 1312 */     vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/* 1313 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
/* 1314 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
/* 1315 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
/* 1316 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
/* 1317 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
/* 1318 */     tessellator.func_78381_a();
/* 1319 */     vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
/* 1320 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
/* 1321 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
/* 1322 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
/* 1323 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
/* 1324 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
/* 1325 */     vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
/* 1326 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
/* 1327 */     vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
/* 1328 */     tessellator.func_78381_a();
/*      */   }
/*      */   
/*      */   public static void glrendermethod() {
/* 1332 */     GL11.glEnable(3042);
/* 1333 */     GL11.glBlendFunc(770, 771);
/* 1334 */     GL11.glEnable(2848);
/* 1335 */     GL11.glLineWidth(2.0F);
/* 1336 */     GL11.glDisable(3553);
/* 1337 */     GL11.glEnable(2884);
/* 1338 */     GL11.glDisable(2929);
/* 1339 */     double viewerPosX = (mc.func_175598_ae()).field_78730_l;
/* 1340 */     double viewerPosY = (mc.func_175598_ae()).field_78731_m;
/* 1341 */     double viewerPosZ = (mc.func_175598_ae()).field_78728_n;
/* 1342 */     GL11.glPushMatrix();
/* 1343 */     GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
/*      */   }
/*      */   
/*      */   public static void glStart(float n, float n2, float n3, float n4) {
/* 1347 */     glrendermethod();
/* 1348 */     GL11.glColor4f(n, n2, n3, n4);
/*      */   }
/*      */   
/*      */   public static void glEnd() {
/* 1352 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1353 */     GL11.glPopMatrix();
/* 1354 */     GL11.glEnable(2929);
/* 1355 */     GL11.glEnable(3553);
/* 1356 */     GL11.glDisable(3042);
/* 1357 */     GL11.glDisable(2848);
/*      */   }
/*      */   
/*      */   public static AxisAlignedBB getBoundingBox(BlockPos blockPos) {
/* 1361 */     return mc.field_71441_e.func_180495_p(blockPos).func_185900_c((IBlockAccess)mc.field_71441_e, blockPos).func_186670_a(blockPos);
/*      */   }
/*      */   
/*      */   public static void drawOutlinedBox(AxisAlignedBB axisAlignedBB) {
/* 1365 */     GL11.glBegin(1);
/* 1366 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1367 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1368 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1369 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1370 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1371 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1372 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1373 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1374 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1375 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1376 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c);
/* 1377 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1378 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1379 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1380 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72334_f);
/* 1381 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1382 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1383 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1384 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1385 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1386 */     GL11.glVertex3d(axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1387 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1388 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f);
/* 1389 */     GL11.glVertex3d(axisAlignedBB.field_72340_a, axisAlignedBB.field_72337_e, axisAlignedBB.field_72339_c);
/* 1390 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */   public static void drawFilledBoxESPN(BlockPos pos, Color color) {
/* 1394 */     AxisAlignedBB bb = new AxisAlignedBB(pos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, pos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, pos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (pos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (pos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (pos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
/* 1395 */     int rgba = ColorUtil.toRGBA(color);
/* 1396 */     drawFilledBox(bb, rgba);
/*      */   }
/*      */   
/*      */   public static void drawFilledBox(AxisAlignedBB bb, int color) {
/* 1400 */     GlStateManager.func_179094_E();
/* 1401 */     GlStateManager.func_179147_l();
/* 1402 */     GlStateManager.func_179097_i();
/* 1403 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1404 */     GlStateManager.func_179090_x();
/* 1405 */     GlStateManager.func_179132_a(false);
/* 1406 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1407 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1408 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1409 */     float blue = (color & 0xFF) / 255.0F;
/* 1410 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1411 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1412 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 1413 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1414 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1415 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1416 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1417 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1418 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1419 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1420 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1421 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1422 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1423 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1424 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1425 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1426 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1427 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1428 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1429 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1430 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1431 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1432 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1433 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1434 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1435 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1436 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1437 */     tessellator.func_78381_a();
/* 1438 */     GlStateManager.func_179132_a(true);
/* 1439 */     GlStateManager.func_179126_j();
/* 1440 */     GlStateManager.func_179098_w();
/* 1441 */     GlStateManager.func_179084_k();
/* 1442 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawBoundingBox(AxisAlignedBB bb, float width, int color) {
/* 1446 */     GlStateManager.func_179094_E();
/* 1447 */     GlStateManager.func_179147_l();
/* 1448 */     GlStateManager.func_179097_i();
/* 1449 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1450 */     GlStateManager.func_179090_x();
/* 1451 */     GlStateManager.func_179132_a(false);
/* 1452 */     GL11.glEnable(2848);
/* 1453 */     GL11.glHint(3154, 4354);
/* 1454 */     GL11.glLineWidth(width);
/* 1455 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1456 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1457 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1458 */     float blue = (color & 0xFF) / 255.0F;
/* 1459 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1460 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1461 */     bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/* 1462 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1463 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1464 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1465 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1466 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1467 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1468 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1469 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1470 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1471 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1472 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1473 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1474 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1475 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1476 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1477 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1478 */     tessellator.func_78381_a();
/* 1479 */     GL11.glDisable(2848);
/* 1480 */     GlStateManager.func_179132_a(true);
/* 1481 */     GlStateManager.func_179126_j();
/* 1482 */     GlStateManager.func_179098_w();
/* 1483 */     GlStateManager.func_179084_k();
/* 1484 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void glBillboard(float x, float y, float z) {
/* 1488 */     float scale = 0.02666667F;
/* 1489 */     GlStateManager.func_179137_b(x - (mc.func_175598_ae()).field_78725_b, y - (mc.func_175598_ae()).field_78726_c, z - (mc.func_175598_ae()).field_78723_d);
/* 1490 */     GlStateManager.func_187432_a(0.0F, 1.0F, 0.0F);
/* 1491 */     GlStateManager.func_179114_b(-mc.field_71439_g.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 1492 */     GlStateManager.func_179114_b(mc.field_71439_g.field_70125_A, (mc.field_71474_y.field_74320_O == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
/* 1493 */     GlStateManager.func_179152_a(-scale, -scale, scale);
/*      */   }
/*      */   
/*      */   public static void glBillboardDistanceScaled(float x, float y, float z, EntityPlayer player, float scale) {
/* 1497 */     glBillboard(x, y, z);
/* 1498 */     int distance = (int)player.func_70011_f(x, y, z);
/* 1499 */     float scaleDistance = distance / 2.0F / (2.0F + 2.0F - scale);
/* 1500 */     if (scaleDistance < 1.0F) {
/* 1501 */       scaleDistance = 1.0F;
/*      */     }
/* 1503 */     GlStateManager.func_179152_a(scaleDistance, scaleDistance, scaleDistance);
/*      */   }
/*      */   
/*      */   public static void drawColoredBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
/* 1507 */     GlStateManager.func_179094_E();
/* 1508 */     GlStateManager.func_179147_l();
/* 1509 */     GlStateManager.func_179097_i();
/* 1510 */     GlStateManager.func_179120_a(770, 771, 0, 1);
/* 1511 */     GlStateManager.func_179090_x();
/* 1512 */     GlStateManager.func_179132_a(false);
/* 1513 */     GL11.glEnable(2848);
/* 1514 */     GL11.glHint(3154, 4354);
/* 1515 */     GL11.glLineWidth(width);
/* 1516 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1517 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1518 */     bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/* 1519 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0F).func_181675_d();
/* 1520 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1521 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1522 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1523 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1524 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1525 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1526 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1527 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1528 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1529 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1530 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0F).func_181675_d();
/* 1531 */     bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1532 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, 0.0F).func_181675_d();
/* 1533 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1534 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, 0.0F).func_181675_d();
/* 1535 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1536 */     bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, 0.0F).func_181675_d();
/* 1537 */     tessellator.func_78381_a();
/* 1538 */     GL11.glDisable(2848);
/* 1539 */     GlStateManager.func_179132_a(true);
/* 1540 */     GlStateManager.func_179126_j();
/* 1541 */     GlStateManager.func_179098_w();
/* 1542 */     GlStateManager.func_179084_k();
/* 1543 */     GlStateManager.func_179121_F();
/*      */   }
/*      */   
/*      */   public static void drawSphere(double x, double y, double z, float size, int slices, int stacks) {
/* 1547 */     Sphere s = new Sphere();
/* 1548 */     GL11.glPushMatrix();
/* 1549 */     GL11.glBlendFunc(770, 771);
/* 1550 */     GL11.glEnable(3042);
/* 1551 */     GL11.glLineWidth(1.2F);
/* 1552 */     GL11.glDisable(3553);
/* 1553 */     GL11.glDisable(2929);
/* 1554 */     GL11.glDepthMask(false);
/* 1555 */     s.setDrawStyle(100013);
/* 1556 */     GL11.glTranslated(x - mc.field_175616_W.field_78725_b, y - mc.field_175616_W.field_78726_c, z - mc.field_175616_W.field_78723_d);
/* 1557 */     s.draw(size, slices, stacks);
/* 1558 */     GL11.glLineWidth(2.0F);
/* 1559 */     GL11.glEnable(3553);
/* 1560 */     GL11.glEnable(2929);
/* 1561 */     GL11.glDepthMask(true);
/* 1562 */     GL11.glDisable(3042);
/* 1563 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static void drawBar(GLUProjection.Projection projection, float width, float height, float totalWidth, Color startColor, Color outlineColor) {
/* 1567 */     if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
/* 1568 */       GlStateManager.func_179094_E();
/* 1569 */       GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0D);
/* 1570 */       drawOutlineRect(-(totalWidth / 2.0F), -(height / 2.0F), totalWidth, height, outlineColor.getRGB());
/* 1571 */       drawRect(-(totalWidth / 2.0F), -(height / 2.0F), width, height, startColor.getRGB());
/* 1572 */       GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0D);
/* 1573 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawProjectedText(GLUProjection.Projection projection, float addX, float addY, String text, Color color, boolean shadow) {
/* 1578 */     if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
/* 1579 */       GlStateManager.func_179094_E();
/* 1580 */       GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0D);
/* 1581 */       Phobos.textManager.drawString(text, -(Phobos.textManager.getStringWidth(text) / 2.0F) + addX, addY, color.getRGB(), shadow);
/* 1582 */       GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0D);
/* 1583 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawChungusESP(GLUProjection.Projection projection, float width, float height, ResourceLocation location) {
/* 1588 */     if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
/* 1589 */       GlStateManager.func_179094_E();
/* 1590 */       GlStateManager.func_179137_b(projection.getX(), projection.getY(), 0.0D);
/* 1591 */       mc.func_110434_K().func_110577_a(location);
/* 1592 */       GlStateManager.func_179098_w();
/* 1593 */       GlStateManager.func_179084_k();
/* 1594 */       mc.func_110434_K().func_110577_a(location);
/* 1595 */       drawCompleteImage(0.0F, 0.0F, width, height);
/* 1596 */       mc.func_110434_K().func_147645_c(location);
/* 1597 */       GlStateManager.func_179147_l();
/* 1598 */       GlStateManager.func_179090_x();
/* 1599 */       GlStateManager.func_179137_b(-projection.getX(), -projection.getY(), 0.0D);
/* 1600 */       GlStateManager.func_179121_F();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void drawCompleteImage(float posX, float posY, float width, float height) {
/* 1605 */     GL11.glPushMatrix();
/* 1606 */     GL11.glTranslatef(posX, posY, 0.0F);
/* 1607 */     GL11.glBegin(7);
/* 1608 */     GL11.glTexCoord2f(0.0F, 0.0F);
/* 1609 */     GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/* 1610 */     GL11.glTexCoord2f(0.0F, 1.0F);
/* 1611 */     GL11.glVertex3f(0.0F, height, 0.0F);
/* 1612 */     GL11.glTexCoord2f(1.0F, 1.0F);
/* 1613 */     GL11.glVertex3f(width, height, 0.0F);
/* 1614 */     GL11.glTexCoord2f(1.0F, 0.0F);
/* 1615 */     GL11.glVertex3f(width, 0.0F, 0.0F);
/* 1616 */     GL11.glEnd();
/* 1617 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static void drawOutlineRect(float x, float y, float w, float h, int color) {
/* 1621 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1622 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1623 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1624 */     float blue = (color & 0xFF) / 255.0F;
/* 1625 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1626 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1627 */     GlStateManager.func_179147_l();
/* 1628 */     GlStateManager.func_179090_x();
/* 1629 */     GlStateManager.func_187441_d(1.0F);
/* 1630 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1631 */     bufferbuilder.func_181668_a(2, DefaultVertexFormats.field_181706_f);
/* 1632 */     bufferbuilder.func_181662_b(x, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1633 */     bufferbuilder.func_181662_b(w, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1634 */     bufferbuilder.func_181662_b(w, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1635 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1636 */     tessellator.func_78381_a();
/* 1637 */     GlStateManager.func_179098_w();
/* 1638 */     GlStateManager.func_179084_k();
/*      */   }
/*      */   
/*      */   public static void draw3DRect(float x, float y, float w, float h, Color startColor, Color endColor, float lineWidth) {
/* 1642 */     float alpha = startColor.getAlpha() / 255.0F;
/* 1643 */     float red = startColor.getRed() / 255.0F;
/* 1644 */     float green = startColor.getGreen() / 255.0F;
/* 1645 */     float blue = startColor.getBlue() / 255.0F;
/* 1646 */     float alpha1 = endColor.getAlpha() / 255.0F;
/* 1647 */     float red1 = endColor.getRed() / 255.0F;
/* 1648 */     float green1 = endColor.getGreen() / 255.0F;
/* 1649 */     float blue1 = endColor.getBlue() / 255.0F;
/* 1650 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 1651 */     BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 1652 */     GlStateManager.func_179147_l();
/* 1653 */     GlStateManager.func_179090_x();
/* 1654 */     GlStateManager.func_187441_d(lineWidth);
/* 1655 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 1656 */     bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 1657 */     bufferbuilder.func_181662_b(x, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1658 */     bufferbuilder.func_181662_b(w, h, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1659 */     bufferbuilder.func_181662_b(w, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1660 */     bufferbuilder.func_181662_b(x, y, 0.0D).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 1661 */     tessellator.func_78381_a();
/* 1662 */     GlStateManager.func_179098_w();
/* 1663 */     GlStateManager.func_179084_k();
/*      */   }
/*      */   
/*      */   public static void drawClock(float x, float y, float radius, int slices, int loops, float lineWidth, boolean fill, Color color) {
/* 1667 */     Disk disk = new Disk();
/* 1668 */     Date date = new Date();
/* 1669 */     int hourAngle = 180 + -(Calendar.getInstance().get(10) * 30 + Calendar.getInstance().get(12) / 2);
/* 1670 */     int minuteAngle = 180 + -(Calendar.getInstance().get(12) * 6 + Calendar.getInstance().get(13) / 10);
/* 1671 */     int secondAngle = 180 + -(Calendar.getInstance().get(13) * 6);
/* 1672 */     int totalMinutesTime = Calendar.getInstance().get(12);
/* 1673 */     int totalHoursTime = Calendar.getInstance().get(10);
/* 1674 */     if (fill) {
/* 1675 */       GL11.glPushMatrix();
/* 1676 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 1677 */       GL11.glBlendFunc(770, 771);
/* 1678 */       GL11.glEnable(3042);
/* 1679 */       GL11.glLineWidth(lineWidth);
/* 1680 */       GL11.glDisable(3553);
/* 1681 */       disk.setOrientation(100020);
/* 1682 */       disk.setDrawStyle(100012);
/* 1683 */       GL11.glTranslated(x, y, 0.0D);
/* 1684 */       disk.draw(0.0F, radius, slices, loops);
/* 1685 */       GL11.glEnable(3553);
/* 1686 */       GL11.glDisable(3042);
/* 1687 */       GL11.glPopMatrix();
/*      */     } else {
/* 1689 */       GL11.glPushMatrix();
/* 1690 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 1691 */       GL11.glEnable(3042);
/* 1692 */       GL11.glLineWidth(lineWidth);
/* 1693 */       GL11.glDisable(3553);
/* 1694 */       GL11.glBegin(3);
/* 1695 */       ArrayList<Vec2f> hVectors = new ArrayList<>();
/* 1696 */       float hue = (float)(System.currentTimeMillis() % 7200L) / 7200.0F;
/* 1697 */       for (int i = 0; i <= 360; i++) {
/* 1698 */         Vec2f vec = new Vec2f(x + (float)Math.sin(i * Math.PI / 180.0D) * radius, y + (float)Math.cos(i * Math.PI / 180.0D) * radius);
/* 1699 */         hVectors.add(vec);
/*      */       } 
/* 1701 */       Color color1 = new Color(Color.HSBtoRGB(hue, 1.0F, 1.0F));
/* 1702 */       for (int j = 0; j < hVectors.size() - 1; j++) {
/* 1703 */         GL11.glColor4f(color1.getRed() / 255.0F, color1.getGreen() / 255.0F, color1.getBlue() / 255.0F, color1.getAlpha() / 255.0F);
/* 1704 */         GL11.glVertex3d(((Vec2f)hVectors.get(j)).field_189982_i, ((Vec2f)hVectors.get(j)).field_189983_j, 0.0D);
/* 1705 */         GL11.glVertex3d(((Vec2f)hVectors.get(j + 1)).field_189982_i, ((Vec2f)hVectors.get(j + 1)).field_189983_j, 0.0D);
/* 1706 */         color1 = new Color(Color.HSBtoRGB(hue += 0.0027777778F, 1.0F, 1.0F));
/*      */       } 
/* 1708 */       GL11.glEnd();
/* 1709 */       GL11.glEnable(3553);
/* 1710 */       GL11.glDisable(3042);
/* 1711 */       GL11.glPopMatrix();
/*      */     } 
/* 1713 */     drawLine(x, y, x + (float)Math.sin(hourAngle * Math.PI / 180.0D) * radius / 2.0F, y + (float)Math.cos(hourAngle * Math.PI / 180.0D) * radius / 2.0F, 1.0F, Color.WHITE.getRGB());
/* 1714 */     drawLine(x, y, x + (float)Math.sin(minuteAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), y + (float)Math.cos(minuteAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), 1.0F, Color.WHITE.getRGB());
/* 1715 */     drawLine(x, y, x + (float)Math.sin(secondAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), y + (float)Math.cos(secondAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), 1.0F, Color.RED.getRGB());
/*      */   }
/*      */   
/*      */   public static void GLPre(float lineWidth) {
/* 1719 */     depth = GL11.glIsEnabled(2896);
/* 1720 */     texture = GL11.glIsEnabled(3042);
/* 1721 */     clean = GL11.glIsEnabled(3553);
/* 1722 */     bind = GL11.glIsEnabled(2929);
/* 1723 */     override = GL11.glIsEnabled(2848);
/* 1724 */     GLPre(depth, texture, clean, bind, override, lineWidth);
/*      */   }
/*      */   
/*      */   public static void GlPost() {
/* 1728 */     GLPost(depth, texture, clean, bind, override);
/*      */   }
/*      */   
/*      */   private static void GLPre(boolean depth, boolean texture, boolean clean, boolean bind, boolean override, float lineWidth) {
/* 1732 */     if (depth) {
/* 1733 */       GL11.glDisable(2896);
/*      */     }
/* 1735 */     if (!texture) {
/* 1736 */       GL11.glEnable(3042);
/*      */     }
/* 1738 */     GL11.glLineWidth(lineWidth);
/* 1739 */     if (clean) {
/* 1740 */       GL11.glDisable(3553);
/*      */     }
/* 1742 */     if (bind) {
/* 1743 */       GL11.glDisable(2929);
/*      */     }
/* 1745 */     if (!override) {
/* 1746 */       GL11.glEnable(2848);
/*      */     }
/* 1748 */     GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 1749 */     GL11.glHint(3154, 4354);
/* 1750 */     GlStateManager.func_179132_a(false);
/*      */   }
/*      */   
/*      */   public static float[][] getBipedRotations(ModelBiped biped) {
/* 1754 */     float[][] rotations = new float[5][];
/* 1755 */     float[] headRotation = { biped.field_78116_c.field_78795_f, biped.field_78116_c.field_78796_g, biped.field_78116_c.field_78808_h };
/* 1756 */     rotations[0] = headRotation;
/* 1757 */     float[] rightArmRotation = { biped.field_178723_h.field_78795_f, biped.field_178723_h.field_78796_g, biped.field_178723_h.field_78808_h };
/* 1758 */     rotations[1] = rightArmRotation;
/* 1759 */     float[] leftArmRotation = { biped.field_178724_i.field_78795_f, biped.field_178724_i.field_78796_g, biped.field_178724_i.field_78808_h };
/* 1760 */     rotations[2] = leftArmRotation;
/* 1761 */     float[] rightLegRotation = { biped.field_178721_j.field_78795_f, biped.field_178721_j.field_78796_g, biped.field_178721_j.field_78808_h };
/* 1762 */     rotations[3] = rightLegRotation;
/* 1763 */     float[] leftLegRotation = { biped.field_178722_k.field_78795_f, biped.field_178722_k.field_78796_g, biped.field_178722_k.field_78808_h };
/* 1764 */     rotations[4] = leftLegRotation;
/* 1765 */     return rotations;
/*      */   }
/*      */   
/*      */   private static void GLPost(boolean depth, boolean texture, boolean clean, boolean bind, boolean override) {
/* 1769 */     GlStateManager.func_179132_a(true);
/* 1770 */     if (!override) {
/* 1771 */       GL11.glDisable(2848);
/*      */     }
/* 1773 */     if (bind) {
/* 1774 */       GL11.glEnable(2929);
/*      */     }
/* 1776 */     if (clean) {
/* 1777 */       GL11.glEnable(3553);
/*      */     }
/* 1779 */     if (!texture) {
/* 1780 */       GL11.glDisable(3042);
/*      */     }
/* 1782 */     if (depth) {
/* 1783 */       GL11.glEnable(2896);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void drawArc(float cx, float cy, float r, float start_angle, float end_angle, int num_segments) {
/* 1788 */     GL11.glBegin(4);
/* 1789 */     int i = (int)(num_segments / 360.0F / start_angle) + 1;
/* 1790 */     while (i <= num_segments / 360.0F / end_angle) {
/* 1791 */       double previousangle = 6.283185307179586D * (i - 1) / num_segments;
/* 1792 */       double angle = 6.283185307179586D * i / num_segments;
/* 1793 */       GL11.glVertex2d(cx, cy);
/* 1794 */       GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
/* 1795 */       GL11.glVertex2d(cx + Math.cos(previousangle) * r, cy + Math.sin(previousangle) * r);
/* 1796 */       i++;
/*      */     } 
/* 1798 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void drawArcOutline(float cx, float cy, float r, float start_angle, float end_angle, int num_segments) {
/* 1802 */     GL11.glBegin(2);
/* 1803 */     int i = (int)(num_segments / 360.0F / start_angle) + 1;
/* 1804 */     while (i <= num_segments / 360.0F / end_angle) {
/* 1805 */       double angle = 6.283185307179586D * i / num_segments;
/* 1806 */       GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
/* 1807 */       i++;
/*      */     } 
/* 1809 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void drawCircleOutline(float x, float y, float radius) {
/* 1813 */     drawCircleOutline(x, y, radius, 0, 360, 40);
/*      */   }
/*      */   
/*      */   public static void drawCircleOutline(float x, float y, float radius, int start, int end, int segments) {
/* 1817 */     drawArcOutline(x, y, radius, start, end, segments);
/*      */   }
/*      */   
/*      */   public static void drawCircle(float x, float y, float radius) {
/* 1821 */     drawCircle(x, y, radius, 0, 360, 64);
/*      */   }
/*      */   
/*      */   public static void drawCircle(float x, float y, float radius, int start, int end, int segments) {
/* 1825 */     drawArc(x, y, radius, start, end, segments);
/*      */   }
/*      */   
/*      */   public static void drawOutlinedRoundedRectangle(int x, int y, int width, int height, float radius, float dR, float dG, float dB, float dA, float outlineWidth) {
/* 1829 */     drawRoundedRectangle(x, y, width, height, radius);
/* 1830 */     GL11.glColor4f(dR, dG, dB, dA);
/* 1831 */     drawRoundedRectangle(x + outlineWidth, y + outlineWidth, width - outlineWidth * 2.0F, height - outlineWidth * 2.0F, radius);
/*      */   }
/*      */   
/*      */   public static void drawRectangle(float x, float y, float width, float height) {
/* 1835 */     GL11.glEnable(3042);
/* 1836 */     GL11.glBlendFunc(770, 771);
/* 1837 */     GL11.glBegin(2);
/* 1838 */     GL11.glVertex2d(width, 0.0D);
/* 1839 */     GL11.glVertex2d(0.0D, 0.0D);
/* 1840 */     GL11.glVertex2d(0.0D, height);
/* 1841 */     GL11.glVertex2d(width, height);
/* 1842 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void drawRectangleXY(float x, float y, float width, float height) {
/* 1846 */     GL11.glEnable(3042);
/* 1847 */     GL11.glBlendFunc(770, 771);
/* 1848 */     GL11.glBegin(2);
/* 1849 */     GL11.glVertex2d((x + width), y);
/* 1850 */     GL11.glVertex2d(x, y);
/* 1851 */     GL11.glVertex2d(x, (y + height));
/* 1852 */     GL11.glVertex2d((x + width), (y + height));
/* 1853 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void drawFilledRectangle(float x, float y, float width, float height) {
/* 1857 */     GL11.glEnable(3042);
/* 1858 */     GL11.glBlendFunc(770, 771);
/* 1859 */     GL11.glBegin(7);
/* 1860 */     GL11.glVertex2d((x + width), y);
/* 1861 */     GL11.glVertex2d(x, y);
/* 1862 */     GL11.glVertex2d(x, (y + height));
/* 1863 */     GL11.glVertex2d((x + width), (y + height));
/* 1864 */     glEnd();
/*      */   }
/*      */   
/*      */   public static Vec3d to2D(double x, double y, double z) {
/* 1868 */     GL11.glGetFloat(2982, modelView);
/* 1869 */     GL11.glGetFloat(2983, projection);
/* 1870 */     GL11.glGetInteger(2978, viewport);
/* 1871 */     boolean result = GLU.gluProject((float)x, (float)y, (float)z, modelView, projection, viewport, screenCoords);
/* 1872 */     if (result) {
/* 1873 */       return new Vec3d(screenCoords.get(0), (Display.getHeight() - screenCoords.get(1)), screenCoords.get(2));
/*      */     }
/* 1875 */     return null;
/*      */   }
/*      */   
/*      */   public static void drawTracerPointer(float x, float y, float size, float widthDiv, float heightDiv, boolean outline, float outlineWidth, int color) {
/* 1879 */     boolean blend = GL11.glIsEnabled(3042);
/* 1880 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1881 */     GL11.glEnable(3042);
/* 1882 */     GL11.glDisable(3553);
/* 1883 */     GL11.glBlendFunc(770, 771);
/* 1884 */     GL11.glEnable(2848);
/* 1885 */     GL11.glPushMatrix();
/* 1886 */     hexColor(color);
/* 1887 */     GL11.glBegin(7);
/* 1888 */     GL11.glVertex2d(x, y);
/* 1889 */     GL11.glVertex2d((x - size / widthDiv), (y + size));
/* 1890 */     GL11.glVertex2d(x, (y + size / heightDiv));
/* 1891 */     GL11.glVertex2d((x + size / widthDiv), (y + size));
/* 1892 */     GL11.glVertex2d(x, y);
/* 1893 */     GL11.glEnd();
/* 1894 */     if (outline) {
/* 1895 */       GL11.glLineWidth(outlineWidth);
/* 1896 */       GL11.glColor4f(0.0F, 0.0F, 0.0F, alpha);
/* 1897 */       GL11.glBegin(2);
/* 1898 */       GL11.glVertex2d(x, y);
/* 1899 */       GL11.glVertex2d((x - size / widthDiv), (y + size));
/* 1900 */       GL11.glVertex2d(x, (y + size / heightDiv));
/* 1901 */       GL11.glVertex2d((x + size / widthDiv), (y + size));
/* 1902 */       GL11.glVertex2d(x, y);
/* 1903 */       GL11.glEnd();
/*      */     } 
/* 1905 */     GL11.glPopMatrix();
/* 1906 */     GL11.glEnable(3553);
/* 1907 */     if (!blend) {
/* 1908 */       GL11.glDisable(3042);
/*      */     }
/* 1910 */     GL11.glDisable(2848);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rotationHelper(float xAngle, float yAngle, float zAngle) {
/* 1915 */     GlStateManager.func_179114_b(yAngle, 0.0F, 1.0F, 0.0F);
/* 1916 */     GlStateManager.func_179114_b(zAngle, 0.0F, 0.0F, 1.0F);
/* 1917 */     GlStateManager.func_179114_b(xAngle, 1.0F, 0.0F, 0.0F);
/*      */   }
/*      */   
/*      */   public static int getRainbow(int speed, int offset, float s, float b) {
/* 1921 */     float hue = (float)((System.currentTimeMillis() + offset) % speed);
/* 1922 */     return Color.getHSBColor(hue /= speed, s, b).getRGB();
/*      */   }
/*      */   
/*      */   public static void hexColor(int hexColor) {
/* 1926 */     float red = (hexColor >> 16 & 0xFF) / 255.0F;
/* 1927 */     float green = (hexColor >> 8 & 0xFF) / 255.0F;
/* 1928 */     float blue = (hexColor & 0xFF) / 255.0F;
/* 1929 */     float alpha = (hexColor >> 24 & 0xFF) / 255.0F;
/* 1930 */     GL11.glColor4f(red, green, blue, alpha);
/*      */   }
/*      */   
/*      */   public static boolean isInViewFrustrum(Entity entity) {
/* 1934 */     return (isInViewFrustrum(entity.func_174813_aQ()) || entity.field_70158_ak);
/*      */   }
/*      */   
/*      */   public static boolean isInViewFrustrum(AxisAlignedBB bb) {
/* 1938 */     Entity current = Minecraft.func_71410_x().func_175606_aa();
/* 1939 */     frustrum.func_78547_a(current.field_70165_t, current.field_70163_u, current.field_70161_v);
/* 1940 */     return frustrum.func_78546_a(bb);
/*      */   }
/*      */   
/*      */   public static void drawRoundedRectangle(float x, float y, float width, float height, float radius) {
/* 1944 */     GL11.glEnable(3042);
/* 1945 */     drawArc(x + width - radius, y + height - radius, radius, 0.0F, 90.0F, 16);
/* 1946 */     drawArc(x + radius, y + height - radius, radius, 90.0F, 180.0F, 16);
/* 1947 */     drawArc(x + radius, y + radius, radius, 180.0F, 270.0F, 16);
/* 1948 */     drawArc(x + width - radius, y + radius, radius, 270.0F, 360.0F, 16);
/* 1949 */     GL11.glBegin(4);
/* 1950 */     GL11.glVertex2d((x + width - radius), y);
/* 1951 */     GL11.glVertex2d((x + radius), y);
/* 1952 */     GL11.glVertex2d((x + width - radius), (y + radius));
/* 1953 */     GL11.glVertex2d((x + width - radius), (y + radius));
/* 1954 */     GL11.glVertex2d((x + radius), y);
/* 1955 */     GL11.glVertex2d((x + radius), (y + radius));
/* 1956 */     GL11.glVertex2d((x + width), (y + radius));
/* 1957 */     GL11.glVertex2d(x, (y + radius));
/* 1958 */     GL11.glVertex2d(x, (y + height - radius));
/* 1959 */     GL11.glVertex2d((x + width), (y + radius));
/* 1960 */     GL11.glVertex2d(x, (y + height - radius));
/* 1961 */     GL11.glVertex2d((x + width), (y + height - radius));
/* 1962 */     GL11.glVertex2d((x + width - radius), (y + height - radius));
/* 1963 */     GL11.glVertex2d((x + radius), (y + height - radius));
/* 1964 */     GL11.glVertex2d((x + width - radius), (y + height));
/* 1965 */     GL11.glVertex2d((x + width - radius), (y + height));
/* 1966 */     GL11.glVertex2d((x + radius), (y + height - radius));
/* 1967 */     GL11.glVertex2d((x + radius), (y + height));
/* 1968 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void renderOne(float lineWidth) {
/* 1972 */     checkSetupFBO();
/* 1973 */     GL11.glPushAttrib(1048575);
/* 1974 */     GL11.glDisable(3008);
/* 1975 */     GL11.glDisable(3553);
/* 1976 */     GL11.glDisable(2896);
/* 1977 */     GL11.glEnable(3042);
/* 1978 */     GL11.glBlendFunc(770, 771);
/* 1979 */     GL11.glLineWidth(lineWidth);
/* 1980 */     GL11.glEnable(2848);
/* 1981 */     GL11.glEnable(2960);
/* 1982 */     GL11.glClear(1024);
/* 1983 */     GL11.glClearStencil(15);
/* 1984 */     GL11.glStencilFunc(512, 1, 15);
/* 1985 */     GL11.glStencilOp(7681, 7681, 7681);
/* 1986 */     GL11.glPolygonMode(1032, 6913);
/*      */   }
/*      */   
/*      */   public static void renderTwo() {
/* 1990 */     GL11.glStencilFunc(512, 0, 15);
/* 1991 */     GL11.glStencilOp(7681, 7681, 7681);
/* 1992 */     GL11.glPolygonMode(1032, 6914);
/*      */   }
/*      */   
/*      */   public static void renderThree() {
/* 1996 */     GL11.glStencilFunc(514, 1, 15);
/* 1997 */     GL11.glStencilOp(7680, 7680, 7680);
/* 1998 */     GL11.glPolygonMode(1032, 6913);
/*      */   }
/*      */   
/*      */   public static void renderFour(Color color) {
/* 2002 */     setColor(color);
/* 2003 */     GL11.glDepthMask(false);
/* 2004 */     GL11.glDisable(2929);
/* 2005 */     GL11.glEnable(10754);
/* 2006 */     GL11.glPolygonOffset(1.0F, -2000000.0F);
/* 2007 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
/*      */   }
/*      */   
/*      */   public static void renderFive() {
/* 2011 */     GL11.glPolygonOffset(1.0F, 2000000.0F);
/* 2012 */     GL11.glDisable(10754);
/* 2013 */     GL11.glEnable(2929);
/* 2014 */     GL11.glDepthMask(true);
/* 2015 */     GL11.glDisable(2960);
/* 2016 */     GL11.glDisable(2848);
/* 2017 */     GL11.glHint(3154, 4352);
/* 2018 */     GL11.glEnable(3042);
/* 2019 */     GL11.glEnable(2896);
/* 2020 */     GL11.glEnable(3553);
/* 2021 */     GL11.glEnable(3008);
/* 2022 */     GL11.glPopAttrib();
/*      */   }
/*      */   
/*      */   public static void setColor(Color color) {
/* 2026 */     GL11.glColor4d(color.getRed() / 255.0D, color.getGreen() / 255.0D, color.getBlue() / 255.0D, color.getAlpha() / 255.0D);
/*      */   }
/*      */   
/*      */   public static void checkSetupFBO() {
/* 2030 */     Framebuffer fbo = mc.field_147124_at;
/* 2031 */     if (fbo != null && fbo.field_147624_h > -1) {
/* 2032 */       setupFBO(fbo);
/* 2033 */       fbo.field_147624_h = -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void setupFBO(Framebuffer fbo) {
/* 2038 */     EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.field_147624_h);
/* 2039 */     int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
/* 2040 */     EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferID);
/* 2041 */     EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, mc.field_71443_c, mc.field_71440_d);
/* 2042 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferID);
/* 2043 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferID);
/*      */   }
/*      */   
/*      */   public static class RenderTesselator
/*      */     extends Tessellator {
/* 2048 */     public static RenderTesselator INSTANCE = new RenderTesselator();
/*      */     
/*      */     public RenderTesselator() {
/* 2051 */       super(2097152);
/*      */     }
/*      */     
/*      */     public static void prepare(int mode) {
/* 2055 */       prepareGL();
/* 2056 */       begin(mode);
/*      */     }
/*      */     
/*      */     public static void prepareGL() {
/* 2060 */       GL11.glBlendFunc(770, 771);
/* 2061 */       GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 2062 */       GlStateManager.func_187441_d(1.5F);
/* 2063 */       GlStateManager.func_179090_x();
/* 2064 */       GlStateManager.func_179132_a(false);
/* 2065 */       GlStateManager.func_179147_l();
/* 2066 */       GlStateManager.func_179097_i();
/* 2067 */       GlStateManager.func_179140_f();
/* 2068 */       GlStateManager.func_179129_p();
/* 2069 */       GlStateManager.func_179141_d();
/* 2070 */       GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
/*      */     }
/*      */     
/*      */     public static void begin(int mode) {
/* 2074 */       INSTANCE.func_178180_c().func_181668_a(mode, DefaultVertexFormats.field_181706_f);
/*      */     }
/*      */     
/*      */     public static void release() {
/* 2078 */       render();
/* 2079 */       releaseGL();
/*      */     }
/*      */     
/*      */     public static void render() {
/* 2083 */       INSTANCE.func_78381_a();
/*      */     }
/*      */     
/*      */     public static void releaseGL() {
/* 2087 */       GlStateManager.func_179089_o();
/* 2088 */       GlStateManager.func_179132_a(true);
/* 2089 */       GlStateManager.func_179098_w();
/* 2090 */       GlStateManager.func_179147_l();
/* 2091 */       GlStateManager.func_179126_j();
/*      */     }
/*      */     
/*      */     public static void drawBox(BlockPos blockPos, int argb, int sides) {
/* 2095 */       int a = argb >>> 24 & 0xFF;
/* 2096 */       int r = argb >>> 16 & 0xFF;
/* 2097 */       int g = argb >>> 8 & 0xFF;
/* 2098 */       int b = argb & 0xFF;
/* 2099 */       drawBox(blockPos, r, g, b, a, sides);
/*      */     }
/*      */     
/*      */     public static void drawBox(float x, float y, float z, int argb, int sides) {
/* 2103 */       int a = argb >>> 24 & 0xFF;
/* 2104 */       int r = argb >>> 16 & 0xFF;
/* 2105 */       int g = argb >>> 8 & 0xFF;
/* 2106 */       int b = argb & 0xFF;
/* 2107 */       drawBox(INSTANCE.func_178180_c(), x, y, z, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */     
/*      */     public static void drawBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
/* 2111 */       drawBox(INSTANCE.func_178180_c(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */     
/*      */     public static BufferBuilder getBufferBuilder() {
/* 2115 */       return INSTANCE.func_178180_c();
/*      */     }
/*      */     
/*      */     public static void drawBox(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
/* 2119 */       if ((sides & 0x1) != 0) {
/* 2120 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2121 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2122 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2123 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2125 */       if ((sides & 0x2) != 0) {
/* 2126 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2127 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2128 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2129 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2131 */       if ((sides & 0x4) != 0) {
/* 2132 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2133 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2134 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2135 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2137 */       if ((sides & 0x8) != 0) {
/* 2138 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2139 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2140 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2141 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2143 */       if ((sides & 0x10) != 0) {
/* 2144 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2145 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2146 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2147 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2149 */       if ((sides & 0x20) != 0) {
/* 2150 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2151 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2152 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2153 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/*      */     }
/*      */     
/*      */     public static void drawLines(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
/* 2158 */       if ((sides & 0x11) != 0) {
/* 2159 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2160 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2162 */       if ((sides & 0x12) != 0) {
/* 2163 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2164 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2166 */       if ((sides & 0x21) != 0) {
/* 2167 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2168 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2170 */       if ((sides & 0x22) != 0) {
/* 2171 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2172 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2174 */       if ((sides & 0x5) != 0) {
/* 2175 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2176 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2178 */       if ((sides & 0x6) != 0) {
/* 2179 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/* 2180 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2182 */       if ((sides & 0x9) != 0) {
/* 2183 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2184 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2186 */       if ((sides & 0xA) != 0) {
/* 2187 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2188 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2190 */       if ((sides & 0x14) != 0) {
/* 2191 */         buffer.func_181662_b(x, y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2192 */         buffer.func_181662_b(x, (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2194 */       if ((sides & 0x24) != 0) {
/* 2195 */         buffer.func_181662_b((x + w), y, z).func_181669_b(r, g, b, a).func_181675_d();
/* 2196 */         buffer.func_181662_b((x + w), (y + h), z).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2198 */       if ((sides & 0x18) != 0) {
/* 2199 */         buffer.func_181662_b(x, y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2200 */         buffer.func_181662_b(x, (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/* 2202 */       if ((sides & 0x28) != 0) {
/* 2203 */         buffer.func_181662_b((x + w), y, (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/* 2204 */         buffer.func_181662_b((x + w), (y + h), (z + d)).func_181669_b(r, g, b, a).func_181675_d();
/*      */       } 
/*      */     }
/*      */     
/*      */     public static void drawBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
/* 2209 */       GlStateManager.func_179094_E();
/* 2210 */       GlStateManager.func_179147_l();
/* 2211 */       GlStateManager.func_179097_i();
/* 2212 */       GlStateManager.func_179120_a(770, 771, 0, 1);
/* 2213 */       GlStateManager.func_179090_x();
/* 2214 */       GlStateManager.func_179132_a(false);
/* 2215 */       GL11.glEnable(2848);
/* 2216 */       GL11.glHint(3154, 4354);
/* 2217 */       GL11.glLineWidth(width);
/* 2218 */       Tessellator tessellator = Tessellator.func_178181_a();
/* 2219 */       BufferBuilder bufferbuilder = tessellator.func_178180_c();
/* 2220 */       bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
/* 2221 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2222 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2223 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2224 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2225 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2226 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2227 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2228 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2229 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2230 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2231 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2232 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2233 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2234 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2235 */       bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2236 */       bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
/* 2237 */       tessellator.func_78381_a();
/* 2238 */       GL11.glDisable(2848);
/* 2239 */       GlStateManager.func_179132_a(true);
/* 2240 */       GlStateManager.func_179126_j();
/* 2241 */       GlStateManager.func_179098_w();
/* 2242 */       GlStateManager.func_179084_k();
/* 2243 */       GlStateManager.func_179121_F();
/*      */     }
/*      */     
/*      */     public static void drawFullBox(AxisAlignedBB bb, BlockPos blockPos, float width, int argb, int alpha2) {
/* 2247 */       int a = argb >>> 24 & 0xFF;
/* 2248 */       int r = argb >>> 16 & 0xFF;
/* 2249 */       int g = argb >>> 8 & 0xFF;
/* 2250 */       int b = argb & 0xFF;
/* 2251 */       drawFullBox(bb, blockPos, width, r, g, b, a, alpha2);
/*      */     }
/*      */     
/*      */     public static void drawFullBox(AxisAlignedBB bb, BlockPos blockPos, float width, int red, int green, int blue, int alpha, int alpha2) {
/* 2255 */       prepare(7);
/* 2256 */       drawBox(blockPos, red, green, blue, alpha, 63);
/* 2257 */       release();
/* 2258 */       drawBoundingBox(bb, width, red, green, blue, alpha2);
/*      */     }
/*      */     
/*      */     public static void drawHalfBox(BlockPos blockPos, int argb, int sides) {
/* 2262 */       int a = argb >>> 24 & 0xFF;
/* 2263 */       int r = argb >>> 16 & 0xFF;
/* 2264 */       int g = argb >>> 8 & 0xFF;
/* 2265 */       int b = argb & 0xFF;
/* 2266 */       drawHalfBox(blockPos, r, g, b, a, sides);
/*      */     }
/*      */     
/*      */     public static void drawHalfBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
/* 2270 */       drawBox(INSTANCE.func_178180_c(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, 0.5F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class GeometryMasks {
/* 2275 */     public static final HashMap<EnumFacing, Integer> FACEMAP = new HashMap<>();
/*      */     
/*      */     static {
/* 2278 */       FACEMAP.put(EnumFacing.DOWN, Integer.valueOf(1));
/* 2279 */       FACEMAP.put(EnumFacing.WEST, Integer.valueOf(16));
/* 2280 */       FACEMAP.put(EnumFacing.NORTH, Integer.valueOf(4));
/* 2281 */       FACEMAP.put(EnumFacing.SOUTH, Integer.valueOf(8));
/* 2282 */       FACEMAP.put(EnumFacing.EAST, Integer.valueOf(32));
/* 2283 */       FACEMAP.put(EnumFacing.UP, Integer.valueOf(2));
/*      */     }
/*      */     
/*      */     public static final class Line {
/*      */       public static final int DOWN_WEST = 17;
/*      */       public static final int UP_WEST = 18;
/*      */       public static final int DOWN_EAST = 33;
/*      */       public static final int UP_EAST = 34;
/*      */       public static final int DOWN_NORTH = 5;
/*      */       public static final int UP_NORTH = 6;
/*      */       public static final int DOWN_SOUTH = 9;
/*      */       public static final int UP_SOUTH = 10;
/*      */       public static final int NORTH_WEST = 20;
/*      */       public static final int NORTH_EAST = 36;
/*      */       public static final int SOUTH_WEST = 24;
/*      */       public static final int SOUTH_EAST = 40;
/*      */       public static final int ALL = 63;
/*      */     }
/*      */     
/*      */     public static final class Quad {
/*      */       public static final int DOWN = 1;
/*      */       public static final int UP = 2;
/*      */       public static final int NORTH = 4;
/*      */       public static final int SOUTH = 8;
/*      */       public static final int WEST = 16;
/*      */       public static final int EAST = 32;
/*      */       public static final int ALL = 63;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobo\\util\RenderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */